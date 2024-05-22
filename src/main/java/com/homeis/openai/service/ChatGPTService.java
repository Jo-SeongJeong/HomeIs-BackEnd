package com.homeis.openai.service;


import com.homeis.openai.dto.ChatCompletion;

public interface ChatGPTService {
    String prompt(ChatCompletion chatCompletion);
}