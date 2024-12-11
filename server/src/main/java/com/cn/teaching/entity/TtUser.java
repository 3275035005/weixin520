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
 * 用户表信息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TtUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号状态 1正常 2禁用
     */
    private String status;

    /**
     * 角色 1管理员 2学生
     */
    private String userType;

    /**
     * 注册时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 班级名称
     */
    @TableField(exist = false)
    private String className;

    /**
     * 接收原密码
     */
    @TableField(exist = false)
    private String oldPassword;


    @TableField(exist = false)
    private String[] roles;


}
