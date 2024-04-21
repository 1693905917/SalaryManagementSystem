package com.example.salarymanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.salarymanage.dao.ProfessionDao;
import com.example.salarymanage.dao.UserDao;
import com.example.salarymanage.domain.Profession;
import com.example.salarymanage.domain.User;
import com.example.salarymanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.service.impl
 * @Author: ASUS
 * @CreateTime: 2024-04-18  16:01
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    UserDao userDao;

    @Override
    public Boolean modify(User user) {
        int i = userDao.updateById(user);
        return i>0;
    }

}
