<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="include/header.jsp"%>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">HOME PAGE</h3>
					</div>
					<a href="/board/listReply" class="btn btn-default">답변형 게시판</a>
					<a href="/test" class="btn btn-default">TEST</a>
					<a href="/uploadForm" class="btn btn-default">파일업로드</a>
					<a href="/uploadAjax" class="btn btn-default">ajaxupload</a>
					<a href="/user/signIn" class="btn btn-default">Sign in</a>
				</div>
			</div>
			<!--/.col (left) -->
		</div>
		<!-- /.row -->
	</section>

<!-- /.content -->
<%@include file="include/footer.jsp"%>


