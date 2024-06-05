package com.demo.transactional.service.impl;

import com.demo.transactional.entity.Student;
import com.demo.transactional.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentServiceImplTest {
    @Autowired
    private StudentService service;
    @Test
    // 测试类方法的内部调用是否会失效
    public void test1() {
        service.test1();
    }

    @Test
    // 测试类方法的内部调用事务生效
    public void addStudent() {
        service.add();
    }

    @Test
    // 测试多线程
    public void selectById() throws InterruptedException {
        Student student = service.selectById();
        log.info("获取到的数据为:{}",student);
    }

    @Test
    public void createStudent(){
        service.createStudent();
    }


    @Test
    public void list(){
        List<Student> list = service.list();
        log.info("数据库中的数据为:{}",list);
    }

}
