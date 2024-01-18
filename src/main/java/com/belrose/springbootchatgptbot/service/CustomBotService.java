package com.belrose.springbootchatgptbot.service;


public interface CustomBotService {
    String completion(String prompt);
    String chatCompletion(String prompt);
}
