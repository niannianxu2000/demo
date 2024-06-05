package com.demo.transactional.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.transactional.entity.Student;
import com.demo.transactional.mapper.StudentMapper;
import com.demo.transactional.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService service;

    @Override
    public void test1() {
        // 使用AopContext来获取代理对象
        //StudentService studentService = (StudentService) AopContext.currentProxy();
        service.test2();
        // 写一个异常
        // throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void test2() {
        Student stu2 = new Student();
        stu2.setName("测试姓名");
        stu2.setAge(20);
        stu2.setAddress("河北省");
        studentMapper.insert(stu2);
    }

    /**
     * 类内部调用的反例
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add() {
        this.addStudent();
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStudent() {
        Student stu2 = new Student();
        stu2.setName("测试姓名");
        stu2.setAge(20);
        stu2.setAddress("河北省");
        studentMapper.insert(stu2);
    }

    // 测试多线程
    @Override
    public Student selectById() throws InterruptedException {
        // 创建多线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Student s = new Student();
                s.setName("张三");
                s.setAge(18);
                s.setAddress("北京市");
                studentMapper.insert(s);
            }
        });
        thread.start();
        // 查询名称为张三的数据
        Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>().eq(Student::getName, "张三"));
        return student;
    }

    /**
     * 测试内部catch之后造成事务失效
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createStudent() {
        service.test3();
        this.test4();
    }

    public void test4() {
        Student s = new Student();
        s.setName("王五");
        s.setAddress("上海市");
        s.setAge(18);
        studentMapper.insert(s);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void test3() {
        Student s = new Student();
        s.setName("李四");
        s.setAddress("青岛市");
        s.setAge(30);
        studentMapper.insert(s);
        throw new RuntimeException();
    }
}
