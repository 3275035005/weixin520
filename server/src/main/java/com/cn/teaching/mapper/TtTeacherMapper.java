package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 师资介绍 Mapper 接口
 * </p>
 */
public interface TtTeacherMapper extends BaseMapper<TtTeacher> {

    List<TtTeacher> pageQuery(TtTeacher data);

}
