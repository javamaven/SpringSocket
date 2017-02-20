package com.myseven.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.myseven.websocket.Interceptor.MyInterceptor;
import com.myseven.websocket.handler.Myhandler;
//这是基于注解的webxocket配置  这个类跟controller一样 必须被扫描到
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		//设置 可访问的 ip String[] allowsOrigins = {"http://www.xxx.com"};
		// TODO Auto-generated method stub
		//添加访问路径  允许所有人访问
        registry.addHandler(myhandler(), "/websocket").addInterceptors(myInterceptors()).setAllowedOrigins("http://localhost:8080");
        registry.addHandler(myhandler(), "/sockjs/websocket").addInterceptors(myInterceptors()).withSockJS();  
	}  

    @Bean  
    public Myhandler myhandler() {  
        return new Myhandler();  
    }  
  
    @Bean  
    public MyInterceptor myInterceptors() {  
        return new MyInterceptor();  
    }  
}
