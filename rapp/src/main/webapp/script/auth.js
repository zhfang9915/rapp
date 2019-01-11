var auth = {
	URL : {
		
		listAuth : function() {
			return rootPath + "ra/auth/list";
		},
		addOrUpdateAuth : function() {
			return rootPath + "ra/auth/addOrUpdate";
		},
		deleteAuth : function() {
			return rootPath + "ra/auth/delete";
		}
		
	},

	init : function() {
		$('#table_auths').bootstrapTable({
			url : auth.URL.listAuth(), // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			search : true,
			queryParams : auth.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 2, // 最少允许的列数
			idField : "authId", // 主键列
			uniqueId : "authId", // 每一行的唯一标识，一般为主键列
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			columns : [ {
				checkbox : true
			}, {
				field : 'authId',
				title : '权限ID'
			}, {
				field : 'authName',
				title : '权限名称'
			}, {
				field : 'requestUrl',
				title : '可访问的URI'
			}, {
				field : 'createTime',
				title : '创建时间'
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
	
	addAuth : function() {
		var data = {};
		data.authName = $("#input-authName").val();
		if (!data.authName) {
			$('#err_msg').hide().html('<label class="label label-danger">权限名称不能为空！</label>').show(300);
			return;
		}
		data.requestUrl = $("#input-requestUrl").val();
		if (!data.requestUrl) {
			$('#err_msg').hide().html('<label class="label label-danger">URI不能为空！</label>').show(300);
			return;
		}
		data.method = $("#_method").val();
		if("edit" == data.method){
			data.authId = $("#hidden-authId").val();
		}
		$.ajax({
			type : "POST",
			url : auth.URL.addOrUpdateAuth(),
			data : data,
			success : function(result) {
				if (result['success']) {
					$('#modal_view').modal("hide");
					$('#table_auths').bootstrapTable("refresh");
					parent.layer.alert(result['msg']);
				} else {
					$('#err_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
				}
			}
		});
	},
	
	deleteAuth : function() {
		var arr = $('#table_auths').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: auth.URL.deleteAuth(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_auths').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	}

	

};