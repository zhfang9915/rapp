<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>JS源码维护</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }code/index">JS源码列表</a></li>
		  <li class="active">${title }</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>请填写JS源码信息</h5>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${method == 'c' }">
						<form id="createJSCodeForm" class="form-horizontal" action="${rapp.rootPath }code/addJSCode" method="post">
							<div class="col-md-12">
							    <div class="form-group">
							        <label class="col-sm-3 control-label">JS源码名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="codeName" class="form-control" placeholder="必填：(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">是否默认：</label>
							        <div class="col-sm-8">
							            <select name="isDefault" class="selectpicker show-menu-arrow" title="该模版是否指定默认">
									  		<option value="1" <c:if test="${code.isDefault == 1 }">selected</c:if>>默认</option>
									  		<option value="0" <c:if test="${code.isDefault == 0 }">selected</c:if>>非默认</option>
										</select>
							            <span class="help-block m-b-none text-info"><small>默认模版只能配一个，若已存在默认模版并配置当前模版为默认，则取消原有的默认模版</small></span>
							        </div>
							        <div class="col-sm-1"></div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">服务器域名/IP：</label>
							        <div class="col-sm-9">
							            <input type="text" name="serverIp" class="form-control" placeholder="必填：(限20字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">服务器端口：</label>
							        <div class="col-sm-9">
							            <input type="text" name="serverPort" class="form-control" placeholder="选填：(限6数字)"> 
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
							        <label class="col-sm-3 control-label">源码：</label>
							        <div class="col-sm-9">
										<textarea name="code" class="form-control" rows="15" placeholder="必填：请输入可执行的JS代码"></textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">保存JS源码</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
					<c:when test="${method == 'u' }">
						<form id="updateJSCodeForm" class="form-horizontal" action="${rapp.rootPath }code/updateJSCode" method="post">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">源码编码：</label>
							        <div class="col-sm-9">
							            <input type="text" name="codeId" class="form-control" value="${code.codeId }" readonly="readonly"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">源码名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="codeName" class="form-control" value="${code.codeName }" placeholder="必填：(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">是否默认：</label>
							        <div class="col-sm-8">
							            <select name="isDefault" class="selectpicker show-menu-arrow" title="该模版是否指定默认">
									  		<option value="1" <c:if test="${code.isDefault == 1 }">selected</c:if>>默认</option>
									  		<option value="0" <c:if test="${code.isDefault == 0 }">selected</c:if>>非默认</option>
										</select>
							            <span class="help-block m-b-none text-info"><small>默认模版只能配一个，若已存在默认模版并配置当前模版为默认，则取消原有的默认模版</small></span>
							        </div>
							        <div class="col-sm-1"></div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">服务器域名/IP：</label>
							        <div class="col-sm-9">
							            <input type="text" name="serverIp" class="form-control" value="${code.serverIp }" placeholder="必填：(限20字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">服务器端口：</label>
							        <div class="col-sm-9">
							            <input type="text" name="serverPort" class="form-control" value="${code.serverPort }" placeholder="必填：(限20字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
										<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明">${code.remark }</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">源码：</label>
							        <div class="col-sm-9">
										<textarea name="code" class="form-control" rows="15" placeholder="必填：请输入可执行的JS代码">${code.code }</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">保存JS源码</button>
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
	<script type="text/javascript" src="${rapp.nginx }script/code.js"></script>

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