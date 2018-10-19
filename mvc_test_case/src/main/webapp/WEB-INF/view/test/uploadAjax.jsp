<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Ajax</title>
	<style>
		.fileDrop{
			width:100%;
			height:200px;
			border:1px solid blue;
		}
	</style>
</head>
<body>
	<h1>Upload Ajax</h1>
	<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	
	<script>
	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			alert("click");
		});
		
		$(".fileDrop").on("drop",function(event){
			event.preventDefault();
			alert("drop");			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			console.log(file);
		});
	</script>
		
	
</body>
</html>