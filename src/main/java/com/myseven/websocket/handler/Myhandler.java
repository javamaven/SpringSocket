package com.myseven.websocket.handler;

import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class Myhandler extends TextWebSocketHandler {

    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
          
        super.handleTextMessage(session, message);  
        System.out.println("GOMA === > WebSocketEndPoint.handlerTextMessage...");  
        System.out.println(message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
        session.sendMessage(returnMessage);  
          
    }  
    
    
}
