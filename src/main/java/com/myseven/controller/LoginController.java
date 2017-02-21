package com.myseven.controller;

import com.myseven.db.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/21.
 */
@Controller
@RequestMapping(value="login")
public class LoginController {

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String loginUser(HttpServletRequest request, HttpServletResponse response, User user ){

        String username = user.getName();
        System.out.println("-----------打印测试数据--name="+username+"LoginController-----login");

        HttpSession session = request.getSession(false);

        session.setAttribute("username",username);

        return "index";

    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(){

        return "login";

    }

}
