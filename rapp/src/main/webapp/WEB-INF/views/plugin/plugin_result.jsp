<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>插件受理结果</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }plugin/index">插件列表</a></li>
		  <li><a href="${rapp.rootPath }plugin/index/add">新增插件</a></li>
		  <li class="active">插件确认</li>
		</ol>
		<c:choose>
			<c:when test="${result.success }">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h5>${result.data.pluginName }信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件编码：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.pluginId }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件名称：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.pluginName }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">状态：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">
											<c:choose>
												<c:when test="${result.data.state == 0}">   
													禁用
												</c:when>
												<c:when test="${result.data.state =='1'}">   
													启用
												</c:when>
											</c:choose>
							            </p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件版本：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.version }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">交叉编译工具版本：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.crossstool }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件存放路径：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.pluginPath }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件下载链接：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.downloadUrl }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件MD5：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.md5 }</p>
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
	<script type="text/javascript" src="${rapp.nginx }script/plugin.js"></script>

</body>
</html>