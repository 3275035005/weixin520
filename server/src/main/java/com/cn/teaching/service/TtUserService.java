package com.cn.teaching.service;

import com.cn.teaching.entity.TtUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

/**
 * <p>
 * 用户表信息 服务类
 * </p>
 */
public interface TtUserService extends IService<TtUser> {

    PageResult pageQuery(int page, int limit, TtUser data);

    TtUser getUserInfoById(String id);

    void updateUserInfo(TtUser ttUser);
}
