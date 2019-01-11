<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>角色管理</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../common/toolbar.jsp" %>
		<table id="table_roles" class="grid-table"></table>
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
							<label for="input-roleName" class="col-sm-3 control-label">权限名称</label>
							<div class="col-sm-9">
								<input type="hidden" id="hidden-roleId" /> 
								<input type="text" class="form-control" id="input-roleName" placeholder="必填" /> 
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="input-roleName" class="col-sm-3 control-label">最大广告数</label>
							<div class="col-sm-9">
								<input type="hidden" id="hidden-roleId" /> 
								<input type="text" class="form-control" id="input-advertMax" placeholder="必填" /> 
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="input-menu" class="col-sm-3 control-label">菜单目录</label>
							<div class="col-sm-9">
								<textarea id="input-menu" class="form-control" rows="5" placeholder="选填：请输入备注说明"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<span id="err_msg" class="glyphicon"></span>
					<button type="button" class="btn btn-primary" onclick="role.addRole()">保&nbsp;&nbsp;存</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/role.js"></script>

	<script type="text/javascript">
		$(function() {
			role.init();
			$("#btn-add").click(function() {
				//显示弹出层
				$("#modal-titile").html("新建角色");
				$("#_method").val("add");
				$('#modal_view').modal({
					show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
				});
			});
			
			$("#btn-del").click(function() {
				role.deleteRole();
			});
			
			$("#btn-edit").click(function() {
				var arr = $('#table_roles').bootstrapTable('getSelections');
				$("#hidden-roleId").val(arr[0].roleId);
				$('#input-roleName').val(arr[0].roleName);
				$('#input-advertMax').val(arr[0].advertMax);
				$('#input-menu').val(arr[0].menu);
				$("#_method").val("edit");
				//显示弹出层
				$("#modal-titile").html("提交修改");
				$('#modal_view').modal("show");
			});
		});
	</script>
</body>
</html>