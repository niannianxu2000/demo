package com.demo.transactional.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.transactional.entity.Student;
import org.springframework.transaction.annotation.Transactional;

public interface StudentService extends IService<Student> {
    void test1();
    void test2();
    void add();
    void addStudent();
    Student selectById() throws InterruptedException;
    void createStudent();

    void test3();
}
