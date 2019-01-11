<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>我要提建议</title>
	<link rel="shortcut icon" href="${rapp.nginx }/favicon.ico">
	<link href="${rapp.nginx }/css/bootstrap.min.css" rel="stylesheet">
	<link href="${rapp.nginx }/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container minheight">
		<br/>
		<div class="col-md-12">
			<form id="suggestForm" action="${rapp.rootPath }suggest/submit" method="post" autocomplete="off">
			    <div class="form-group">
			        <label class="col-sm-2 control-label text-right">主题：</label>
			        <div class="col-sm-10">
			            <input type="text" name="title" class="form-control" placeholder="请输入建议主题" value="${suggest.title }">
						<span class="help-block m-b-none"></span>
			        </div>
			    </div>
			    <div class="form-group">
			        <label class="col-sm-2 control-label text-right">内容：</label>
			        <div class="col-sm-10">
			            <textarea type="text" name="context" class="form-control" rows="10" placeholder="请输入文本"> ${suggest.context }</textarea>
						<span class="help-block m-b-none"></span>
			        </div>
			    </div>
			    <div class="form-group">
			        <div class="col-sm-12 col-sm-offset-2">
			            <button class="btn btn-primary" type="submit">提交建议</button>
			        </div>
			    </div>
		    </form>
		</div>
	</div>

	<script type="text/javascript" src="${rapp.nginx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }/js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript">
		$(function(){
			$('#suggestForm').bootstrapValidator({
		        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {/*验证*/
		        	title: {
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
		            context: {
		                validators: {
		                	notEmpty: {/*非空提示*/
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