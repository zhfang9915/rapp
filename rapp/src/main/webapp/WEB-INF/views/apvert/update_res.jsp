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
	<link href="${rapp.nginx }css/font-awesome.min93e3.css" rel="stylesheet">
	<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
	<link href="${rapp.nginx }css/awesome-bootstrap-checkbox.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-datepicker.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>广告中心-更新广告</title>
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
	                        <h5>更新广告</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <div class="row">
	                            <div class="col-sm-8 b-r">
	                                <!-- form左边 -->
											<div class="col-md-12">
												<form id="configAdvertForm" class="form-horizontal" action="${rapp.rootPath }apvert/update/res/${advert.advertId }" 
													method="post" autocomplete="off">
												    
												    <div id="advertRes">
												    	<c:forEach var="it" items="${advert.items }" varStatus="status">
												    		<div class="col-sm-11 res-${status.index+1 }">
													    		<div class="form-group">
															    	<label class="col-sm-2 control-label">资源${status.index+1 }：</label>
															        <div class="col-sm-10">
															            <div class="input-group">
										                                    <span class="input-group-btn">
										                                        <button type="button" class="btn btn-info" onclick="advert.openUploadModal(${status.index+1 });">
										                                            	本地上传
										                                        </button>
										                                    </span>
										                                    <input type="text" id="advertUrl${status.index+1 }" name="advertUrl" class="form-control" placeholder="必填:请输入广告图片或视频链接" 
										                                    	onclick="advert.removeErrorClass('advertUrl1');" value="${it.advertUrl }">
										                                </div>
															            <span class="help-block m-b-none"></span>
															        </div>
															    </div>
															    <div class="form-group">
															        <label class="col-sm-2 control-label">链接${status.index+1 }：</label>
															        <div class="col-sm-10">
															            <input type="text" id="toUrl${status.index+1 }" name="toUrl" class="form-control" placeholder="选填:请输入广告点击后跳转的链接" value="${it.toUrl }"> 
															            <span class="help-block m-b-none"></span>
															        </div>
															        
															    </div>
													    	</div>
													    	<div class="col-sm-1 res-${status.index+1 } text-center">
													    		<c:if test="${advert.temp.isMore eq 'Y' }">
														    		<button type="button" class="btn btn-default btn-sm" onclick="advert.removeAdvertRes('${status.index+1 }');" style="margin-top: 30px;">
														        		<span class="glyphicon glyphicon-minus"></span>
													        		</button>
												        		</c:if>
													    	</div>
												    	</c:forEach>
												    </div>
												    <c:if test="${advert.temp.isMore eq 'Y' }">
													    <div class="form-group add-advert-res">
													        <div class="col-sm-12 text-center">
													        	<button type="button" class="btn btn-default" onclick="advert.addAdvertRes();">
													        		<span class="glyphicon glyphicon-plus"></span>
												        		</button>
															</div>
													    </div>
												    </c:if>
										            <hr>
												    <div class="form-group">
												    	<label class="col-sm-2 control-label"></label>
												        <div class="col-sm-10">
												            <div class="checkbox checkbox-danger">
															    <input name="agree" id="input-agree" type="checkbox">
															    <label for="input-agree">
															      	  我已阅读并同意
															    </label>
															    	《 <a href="${rapp.nginx }protocol/adlaw.html" target="_blank">中华人民共和国广告法</a>》
															    	《<a href="#">前辰无线平台业务服务协议</a>》
															    <span class="help-block m-b-none"></span>
															</div>
														</div>
												    </div>
												    <div class="form-group">
													    <label class="col-sm-3 control-label"></label>
												        <div class="col-sm-9">
												            <button class="btn btn-primary" type="button" onclick="advert.showTemplate('${advert.advertId }');">预 览</button>
												            <button class="btn btn-primary" type="button" onclick="advert.submitConfig();">确认，更新广告</button>
												        </div>
												        
												    </div>
												</form>
											</div>
											
											
			                            	<%@include file="../common/advertFileUpload.jsp"%>
											
											<div id="div_template"></div>
								</div>
	                            <div class="col-sm-4">
	                            	<!-- form右边 -->
	                            	<%@include file="../common/advertTip.jsp"%>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/jquery.form.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function(){
			$('#uploadForm').bootstrapValidator({
		        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {/*验证*/
		        	advert_file: {
		                validators: {
		                    notEmpty: {/*非空提示*/
		                        message: ' '
		                    },
	                        file: {
	                            maxSize: 1*1024*1024,   // 1 MB
	                            message: '请上传1M以内的资源'
	                      	}
		                }
		            }
		        }
		    });
			
			
		});
	</script>
</body>
</html>