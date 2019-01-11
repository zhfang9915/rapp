<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>插件维护</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }plugin/index">插件列表</a></li>
		  <li class="active">${title }</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>请填写插件信息</h5>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${method == 'c' }">
						<form id="createPluginUnitForm" class="form-horizontal" action="${rapp.rootPath }plugin/addPluginUnit" method="post" enctype="multipart/form-data">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">上传插件：</label>
							        <div class="col-sm-9">
							            <input type="file" name="plugin_file" class="form-control">
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="version" class="form-control" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">选择固件：</label>
							        <div class="col-sm-9">
							            <select name="crossstool" class="selectpicker show-menu-arrow" title="选择固件" data-size="6" >
											<c:choose>
												<c:when test="${fws.success }">
													<c:forEach var="fw" items="${fws.data}">
												  		<option value="${fw.crossstool }">${fw.version }(${fw.crossstool })</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<option disabled>无可配置的固件</option>
												</c:otherwise>
											</c:choose>
										</select>
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">状态：</label>
							        <div class="col-sm-9">
							            <div class="col-sm-9">
								            <label class="radio-inline" for="state-off">
								                <input type="radio" checked="checked" value="0" id="state-off" name="state">禁用</label>
								            <label class="radio-inline" for="state-on">
								                <input type="radio" value="1" id="state-on" name="state">启用</label>
								            <span class="help-block m-b-none"></span>
								        </div>
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
										<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明"></textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">保存插件</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
					<c:when test="${method == 'u' }">
						<form id="updatePluginUnitForm" class="form-horizontal" action="${rapp.rootPath }plugin/updatePluginUnit" method="post">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">插件编码：</label>
							        <div class="col-sm-9">
							            <input type="text" name="pluginId" class="form-control" value="${pu.pluginId }" readonly="readonly"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="pluginName" class="form-control" value="${pu.pluginName }" placeholder="必填：(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label" for="-NaN">状态：</label>
							        <div class="col-sm-9">
							            <label class="radio-inline" for="state-off">
							                <input type="radio" <c:if test="${pu.state == 0 }">checked="checked"</c:if> value="0" id="state-off" name="state">禁用</label>
							            <label class="radio-inline" for="state-on">
							                <input type="radio" <c:if test="${pu.state == 1 }">checked="checked"</c:if> value="1" id="state-on" name="state">启用</label>
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">插件版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="version" class="form-control" value="${pu.version }" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">选择固件：</label>
							        <div class="col-sm-9">
							            <select name="crossstool" class="selectpicker show-menu-arrow" title="选择固件" data-size="6" >
											<c:choose>
												<c:when test="${fws.success }">
													<c:forEach var="fw" items="${fws.data}">
												  		<option value="${fw.crossstool }" <c:if test="${pu.fw.crossstool == fw.crossstool }">selected</c:if>>${fw.version }(${fw.crossstool })</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<option disabled>无可配置的固件</option>
												</c:otherwise>
											</c:choose>
										</select>
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
										<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明">${pu.remark }</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">更新插件</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
				</c:choose>
			</div>
		</div>
	
	</div>
	
	<%@include file="../common/foot.jsp"%>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/plugin.js"></script>

	<script type="text/javascript">
		$(function() {
			//重置表单
			 $('#createPluginUnitForm,#updatePluginUnitForm').bootstrapValidator('resetForm');
			 
			 $('#createPluginUnitForm,#updatePluginUnitForm').bootstrapValidator({
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		            	pluginName: {
		                    message: ' ',
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
		                version: {
		                    validators: {
		                    	notEmpty: {/*非空提示*/
		                            message: ' '
		                        },
		                        stringLength: {
		                            min: 1,
		                            max: 50,
		                            message: ' '
		                        }
		                    }
		                },
		                openwrt: {
		                    validators: {
		                    	notEmpty: {/*非空提示*/
		                            message: ' '
		                        },
		                        stringLength: {
		                            min: 1,
		                            max: 50,
		                            message: ' '
		                        }
		                    }
		                },
		                crossstool: {
		                    validators: {
		                    	notEmpty: {/*非空提示*/
		                            message: ' '
		                        },
		                        stringLength: {
		                            min: 1,
		                            max: 50,
		                            message: ' '
		                        }
		                    }
		                },
		                
		                farmware_file: {
		                    validators: {
		                    	notEmpty: {
		                            message: ' '
		                        },
		                        file: {
		                            maxSize: 10*1024*1024,   // 10 MB
		                            message: ' '
		                      	},
		                    }
		                }
		            }
		        });
		});
	</script>
</body>
</html>