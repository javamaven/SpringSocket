package com.myseven.websocket.handler;

import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;



//实现WebSocketHandler接口并重写接口中的方法，为消息的处理实现定制化。
// Spring Websocket通过WebSocketSession建立会话，发送消息或关闭会话。
// Websocket可发送两类消息体，分别为文本消息TextMessage和二进制消息BinaryMessage，
// 两类消息都实现了WebSocketMessage接口
public class Myhandler extends TextWebSocketHandler {

    //定义一个hashMap装用户
    private static  final  ConcurrentHashMap<String,WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

    Logger logger = Logger.getLogger(Myhandler.class);

    //链接成功后调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("-----------打印测试数据--成功链接Myhandler-----afterConnectionEstablished");

        System.out.println("-----------打印测试数据--session"+session.getAttributes().get("username")+"Myhandler-----afterConnectionEstablished");

        //链接成功 加入在线列表
        users.put((String)session.getAttributes().get("username"),session);

        super.afterConnectionEstablished(session);
    }

    //web sendmessage的时候调用
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
          
        super.handleTextMessage(session, message);  
        System.out.println("GOMA === > WebSocketEndPoint.handlerTextMessage..."+session.getAttributes().get("username"));
        System.out.println(message);
        TextMessage returnMessage = new TextMessage(session.getAttributes().get("username")+message.getPayload()+" received at server");
        session.sendMessage(returnMessage);  
          
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}
