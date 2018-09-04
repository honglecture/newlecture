package com.newlecture.aop.spring.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
public class CalculatorAspectHandler {

	// pointcut 값을 줘야 한다. 실행될 대상 @Before
	@Before(value = "execution (* *..NewlecCalculator.add(..))")
	public void authorHandler() {
		System.out.println("권한 설정");
	}

	// pointcut 값을 줘야 한다. 실행될 대상 @After
	@AfterReturning(pointcut = "execution (* *..NewlecCalculator.add(..))", returning = "returnValue")
	public void after(JoinPoint joinPoint, Object returnValue) {
		int result = (Integer) returnValue;
		if (result < 0)
			System.out.println("음수를 반환하였습니다.");
	}

	// pointcut 값을 줘야 한다. 실행될 대상 @AfterThrowing(pointcut과 Throwable변수명)
	@AfterThrowing(pointcut = "execution (* *..NewlecCalculator.div(..))", throwing = "e")
	public void errorHandler(JoinPoint joinPoint, Throwable e) {
		System.out.println("죄송합니다. 오류가 발생했습니다.");
	}

	// pointcut 값을 줘야 한다. 실행될 대상 @Around(pointcut)
	@Around(value = "execution (* *..NewlecCalculator.add(..))")
	public Object logHandler(ProceedingJoinPoint joinPoint) {
		// 스프링 프레엠워크가 제공하는 타이머
		StopWatch watch = new StopWatch();
		watch.start();
		// --------------------------------------

		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// --------------------------------------
		watch.stop();
		long duration = watch.getTotalTimeMillis();
		String message = duration + "ms가 걸림";
		System.out.println(message);

		return result;
	}

}