package com.newlecture.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.di.ui.FlowExamConsole;

public class Program {

	public static void main(String[] args) {
		/* Exam exam = new Exam(1,1,1); */

		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/di/spring-context.xml");
		FlowExamConsole console = (FlowExamConsole) context.getBean("console");
		console.print();

	}

}