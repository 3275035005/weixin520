package com.cn.teaching.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.teaching.entity.TtBanner;
import com.cn.teaching.service.TtBannerService;
import com.cn.teaching.service.TtBannerService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 轮播图表 前端控制器
 * </P>
 */
@RestController
@RequestMapping("/tt-banner")
public class TtBannerController {


    @Autowired
    private TtBannerService ttBannerService;

    /**
     * 分页条件查询
     *
     * @param page  当前页码
     * @param limit 每页的大小
     * @param data  封装查询条件数据
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public R getPageData(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestBody TtBanner data) {
        PageResult pageResult = ttBannerService.pageQuery(page, limit, data);
        return R.ok().data("rows", pageResult);
    }

    /**
     * 修改操作
     *
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtBanner data) {
        ttBannerService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     *
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody TtBanner data) {
        ttBannerService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id) {
        ttBannerService.removeById(id);
        return R.ok();
    }

}

