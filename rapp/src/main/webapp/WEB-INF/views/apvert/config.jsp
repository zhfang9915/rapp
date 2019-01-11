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
	<link href="${rapp.nginx }css/ion.rangeSlider.css" rel="stylesheet">
	<link href="${rapp.nginx }css/ion.rangeSlider.skinFlat.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-datepicker.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>广告中心-创建广告</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container minheight">
		<div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>创建新广告 </h5>
	                    </div>
	                    <div class="ibox-content">
	                        <div class="row">
	                            <div class="col-sm-8 b-r">
	                                <!-- form左边 -->
									<c:choose>
										<c:when test="${result.success == true }">
											<div class="col-md-12">
												<form id="configAdvertForm" class="form-horizontal" action="${rapp.rootPath }apvert/config/submit" 
													method="post" autocomplete="off">
												    <div class="form-group">
												        <label class="col-sm-2 control-label">广告名称：</label>
												        <div class="col-sm-10">
												            <input type="hidden" name="advertId" value="${uuid }"> 
												            <input type="text" name="advertName" class="form-control" placeholder="必填：请输入广告名称"> 
												            <span class="help-block m-b-none"></span>
												        </div>
												        
												    </div>
												    <div class="form-group">
												        <label class="col-sm-2 control-label">推送区域：</label>
												        <div class="col-sm-10">
															<c:choose>
																<c:when test="${channels.success}">
												            		<select name="channelsId" multiple class="selectpicker show-menu-arrow" title="必选：选择所属渠道(可多选)" data-size="6" data-width="100%">
																		<c:forEach var="channel" items="${channels.data}">
																	  		<option value="${channel.channelId }">${channel.channelNameCh }</option>
																		</c:forEach>
																	</select>
																</c:when>
																<c:otherwise>
																	无可选择的区域,您可以点击<a href="${rootPath }channel/index/add" class="btn btn-link">创建渠道</a>
																</c:otherwise>
															</c:choose>
												            <span class="help-block m-b-none"></span>
												        </div>
												    </div>
												    <div class="form-group">
												        <label class="col-sm-2 control-label">下架日期：</label>
												        <div class="col-sm-10">
												        	<div class="input-daterange input-group">
												                <input type="text" class="form-control" id="offTime" name="offTime" placeholder="选填：设置广告下架日期"/>
												            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
												            </div>
												            <span class="help-block m-b-none"></span>
												        </div>
												        
												    </div>
												    <div class="form-group">
												        <label class="col-sm-2 control-label">广告模版：</label>
												        <div class="col-sm-10">
												            <select id="tempId" name="tempId" class="selectpicker show-menu-arrow" title="必选：选择广告模版">
														  		<c:forEach var="temp" items="${temps}">
															  		<option value="${temp.tempId }">${temp.tempName }</option>
																</c:forEach>
															</select>
												            <span class="help-block m-b-none"></span>
												        </div>
												        
												    </div>
												    <div class="form-group">
												        <label class="col-sm-2 control-label">推送权重：</label>
												        <div class="col-sm-10">
												            <div id="weight-rangeSlider"></div>
												            <input type="hidden"  name="weight" id="push-weight" value="1">
												            <span class="help-block m-b-none"><small>权重值越大则被推送的概率就越大，默认为1</small></span>
												        </div>
												        
												    </div>
												    <div class="form-group">
												        <label class="col-sm-2 control-label">备注：</label>
												        <div class="col-sm-10">
															<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明"></textarea>
															<span class="help-block m-b-none"></span>
												        </div>
												        
												    </div>
												    <div class="form-group">
													    <label class="col-sm-3 control-label"></label>
												        <div class="col-sm-9">
												            <button class="btn btn-primary" type="submit">下一步，配置广告资源</button>
												        </div>
												        
												    </div>
												</form>
											</div>
								
										</c:when>
										<c:otherwise>
													<form class="form-horizontal">
														<div class="col-md-12">
														    <h3><span class="text-center text-danger">${result.msg }</span></h3>
														</div>
													</form>
										</c:otherwise>
									</c:choose>
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
	<script type="text/javascript" src="${rapp.nginx }js/ion.rangeSlider.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/jquery.form.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function(){
			$("#offTime").datepicker({
				autoclose:true,
				startDate:new Date()
			});
			$("#weight-rangeSlider").ionRangeSlider({
			    min: 1,
			    max: 100,
			    step: 1,
			    onFinish: function(obj){
			        $("#push-weight").val($("#weight-rangeSlider").val());
			    }
			});
			
			$('#configAdvertForm').bootstrapValidator({
		        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {/*验证*/
		        	advertName: {
		                validators: {
		                    notEmpty: {/*非空提示*/
		                        message: ' '
		                    },
		                    stringLength: {/*长度提示*/
		                        min: 1,
		                        max: 100,
		                        message: ' '
		                    }
		                }
		            },
		            channelsId: {
		                validators: {
		                    notEmpty: {
		                        message: ' '
		                    }
		                }
		            },
		            tempId: {
		            	validators: {
		            		notEmpty: {
		            			message: ' '
		            		}
		            	}
		            }
		        }
		    });
			
		});
	</script>
</body>
</html>