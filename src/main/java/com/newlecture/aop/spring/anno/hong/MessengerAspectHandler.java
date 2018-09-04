package com.newlecture.aop.spring.anno.hong;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MessengerAspectHandler {

	@Before(value = "execution (* *..KakaoMessenger.sendText(..))")
	public void auth() {
		System.out.println("before 통신 작업을 하기 위한 준비작업 or 권한 작업");
	}
	
}