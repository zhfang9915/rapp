var aplog = {
		URL : {
			queryAdvertLog4Table : function(){
				return rootPath + "aplog/search/history";
			}
		},
		
		tableInit : function() {
			$('#table_advertlogs,#table_advertNamelogs,#table_advertlogsByRouter').bootstrapTable({
				url : aplog.URL.queryAdvertLog4Table(), // 请求后台的URL（*）
				method : 'post', // 请求方式（*）
				dataType: "json", 
				striped : true, // 是否显示行间隔色
				cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, // 是否显示分页（*）
				queryParams : aplog.queryParams,// 传递参数（*）
				queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pageSize : 15, // 每页的记录行数（*）
				pageList : [ 15, 50, 50, 100], // 可供选择的每页的行数（*）
				showColumns : true, // 是否显示所有的列
				showRefresh : true, // 是否显示刷新按钮
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				minimumCountColumns : 3, // 最少允许的列数
				columns : [ {
					field : 'advertName',
					title : '广告名称'
				}, {
					field : 'devName',
					title : '设备名称'
				}, {
					field : 'phoneMac',
					align: 'center',
					title : '手机MAC'
				}, {
					field : 'pushTime',
					align: 'center',
					title : '推送时间'
				}, {
					field : 'showTime',
					align: 'center',
					title : '广告显示时间'
				}, {
					field : 'closeTime',
					align: 'center',
					title : '广告关闭时间'
				}, {
					field : 'clickTime',
					align: 'center',
					title : '广告点击时间'
				}, {
					field : 'url',
					title : '推送目标网址',
					formatter : function(value, row, index){
						return '<a class="btn btn-link btn-xs" target="_blank" href="'+value+'">'+value+'</a>';
					}
				} ]
			});
		},
		
		queryParams : function(params) {
			var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				limit : params.limit, // 页面大小
				offset : params.offset, // 页码
				id : $('#search-id').val(),
				devNo : $('#search-devNo option:selected').val(),
				advertId : $('#search-advertId option:selected').val(),
				advertName : $('#search-advertName').val(),
				startTime : $('#search-startTime').val(),
				pushTime : $('#search-pushTime').val(),
				endTime : $('#search-endTime').val(),
				dateType : $("#search-dateType").val()
			};
			console.log(temp);
			return temp;
		},
		
		/*根据条件查询数据*/
		queryAdvertLog : function() {
			$('#table_advertlogs').bootstrapTable("refresh");
		},
		
		queryAdvertLogByName : function() {
			$('#table_advertNamelogs').bootstrapTable("refresh");
		},
		
		queryAdvertLogByRouter : function() {
			$('#table_advertlogsByRouter').bootstrapTable("refresh");
		}
		

}