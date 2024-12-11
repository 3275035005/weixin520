package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程信息 Mapper 接口
 * </p>
 */
public interface TtCourseMapper extends BaseMapper<TtCourse> {

    List<TtCourse> pageQuery(TtCourse data);

    TtCourse getCourseById(@Param("id") String id);
}
