<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>用户注册</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>用户注册</h1>
			<p></p>
			<p><a class="btn btn-primary btn-lg" href="${rapp.nginx }" role="button">去官网</a>
			</p>
		</div>
	</div>
	<div class="container-fluid">
		<c:choose>
			<c:when test="${result.success == true }">
				<form class="form-horizontal">
					<div class="col-md-12">
					    <div class="form-group">
					        <label class="col-sm-3 control-label">用户名：</label>
					        <p class="form-control-static col-sm-7">${result.data.username }</p>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-3 control-label">注册邮箱：</label>
					        <p class="form-control-static col-sm-7">${result.data.email }</p>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-3 control-label"></label>
					        <p class="form-control-static col-sm-7 text-danger">激活链接已发送至您邮箱，请前往邮箱中激活后登录</p>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-3 control-label"></label>
					        <p class="form-control-static col-sm-7 text-info">没有收到邮件，请点击
					        <button id="btn-sendAgain" class="btn btn-primary" type="button" onClick="sendEmailAgain('${result.data.username }','${result.data.email }');">重新发送</button></p>
					        <div class="col-sm-2"></div>
					    </div>
				    </div>
				</form>
			</c:when>
			<c:otherwise>
				<form class="form-horizontal">
					<div class="col-md-12 text-center">
					    <h3><span class="text-danger"> ${result.msg }</span></h3>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="${rapp.nginx }script/user/user.register.js"></script>
	
</body>
</html>