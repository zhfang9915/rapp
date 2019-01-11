var spy = {
	URL : {
		
		listWifiSpy : function() {
			return rootPath + "spy/list";
		},
		listDataByWifiSpy : function (spyId){
			return rootPath + "spy/data/" + spyId;
		}
	},

	spyInit : function() {
		$('#table_spys').bootstrapTable({
			url : spy.URL.listWifiSpy(), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : spy.queryParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			idField : "spyId", // 主键列
			uniqueId : "spyId", // 每一行的唯一标识，一般为主键列
			onClickRow : spy.queryData,
			columns : [ {
				field : 'fcs',
				title : 'FCS检测'
			}, {
				field : 'devId',
				title : '路由器ID'
			}, {
				field : 'devMac',
				title : '路由器MAC'
			} ]
		});
	},
	dataInit : function() {
		$('#table_datas').bootstrapTable({
//			url : spy.URL.listDataByWifiSpy(1), // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			dataType: "json", 
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			queryParams : spy.queryDataParams,// 传递参数（*）
			queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			idField : "dataId", // 主键列
			uniqueId : "dataId", // 每一行的唯一标识，一般为主键列
			columns : [ {
				field : 'staMac',
				title : '手机MAC'
			}, {
				field : 'apMac',
				title : 'AP MAC'
			}, {
				field : 'ssId',
				title : 'SSID'
			}, {
				field : 'pwr',
				title : 'AP功率'
			}, {
				field : 'channel',
				title : 'AP信道'
			} ]
		});
	},
	queryParams : function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			devId : $("#search-spy-devid").val(),
			devMac : $("#search-spy-devmac").val()
		};
		return temp;
	},
	queryDataParams : function(params) {
		var temp = { 
				limit : params.limit,
				offset : params.offset
		};
		return temp;
	},
    
    /*根据条件查询数据*/
	queryWifiSpy : function() {
		$('#table_spys').bootstrapTable("refresh");
	},
	
	queryData : function (row, tr, field){
		$('#table_datas').bootstrapTable("refresh",{url: spy.URL.listDataByWifiSpy(row.spyId)});
	}
	
	

};