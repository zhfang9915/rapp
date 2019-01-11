<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>广告推送流水</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<link href="${rapp.nginx }css/style.min862f.css?v=4.1.0" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container">
		<div class="wrapper wrapper-content animated fadeInRight minheight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>推送明细 <small>已删除的广告不展示明细</small></h5>
	                    </div>
	                    <div class="ibox-content">
	                        <div class="row">
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="form-group">
								            <select id="search-devNo" class="selectpicker show-menu-arrow" title="选择要查询的设备" data-size="6">
												<option value="">全部</option>
												<c:choose>
													<c:when test="${routers.success }">
														<c:forEach var="r" items="${routers.data}">
													  		<option value="${r.devNo }">${r.devName }</option>
														</c:forEach>
													</c:when>
												</c:choose>
											</select>
								            <span class="help-block m-b-none"></span>
									    </div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
								            <select id="search-advertId" class="selectpicker show-menu-arrow" title="选择要查询的广告" data-size="6">
												<option value="">全部</option>
												<c:choose>
													<c:when test="${ads.success }">
														<c:forEach var="a" items="${ads.data}">
													  		<option value="${a.advertId }">${a.advertName }</option>
														</c:forEach>
													</c:when>
												</c:choose>
											</select>
								            <span class="help-block m-b-none"></span>
										</div>
									</div>
									<div class="col-md-2">
									    <div class="form-group">
									    	<input type="hidden" id="search-dateType" value="D">
									    	<input type="hidden" id="search-pushTime">
								            <input type="text" id="search-startTime" class="form-control layer-date" placeholder="推送起始时间"> 
								            <span class="help-block m-b-none"></span>
									    </div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
								            <input type="text" id="search-endTime" class="form-control layer-date" placeholder="推送结束时间"> 
								            <span class="help-block m-b-none"></span>
									    </div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
										    <button class="btn btn-primary pull-right" type="button" onclick="aplog.queryAdvertLog();">
										    	<span class="glyphicon glyphicon-search"></span> 搜索
										    </button>
										</div>
									</div>
								</div>
							</div>
							<table id="table_advertlogs" class="grid-table"></table>
						</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/laydate.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert/aplog.history.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function() {
			aplog.tableInit();
			aplog.queryAdvertLog();
		});
		
		
		var start = {
			elem : "#search-startTime",
			format : "YYYY-MM-DD",
			max : "2099-06-16",
			choose : function(datas) {
				end.min = datas;
				end.start = datas
			}
		};
		var end = {
			elem : "#search-endTime",
			format : "YYYY-MM-DD",
			max : "2099-06-16",
			choose : function(datas) {
				start.max = datas
			}
		};
		laydate(start);
		laydate(end);
	</script>
</body>
</html>