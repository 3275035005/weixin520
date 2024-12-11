package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtTeacher;
import com.cn.teaching.mapper.TtTeacherMapper;
import com.cn.teaching.service.TtTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 师资介绍 服务实现类
 * </p>
 */
@Service
public class TtTeacherServiceImpl extends ServiceImpl<TtTeacherMapper, TtTeacher> implements TtTeacherService {

    @Override
    public PageResult pageQuery(int page, int limit, TtTeacher data) {
        List<TtTeacher> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtTeacher> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
