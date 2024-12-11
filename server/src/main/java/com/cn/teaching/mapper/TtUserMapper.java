package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表信息 Mapper 接口
 * </p>
 */
public interface TtUserMapper extends BaseMapper<TtUser> {

    List<TtUser> pageQuery(TtUser data);

    TtUser getUserInfoById(@Param("id") String id);

    void updateUserInfo(TtUser ttUser);
}
