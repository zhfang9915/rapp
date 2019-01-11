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

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>设备中心-数据统计</title>
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
                            <li class="active"><a data-toggle="tab" href="#routerBar-tab"> 按时间统计</a></li>
                        </ul>
                        
                        <div class="tab-content">
                            <div id="routerBar-tab" class="tab-pane active">
                                <div class="panel-body minheight">
                                	<%@include file="routerBar.jsp"%>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
        
    </div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap-table.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/echarts.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/macarons.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/laydate.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/statistical.router.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert/aplog.history.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function(){
			ds.initLayDate();//日期初始化
			aplog.tableInit();//表格初始化
			ds.routerBar();//图表初始化
			
			$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				$("#routerBar-detail-div").hide();//隐藏表格
		       // 获取已激活的标签页的名称
		       var activeTab = $(e.target)[0].hash;
		       if(activeTab=="#routerBar-tab") ds.routerBar('');
		       
		       
		       if(activeTab=="#routerBar-year") {
		    	   $("#search-dateType").val('Y');
		    	   ds.routerBar();
		       }
		       if(activeTab=="#routerBar-month") {
		    	   $("#search-dateType").val('M');
		    	   ds.routerBar();
		       }
		       if(activeTab=="#routerBar-day") {
		    	   $("#search-dateType").val('D');
		    	   ds.routerBar();
		       }
		       if(activeTab=="#routerBar-hour") {
		    	   $("#search-dateType").val('H');
		    	   ds.routerBar();
		       }
		   });
			
		});
	</script>
</body>
</html>