<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정확인</h1>
	<script>
		alert("수정되었습니다.");
		location.href = "${pageContext.request.contextPath}/member/list";
	</script>
</body>
</html>