<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>角色详情</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }ra/role">角色列表</a></li>
		  <li class="active">角色详情</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>${role.roleName } 权限配置</h5>
			</div>
			<div class="panel-body">
				<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<table id="table_existAuth" class="grid-table"></table>
			</div>
			<div class="col-md-2 text-center">
				<button type="button" class="btn btn-default btn-lg" 
					style="margin-top: 170px;"
					onclick="role.removeAuthFromRole('${roleId}');">
					<span class="glyphicon glyphicon-forward"></span>
				</button>
				<hr/>
				<button type="button" class="btn btn-default btn-lg" 
					style="margin-top: 10px;"
					onclick="role.addAuthToRole('${roleId}');">
					<span class="glyphicon glyphicon-backward"></span>
				</button>
			</div>
			<div class="col-md-3">
				<table id="table_optionalAuth" class="grid-table"></table>
			</div>
			<div class="col-md-2"></div>
		</div>
			</div>
		</div>
		


	</div>

	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/role.js"></script>

	<script type="text/javascript">
		$(function() {
			role.existAuthInit(<%=request.getAttribute("roleId")%>);
			role.optionalAuthInit(<%=request.getAttribute("roleId")%>);
			
		});
	</script>
</body>
</html>