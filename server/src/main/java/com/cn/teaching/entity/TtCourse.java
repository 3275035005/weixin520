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
 * 课程信息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TtCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 课程名称
     */
    private String title;

    /**
     * 课程分类id
     */
    private String categoryId;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 讲师id
     */
    private String teacherId;

    /**
     * 课程封面
     */
    private String image;

    /**
     * 课程状态（1正常 2已结束）
     */
    private String status;

    /**
     *  是否为热门课程（0, 否 1是）
     */
    private String hot;

    /**
     * 座位数
     */
    private Integer number;

    /**
     * 上课开始时间
     */
    private Date startTime;

    /**
     * 上课截止时间
     */
    private Date endTime;

    /**
     * 创建课程时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 课程类别名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 讲师姓名
     */
    @TableField(exist = false)
    private String teacherName;

    /**
     * 是否预约课程
     */
    @TableField(exist = false)
    private boolean flag;

    /**
     * 剩余座位数
     */
    @TableField(exist = false)
    private Integer residueNumber;
}
