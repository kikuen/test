<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>READ PAGE</h1>
	TITLE : ${boardVO.title}  <br/>
	WRITER : ${boardVO.writer}  <br/>
	CONTENT : ${boardVO.content}  <br/>
	<form id="readForm">
		<input type="hidden" name="bno" value="${boardVO.bno}"/>
		<input type="button" id="modifyBtn" value="MODIFY"/>
		<input type="button" id="deleteBtn" value="DELETE"/>
		<input type="button" id="listBtn" value="LIST PAGE"/>
	</form>
	
<script>
	var form = $("#readForm");
	
	$("#modifyBtn").click(function(){
		form.attr("action","/board/modify");
		form.submit();
	});
	
	$("#deleteBtn").click(function(){
		form.attr("action","/board/delete");
		form.submit();
	});
		
	$("#listBtn").click(function(){
		location.href="/board/listPage";
	});
	
</script>	
</body>
</html>