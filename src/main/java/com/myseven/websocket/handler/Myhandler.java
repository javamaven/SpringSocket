package com.myseven.websocket.handler;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



//实现WebSocketHandler接口并重写接口中的方法，为消息的处理实现定制化。
// Spring Websocket通过WebSocketSession建立会话，发送消息或关闭会话。
// Websocket可发送两类消息体，分别为文本消息TextMessage和二进制消息BinaryMessage，
// 两类消息都实现了WebSocketMessage接口
public class Myhandler extends TextWebSocketHandler {

    //定义一个hashMap装用户
    private static  final Map<String,WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

    Logger logger = Logger.getLogger(Myhandler.class);

    // 连接成功 触发页面.onopen
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("-----------打印测试数据--成功链接Myhandler-----afterConnectionEstablished");

        System.out.println("-----------打印测试数据--session"+session.getAttributes().get("username")+"Myhandler-----afterConnectionEstablished");

        //链接成功 加入在线列表
        String username = (String)session.getAttributes().get("username");
        users.put(username,session);

        //通知在线用户 我已上线
        TextMessage msg = new TextMessage(username+"已上线");
        broadcastToUsers(msg);

        super.afterConnectionEstablished(session);
    }

    //web sendmessage的时候
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
          
        super.handleTextMessage(session, message);  
        System.out.println("GOMA === > WebSocketEndPoint.handlerTextMessage..."+session.getAttributes().get("username"));
        System.out.println(message);


        //回发消息
        TextMessage returnMessage = new TextMessage(session.getAttributes().get("username")+message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
          
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("websocket connection closed......");
        String username = (String)session.getAttributes().get("username");
        System.out.println("--------------------username="+username+","+"当前类=Myhandler.handleTransportError()");
        users.remove(username);

        TextMessage outmsg = new TextMessage(username+"已下线");

        broadcastToUsers(outmsg);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        System.out.println("websocket connection closed......");

        String username = (String)session.getAttributes().get("username");

        System.out.println("--------------------username="+username+","+"当前类=Myhandler.handleTransportError()");

        users.remove(username);

        TextMessage outmsg = new TextMessage(username+"已下线");

        broadcastToUsers(outmsg);
    }

    //自定义方法 发消息给某个人
    public void sendMsgToUser(String username,TextMessage msg){

        //根据username获取websocket
        WebSocketSession session = users.get(username);

        if (session != null){

            if(session.isOpen()){

                try {
                    System.out.println("--------------------发送消息给="+username+","+"当前类=Myhandler.sendMsgToUser()");
                    session.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

                users.remove(username);

                TextMessage outmsg = new TextMessage(username+"已下线");

                broadcastToUsers(outmsg);

            }

        }
    }

    //自定义方法 发消息给全部人
    public void broadcastToUsers(TextMessage msg){
        
        //遍历发消息
        for(String username : users.keySet()){
            System.out.println("--------------------username="+username+","+"当前类=Myhandler.broadcastToUsers()");
            sendMsgToUser(username,msg);
        }
        
    }

}
