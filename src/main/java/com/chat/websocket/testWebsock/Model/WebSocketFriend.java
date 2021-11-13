package com.chat.websocket.testWebsock.Model;


public class WebSocketFriend {
    private int id;
    private String friend_first_name;
    private String friend_last_name;
    private int friendid;
    private Integer chatID;


    public String getFriend_last_name() {
        return friend_last_name;
    }

    public void setFriend_last_name(String friend_last_name) {
        this.friend_last_name = friend_last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFriend_first_name() {
        return friend_first_name;
    }

    public void setFriend_first_name(String friend_first_name) {
        this.friend_first_name = friend_first_name;
    }



    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public Integer getChatID() {
        return chatID;
    }

    public void setChatID(Integer chatID) {
        this.chatID = chatID;
    }
}
