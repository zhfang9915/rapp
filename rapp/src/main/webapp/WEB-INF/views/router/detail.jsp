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
	<title>设备详情-${devNo }</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container wrapper wrapper-content minheight">
		<div class="ibox">
			<div class="ibox-title">
			<h5>设备号：${router.devNo }</h5>
				<input type="hidden" id="router-devNo" value="${router.devNo }">  
			    <div class="ibox-tools">
			        <a href="${rapp.taobao }" target="_blank" class="btn btn-primary btn-xs">购买新设备</a>
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
									<label class="col-sm-3 control-label">设备名称</label>
									<div class="col-sm-9">
										<p class="form-control-static">${router.devName }
											<c:choose>
												<c:when test="${router.state == 2 }">
													<span class="label label-primary pull-right">已启用</span>
												</c:when>
												<c:when test="${router.state == 1 }">
													<span class="label label-default pull-right">未激活</span>
												</c:when>
												<c:otherwise>
													<span class="label label-danger pull-right">欠费停机</span>
												</c:otherwise>
											</c:choose>
										</p>
									</div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">安装地址</label>
								   <div class="col-sm-9">
								     <p class="form-control-static"><i class="fa fa-map-marker"></i>${router.selectCity } ${router.installAddress }</p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">MAC</label>
								   <div class="col-sm-9">
								     <p class="form-control-static"> ${router.mac }</p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">注册时间</label>
								   <div class="col-sm-9">
								     <p class="form-control-static"><fmt:formatDate value="${router.createTime }" type="BOTH"/></p>
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
                        <h5>设备明细</h5>
                    </div>
                    <div class="ibox-content">

                        <div>
                            <div class="feed-activity-list">

                                <div class="feed-element">
                                    <div class="media-body ">
                                        <strong>固件信息</strong>.
                                        <br>
                                        <div class="actions">
                                            <form class="form-horizontal">
												<div class="form-group">
												   <label class="col-sm-3 control-label">固件名称</label>
												   <div class="col-sm-9">
												     <p class="form-control-static">${router.fw.fwName }</p>
												   </div>
												</div>
												<div class="form-group">
												   <label class="col-sm-3 control-label">固件版本</label>
												   <div class="col-sm-9">
												     <p class="form-control-static">${router.fw.version }</p>
												   </div>
												</div>
												<div class="form-group">
												   <label class="col-sm-3 control-label">openwrt</label>
												   <div class="col-sm-9">
												     <p class="form-control-static">${router.fw.openwrt }</p>
												   </div>
												</div>
												<div class="form-group">
												   <label class="col-sm-3 control-label">交叉版本</label>
												   <div class="col-sm-9">
												     <p class="form-control-static">${router.fw.crossstool }</p>
												   </div>
												</div>
											</form>
                                        </div>
                                    </div>
                                </div>

                                <div class="feed-element">
                                    <div class="media-body ">
                                        <strong>渠道信息</strong>.
                                        <br>
                                        <div class="actions">
                                            <form class="form-horizontal" id="channel-form">
                                            	<c:choose>
													<c:when test="${router.channel != null }">   
														<div class="form-group">
														   <label class="col-sm-3 control-label">渠道名称</label>
														   <div class="col-sm-7">
														     <p class="form-control-static">${router.channel.channelNameCh }</p>
														   </div>
														   <div class="col-sm-2">
														   	 <button type="button" class="btn btn-danger btn-xs pull-right" onclick="removeBinding('${router.channel.channelId }','${router.devNo }')">解除绑定</button>
														   </div>
														</div>
														<div class="form-group">
														   <label class="col-sm-3 control-label">渠道简称</label>
														   <div class="col-sm-9">
														     <p class="form-control-static">${router.channel.channelNameEn }</p>
														   </div>
														</div>
														<div class="form-group">
														   <label class="col-sm-3 control-label">状态</label>
														   <div class="col-sm-9">
														     <p class="form-control-static">
														     	<c:choose>
																	<c:when test="${router.channel.state == '0' }">   
																		禁用
																	</c:when>
																	<c:when test="${router.channel.state == '1'}">   
																		启用
																	</c:when>
																</c:choose>
														     </p>
														   </div>
														</div>
													</c:when>
													<c:otherwise>
														<div class="form-group text-center">
											                 <h2>设备未绑定渠道</h2>
											                 <span>您可以点击</span>
											                 <button class="btn btn-primary" type=button onclick="bindingChannel('${router.devNo }');">
											                 	<i class="fa fa-plus"></i> <span class="bold">绑定渠道</span>
											                 </button>
														</div>
													</c:otherwise>
												</c:choose>
												
											</form>
                                        </div>
                                    </div>
                                </div>
                                
                                
                                <div class="feed-element">
                                    <div class="media-body ">
                                        <strong>半月推送</strong>.
                                        <br>
                                        <div class="actions">
                                            <div id="routerHalfMonthBar" style="width: 100%;height:400px;"></div>
                                        </div>
                                    </div>
                                </div>

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
					<c:choose>
						<c:when test="${channels.success }">
							<button type="button" class="btn btn-primary" onclick="bindingSubmit();">确定绑定</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-primary" onclick="$('#binding-channel-modal').modal('hide');">关闭</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/echarts.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/macarons.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script src="${rapp.nginx }js/jquery.peity.min.js"></script>
	<script src="${rapp.nginx }js/peity.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/router/detail.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/statistical.router.js"></script>
	<%@include file="../common/footer.jsp"%>
	
	<script type="text/javascript">
		$(function() {
			ds.routerHalfMonthBar($("#router-devNo").val());

		});	
	</script>
</body>
</html>