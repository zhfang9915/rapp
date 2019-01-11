var role = {
	URL : {
		
		listRole : function() {
			return rootPath + "ra/role/list";
		},
		addOrUpdateRole : function() {
			return rootPath + "ra/role/addOrUpdate";
		},
		deleteRole : function() {
			return rootPath + "ra/role/delete";
		},
		detailRole : function(roleId) {
			return rootPath + "ra/role/detail/"+roleId;
		},
		existAuth : function(roleId){
			return rootPath + "ra/role/exist/"+roleId;
		},
		optionalAuth : function(roleId){
			return rootPath + "ra/role/optional/"+roleId;
		},
		addAuthToRole : function (roleId){
			return rootPath + "ra/role/"+roleId+"/auth/add";
		},
		removeAuthFromRole : function (roleId){
			return rootPath + "ra/role/"+roleId+"/auth/remove";
		}
		
	},
	
	init : function() {
		$('#table_roles').bootstrapTable({
			url : role.URL.listRole(), // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			search : true,
			queryParams : role.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 2, // 最少允许的列数
			idField : "roleId", // 主键列
			uniqueId : "roleId", // 每一行的唯一标识，一般为主键列
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			detailView : true,
			detailFormatter : role.detailFormatter,
			columns : [ {
				checkbox : true
			}, {
				field : 'roleId',
				title : '权限ID'
			}, {
				field : 'roleName',
				title : '权限名称'
			}, {
				field : 'advertMax',
				title : '最大广告数'
			}, {
				field : 'createTime',
				title : '创建时间'
			}, {
				title : '操作',
				formatter : function(value, row, index){
					return [
			            '<a href="'+role.URL.detailRole(row.roleId)+'">',
			            '<span class="glyphicon glyphicon-wrench"></span> 权限配置',
			            '</a>'
			        ].join('');
				}
			} ]
		});
	},
	queryParams : function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			search : params.search//搜索内容
		};
		return temp;
	},
	
	detailFormatter : function (index, row) {
        var html = '<form class="form-horizontal">' +
					    '<div class="col-md-12">' +
					        '<div class="form-group">' +
					            '<label class="col-sm-1 control-label">菜单目录：</label>' +
					            '<div class="col-sm-11">' +
					                '<p class="form-control-static"><pre style="width:95%;height:auto;">'+row.menu+'</pre></p>' +
					            '</div>' +
					        '</div>' +
					    '</div>' +
					'</form>';
        return html;
    },
	
	addRole : function() {
		var data = {};
		data.roleName = $("#input-roleName").val();
		if (!data.roleName) {
			$('#err_msg').hide().html('<label class="label label-danger">权限名称不能为空！</label>').show(300);
			return;
		}
		data.advertMax = $("#input-advertMax").val();
		if (!data.advertMax) {
			$('#err_msg').hide().html('<label class="label label-danger">最大广告数不能为空！</label>').show(300);
			return;
		}
		data.menu = $("#input-menu").val();
		data.method = $("#_method").val();
		if("edit" == data.method){
			data.roleId = $("#hidden-roleId").val();
		}
		$.ajax({
			type : "POST",
			url : role.URL.addOrUpdateRole(),
			data : data,
			success : function(result) {
				if (result['success']) {
					$('#modal_view').modal("hide");
					$('#table_roles').bootstrapTable("refresh");
					common.alert(result['msg']);
				} else {
					$('#err_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
				}
			}
		});
	},
	
	deleteRole : function() {
		var arr = $('#table_roles').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: role.URL.deleteRole(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_roles').bootstrapTable("refresh");
					}
					common.alert(result['msg']);
				}
		    });
		}
		return false;
	},
	
	existAuthInit : function (roleId){
		$('#table_existAuth').bootstrapTable({
			url : role.URL.existAuth(roleId), // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			search : true,
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			idField : "authId", // 主键列
			uniqueId : "authId", // 每一行的唯一标识，一般为主键列
			height : 500,
//			queryParams : role.getSearchText,// 传递参数（*）
			columns : [ {
				checkbox : true
			}, {
				field : 'authName',
				title : '已有权限'
			}]
		});
	},
	
	optionalAuthInit : function (roleId){
		$('#table_optionalAuth').bootstrapTable({
			url : role.URL.optionalAuth(roleId), // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			search : true,
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			idField : "authId", // 主键列
			uniqueId : "authId", // 每一行的唯一标识，一般为主键列
			height : 500,
			columns : [ {
				checkbox : true
			}, {
				field : 'authName',
				title : '可选权限'
			}]
		});
	},
	
	addAuthToRole : function (roleId){
		var optionals = $('#table_optionalAuth').bootstrapTable('getSelections');
		if (optionals.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: role.URL.addAuthToRole(roleId),    //提交的页面，方法名
		        data: JSON.stringify(optionals),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_existAuth').bootstrapTable("refresh");
						$('#table_optionalAuth').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	},
	
	removeAuthFromRole : function (roleId){
		var exists = $('#table_existAuth').bootstrapTable('getSelections');
		if (exists.length>0) {
			$.ajax({
				type: "post",     //提交方式
				contentType: "application/json; charset=utf-8",   //内容类型
				dataType: "json",     //类型
				url: role.URL.removeAuthFromRole(roleId),    //提交的页面，方法名
				data: JSON.stringify(exists),    //参数
				success : function(result) {
					if (result['success']) {
						$('#table_existAuth').bootstrapTable("refresh");
						$('#table_optionalAuth').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
			});
		}
		return false;
	}
	
	

	

};