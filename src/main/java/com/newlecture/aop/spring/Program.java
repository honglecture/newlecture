package com.newlecture.aop.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {

		// cal 객체 생성
		// proxy 객체 생성
		// 이 둘의 결합
		// 이것들을 스프링 컨텍스트에 옮긴다.
		// 스프링이 원본 객체를 줄지
		// 아니면 프록시를 줄지 여기 코드에서는 알 수 없음
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/aop/spring/spring-context.xml");
		Calculator cal = (Calculator) context.getBean("cal");
		int result = cal.add(3, 0); // 가짜가 실행된다.
		System.out.println(result);
		
	}

}
