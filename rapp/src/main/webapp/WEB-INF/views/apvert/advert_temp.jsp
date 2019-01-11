<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>广告模板管理</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx}css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>广告模板列表</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">名称：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-temp-name" class="form-control" placeholder="请输入名称"> 
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
			    </div>
			    <div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">是否活跃：</label>
				        <div class="col-sm-8">
				            <select id="search-state" class="selectpicker show-menu-arrow" title="该模板是否活跃">
				            	<option value="">不限</option>
						  		<option value="Y">活跃</option>
						  		<option value="N">禁用</option>
							</select>
				        </div>
				    </div>
			    </div>
			    <div class="col-md-2">
					<div class="col-md-2">
						<div class="form-group">
						    <button class="btn btn-primary" type="button" onclick="advertTemp.queryAdvertTemp();">
						    	<span class="glyphicon glyphicon-search"></span> 搜索
						    </button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../common/toolbar.jsp" %>
		<table id="table_temps" class="grid-table"></table>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert/advert_temp.js"></script>

	<script type="text/javascript">
		$(function() {
			advertTemp.init();
			
			$("#btn-add").click(function() {
				window.open(advertTemp.URL.addAdvertTemp(), '_self');
			});
			
			$("#btn-del").click(function() {
				advertTemp.deleteAdvertTemp();
			});
			
			$("#btn-edit").click(function() {
				var temp = $("#table_temps").bootstrapTable('getSelections');
				window.open(rootPath + 'advertTemp/index/update/' + temp[0].tempId, '_self'); 
			});
		});
	</script>
</body>
</html>