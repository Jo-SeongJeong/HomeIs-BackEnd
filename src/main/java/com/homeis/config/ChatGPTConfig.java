package com.homeis.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * ChatGPT에서 사용하는 환경 구성
 *
 * @author : lee
 * @fileName : RestTemplate
 * @since : 01/18/24
 */
@Configuration
public class ChatGPTConfig {

    @Value("${openai.api.key}")
    private String key;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}