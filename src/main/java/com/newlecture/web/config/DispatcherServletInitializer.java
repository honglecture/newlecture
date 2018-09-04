package com.newlecture.web.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xmlDispatcherServlet

	
 /* <servlet-mapping>
<servlet-name>dispatcher</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>*/

/*2. 
servlet-context.xml service-context.xml, securety-context.xml
<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
/WEB-INF/spring/service-context.xml
/WEB-INF/spring/security-context.xml
</param-value>
</context-param>*/

/*
4. welcomefile 
*/

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		/*<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/servlet-context.xml</param-value>
		</init-param>*/
		//class ServletContextConfig{}
		return new Class[] {
				 ServletContextConfig.class 
				,ServiceContextConfig.class
				,SecurityContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		/*1.
		<servlet-mapping>
			<servlet-name>dispatcher</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		*/
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return super.getServletFilters();
	}
	
}