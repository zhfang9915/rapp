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

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container minheight">
		<br/>
		<div class="row">
			<div class="col-sm-12">
				<div class="wrapper wrapper-content animated fadeInUp">

					<div class="ibox-content m-b-sm border-bottom">
						<div class="text-center p-lg">
							<h2>管理员已收到您的建议</h2>
							<span>非常感谢您为前辰无线提出了宝贵的意见</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${rapp.nginx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }/js/bootstrap.min.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>