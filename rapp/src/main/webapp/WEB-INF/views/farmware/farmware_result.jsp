<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>固件受理结果</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }fw/index">固件列表</a></li>
		  <li><a href="${rapp.rootPath }fw/index/add">新增固件</a></li>
		  <li class="active">固件确认</li>
		</ol>
		<c:choose>
			<c:when test="${result.success }">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h5>${result.data.fwName }信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">固件编码：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.fwId }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">固件名称：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.fwName }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">固件版本：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.version }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">openwrt版本：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.openwrt }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">交叉编译工具版本：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.crossstool }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">刷机教程：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.flashCourse }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.remark }</p>
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
	<script type="text/javascript" src="${rapp.nginx }script/farmware.js"></script>

</body>
</html>