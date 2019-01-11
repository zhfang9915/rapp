var code = {
	URL : {
		
		listJSCode : function() {
			return rootPath + "code/list";
		},
		addJSCode : function () {
			return rootPath + "code/index/add";
		},
		deleteJSCode : function() {
			return rootPath + "code/delete";
		},
		updateJSCode : function(codeId) {
			return rootPath + "code/index/update/"+codeId;
		}
	},

	init : function() {
		$('#table_codes').bootstrapTable({
			url : code.URL.listJSCode(), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : code.queryParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 3, // 最少允许的列数
			idField : "codeId", // 主键列
			uniqueId : "codeId", // 每一行的唯一标识，一般为主键列
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			detailView : true,
			detailFormatter : code.detailFormatter,
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'codeId',
				title : '源码编码'
			}, {
				field : 'codeName',
				title : '源码名称'
			}, {
				align: 'center',
				field : 'isDefault',
				title : '默认',
				formatter : function(value, row, index){
					if (value == 1) {
						return '<span class="glyphicon glyphicon-ok"></span>';
					}else {
						return "-";
					}
				}
			}, {
				field : 'serverIp',
				title : '服务器域名/IP'
			}, {
				field : 'serverPort',
				title : '服务器端口'
			}, {
				field : 'createTime',
				title : '创建时间'
			} ]
		});
	},
	queryParams : function(params) {
		var code = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			codeId : $("#search-code-id").val(),
			codeName : $("#search-code-name").val()
		};
		return code;
	},
	
	detailFormatter : function (index, row) {
        var html = '<form class="form-horizontal">' +
	        			'<div class="col-md-12">' +
					        '<div class="form-group">' +
				            '<label class="col-sm-1 control-label">备注：</label>' +
				            '<div class="col-sm-11">' +
				                '<p class="form-control-static">'+row.remark+'</p>' +
				            '</div>' +
			            '</div>' +
			            '<div class="col-md-12">' +
				            '<div class="form-group">' +
				            '<label class="col-sm-1 control-label">源码：</label>' +
				            '<div class="col-sm-11">' +
				                '<p class="form-control-static"><pre style="width:95%;height:auto;">'+row.code+'</pre></p>' +
				            '</div>' +
				        '</div>' +
				'</form>';
        return html;
    },
    
    /*根据条件查询数据*/
	queryJSCode : function() {
		$('#table_codes').bootstrapTable("refresh");
	},
	
	
	deleteJSCode : function() {
		var arr = $('#table_codes').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: code.URL.deleteJSCode(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_codes').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	}

};