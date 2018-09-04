package com.newlecture.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class NewlectureAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
/*
    USER-> UI -> DB
    USER-> UI -> DAO -> DB
	USER-> UI -> SERVICE -> DAO -> DB 사용자가 DB를 직접접근할 수 없으니, 사용자와 DB를 연결하는 접점이다. 
	그래서 USER INTERFACE이다.
*/
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

	}

}