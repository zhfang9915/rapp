<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!--header-->
<header>
	<link href="${rapp.nginx }css/main.style.css" type="text/css" rel="stylesheet" media="all">
	<div class="customheader header">
		<div class="customheader container">
			<nav class="customheader navbar navbar-default" role="navigation">
				<div class="container-fluid">
				  <div class="container">
					<div class="row">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							  <span class="sr-only">Toggle navigation</span>
							  <span class="icon-bar"></span>
							  <span class="icon-bar"></span>
							  <span class="icon-bar"></span>
							</button>
							<a class="navbar-brand logo" href="${rapp.nginx }"><img src="${rapp.nginx }img/qclogo.png" alt="" class="logoimg"></a>
						  </div>
						<!--navbar-header-->
						<div class="customheader collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="customheader nav navbar-nav">
								<li><a href="${rapp.rootPath }" >主页</a></li>
								<li><a href="${rapp.rootPath }channel/list/index">区域管理</a></li>
							   	<li class="customheader dropdown">
									<a href="#" class="customheader dropdown-toggle js-activated" data-toggle="dropdown">设备管理<b class="customheader caret"></b></a>
									<ul class="customheader dropdown-menu multi-column columns-4" style="min-width: 105px;">
										<div class="customheader row">
											<div class="customheader col-sm-12">
												<ul class="customheader multi-column-dropdown">
													<li><a class="customheader list" href="${rapp.rootPath }router/list">我的设备</a></li>
													<li><a class="customheader list" href="${rapp.taobao }" target="_blank">购买设备</a></li>
												</ul>
											</div>																		
										</div>
									</ul>
								</li>
							   	<li class="customheader dropdown">
									<a href="#" class="customheader dropdown-toggle js-activated" data-toggle="dropdown">广告<b class="customheader caret"></b></a>
									<ul class="customheader dropdown-menu multi-column columns-4" style="min-width: 105px;">
										<div class="customheader row">
											<div class="customheader col-sm-12">
												<ul class="customheader multi-column-dropdown">
													<li><a class="customheader list" href="${rapp.rootPath }apvert/config">创建广告</a></li>
													<li><a class="customheader list" href="${rapp.rootPath }apvert/list">广告总揽</a></li>
													<li><a class="customheader list" href="${rapp.rootPath }aplog/history">推送历史</a></li>
												</ul>
											</div>																		
										</div>
									</ul>
								</li>
		<!-- 						<li><a href="#">认证</a></li>	 -->
								<li class="customheader dropdown">
									<a href="#" class="customheader dropdown-toggle js-activated" data-toggle="dropdown">统计报表<b class="customheader caret"></b></a>
									<ul class="customheader dropdown-menu multi-column columns-4">
										<div class="customheader row">
											<div class="customheader col-sm-4">
												<h4>广告</h4>
												<ul class="customheader multi-column-dropdown">
													<li><a class="customheader list" href="${rapp.rootPath }ds/apc">广告统计</a></li>
												</ul>
											</div>																		
											<div class="customheader col-sm-4">
												<h4>设备</h4>
												<ul class="customheader multi-column-dropdown">
													<li><a class="customheader list" href="#">功能内测</a></li>
												</ul>
											</div>																		
											<div class="customheader col-sm-4">
												<h4>认证</h4>
												<ul class="customheader multi-column-dropdown">
													<li><a class="customheader list" href="#">功能内测</a></li>
												</ul>
											</div>																		
										</div>
									</ul>
								</li>					
		<%-- 						<li><a href="${rapp.rootPath }spy/index">WIFI侦探</a></li>						 --%>
<%-- 								<li><a href="${rapp.nginx }download.html" >固件下载</a></li> --%>
								<li><a href="${rapp.rootPath }suggest/index">建议</a></li>
							</ul> 
							<!--/.navbar-collapse-->
						</div>
					</div>
				</div>
			</div>
			<!--//navbar-header-->
			</nav>
			<div class="customheader header-info">
				<div class="customheader header-right login">
					<a href="#"><span class="customheader glyphicon glyphicon-user" aria-hidden="true"></span><b class="customheader caret"></b></a>
					<div id="loginBox">                
						<form id="loginForm">
							<fieldset id="body">
								<fieldset>
									<strong>${backUser.username}</strong>
								</fieldset>
								<fieldset>
									<a href="${rapp.rootPath }user/center">个人信息</a>
								</fieldset>
								<fieldset>
									<a href="${rapp.rootPath }suggest/index">我要提建议</a>
								</fieldset>
								<fieldset>
									<a href="${rapp.rootPath }login/exit">退出</a>
								</fieldset>
							</fieldset>
						</form>
					</div>
				</div>			
			</div>
		</div>
	</div>
</header>
<!--//header-->