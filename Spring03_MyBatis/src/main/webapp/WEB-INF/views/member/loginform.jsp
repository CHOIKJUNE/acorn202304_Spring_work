<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>loginform</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>로그인 폼</h1>
		<form action="login" method="post">
		<!-- 폼에 입력한 정보외에 추가로 같이 전송할 값이 있으면 input type="hidden"을 활용 -->
		<input type="hidden" name="url">
			<div>
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="writer" id="id">
			</div>
			<div>
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd">
			</div>
			<button class="btn btn-primary" type="submit">로그인</button>
</body>
</html>