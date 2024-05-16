package com.homeis.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.homeis.util.JWTUtil;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor{
	private final JWTUtil jwtUtil; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//단순 조회 요청과 preflight 요청인 경우, true 로 넘김
		String method = request.getMethod();
		String requestURI = request.getRequestURI();
		log.debug("AuthInterceptor()의 preHandle실행 method:{}", method);
		
//		System.out.println(method);
//		System.out.println(requestURI);
		
		if(requestURI.startsWith("/homeis/loan")) return checkToken(request, response);
		
		if(method.equals("GET") || method.equals("OPTIONS")) return true;
		
//		System.out.println(requestURI.startsWith("/homeis/loan"));
		
		return checkToken(request, response);
	}
	
	private boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tokenHeader = request.getHeader("Authorization");	//Header에서 토큰 정보 추출
//		System.out.println(tokenHeader);
		
		//토큰 헤더가 없거나 Bearer로 시작하지 않는 경우
		if(tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
			response.setStatus(401);
			response.getWriter().write("Unauthorized");
			return false;
		}
		//토큰이 유효하지 않은 경우
		String token = tokenHeader.substring(7);
		if(!jwtUtil.isValid(token) ) {	
			response.setStatus(401);
			response.getWriter().write("Unauthorized");
			return false;
		}
		
		System.out.println("ASDSADSAD");
		//토큰이 유효한 경우
		return true;
	}
}
