var aplog = {
		
		URL : {
			queryAdvertLog4Table : function(){
				return rootPath + "aplog/search/table";
			}
		},
		
		tableInit : function() {
			$('#table_advertlogs').bootstrapTable({
				url : aplog.URL.queryAdvertLog4Table(), // 请求后台的URL（*）
				method : 'post', // 请求方式（*）
				dataType: "json", 
				toolbar : '#toolbar', // 工具按钮用哪个容器
				striped : true, // 是否显示行间隔色
				cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, // 是否显示分页（*）
				queryParams : aplog.queryParams,// 传递参数（*）
				queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pageSize : 10, // 每页的记录行数（*）
				pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
				showColumns : true, // 是否显示所有的列
				showRefresh : true, // 是否显示刷新按钮
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				minimumCountColumns : 3, // 最少允许的列数
				idField : "id", // 主键列
				uniqueId : "id", // 每一行的唯一标识，一般为主键列
				detailView : true,
				detailFormatter : aplog.detailFormatter,
				columns : [ {
	                checkbox: true,
	                align: 'center',
	                valign: 'middle'
				}, {
					field : 'id',
					title : '推送流水'
				}, {
					field : 'pushTime',
					title : '推送时间'
				}, {
					field : 'router.devNo',
					title : '设备ID'
				}, {
					field : 'router.devName',
					title : '设备名称'
				}, {
					field : 'advert.advertId',
					title : '广告ID'
				}, {
					field : 'advert.advertName',
					title : '广告名称'
				}, {
					field : 'phoneMac',
					title : '手机MAC'
				} ]
			});
		},
		
		queryParams : function(params) {
			var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				limit : params.limit, // 页面大小
				offset : params.offset, // 页码
				id : $('#search-id').val(),
				devNo : $('#search-devNo').val(),
				advertId : $('#search-advertId').val(),
				startTime : $('#search-startTime').val(),
				endTime : $('#search-endTime').val()
			};
			return temp;
		},
		
		/*根据条件查询数据*/
		queryAdvertLog : function() {
			$('#table_advertlogs').bootstrapTable("refresh");
		},
		
		detailFormatter : function (index, row) {
	        var html = '<form class="form-horizontal">' +
						    '<div class="col-md-12">' +
							    '<div class="form-group">' +
							    	'<label class="col-sm-1 control-label">来源网址</label>' +
							    	'<p class="form-control-static col-sm-11"><a href="'+ row.url +'" target="_blank">'+row.url+'</a></p>' +
							    '</div>' +
							    '<div class="form-group">' +
							    '<label class="col-sm-1 control-label">设备地址</label>' +
							    '<p class="form-control-static col-sm-11">'+row.router.installAddress+'</p>' +
							    '</div>' +
						    '</div>' +
						'</form>';
	        return html;
	    }
	    

		
		
	    

}