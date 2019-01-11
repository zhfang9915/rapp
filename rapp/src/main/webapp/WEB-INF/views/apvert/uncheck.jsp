<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>广告审核列表</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
<link href="${rapp.nginx }css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="${rapp.nginx }css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="${rapp.nginx }css/bootstrap-table.min.css" rel="stylesheet">
<!-- <link href="http://cdn.bootcss.com/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet"> -->

<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<table id="table_unCheckAdverts" class="grid-table"></table>
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert.js"></script>

	<script type="text/javascript">
		$(function() {
			advert.checkTableInit();
			
			layer.config({extend: 'extend/layer.ext.js'});
		});
	</script>
</body>
</html>