package com.example.salarymanage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.dao
 * @Author: ASUS
 * @CreateTime: 2024-03-06  15:27
 * @Description: TODO  雇员表
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
    private String type;
    private String name;
}
