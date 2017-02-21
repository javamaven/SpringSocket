package com.myseven.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.myseven.websocket.Interceptor.MyInterceptor;
import com.myseven.websocket.handler.Myhandler;
//实现websocket 除了导入对对应的jar外 还需要 3个类
//这是基于注解的webxocket配置  这个类跟controller一样 必须被扫描到
//实现WebSocketConfigurer接口，重写registerWebSocketHandlers方法，这是一个核心实现方法，配置websocket入口，允许访问的域、注册Handler、SockJs支持和拦截器。
//registry.addHandler注册和路由的功能，当客户端发起websocket连接，把/path交给对应的handler处理，而不实现具体的业务逻辑，可以理解为收集和任务分发中心。
//setAllowedOrigins(String[] domains),允许指定的域名或IP(含端口号)建立长连接，如果只允许自家域名访问，这里轻松设置。如果不限时使用"*"号，如果指定了域名，则必须要以http或https开头。
// addInterceptors，顾名思义就是为handler添加拦截器，可以在调用handler前后加入我们自己的逻辑代码。

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		//设置 可访问的 ip
        //String[] allowsOrigins = {"http://192.168.10.45"};
		// TODO Auto-generated method stub
		//添加访问路径  允许所有人访问
        registry.addHandler(myhandler(), "/websocket").addInterceptors(myInterceptors());
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
