package com.demo.springbootoracle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springbootoracle.entity.Student;

public interface DemoService {
    IPage<Student> list(Student student, IPage<Student> iPage);

    String save(Student student);
}
