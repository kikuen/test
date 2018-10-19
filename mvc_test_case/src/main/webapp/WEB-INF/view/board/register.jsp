<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style>
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
}
</style>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" id="registerForm" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> 
							<input type="text" name='title' class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea name="content" class="form-control" rows="3" placeholder="Enter ..."></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${userInfo.uname}" readonly>
							<input type="hidden" name="uno" value="${userInfo.uno}"/>
						</div>
						<div class="form-group">
							<label>File DROP Here</label>
							<div class="fileDrop"></div>
						</div>
					</div>
					<!-- /.box-body -->
				
					<div class="box-footer">
						<div>
							<hr>
						</div>
				
						<ul class="mailbox-attachments clearfix uploadedList">
						</ul>
				
						<button type="submit" class="btn btn-primary">Submit</button>
				
					</div>
				</form>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
</script>    

<script>

var template = Handlebars.compile($("#template").html());

$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	// jQuery.event 의 이벤트를 막고
	event.preventDefault();
	
	// Dom 실제 브라우저의 이벤트를 받아 온다.
	var files = event.originalEvent.dataTransfer.files;
	console.log(files.length);
	/* 
	FormData 인터페이스는 XMLHttpRequest.send()로 쉽게 보내질 수 있는 폼 field와
	그 값들로 나타나는 key/value쌍들을 쉽게 만들 수 있는 방법을 제공한다. 
	만약에 인코딩 타입이 "multipart/form-data"로 설정이 되어 있으면
	폼이 사용하는 것과 같은 포맷으로 사용한다.

	FormData 객체는 entries() 대신 for...of로 바로 사용되어 질 수 있다
	: for (var p of myFormData)는 (var p of myFormData.entries())와 같다.
	*/
	
	/* 다중 파일 업로드 지원 */
	for(var i=0; i<files.length; i++){
		var file = files[i];
	
		var formData = new FormData();
		formData.append("file", file);	
		
		$.ajax({
			  url: '/uploadAjax',
			  data: formData,
			  dataType:'text',
			  processData: false,
			  contentType: false,
			  type: 'POST',
			  success: function(data){
				  var fileInfo = getFileInfo(data);				  
				  var html = template(fileInfo);				  
				  $(".uploadedList").append(html);
			  }
		});	
	}
});

$(".uploadedList").on("click", ".delbtn", function(event){
	
	event.preventDefault();
	
	var that = $(this);
	 
	$.ajax({
	   url:"/deleteFile",
	   type:"post",
	   data: {fileName:$(this).attr("href")},
	   dataType:"text",
	   success:function(result){
		   if(result == 'deleted'){
			   that.closest("li").remove();
		   }
	   }
   });
});


$("#registerForm").submit(function(event){
	event.preventDefault();
	var that = $(this);
	var str ="";
	$(".uploadedList .delbtn").each(function(index){
		 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
	});
	that.append(str);
	that.get(0).submit();
});



</script>

<%@include file="../include/footer.jsp"%>
