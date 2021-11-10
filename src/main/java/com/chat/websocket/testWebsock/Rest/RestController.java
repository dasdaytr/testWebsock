package com.chat.websocket.testWebsock.Rest;

import com.chat.websocket.testWebsock.Test.Allinfo;
import com.chat.websocket.testWebsock.Test.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private Allinfo allinfo;

    @PostMapping("person")
    public String addPerson(@Autowired Person person){
        allinfo.getPersonList().add(person);
        return "успешно";
    }
    @GetMapping("person")
    public List<Person> getPerson(){
        allinfo.getPersonList().add(new Person(5,"dan",5));
        allinfo.getPersonList().add(new Person(54,"d",54));
        return allinfo.getPersonList();
    }
}
