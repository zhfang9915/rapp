<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>广告中心-我的广告</title>
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
	<link href="${rapp.nginx }css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

	<%-- <link href="${rapp.nginx }css/animate.min.css" rel="stylesheet"> --%>
	<link href="${rapp.nginx }css/style.min862f.css?v=4.1.0" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<%@include file="../common/notop.jsp"%>
	
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container minheight">
	<div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInUp">
				<c:if test="${((result.data)!= null && fn:length(result.data) < backUser.role.advertMax) || backUser.role.advertMax < 0}">
	                <div class="ibox-content m-b-sm border-bottom">
	                    <div class="text-center p-lg">
	                        <c:choose>
	                        	<c:when test="${backUser.role.advertMax < 0}">
		                        	<h2>如果您需要配置新的广告</h2>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<h2>您还可再配置 <span class="text-danger">${backUser.role.advertMax-fn:length(result.data) }</span> 条广告</h2>
	                        	</c:otherwise>
	                        </c:choose>
	                        <span>您可以点击</span>
	                        <a class="btn btn-primary btn-sm" href="${rapp.rootPath }apvert/config">
	                        	<i class="fa fa-plus"></i> <span class="bold">创建广告</span>
	                        </a>
	                    </div>
	                </div>
                </c:if>

				<c:choose>
					<c:when test="${result.success }">
						<c:forEach var="a" items="${result.data}">
			                <div class="faq-item" id="aps-${a.advertId }">
			                    <div class="row">
			                        <div class="col-md-4">
			                            <a data-toggle="collapse" href="#${a.advertId}" class="faq-question">${a.advertName }</a>
			                            <small>创建于<fmt:formatDate value="${a.createTime }" type="BOTH"/></small>
			                        </div>
			                        <div class="col-md-1">
			                            <div class="tag-list">
			                            	<span class="tag-item">
				                            	<c:choose>
													<c:when test="${a.state==0 }">禁用</c:when>
													<c:when test="${a.state==1 }">待审核</c:when>
													<c:when test="${a.state==2 }">审核通过</c:when>
													<c:when test="${a.state==3 }">审核未通过</c:when>
													<c:when test="${a.state==4 }">启用</c:when>
													<c:otherwise>未定义</c:otherwise>
												</c:choose>
			                                </span>
			                            </div>
			                        </div>
			                        <div class="col-md-1">
			                            <div class="tag-list">
			                            	<span class="tag-item">
<%-- 				                            	<c:choose> --%>
<%-- 													<c:when test="${a.advertType==1 }">图文广告</c:when> --%>
<%-- 													<c:when test="${a.advertType==2 }">视频广告</c:when> --%>
<%-- 													<c:when test="${a.advertType==3 }">插屏广告</c:when> --%>
<%-- 													<c:otherwise>未定义</c:otherwise> --%>
<%-- 												</c:choose> --%>
													${a.temp.tempName }
			                                </span>
			                            </div>
			                        </div>
			                        <div class="col-md-1 text-center">
			                            <span class="small font-bold">权重 </span>
			                            <br/>
			                            <small>${a.weight }</small>
			                        </div>
			                        <div class="col-md-2 text-center">
			                            <span class="small font-bold">下架日期 </span>
			                            <br/>
			                            <small>
				                            <c:choose>
												<c:when test="${a.offTime==null or a.offTime==''}">
													不下架
												</c:when>
												<c:otherwise>
													${a.offTime }
												</c:otherwise>
											</c:choose>
										</small>
			                        </div>
			                        <div class="col-md-3 text-right">
			                            <div class="small text-right">
											<c:choose>
												<c:when test="${a.state==0 || a.state==2 }">
													<button onclick="advert.enableOrDisable('${a.advertId}','enable');" class="btn btn-primary btn-xs" type="button">启用</button>
												</c:when>
												<c:when test="${a.state==4 }">
													<button onclick="advert.enableOrDisable('${a.advertId}','disable');" class="btn btn-danger btn-xs" type="button">禁用</button>
												</c:when>
											</c:choose>
											<a href="${rapp.rootPath }apvert/detail/${a.advertId}" class="btn btn-primary btn-xs" type="button">详情</a>
											<a href="${rapp.rootPath }apvert/update/${a.advertId}" class="btn btn-info btn-xs" type="button">编辑</a>
											<button onclick="advert.deleteAdvertById('${a.advertId}');" class="btn btn-danger btn-xs">删除</button>
										</div>
			                        </div>
			                    </div>
			                    <div class="row" id="row-${a.advertId }">
			                        <div class="col-sm-12">
			                            <div id="${a.advertId}" class="panel-collapse collapse faq-answer">
											<div class="form-group">
												<div class="carousel slide" id="carousel${a.advertId}">
													<div class="carousel-inner">
														<c:forEach var="it" items="${a.items}" varStatus="status">
															<div class="item <c:if test="${status.index==0 }">active</c:if>">
																<a href="${it.toUrl }" target="_blank">
																	<img class="img-responsive" src="${it.advertUrl }">
																</a>
															</div>
														</c:forEach>
													</div>
													<a data-slide="prev" href="#carousel${a.advertId}" class="left carousel-control">
														<span class="icon-prev"></span>
													</a>
													<a data-slide="next" href="#carousel${a.advertId}" class="right carousel-control">
														<span class="icon-next"></span>
													</a>
												</div>
											</div>
											<div class="form-group">
							        			<span class="text-muted">
										        	${a.remark }
										        </span>
											</div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
                		</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="ibox">
							<div class="ibox-content">
								<div class="small text-center">
									<h3>未查询到您账户下配置的广告信息</h3>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
                
                
            </div>
        </div>
    </div>
	</div>
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function() {
			layer.config({extend: 'extend/layer.ext.js'});
		});
	</script>
</body>
</html>