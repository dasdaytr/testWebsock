package com.chat.websocket.testWebsock.Service;

import com.chat.websocket.testWebsock.Model.*;
import com.chat.websocket.testWebsock.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MainService {
    @Autowired
    private UserRepository userRepository;
    public String infoUser(ValidUser infoRegistrationUser, Model model, String rpassword ){
        System.out.println(userRepository.findAll());
        if (userRepository.findByEmail(infoRegistrationUser.getEmail()).isEmpty()){
            if (infoRegistrationUser.getPassword().equals(rpassword)){
                savePerson(infoRegistrationUser);
                return "redirect:/mainPage";
            }
            else{
                model.addAttribute("error","Пароли не совпадают");
                return "First_page/reg";
            }
        }
        else{
            model.addAttribute("errorEmail","Такой email уже существует");
            return "First_page/reg";
        }

    }
    private void savePerson(ValidUser person){
        User user = new User();
        user.setEmail(person.getEmail());
        user.setFirstname(person.getFirstname());
        user.setLastname(person.getLastname());
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
        System.out.println(userRepository.save(user));
    }
}
