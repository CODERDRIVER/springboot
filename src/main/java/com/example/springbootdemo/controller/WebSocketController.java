package com.example.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void handleChat(Principal principal,String msg)
    {
        /**
         * principal中保存中用户信息
         */

        if(principal.getName().equals("lxy")){
            simpMessagingTemplate
                    .convertAndSendToUser("sq","/queue/notifications",principal.getName()+"-send"+msg);
        }else{
            simpMessagingTemplate
                    .convertAndSendToUser("lxy","/queue/notifications",principal.getName()+"-send"+msg);
        }
    }

}

