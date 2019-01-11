<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>设备绑定信息</title>
	<%@include file="../common/head.jsp"%>
	<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
	<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox-content" id="no-router" style="display:none;">
             <c:choose>
				<c:when test="${result.success == true }">
					<div class="text-center p-lg">
		                 <h2>${channel.channelNameCh } 渠道绑定设备成功</h2>
		                 <span>您可以点击</span>
		                 <a class="btn btn-primary" href="${rootPath }router/binding/${channel.channelId}">
		                 	<i class="fa fa-plus"></i> <span class="bold">查看已有设备</span>
		                 </a>
		             </div>
				</c:when>
				<c:otherwise>
					<div class="text-center p-lg">
		                 <h2>${channel.channelNameCh } 渠道绑定设备失败</h2>
		                 <span>您可以点击</span>
		                 <a class="btn btn-primary" href="${rootPath }router/update/${channel.channelId}/add">
		                 	<i class="fa fa-plus"></i> <span class="bold">重新绑定</span>
		                 </a>
		             </div>
				</c:otherwise>
			</c:choose>
         </div>
    </div>
		
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	
</body>
</html>