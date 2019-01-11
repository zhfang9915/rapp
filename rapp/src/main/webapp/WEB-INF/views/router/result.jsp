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
	<title>设备注册信息</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>设备注册</h1>
			<p>让您的设备商业化</p>
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
					        <label class="col-sm-3 control-label">设备编码：</label>
					        <p class="form-control-static col-sm-7">${result.data.devNo }</p>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-3 control-label">MAC地址：</label>
					        <p class="form-control-static col-sm-7">${result.data.mac }</p>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-3 control-label">备注：</label>
					        <p class="form-control-static col-sm-7">${result.data.remark }</p>
					        <div class="col-sm-2"></div>
					    </div>
<!-- 					    <div class="form-group"> -->
<!-- 						    <label class="col-sm-3 control-label"></label> -->
<!-- 					        <div class="col-sm-9"> -->
<!-- 					            <a class="btn btn-primary" >立即支付</a> -->
<%-- 					            <a class="btn btn-default" href="${pageContext.request.contextPath}" target="_blank">暂不支付</a> --%>
<!-- 					        </div> -->
<!-- 					    </div> -->
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
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
<%-- 	<script type="text/javascript" src="${rapp.nginx }script/router.js"></script> --%>
	
</body>
</html>