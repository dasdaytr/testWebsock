package com.chat.websocket.testWebsock.controllers;

import com.chat.websocket.testWebsock.Model.Message;
import com.chat.websocket.testWebsock.Model.WebSocketFriend;
import com.chat.websocket.testWebsock.Model.friends;
import com.chat.websocket.testWebsock.Repository.FriendRepository;
import com.chat.websocket.testWebsock.Repository.UserRepository;
import com.chat.websocket.testWebsock.Test.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private FriendRepository repository;
    @MessageMapping("/friend/new/{id}")
    private void addFriend(@PathVariable("id") String id, WebSocketFriend friend){
        System.out.println("id" + id);
        System.out.println("friend" +friend.toString());
        friends friends = repository.findByMyidAndFriendId(friend.getId(), friend.getFriendid());
        friend.setChatID(friends.getIdChatRoom());
        simpMessagingTemplate.convertAndSend("/topic/friend/"+friend.getId(),friend);
    }
    @MessageMapping("/chat/{id}")
    private void addFriend(Message message){
        System.out.println("asdasdasdasdasdasd");
        simpMessagingTemplate.convertAndSend("/topic/messages/"+message.getIdChatRoom(),message);
    }
}
