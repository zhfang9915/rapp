<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
	<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
	<link href="${rapp.nginx }css/font-awesome.min93e3.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>区域详情-${result.data.channelNameCh }</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container wrapper wrapper-content minheight">
		<div class="ibox">
			<div class="ibox-title">
				<h5>区域编号：${result.data.channelId }</h5>
			    <div class="ibox-tools">
			        <a href="${rapp.rootPath }channel/index/add" class="btn btn-primary btn-xs" target="_blank">创建新区域</a>
			    </div>
			</div>
		</div>
        <div class="row animated fadeInRight">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基本信息</h5>
                    </div>
                    <div>
                        <div class="ibox-content profile-content">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">区域名称</label>
									<div class="col-sm-9">
										<p class="form-control-static">${result.data.channelNameCh }
											<c:choose>
												<c:when test="${result.data.state == 1 }">
													<span class="label label-primary pull-right">启用</span>
												</c:when>
												<c:when test="${result.data.state == 0 }">
													<span class="label label-danger pull-right">禁用</span>
												</c:when>
											</c:choose>
										</p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">区域简称</label>
									<div class="col-sm-9">
										<p class="form-control-static">${result.data.channelNameEn }</p>
									</div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">创建时间</label>
								   <div class="col-sm-9">
								     <p class="form-control-static"><fmt:formatDate value="${result.data.createTime }" type="BOTH"/></p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">创建人</label>
								   <div class="col-sm-9">
								     <p class="form-control-static">${result.data.createBy }</p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">备注</label>
								   <div class="col-sm-9">
								     <p class="form-control-static">${result.data.remark } </p>
								   </div>
								</div>
								<div class="form-group">
								   <div class="col-sm-12 text-right">
								     <a href="${rapp.rootPath }router/binding/${result.data.channelId }" target="_blank" class="btn btn-sm btn-primary">设备管理</a>
								     <a href="${rapp.rootPath }channel/index/update/${result.data.channelId }" target="_blank" class="btn btn-sm btn-info">修改</a>
								     <button type="button" onclick="deleteChannel('${result.data.channelId }');" class="btn btn-sm btn-danger">删除</button>
								   </div>
								</div>
							</form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>最新动态</h5>
                    </div>
                    <div class="ibox-content text-center">
						<p class="form-control-static">功能内测中，敬请期待!</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
    
    
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/echarts.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/macarons.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/channel/channel_detail.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>