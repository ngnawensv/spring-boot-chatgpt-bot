package com.belrose.springbootchatgptbot.controller;

import com.belrose.springbootchatgptbot.dto.ChatGptRequest;
import com.belrose.springbootchatgptbot.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class CustomBotController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;
    @Autowired
    private RestTemplate template;

    @PostMapping("/bot")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGptRequest request = new ChatGptRequest(model,prompt);
        ChatGptResponse chatGptResponse = template
                .postForObject(apiUrl,request, ChatGptResponse.class);
        assert chatGptResponse != null;
        return chatGptResponse.getChoices().getFirst().getMessage().getContent();
    }
}
