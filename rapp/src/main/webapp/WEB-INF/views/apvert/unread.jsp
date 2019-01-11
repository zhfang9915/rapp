<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>个人中心</title>
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
	<link href="${rapp.nginx }css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${rapp.nginx }css/style.min862f.css?v=4.1.0" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>广告审核结果</h5>
                        <div class="ibox-tools">
                            <a href="${rapp.rootPath }apvert/config" class="btn btn-primary btn-xs">创建新广告</a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover">
                                <tbody>
                                
                                	<c:choose>
										<c:when test="${result.success }">
											<c:forEach var="log" items="${result.data}">
			                                    <tr>
			                                        <td class="project-status">
<!-- 			                                            <span class="label label-primary">通过 -->
			                                        </td>
			                                        <td class="project-title">
			                                            <a href="${rapp.rootPath }apvert/detail/${log.advertId}">${log.title }</a>
			                                            <br/>
			                                            <small>${log.msg }</small>
			                                        </td>
			                                        <td class="project-completion">
			                                            <small>${log.createTime }</small>
			                                        </td>
			                                        <td class="project-actions">
			                                            <small>${log.createBy }</small>
			                                        </td>
			                                    </tr>
		                                    </c:forEach>
	                                    </c:when>
	                                    <c:otherwise>
											<tr><p class="text-danger text-center"><strong>${result.msg }</strong></p></tr>
										</c:otherwise>
									</c:choose>
			                                    
			                                    
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>

	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap.min.js"></script>
	
</body>
</html>