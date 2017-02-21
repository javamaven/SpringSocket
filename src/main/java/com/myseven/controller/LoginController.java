package com.myseven.controller;

import com.myseven.db.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/21.
 */
@Controller
@RequestMapping(value="login")
public class LoginController {

    //还可以通过modelandview

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public ModelAndView  getuser(String id){
        ModelAndView mav = new ModelAndView("userdetail");

        User user = new User();
        user.setName("12345");
        user.setPassword("789");
        mav.addObject("user",user);
        return mav;
    }


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
