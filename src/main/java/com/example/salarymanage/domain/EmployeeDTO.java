package com.example.salarymanage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.domain
 * @Author: ASUS
 * @CreateTime: 2024-03-06  16:06
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Employee employee;
    private Profession profession;
}
