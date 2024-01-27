package com.belrose.springbootchatgptbot.controller;

import com.belrose.springbootchatgptbot.dto.songs.TopSong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class SongsController {

    private  final OpenAiChatClient openAiChatClient;

    public SongsController(OpenAiChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }

    @GetMapping("/songs")
    public ResponseEntity<String> topSong(){
        String prompt = "What was the billboard number one year-end to 100 single for 1980?";
        return ResponseEntity.ok(openAiChatClient.generate(prompt));
    }

    @GetMapping("/songs/{year}")
    public ResponseEntity<String> topSong1(@PathVariable("year") int year){
        String prompt = "What was the billboard number one year-end to 100 single for {year}?";
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        promptTemplate.add("year",year);
        return ResponseEntity.ok(openAiChatClient.generate(promptTemplate.render()));
    }

    @GetMapping("/songs/custo/{year}")
    public ResponseEntity<TopSong> topCustoSong2(@PathVariable("year") int year){

        BeanOutputParser<TopSong> parser = new BeanOutputParser<>(TopSong.class);

        String promptString = """ 
                What was the billboard number one year-end to 100 single for {year}?
                {format}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("year",year);
        promptTemplate.add("format",parser.getFormat());
        promptTemplate.setOutputParser(parser);

        log.info("FORMAT STRING: "+parser.getFormat());

        Prompt prompt = promptTemplate.create();
        ChatResponse aiResponse = openAiChatClient.generate(prompt);
        String text = aiResponse.getGeneration().getContent();

        return ResponseEntity.ok(parser.parse(text));
    }
}
