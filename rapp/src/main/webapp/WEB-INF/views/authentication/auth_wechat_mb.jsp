<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head lang="zh-CN">
<meta charset="UTF-8">
<title>前辰科技-微信连Wi-Fi</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<script type="text/javascript" src="${rapp.nginx }js/jquery.min.js"></script>
<script type="text/javascript" src="${rapp.nginx }js/auth/wechat/jQuery.md5.js"></script>
<script type="text/javascript" src="https://wifi.weixin.qq.com/resources/js/wechatticket/wechatutil.js"></script>
<script type="text/javascript">
		
	/**
	 * 微信连Wi-Fi协议3.1供运营商portal呼起微信浏览器使用
	 */
	var loadIframe = null;
	var noResponse = null;
	var callUpTimestamp = 0;

	function putNoResponse(ev) {
		clearTimeout(noResponse);
	}

	
	function errorJump() {
		var now = new Date().getTime();
		if ((now - callUpTimestamp) > 4 * 1000) {
			return;
		}
		alert('该浏览器不支持自动跳转微信请手动打开微信\n如果已跳转请忽略此提示');
	}

	
	myHandler = function(error) {
		errorJump();
	};

	
	function createIframe() {
		var iframe = document.createElement("iframe");
		iframe.style.cssText = "display:none;width:0px;height:0px;";
		document.body.appendChild(iframe);
		loadIframe = iframe;
	}
	//注册回调函数
	function jsonpCallback(result) {
		if (result && result.success) {
			console.log('WeChat will call up : ' + result.success + '  data:'
					+ result.data);
			var ua = navigator.userAgent;
			if (ua.indexOf("iPhone") != -1 || ua.indexOf("iPod") != -1
					|| ua.indexOf("iPad") != -1) { //iPhone             
				document.location = result.data;
			} else {

				if ('false' == 'true') {
					alert('[强制]该浏览器不支持自动跳转微信请手动打开微信\n如果已跳转请忽略此提示');
					return;
				}

				createIframe();
				callUpTimestamp = new Date().getTime();
				loadIframe.src = result.data;
				noResponse = setTimeout(function() {
					errorJump();
				}, 3000);
			}
		} else if (result && !result.success) {
			alert(result.data);
		}
	}

	function Wechat_GotoRedirect(appId, extend, timestamp, sign, shopId,
			authUrl, mac, ssid, bssid) {

		//将回调函数名称带到服务器端
		var url = "https://wifi.weixin.qq.com/operator/callWechatBrowser.xhtml?appId="
				+ appId
				+ "&extend="
				+ extend
				+ "&timestamp="
				+ timestamp
				+ "&sign=" + sign;

		//如果sign后面的参数有值，则是新3.1发起的流程
		if (authUrl && shopId) {

			url = "https://wifi.weixin.qq.com/operator/callWechat.xhtml?appId="
					+ appId + "&extend=" + extend + "&timestamp=" + timestamp
					+ "&sign=" + sign + "&shopId=" + shopId + "&authUrl="
					+ encodeURIComponent(authUrl) + "&mac=" + mac + "&ssid="
					+ ssid + "&bssid=" + bssid;

		}

		//通过dom操作创建script节点实现异步请求  
		var script = document.createElement('script');
		script.setAttribute('src', url);
		document.getElementsByTagName('head')[0].appendChild(script);
	}
</script>
<link rel="stylesheet" href="${rapp.nginx }css/auth/wechat/style-simple-follow.css" />
</head>
<body class="mod-simple-follow">
	<div class="mod-simple-follow-page">
		<div class="mod-simple-follow-page__banner">
			<img class="mod-simple-follow-page__banner-bg"
				src="${rapp.nginx }img/auth/wechat/banner01.jpg" alt="商家 bunner" />
			<div class="mod-simple-follow-page__img-shadow"></div>
		</div>
		<div class="mod-simple-follow-page__attention">
			<p class="mod-simple-follow-page__attention-txt">温馨提示：未完成认证的用户将在1分钟后失去上网权限，需重新认证</p>
			<a class="mod-simple-follow-page__attention-btn"
				onclick="callWechatBrowser()">一键打开微信连Wi-Fi</a>
		</div>
	</div>
</body>
<script type="text/javascript">
		function callWechatBrowser(){
			//进行临时放行
			var script = document.createElement("script");
			script.setAttribute('type', "text/javascript");
			script.setAttribute('src', "http://${wal.gw}:${wal.port}/qcdog/auth?jumpweb=success&temp_accept=50");
			document.getElementsByTagName('head')[0].appendChild(script);
			//唤起微信
			var extend = "mb";
			var timestamp = new Date().getTime();
			var authUrl = '${rapp.rootPath}authentication/wechat/callBack/${cbid}';
			var sign = $.md5(
				"${wa.appId}" 
				+ extend 
				+ timestamp 
				+ "${wa.shopId}" 
				+ authUrl 
				+ "${wal.mac}" 
				+ "${wal.ssid}" 
				+ "${wal.bssid}" 
				+ "${secretkey}"
			);
			Wechat_GotoRedirect(
				"${wa.appId}",
				extend,
				timestamp,
				sign,
				"${wa.shopId}",
				authUrl,
				"${wal.mac}",
				"${wal.ssid}",
				"${wal.bssid}"
			);
		}
</script>

<script type="text/javascript">
	document.addEventListener('visibilitychange', putNoResponse, false);
</script>
</html>