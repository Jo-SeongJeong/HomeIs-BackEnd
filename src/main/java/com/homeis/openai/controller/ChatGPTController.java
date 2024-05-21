package com.homeis.openai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.openai.dto.ChatCompletion;
import com.homeis.openai.dto.ChatRequestMsg;
import com.homeis.openai.service.ChatGPTService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/prompt")
    public ResponseEntity<String> selectPrompt(@RequestBody ChatCompletion chatCompletion) {
        
    	chatCompletion.setModel("gpt-4o");
    	
        for(ChatRequestMsg msg : chatCompletion.getMessages()) {
        	msg.setRole("system");
        }
        
        System.out.println("param :: " + chatCompletion.toString());
        
        String result = chatGPTService.prompt(chatCompletion);
        return ResponseEntity.ok(result);
    }
}