package com.example.salarymanage.controller;

import com.example.salarymanage.domain.User;
import com.example.salarymanage.service.IUserService;
import com.example.salarymanage.tool.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    TokenTools tools;

    /*
     * @description:用户登录
     * @author:  HZP
     * @date: 2024/4/19 23:36
     * @param:
     * @return:
     **/
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
        List<User> list = userService.list();
//        tools.removeToken(request,"UUID");
        System.out.println(username+password);
        for (User user : list) {
            if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                //生成tokens
//                tools.createToken(request,"UUID",user);
                return "success";
            }
        }
        return "error";

    }
}
