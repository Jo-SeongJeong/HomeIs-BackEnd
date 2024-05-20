package com.homeis.openai.service;


import com.homeis.openai.dto.ChatCompletion;

import java.util.Map;

/**
 * ChatGPT 서비스 인터페이스
 *
 * @author : lee
 * @fileName : ChatGPTService
 * @since : 12/29/23
 */

public interface ChatGPTService {
    Map<String, Object> prompt(ChatCompletion chatCompletion);
}