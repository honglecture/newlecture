package com.newlecture.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {

		Calculator cal = new NewlecCalculator();
		
		//cal은 원본, 원본을 직접 사용하지 않고 프록시(대리자)를 통해 사용한다.
		
		//loader(실제객체)의 로더, 인터페이스, 곁다리업무
		Calculator calProxy = (Calculator) Proxy.newProxyInstance(
				NewlecCalculator.class.getClassLoader(), 
				new Class[] {Calculator.class}, 
				new InvocationHandler() {
					//접점에 있는 것이 invoke
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						//곁다리 업무를 꽂는다.
						long start = System.currentTimeMillis();
						//--------------------------------------
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
						}
						//실제 객체를 호출하여 반환한다. 
						Object result = method.invoke(cal, args);
						//--------------------------------------
						long end = System.currentTimeMillis();
						String between = (end - start) + "ms가 걸림";
						System.out.println(between);
						
						return result;
					}
				});
		
		int result = calProxy.add(10, 5); // 가짜가 실행된다.
		System.out.println(result);

	}

}
