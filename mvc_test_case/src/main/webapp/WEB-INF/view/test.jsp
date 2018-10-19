<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}

.pagination {
  width: 100%;
}

.pagination li{
  list-style: none;
  float: left; 
  padding: 3px; 
  border: 1px solid blue;
  margin:3px;  
}

.pagination li a{
  margin: 3px;
  text-decoration: none;  
}

</style>
</head>

<body>


	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='commentText'>
		</div>
		<div>
			<button type="button" id="commentModBtn">Modify</button>
			<button type="button" id="commentDelBtn">DELETE</button>
			<button type="button" id='closeBtn'>Close</button>
		</div>
	</div>

	<h2>Ajax Test Page</h2>

	<div>
		<div>
			Comment Auth <input type='text' name='commentAuth' id='newCommentAuth'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='commentText' id='newCommentText'>
		</div>
		<button id="commentAddBtn">ADD Comment</button>
	</div>



	<ul id="comments">
	</ul>
	
	<ul class='pagination'>
	</ul>	


	<!-- jQuery 2.1.4 -->
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>

	<script>
	
		var bno = 2;
		
		//getPageList(1);
		getAllList();

		function getAllList() {
			$.getJSON("/comments/all/" + bno,function(data) {
				var str = "";

				$(data).each(function() {
					str += "<li data-cno='"+this.cno+"' class='commentLi'>"
							+ this.cno
							+ ":"
							+ this.commentText
							+ "<button>MOD</button></li>";
				});

				$("#comments").html(str);
			});
		}

		$("#commentAddBtn").on("click", function() {

			var commentAuth = $("#newCommentAuth").val();
			var commentText = $("#newCommentText").val();

			$.ajax({
				type : 'post',
				url : '/comments',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					commentAuth : commentAuth,
					commentText : commentText
				}),
				success : function(result) {
					if (result == 'SUCCESS') {
						alert("등록 되었습니다.");
						getAllList();

					}
				}
			});
		});

		$("#comments").on("click", ".commentLi button", function() {
			alert("click");
			var comment = $(this).parent();
			var cno = comment.attr("data-cno");
			var commentText = comment.text(); // comment.text();
			alert(comment);
			alert("cno : "+cno);
			alert("commentText : "+commentText);
			$(".modal-title").html(cno);
			$("#commentText").val(commentText);
			$("#modDiv").show("slow");

		});

		$("#commentDelBtn").on("click", function() {

			var cno = $(".modal-title").html();
			var commentTExt = $("#commentText").val();

			$.ajax({
				type : 'delete',
				url : '/comments/' + rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text',
				success : function(result) {
					console.log("result: " + result);
					if (result == 'SUCCESS') {
						alert("삭제 되었습니다.");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
			});
		});
		
		$("#commentModBtn").on("click",function(){
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
							 $("#modDiv").hide("slow");
							getAllList();
							 //getPageList(replyPage);
						}
				}});
		});		
		
		function getPageList(page){
			
		  $.getJSON("/comments/"+bno+"/"+page , function(data){
			  
			  console.log(data.list.length);
			  
			  var str ="";
			  
			  $(data.list).each(function(){
				  str+= "<li data-cno='"+this.cno+"' class='commentLi'>" 
				  +this.cno+":"+ this.commentText+
				  "<button>MOD</button></li>";
			  });
			  
			  $("#comments").html(str);
			  
			  /* printPaging(data.pageMaker); */
			  
		  });
	  }		
		
		  
		 function printPaging(pageMaker){
			var str = "";
			if(pageMaker.prev){
				str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
			}
			for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){				
					var strClass= pageMaker.cri.page == i?'class=active':'';
				  str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
			}
			
			if(pageMaker.next){
				str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
			}
			$('.pagination').html(str);				
		}
		
		var commentPage = 1;
		
		$(".pagination").on("click", "li a", function(event){
			
			event.preventDefault();
			
			commentPage = $(this).attr("href");
			
			getPageList(commentPage);
			
		}); 
	</script>
</body>
</html>

