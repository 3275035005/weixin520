package com.cn.teaching.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.teaching.entity.TtUser;
import com.cn.teaching.service.TtClassService;
import com.cn.teaching.service.TtCourseService;
import com.cn.teaching.service.TtTeacherService;
import com.cn.teaching.service.TtUserService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import com.cn.teaching.utils.utils.AceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表信息 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-user")
public class TtUserController {


    @Autowired
    private TtUserService ttUserService;

    @Autowired
    private TtCourseService ttCourseService;

    @Autowired
    private TtClassService ttClassService;

    @Autowired
    private TtTeacherService ttTeacherService;

    /**
     * 分页条件查询
     * @param page   当前页码
     * @param limit 每页的大小
     * @param data 封装查询条件数据
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public R getPageData(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestBody TtUser data){
        PageResult pageResult = ttUserService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtUser data){
        ttUserService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody TtUser data){
        // MD5单向加密
        data.setPassword(AceUtils.string2MD5(data.getPassword()));
        ttUserService.save(data);
        return R.ok();
    }

    /**
     * 重置密码 默认密码666666
     * @param id
     * @return
     */
    @PostMapping("resetPassword/{id}")
    public R resetPassword(@PathVariable String id){
        TtUser pUser = ttUserService.getById(id);
        // MD5单向加密
        pUser.setPassword(AceUtils.string2MD5("666666"));
        ttUserService.updateById(pUser);
        return R.ok();
    }
    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttUserService.removeById(id);
        return R.ok();
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @GetMapping("getUserAll")
    public R getUserAll(){
        LambdaQueryWrapper<TtUser> qw = new LambdaQueryWrapper<>();
        qw.eq(TtUser::getUserType, "2");
        qw.orderByAsc(TtUser::getCreateTime);
        List<TtUser> list = ttUserService.list(qw);
        return R.ok().data("row", list);
    }

    /**
     * 修改密码
     */
    @PostMapping("updatePassword")
    public R updatePassword(@RequestBody TtUser data){
        TtUser pUser = ttUserService.getById(data.getId());

        // 加密旧密码
        String oldPassword = AceUtils.string2MD5(data.getOldPassword());

        if(!oldPassword.equals(pUser.getPassword())){
            return R.error("旧密码不正确");
        }
        // 更新密码
        pUser.setPassword(AceUtils.string2MD5(data.getPassword()));
        ttUserService.updateById(pUser);
        return R.ok();
    }

    /**
     * 通过用户id获取用户信息
     */
    @GetMapping("getUserInfo")
    public R getUserInfo(String userId){
        TtUser pUser = ttUserService.getById(userId);
        return R.ok().data("data", pUser);
    }

    /**
     * 登录功能
     */
    @PostMapping("login")
    public R login(HttpServletRequest request, @RequestBody TtUser data){
        QueryWrapper<TtUser> qw = new QueryWrapper<>();
        LambdaQueryWrapper<TtUser> lambda = qw.lambda();
        lambda.eq(TtUser::getUsername, data.getUsername());
        TtUser pUser = ttUserService.getOne(qw);
        if(pUser == null){
            return R.error("账号不存在");
        }
        String string2MD5Password = AceUtils.string2MD5(data.getPassword());
        // 判断密码
        if(!string2MD5Password.equals(pUser.getPassword())){
            return R.error("密码不正确");
        }
        if(!"1".equals(pUser.getUserType())){
            return R.error("请登录管理员账号");
        }
        String status = pUser.getStatus();

        // 判断账号状态
        if(!"1".equals(status)){
            return R.error("账号已被禁用");
        }

        return R.ok().data("token",pUser.getId());
    }



    /**
     * 通过用户id获取信息
     * @return
     */
    @GetMapping("info")
    public R info(String token){
        TtUser pUser = ttUserService.getById(token);
        pUser.setRoles(new String[]{"admin"});
        return R.ok().data("data",pUser);
    }


    /**
     * 通过用户id获取信息
     * @return
     */
    @GetMapping("getHome")
    public R getHome(){

        LambdaQueryWrapper<TtUser> qw = new LambdaQueryWrapper<>();
        qw.eq(TtUser::getUserType, "2");
        int userCount = ttUserService.count(qw);
        int courseCount = ttCourseService.count(null);
        int classCount = ttClassService.count(null);
        int teacherCount = ttTeacherService.count(null);
        Map<String, Integer> map = new HashMap<>();
        map.put("userCount", userCount);
        map.put("courseCount", courseCount);
        map.put("classCount", classCount);
        map.put("teacherCount", teacherCount);

        return R.ok().data("data",map);
    }
}

