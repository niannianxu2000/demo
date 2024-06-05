package com.demo.springbootoracle.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springbootoracle.entity.Student;
import com.demo.springbootoracle.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * 使用mybatis-plus来进行分页
     * @param pageNo 起始页
     * @param pageSize 每页的数据大小
     * @return
     */
    @GetMapping("/list")
    public List<Student> list(Student student,
                              @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        // 创建分页Ipage对象并传入起始页和每页大小
        IPage<Student> ipage = new Page<>(pageNo,pageSize);
        IPage<Student> list = demoService.list(student,ipage);
        return list.getRecords();
    }

    /**
     * 添加方法
     * @param student
     * @return
     */
    @PostMapping("save")
    public String save(@RequestBody Student student) {
        String save = demoService.save(student);
        return save;
    }

}
