<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<a href="">${id}</a>님 로그인중...
	<a href="users/logout">로그아웃</a>
	<h1>인덱스 페이지입니다.</h1>
	<ul>
		<li><a href="users/signupform">회원가입</a></li>
		<li><a href="users/loginform">로그인</a></li>
		<li><a href="member/list">회원목록 보기</a></li>
		<li><a href="guest/list">방명록 보기</a></li>
	</ul> 
	<!-- resources는 dispatcher로부터 exclude된다.-->
	<img src="${pageContext.request.contextPath}/resources/images/kim1.png">
	<h2>공지사항</h2>
	<ul>
		<c:forEach var="tmp" items="${requestScope.noticeList}">
			<li>${tmp}</li>
		</c:forEach>
	</ul>
</body>
</html>
