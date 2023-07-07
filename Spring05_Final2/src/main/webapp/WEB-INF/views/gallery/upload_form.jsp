<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
<style>
 	#ajaxForm { 
 		display: none; 
 	} 
</style>
</head>
<body>
	<h2>이미지 업로드</h2>
		<form action="${pageContext.request.contextPath}/gallery/image_upload" method="post" id="imageForm">
		<div class="card" style="width: 18rem;">
			 <img src="..." class="card-img-top" alt="...">
  		</div>
  		<button type="button" id="imageButton">사진 선택</button><br>
		<div class="form-floating">
			<textarea class="form-control" name="caption" placeholder="이미지 설명..." 
			id="floatingTextarea2" style="height: 100px"></textarea>
			<label for="floatingTextarea2">이미지 설명</label><br>
		</div>
		<div id="hiddenImage">
		</div>
			<button type="submit">업로드</button>
		</form>
		
		<form id="ajaxForm" action="${pageContext.request.contextPath}/gallery/ajaxImage" method="post" enctype="multipart/form-data">
			프로필 사진
			<input type="file" id="image" name="ajaxImage" accept=".jpg, .png, .gif"/>
			<button type="submit">업로드</button>
		</form>
	<script>
		document.querySelector("#imageButton").addEventListener("click", ()=>{
			document.querySelector("#image").click();
		});
		
		document.querySelector("#image").addEventListener("change", ()=>{
			const form = document.querySelector("#ajaxForm");
			const ajaxForm = new FormData(form);
			fetch("${pageContext.request.contextPath}/gallery/ajaxImage", {
				method: "POST",
				body: ajaxForm
			})
			.then(function(response) {
				return response.json();
			}).then(function(data) {
				console.log(data);
				const img = `<img src="${pageContext.request.contextPath}\${data.imagePath}" class="card-img-top">`;
				document.querySelector(".card").innerHTML = img;
				const hiddenImg = `<input type="hidden" name="image" value="${pageContext.request.contextPath}\${data.imagePath}">`;
				document.querySelector("#hiddenImage").innerHTML = hiddenImg;
			})
		})
	</script>
	
	   <script>
// 	   document.querySelector("#imageButton").addEventListener("click", ()=>{
// 			document.querySelector("#image").click();
// 		});
	   
//        document.querySelector("#image").addEventListener("change", (e)=>{
//          //선택된 파일 배열 객체를 얻어낸다.
//          const files = e.target.files;
//          //만일 파일 데이터가 존재한다면
//          if(files.length > 0){
//             //파일로 부터 데이터를 읽어들일 객체 생성
//             const reader=new FileReader();
//             //로딩이 완료(파일데이터를 모드 읽었을때) 되었을때 실행할 함수 등록
//             reader.onload=(event)=>{
//                //읽은 파일 데이터 얻어내기 
//                const data=event.target.result;
//                console.log(data);
//             };
//             //파일을 DataURL 형식의 문자열로 읽어들이기
//             reader.readAsDataURL(files[0]);
//          }
//       });
   </script>
</body>
</html>