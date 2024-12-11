package com.cn.teaching.service;

import com.cn.teaching.entity.TtNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

/**
 * <p>
 * 通知信息表 服务类
 * </p>
 */
public interface TtNoticeService extends IService<TtNotice> {

    PageResult pageQuery(int page, int limit, TtNotice data);

}
