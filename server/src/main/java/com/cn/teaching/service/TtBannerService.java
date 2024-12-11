package com.cn.teaching.service;

import com.cn.teaching.entity.TtBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

/**
 * <p>
 * 轮播图表 服务类
 * </p>
 */
public interface TtBannerService extends IService<TtBanner> {

    PageResult pageQuery(int page, int limit, TtBanner data);
}
