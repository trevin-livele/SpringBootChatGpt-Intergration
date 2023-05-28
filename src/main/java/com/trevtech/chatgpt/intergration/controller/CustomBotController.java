package com.trevtech.chatgpt.intergration.controller;

import com.trevtech.chatgpt.intergration.dto.ChatGptRequest;
import com.trevtech.chatgpt.intergration.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/bot")
public class CustomBotController {

    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;
    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGptRequest request = new ChatGptRequest(model,prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }


}
