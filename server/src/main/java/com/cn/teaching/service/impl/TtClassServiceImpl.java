package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtClass;
import com.cn.teaching.entity.TtClass;
import com.cn.teaching.mapper.TtClassMapper;
import com.cn.teaching.service.TtClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级信息表 服务实现类
 * </p>
 */
@Service
public class TtClassServiceImpl extends ServiceImpl<TtClassMapper, TtClass> implements TtClassService {

    @Override
    public PageResult pageQuery(int page, int limit, TtClass data) {
        List<TtClass> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtClass> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
