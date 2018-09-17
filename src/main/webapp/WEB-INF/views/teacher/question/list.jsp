<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<main>
	<section>
		<h1>검색분류</h1>
		<ul>
			<li>
				<label>교과목</label>
				<select>
				<c:forEach items="${subjects }" var="subject">
					<option value="${subject.id }">${subject.title }</option>				
				</c:forEach>
				</select>
			</li>
			<li>
				<label>난이도</label>
				<select>
				<c:forEach items="${levels }" var="level">
					<option value="${level.id }">${level.name }</option>				
				</c:forEach>
				</select>
			</li>
			<li>
				<label>검색어</label>
				<input type="text" name="query" />
			</li>
		</ul>
	</section>
	
	<section>
		<h1>문제목록</h1>
		<div>
			<h1>검색조건</h1>
			내것만으로 한정
			최신순
			난이도순
		</div>
		<div>
			<h1>문제목록</h1>
		</div>
		<div>
			<h1>페이저</h1>
			1,2,3,4,5
		</div>
	</section>
	
</main>