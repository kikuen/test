<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>REGISTER PAGE</h1>
	<form action="/board/registerBoard" method="post">
		TITLE <input type="text" name="title"/> <br/>
		CONTENT <textarea rows="10" cols="30" name="content"></textarea><br/>
		WRITER <input type="text" name="writer" /><br/>
		<input type="submit" />
	</form>
</body>
</html>