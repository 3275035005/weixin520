package com.cn.teaching.controller;


import com.cn.teaching.entity.TtCourse;
import com.cn.teaching.entity.TtCourseAppointment;
import com.cn.teaching.entity.TtCourseCategory;
import com.cn.teaching.service.TtCourseAppointmentService;
import com.cn.teaching.service.TtCourseService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程预约记录查询 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-course-appointment")
public class TtCourseAppointmentController {


    @Autowired
    private TtCourseAppointmentService ttCourseAppointmentService;

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
            @RequestBody TtCourseAppointment data){
        PageResult pageResult = ttCourseAppointmentService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }


    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttCourseAppointmentService.removeById(id);
        return R.ok();
    }

}

