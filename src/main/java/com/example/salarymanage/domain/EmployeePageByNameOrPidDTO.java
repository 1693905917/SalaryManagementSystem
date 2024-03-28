package com.example.salarymanage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.domain
 * @Author: ASUS
 * @CreateTime: 2024-03-20  16:05
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePageByNameOrPidDTO implements Serializable {
    private int page;

    private int pageSize;

    //员工姓名
    private String name;

    //职位ID
    private Integer pid;

}
