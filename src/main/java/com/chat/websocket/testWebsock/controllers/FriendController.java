package com.chat.websocket.testWebsock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendController {
    @GetMapping("friends")
    public String getMainPageFriend(){
        return "index";
    }
    @GetMapping("/friend/message")
    public String messages(){
        return "dialog";
    }
}
