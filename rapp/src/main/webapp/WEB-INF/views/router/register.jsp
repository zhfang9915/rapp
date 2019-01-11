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
	<link href="${rapp.nginx }css/bootstrap.min14ed.css" rel="stylesheet">
	<link href="${rapp.nginx }css/font-awesome.min93e3.css" rel="stylesheet">
	<link href="${rapp.nginx }css/bootstrap-select.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${rapp.nginx }css/bootstrap-chinese-region.css">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
	<title>设备注册</title>
	<%@include file="../common/notop.jsp"%>
	<script src="${rapp.nginx }js/jquery.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7a6QKaIilZftIMmKGAFLG7QT1GLfIncg"></script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>设备注册</h1>
			<p>让您的设备商业化</p>
			<p><a class="btn btn-primary btn-lg" href="${rapp.nginx }" role="button">去官网</a>
			</p>
		</div>
	</div>
	<div class="container-fluid">
		<c:choose>
			<c:when test="${result.success == true }">
				<div class="col-md-12">
					<form id="registerRouterForm" class="form-horizontal" action="${rapp.rootPath }router/register/submit" 
						method="post" autocomplete="off">
						<input type="hidden" name="devNo" value="${result.data.devNo }">
						<input type="hidden" name="mac" value="${result.data.mac }">
						<input type="hidden" name="fwId" value="${result.data.fwId }">
						<input type="hidden" name="backUrl" value="${result.data.backUrl }">
					    <div class="form-group">
					        <label class="col-sm-2 control-label">所属渠道：</label>
					        <div class="col-sm-10">
								<c:choose>
									<c:when test="${channels.success }">
							            <select name="channelId" class="selectpicker show-menu-arrow" title="选择所属渠道" data-size="6" >
											<c:forEach var="channel" items="${channels.data}">
										  		<option value="${channel.channelId }">${channel.channelNameCh }</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<p class="form-control-static">您还没有渠道，可在创建渠道后分配设备所属渠道</p>
									</c:otherwise>
								</c:choose>
					            <span class="help-block m-b-none"></span>
					        </div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-2 control-label">设备名称：</label>
					        <div class="col-sm-8">
								<input name="devName" class="form-control" placeholder="选填：请输入设备名称(限50字)"></input>
								<span class="help-block m-b-none"></span>
					        </div>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-2 control-label">安装地址：</label>
					        <div class="col-sm-3">
								<div class="bs-chinese-region flat dropdown" data-min-level="1" data-max-level="3" data-def-val="[name=address]">
									<input type="text" class="form-control" id="address" name="selectCity" placeholder="请选择地区" data-toggle="dropdown" readonly="">
									<input type="hidden" class="form-control" name="address">
									<div class="dropdown-menu" role="menu" aria-labelledby="dLabel">
										<div>
											<ul class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active"><a href="#province" data-next="city" role="tab" data-toggle="tab">省份</a></li>
												<li role="presentation"><a href="#city" data-next="district" role="tab" data-toggle="tab">城市</a></li>
												<li role="presentation"><a href="#district" data-next="street" role="tab" data-toggle="tab">县区</a></li>
											</ul>
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="province">--</div>
												<div role="tabpanel" class="tab-pane" id="city">--</div>
												<div role="tabpanel" class="tab-pane" id="district">--</div>
											</div>
										</div>
									</div>
								</div>
					        </div>
					        <div class="col-sm-5">
								<input name="installAddress" class="form-control" placeholder="必填：请输入街道地址"></input>
								<input type="hidden" class="form-control" id="lng" name="lng">
								<input type="hidden" class="form-control" id="lat" name="lat">
								<span class="help-block m-b-none"></span>
					        </div>
					        <div class="col-sm-2"></div>
					        <span class="help-block m-b-none"></span>
					    </div>
<!-- 					    <div class="form-group"> -->
<!-- 					        <label class="col-sm-2 control-label"></label> -->
<!-- 					        <div class="col-sm-8"> -->
<!-- 								<input name="installAddress" class="form-control" placeholder="必填：请输入街道地址"></input> -->
<!-- 								<input type="text" class="form-control" id="lnglat"> -->
<!-- 								<span class="help-block m-b-none"></span> -->
<!-- 					        </div> -->
<!-- 					        <div class="col-sm-2"></div> -->
<!-- 					    </div> -->
					    <div class="form-group">
				        	<label class="col-sm-2 control-label"></label>
					        <div class="col-sm-8">
								<div id="container" style="width: 100%; height:300px; border: 1px solid gray;">
								</div>
					        </div>
					        <div class="col-sm-2"></div>
					        <span class="help-block m-b-none"></span>
					    </div>
					    <div class="form-group">
					        <label class="col-sm-2 control-label">备注：</label>
					        <div class="col-sm-8">
								<textarea name="remark" class="form-control" rows="3" placeholder="选填：请输入备注说明"></textarea>
								<span class="help-block m-b-none"></span>
					        </div>
					        <div class="col-sm-2"></div>
					    </div>
					    <div class="form-group">
						    <label class="col-sm-2 control-label"></label>
					        <div class="col-sm-10">
					            <button class="btn btn-primary" type="submit">确认注册</button>
					        </div>
					    </div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<form class="form-horizontal">
					<div class="col-md-12">
					    <h3><span class="text-center text-danger">${result.msg }</span></h3>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	
	<script src="${rapp.nginx }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/baidu.map.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/bootstrap-chinese-region.js"></script>
	<script type="text/javascript" src="${rapp.nginx }js/layer.min.js"></script>
	<script type="text/javascript" src="${rapp.nginx }script/common.js"></script>
<%-- 	<script type="text/javascript" src="${rapp.nginx }script/router.js"></script> --%>
	<script type="text/javascript">
		$(function(){
			$.getJSON(rootPath + 'js/sql_areas.json',function(data){
				for (var i = 0; i < data.length; i++) {
					var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
					data[i] = area;
				}
				$('.bs-chinese-region').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
					$(this).find('[name=address]').val(areas[areas.length-1].id);
				});
			});
		});
	</script>
	
</body>
</html>