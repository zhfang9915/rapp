<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>区域中心</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
<link rel="stylesheet" href="${rapp.nginx }js/themes/default/style.min.css" />
<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container-fluid">
		<div class="wrapper wrapper-content">
			<div class="ibox-content minheight">
		        <div class="row">
		        	<div class="col-sm-3 b-r minheight">
						<div id="channelTree">
						</div>
					</div>
		        	<div class="col-sm-9">
						<div class="text-center">
							<div id="container" style="width: 100%;height: 500px; border: 1px solid gray;"></div>
						</div>
					</div>
		         </div>
	         </div>
	     </div>
     </div>
	
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7a6QKaIilZftIMmKGAFLG7QT1GLfIncg"></script>
	<script type="text/javascript" src="${rapp.nginx }script/channel/channel.my.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/jstree.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>