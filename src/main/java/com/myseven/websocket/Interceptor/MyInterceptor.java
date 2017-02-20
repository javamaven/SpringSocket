package com.myseven.websocket.Interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class MyInterceptor extends HttpSessionHandshakeInterceptor {

    private Logger logger = Logger.getLogger(getClass());  
    
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
    	System.out.println("GOMA ===> Before Handshake");  
        return super.beforeHandshake(request, response, wsHandler, attributes);  
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
    	System.out.println("GOMA ===> After Handshake");  
        super.afterHandshake(request, response, wsHandler, ex);  
    } 
	
}
