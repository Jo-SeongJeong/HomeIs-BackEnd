package com.homeis.openai.dto;
import lombok.*;

import java.util.List;

/**
 * 새로운 모델에 대한 요청 객체를 관리합니다. : gpt-4, gpt-4 turbo, gpt-3.5-turbo
 *
 * @author : lee
 * @fileName : ChatCompletionDto
 * @since : 1/18/24
 */

@Data
public class ChatCompletion {
    private String model;

    private List<ChatRequestMsg> messages;
    
}