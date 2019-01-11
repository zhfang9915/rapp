<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>广告推送流水</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>广告推送流水</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="col-md-3">
					    <div class="form-group">
				            <input type="text" id="search-id" class="form-control" placeholder="请输入流水编码"> 
				            <span class="help-block m-b-none"></span>
					    </div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
				            <input type="text" id="search-devNo" class="form-control" placeholder="请输入设备编码"> 
				            <span class="help-block m-b-none"></span>
					    </div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
				            <input type="text" id="search-advertId" class="form-control" placeholder="请输入广告ID"> 
				            <span class="help-block m-b-none"></span>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
						    <button class="btn btn-primary pull-right" type="button" onclick="aplog.queryAdvertLog();">
						    	<span class="glyphicon glyphicon-search"></span> 搜索
						    </button>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3">
					    <div class="form-group">
				            <input type="text" id="search-startTime" class="form-control layer-date" placeholder="推送起始时间"> 
				            <span class="help-block m-b-none"></span>
					    </div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
				            <input type="text" id="search-endTime" class="form-control layer-date" placeholder="推送结束时间"> 
				            <span class="help-block m-b-none"></span>
					    </div>
					</div>
				</div>
			</div>
		</div>
		<table id="table_advertlogs" class="grid-table"></table>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/laydate.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/aplog.js"></script>

	<script type="text/javascript">
		$(function() {
			aplog.tableInit();
		});
		
		var start={elem:"#search-startTime",format:"YYYY-MM-DD hh:mm:ss",min:laydate.now(),max:"2099-06-16 23:59:59",istime:true,istoday:false,choose:function(datas){end.min=datas;end.start=datas}};
		var end={elem:"#search-endTime",format:"YYYY-MM-DD hh:mm:ss",min:laydate.now(),max:"2099-06-16 23:59:59",istime:true,istoday:false,choose:function(datas){start.max=datas}};laydate(start);laydate(end);
	</script>
</body>
</html>