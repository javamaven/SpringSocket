package com.myseven.websocket.Interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//该拦截器实现了HandshakeInterceptor接口，HandshakeInterceptor
// 可拦截Websocket的握手请求(通过HTTP协议)并可设置
// 与Websocket session建立连接的HTTP握手连接的属性值。
public class MyInterceptor extends HttpSessionHandshakeInterceptor {

    private Logger logger = Logger.getLogger(getClass());  
    
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
    	System.out.println("GOMA ===> Before Handshake");

        if (request instanceof ServletServerHttpRequest) {

            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                System.out.println("-----------打印测试数据--session"+session+"MyInterceptor-----beforeHandshake");
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute("username");
                if (userName==null) {
                    userName="default-system";
                }
                attributes.put("username",userName);

            }

        }
        return super.beforeHandshake(request, response, wsHandler, attributes);

    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
    	System.out.println("GOMA ===> After Handshake");
    	//握手成功
        super.afterHandshake(request, response, wsHandler, ex);  
    } 
	
}
