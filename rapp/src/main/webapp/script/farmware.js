var fw = {
	URL : {
		
		listFirmWare : function() {
			return rootPath + "fw/list";
		},
		addFirmWare : function () {
			return rootPath + "fw/index/add";
		},
		deleteFirmWare : function() {
			return rootPath + "fw/delete";
		},
		updateFirmWare : function(fwId) {
			return rootPath + "fw/index/update/"+fwId;
		}
	},

	init : function() {
		$('#table_fws').bootstrapTable({
			url : fw.URL.listFirmWare(), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : fw.queryParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 3, // 最少允许的列数
			idField : "fwId", // 主键列
			uniqueId : "fwId", // 每一行的唯一标识，一般为主键列
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			detailView : true,
			detailFormatter : fw.detailFormatter,
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'fwId',
				title : '固件编码'
			}, {
				field : 'fwName',
				title : '固件名称'
			}, {
				field : 'version',
				title : '固件版本'
			}, {
				field : 'openwrt',
				title : 'openwrt版本'
			}, {
				field : 'crossstool',
				title : '交叉编译工具版本'
			}, {
				field : 'flashCourse',
				title : '刷机教程'
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
			fwId : $("#search-fw-id").val(),
			fwName : $("#search-fw-name").val()
		};
		return temp;
	},
	
	detailFormatter : function (index, row) {
        var html = '<form class="form-horizontal">' +
	        			'<div class="col-md-12">' +
					        '<div class="form-group">' +
				            '<label class="col-sm-2 control-label">固件存放路径：</label>' +
				            '<div class="col-sm-10">' +
				                '<p class="form-control-static">'+row.fwPath+'</p>' +
				            '</div>' +
			            '</div>' +
			            '<div class="col-md-12">' +
				            '<div class="form-group">' +
				            '<label class="col-sm-2 control-label">备注：</label>' +
				            '<div class="col-sm-10">' +
				            '<p class="form-control-static">'+row.remark+'</p>' +
				            '</div>' +
			            '</div>' +
				'</form>';
        return html;
    },
    
    /*根据条件查询数据*/
	queryFirmWare : function() {
		$('#table_fws').bootstrapTable("refresh");
	},
	
	
	deleteFirmWare : function() {
		var arr = $('#table_fws').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: fw.URL.deleteFirmWare(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_fws').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	}

};