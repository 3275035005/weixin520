package com.cn.teaching.controller;


import com.cn.teaching.entity.TtNotice;
import com.cn.teaching.service.TtNoticeService;
import com.cn.teaching.utils.page.PageResult;
import com.cn.teaching.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 通知信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/tt-notice")
public class TtNoticeController {


    @Autowired
    private TtNoticeService ttNoticeService;

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
            @RequestBody TtNotice data){
        PageResult pageResult = ttNoticeService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody TtNotice data){
        ttNoticeService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  TtNotice data){
        ttNoticeService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ttNoticeService.removeById(id);
        return R.ok();
    }
}

