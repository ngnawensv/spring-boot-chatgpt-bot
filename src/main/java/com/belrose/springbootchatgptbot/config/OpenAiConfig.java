package com.belrose.springbootchatgptbot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class OpenAiConfig {

    @Value("${openai.api.key}")
    private String openApiKey;

    @Bean
    public RestTemplate template(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) ->{
                request.getHeaders().add("Authorization", "Bearer "+openApiKey);
        return execution.execute(request,body);
    });
      return restTemplate;
  }
}
