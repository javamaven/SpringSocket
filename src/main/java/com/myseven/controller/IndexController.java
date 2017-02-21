package com.myseven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="index")
public class IndexController {
	
	@RequestMapping(value="get")
	@ResponseBody
	public String  get(HttpServletRequest request){
		HttpSession session = request.getSession();

		if (session != null){
			String name = (String)session.getAttribute("username");

			System.out.println("-----------打印测试数据--name"+name+"IndexController-----get");
		}

		return "index";
	}

}
