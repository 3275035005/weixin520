package com.cn.teaching.service;

import com.cn.teaching.entity.TtClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

/**
 * <p>
 * 班级信息表 服务类
 * </p>
 */
public interface TtClassService extends IService<TtClass> {

    PageResult pageQuery(int page, int limit, TtClass data);

}
