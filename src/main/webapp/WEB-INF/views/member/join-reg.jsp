<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<section id="form-section">
		<h1>회원 가입 페이지</h1>
			<form method="post" enctype="multipart/form-data"> 
				<table>
					<tr>
						<td>
							<label>사진 :</label>
							<img class="photo" src="" />
							<!-- 안보임 -->
							<input type="file" id="file-button" name="photo-file" hidden="true" value="사진선택" />
							<span class="photo-button">사진선택</span>
						</td>  
					</tr>
					<tr>
						<td>
							<label>아이디 :</label>
							<input type="text" name="id" class="id" value="${uid }" />
							<input type="button" class="id-check-button" value="중복조회" />
						</td>
					</tr>
					<tr>
						<td>
							<label>비밀번호 :</label>
							<input type="password" name="pwd" required="required" />
						</td>
					</tr>
					<tr>
						<td>
							<label>이름 :</label>
							<input type="text" name="name" required="required" />
						</td>
					</tr>
					<!-- 이메일은 인증 이메일 수정 불가능 readonly 쓰기만 안됨, 전송가능 -->
					<tr>
						<td>
							<label>이메일 :</label>
							<input type="text" name="email" value="${email }" readonly="readonly" required="required" />
						</td>
					</tr>
					<tr>
						<td>
							<label>다음 계산결과는?<img alt="계산식" name="calc" src="moonjae.jpg"></label>
							<input name="moonjae" type="text"> 
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="회원가입" />
						</td>
					</tr>
				</table>
			</form>
		<!-- 아이디, 이름, 이메일, 비밀번호  -->
		<!-- 생년월일, 전화번호, 닉네임, 성별 -->  
		<!-- 필수와 옵션은 분리한다. -->
	</section>
</main>

<script type="text/javascript">  
	window.addEventListener("load", function(){ 

		// 폼 섹션
		var formSection = document.querySelector("#form-section");

		// id 검사 버튼
		var idCheckButton = formSection.querySelector(".id-check-button");
		var idInput = formSection.querySelector("input[name='id']");
		
		// 전송 버튼
		var submitButton = formSection.querySelector("input[type='submit']");
 
		//사진선택
		var photoButton = formSection.querySelector(".photo-button");
		var fileButton = formSection.querySelector("#file-button");
		var photo = formSection.querySelector(".photo");
		 
		// 상태값  
		var idOk = false;

		fileButton.onchange = function(e){

			var file = fileButton.files[0];
			
			// 선택한 파일에 대한 조건 제어
			for(var p in file){
				console.log(p);
			}
			
			console.log(file.type); //image/jpeg
			
			if(file.type.indexOf("image/") < 0){
				alert("이미지가 아닙니다.");
				return;
			}
			
			if(file.size > 1024*1024*10){
				alert("죄송합니다. 10MB를 초과할 수 없습니다.")
				return;
			}
			
			// 브라우저 메모리에 파일이 올라감
			// html5 기능 로컬 이미지 불러들이기
			var reader = new FileReader();
			reader.onload = function(evt){ 
				photo.src = evt.target.result; // 여기서 file 들어간다.
			};
			// 다 읽어 왔을 때.. background에서.. 
			reader.readAsDataURL(file);	

		};

		photoButton.onclick = function(e){
			var event = new MouseEvent("click",{
				"view" : window,
				"bubbles" : true,
				"cancelable" : true
			});
			fileButton.dispatchEvent(event);
		}

		submitButton.onclick = function(e){
			// 조건검사를 해야 함
			//아이디가 유효하지 않거나 유효성 검사를 통과하지못했을 떄.... 
			if(!idOk){ //true가 아니면..
				e.preventDefault();
				alert("아이디 중복검사를 하지 않았거나 유효한 아이디가 아닙니다.");
			}
		}

		idCheckButton.onclick = function(e){
			// ajax -> 협력자 백엔드에게 연락해서 알아봐야 함.
			// member/is-id-duplicated
			var id = idInput.value;
			// 요청
			var request = new XMLHttpRequest();
			
			request.onload = function(e){
		        if (request.status === 200) {
				  var duplicated = JSON.parse(request.responseText);
				  if(duplicated){
					  alert("이미 사용중인 아이디입니다.");
					  return;
				  }
				alert("사용해도 좋습니다~~");
				idOk = true;  
		        } else{  
		          alert('request에 뭔가 문제가 있어요.');
		        }
			}
			request.open("get", "is-id-duplicated?id=" + id, true);
			request.send();
		}
	});
</script>