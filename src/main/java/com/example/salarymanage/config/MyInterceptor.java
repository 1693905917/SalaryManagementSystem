package com.example.salarymanage.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.salarymanage.domain.User;
import com.example.salarymanage.service.IUserService;
import com.example.salarymanage.tool.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.config
 * @Author: ASUS
 * @CreateTime: 2024-04-21  22:32
 * @Description: TODO
 * @Version: 1.0
 */
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    TokenTools tools;

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("进入拦截器了");
        return true;
//        HttpSession session = request.getSession();
//        String uuid = session.getAttribute("UUID").toString();
//        QueryWrapper<User>  queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("token",uuid);
//        User one = userService.getOne(queryWrapper);
//        System.out.println("获取数据中User中的Token:"+one);
//        boolean b = tools.judgeTokenIsEqual(request, uuid, one.getToken());
//        if(b){
//            return true;
//        }
//        try {
//            response.sendRedirect(request.getContextPath() + "pages/index.html");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        System.out.println("controller 执行完了");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("我获取到了一个返回的结果："+response);
        System.out.println("请求结束了");
    }

}
