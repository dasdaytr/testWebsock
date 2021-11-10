package com.chat.websocket.testWebsock.Model;

public enum Permission {
    DEVELOPERS_READ("developers:read");
    private final String permission;
    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
