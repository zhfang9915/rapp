<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>广告模板维护</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }advertTemp/index">广告模板列表</a></li>
		  <li class="active">${title }</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>请填写广告模板信息</h5>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${method == 'c' }">
						<form id="createAdvertTempForm" class="form-horizontal" action="${rapp.rootPath }advertTemp/addAdvertTemp" method="post">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">模板名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="tempName" class="form-control" placeholder="必填：(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">是否活跃：</label>
							        <div class="col-sm-8">
							            <select name="state" class="selectpicker show-menu-arrow" title="该模板的是否活跃">
									  		<option value="Y">活跃</option>
									  		<option value="N">禁用</option>
										</select>
							            <!-- <span class="help-block m-b-none text-info"><small>默认模版只能配一个，若已存在默认模版并配置当前模版为默认，则取消原有的默认模版</small></span> -->
							        </div>
							        <div class="col-sm-1"></div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">是否多图：</label>
							        <div class="col-sm-8">
							            <select name="isMore" class="selectpicker show-menu-arrow" title="该模版是否有多图">
									  		<option value="Y">是</option>
									  		<option value="N">否</option>
										</select>
							            <!-- <span class="help-block m-b-none text-info"><small>默认模版只能配一个，若已存在默认模版并配置当前模版为默认，则取消原有的默认模版</small></span> -->
							        </div>
							        <div class="col-sm-1"></div>
							    </div>
								<div class="form-group">
							        <label class="col-sm-3 control-label">基础内容：</label>
							        <div class="col-sm-9">
										<textarea name="temp" class="form-control" rows="15" placeholder="必填"></textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">循环内容：</label>
							        <div class="col-sm-9">
										<textarea name="tempFor" class="form-control" rows="6" placeholder="必填"></textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">相关js地址：</label>
							        <div class="col-sm-9">
										<textarea name="tempJs" class="form-control" rows="2" placeholder="必填"></textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">保存模板</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
					<c:when test="${method == 'u'}">
						<form id="updateJSCodeForm" class="form-horizontal" action="${rapp.rootPath}advertTemp/updateAdvertTemp" method="post">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">模板编码：</label>
							        <div class="col-sm-9">
							            <input type="text" name="tempId" class="form-control" value="${advertTemp.tempId}" readonly="readonly"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">模板名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="tempName" class="form-control" value="${advertTemp.tempName}" placeholder="必填：(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">是否活跃：</label>
							        <div class="col-sm-8">
							            <select name="state" class="selectpicker show-menu-arrow" title="该模版是否活跃">
									  		<option value="Y" <c:if test="${advertTemp.state == 'Y' }">selected</c:if>>活跃</option>
									  		<option value="N" <c:if test="${advertTemp.state == 'N' }">selected</c:if>>禁用</option>
										</select>
							            <!-- <span class="help-block m-b-none text-info"><small>默认模版只能配一个，若已存在默认模版并配置当前模版为默认，则取消原有的默认模版</small></span> -->
							        </div>
							        <div class="col-sm-1"></div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">是否多图：</label>
							        <div class="col-sm-8">
							            <select name="isMore" class="selectpicker show-menu-arrow" title="该模版是否有多图">
									  		<option value="Y" <c:if test="${advertTemp.isMore == 'Y' }">selected</c:if>>是</option>
									  		<option value="N" <c:if test="${advertTemp.isMore == 'N' }">selected</c:if>>否</option>
										</select>
							            <!-- <span class="help-block m-b-none text-info"><small>默认模版只能配一个，若已存在默认模版并配置当前模版为默认，则取消原有的默认模版</small></span> -->
							        </div>
							        <div class="col-sm-1"></div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">基础内容：</label>
							        <div class="col-sm-9">
										<textarea name="temp" class="form-control" rows="15" placeholder="必填">${advertTemp.temp}</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">循环内容：</label>
							        <div class="col-sm-9">
										<textarea name="tempFor" class="form-control" rows="15" placeholder="必填">${advertTemp.tempFor}</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">相关js地址：</label>
							        <div class="col-sm-9">
										<textarea name="tempJs" class="form-control" rows="15" placeholder="必填：请输入可执行的JS代码">${advertTemp.tempJs}</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">确认更新</button>
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
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/apvert/advert_temp.js"></script>

	<script type="text/javascript">
		$(function() {
			 $('#createJSCodeForm,#updateJSCodeForm').bootstrapValidator({
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		            	codeName: {
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
		                serverIp: {
		                    validators: {
		                        stringLength: {
		                            min: 1,
		                            max: 20,
		                            message: ' '
		                        }
		                    }
		                },
		                serverPort: {
		                    validators: {
		                    	regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
		                            regexp: /^[0-9_\.]+$/,
		                            message: ' '
		                        },
		                        stringLength: {
		                            min: 1,
		                            max: 6,
		                            message: ' '
		                        }
		                    }
		                },
		                code: {
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