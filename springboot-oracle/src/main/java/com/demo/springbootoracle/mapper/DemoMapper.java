package com.demo.springbootoracle.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.springbootoracle.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<Student> {
}
