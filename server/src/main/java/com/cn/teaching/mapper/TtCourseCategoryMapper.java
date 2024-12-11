package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtCourseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程分类信息表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-11-03
 */
public interface TtCourseCategoryMapper extends BaseMapper<TtCourseCategory> {

    List<TtCourseCategory> pageQuery(TtCourseCategory data);

}
