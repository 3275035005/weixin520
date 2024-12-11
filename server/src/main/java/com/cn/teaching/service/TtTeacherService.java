package com.cn.teaching.service;

import com.cn.teaching.entity.TtTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

/**
 * <p>
 * 师资介绍 服务类
 * </p>
 *
 * @author 
 * @since 2023-11-07
 */
public interface TtTeacherService extends IService<TtTeacher> {

    PageResult pageQuery(int page, int limit, TtTeacher data);
}
