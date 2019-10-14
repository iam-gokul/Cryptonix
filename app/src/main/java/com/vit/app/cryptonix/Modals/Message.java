package com.vit.app.cryptonix.Modals;

public class Message {
    String name;



    String Message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message(String name, String message) {
        this.name = name;
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
