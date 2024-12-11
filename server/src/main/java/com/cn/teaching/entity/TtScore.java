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
 * 成绩信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TtScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 学生id
     */
    private String userId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 成绩
     */
    private Integer score;

    /**
     * 评定时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 课程名称
     */
    @TableField(exist = false)
    private String courseName;

    /**
     * 学生姓名
     */
    @TableField(exist = false)
    private String userName;


    /**
     * 接收前端课程预约id
     */
    @TableField(exist = false)
    private String appointmentId;

}
