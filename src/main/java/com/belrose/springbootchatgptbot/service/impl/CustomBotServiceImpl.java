package com.belrose.springbootchatgptbot.service.impl;

import com.belrose.springbootchatgptbot.dto.chatcompletions.ChatGptRequest;
import com.belrose.springbootchatgptbot.dto.chatcompletions.ChatGptResponse;
import com.belrose.springbootchatgptbot.dto.chatcompletions.Message;
import com.belrose.springbootchatgptbot.dto.completions.GptRequest;
import com.belrose.springbootchatgptbot.dto.completions.GptResponse;
import com.belrose.springbootchatgptbot.service.CustomBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CustomBotServiceImpl implements CustomBotService {
    @Value("${openai.model.completions}")
    private String modelOfCompletionsApi;
    @Value("${openai.model.chat-completions}")
    private String modelOfChatCompletionsApi;

    @Value("${openai.api.url.completions}")
    private String urlOfCompletionsApi;
    @Value("${openai.api.url.chat-completions}")
    private String urlOfChatCompletionsApi;
    @Autowired
    private RestTemplate template;

    @Override
    public String completion(String prompt) {
        GptRequest request = new GptRequest(modelOfCompletionsApi,prompt);
        GptResponse gptResponse = template
                .postForObject(urlOfCompletionsApi,request, GptResponse.class);
        assert gptResponse != null;
        return gptResponse.getChoices().getFirst().getText();
    }

    @Override
    public String chatCompletion(String prompt) {
        Message message = new Message("user",prompt);
        ChatGptRequest request = new ChatGptRequest(modelOfChatCompletionsApi, List.of(message));
        ChatGptResponse chatGptResponse = template
                .postForObject(urlOfChatCompletionsApi,request, ChatGptResponse.class);
        assert chatGptResponse != null;
        return chatGptResponse.getChoices().getFirst().getMessage().getContent();
    }
}
