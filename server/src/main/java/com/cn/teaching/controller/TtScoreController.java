package com.cn.teaching.controller;


import com.cn.teaching.entity.TtCourseAppointment;
import com.cn.teaching.entity.TtScore;
import com.cn.teaching.service.TtCourseAppointmentService;
import com.cn.teaching.service.TtScoreService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 成绩信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-score")
public class TtScoreController {


    @Autowired
    private TtScoreService ttScoreService;

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
            @RequestBody TtScore data){
        PageResult pageResult = ttScoreService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtScore data){
        TtCourseAppointment t = ttCourseAppointmentService.getById(data.getAppointmentId());
        data.setCourseId(t.getCourseId());
        data.setUserId(t.getUserId());
        ttScoreService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  TtScore data){
        TtCourseAppointment t = ttCourseAppointmentService.getById(data.getAppointmentId());
        data.setCourseId(t.getCourseId());
        data.setUserId(t.getUserId());
        ttScoreService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttScoreService.removeById(id);
        return R.ok();
    }
    /**
     * 统计已预约课程的未评分的学生
     * @return
     */
    @GetMapping("getNotScoreInfo")
    public R getNotScoreInfo(){
        List<TtScore> list = ttScoreService.getNotScoreInfo();
        return R.ok().data("row", list);
    }

}

