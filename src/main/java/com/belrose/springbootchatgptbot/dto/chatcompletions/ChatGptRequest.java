package com.belrose.springbootchatgptbot.dto.chatcompletions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptRequest implements Serializable {
    private String model;
    private List<Message> messages;

   /* public ChatGptRequest(String model, String content){
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",content));
    }*/
}
