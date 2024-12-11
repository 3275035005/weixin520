package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtNotice;
import com.cn.teaching.mapper.TtNoticeMapper;
import com.cn.teaching.service.TtNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通知信息表 服务实现类
 * </p>
 */
@Service
public class TtNoticeServiceImpl extends ServiceImpl<TtNoticeMapper, TtNotice> implements TtNoticeService {

    @Override
    public PageResult pageQuery(int page, int limit, TtNotice data) {
        List<TtNotice> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtNotice> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
