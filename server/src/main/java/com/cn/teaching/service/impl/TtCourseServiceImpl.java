package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtCourse;
import com.cn.teaching.mapper.TtCourseMapper;
import com.cn.teaching.service.TtCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程信息 服务实现类
 * </p>
 */
@Service
public class TtCourseServiceImpl extends ServiceImpl<TtCourseMapper, TtCourse> implements TtCourseService {

    @Override
    public PageResult pageQuery(int page, int limit, TtCourse data) {
        List<TtCourse> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtCourse> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<TtCourse> getCourseList(String categoryId) {
        return baseMapper.pageQuery(new TtCourse().setCategoryId(categoryId));
    }

    @Override
    public TtCourse getCourseById(String id) {
        return baseMapper.getCourseById(id);
    }
}
