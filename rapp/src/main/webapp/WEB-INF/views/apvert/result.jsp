<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>广告配置受理结果</title>
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
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container minheight">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }apvert/config">广告配置</a></li>
		  <li class="active">广告配置受理结果</li>
		</ol>
		<c:choose>
			<c:when test="${result.success == true }">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h5>${result.data.advertName } 配置信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">广告编码：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.advertId }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">广告名称：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.advertName }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">状态：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static text-danger">
											<c:choose>
												<c:when test="${result.data.state == 0}">   
													禁用
												</c:when>
												<c:when test="${result.data.state =='1'}">   
													待审核（审核通过后您方可启用该广告）
												</c:when>
												<c:when test="${result.data.state =='2'}">   
													审核通过
												</c:when>
												<c:when test="${result.data.state =='3'}">   
													审核未通过
												</c:when>
												<c:when test="${result.data.state =='4'}">   
													启用
												</c:when>
											</c:choose>
							            </p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">广告类型：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">
											<c:choose>
												<c:when test="${result.data.advertType =='1'}">   
													图文广告
												</c:when>
												<c:when test="${result.data.advertType =='2'}">   
													视频广告
												</c:when>
												<c:when test="${result.data.advertType =='3'}">   
													插屏广告
												</c:when>
											</c:choose>
							            </p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">广告资源：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.advertUrl }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">跳转链接：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.toUrl }</p>
							        </div>
							    </div>
<!-- 							    <div class="form-group"> -->
<!-- 							        <label class="col-sm-3 control-label">显示位置：</label> -->
<!-- 							        <div class="col-sm-9"> -->
<!-- 							            <p class="form-control-static"> -->
<%-- 											<c:choose> --%>
<%-- 												<c:when test="${result.data.location =='1'}">    --%>
<!-- 													屏幕顶部 -->
<%-- 												</c:when> --%>
<%-- 												<c:when test="${result.data.location =='2'}">    --%>
<!-- 													屏幕底部 -->
<%-- 												</c:when> --%>
<%-- 												<c:when test="${result.data.location =='3'}">    --%>
<!-- 													屏幕中间 -->
<%-- 												</c:when> --%>
<%-- 											</c:choose> --%>
<!-- 							            </p> -->
<!-- 							        </div> -->
<!-- 							    </div> -->
							    <div class="form-group">
							        <label class="col-sm-3 control-label">推送权重：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.weight}</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">所属渠道：</label>
							        <div class="col-sm-9">
							        	<c:forEach items="${result.data.channels }" var="channel">
								            <p class="form-control-static">${channel.channelNameCh }</p>
								        </c:forEach>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
							            <p class="form-control-static">${result.data.remark }</p>
							        </div>
							    </div>
							</div>
						</form>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h5>错误信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <h3><span class="text-center text-danger">${result.msg }</span></h3>
							</div>
						</form>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
			
		
	
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
	$(document).ready(function(e) {
		var counter = 0;
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function() {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, '#'); // 在IE中必须得有这两行
		window.history.forward(1);
	});
	</script>

</body>
</html>