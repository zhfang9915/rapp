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
	<title>用户激活</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>用户激活</h1>
			<p></p>
			<p><a class="btn btn-primary btn-lg" href="${rapp.nginx }" role="button">去官网</a>
			</p>
		</div>
	</div>
	<div class="container-fluid">
		<div class="col-md-12">
		    <form class="form-horizontal">
				<div class="col-md-12">
					<c:choose>
						<c:when test="${flag == true }">
						    <div class="form-group">
						        <label class="col-sm-3 control-label">用户名：</label>
						        <p class="form-control-static col-sm-7">${account.username }</p>
						        <div class="col-sm-2"></div>
						    </div>
						    <div class="form-group">
						        <label class="col-sm-3 control-label">注册邮箱：</label>
						        <p class="form-control-static col-sm-7">${account.email }</p>
						        <div class="col-sm-2"></div>
						    </div>
						    <div class="form-group">
						        <label class="col-sm-3 control-label"></label>
						        <p class="form-control-static col-sm-7 text-info">您的账户没有激活，请点击 
						        <button id="btn-sendAgain" class="btn btn-primary" type="button" onClick="sendEmailAgain('${account.username }','${account.email }');">发送激活邮件</button></p>
						        <div class="col-sm-2"></div>
						    </div>
				    	</c:when>
						<c:otherwise>
						    <div class="form-group">
						        <label class="col-sm-3 control-label"></label>
						        <p class="form-control-static col-sm-7 text-danger">不存在此用户注册信息 </p>
						        <div class="col-sm-2"></div>
						    </div>
						 </c:otherwise>
					</c:choose>
			    </div>
			</form>
		</div>
	</div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="${rapp.nginx }script/user/user.register.js"></script>
</body>
</html>