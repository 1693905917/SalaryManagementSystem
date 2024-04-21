package com.example.salarymanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.salarymanage.domain.Employee;
import com.example.salarymanage.domain.Profession;
import com.example.salarymanage.domain.User;

public interface IUserService extends IService<User> {
    Boolean modify(User user);

//    User selectUserByUUID();
}
