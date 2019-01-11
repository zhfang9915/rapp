<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>区域中心</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
<script type="text/javascript">
	var createBy="<%=request.getAttribute("createBy")%>"; 
</script>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container-fluid">
		<div class="wrapper wrapper-content animated fadeInUp minheight">
	        <div class="row">
	            <div class="col-sm-12">
	
	                <div class="ibox">
	                    <div class="ibox-heading">
	                        <div class="ibox-tools">
	                            <a href="${rootPath }channel/index/add" class="btn btn-primary" target="_blank">创建新区域</a>
	                        </div>
	                    </div>
	                    <div class="ibox-content" id="data-content" style="display:none;">
	                        <div class="row m-b-sm m-t-sm">
	                            <div class="col-md-2"></div>
	                            <div class="col-md-8">
	                                <div class="input-group">
	                                    <input type="text" placeholder="请输入名称区域关键字" class="form-control" id="search-keywords"> 
	                                    <span class="input-group-btn">
	                                        <button type="button" class="btn btn-primary" onClick="searchData();"> 搜索</button> 
	                                    </span>
	                                </div>
	                            </div>
	                            <div class="col-md-2"></div>
	                        </div>
	                        <div class="project-list">
	                            <table class="table table-hover">
	                                <tbody id="channel_tbody">
	                                    
	                                 </tbody>
	                             </table>
	                             <div class="text-center">
								    <ul id='bp-page-channel'></ul>
								</div>
	                         </div>
	                     </div>
	                    <div class="ibox-content" id="nodata-content" style="display:none;">
	                        <div class="text-center p-lg">
		                        <h2>您还没有区域</h2>
		                        <span>您可以点击</span>
		                        <a class="btn btn-primary btn-sm" href="${rapp.rootPath }channel/index/add">
		                        	<i class="fa fa-plus"></i> <span class="bold">创建区域</span>
		                        </a>
		                    </div>
	                        
	                     </div>
	                 </div>
	             </div>
	         </div>
	     </div>
     </div>
	
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-hover-dropdown.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/channel/list.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>