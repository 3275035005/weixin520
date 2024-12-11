package com.cn.teaching.service;

import com.cn.teaching.entity.TtCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 课程信息 服务类
 * </p>
 */
public interface TtCourseService extends IService<TtCourse> {

    PageResult pageQuery(int page, int limit, TtCourse data);

    List<TtCourse> getCourseList(String categoryId);

    TtCourse getCourseById(String id);
}
