package com.demo.springbootoracle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springbootoracle.entity.Student;
import com.demo.springbootoracle.mapper.DemoMapper;
import com.demo.springbootoracle.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public IPage<Student> list(Student student, IPage<Student> iPage) {
        // 可以加分页条件
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        // 可以加入查询条件,eq为相等、like为模糊查询、gt为大于，ge为大于等于、lt为小于、le为小于等于
        // 在LambdaQueryWrapper中的eq等api可以传入三个参数  第一个参数是布尔值，如果为true则生效
        queryWrapper.eq(student.getTId() != null ? true : false,Student::getTId,student.getTId());
        queryWrapper.like(StringUtils.isEmpty(student.getTName()),Student::getTName,student.getTName());
        IPage<Student> page = demoMapper.selectPage(iPage, queryWrapper);
        return page;
    }

    @Override
    public String save(Student student) {
        log.info("保存数据：{}",student);
        int insert = demoMapper.insert(student);
        if (insert > 0){
            return "保存成功";
        }else {
            return "保存失败";
        }
    }
}
