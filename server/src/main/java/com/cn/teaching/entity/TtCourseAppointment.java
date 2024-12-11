package com.cn.teaching.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程预约信息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TtCourseAppointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 学生id
     */
    private String userId;

    /**
     * 预约状态（1预约成功 2已取消）
     */
    private String status;

    /**
     * 预约时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 课程名称
     */
    @TableField(exist = false)
    private String courseName;


    /**
     * 预约学生姓名
     */
    @TableField(exist = false)
    private String userName;

}
