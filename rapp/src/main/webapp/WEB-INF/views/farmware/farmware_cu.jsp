<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>固件维护</title>
<%@include file="../common/head.jsp"%>
<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li><a href="${rapp.rootPath }fw/index">固件列表</a></li>
		  <li class="active">${title }</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>请填写固件信息</h5>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${method == 'c' }">
						<form id="createFirmWareForm" class="form-horizontal" action="${rapp.rootPath }fw/addFirmWare" method="post" enctype="multipart/form-data">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">上传固件：</label>
							        <div class="col-sm-9">
							            <input type="file" name="farmware_file" class="form-control">
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">固件版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="version" class="form-control" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">openwrt版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="openwrt" class="form-control" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">交叉编译工具版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="crossstool" class="form-control" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">刷机教程说明：</label>
							        <div class="col-sm-9">
							            <input type="text" name="flashCourse" class="form-control" placeholder="选填：(限150字符)"> 
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
							            <button class="btn btn-primary" type="submit">保存固件</button>
							        </div>
							    </div>
							</div>
						</form>
					</c:when>
					<c:when test="${method == 'u' }">
						<form id="updateFirmWareForm" class="form-horizontal" action="${rapp.rootPath }fw/updateFirmWare" method="post">
							<div class="col-md-12">
								<div class="form-group">
							        <label class="col-sm-3 control-label">固件编码：</label>
							        <div class="col-sm-9">
							            <input type="text" name="fwId" class="form-control" value="${fw.fwId }" readonly="readonly"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">固件名称：</label>
							        <div class="col-sm-9">
							            <input type="text" name="fwName" class="form-control" value="${fw.fwName }" placeholder="必填：(限100字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">固件版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="version" class="form-control" value="${fw.version }" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">openwrt版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="openwrt" class="form-control" value="${fw.openwrt }" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">交叉编译工具版本：</label>
							        <div class="col-sm-9">
							            <input type="text" name="crossstool" class="form-control" value="${fw.crossstool }" placeholder="必填：(限50字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">刷机教程说明：</label>
							        <div class="col-sm-9">
							            <input type="text" name="flashCourse" class="form-control" value="${fw.flashCourse }" placeholder="选填：(限150字符)"> 
							            <span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <label class="col-sm-3 control-label">备注：</label>
							        <div class="col-sm-9">
										<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明">${fw.remark }</textarea>
										<span class="help-block m-b-none"></span>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-12 col-sm-offset-3">
							            <button class="btn btn-primary" type="submit">更新固件</button>
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
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/farmware.js"></script>

	<script type="text/javascript">
		$(function() {
			//重置表单
			 $('#createFirmWareForm,#updateFirmWareForm').bootstrapValidator('resetForm');
			 
			 $('#createFirmWareForm,#updateFirmWareForm').bootstrapValidator({
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		            	fwName: {
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
		                      	}
		                    }
		                }
		            }
		        });
		});
	</script>
</body>
</html>