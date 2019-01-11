<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>固件管理</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>固件列表信息</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">固件编码：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-fw-id" class="form-control" placeholder="请输入固件编码"> 
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
			    </div>
			    <div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">固件名称：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-fw-name" class="form-control" placeholder="请输入固件名称"> 
				            <span class="help-block m-b-none"></span>
				
				        </div>
				    </div>
				</div>
			    <div class="col-md-2">
					<div class="col-md-2">
						<div class="form-group">
						    <button class="btn btn-primary" type="button" onclick="fw.queryFirmWare();">
						    	<span class="glyphicon glyphicon-search"></span> 搜索
						    </button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../common/toolbar.jsp" %>
		<table id="table_fws" class="grid-table"></table>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/farmware.js"></script>

	<script type="text/javascript">
		$(function() {
			fw.init();
			$("#btn-add").click(function() {
				window.open(fw.URL.addFirmWare(), '_self');
			});
			
			$("#btn-del").click(function() {
				fw.deleteFirmWare();
			});
			
			$("#btn-edit").click(function() {
				var fw = $('#table_fws').bootstrapTable('getSelections');
				window.open(rootPath + 'fw/index/update/'+fw[0].fwId, '_self');
			});
		});
	</script>
</body>
</html>