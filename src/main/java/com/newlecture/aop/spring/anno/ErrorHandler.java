package com.newlecture.aop.spring.anno;

import org.springframework.aop.ThrowsAdvice;

public class ErrorHandler implements ThrowsAdvice {

	// 오류를 처리하는 코드를 작성합니다.
	// 우리는 어떤 예외가 발생할 수 있는데요. target에서 메소드를 호출하다가
	// 여기서는 그 예외를 받는 곳입니다.
	// 그 예외를 받아서 처리하는 곳입니다.
	// 즉, 예외 처리하는 로직의 집중화, 장소

	// 처리할 예외객체 파라미터로 등록
	public void afterThrowing(ArithmeticException e) throws Throwable {
		System.out.println("ArithmeticException 에러 입니다.");
	}
	public void afterThrowing(IllegalArgumentException e) throws Throwable {
		System.out.println("IllegalArgumentException 에러입니다.");
	}

}
