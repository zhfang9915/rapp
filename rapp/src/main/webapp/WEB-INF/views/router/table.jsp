<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>我的设备-已有设备</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>搜索</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">设备编码：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-devNo" class="form-control" placeholder="请输入渠道编码"> 
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
				    <div class="form-group">
				        <label class="col-sm-3 control-label">MAC地址：</label>
				        <div class="col-sm-9">
				            <input type="text" id="search-mac" class="form-control" placeholder="请输入渠道名称"> 
				            <span class="help-block m-b-none"></span>
				
				        </div>
				    </div>
				    <c:if test="${roleId<3 }">
					    <div class="form-group">
					        <label class="col-sm-3 control-label">创建人：</label>
					        <div class="col-sm-9">
					            <input type="text" id="search-createBy" class="form-control" placeholder="请输入创建人"> 
					            <span class="help-block m-b-none"></span>
					
					        </div>
					    </div>
				    </c:if>
				</div>
				<div class="col-md-5">
				    <div class="form-group">
				        <label class="col-sm-3 control-label">所属渠道：</label>
				        <div class="col-sm-9">
				            <select name="search-channelId" class="selectpicker show-menu-arrow" title="选择所属渠道" data-size="6"> -->
				            	<option value="">全渠道</option>
								<c:choose>
									<c:when test="${channels.success }">
										<c:forEach var="channel" items="${channels.data}">
									  		<option value="${channel.channelId }">${channel.channelNameCh }</option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option disabled>无可选择的渠道</option>
									</c:otherwise>
								</c:choose>
							</select>
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
				    <div class="form-group">
				        <label class="col-sm-3 control-label">状态：</label>
				        <div class="col-sm-9">
				            <select class="selectpicker" id="search-state">
				            	<option value="" selected="selected">全部</option>
				            	<option value="1">未激活</option>
				            	<option value="2">正常</option>
				            	<option value="3">欠费停机</option>
				            </select>
				            <span class="help-block m-b-none"></span>
				        </div>
				    </div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="col-sm-2">
						    <button class="btn btn-primary" type="button" onclick="router.queryRouter();">
						    	<span class="glyphicon glyphicon-search"></span> 搜索
						    </button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="toolbar" class="btn-group">
			<button id="btn-update-router" type="button" class="btn btn-default btn-sm">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
			</button>
			<button id="btn-edit-router" type="button" class="btn btn-default btn-sm">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true">分属渠道</span>
			</button>
			<button id="btn-del-router" type="button" class="btn btn-default btn-sm">
				<span class="glyphicon glyphicon-remove" aria-hidden="true">删除</span>
			</button>
		</div>
		<table id="table_routers" class="grid-table"></table>
	</div>
	
	<div id="update-router-modal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">分配设备所属渠道</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">所属渠道：</label>
					        <div class="col-sm-9">
								<c:choose>
									<c:when test="${channels.success }">
							            <select id="update-channelId" class="selectpicker show-menu-arrow" title="选择所属渠道" data-size="6" >
											<c:forEach var="channel" items="${channels.data}">
										  		<option value="${channel.channelId }">${channel.channelNameCh }</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<p class="form-control-static">您还没有渠道，可在创建渠道后分配设备所属渠道</p>
									</c:otherwise>
								</c:choose>
					            <span class="help-block m-b-none text-info"><small>当前操作会将您所选的设备分属到选择的渠道下</small></span>
					        </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<span id="err_msg" class="glyphicon"></span>
					<button type="button" class="btn btn-primary" onclick="router.updateSubmit()">确定分配</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<div id="update-address-modal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">修改设备信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">设备别名：</label>
					        <div class="col-sm-9">
					        	<input type="hidden" id="update-devNo">
								<input type="text" id="update-devName" class="form-control" placeholder="选填：为您的设备取个别名(限50字)"> 
					            <span class="help-block m-b-none"></span>
					        </div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">安装地址：</label>
					        <div class="col-sm-9">
								<input type="text" id="update-installAddress" class="form-control" placeholder="必填：您设备安装所在的详细地址(限50字)"> 
					            <span class="help-block m-b-none">请填写该设备安装的详细地址信息</span>
					        </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<span id="err_address_msg" class="glyphicon"></span>
					<button type="button" class="btn btn-primary" onclick="router.updateRouterAddressSubmit()">确定修改</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/router.js"></script>

	<script type="text/javascript">
		$(function() {
			router.init();
			
			$("#btn-del-router").click(function() {
				router.deleteRouter();
			});
			
			$("#btn-edit-router").click(function() {
				router.updateRouterChannel();
			});
			
			$("#btn-update-router").click(function() {
				router.updateRouterAddress();
			});
		});
	</script>
</body>
</html>