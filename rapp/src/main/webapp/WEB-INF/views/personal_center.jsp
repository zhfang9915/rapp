<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>个人中心</title>
	<link rel="shortcut icon" href="${rapp.nginx }/favicon.ico">
	<link href="${rapp.nginx }/css/bootstrap.min14ed.css" rel="stylesheet">
	<link href="${rapp.nginx }/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${rapp.nginx }/css/style.min862f.css?v=4.1.0" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
		if (window == top) {
			top.location.href = "../error/404";
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
             <div class="col-md-2">
                 <div class="ibox float-e-margins">
                     <div class="ibox-title">
                         <span class="label label-success pull-right">月</span>
                         <h5>浏览量</h5>
                     </div>
                     <div class="ibox-content">
                         <h1 class="no-margins">386,200</h1>
                         <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i>
                         </div>
                         <small>总计浏览量</small>
                     </div>
                 </div>
             </div>
             <div class="col-md-2">
                 <div class="ibox float-e-margins">
                     <div class="ibox-title">
                         <span class="label label-info pull-right">年</span>
                         <h5>订单</h5>
                     </div>
                     <div class="ibox-content">
                         <h1 class="no-margins">80,800</h1>
                         <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i>
                         </div>
                         <small>新订单</small>
                     </div>
                 </div>
             </div>

             <div class="col-md-4">
                 <div class="ibox float-e-margins">
                     <div class="ibox-title">
                         <span class="label label-primary pull-right">今天</span>
                         <h5>访问人次</h5>
                     </div>
                     <div class="ibox-content">

                         <div class="row">
                             <div class="col-md-6">
                                 <h1 class="no-margins">&yen; 406,420</h1>
                                 <div class="font-bold text-navy">44% <i class="fa fa-level-up"></i> <small>增长较快</small>
                                 </div>
                             </div>
                             <div class="col-md-6">
                                 <h1 class="no-margins">206,120</h1>
                                 <div class="font-bold text-navy">22% <i class="fa fa-level-up"></i> <small>增长较慢</small>
                                 </div>
                             </div>
                         </div>


                     </div>
                 </div>
             </div>
             <div class="col-md-2">
                 <div class="ibox float-e-margins">
                     <div class="ibox-title">
                         <span class="label label-info pull-right">年</span>
                         <h5>订单</h5>
                     </div>
                     <div class="ibox-content">
                         <h1 class="no-margins">80,800</h1>
                         <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i>
                         </div>
                         <small>新订单</small>
                     </div>
                 </div>
             </div>
             <div class="col-md-2">
                 <div class="ibox float-e-margins">
                     <div class="ibox-title">
                         <span class="label label-info pull-right">广告</span>
                         <h5>审核消息</h5>
                     </div>
                     <div class="ibox-content text-center">
                     	<a href="${unreadMsg4Advert_URL}" target="_self">
							<h1 class="no-margins">${unreadMsg4Advert }</h1>
                     	</a>
                     </div>
                 </div>
             </div>
         </div>
	</div>

	<script type="text/javascript" src="${rapp.nginx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }/js/bootstrap.min.js"></script>
	
</body>
</html>