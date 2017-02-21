package com.myseven.controller;

import com.myseven.websocket.handler.Myhandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

/**
 * Created by chenhaijun on 2017/2/21.
 */
@Controller
@RequestMapping(value="/message")
public class MessageController {


    @Autowired
    private Myhandler myhandler;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String tosend(){

        return "send";

    }

    @RequestMapping(value="/send",method = RequestMethod.POST)
    @ResponseBody //直接返回json格式
    public String sendToone(String username,String msg){

        TextMessage textMessage= new TextMessage(msg);

        myhandler.sendMsgToUser(username,textMessage);

        return "seccess";

    }

    @RequestMapping(value="/sendall",method = RequestMethod.POST)
    @ResponseBody
    public String sendToAll(String msg){
        TextMessage textMessage= new TextMessage(msg);
        myhandler.broadcastToUsers(textMessage);
        return "success";
    }

}
