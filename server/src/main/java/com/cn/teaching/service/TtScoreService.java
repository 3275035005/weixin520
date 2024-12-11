package com.cn.teaching.service;

import com.cn.teaching.entity.TtScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 成绩信息表 服务类
 * </p>
 */
public interface TtScoreService extends IService<TtScore> {

    PageResult pageQuery(int page, int limit, TtScore data);

    /**
     * 根据用户id查询成绩
     * @param id
     * @return
     */
    List<TtScore> getScoreInfoByUserId(String id);

    /**
     * 统计已预约课程的未评分的学生
     * @return
     */
    List<TtScore> getNotScoreInfo();


}
