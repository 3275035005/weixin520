package com.cn.teaching.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.teaching.entity.TtCourse;
import com.cn.teaching.service.TtCourseService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程信息 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-course")
public class TtCourseController {


    @Autowired
    private TtCourseService ttCourseService;

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
            @RequestBody TtCourse data){
        PageResult pageResult = ttCourseService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtCourse data){
        ttCourseService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  TtCourse data){
        ttCourseService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttCourseService.removeById(id);
        return R.ok();
    }

    /**
     * 查询所有课程信息
     * @return
     */
    @GetMapping("getCourseAll")
    public R getCourseAll(){
        LambdaQueryWrapper<TtCourse> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(TtCourse::getCreateTime);
        List<TtCourse> list = ttCourseService.list(qw);
        return R.ok().data("row", list);
    }
}

