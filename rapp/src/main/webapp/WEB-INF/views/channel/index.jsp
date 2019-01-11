<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>区域中心</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>区域列表信息</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">区域编码：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-channel-id" class="form-control" placeholder="请输入区域编码"> 
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
				    <div class="form-group">
				        <label class="col-sm-3 control-label">区域名称：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-channel-name-ch" class="form-control" placeholder="请输入区域名称"> 
				            <span class="help-block m-b-none"></span>
				
				        </div>
				    </div>
				    <c:if test="${roleId<3 }">
					    <div class="form-group">
					        <label class="col-sm-3 control-label">创建人：</label>
					        <div class="col-sm-9">
					            <input type="text" id="search-channel-createBy" class="form-control" placeholder="请输入创建人"> 
					            <span class="help-block m-b-none"></span>
					
					        </div>
					    </div>
				    </c:if>
				</div>
				<div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">区域状态：</label>
				        <div class="col-sm-9">
				            <select class="form-control" id="search-channel-state">
				            	<option value="" selected="selected">全部</option>
				            	<option value="0">禁用</option>
				            	<option value="1">启用</option>
				            </select>
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
				    <div class="form-group">
				        <label class="col-sm-3 control-label">区域简称：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-channel-name-en" class="form-control" placeholder="请输入区域简称"> 
				            <span class="help-block m-b-none"></span>
				
				        </div>
				    </div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="col-sm-2">
						    <button class="btn btn-primary" type="button" onclick="channel.queryChannel();">
						    	<span class="glyphicon glyphicon-search"></span> 搜索
						    </button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../common/toolbar.jsp" %>
		<table id="table_channels" class="grid-table"></table>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/channel.js"></script>

	<script type="text/javascript">
		$(function() {
			channel.init();
			$("#btn-add").click(function() {
				window.open(channel.URL.addChannel(), '_self');
			});
			
			$("#btn-del").click(function() {
				channel.deleteChannel();
			});
			
			$("#btn-edit").click(function() {
				var channel = $('#table_channels').bootstrapTable('getSelections');
				window.open(rootPath + 'channel/index/update/'+channel[0].channelId, '_self');
			});
		});
	</script>
</body>
</html>