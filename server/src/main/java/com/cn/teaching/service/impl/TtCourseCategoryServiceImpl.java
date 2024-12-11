package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtCourseCategory;
import com.cn.teaching.entity.TtCourseCategory;
import com.cn.teaching.mapper.TtCourseCategoryMapper;
import com.cn.teaching.service.TtCourseCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程分类信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2023-11-03
 */
@Service
public class TtCourseCategoryServiceImpl extends ServiceImpl<TtCourseCategoryMapper, TtCourseCategory> implements TtCourseCategoryService {

    @Override
    public PageResult pageQuery(int page, int limit, TtCourseCategory data) {
        List<TtCourseCategory> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtCourseCategory> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
