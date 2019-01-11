<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>JS源码受理结果</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }code/index">源码列表</a></li>
		  <li><a href="${rapp.rootPath }code/index/add">新增源码</a></li>
		  <li class="active">源码确认</li>
		</ol>
		<c:choose>
			<c:when test="${result.success == true }">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h5>${result.data.codeName }信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">源码编码：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.codeId }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">源码名称：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.codeName }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">服务器域名/IP：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.serverIp }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">服务器端口：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.serverPort }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.remark }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">源码：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">
							            	<pre>${result.data.code }</pre>
							            </p>
							        </div>
							    </div>
							</div>
						</form>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h5>错误信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <h3><span class="text-center text-danger">${result.msg }</span></h3>
							</div>
						</form>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
			
		
	
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/code.js"></script>

</body>
</html>