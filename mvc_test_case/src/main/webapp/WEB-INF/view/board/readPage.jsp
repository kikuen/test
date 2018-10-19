<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<!-- jstl upload.js 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- Main content -->

<style type="text/css">
	.popup {
		position: fixed;
	}
	.back {
		left:0;
		top:0;
		background-color: gray; 
		opacity:0.5; 
		width: 100%; 
		height: 100%; 
		overflow:hidden;  
		z-index:1101;
	}
	
	.front { 
		left:0;
		top:0;
		z-index:1110; 
		opacity:1; 
		boarder:5px solid white;
		margin: auto; 
	}
	
	#popup_img{
		width:800px;
		height:500px;
	}
	
	#popup_img:hover{
		cursor:pointer;
	}
</style>
<!-- style="display:none;" -->
<div class='popup back' style="display:none;"></div>
<div id="popup_front" class='popup front' style="display:none;">
	<a id="imgDown" href="" target="_blank"><img id="popup_img"></a>
</div>

<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" action="modifyPage" method="post">
					<input type='hidden' name='bno' value="${boardVo.bno}"> 
					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">
				</form>

				<div class="box-body">
					<div class="form-group">
						<label>Title</label> 
						<input type="text" name='title' class="form-control" value="${boardVo.title}" readonly="readonly">
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVo.content}</textarea>
					</div>
					<div class="form-group">
						<label>Writer</label> 
						<input type="text" name="writer" class="form-control" value="${boardVo.writer}" readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<!-- 첨부파일 추가 -->
					<div><hr></div>
				    <ul class="mailbox-attachments clearfix uploadedList">
				    </ul>
				    <!-- 첨부파일 추가 끝-->
					<button type="button" id="modifyBtn" class="btn btn-warning">Modify</button>
					<button type="button" id="removeBtn" class="btn btn-danger">REMOVE</button>
					<button type="button" id="golistBtn" class="btn btn-primary">GO LIST</button>
					<button type="button" id="replyBoardBtn" class="btn btn-default">REPLY</button>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
	
	
	
	<div class="row">
		<div class="col-md-12">

			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW COMMENT</h3>
				</div>
				<div class="box-body">
					<label for="exampleInputEmail1">Writer</label>
					<input class="form-control" readonly type="text" placeholder="UNAME" value="${userInfo.uname}" id="newCommentWriter"> 
					<label for="exampleInputEmail1">Comment Text</label> 
					<input class="form-control" type="text" placeholder="COMMENT TEXT" id="newCommentText">
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="button" class="btn btn-primary" id="commentAddBtn">ADD COMMENT</button>
				</div>
			</div>

			<!-- The time line -->
			<ul class="timeline">
				<!-- timeline time label -->
				<li class="time-label" id="commentsDiv">
					<span class="bg-green">Comment List <small id='commentcntSmall'>[${boardVo.commentCnt}]</small></span>
				</li>
			</ul>
			<div class='text-center'>
				<ul id="pagination" class="pagination pagination-sm no-margin ">

				</ul>
			</div>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->

     
<!-- Modal -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
        <h4 class="modal-title" id="auth"></h4>
      </div>
      <div class="modal-body" data-cno>
        <p><input type="text" id="commentText" class="form-control"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="commentModBtn">Modify</button>
        <button type="button" class="btn btn-danger" id="commentDelBtn">DELETE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>      
	
	
</section>
<!-- /.content -->

<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
  </div>
</li>                
</script>  
<script id="template" type="text/x-handlebars-template">

{{#each .}}
<li class="commentLi" data-cno={{cno}} data-auth={{commentAuth}}>
<i class="fa fa-comments bg-blue"></i>
 <div class="timeline-item" >
  <span class="time">
    <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
  </span>
  <h3 class="timeline-header"><strong>{{rno}}</strong> -{{commentAuth}}</h3>
  <div class="timeline-body">{{commentText}} </div>
    <div class="timeline-footer">
		{{#isCheck uno}}
			<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
		{{else}}
			글작성자가 아니네요
		{{/isCheck}}
    </div>
  </div>			
</li>
{{/each}}
</script>

<script>
	var message = "${message}";
	if(message != null && message != ""){
		alert(message);
	}else{
		alert("dd");
	}

	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});
	
	Handlebars.registerHelper("isCheck", function(uno,options) {
		var userUno = "${userInfo.uno}";
		if(userUno == uno){
			return options.fn(this);
		}else{
			return options.inverse(this);
		}
	});

	var printData = function(commentArr, target, templateObject,view) {

		var template = Handlebars.compile(templateObject.html());

		var html = template(commentArr);
		//$(".commentLi").remove();
		//target.after(html);
		if(view == "new"){
			$(".commentLi").remove();
		}
		target.parent().append(html);
	}

	var bno = ${boardVo.bno};
	
	var commentPage = 1;

	function getPage(pageInfo,view) {
		$.getJSON(pageInfo, function(data) {
			printData(data.list, $("#commentsDiv"), $('#template'),view);
			//printPaging(data.pageMaker, $(".pagination"));

			$("#modifyModal").modal('hide');
			$("#commentcntSmall").html("[ " + data.pageMaker.totalCount +" ]");
			

		});
	}

	var printPaging = function(pageMaker, target) {

		var str = "";

		if (pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
		}

		if (pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
		}

		target.html(str);
	};

	$("#commentsDiv").on("click", function() {

		if ($(".timeline li").size() > 1) {
			return;
		}
		getPage("/comments/" + bno + "/1");

	});
	

	$(".pagination").on("click", "li a", function(event){
		
		event.preventDefault();
		
		commentPage = $(this).attr("href");
		
		getPage("/comments/"+bno+"/"+commentPage);
		
	});
	

	$("#commentAddBtn").on("click",function(){
		 var commentAuthObj = $("#newCommentWriter");
		 var commentTextObj = $("#newCommentText");
		 var commentAuth = commentAuthObj.val();
		 var commentText = commentTextObj.val();
		 var uno = "${userInfo.uno}";
		  
		  $.ajax({
				type:'post',
				url:'/comments/',
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "POST" },
				dataType:'text',
				data: JSON.stringify({bno:bno, commentAuth:commentAuth, commentText:commentText,uno:uno}),
				success:function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("등록 되었습니다.");
						getPage("/comments/"+bno+"/"+commentPage,"new");
						commentTextObj.val("");
					}
			}});
	});


	$(".timeline").on("click", ".commentLi", function(event){
		
		var comment = $(this);
		
		$("#commentText").val(comment.find('.timeline-body').text());
		$(".modal-title").html(comment.attr("data-cno"));
		$("#auth").html(comment.attr("data-auth"))
		
	});
	
	

	$("#commentModBtn").on("click",function(){
		alert("수정");	 
		  var cno = $(".modal-title").html();
		  var commentText = $("#commentText").val();
		  
		  $.ajax({
				type:'put',
				url:'/comments/'+cno,
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "PUT" },
				data:JSON.stringify({commentText:commentText}), 
				dataType:'text', 
				success:function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("수정 되었습니다.");
						getPage("/comments/"+bno+"/"+commentPage,"new");
					}
			}});
	});

	$("#commentDelBtn").on("click",function(){
		alert("삭제");
		  var cno = $(".modal-title").html();
		  var commentText = $("#commentText").val();
		  
		  $.ajax({
			type:'delete',
			url:'/comments/'+cno,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "DELETE" },
			dataType:'text', 
			success:function(result){
				console.log("result: " + result);
				if(result == 'SUCCESS'){
					commentPage =1;
					alert("삭제 되었습니다.");
					getPage("/comments/"+bno+"/"+commentPage,"new");
				}
			}
     	  });
	});
	
	$(window).scroll(function(){
		var dh = $(document).height(); // 문서의 높이 (스크롤)
		var wh = $(window).height();   // 브라우저의 높이
		var wt = $(window).scrollTop() // 현재 스크롤 위치
	   if((dh-10)  < (wt+wh)){
		   if ($(".timeline li").size() <= 1) {
				return;
			}
		   commentPage++;
		   var pageInfo = "/comments/" + bno + "/"+commentPage;
		   getPage(pageInfo,"scroll");
	   } 
	});
	
	
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/board/getAttach/"+bno,function(list){
		$(list).each(function(){
			var fileInfo = getFileInfo(this);
			var html = template(fileInfo);
			 $(".uploadedList").append(html);
		});
	});
	
	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		
		var fileLink = $(this).attr("href");
		/* upload.js */
		if(checkImageType(fileLink)){
			event.preventDefault();
			var imgTag = $("#popup_img");
			var imgDown = $("#imgDown");
			imgTag.attr("src", fileLink);
			imgDown.attr("href", fileLink);
			console.log(imgTag.attr("src"));
			console.log(imgTag.width());
			imgTag.load(function(){
				var height = $(this).height();
				var width = $(this).width();
				$(".front").css({"top":"50%","left":"50%"});
				$(".front").css("margin-left",-(width/2));
				$(".front").css("margin-top",-(height/2));
				$(".popup").show('slow');
			})
		}	
	});
	
	$(".back").on("click", function(){		
		$(".popup").hide('slow');		
	});	

	var formObj = $("form[role='form']");
	console.log(formObj);
	$("#modifyBtn").on("click", function() {
		formObj.attr("action", "/board/modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$("#removeBtn").on("click", function() {
		var commentCnt =  ${boardVo.commentCnt};
		
		if(commentCnt > 0 ){
			alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
			return;
		}	
		
		var arr = [];
		$(".uploadedList li").each(function(index){
			 arr.push($(this).attr("data-src"));
		});
		
		if(arr.length > 0){
			$.post("/deleteAllFiles",{files:arr}, function(result){
					alert(result);
			});
		}
		formObj.attr("action", "/board/removePage");
		formObj.submit();
	});

	$("#golistBtn").on("click", function() {
		formObj.attr("method", "get");
		formObj.attr("action", "/board/listReply");
		formObj.submit();
	});
	
	$("#replyBoardBtn").on("click", function() {
		formObj.attr("action", "/board/replyRegister");
		formObj.attr("method", "get");
		formObj.submit();
	});
</script>	
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
