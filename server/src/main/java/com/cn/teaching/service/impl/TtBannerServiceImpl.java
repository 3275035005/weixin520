package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtBanner;
import com.cn.teaching.mapper.TtBannerMapper;
import com.cn.teaching.service.TtBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 轮播图表 服务实现类
 * </p>
 */
@Service
public class TtBannerServiceImpl extends ServiceImpl<TtBannerMapper, TtBanner> implements TtBannerService {

    @Override
    public PageResult pageQuery(int page, int limit, TtBanner data) {
        List<TtBanner> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtBanner> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
