
var ds = {
	URL : {
		showRouterHalfMonthBar : function (devNo){
			return rootPath + 'ds/apc/router/day/bar?rid='+devNo;
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
		}
	},
	
	routerHalfMonthBar : function(devNo){
		var myChart = echarts.init(document.getElementById('routerHalfMonthBar'), 'macarons');
		var options = ds.options.bar();
		var data = {};
		data.statisticalType = "D";
		data.devNo = devNo;
		$.ajax({
			type : "post",
			data : data,
			url : ds.URL.showRouterHalfMonthBar(devNo),
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
	
	
	
	initLayDate : function (){
		var routerBarYear_startTime = {
			elem : "#routerBarYear-startTime",
			format : "YYYY",
			max : laydate.now()
		};
		laydate(routerBarYear_startTime);
		
		var routerBarYear_endTime = {
			elem : "#routerBarYear-endTime",
			format : "YYYY",
			max : laydate.now()
		};
		laydate(routerBarYear_endTime);
		
		var routerBarMonth_startTime = {
				elem : "#routerBarMonth-startTime",
				format : "YYYY-MM",
				max : laydate.now()
		};
		laydate(routerBarMonth_startTime);
		
		var routerBarMonth_endTime = {
				elem : "#routerBarMonth-endTime",
				format : "YYYY-MM",
				max : laydate.now()
		};
		laydate(routerBarMonth_endTime);
		
		var routerBar_startTime = {
			elem : "#routerBarDay-startTime",
			format : "YYYY-MM-DD",
			max : laydate.now(-1),
			choose : function(datas) {
				routerBar_endTime.min = datas;
				routerBar_endTime.start = datas
			}
		};
		laydate(routerBar_startTime);
		
		var routerBar_endTime = {
				elem : "#routerBarDay-endTime",
				format : "YYYY-MM-DD",
				max : laydate.now(-1),
				choose : function(datas) {
					routerBar_startTime.max = datas
				}
		};
		laydate(routerBar_endTime);
		
		laydate({
			elem : "#routerBarHour-pushTime",
			format : "YYYY-MM-DD",
			max : laydate.now(-1)
		});
		
	}
	
}