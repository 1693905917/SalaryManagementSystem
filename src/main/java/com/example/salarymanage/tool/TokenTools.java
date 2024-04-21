package com.example.salarymanage.tool;

import com.example.salarymanage.domain.User;
import com.example.salarymanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.tool
 * @Author: ASUS
 * @CreateTime: 2024-04-21  22:53
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class TokenTools {
    /**

     * 生成token放入session

     * @param request

     * @param tokenServerkey

     */
    @Autowired
    TokenProcessor tokenProcessor;

    @Autowired
    private IUserService userService;

    public  void createToken(HttpServletRequest request, String tokenServerkey, User user){

        String token = tokenProcessor.makeToken();
        System.out.println("生成的token是：》》》》》》》"+token);
        user.setToken(token);
        userService.modify(user);
        System.out.println("用户数据库存储的Token："+ userService.getById(user.getId()).getToken());
        request.getSession().setAttribute(tokenServerkey, token);

    }



    /**

     * 移除token

     * @param request

     * @param tokenServerkey

     */

    public  void removeToken(HttpServletRequest request,String tokenServerkey){

        request.getSession().removeAttribute(tokenServerkey);

    }



    /**

     * 判断请求参数中的token是否和session中一致

     * @param request

     * @param tokenClientkey

     * @param tokenServerkey

     * @return

     */

    public boolean judgeTokenIsEqual(HttpServletRequest request,String tokenClientkey,String tokenServerkey){

        String token_client = request.getParameter(tokenClientkey);

        if(StringUtils.isEmpty(token_client)){

            return false;

        }

        String token_server = (String) request.getSession().getAttribute(tokenServerkey);

        if(StringUtils.isEmpty(token_server)){

            return false;

        }



        if(!token_server.equals(token_client)){

            return false;

        }



        return true;

    }

}
