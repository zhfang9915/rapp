<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>找回密码</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>找回密码</h1>
			<p></p>
			<p><a class="btn btn-primary btn-lg" href="${rapp.nginx }" role="button">去官网</a>
			</p>
		</div>
	</div>
	<div class="container-fluid">
				<div class="col-md-12">
					<form id="registerForm" class="form-horizontal" action="${rapp.rootPath }user/find/submit" 
						method="post">
					    <div class="form-group">
					        <label class="col-sm-3 control-label">用户名：</label>
					        <div class="col-sm-5">
								<input name="username" value="${ur.username }" class="form-control" placeholder="必填：可输入数字、字母、常用字符(限50字)"></input>
								<span class="text-danger">${errun }</span>
					        </div>
					        <div class="col-sm-4"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-3 control-label">邮箱地址：</label>
					        <div class="col-sm-5">
								<input name="email" value="${ur.email }" class="form-control" placeholder="必填"></input>
								<span class="text-danger">${erremail }</span>
					        </div>
					        <div class="col-sm-4"></div>
					    </div>
					    <div class="form-group">
					    	<label class="col-sm-3 control-label">验证码：</label>
					        <div class="col-sm-5">
								<input name="rand" type="text" class="form-control" placeholder="验证码" style="width: 50%;float: left;">
								<img src="${rapp.rootPath }base/randcode?"+(new Date()).valueOf() class="img-randconde pull-right"
									onclick="common.clickImage();" style="cursor: pointer;width: 27%;margin-right: 10%;height: 40px;">
					        </div>
					        <div class="col-sm-4">${errcode }</div>
						</div>
					    <div class="form-group">
						    <label class="col-sm-3 control-label"></label>
					        <div class="col-sm-9">
					            <button class="btn btn-primary" type="submit">确认提交</button>
					        </div>
					    </div>
					</form>
				</div>
	</div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript">
		var rootPath = "${rootPath}";
		$(function(){
			
			$('#registerForm').bootstrapValidator({
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	username: {
		                validators: {
		                    notEmpty: {/*非空提示*/
		                        message: ' '
		                    },
		                    stringLength: {/*长度提示*/
		                        min: 1,
		                        max: 50,
		                        message: ' '
		                    }
		                }
		            },
		            
		            email: {
		                validators: {
		                    notEmpty: {/*非空提示*/
		                        message: ' '
		                    },
		                    stringLength: {/*长度提示*/
		                        min: 1,
		                        max: 50,
		                        message: ' '
		                    },
		                    emailAddress : {
		                    	message: ' '
		                    }
		                }
		            },
		            rand: {
		            	validators: {
		            		notEmpty: {
		            			message: ' '
		            		},
		            		stringLength: {
		            			min: 4,
		            			max: 4,
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