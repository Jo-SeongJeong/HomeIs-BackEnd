package com.homeis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
		.addPathPatterns("/user/**", "/board/**", "/qna/**", "/notice/**", "/loan/**", "/map/**", "/homesta/**", "/prompt/**");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 기능 완성하고 마지막에 최적화 하기
//		registry.addMapping("/auth/**")
//		.allowedOrigins("http://localhost:5173", "http://192.168.206.40:5173")
//		.allowedMethods("POST");
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:5173", "http://192.168.206.40:5173")
		.allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("file:///C:/ssafy/save/");
	}

}
