package com.newlecture.aop.spring.anno.hong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/aop/spring/anno/hong/spring-context.xml");
		KakaoMessenger kakao = (KakaoMessenger) context.getBean("kakao");
		System.out.println(kakao.sendText("hello world"));
		
	}

}
