<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head lang="zh-CN">
	<meta charset="UTF-8">
	<title>前辰科技-微信连Wi-Fi</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="format-detection" content="telephone=no">
	
	<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/auth/wechat/pcauth.js"></script>
	<link rel="stylesheet" href="${rapp.nginx }css/auth/wechat/style-pcdemo.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<img src="${rapp.nginx }img/auth/wechat/logo.png" class="header__logo" alt="商户logo">
			<!--建议图片大小为570x140 或 287x70-->
		</div>

		<div class="main" style="background-image: url(${rapp.nginx }img/auth/wechat/banner01.jpg);">
			<!--建议图片大小为 1920x1200 或 1920x1080-->
			<div class="main__content">
				<h2 class="main__content-title">
					欢迎使用<em>免费Wi-Fi</em>
				</h2>
				<div class="main__content-qrcode" id='qrcode_zone' style="text-align: center; margin: 20px auto; width: 250px;"></div>
				<div class="main__content-info">使用微信扫描二维码</div>
			</div>

		</div>

		<div class="footer">
			<div class="main__content-info" style="color: white">温馨提示：未认证成功的用户 1 分钟后将失去上网权限，需重新认证</div>
		</div>
	</div>
</body>
<script type="text/javascript">
		
		JSAPI.auth({
			target : document.getElementById('qrcode_zone'),
		 	appId : '${wa.appId}',
            shopId : '${wa.shopId}',
			extend : 'pc',
			authUrl : '${rapp.rootPath}authentication/wechat/callBack/${cbid}'
		});
</script>
</html>