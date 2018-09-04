package com.newlecture.aop.spring.anno;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterHandler implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object object) throws Throwable {
		// 반환값을 가지고 조건을 처리하거나 할 때 사용되는 로직 : After
		int result = (Integer) returnValue; // object => int
		if (result < 0) {
			System.out.println("0보다 작아요 음수입니다");
		} else {
			System.out.println("양수입니다.");
		}
	}

}
