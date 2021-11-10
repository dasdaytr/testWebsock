package com.chat.websocket.testWebsock.Test;

public class Person {
    private int id;
    private String FIO;
    private int id_friend;

    public Person() {
    }

    public Person(int id, String FIO, int id_friend) {
        this.id = id;
        this.FIO = FIO;
        this.id_friend = id_friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getId_friend() {
        return id_friend;
    }

    public void setId_friend(int id_friend) {
        this.id_friend = id_friend;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", id_friend=" + id_friend +
                '}';
    }
}
