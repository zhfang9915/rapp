<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${channel.channelNameCh }已有设备</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${rapp.nginx }favicon.ico" type="image/x-icon" />
<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
<link href="${rapp.nginx }css/awesome-bootstrap-checkbox.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

<%@include file="../common/notop.jsp"%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="wrapper wrapper-content animated fadeInRight minheight">
	<div class="container">
		<div class="ibox-title">
			<div class="col-md-6"><h3>${channel.channelNameCh }已绑定的设备</h3></div>
            <div class="col-md-6 ibox-tools text-right">
                <a href="${rootPath }router/update/${channel.channelId}/index" class="btn btn-primary">绑定新设备</a>
            </div>
        </div>
		<div class="ibox-content" id="no-router" style="display:none;">
             <div class="text-center p-lg">
                 <h2>${channel.channelNameCh } 渠道未绑定设备</h2>
                 <span>您可以点击</span>
                 <a class="btn btn-primary" href="${rootPath }router/update/${channel.channelId}/index">
                 	<i class="fa fa-plus"></i> <span class="bold">绑定新设备</span>
                 </a>
             </div>
         </div>
         
        <div class="ibox-content" id="search-router" style="display:none;">
			<div class="row m-b-sm m-t-sm">
	            <div class="col-md-2"></div>
	            <div class="col-md-8">
	                <div class="input-group">
	                    <input type="text" placeholder="请输入名称关键字" class="form-control" id="search-keywords"> 
	                    <input id="hidden-channelId" type="hidden" value="${channel.channelId}">
	                    <span class="input-group-btn">
	                        <button type="button" class="btn btn-primary" onClick="searchData();"> 搜索</button> 
	                    </span>
	                </div>
	            </div>
	            <div class="col-md-2"></div>
	        </div>
        </div>
        <div class="ibox-title" id="binded-edit" style="display:none;">
            <div class="col-md-6 text-left" style="left:-30px;">
                <button class="btn btn-default" id="checkAll">全选</button>
                <button class="btn btn-default" id="checkNull">全不选</button>
            </div>
            <div class="col-md-6 text-right" style="right:-30px;">
                <button class="btn btn-primary" id="unbindSubmit">解除绑定</button>
            </div>
        </div>
        <br/>
        <div class="row" id="data_body">
        </div>
        <div class="text-center">
		    <ul id='bp-page-router'></ul>
		</div>
    </div>
    </div>
	
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/router/binding.js"></script>
<%-- 	<script type="text/javascript" src="${rapp.nginx }script/channel.js"></script> --%>
	<%@include file="../common/footer.jsp"%>
</body>
</html>