<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${result.data.channelNameCh }详情</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${rapp.nginx }favicon.ico" type="image/x-icon" />
<link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container minheight">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }channel/list/index">区域列表</a></li>
		  <li><a href="${rapp.rootPath }channel/index/add">创建区域</a></li>
		  <li class="active">区域详情</li>
		</ol>
		<c:choose>
			<c:when test="${result.success == true }">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h5>${result.data.channelNameCh }信息</h5>
					</div>
					<div class="panel-body">
						<form class="form-horizontal">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-2 control-label">区域编码：</label>
							        <div class="col-sm-10">
							            <p class="form-control-static">${result.data.channelId }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-2 control-label">区域名称：</label>
							        <div class="col-sm-10">
							            <p class="form-control-static">${result.data.channelNameCh }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-2 control-label">区域简称：</label>
							        <div class="col-sm-10">
							            <p class="form-control-static">${result.data.channelNameEn }</p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-2 control-label">状态：</label>
							        <div class="col-sm-10">
							            <p class="form-control-static">
											<c:choose>
												<c:when test="${result.data.state == 0}">   
													禁用
												</c:when>
												<c:when test="${result.data.state =='1'}">   
													启用
												</c:when>
											</c:choose>
							            </p>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-2 control-label">备注：</label>
							        <div class="col-sm-10">
							            <p class="form-control-static">${result.data.remark }</p>
							        </div>
							    </div>
							    <c:if test="${roleId<3 }">
								    <div class="form-group">
								        <label class="col-sm-2 control-label">JS模版：</label>
								        <div class="col-sm-10">
								            <p class="form-control-static">${result.data.code.codeName }</p>
								        </div>
								    </div>
								    <div class="form-group">
								        <label class="col-sm-2 control-label">JS源码：</label>
								            <div class="col-sm-10">
								                <p class="form-control-static">
								                	<pre style="width:95%;height:auto;">${result.data.code.code }</pre>
								                </p>
								            </div>
								        
								    </div>
							    </c:if>
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
	<script type="text/javascript" src="${rapp.nginx }script/channel.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>