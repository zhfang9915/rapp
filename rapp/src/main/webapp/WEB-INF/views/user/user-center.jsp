<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" href="${rapp.nginx }favicon.ico">
	<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
	<link href="${rapp.nginx }css/style.min862f.css" rel="stylesheet">
	<link href="${rapp.nginx }css/font-awesome.min93e3.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>我的个人信息</title>
	<%@include file="../common/notop.jsp"%>
</head>
<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
	<div class="container wrapper wrapper-content minheight">
		<div class="ibox">
			<div class="ibox-title">
				<h5>账户：${backUser.username }</h5>
			    <div class="ibox-tools">
			        <button class="btn btn-primary btn-xs" type="button" onClick="account.openChangpwd();">修改密码</button>
			    </div>
			</div>
		</div>
        <div class="row animated fadeInRight">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基本信息</h5>
                    </div>
                    <div>
                    	<div class="ibox-content no-padding border-left-right thumbnail" style="margin-bottom:0px;">
                            <img alt="image" class="img-responsive img-rounded" src="${rapp.nginx }img/qclogo.png"/>
                        </div>
                        <div class="ibox-content profile-content">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">用户名</label>
									<div class="col-sm-9">
										<p class="form-control-static">${backUser.username }
											<c:choose>
												<c:when test="${backUser.state == 2 }">
													<span class="label label-success pull-right">未激活</span>
												</c:when>
												<c:when test="${backUser.state == 1 }">
													<span class="label label-primary pull-right">激活</span>
												</c:when>
											</c:choose>
										</p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">邮箱地址</label>
									<div class="col-sm-9">
										<p class="form-control-static"> ${backUser.email }</p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">用户类型</label>
									<div class="col-sm-9">
										<p class="form-control-static">${backUser.role.roleName }</p>
									</div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">可配广告</label>
								   <div class="col-sm-9">
								     <p class="form-control-static">
								     	<c:choose>
											<c:when test="${backUser.role.advertMax == -1 }">
												无限
											</c:when>
											<c:otherwise>
												${backUser.role.advertMax} 条
											</c:otherwise>
										</c:choose>
								     	
								     </p>
								   </div>
								</div>
								<div class="form-group">
								   <label class="col-sm-3 control-label">注册时间</label>
								   <div class="col-sm-9">
								     <p class="form-control-static"><fmt:formatDate value="${backUser.createTime }" type="BOTH"/></p>
								   </div>
								</div>
							</form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>最新消息</h5>
                    </div>
                    <div class="ibox-content text-center">
						<p class="form-control-static">功能内测中，敬请期待!</p>
						
                    </div>
                </div>

            </div>
        </div>
    </div>
    
    <div id="modal_changepwd" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">修改登录密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="input-password" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="new-password" placeholder="可输入数字、字母、常用字符(至少8位，限30位)" />
							</div>
						</div>
						<div class="form-group">
							<label for="input-password" class="col-sm-3 control-label">重复新密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="new-password-repair" placeholder="请再次输入新密码" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<!--验证信息-->
					<span id="tip_msg" class="glyphicon"></span>
					<button type="button" class="btn btn-primary" onclick="account.changePwd();">提&nbsp;&nbsp;交</button>
				</div>
			</div> <!-- /.modal-content -->
		</div> <!-- /.modal-dialog -->
	</div> <!-- /.modal -->
	
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/user.js"></script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>