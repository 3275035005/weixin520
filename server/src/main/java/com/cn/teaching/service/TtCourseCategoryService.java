package com.cn.teaching.service;

import com.cn.teaching.entity.TtCourseCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

/**
 * <p>
 * 课程分类信息表 服务类
 * </p>
 *
 * @author 
 * @since 2023-11-03
 */
public interface TtCourseCategoryService extends IService<TtCourseCategory> {

    PageResult pageQuery(int page, int limit, TtCourseCategory data);
}
