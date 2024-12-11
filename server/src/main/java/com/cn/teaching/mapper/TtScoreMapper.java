package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtClass;
import com.cn.teaching.entity.TtScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 成绩信息表 Mapper 接口
 * </p>
 */
public interface TtScoreMapper extends BaseMapper<TtScore> {

    List<TtScore> pageQuery(TtScore data);

    List<TtScore> getScoreInfoByUserId(@Param("id") String id);

    List<TtScore> getNotScoreInfo();

}
