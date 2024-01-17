package com.belrose.springbootchatgptbot.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGptRequest implements Serializable {
    private String model;
    private List<Message> messages;

    public ChatGptRequest(String model, String prompt){
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",prompt));
    }
}
