package com.example.salarymanage.config;

import lombok.Data;

/**
 * @BelongsProject: springBoot
 * @BelongsPackage: com.itheima.controller.utils
 * @Author: ASUS
 * @CreateTime: 2023-07-10  15:10
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class R {
    private Boolean flag;
    private Object data;
    private String msg;//要显示信息


    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

}
