<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- Main content -->
<style type="text/css">
	.fileDrop {
	  width: 80%;
	  height: 100px;
	  border: 1px dotted gray;
	  background-color: lightslategrey;
	  margin: auto;
	}
	.popup {
		position: absolute;
	}
	.back {
		background-color: gray; 
		opacity:0.5; 
		width: 100%; 
		height: 300%; 
		overflow:hidden;  
		z-index:1101;
	}
	
	.front { 
		z-index:1110; 
		opacity:1; 
		boarder:1px;
		margin: auto; 
	}
	
	.show{
	   position:relative;
	   max-width: 1200px; 
	   max-height: 800px; 
	   overflow: auto;       
	} 
  	
</style>
<!-- style="display:none;" -->
<div class='popup back' style="display:none;"></div>
<div id="popup_front" class='popup front' style="display:none;">
	<img id="popup_img">
</div>
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" id="modifyForm" action="modifyPage" method="post">

					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">

					<div class="box-body">
						<div class="form-group">
							<label>BNO</label> 
							<input type="text" name='bno' class="form-control" value="${boardVo.bno}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>Title</label>
							<input type="text" name='title' class="form-control" value="${boardVo.title}">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea class="form-control" name="content" rows="3">${boardVo.content}</textarea>
						</div>
						<div class="form-group">
							<label>Writer</label> 
							<input type="text" name="writer" class="form-control" value="${userInfo.uname}" readonly/>
						</div>
						<div class="form-group">
							<label>File DROP Here</label>
							<div class="fileDrop"></div>
						</div>
					</div>
					<!-- /.box-body -->
				</form>
				<div class="box-footer">
					<div>
						<hr>
					</div>
			
					<ul class="mailbox-attachments clearfix uploadedList">
					</ul>
					<button type="button" class="btn btn-primary" id="saveBtn">SAVE</button>
					<button type="button" class="btn btn-warning" id="cancelBtn">CANCEL</button>
				</div>
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
  <span class="mailbox-attachment-icon has-img">
		<img src="{{imgsrc}}" alt="Attachment">
  </span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
		<i class="fa fa-fw fa-remove"></i>
	</a>
  </div>
</li>                
</script> 
<script>
var bno = ${boardVo.bno};
var template = Handlebars.compile($("#template").html());


$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
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
			   //현재요소에서  가장 가까운 li 요소를 찾는다.
			   that.closest("li").remove();
		   }
	   }
   });
});

$.getJSON("/board/getAttach/"+bno,function(list){
	$(list).each(function(){
		var fileInfo = getFileInfo(this);
		var html = template(fileInfo);
		console.log(html);
		 $(".uploadedList").append(html);
	});
});

$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
	
	var fileLink = $(this).attr("href");
	/* upload.js */
	if(checkImageType(fileLink)){
		event.preventDefault();
		var imgTag = $("#popup_img");
		imgTag.attr("src", fileLink);
		console.log(imgTag.attr("src"));
		$(".popup").show('slow');
		imgTag.addClass("show");		
	}	
});

$("#popup_img").on("click", function(){		
	$(".popup").hide('slow');		
});	





$(document).ready(function() {
	var formObj = $("#modifyForm");
	$("#cancelBtn").on("click", function() {
		formObj.attr("action","/board/listReply");
		formObj.attr("method","get");
		formObj.submit();
	});
	
	$("#saveBtn").on("click", function() {
		var target = formObj;
		var str ="";
		$(".uploadedList .delbtn").each(function(index){
			 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
		});
		target.append(str);
		target.submit();
	});
	
	
/* 	$("#saveBtn").on("click", function() {
		formObj.submit();
	});
	
	formObj.submit(function(event){
		event.preventDefault();
		var target = $(this);
		var str ="";
		$(".uploadedList .delbtn").each(function(index){
			 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
		});
		target.append(str);
		target.get(0).submit();
	}); */
});
</script>
<%@include file="../include/footer.jsp"%>
