package com.belrose.springbootchatgptbot.controller;

import com.belrose.springbootchatgptbot.service.CustomBotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bot")
public class CustomBotController {

    private final CustomBotService customBotService;

    public CustomBotController(CustomBotService customBotService) {
        this.customBotService = customBotService;
    }

    @PostMapping("/completions")
    public ResponseEntity<String> completion(@RequestHeader("prompt") String prompt){
        String response = customBotService.completion(prompt);
        return  ResponseEntity.ok().body(response);
    }

    @PostMapping("/chat-completions")
    public ResponseEntity<String> chatCompletion(@RequestHeader("prompt") String prompt){
        String response = customBotService.chatCompletion(prompt);
        return  ResponseEntity.ok().body(response);
    }
}
