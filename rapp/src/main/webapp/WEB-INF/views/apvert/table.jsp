<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>所有广告</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>广告搜索</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-3">
				    <div class="form-group">
			            <input type="text" id="search-channel-id" class="form-control" placeholder="请输入广告编码"> 
			            <span class="help-block m-b-none"></span>
				    </div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
			            <input type="text" id="search-apvert-name" class="form-control" placeholder="请输入广告名称"> 
			            <span class="help-block m-b-none"></span>
				    </div>
<!-- 				    <div class="form-group"> -->
<!-- 			            <select name="search-channel-id" class="selectpicker show-menu-arrow" title="选择所属渠道" data-size="6"> -->
<!-- 			            	<option value="">全渠道</option> -->
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${channels.success }"> --%>
<%-- 									<c:forEach var="channel" items="${channels.data}"> --%>
<%-- 								  		<option value="${channel.channelId }">${channel.channelNameCh }</option> --%>
<%-- 									</c:forEach> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 									<option disabled>无可选择的渠道</option> -->
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<!-- 						</select> -->
<!-- 			            <span class="help-block m-b-none"></span> -->
<!-- 				    </div> -->
				</div>
				<div class="col-md-2">
					<div class="form-group">
			            <input type="text" id="search-channel-createBy" class="form-control" placeholder="请输入创建人"> 
			            <span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
			            <select class="selectpicker show-menu-arrow" id="search-apvert-state" title="选择广告状态查询">
			            	<option value="">全状态</option>
			            	<option value="0">禁用</option>
			            	<option value="1">待审核</option>
			            	<option value="2">审核通过</option>
			            	<option value="3">审核未通过</option>
			            	<option value="4">启用</option>
			            </select>
			            <span class="help-block m-b-none"></span>
				    </div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
					    <button class="btn btn-primary pull-right" type="button" onclick="advert.queryAdvert();">
					    	<span class="glyphicon glyphicon-search"></span> 搜索
					    </button>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../common/toolbar.jsp" %>
		<table id="table_adverts" class="grid-table"></table>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert.js"></script>

	<script type="text/javascript">
		$(function() {
			advert.tableInit();
			
			$("#btn-add").click(function() {
				window.open(advert.URL.configAdvert(), '_self');
			});
			
			$("#btn-del").click(function() {
				advert.deleteAdvert();
			});
			
			$("#btn-edit").click(function() {
				var advert = $('#table_adverts').bootstrapTable('getSelections');
				window.open(rootPath + 'apvert/update/'+advert[0].advertId, '_self');
			});
		});
	</script>
</body>
</html>