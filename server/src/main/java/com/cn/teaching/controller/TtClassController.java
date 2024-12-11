package com.cn.teaching.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.teaching.entity.TtClass;
import com.cn.teaching.service.TtClassService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 班级信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-class")
public class TtClassController {


    @Autowired
    private TtClassService ttClassService;

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
            @RequestBody TtClass data){
        PageResult pageResult = ttClassService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtClass data){
        ttClassService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  TtClass data){
        ttClassService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttClassService.removeById(id);
        return R.ok();
    }

    /**
     * 查询所有班级信息
     * @return
     */
    @GetMapping("getClassAll")
    public R getClassAll(){
        LambdaQueryWrapper<TtClass> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(TtClass::getCreateTime);
        List<TtClass> list = ttClassService.list(qw);
        return R.ok().data("row", list);
    }
}

