<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>区域管理</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<link rel="stylesheet" href="${rapp.nginx }css/bootstrap-chinese-region.css">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }channel/list/index">区域列表</a></li>
		  <li class="active">${title }</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>请填写区域信息</h5>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${method == 'c' }">
						<form id="createChannelForm" class="form-horizontal" action="${rapp.rootPath }channel/addChannel" method="post">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">区域名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="channelNameCh" class="form-control" placeholder="必填：请输入区域中文名称(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">区域简称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="channelNameEn" class="form-control" placeholder="必填：请输入字母或数字组合(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label" for="-NaN">状态：</label>
							        <div class="col-sm-9">
							            <label class="radio-inline" for="state-off">
							                <input type="radio" checked="checked" value="0" id="state-off" name="state">禁用</label>
							            <label class="radio-inline" for="state-on">
							                <input type="radio" value="1" id="state-on" name="state">启用</label>
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <c:if test="${roleId<3 }">
								    <div class="form-group">
								        <label class="col-sm-3 control-label">分配JS：</label>
								        <div class="col-sm-9">
								            <select name="codeId" class="selectpicker show-menu-arrow" title="选择JS模版" data-size="6" >
												<c:choose>
													<c:when test="${codes.success }">
														<c:forEach var="code" items="${codes.data}">
													  		<option value="${code.codeId }">${code.codeName }</option>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<option disabled>无可配置的JS模版</option>
													</c:otherwise>
												</c:choose>
											</select>
								            <span class="help-block m-b-none"></span>
								        </div>
								    </div>
								</c:if>
<!-- 							    <div class="form-group"> -->
<!-- 							        <label class="col-sm-3 control-label">区域信息：</label> -->
<!-- 							        <div class="col-sm-9"> -->
<!-- 							        	<div class="bs-chinese-region flat dropdown" data-min-level="1" data-max-level="3" data-def-val="[name=address]"> -->
<!-- 											<input type="text" class="form-control" id="address" name="selectCity" placeholder="请选择地区" data-toggle="dropdown" readonly=""> -->
<!-- 											<input type="hidden" class="form-control" name="address"> -->
<!-- 											<div class="dropdown-menu" role="menu" aria-labelledby="dLabel"> -->
<!-- 												<div> -->
<!-- 													<ul class="nav nav-tabs" role="tablist"> -->
<!-- 														<li role="presentation" class="active"><a href="#province" data-next="city" role="tab" data-toggle="tab">省份</a></li> -->
<!-- 														<li role="presentation"><a href="#city" data-next="district" role="tab" data-toggle="tab">城市</a></li> -->
<!-- 														<li role="presentation"><a href="#district" data-next="street" role="tab" data-toggle="tab">县区</a></li> -->
<!-- 													</ul> -->
<!-- 													<div class="tab-content"> -->
<!-- 														<div role="tabpanel" class="tab-pane active" id="province">--</div> -->
<!-- 														<div role="tabpanel" class="tab-pane" id="city">--</div> -->
<!-- 														<div role="tabpanel" class="tab-pane" id="district">--</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 							        </div> -->
<!-- 							    </div> -->
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
										<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明"></textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">保存区域</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
					<c:when test="${method == 'u' }">
						<form id="updateChannelForm" class="form-horizontal" action="${rapp.rootPath }channel/updateChannel" method="post">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">区域编码：</label>
							        <div class="col-sm-9">
							            <input type="text" name="channelId" class="form-control" value="${channel.channelId }" readonly="readonly"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">区域名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="channelNameCh" class="form-control" value="${channel.channelNameCh }" placeholder="必填：请输入区域中文名称(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">区域简称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="channelNameEn" class="form-control" value="${channel.channelNameEn }" placeholder="必填：请输入字母或数字组合(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label" for="-NaN">状态：</label>
							        <div class="col-sm-9">
							            <label class="radio-inline" for="state-off">
							                <input type="radio" <c:if test="${channel.state == 0 }">checked="checked"</c:if> value="0" id="state-off" name="state">禁用</label>
							            <label class="radio-inline" for="state-on">
							                <input type="radio" <c:if test="${channel.state == 1 }">checked="checked"</c:if> value="1" id="state-on" name="state">启用</label>
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <c:if test="${roleId<3 }">
								    <div class="form-group">
								        <label class="col-sm-3 control-label">分配JS：</label>
								        <div class="col-sm-9">
								            <select name="codeId" class="selectpicker show-menu-arrow" title="选择JS模版" data-size="6" >
												<c:choose>
													<c:when test="${codes.success }">
														<c:forEach var="code" items="${codes.data}">
													  		<option value="${code.codeId }" <c:if test="${channel.code.codeId == code.codeId }">selected</c:if>>${code.codeName }</option>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<option disabled>无可配置的JS模版</option>
													</c:otherwise>
												</c:choose>
											</select>
								            <span class="help-block m-b-none"></span>
								        </div>
								    </div>
							    </c:if>
<!-- 							    <div class="form-group"> -->
<!-- 							        <label class="col-sm-3 control-label">区域信息：</label> -->
<!-- 							        <div class="col-sm-9"> -->
<!-- 							        	<div class="bs-chinese-region flat dropdown" data-min-level="1" data-max-level="3" data-def-val="[name=address]"> -->
<!-- 											<input type="text" class="form-control" id="address" name="selectCity" placeholder="请选择地区" data-toggle="dropdown" readonly=""> -->
<%-- 											<input type="hidden" class="form-control" name="address" value="${channel.address }"> --%>
<!-- 											<div class="dropdown-menu" role="menu" aria-labelledby="dLabel"> -->
<!-- 												<div> -->
<!-- 													<ul class="nav nav-tabs" role="tablist"> -->
<!-- 														<li role="presentation" class="active"><a href="#province" data-next="city" role="tab" data-toggle="tab">省份</a></li> -->
<!-- 														<li role="presentation"><a href="#city" data-next="district" role="tab" data-toggle="tab">城市</a></li> -->
<!-- 														<li role="presentation"><a href="#district" data-next="street" role="tab" data-toggle="tab">县区</a></li> -->
<!-- 													</ul> -->
<!-- 													<div class="tab-content"> -->
<!-- 														<div role="tabpanel" class="tab-pane active" id="province">--</div> -->
<!-- 														<div role="tabpanel" class="tab-pane" id="city">--</div> -->
<!-- 														<div role="tabpanel" class="tab-pane" id="district">--</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 							        </div> -->
<!-- 							    </div> -->
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
										<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明">${channel.remark }</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">保存区域</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
				</c:choose>
			</div>
		</div>
	
	</div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="${rapp.nginx }js/layer.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-chinese-region.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/channel.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
	
		$(function() {
			$.getJSON(rootPath + 'js/sql_areas.json',function(data){
				for (var i = 0; i < data.length; i++) {
					var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
					data[i] = area;
				}
				$('.bs-chinese-region').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
					$(this).find('[name=address]').val(areas[areas.length-1].id);
				});
			});
			
			 $('#createChannelForm,#updateChannelForm').bootstrapValidator({
	            excluded : [':disabled'],//[':disabled', ':hidden', ':not(:visible)'] //设置隐藏组件可验证
	            live: 'enabled',
	            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {/*验证*/
	            	channelNameCh: {
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
	                channelNameEn: {
	                    validators: {
	                        notEmpty: {
	                            message: ' '
	                        },
	                        regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
	                            regexp: /^[a-zA-Z0-9_\.]+$/,
	                            message: ' '
	                        },
	                        stringLength: {
	                            min: 1,
	                            max: 50,
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