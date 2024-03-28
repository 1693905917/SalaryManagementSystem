package com.example.salarymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.salarymanage.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {
}
