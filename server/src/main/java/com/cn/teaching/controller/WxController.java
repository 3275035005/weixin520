package com.cn.teaching.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cn.teaching.entity.*;
import com.cn.teaching.service.*;
import com.cn.teaching.utils.response.R;
import com.cn.teaching.utils.utils.AceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  对微信小程序提供后端接口
 * </p>
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private TtUserService ttUserService;

    @Autowired
    private TtBannerService ttBannerService;

    @Autowired
    private TtNoticeService ttNoticeService;

    @Autowired
    private TtCourseService ttCourseService;

    @Autowired
    private TtTeacherService ttTeacherService;

    @Autowired
    private TtClassService ttClassService;

    @Autowired
    private TtCourseCategoryService ttCourseCategoryService;

    @Autowired
    private TtCourseAppointmentService ttCourseAppointmentService;

    @Autowired
    private TtScoreService ttScoreService;

    /**
     * 登录功能
     */
    @PostMapping("login")
    public R login(@RequestBody TtUser data){
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
        if(!"2".equals(pUser.getUserType())){
            return R.error("请登录学生账号");
        }
        String status = pUser.getStatus();

        // 判断账号状态
        if(!"1".equals(status)){
            return R.error("账号已被禁用");
        }

        return R.ok().data("token",pUser.getId());
    }


    /**
     * 注册功能
     * @param data
     * @return
     */
    @PostMapping("register")
    public R register(@RequestBody TtUser data){
        // MD5单向加密
        data.setPassword(AceUtils.string2MD5(data.getPassword()));
        data.setStatus("1");
        data.setUserType("2");
        ttUserService.save(data);
        return R.ok();
    }

    /**
     * 查询所有的班级
     * @return
     */
    @GetMapping("getClass")
    public R getClassList(){
        LambdaQueryWrapper<TtClass> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(TtClass::getCreateTime);
        List<TtClass> list = ttClassService.list(qw);
        return R.ok().data("row", list);
    }

    /**
     * 查询所有的课程分类
     * @return
     */
    @GetMapping("getCategory")
    public R getCategory(){
        LambdaQueryWrapper<TtCourseCategory> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(TtCourseCategory::getCreateTime);
        List<TtCourseCategory> list = ttCourseCategoryService.list(qw);
        return R.ok().data("row", list);
    }


    /**
     * 首页数据查询
     *      1、轮播图查询
     *      2、通知查询
     *      3、热门课程查询
     */
    @GetMapping("getHome")
    public R getHome(){
        // 查询轮播图
        List<TtBanner> banners = ttBannerService.list(null);

        // 查询通知
        List<TtNotice> notices = ttNoticeService.list(null);

        // 查询热门课程
        List<TtCourse> courses = ttCourseService.list(new QueryWrapper<TtCourse>().eq("hot", "1"));

        Map<String, Object> map = new HashMap<>();
        map.put("banners",banners);
        map.put("notices",notices);
        map.put("courses",courses);
        return R.ok().data("row", map);
    }

    /**
     * 查询所有最新通知
     */
    @GetMapping("getNotice")
    public R getNotice(){
        List<TtNotice> noticeList = ttNoticeService.list(new QueryWrapper<TtNotice>().orderByAsc("sort"));
        return R.ok().data("row", noticeList);
    }

    /**
     * 查询所有师资介绍
     */
    @GetMapping("getTeacher")
    public R getTeacher(){
        List<TtTeacher> teacherList = ttTeacherService.list(new QueryWrapper<TtTeacher>().orderByDesc("create_time"));
        return R.ok().data("row", teacherList);
    }


    /**
     * 查询所有课程预约的未结束
     */
    @GetMapping("getCourse")
    public R getCourse(String categoryId){
        List<TtCourse>courseList = ttCourseService.getCourseList(categoryId);
        return R.ok().data("row", courseList);
    }

    /**
     * 通知详情查询
     */
    @GetMapping("getNoticeById")
    public R getNoticeById(String id){
        TtNotice ttNotice = ttNoticeService.getById(id);
        return R.ok().data("row", ttNotice);
    }

    /**
     * 师资详情查询
     */
    @GetMapping("getTeacherById")
    public R getTeacherById(String id){
        TtTeacher ttTeacher = ttTeacherService.getById(id);
        return R.ok().data("row", ttTeacher);
    }


    /**
     * 课程详情查询
     */
    @GetMapping("getCourseById")
    public R getCourseById(String id, String userId){
        TtCourse ttCourse = ttCourseService.getCourseById(id);
        int count = ttCourseAppointmentService.count(new QueryWrapper<TtCourseAppointment>().eq("course_id", id).eq("status","1"));
        ttCourse.setResidueNumber(ttCourse.getNumber() - count);
        // 查询当前用是否已经预约过了
        ttCourse.setFlag(ttCourseAppointmentService.getByCourseByAndUserId(id, userId));
        return R.ok().data("row", ttCourse);
    }

    /**
     * 预约课程
     */
    @PostMapping("appointmentCourse")
    public R appointmentCourse(@RequestBody TtCourseAppointment ttCourseAppointment){

        // 判断当前用户是否已经预约过了
        boolean flag = ttCourseAppointmentService.getByCourseByAndUserId(ttCourseAppointment.getCourseId(), ttCourseAppointment.getUserId());
        if(flag){
            return R.error("您已经预约过了");
        }
        // 查询是否还有座位
        int count = ttCourseAppointmentService.count(new QueryWrapper<TtCourseAppointment>().eq("course_id", ttCourseAppointment.getCourseId()).eq("status","1"));
        TtCourse ttCourse = ttCourseService.getById(ttCourseAppointment.getCourseId());
        if(count >= ttCourse.getNumber()){
            return R.error("预约座位已满员");
        }
        ttCourseAppointment.setStatus("1");
        ttCourseAppointmentService.save(ttCourseAppointment);

        return R.ok();
    }

    /**
     * 取消预约
     */
    @PostMapping("cancelAppointmentCourse")
    public R cancelAppointmentCourse(@RequestBody TtCourseAppointment ttCourseAppointment){

        ttCourseAppointmentService.cancelAppointmentCourse(ttCourseAppointment);
        return R.ok();
    }


    /**
     * 密码修改
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
     * 个人信息查询
     */
    @GetMapping("getUserInfo")
    public R getUserInfo(String id){
        TtUser ttUser = ttUserService.getUserInfoById(id);
        return R.ok().data("row", ttUser);
    }

    /**
     * 个人信息修改
     */
    @PostMapping("updateUserInfo")
    public R updateUserInfo(@RequestBody TtUser ttUser){
        ttUserService.updateUserInfo(ttUser);
        return R.ok().data("row", ttUser);
    }

    /**
     * 查询我的预约记录
     * @param id 用户id
     * @return
     */
    @GetMapping("getCourseAppointmentInfo")
    public R getCourseAppointmentInfo(String id,String status){
        List<TtCourseAppointment> list =  ttCourseAppointmentService.getCourseAppointmentInfo(id, status);
        return R.ok().data("row", list);
    }


    /**
     * 查询我的成绩
     * @param id 用户id
     * @return
     */
    @GetMapping("getScoreInfo")
    public R getScoreInfoByUserId(String id){
        List<TtScore> list =  ttScoreService.getScoreInfoByUserId(id);
        return R.ok().data("row", list);
    }
}
