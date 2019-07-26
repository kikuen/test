<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	function listPage(){
		location.href="/board/listPage";
	}
</script>
	<h1>MODIFY PAGE</h1>
	<form action="/board/modifyAccept" method="post">
	TITLE : <input type="text" name="title" value="${boardVO.title}"/><br/>
	WRITER : <input type="text" name="writer" value="${boardVO.writer}"/><br/>
	CONTENT : <textarea name="content">${boardVO.content}</textarea>
	<input type="hidden" name="bno" value="${boardVO.bno}"/><br/>
	<input type="submit"/> <input type="button" onclick="listPage();" value="ALL LIST"/>
	
	</form>
</body>
</html>