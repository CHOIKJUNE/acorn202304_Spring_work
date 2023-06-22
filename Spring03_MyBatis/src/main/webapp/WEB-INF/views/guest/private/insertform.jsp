<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
<title>/cafe/private/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>새글 작성 양식</h1>
		<form action="${pageContext.request.contextPath}/guest/private/insert" method="post">
			<div class="mb-2">
				<label for="writer" class="form-label">작성자</label>
				<input type="text" name="writer" id="writer" class="form-control">
			</div>
			<div class="mb-2">
				<label for="content" class="form-label">내용</label>
				<textarea name="content" id="content" rows="10" class="form-control"></textarea>
			</div>
			<div class="mb-2">
				<label for="pwd" class="form-label">비밀번호</label>
				<input type="password" id="pwd" name="pwd" class="form-control">
			</div>
			<!-- 비밀번호 확인은 나중에 -->
			<button type="submit" class="btn btn-success">저장</button>
		</form>
	</div>
</body>
</html>