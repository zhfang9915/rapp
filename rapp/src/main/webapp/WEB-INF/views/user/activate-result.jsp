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
	<link href="${rapp.nginx }css/font-awesome.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>用户激活</title>
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
		<c:choose>
			<c:when test="${result.success == true }">
				<form class="form-horizontal">
					<div class="col-md-12">
					    <div class="form-group">
					        <span class="text-center text-success"><h1>${result.data }激活成功</h1></span>
					    </div>
					    <div class="form-group">
					        <div class="text-center">
					            <a class="btn btn-default" href="${rapp.rootPath }login/index">去登录</a>
					        </div>
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
	
</body>
</html>