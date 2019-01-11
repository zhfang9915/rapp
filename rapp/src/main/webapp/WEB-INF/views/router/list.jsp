<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>我的设备-已有设备</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
<script type="text/javascript">
	var createBy="<%=request.getAttribute("createBy")%>"; 
</script>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container">
		<div class="wrapper wrapper-content animated fadeInUp minheight">
	        <div class="row">
	            <div class="col-sm-12">
	
	                <div class="ibox">
	                    <div class="ibox-heading">
	                        <div class="ibox-tools">
	                            <a href="#" class="btn btn-primary" target="_blank">购买新设备</a>
	                        </div>
	                    </div>
	                    <div class="ibox-content" id="data-content" style="display:none;">
	                        <div class="row m-b-sm m-t-sm">
	                            <div class="col-md-2"></div>
	                            <div class="col-md-8">
	                                <div class="input-group">
	                                    <input type="text" placeholder="请输入名称渠道关键字" class="form-control" id="search-keywords"> 
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
								    <ul id='bp-page-router'></ul>
								</div>
	                         </div>
	                     </div>
	                     <div class="ibox-content" id="nodata-content" style="display:none;">
	                        <div class="text-center p-lg">
		                        <h2>您还没有设备</h2>
		                        <span>您可以点击</span>
		                        <a class="btn btn-primary btn-sm" href="${rapp.taobao }" target="_blank">
		                        	<i class="fa fa-plus"></i> <span class="bold">购买设备</span>
		                        </a>
		                    </div>
	                        
	                     </div>
	                 </div>
	             </div>
	         </div>
	     </div>
     </div>
	
	<div id="binding-channel-modal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">绑定渠道</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">选择渠道：</label>
					        <div class="col-sm-9">
								<c:choose>
									<c:when test="${channels.success }">
							            <select id="binding-channelId" class="selectpicker show-menu-arrow" title="选择所属渠道" data-size="6" >
											<c:forEach var="channel" items="${channels.data}">
										  		<option value="${channel.channelId }">${channel.channelNameCh }</option>
											</c:forEach>
										</select>
										<input type="hidden" id="binding-devNo"/>
									</c:when>
									<c:otherwise>
										<p class="form-control-static">您还没有渠道，可在<a href="${rootPath }channel/index/add" class="btn btn-default btn-xs" target="_blank">创建新渠道</a>后分配设备所属渠道</p>
									</c:otherwise>
								</c:choose>
					            <span class="help-block m-b-none text-info"><small>本操作会将您当前设备绑定到选择渠道,也可在<a href="${rapp.rootPath }channel/list/index" class="btn btn-default btn-xs" target="_blank">渠道中心</a>批量绑定设备。</small></span>
					        </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<button type="button" class="btn btn-primary" onclick="bindingSubmit();">确定绑定</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-hover-dropdown.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/router/list.js"></script>
	<%@include file="../common/footer.jsp"%>
	
</body>
</html>