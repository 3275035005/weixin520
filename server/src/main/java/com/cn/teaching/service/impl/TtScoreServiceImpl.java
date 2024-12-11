package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtScore;
import com.cn.teaching.mapper.TtScoreMapper;
import com.cn.teaching.service.TtScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 成绩信息表 服务实现类
 * </p>
 */
@Service
public class TtScoreServiceImpl extends ServiceImpl<TtScoreMapper, TtScore> implements TtScoreService {

    @Override
    public PageResult pageQuery(int page, int limit, TtScore data) {
        List<TtScore> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtScore> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<TtScore> getScoreInfoByUserId(String id) {
        return baseMapper.getScoreInfoByUserId(id);
    }

    @Override
    public List<TtScore> getNotScoreInfo() {
        return baseMapper.getNotScoreInfo();
    }
}
