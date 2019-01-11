var router = {
	URL : {
		
		query4Table : function (){
			return rootPath + "router/query4Table";
		},
		
		deleteRouter : function (){
			return rootPath + "router/delete";
		},
		
		updateRouterChannel : function (channelId){
			return rootPath + "router/update/" + channelId;
		},
		
		updateRouterAddress : function (){
			return rootPath + "router/update/address";
		}
	},

	init : function() {
		$('#table_routers').bootstrapTable({
			url : router.URL.query4Table(), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : router.queryParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			minimumCountColumns : 3, // 最少允许的列数
			idField : "devNo", // 主键列
			uniqueId : "devNo", // 每一行的唯一标识，一般为主键列
			detailView : true,
			detailFormatter : router.detailFormatter,
			columns : [ {
                checkbox : true,
                align : 'center',
                valign : 'middle'
			}, {
				field : 'devNo',
				align : 'center',
				title : '设备编码'
			}, {
				field : 'devName',
				title : '设备名称'
			}, {
				field : 'mac',
				align : 'center',
				title : 'MAC'
			}, {
				field : 'state',
				align : 'center',
				title : '状态',
				formatter : function(value, row, index){
					if (value == 1) {
						return '<span class="text-danger"><strong>未激活</strong></span>';
					}else if (value == 2) {
						return '<span class="text-success"><strong>正常</strong></span>';
					}else if (value == 3) {
						return '<span class="text-warning"><strong>欠费停机</strong></span>';
					}else {
						return "未定义";
					}
				}
			}, {
				field : 'installAddress',
				title : '安装地址'
			}, {
				field : 'createBy',
				title : '创建人'
			}, {
				field : 'createTime',
				align : 'center',
				title : '创建时间'
			}, {
				title : '详情',
				align : 'center',
				formatter : function(value, row, index){
					return [
			            '<a href="">',
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
			devNo : $("#search-devNo").val(),
			mac : $("#search-mac").val(),
			createBy : $("#search-createBy").val(),
			state : $("#search-state option:selected").val(),
			channelId : $("#search-channelId option:selected").val()
		};
		return temp;
	},
	
	detailFormatter : function (index, row) {
		var channelName = "暂未分配";
		if (row.channel != null){
			channelName = row.channel.channelNameCh;
		}
        var html = '<form class="form-horizontal">' +
					    '<div class="col-md-12">' +
						    '<div class="form-group">' +
						    	'<label class="col-sm-2 control-label">所属渠道：</label>' +
						    	'<p class="form-control-static col-sm-10">'+channelName+'</p>' +
						    '</div>' +
						    '<div class="form-group">' +
							    '<label class="col-sm-2 control-label">固件名称：</label>' +
							    '<p class="form-control-static col-sm-10">'+row.fw.fwName+'</p>' +
						    '</div>' +
						    '<div class="form-group">' +
							    '<label class="col-sm-2 control-label">备注：</label>' +
							    '<p class="form-control-static col-sm-10">'+row.remark+'</p>' +
						    '</div>' +
					    '</div>' +
					'</form>';
        return html;
    },
    
    /*根据条件查询数据*/
    queryRouter : function() {
		$('#table_routers').bootstrapTable("refresh");
	},
	
	/*删除设备*/
	deleteRouter : function() {
		var arr = $('#table_routers').bootstrapTable('getSelections');
		if (arr.length>0) {
			parent.layer.confirm('删除后将无法恢复，确定删除吗？', {
			    btn: ['确定删除','取消'], //按钮
			    shade: false //不显示遮罩
			}, function(){
				parent.layer.closeAll('dialog');
				$.ajax({
			        type: "post",
			        contentType: "application/json; charset=utf-8",
			        dataType: "json",
			        url: router.URL.deleteRouter(),
			        data: JSON.stringify(arr),
			        success : function(result) {
						if (result['success']) {
							$('#table_routers').bootstrapTable("refresh");
							common.alert(result['data']);
						}else {
							common.alert(result['msg']);
						}
					}
			    });
			}, function(){});
		}else {
			parent.layer.alert("请选择需要删除的设备，可多选");
		}
		return false;
	},
	
	/*更新设备*/
	updateRouterChannel : function() {
		var arr = $('#table_routers').bootstrapTable('getSelections');
		if (arr.length>0) {
			$('#err_msg').html('');
			$('#update-channelId').val("");
			$('#update-router-modal').modal({
				show:true,//显示弹出层
                backdrop:'static',//禁止位置关闭
                keyboard:false//关闭键盘事件
			});
		}else {
			parent.layer.alert("请选择需要分属的设备，可多选");
		}
		return false;
	},
	
	updateSubmit : function (){
		var arr = $('#table_routers').bootstrapTable('getSelections');
		var channelId = $("#update-channelId option:selected").val();
		if(!channelId){
			$('#err_msg').hide().html('<label class="label label-danger">请选择渠道</label>').show(300);
			return;
		}
		$.ajax({
	        type: "post",
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        url: router.URL.updateRouterChannel(channelId),
	        data: JSON.stringify(arr),
	        success : function(result) {
	        	if (result['success']) {
					$('#update-router-modal').modal("hide");
					$('#table_routers').bootstrapTable("refresh");
					common.alert(result['msg']);
				} else {
					$('#err_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
				}
			}
	    });
	},

	updateRouterAddress : function() {
		var arr = $('#table_routers').bootstrapTable('getSelections');
		if (arr.length == 1) {
			$('#err_address_msg').html('');
			$("#update-devNo").val(arr[0].devNo);
			$("#update-devName").val(arr[0].devName);
			$("#update-installAddress").val(arr[0].installAddress);
			$('#update-address-modal').modal({
				show:true,//显示弹出层
				backdrop:'static',//禁止位置关闭
				keyboard:false//关闭键盘事件
			});
		}else {
			parent.layer.alert("请选择需要修改的设备，仅单选");
		}
		return false;
	},
	
	updateRouterAddressSubmit : function(){
		var data = {};
		data.devNo = $("#update-devNo").val();
		data.devName = $("#update-devName").val();
		data.installAddress = $("#update-installAddress").val();
		if(!data.installAddress){
			$('#err_address_msg').hide().html('<label class="label label-danger">请相信填写当前设备的安装地址</label>').show(300);
			return;
		}
		$.ajax({
			type : "POST",
			url : router.URL.updateRouterAddress(),
			data : data,
			success : function(result) {
				if (result && result['success']) {
					$('#update-address-modal').modal("hide");
					parent.layer.alert(result['data']);
					$('#table_routers').bootstrapTable("refresh");
				} else {
					$('#err_address_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
				}
			}
		});
	}

};