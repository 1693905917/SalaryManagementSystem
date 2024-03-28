package com.example.salarymanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.salarymanage.dao.EmployeeDao;
import com.example.salarymanage.dao.ProfessionDao;
import com.example.salarymanage.domain.Employee;
import com.example.salarymanage.domain.Profession;
import com.example.salarymanage.service.IEmployeeService;
import com.example.salarymanage.service.IProfessionService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.service.impl
 * @Author: ASUS
 * @CreateTime: 2024-03-13  14:29
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class IProfessionServiceImpl extends ServiceImpl<ProfessionDao, Profession> implements IProfessionService {
}
