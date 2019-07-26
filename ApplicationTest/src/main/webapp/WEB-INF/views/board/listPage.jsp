<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ul{
		list-style:none;
	}
	
	ul li {
		float:left;
		margin :10px;
	}
</style>
</head>
<body>
	<h1>LIST PAGE</h1>
	
	<table border=1>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- boardList -->
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.bno}</td>
				<td><a href="/board/read?bno=${board.bno}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td>${board.regdate}</td>
				<td>${board.viewcnt}</td>
			</tr>
		</c:forEach>
	</table>
	<ul>
		<c:if test="${!empty PageMaker}">
			<c:if test="${PageMaker.prev}">
			<li>
				<a href="/board/listPage?page=${PageMaker.startPage - 1}&perPageNum=${cri.perPageNum}">
				&laquo;</a>
			</li>
			</c:if>
			
			<c:forEach var ="i" begin="${PageMaker.startPage}" end="${PageMaker.endPage}">
				<li>
				<a href="/board/listPage?page=${i}&perPageNum=${cri.perPageNum}">${i}</a>
				</li>
			</c:forEach>
			
			<c:if test="${PageMaker.next}">
			<li>
				<a href="/board/listPage?page=${PageMaker.endPage + 1}&perPageNum=${cri.perPageNum}">
				&raquo;</a>
			</li>
			</c:if>
		</c:if>
	</ul>
	<script>
		var result = '${result}';
		if(result != null && result != ""){
			alert("결과"+result);	
		}
	</script>
</body>
</html>