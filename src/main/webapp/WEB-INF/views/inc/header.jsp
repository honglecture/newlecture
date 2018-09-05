<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header"> 
	<h1>  
		<a href="/index">
			<picture>
				 <!-- 최소 700이상이면 바뀐다. -->
			 	 <!-- <source media="(min-width: 700px)" srcset="/resources/images/okinawa.jpg"> -->
				 <img src="/resources/academy/sist/images/logo.png">
			</picture>
		</a>
	</h1>  
	<section id="header-buttons">  
		<h1 class="hidden">헤더 버튼</h1> 
		<input class="button find-button" type="button" value="검색">
		<input class="button hamburger-button" type="button" value="메뉴보기">
	</section>
	

	
	<!-- aside로 묶는다. -->
	<aside id="login-info">
		<h1 class="hidden">로그인 정보</h1>
		<section class="profile">
			<h1 class="hidden">프로필</h1>
			<div>
				<div class="photo" style="background: url('/resources/images/okinawa.jpg') no-repeat center; background-size: cover; "></div>
				<div class="uid"><span>newlec</span></div>
<%-- 				<%=
					request.getUserPrincipal().getName()
				%>
				${pageContext.request.userPrincipal} --%>    
				<c:if test="${empty pageContext.request.userPrincipal}">
					<div class="auth-status"><a href="/member/login">로그인</a></div>
				</c:if>
				<c:if test="${not empty pageContext.request.userPrincipal}"> 
					<div class="auth-status"><a href="/member/logout">로그아웃</a></div>
				</c:if>  
				<div class="notice"><span>강사공지 : </span><a href="#">3</a></div>
			</div>     
		</section>   
		<section id="teacher-menu">
			<h1 class="hidden">강사메뉴</h1>
			<ul>
				<li><a href="/teacher/question/type">문제관리</a></li>
				<li><a href="">시험관리</a></li>
				<li><a href="">일정관리</a></li>
				<li><a href="">수업관리</a></li>
			</ul>
		</section>
	</aside>
	
</header>
<script>
window.addEventListener("load", function(){
    var header = document.querySelector("#header");
    var headerButtons = document.querySelector("#header-buttons");
    header.onclick = function(e){
        var el = e.target;
        if(el.nodeName =="HEADER")
            header.classList.remove("menu-show");
        else 
            return;
    }; 
    headerButtons.onclick = function(e){
        header.classList.add("menu-show"); 
        e.stopPropagation();
    };
});
</script>