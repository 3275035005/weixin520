package com.cn.teaching.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.teaching.entity.TtTeacher;
import com.cn.teaching.service.TtTeacherService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 师资介绍 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-teacher")
public class TtTeacherController {

    @Autowired
    private TtTeacherService tTeacherService;

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
            @RequestBody TtTeacher data){
        PageResult pageResult = tTeacherService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtTeacher data){
        tTeacherService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  TtTeacher data){
        tTeacherService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        tTeacherService.removeById(id);
        return R.ok();
    }

    /**
     * 查询所有教师信息
     * @return
     */
    @GetMapping("getTeacherAll")
    public R getTeacherAll(){
        LambdaQueryWrapper<TtTeacher> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(TtTeacher::getCreateTime);
        List<TtTeacher> list = tTeacherService.list(qw);
        return R.ok().data("row", list);
    }
}

