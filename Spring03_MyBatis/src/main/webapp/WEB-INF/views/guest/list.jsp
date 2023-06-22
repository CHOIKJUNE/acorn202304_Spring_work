<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/list.jsp</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/guest/private/insertform">글 추가</a>
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.num}</td>
					<td><a href="detail">${tmp.writer}</a></td>
					<td>${tmp.content}</td>
					<td>${tmp.regdate}</td>
					<td><a href="private/updateform?num=${tmp.num}">수정</a></td>
					<td>
                     <form action="${pageContext.request.contextPath}/guest/private/delete" method="post">
                        <input type="hidden" name="num" value="${tmp.num }"/> <!-- 핵심 -->
                        <input type="password" name="pwd" placeholder="비밀번호..."/>
                        <button type="submit">삭제</button>
                     </form>
                  </td>
					
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>