<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">
	<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>前辰无线运营平台</title>
	<%@include file="common/notop.jsp"%>
</head>
<body class="gray-bg">
<%@include file="common/header.jsp"%>

	<div class="container-fluid">
		<div class="wrapper wrapper-content">
			<div class="ibox-content minheight">
				<div class="row">
					<div class="col-sm-3 b-r">
						<div class="text-center">
							<h1 class="text-logo"
								style="display: inline; margin-top: 8px; line-height: 60px;">${map.onlineUser }</h1>
							<small class="text-logo">人</small>
						</div>
						<div class="text-center">
							<p>昨日联网人数</p>
						</div>
					</div>
					<div class="col-sm-3 b-r">
						<div class="text-center">
							<h1 class="text-logo"
								style="display: inline; margin-top: 8px; line-height: 60px;">${map.pushCountYestoday }</h1>
							<small class="text-logo">次</small>
						</div>
						<div class="text-center">
							<p>昨日广告显示量</p>
						</div>
					</div>
					<div class="col-sm-3 b-r">
						<div class="text-center">
							<div class="form-group">
								<div class="col-md-6" style="margin-top: 12px;">已激活</div>
								<div class="col-md-6">
									<h1 class="text-logo" style="display: inline;">${map.activeRouter }</h1>
									<small class="text-logo">台</small>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6" style="margin-top: 12px;">未激活</div>
								<div class="col-md-6">
									<h1 class="text-logo" style="display: inline;">${map.unActiveRouter }</h1>
									<small class="text-logo">台</small>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="text-center">
							<div class="form-group">
								<div class="col-md-6" style="margin-top: 12px;">在线</div>
								<div class="col-md-6">
									<h1 class="text-logo" style="display: inline;">${map.onlineRouter }</h1>
									<small class="text-logo">台</small>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6" style="margin-top: 12px;">离线</div>
								<div class="col-md-6">
									<h1 class="text-logo" style="display: inline;">${map.offlineRouter }</h1>
									<small class="text-logo">台</small>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<hr/>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="pull-right" href="${rapp.rootPath }channel/query/my" target="_blank">更多>></a>
								我的区域
							</div>
							<div class="panel-body" id="myChannelPannel">
								
							</div>
						</div>
					</div>

<!-- 					<div class="col-sm-4"> -->
<!-- 						<div class="panel panel-info"> -->
<!-- 							<div class="panel-heading">昨日排行榜</div> -->
<!-- 							<div class="table-responsive"> -->
<!-- 								<table class="table"> -->
<!-- 									<thead> -->
<!-- 								        <tr> -->
<!-- 								          <th>序号</th> -->
<!-- 								          <th>客户名称</th> -->
<!-- 								          <th>广告显示量</th> -->
<!-- 								        </tr> -->
<!-- 								      </thead> -->
<!-- 								      <tbody> -->
<!-- 								        <tr> -->
<!-- 								          <th scope="row">1</th> -->
<!-- 								          <td>cxw</td> -->
<!-- 								          <td>177778次</td> -->
<!-- 								        </tr> -->
<!-- 								        <tr> -->
<!-- 								          <th scope="row">2</th> -->
<!-- 								          <td>root</td> -->
<!-- 								          <td>2017次</td> -->
<!-- 								        </tr> -->
<!-- 								      </tbody> -->
<!-- 								</table> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
			</div>


		</div>
	</div>

	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/user/user.main.js"></script>
	<%@include file="common/footer.jsp"%>
</body>
</html>