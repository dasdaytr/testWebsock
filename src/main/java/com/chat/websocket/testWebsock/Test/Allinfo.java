package com.chat.websocket.testWebsock.Test;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Allinfo {
    private List<Person> personList;
    private List<Friends> friendsList;
    private  List<CharRoom> charRoomList;

    public Allinfo(List<Person> personList, List<Friends> friendsList, List<CharRoom> charRoomList) {
        this.personList = personList;
        this.friendsList = friendsList;
        this.charRoomList = charRoomList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Friends> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<Friends> friendsList) {
        this.friendsList = friendsList;
    }

    public List<CharRoom> getCharRoomList() {
        return charRoomList;
    }

    public void setCharRoomList(List<CharRoom> charRoomList) {
        this.charRoomList = charRoomList;
    }

    @Override
    public String toString() {
        return "Allinfo{" +
                "personList=" + personList +
                '}';
    }
}
