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
	<title>用户注册</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>用户注册</h1>
			<p></p>
			<p><a class="btn btn-primary btn-lg" href="${rapp.nginx }" role="button">去官网</a>
			</p>
		</div>
	</div>
	<div class="container-fluid">
		<div class="col-md-12">
			<form id="registerForm" class="form-horizontal" action="${rapp.rootPath }user/register/submit" 
				method="post" autocomplete="off">
			    <div class="form-group">
			        <label class="col-sm-3 control-label">用户名：</label>
			        <div class="col-sm-7">
						<input name="username" value="${ur.username }" class="form-control" placeholder="必填：可输入数字、字母、常用字符(限3~50字符)"></input>
						<span class="text-danger">${errun }</span>
			        </div>
			        <div class="col-sm-2"></div>
			    </div>
			    <div class="form-group">
			        <label class="col-sm-3 control-label">登录密码：</label>
			        <div class="col-sm-7">
						<input name="password" type="password" value="${ur.password }" class="form-control" placeholder="必填：可输入数字、字母、常用字符(至少8位，限30位)"></input>
						<span class="text-danger"></span>
			        </div>
			        <div class="col-sm-2"></div>
			    </div>
			    <div class="form-group">
			        <label class="col-sm-3 control-label">重复登录密码：</label>
			        <div class="col-sm-7">
						<input name="password_repeat" type="password" value="${ur.password_repeat }" class="form-control" placeholder="必填：可输入数字、字母、常用字符(至少8位，限30位)"></input>
						<span class="text-danger">${errpr }</span>
			        </div>
			        <div class="col-sm-2"></div>
			    </div>
			    <div class="form-group">
			        <label class="col-sm-3 control-label">邮箱地址：</label>
			        <div class="col-sm-7">
						<input name="email" value="${ur.email }" class="form-control" placeholder="必填"></input>
						<span class="text-danger">${erremail }</span>
			        </div>
			        <div class="col-sm-2"></div>
			    </div>
			    <div class="form-group">
			    	<label class="col-sm-3 control-label"></label>
			        <div class="col-sm-7">
			            <div class="checkbox checkbox-danger">
						    <input name="agree" id="input-agree" type="checkbox">
						    <label for="input-agree">
						      	  我已阅读并同意
						    </label>
						    	《 <a href="#">前辰无线平台服务条款</a>》《<a href="#">法律声明</a>》
						    <span class="help-block m-b-none"></span>
						</div>
					</div>
			        <div class="col-sm-2"></div>
			    </div>
			    <div class="form-group">
				    <label class="col-sm-3 control-label"></label>
			        <div class="col-sm-9">
			            <button class="btn btn-primary" type="submit">确认注册</button>
			           	 已有账号，直接去<a href="${rapp.rootPath }login/index">登录</a>
			        </div>
			    </div>
			</form>
		</div>
	</div>
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript">
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
		                        min: 3,
		                        max: 50,
		                        message: ' '
		                    },
	                        regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
	                            regexp: /^[a-zA-Z0-9_\.]+$/,
	                            message: ' '
	                        },
		                    remote: {  
		                        url: 'validate/user',  
		                        message:'该用户名已被使用',  
		                        type: "get",  
		                        dataType: 'json',  
		                        delay: 1000
		                    }
		                }
		            },
		            
		            password: {
		                validators: {
		                    notEmpty: {/*非空提示*/
		                        message: ' '
		                    },
		                    stringLength: {/*长度提示*/
		                        min: 8,
		                        max: 30,
		                        message: ' '
		                    }
		                }
		            },
		            
		            password_repeat: {
		                validators: {
		                    notEmpty: {/*非空提示*/
		                        message: ' '
		                    },
		                    stringLength: {/*长度提示*/
		                        min: 8,
		                        max: 30,
		                        message: ' '
		                    },
		                    identical: {
		                    	field: 'password',
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
		                    },
		                    remote: {  
		                        url: 'validate/email',  
		                        message:'该邮箱已被注册',  
		                        type: "get",  
		                        dataType: 'json',  
		                        delay: 1000
		                    }
		                }
		            }
		        }
		    });
			
			
		});
	</script>
</body>
</html>