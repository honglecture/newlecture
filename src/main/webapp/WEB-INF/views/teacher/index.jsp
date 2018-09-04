<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
	<section>
		<h1>선생님 홈페이지</h1>  
		<c:forEach items="${subjects }" var="subject">
			 ${subject.title }<br />
		</c:forEach>
	</section> 
</main>