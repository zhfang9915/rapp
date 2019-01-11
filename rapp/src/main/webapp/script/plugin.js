var pu = {
	URL : {
		
		listPluginUnit : function() {
			return rootPath + "plugin/list";
		},
		addPluginUnit : function () {
			return rootPath + "plugin/index/add";
		},
		deletePluginUnit : function() {
			return rootPath + "plugin/delete";
		},
		updatePluginUnit : function(puId) {
			return rootPath + "plugin/index/update/"+puId;
		}
	},

	init : function() {
		$('#table_pus').bootstrapTable({
			url : pu.URL.listPluginUnit(), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : pu.queryParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 3, // 最少允许的列数
			idField : "puId", // 主键列
			uniqueId : "puId", // 每一行的唯一标识，一般为主键列
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			detailView : true,
			detailFormatter : pu.detailFormatter,
			columns : 
                [
                    {
                        checkbox: true,
                        align: 'center',
                        valign: 'middle'
                    }, {
                        title: '插件编码',
                        field: 'pluginId',
                        valign: 'middle'
                    }, {
                    	title: '插件名称',
                    	field: 'pluginName',
                    	valign: 'middle'
                    }, {
                    	title: '状态',
                    	field: 'state',
                    	valign: 'middle'
                    }, {
                    	title: '插件版本',
                    	field: 'version',
                    	valign: 'middle'
                    }, {
                    	title: '交叉编译版本',
                    	field: 'crossstool',
                    	valign: 'middle'
                    }, {
                    	title: 'MD5',
                    	field: 'md5',
                    	valign: 'middle'
                    }, {
                    	title: '创建时间',
                    	field: 'createTime',
                    	valign: 'middle'
                    }
                ]
            
		});
	},
	queryParams : function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			pluginId : $("#search-pu-id").val(),
			pluginName : $("#search-pu-name").val()
		};
		return temp;
	},
	
	detailFormatter : function (index, row) {
        var html = '<form class="form-horizontal">' +
	        			'<div class="col-md-12">' +
					        '<div class="form-group">' +
					            '<label class="col-sm-2 control-label">插件存放路径：</label>' +
					            '<div class="col-sm-10">' +
					                '<p class="form-control-static">'+row.pluginPath+'</p>' +
					            '</div>' +
				            '</div>' +
			            '</div>' +
			            '<div class="col-md-12">' +
				            '<div class="form-group">' +
					            '<label class="col-sm-2 control-label">插件下载链接：</label>' +
					            '<div class="col-sm-10">' +
						            '<p class="form-control-static">'+row.downloadUrl+'</p>' +
					            '</div>' +
				            '</div>' +
			            '</div>' +
			            '<div class="col-md-12">' +
				            '<div class="form-group">' +
					            '<label class="col-sm-2 control-label">备注：</label>' +
					            '<div class="col-sm-10">' +
					            	'<p class="form-control-static">'+row.remark+'</p>' +
					            '</div>' +
				            '</div>' +
			            '</div>' +
				'</form>';
        return html;
    },
    
    /*根据条件查询数据*/
	queryPluginUnit : function() {
		$('#table_pus').bootstrapTable("refresh");
	},
	
	
	deletePluginUnit : function() {
		var arr = $('#table_pus').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: pu.URL.deletePluginUnit(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_pus').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	}

};