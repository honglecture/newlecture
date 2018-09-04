<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
/*--reset---------------------------------------------- */
body {
	margin: 0px;
}

h1 { 
	margin: 0px;
	padding : 0px; 
}

ul {
	padding: 0px;
	margin: 0px;
}

li {
	list-style-type: none;
}

a {
	text-decoration: none;
	color: black;
	padding: 0px;
	margin: 0px;
}
/*--custom style----------------------------------------*/
.hidden {
	display: none;
}

/*--component style-------------------------------------*/

.button{
	width: 24px;
	height: 24px;
	overflow: hidden; 
	text-indent: -999px;  
	border: 0px;
	padding: 0px;
}

	.find-button {
		background: url("/resources/images/icon-set.png") no-repeat -24px 0px;
	}  
	
	.hamburger-button {
		background: url("/resources/images/icon-set.png") no-repeat 0px 0px;
	}
	
.photo{
	width : 150px;
	height : 150px;
	border : 1px solid #a9a9a9;
	border-radius: 75px; 
/* 	background-size: cover;  */
	/*cover는 빈 공백이 없게 하라*/  
}

.photo-sm{

}
  
/*--header---------------------------------------------- */
#header {
	width: 100%;    
	height: 45px; 
	position: relative;
	line-height: 45px; 
	border-bottom : 1px solid #a9a9a9;
}    
 
	#header > h1{ 
		display: inline-block;
		position: absolute;
		left: 10px;
		top : 0px;  
	}

	#header:after {
		display: block;
		position: fixed;
		background : #000000;
		top: 0px;   
		left: 0px;
		width: 100%; 
		height: 0%; /*장막을 위로 올린다.*/ 
		content: "";
		opacity: 0; 
		transition : 500ms opacity ease;
	}
	
	
#header-buttons{ 
	height : 100%; 
	position: absolute;  
	top:0px; 
	right:0px;
	padding-right: 10px; 
}  


	#header #login-info {
		/*가상클래쓰를 쓰기 위해 header에 클래스를 둔다. 형제로 가즈아*/
		position: fixed;
		top: 0px;
		right: -80%;
		width: 80%;
		height: 100%; 
		background: #ffffff;
		z-index: 10;
		display: block;
		transition : 500ms right ease;
	}

	#header.menu-show #login-info {
		right: 0%;
	}
	
	#header.menu-show:after {
		opacity: 0.5; 
		height: 100%;
	}
	  
	 
	#teacher-menu li{
		margin-left: auto;    
		margin-right : auto; 
		width: 100px;
		text-align: center;
	}
	
	@media all and (min-width:700px){
		#header{
			background: pink;
		}
	}
</style>
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