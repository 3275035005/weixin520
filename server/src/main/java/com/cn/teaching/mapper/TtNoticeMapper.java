package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 通知信息表 Mapper 接口
 * </p>
 */
public interface TtNoticeMapper extends BaseMapper<TtNotice> {

    List<TtNotice> pageQuery(TtNotice data);

}
