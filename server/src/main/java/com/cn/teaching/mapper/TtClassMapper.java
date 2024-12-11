package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 班级信息表 Mapper 接口
 * </p>
 */
public interface TtClassMapper extends BaseMapper<TtClass> {

    List<TtClass> pageQuery(TtClass data);

}
