package com.example.salarymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.salarymanage.domain.Profession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfessionDao extends BaseMapper<Profession> {

}
