<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>WIFI侦探</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
<%@include file="../common/header.jsp"%>
	<div class="container minheight">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>WIFI侦探数据中心</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="col-md-5">
					    <div class="form-group">
					        <label class="col-sm-3 control-label">路由器MAC：</label>
					        <div class="col-sm-9">
					            <input type="text" id="search-spy-devmac" class="form-control" placeholder="请输入路由器MAC"> 
					            <span class="help-block m-b-none"></span>
					        </div>
					    </div>
					</div>
					<div class="col-md-5">
					    <div class="form-group">
					        <label class="col-sm-3 control-label">路由器设备ID：</label>
					        <div class="col-sm-9">
					            <input type="text" id="search-spy-devid" class="form-control" placeholder="请输入路由器设备ID"> 
					            <span class="help-block m-b-none"></span>
					
					        </div>
					    </div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<div class="col-sm-2">
							    <button class="btn btn-primary" type="button" onclick="spy.queryWifiSpy();">
							    	<span class="glyphicon glyphicon-search"></span> 搜索
							    </button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-5">
						<table id="table_spys" class="grid-table"></table>
					</div>				
					<div class="col-md-7">
						<table id="table_datas" class="grid-table"></table>
					</div>				
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="/rapp/script/common.js"></script>
	<script type="text/javascript" src="/rapp/script/spy.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function() {
			spy.spyInit();
			spy.dataInit();
		});
	</script>
</body>
</html>