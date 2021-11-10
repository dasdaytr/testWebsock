package com.chat.websocket.testWebsock.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Person greeting(Person person) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println(person.toString());
        return  person;
    }
    @MessageMapping("/hello/{from}")
    public void sendMessage(@PathVariable("from") String from, Message message){
        System.out.println(message.toString() + from);
            simpMessagingTemplate.convertAndSend("/topic/greeting/" + message.getTo(),message);
    }
}
