var account = {
	URL : {
		validateAccount : function(username) {
			return rootPath + "user/validate/"+username;
		},
		deleteAccount : function() {
			return rootPath + "user/delete";;
		},
		listAccount : function() {
			return rootPath + "user/accountList";
		},
		addOrUpdateAccount : function() {
			return rootPath + "user/addOrUpdate";
		},
		changePwd : function() {
			return rootPath + "user/changePwd";
		}
	},

	init : function() {
		$('#table_accounts').bootstrapTable({
			url : account.URL.listAccount(), // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : account.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			idField : "account", // 主键列
			uniqueId : "account", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			columns : [ {
				checkbox : true
			}, {
				field : 'username',
				title : '账户名'
			}, {
				field : 'email',
				title : '注册邮箱'
			}, {
				field : 'role.roleName',
				title : '角色'
			}, {
				field : 'state',
				align: 'center',
				title : '状态',
				formatter : function(value, row, index) {
					if (value == 1) {
						return '<span class="glyphicon glyphicon-ok"></span>';
					} else {
						return '<span class="glyphicon glyphicon-remove"></span>';
					}
				}
			}, {
				field : 'createTime',
				title : '注册时间'
			} ]
		});
	},
	queryParams : function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			username : $("#txt_search_account").val(),
			roleId : $("#txt_search_roleId").val(),
			state : $("#txt_search_state").val()
		};
		return temp;
	},
	
	addOrUpdateAccount : function() {
		var data = {};
		data.username = $("#input-username").val();
		if (!data.username) {
			$('#add_account_msg').hide().html('<label class="label label-danger">账户名不能为空！</label>').show(300);
			return;
		}
		// 1:激活（可用），2:冻结（不可用）
		data.state = $("#update-state").val();
		data.method = $("#_method").val();
		if ($("#_method").val()=="add") {
			// 表示新增用户
			data.state = 0;
			
			data.password = $("#input-password").val();
			if (!data.password) {
				$('#add_account_msg').hide().html('<label class="label label-danger">密码不能为空！</label>').show(300);
				return;
			}
			
		}
		data.roleId = $("#input-role-id option:selected").val();
		if (!data.roleId) {
			$('#add_account_msg').hide().html('<label class="label label-danger">请选择角色！</label>').show(300);
			return;
		}
		$.ajax({
			type : "POST",
			url : account.URL.addOrUpdateAccount(),
			data : data,
			success : function(result) {
				if (result && result['success']) {
					$('#modal_add_account').modal("hide");
					parent.layer.alert('操作成功');
					$('#table_accounts').bootstrapTable("refresh");
				} else {
					$('#add_account_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
				}
			}
		});
	},

	/* 校验账户 */
	validateAccount : function() {
		var inputAccount = $("#input-username");
		var username = inputAccount.val();
		if (username) {
			$.get(account.URL.validateAccount(username), {},
				function(result) {
					if (result && result['success']) {
						inputAccount.parent().parent().removeClass("has-error");
						inputAccount.parent().find("span").removeClass("glyphicon-remove");
						// 用户名可用
						inputAccount.parent().parent().addClass("has-success");
						inputAccount.parent().find("span").addClass("glyphicon-ok");
					} else {
						inputAccount.parent().parent().removeClass("has-success");
						inputAccount.parent().find("span").removeClass("glyphicon-ok");
						// 用户名不可用
						inputAccount.parent().parent().addClass("has-error");
						inputAccount.parent().find("span").addClass("glyphicon-remove");
					}
				});
		}else {
			inputAccount.parent().parent().removeClass("has-success");
			inputAccount.parent().find("span").removeClass("glyphicon-ok");
			inputAccount.parent().parent().removeClass("has-error");
			inputAccount.parent().find("span").removeClass("glyphicon-remove");
		}
	},

	/* 删除账户 */
	deleteAccount : function() {
		var arr = $('#table_accounts').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "POST",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: account.URL.deleteAccount(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result && result['success']) {
						$('#table_accounts').bootstrapTable("refresh");
					} 
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	},
	
	/*根据条件查询数据*/
	queryAccount : function() {
		$('#table_accounts').bootstrapTable("refresh");
	},
	
	//重置搜索条件
	resetQuery : function() {
		$("#txt_search_account").val("");
		$("#txt_search_state").selectpicker('val','');
		$("#txt_search_roleId").selectpicker('val','');
	},
	
	openChangpwd : function(){
		$('#modal_changepwd').modal({
			show:true,//显示弹出层
            backdrop:'static',//禁止位置关闭
            keyboard:false//关闭键盘事件
		});
	},
	
	changePwd : function (){
		var data = {};
		data.newPwd = $('#new-password').val();
		data.newPwdr = $('#new-password-repair').val();
		if(data.newPwd=='' || data.newPwd.length<8){
			$('#tip_msg').hide().html('<label class="label label-danger">新密码太过于简单</label>').show(300);
			return;
		}
		if(data.newPwd != data.newPwdr){
			$('#tip_msg').hide().html('<label class="label label-danger">两次密码输入不一致</label>').show(300);
			return;
		}
		
		$.ajax({
			type : "POST",
			url : account.URL.changePwd(),
			data : data,
			success : function(result) {
				if (result && result['success']) {
					$('#modal_changepwd').modal("hide");
					common.alert('密码修改成功，下次登录请使用新密码哦！');
				} else {
					$('#add_account_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
				}
			}
		});
		
	}
	
	
};