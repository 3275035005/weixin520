package com.cn.teaching.mapper;

import com.cn.teaching.entity.TtCourse;
import com.cn.teaching.entity.TtCourseAppointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程预约信息 Mapper 接口
 * </p>
 */
public interface TtCourseAppointmentMapper extends BaseMapper<TtCourseAppointment> {

    List<TtCourseAppointment> pageQuery(TtCourseAppointment data);

    List<TtCourseAppointment> getCourseAppointmentInfo(@Param("userId") String userId,@Param("status") String status);

    void cancelAppointmentCourse(TtCourseAppointment ttCourseAppointment);
}
