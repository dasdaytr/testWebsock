package com.chat.websocket.testWebsock.controllers;

import com.chat.websocket.testWebsock.Model.User;
import com.chat.websocket.testWebsock.Model.ValidUser;
import com.chat.websocket.testWebsock.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    @GetMapping("/mainPage")
    public String mainPage() {
        return "MainPage/Main_Page";
    }

    @GetMapping("/reg")
    public String singIn(@ModelAttribute("infoRegistrationUser") ValidUser person) {
        return "First_page/reg";
    }

    @PostMapping("/reg")
    public String signInInfo(@ModelAttribute("infoRegistrationUser") @Valid ValidUser infoRegistrationUser , BindingResult bindingResult, Model model, @RequestParam("passwordReturn") String rpassword ) {
        if (bindingResult.hasErrors()){
            return "First_page/reg";
        }
        return mainService.infoUser(infoRegistrationUser,model,rpassword);
    }
    @GetMapping("/signIn")
    public String singUp(Model model,@ModelAttribute("infoRegistrationUser") ValidUser Person){
        return "First_page/Sign_in";
    }
    @PostMapping("/signIn")
    public String singUpPost(@ModelAttribute("infoRegistrationUser") ValidUser person2){
        return "redirect:/mainPage";
    }

}