package com.cn.teaching.service;

import com.cn.teaching.entity.TtCourse;
import com.cn.teaching.entity.TtCourseAppointment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.teaching.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 课程预约信息 服务类
 * </p>
 */
public interface TtCourseAppointmentService extends IService<TtCourseAppointment> {

    PageResult pageQuery(int page, int limit, TtCourseAppointment data);

    /**
     * 根据课程id和用户id查询是否已经预约过了
     * @param courseId
     * @param userId
     * @return
     */
    boolean getByCourseByAndUserId(String courseId, String userId);

    /**
     * 根据用户id查询上课记录
     * @param id
     * @return
     */
    List<TtCourseAppointment> getCourseAppointmentInfo(String id, String status);

    void cancelAppointmentCourse(TtCourseAppointment ttCourseAppointment);

}
