package com.newlecture.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//@ComponentScan = <context:component-scan base-package="com.newlecture.web.controller" />
@Configuration
@ComponentScan(basePackages = "com.newlecture.web.controller")
@EnableWebMvc //annotation-driven
public class ServletContextConfig implements WebMvcConfigurer {

	/*
	 * <bean
	 * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 * <property name="prefix" value="/WEB-INF/views/" /> <property name="suffix"
	 * value=".jsp" /> <property name="order" value="2" /> </bean>
	 */

	// 이 클래스를 생성해서 달라고 하는건데, 메소드 이름은 컨테이너에 담겨진 이름 get이 들어가면 안된다.
	/* 왼쪽 클래스를 객체화 해서 오른쪽 이름으로 IoC에 담아주세요 */
	/*
	 * <bean name="internalResourceViewResolver"
	 * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 * <property name="prefix" value="/WEB-INF/views/" /> <property name="suffix"
	 * value=".jsp" /> <property name="order" value="2" /> </bean>
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		return resolver;
	}

	/*
	 * public int add(Integer... args) { return 0; }
	 */
	
/*	<bean class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
		<property name="definitions" value="/WEB-INF/tiles.xml" />
	</bean>
	
	<bean class="org.springframework.web.servlet.view.UrlBasedViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView" />
		<property name="order" value="1" />
	</bean>*/

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
		return tilesConfigurer;
	}

	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(1);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<mvc:resources location="/resources/" mapping="/resources/**" />
		registry
		.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/");
	}
	

}