var ds = {
	URL : {
		showPushCountBar : function (){
			return rootPath + 'ds/apc/advert/bar';
		},
		showDaysPushCountBar : function (){
			return rootPath + 'ds/apc/date/bar';
		},
		showRouterBar : function (){
			return rootPath + 'ds/apc/router/bar';
		}
		
	},
	
	options : {
		bar :function (mainTitle){
			return options = {
					title : {
						text : mainTitle
					},
					tooltip : {
				        trigger: 'axis'
				    },
					legend : {
						data : []
					},
					toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
					xAxis : {
						axisLabel: {
							rotate: 45,
							interval:0
						},
						data : []
					},
					yAxis : {},
					series : []
				};
		},
	},
	
	//按广告统计
	showPushCountBar : function(){
		var myChart = echarts.init(document.getElementById('pushCountBar'), 'macarons');
		//点击事件是这样调用的，当时因为写这个点击事件，我可折腾了好久，写出来才发现，好简单有木有
		myChart.on("click",ds.pushCountBarClick);
		var options = ds.options.bar();
		var data = {};
		data.startTime = $("#pushCountBar-startTime").val();
		data.endTime = $("#pushCountBar-endTime").val();
		$.ajax({
			type : "post",
			url : ds.URL.showPushCountBar(),
			data : data,
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result['success']) {
					var data = result['data'];
					options.xAxis.data = data.category;
					options.series = data.series;
					options.legend.data = data.legend;
				} 
				myChart.setOption(options);
			},
			error : function(errorMsg) {
				myChart.setOption(options);
			}
		});
		
	},
	
	//按广告统计点击时间
	pushCountBarClick : function(param) {
		$("#search-startTime").val($("#pushCountBar-startTime").val());
		$("#search-endTime").val($("#pushCountBar-endTime").val());
		$("#search-advertName").val(param.name);
		
		$("#pushCountBar-detail-div").show();
		aplog.queryAdvertLogByName();
	},
	
//	daysPushCountBar : function(){
//		var myChart = echarts.init(document.getElementById('daysPushCountBar'), 'macarons');
//		var options = ds.options.bar();
//		$.ajax({
//			type : "post",
//			url : ds.URL.showDaysPushCountBar(),
//			dataType : "json", //返回数据形式为json
//			success : function(result) {
//				if (result['success']) {
//					var data = result['data'];
//					options.xAxis.data = data.category;
//					options.series = data.series;
//					options.legend.data = data.legend;
//				} 
//				myChart.setOption(options);
//			},
//			error : function(errorMsg) {
//				myChart.setOption(options);
//			}
//		});
//		
//	},
	
	pushAdvertByHours : function(aid){
		var myChart = echarts.init(document.getElementById('pushAdvertByHours'), 'macarons');
		var options = ds.options.bar();
		var data = {};
		data.statisticalType = 'H';
		data.advertId = aid;
		$.ajax({
			type : "post",
			data : data,
			url : ds.URL.showDaysPushCountBar(),
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result['success']) {
					var data = result['data'];
					options.xAxis.data = data.category;
					options.series = data.series;
					options.legend.data = data.legend;
				} 
				myChart.setOption(options);
			},
			error : function(errorMsg) {
				myChart.setOption(options);
			}
		});
		
	},
	
	//广告搞统计
	advertBar : function(){
		var advertBar = echarts.init(document.getElementById('advertBar'), 'macarons');
		advertBar.on("click",ds.advertBarClick);
		var options = ds.options.bar();
		var data = {};
		data.statisticalType = $("#advertBar-type").val();
		if(data.statisticalType == 'D'){
			data.advertId = $("#advertBarDay-rno option:selected").val();
			data.startTime = $('#advertBar-startTime').val();
			data.endTime = $('#advertBar-endTime').val();
		}else if (data.statisticalType == 'H'){
			data.advertId = $("#advertBarHour-rno option:selected").val();
			data.pushTime = $('#advertBar-pushTime').val();
		}else if (data.statisticalType == 'M') {
			data.advertId = $("#advertBarMonth-rno option:selected").val();
			data.startTime = $('#advertBarMonth-startTime').val();
			data.endTime = $('#advertBarMonth-endTime').val();
		}else if (data.statisticalType == 'Y') {
			data.advertId = $("#advertBarYear-rno option:selected").val();
			data.startTime = $('#advertBarYear-startTime').val();
			data.endTime = $('#advertBarYear-endTime').val();
		}
		$.ajax({
			type : "post",
			data : data,
			url : ds.URL.showDaysPushCountBar(),
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result['success']) {
					var data = result['data'];
					options.xAxis.data = data.category;
					options.series = data.series;
					options.legend.data = data.legend;
				} 
				advertBar.setOption(options);
			},
			error : function(errorMsg) {
				advertBar.setOption(options);
			}
		});
	},
	
	//按日统计点击时间
	advertBarClick : function (param){
		var current = param.name;
		$("#search-pushTime").val(current);
		var statisticalType = $("#advertBar-type").val();
		var advertId = "";
		if(statisticalType == 'D'){
			advertId = $("#advertBarDay-rno option:selected").val();
		}else if (statisticalType == 'H'){
			advertId = $("#advertBarHour-rno option:selected").val();
		}else if (statisticalType == 'M') {
			advertId = $("#advertBarMonth-rno option:selected").val();
		}else if (statisticalType == 'Y') {
			advertId = $("#advertBarYear-rno option:selected").val();
		}
		$("#search-advertId").val(advertId);
		$("#search-dateType").val(statisticalType);
		
		$("#advertBar-detail-div").show();
		aplog.queryAdvertLog();
	},
	
	routerBar : function(){
		var myChart = echarts.init(document.getElementById('routerBar'), 'macarons');
		myChart.on("click",ds.routerBarClick);
		var options = ds.options.bar();
		var data = {};
		data.statisticalType = $("#advertBar-type").val();
		if(data.statisticalType == 'D'){
			data.devNo = $("#routerBarDay-rno option:selected").val();
			data.startTime = $('#routerBarDay-startTime').val();
			data.endTime = $('#routerBarDay-endTime').val();
		}else if (data.statisticalType == 'H'){
			data.devNo = $("#routerBarHour-rno option:selected").val();
			data.pushTime = $('#routerBarHour-pushTime').val();
		}else if (data.statisticalType == 'M') {
			data.devNo = $("#routerBarMonth-rno option:selected").val();
			data.startTime = $('#routerBarMonth-startTime').val();
			data.endTime = $('#routerBarMonth-endTime').val();
		}else if (data.statisticalType == 'Y') {
			data.devNo = $("#routerBarYear-rno option:selected").val();
			data.startTime = $('#routerBarYear-startTime').val();
			data.endTime = $('#routerBarYear-endTime').val();
		}
		$.ajax({
			type : "post",
			data : data,
			url : ds.URL.showRouterBar(),
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result['success']) {
					var data = result['data'];
					options.xAxis.data = data.category;
					options.series = data.series;
					options.legend.data = data.legend;
				} 
				myChart.setOption(options);
			},
			error : function(errorMsg) {
				myChart.setOption(options);
			}
		});
		
	},
	
	//按日统计点击时间
	routerBarClick : function (param){
		var current = param.name;
		$("#search-pushTime").val(current);
		var statisticalType = $("#advertBar-type").val();
		var devNo = "";
		if(statisticalType == 'D'){
			devNo = $("#routerBarDay-rno option:selected").val();
		}else if (statisticalType == 'H'){
			devNo = $("#routerBarHour-rno option:selected").val();
		}else if (statisticalType == 'M') {
			devNo = $("#routerBarMonth-rno option:selected").val();
		}else if (statisticalType == 'Y') {
			devNo = $("#routerBarYear-rno option:selected").val();
		}
		$("#search-devNo").val(devNo);
		$("#search-dateType").val(statisticalType);
		
		$("#routerBar-detail-div").show();
		aplog.queryAdvertLogByRouter();
	},
	
	initLayDate : function (){
		$(".date-day .date").datepicker({
			autoclose:true,
			endDate:new Date()
		});
		$(".date-day .input-daterange").datepicker({
			autoclose:true,
			endDate:new Date()
		});
		$(".date-month .input-daterange").datepicker({
			startView: 1,  
	        maxViewMode: 1,
	        minViewMode:1,
			autoclose:true,
			endDate:new Date(),
			format:"yyyy-mm"
		});
		$(".date-year .input-daterange").datepicker({
			startView: 2,  
	        maxViewMode: 2,
	        minViewMode:2,
			autoclose:true,
			endDate:new Date(),
			format:"yyyy"
		});
		
	},
	
	removeValue : function(){
		$("#search-startTime").val('');
		$("#search-endTime").val('');
		$("#search-pushTime").val('');
		$("#search-advertId").val('');
		$("#search-dateType").val('');
		$("#search-advertName").val('');
	}
	
	
	
}