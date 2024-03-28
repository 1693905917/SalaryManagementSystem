package com.example.salarymanage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageQueryDTO implements Serializable {
    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}
