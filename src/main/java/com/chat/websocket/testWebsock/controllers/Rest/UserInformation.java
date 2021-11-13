package com.chat.websocket.testWebsock.controllers.Rest;

import com.chat.websocket.testWebsock.Model.User;
import com.chat.websocket.testWebsock.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserInformation {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("secured/id")
    public List<String> getId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional <User> user = userRepository.findByEmail(((UserDetails)principal).getUsername());
        List<String> list = new ArrayList<>();
        list.add(user.get().getId().toString());
        list.add(user.get().getFirstname());
        list.add(user.get().getLastname());
        System.out.println(list);
        return list;
    }
}
