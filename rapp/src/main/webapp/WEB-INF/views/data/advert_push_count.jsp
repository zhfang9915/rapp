<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-table.min.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
	<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-datepicker.css" rel="stylesheet">
	<link href="${rapp.nginx }css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>广告中心-数据统计</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
<%@include file="../common/header.jsp"%>
	<div class="container wrapper wrapper-content animated fadeIn minheight">
	
        <div class="row m-b-lg">
            <div class="col-sm-12">
                <div class="tabs-container">

                    <div class="tabs-left">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#pushCountBar-tab"> 按广告统计</a></li>
                            <li class=""><a data-toggle="tab" href="#advertBar-tab"> 按时间统计</a></li>
                            <li class=""><a data-toggle="tab" href="#routerBar-tab"> 按设备统计</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="pushCountBar-tab" class="tab-pane active">
                                <div class="panel-body minheight" >
                                	<%@include file="pushCountBar.jsp"%>
                                </div>
                            </div>
                            <div id="advertBar-tab" class="tab-pane">
                                <div class="panel-body minheight">
									<%@include file="advertBar.jsp"%>
                                </div>
                            </div>
                            <div id="routerBar-tab" class="tab-pane">
                                <div class="panel-body minheight">
									<%@include file="routerBar.jsp"%>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <input type="hidden" id="search-startTime">
		<input type="hidden" id="search-endTime">
		<input type="hidden" id="search-pushTime">
		<input type="hidden" id="search-advertId">
		<input type="hidden" id="search-dateType">
		<input type="hidden" id="search-advertName">
		<input type="hidden" id="search-devNo">
    </div>
	
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-table.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/cropper.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/echarts.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/macarons.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert/aplog.history.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/statistical.apvert.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function() {
			ds.initLayDate();//日期初始化
			ds.showPushCountBar();
			aplog.tableInit();//表格初始化
	
			$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				$("#advertBar-detail-div").hide();//隐藏表格
				$("#pushCountBar-detail-div").hide();//隐藏表格
				$("#routerBar-detail-div").hide();//隐藏表格
				ds.removeValue();
				
		       // 获取已激活的标签页的名称
		       var activeTab = $(e.target)[0].hash;
		       if(activeTab=="#pushCountBar-tab") {
		    	   ds.showPushCountBar();
		       }
		       if(activeTab=="#advertBar-tab") {
		    	   $("#advertBar-type").val('D');
		    	   ds.advertBar();
		       }
		       if(activeTab=="#routerBar-tab") {
		    	   $("#advertBar-type").val('D');
		    	   ds.routerBar();
		       }
		       
		       //advert
		       if(activeTab=="#advertBar-year") {
		    	   $("#advertBar-type").val('Y');
		    	   ds.advertBar();
		       }
		       if(activeTab=="#advertBar-month") {
		    	   $("#advertBar-type").val('M');
		    	   ds.advertBar();
		       }
		       if(activeTab=="#advertBar-day") {
		    	   $("#advertBar-type").val('D');
		    	   ds.advertBar();
		       }
		       if(activeTab=="#advertBar-hour") {
		    	   $("#advertBar-type").val('H');
		    	   ds.advertBar();
		       }
		       
		       //router
		       if(activeTab=="#routerBar-year") {
		    	   $("#advertBar-type").val('Y');
		    	   ds.routerBar();
		       }
		       if(activeTab=="#routerBar-month") {
		    	   $("#advertBar-type").val('M');
		    	   ds.routerBar();
		       }
		       if(activeTab=="#routerBar-day") {
		    	   $("#advertBar-type").val('D');
		    	   ds.routerBar();
		       }
		       if(activeTab=="#routerBar-hour") {
		    	   $("#advertBar-type").val('H');
		    	   ds.routerBar();
		       }
		       
		   });
			
		});
	</script>
</body>
</html>