<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<section>
		<h1>회원 가입 페이지</h1>
			<form method="post">
				<table>
					<tr>
						<td>
							<label>사진 :</label>
							<img src="" />
							<!-- 안보임 -->
							<input type="file" hidden="true" class="hidden" value="사진선택" />
							<span>사진선택</span>
						</td>
						<td>
							<label>아이디 :</label>
							<input type="text" name="id" value="${uid }"/>
							<input type="button"  value="중복조회" />
						</td>
					</tr>
					<tr>
						<td>
							<label>비밀번호 :</label>
							<input type="password" name="pwd" />
						</td>
					</tr>
					<tr>
						<td>
							<label>이름 :</label>
							<input type="text" name="name" />
						</td>
					</tr>
					<!-- 이메일은 인증 이메일 수정 불가능 readonly 쓰기만 안됨, 전송가능 -->
					<tr>
						<td>
							<label>이메일 :</label>
							<input type="text" name="email" value="${email }" readonly="readonly" />
						</td>
					</tr>
				</table>
			</form>
		<!-- 아이디, 이름, 이메일, 비밀번호  -->
		<!-- 생년월일, 전화번호, 닉네임, 성별 -->
		<!-- 필수와 옵션은 분리한다. -->
	</section>
</main>