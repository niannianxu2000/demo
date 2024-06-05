package com.demo.springbootoracle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wzc.t_stu")
public class Student {
    @TableField("t_id")
    @TableId(type = IdType.AUTO)
    private Long TId;// 主键
    @TableField("t_name")
    private String TName;
    @TableField("age")
    private Integer age;
    @TableField("t_address")
    private String TAddress;
}
