package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 轮播图表 Mapper 接口
 * </p>
 */
public interface TtBannerMapper extends BaseMapper<TtBanner> {

    List<TtBanner> pageQuery(TtBanner data);
}
