<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
<style>
h1 {
	margin-top: 50px;
}

.form-select {
	width: 130px;
}
a {
	text-decoration: none;
}
div {
 	border: 1px solid red; 
}
.container {
	height: 100vh;
 	border: 1px solid black; 
}
.board {
	margin: 60px auto 0px auto;
	heigth: 100%;
	width: 82%;
 	border: 1px solid green; 
}
td a img {
	margin: 0px 2px 3px 0px;
}
</style>
</head>
<body>
	<!-- 나중에 화면 상에서 없앨 회원가입,로그인 하이퍼링크 -->
	<c:if test="${empty id}">
		<a href="${pageContext.request.contextPath}/users/signup_form">회원가입</a>
		<a href="${pageContext.request.contextPath}/users/login_form">로그인</a>
	</c:if>
	<c:if test="${not empty id}">
		<strong><a href="">${id}님 </a></strong>로그인중...
		<a href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/board/insert_form">글쓰기</a>
	<div class="container text-center">
	<div class="row">
		<div class="col-md-4">
			<h1>자유게시판</h1>
		</div>
		<div class="col-md-4 offset-md-4" style="margin-top: 62px;">
			<form class="d-flex align-items-center">
				<div class="flex-grow-1 me-2">
					<select class="form-select" aria-label="Default select example">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="titleContent">제목+내용</option>
					</select>
				</div>
				<div class="flex-grow-1 me-2">
					<input type="text" class="form-control" />
				</div>
				<div class="flex-shrink-0">
					<button type="submit" class="btn btn-primary">검색</button>
				</div>
			</form>
		</div>
		<div class="board">
		<table class="table">
  <tbody>
  <c:forEach var="i" begin="1" end="15">
    <tr> 
      <td style="width:50%;">Mark</td>
      <td style="width:20%;"><a href=""><img src="https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_16.png">작성자</a></td>
      <td>@mdo</td>
      <td>ㅇㅇ</td>
      <td>ㅇㅇ</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
		</div>
	</div>
</div>
</body>
</html>
