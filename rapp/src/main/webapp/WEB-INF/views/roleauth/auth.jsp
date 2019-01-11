<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>权限管理</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../common/toolbar.jsp" %>
		<table id="table_auths" class="grid-table"></table>
	</div>
	
	<div id="modal_view" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"><p id="modal-titile"></p></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<input type="hidden" id="_method">
						<div class="form-group has-feedback">
							<label for="input-accountId" class="col-sm-3 control-label">权限名称</label>
							<div class="col-sm-9">
								<input type="hidden" id="hidden-authId" /> 
								<input type="text" class="form-control" id="input-authName" placeholder="必填" /> 
							</div>
						</div>
						<div class="form-group">
							<label for="input-password" class="col-sm-3 control-label">可访问的URI</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="input-requestUrl" placeholder="必填！格式如：/aaa/bbb" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<span id="err_msg" class="glyphicon"></span>
					<button type="button" class="btn btn-primary" onclick="auth.addAuth()">保&nbsp;&nbsp;存</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/auth.js"></script>

	<script type="text/javascript">
		$(function() {
			auth.init();
			$("#btn-add").click(function() {
				//显示弹出层
				$("#modal-titile").html("新建权限");
				$("#_method").val("add");
				$('#modal_view').modal({
					show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
				});
			});
			
			$("#btn-del").click(function() {
				auth.deleteAuth();
			});
			
			$("#btn-edit").click(function() {
				var arr = $('#table_auths').bootstrapTable('getSelections');
				$("#hidden-authId").val(arr[0].authId);
				$('#input-authName').val(arr[0].authName);
				$("#input-requestUrl").val(arr[0].requestUrl);
				$("#_method").val("edit");
				//显示弹出层
				$("#modal-titile").html("提交修改");
				$('#modal_view').modal("show");
			});
		});
	</script>
</body>
</html>