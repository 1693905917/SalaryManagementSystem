package com.example.salarymanage.controller;

import com.example.salarymanage.domain.User;
import com.example.salarymanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.controller
 * @Author: ASUS
 * @CreateTime: 2024-04-18  16:06
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
//        System.out.println(username+"============"+password);
        List<User> list = userService.list();
//        System.out.println(list);
        for (User user : list) {
            if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                return "{data:'success'}";
            }
        }
        return "{data:'error'}";

    }




}
