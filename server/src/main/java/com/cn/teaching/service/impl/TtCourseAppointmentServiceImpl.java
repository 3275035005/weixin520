package com.cn.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.teaching.entity.TtCourse;
import com.cn.teaching.entity.TtCourseAppointment;
import com.cn.teaching.mapper.TtCourseAppointmentMapper;
import com.cn.teaching.service.TtCourseAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程预约信息 服务实现类
 * </p>
 */
@Service
public class TtCourseAppointmentServiceImpl extends ServiceImpl<TtCourseAppointmentMapper, TtCourseAppointment> implements TtCourseAppointmentService {

    @Override
    public PageResult pageQuery(int page, int limit, TtCourseAppointment data) {
        List<TtCourseAppointment> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtCourseAppointment> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public boolean getByCourseByAndUserId(String courseId, String userId) {
        LambdaQueryWrapper<TtCourseAppointment> qw = new LambdaQueryWrapper<>();
        qw.eq(TtCourseAppointment::getCourseId, courseId);
        qw.eq(TtCourseAppointment::getUserId, userId);
        qw.eq(TtCourseAppointment::getStatus, "1");
        return baseMapper.selectOne(qw)!=null;
    }

    @Override
    public List<TtCourseAppointment> getCourseAppointmentInfo(String id, String status) {
        return baseMapper.getCourseAppointmentInfo(id, status);
    }

    @Override
    public void cancelAppointmentCourse(TtCourseAppointment ttCourseAppointment) {
        baseMapper.cancelAppointmentCourse(ttCourseAppointment);
    }
}
