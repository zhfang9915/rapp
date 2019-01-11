var channel = {
	URL : {
		
		listChannel : function() {
			return rootPath + "channel/list";
		},
		addChannel : function () {
			return rootPath + "channel/index/add";
		},
		deleteChannel : function() {
			return rootPath + "channel/delete";
		},
		updateChannel : function(channelId) {
			return rootPath + "channel/index/update/"+channelId;
		},
		detailChannel : function(channelId) {
			return rootPath + "channel/detail/"+channelId;
		}
	},

	init : function() {
		$('#table_channels').bootstrapTable({
			url : channel.URL.listChannel(), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : channel.queryParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 3, // 最少允许的列数
			idField : "channelId", // 主键列
			uniqueId : "channelId", // 每一行的唯一标识，一般为主键列
			onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
			detailView : true,
			detailFormatter : channel.detailFormatter,
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'channelId',
				title : '区域ID'
			}, {
				field : 'channelNameCh',
				title : '区域名称'
			}, {
				field : 'channelNameEn',
				title : '区域简称'
			}, {
				field : 'state',
				title : '状态',
				formatter : function(value, row, index){
					if (row.state == 0) {
						return '<span class="text-danger"><strong>禁用</strong></span>';
					}else if (row.state == 1) {
						return '<span class="text-success"><strong>启用</strong></span>';
					}else {
						return "未定义";
					}
				}
			}, {
				field : 'createBy',
				title : '创建人'
			}, {
				field : 'createTime',
				title : '创建时间'
			}, {
				title : '',
				align: 'center',
				formatter : function(value, row, index){
					return [
			            '<a class="btn btn-default btn-sm" href="'+channel.URL.detailChannel(row.channelId)+'">',
			            '<span class="glyphicon glyphicon-eye-open"></span>',
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
			channelId : $("#search-channel-id").val(),
			channelNameEn : $("#search-channel-name-en").val(),
			channelNameCh : $("#search-channel-name-ch").val(),
			createBy : $("#search-channel-createBy").val(),
			state : $("#search-channel-state option:selected").val()
		};
		return temp;
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
					    '</div>' +
					'</form>';
        return html;
    },
    
    /*根据条件查询数据*/
	queryChannel : function() {
		$('#table_channels').bootstrapTable("refresh");
	},
	
	
	deleteChannel : function() {
		var arr = $('#table_channels').bootstrapTable('getSelections');
		if (arr.length>0) {
			$.ajax({
		        type: "post",     //提交方式
		        contentType: "application/json; charset=utf-8",   //内容类型
		        dataType: "json",     //类型
		        url: channel.URL.deleteChannel(),    //提交的页面，方法名
		        data: JSON.stringify(arr),    //参数
		        success : function(result) {
					if (result['success']) {
						$('#table_channels').bootstrapTable("refresh");
					}
					parent.layer.alert(result['msg']);
				}
		    });
		}
		return false;
	}

	

};