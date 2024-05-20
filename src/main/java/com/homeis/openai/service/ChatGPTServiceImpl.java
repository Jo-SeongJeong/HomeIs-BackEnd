package com.homeis.openai.service;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeis.config.ChatGPTConfig;
import com.homeis.openai.dto.ChatCompletion;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;

    @Value("${openai.model}")
    private String modelUrl;

    @Value("${openai.api.url}")
    private String promptUrl;
    
    @Override
    public Map<String, Object> prompt(ChatCompletion chatCompletion) {
        System.out.println("[+] 신규 프롬프트를 수행합니다.");

        Map<String, Object> resultMap = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity<ChatCompletion> requestEntity = new HttpEntity<>(chatCompletion, headers);
        ResponseEntity<String> response = chatGPTConfig
                .restTemplate()
                .exchange(promptUrl, HttpMethod.POST, requestEntity, String.class);
        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            ObjectMapper om = new ObjectMapper();
            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
        	System.out.println("JsonMappingException :: " + e.getMessage());
        } catch (RuntimeException e) {
        	System.out.println("RuntimeException :: " + e.getMessage());
        }
        return resultMap;
    }
}