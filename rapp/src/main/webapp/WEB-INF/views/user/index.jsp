<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>用户管理</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">

		<div class="panel-body" style="padding-bottom: 0px;">
			<div class="panel panel-default">
				<div class="panel-heading">查询条件</div>
				<div class="panel-body">
					<form class="form-horizontal">
						<div class="form-group" style="margin-top: 15px">
							<label class="control-label col-sm-1" for="txt_search_account">账户名</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="txt_search_account">
							</div>
							<label class="control-label col-sm-1" for="txt_search_state">状态</label>
							<div class="col-sm-2">
								<select class="selectpicker show-menu-arrow" id="txt_search_state">
									<option value="" selected>请选择状态</option>
									<option value="1">激活</option>
									<option value="2">冻结</option>
								</select>
							</div>
							<label class="control-label col-sm-1" for="txt_search_state">角色</label>
							<div class="col-sm-2">
								<select class="selectpicker show-menu-arrow" id="txt_search_roleId">
									<c:choose>
										<c:when test="${result.success }">
											<option value="">全部</option>
											<c:forEach var="role" items="${result.data}">
										  		<option value="${role.roleId }">${role.roleName }</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<option disabled>无可选择的角色</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
							<div class="col-sm-3" style="text-align: left;">
								<button type="button" style="margin-left: 50px" class="btn btn-primary" onclick="account.queryAccount();">查询</button>
								<button type="button" class="btn btn-warning" onclick="account.resetQuery();">重置</button>
							</div>
						</div>
						
					</form>
				</div>
			</div>
			
			<%@include file="../common/toolbar.jsp" %>
			<table id="table_accounts" class="grid-table"></table>
		</div>


	</div>
	
	<div id="modal_add_account" class="modal fade">
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
							<label for="input-accountId" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="input-username" placeholder="必填" /> 
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
							</div>
						</div>
						<div class="form-group" id="div-update-password">
							<label for="input-password" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="input-password" placeholder="必填" />
							</div>
						</div>
						<div class="form-group" style="display:none;" id="div-update-state">
							<label for="update-state" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10">
								<select class="form-control" id="update-state">
					                <option value="1">激活</option>
					                <option value="2">冻结</option>	
					            </select>
							</div>
						</div>
						<div class="form-group">
							<label for="input-role-id" class="col-sm-2 control-label">角色</label>
							<div class="col-sm-10">
								<select class="form-control" id="input-role-id">
					                <c:choose>
										<c:when test="${result.success }">
											<c:forEach var="role" items="${result.data}">
										  		<option value="${role.roleId }">${role.roleName }</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<option disabled>无可选择的角色</option>
										</c:otherwise>
									</c:choose>
					            </select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<span id="add_account_msg" class="glyphicon"></span>
					<button type="button" class="btn btn-primary" onclick="account.addOrUpdateAccount();">保&nbsp;&nbsp;存</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/user.js"></script>

	<script type="text/javascript">
		$(function() {
			account.init();
			// 新建按钮事件
			$("#btn-add").click(function() {
				//显示弹出层
				$("#modal-titile").html("新建账户");
				$("#_method").val("add");
				$("#div-update-password").show();
				$('#modal_add_account').modal({
					show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
				});
			});
			// 删除按钮事件
			$("#btn-del").click(function() {
				account.deleteAccount();
			});
			// 修改按钮事件
			$("#btn-edit").click(function() {
				var arr = $('#table_accounts').bootstrapTable('getSelections');
				$("#input-username").val(arr[0].username);
				$('#input-username').attr('disabled',"true");//添加disabled属性 
				$("#update-state").val(arr[0].state);
				$("#input-role-id").val(arr[0].roleId);
				$("#div-update-password").hide();
				$("#div-update-state").show();
				$("#_method").val("edit");
				//显示弹出层
				$("#modal-titile").html("更新账户");
				$('#modal_add_account').modal("show");
			});
			//关闭弹出框后执行的函数
			$('#modal_add_account').on('hidden.bs.modal', function (e) {
				$(this).find("input").val("");
				$(this).find("select").val("");
				$('#input-username').removeAttr('disabled');//移除disabled属性 
				$("#div-update-state").hide();
				
				//取消提示信息
				$("#input-username").parent().parent().removeClass("has-success");
				$("#input-username").parent().find("span").removeClass("glyphicon-ok");
				$("#input-username").parent().parent().removeClass("has-error");
				$("#input-username").parent().find("span").removeClass("glyphicon-remove");
			});
		});
	</script>
</body>
</html>