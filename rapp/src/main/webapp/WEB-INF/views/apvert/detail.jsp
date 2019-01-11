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
	<title>广告详情-${advertId }</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container wrapper wrapper-content minheight">
		<div class="ibox">
			<div class="ibox-title">
				<h5>广告编号：${result.data.advertId }</h5>
				<input type="hidden" id="advert-id" value="${advertId }">
			    <div class="ibox-tools">
			        <a href="${rapp.rootPath }apvert/config" class="btn btn-primary btn-xs" target="_blank">创建新的广告</a>
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
                    	<div class="ibox-content no-padding border-left-right">
                    		<div class="carousel slide" id="carousel${result.data.advertId}">
								<div class="carousel-inner">
									<c:forEach var="it" items="${result.data.items}" varStatus="status">
										<div class="item <c:if test="${status.index==0 }">active</c:if>">
											<a href="${it.toUrl }" target="_blank">
												<img class="img-responsive" src="${it.advertUrl }">
											</a>
										</div>
									</c:forEach>
								</div>
								<a data-slide="prev" href="#carousel${result.data.advertId}" class="left carousel-control">
									<span class="icon-prev"></span>
								</a>
								<a data-slide="next" href="#carousel${result.data.advertId}" class="right carousel-control">
									<span class="icon-next"></span>
								</a>
							</div>
                        </div>
                        <div class="ibox-content profile-content">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">广告名称</label>
									<div class="col-sm-9">
										<p class="form-control-static">${result.data.advertName }
											<c:choose>
												<c:when test="${result.data.state == 4 }">
													<span class="label label-primary pull-right">已启用</span>
												</c:when>
												<c:when test="${result.data.state == 3 }">
													<span class="label label-warning pull-right">审核未通过</span>
												</c:when>
												<c:when test="${result.data.state == 2 }">
													<span class="label label-success pull-right">审核通过</span>
												</c:when>
												<c:when test="${result.data.state == 1 }">
													<span class="label label-default pull-right">待审核</span>
												</c:when>
												<c:when test="${result.data.state == 0 }">
													<span class="label label-danger pull-right">禁用</span>
												</c:when>
											</c:choose>
										</p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">广告模版</label>
									<div class="col-sm-9">
										<p class="form-control-static">
											${result.data.temp.tempName }
										</p>
									</div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">推送权重</label>
								   <div class="col-sm-9">
								     <p class="form-control-static">${result.data.weight } </p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">下架日期</label>
								   <div class="col-sm-9">
								     <p class="form-control-static">
								     	<c:choose>
											<c:when test="${result.data.offTime==null }">
												不下架
											</c:when>
											<c:otherwise>
												${result.data.offTime }
											</c:otherwise>
										</c:choose>
								     </p>
								   </div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">推送渠道</label>
									<div class="col-sm-9">
										<c:forEach var="c" items="${result.data.channels}">
											<p class="form-control-static">${c.channelNameCh }</p>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">创建时间</label>
								   <div class="col-sm-9">
								     <p class="form-control-static"><fmt:formatDate value="${result.data.createTime }" type="BOTH"/></p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">备注</label>
								   <div class="col-sm-9">
								     <p class="form-control-static">${result.data.remark }</p>
								   </div>
								</div>
								<div class="form-group">
								   <div class="col-sm-12 text-right">
								     <c:choose>
										<c:when test="${result.data.state==0 || result.data.state==2 }">
											<button onclick="enableOrDisable('${result.data.advertId}','enable');" class="btn btn-primary btn-sm" type="button">启用</button>
										</c:when>
										<c:when test="${result.data.state==4 }">
											<button onclick="enableOrDisable('${result.data.advertId}','disable');" class="btn btn-danger btn-sm" type="button">禁用</button>
										</c:when>
									</c:choose>
								     <a href="${rapp.rootPath }apvert/update/${result.data.advertId }" class="btn btn-sm btn-info">编辑</a>
								     <button type="button" onclick="deleteAdvertById('${result.data.advertId}');" class="btn btn-sm btn-danger">删除</button>
								   </div>
								</div>
							</form>
                        </div>
                    </div>
                </div>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>近半月数据</h5>
                    </div>
                    <div>
                        <div class="ibox-content profile-content">
                            <div class="row m-t-lg">
                                <c:choose>
                                	<c:when test="${mapResult.success }">
                           				<a href="${rapp.rootPath }ds/apc?aid=${result.data.advertId }" target="_blank">
	                               			<div class="col-sm-5 text-center">
			                                    <span class="bar">${mapResult.data.push }</span>
			                                    <h5>推送量</h5>
	                                		</div>
	                                    </a>
	                                    <div class="col-sm-2"></div>
	                                    <a href="${rapp.rootPath }ds/apc?aid=${result.data.advertId }" target="_blank">
	                                		<div class="col-sm-5 text-center">
			                                    <span class="line">${mapResult.data.click }</span>
			                                    <h5>点击量</h5>
	                                		</div>
		                                </a>
                                		
                                	</c:when>
	                                <c:otherwise>
	                                	<div class="col-sm-12 text-center">
		                                    <h5>暂无数据</h5>
		                                </div>
	                                </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>昨日推送量</h5>
                    </div>
                    <div class="ibox-content">
						<div id="pushAdvertByHours" style="width: 100%;height:400px;"></div>
                    </div>
                </div>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>推送明细</h5>
                    </div>
                    <div class="ibox-content">

                        <div>
                            <div class="feed-activity-list" id="pushData_tbody">

                            </div>
							<button id="show-more" class="btn btn-primary btn-block m" style="display:none;" onclick="initData();">
								<i class="fa fa-arrow-down"></i> 显示更多
							</button>
							<a id="show-history" href="${rapp.rootPath }aplog/history" target="_blank" class="btn btn-default btn-block m" style="display:none;">
								<i class="fa fa-arrow-down"></i> 显示更多历史明细
							</a>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
    
    
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script src="${rapp.nginx }js/jquery.peity.min.js"></script>
	<script src="${rapp.nginx }js/peity.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/echarts.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/macarons.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert/detail.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/statistical.apvert.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>