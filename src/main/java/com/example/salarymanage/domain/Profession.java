package com.example.salarymanage.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.*;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.dao
 * @Author: ASUS
 * @CreateTime: 2024-03-06  15:05
 * @Description: TODO  职位表
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profession  {
    @TableId("pid")
    private Integer pid;
    private String typename;
    private Integer salary;
}
