package com.cn.teaching.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.teaching.entity.TtCourseCategory;
import com.cn.teaching.service.TtCourseCategoryService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程分类信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-course-category")
public class TtCourseCategoryController {


    @Autowired
    private TtCourseCategoryService ttCourseCategoryService;

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
            @RequestBody TtCourseCategory data){
        PageResult pageResult = ttCourseCategoryService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtCourseCategory data){
        ttCourseCategoryService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  TtCourseCategory data){
        ttCourseCategoryService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttCourseCategoryService.removeById(id);
        return R.ok();
    }

    /**
     * 查询所有课程分类信息
     * @return
     */
    @GetMapping("getCategoryAll")
    public R getCategoryAll(){
        LambdaQueryWrapper<TtCourseCategory> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(TtCourseCategory::getCreateTime);
        List<TtCourseCategory> list = ttCourseCategoryService.list(qw);
        return R.ok().data("row", list);
    }
    
}

