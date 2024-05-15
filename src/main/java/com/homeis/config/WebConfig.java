package com.homeis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.homeis.interceptor.AuthInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@MapperScan(basePackages = "com.homeis.*.model.mapper")
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{
	private final AuthInterceptor authInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(authInterceptor)
		.addPathPatterns("/board/**", "/qna/**");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/auth/**")
		.allowedOrigins("http://localhost:5173", "http://192.168.206.40:5173")
		.allowedMethods("POST");
		registry.addMapping("/board/**")
		.allowedOrigins("http://localhost:5173", "http://192.168.206.40:5173")
		.allowedMethods("GET","POST","PUT","DELETE","OPTIONS");
	}

}
