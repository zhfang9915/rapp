$(function() {
	initData('');
	
});

function bindingSubmit(){
	$('#binding-channel-modal').modal("hide");
	var devNo = $("#binding-devNo").val();
	$.ajax({
        type: "post",
        dataType: "json",
        url: rootPath + "router/binding/" + $("#binding-channelId").val() + "/" + devNo,
        success : function(result) {
			if (result['success']) {
				$('#binding_'+devNo).remove();
				common.open(result['data']);
			}else {
				common.alert(result['msg']);
			}
		}
    });
	
}


function bindingChannel(devNo){
	$('#binding-devNo').val(devNo);
	$('#binding-channelId').val("");
	$('#binding-channel-modal').modal({
		show:true,//显示弹出层
        backdrop:'static',//禁止位置关闭
        keyboard:false//关闭键盘事件
	});
}

function searchData(){
	var keywords = $("#search-keywords").val();
	initData(keywords);
}

function initData(keywords) {
	$.ajax({
		url : rootPath + "router/list/1",
		type : "post",
		data : {'key' : keywords},
		success : function(data) {
			if (data != null) {
				var pageCount = data.pageCount; //取到pageCount的值(把返回数据转成object类型)
				if(pageCount>0){
					eachData(data);
					options = {
							size:"large",
							bootstrapMajorVersion:3,
							currentPage: 1,
							numberOfPages: 6,
							totalPages:pageCount,
							itemTexts : function(type, page, current) {
								switch (type) {
								case "first":
									return "首页";
								case "prev":
									return "上一页";
								case "next":
									return "下一页";
								case "last":
									return "末页";
								case "page":
									return page;
								}
							},
							onPageClicked : function(event, originalEvent, type, page) {
								$.ajax({
									url: rootPath + "router/list/" + page,
									type: "post",
									data : {'key' : keywords},
									success: function (data) {
										if (data != null) {
											eachData(data);
										}
									}
								});
							}
					};
					var element = $('#bp-page-router');
					element.bootstrapPaginator(options);
					$("#data-content").show();
				}else{
					$("#nodata-content").show();
				}
			}
		}
	});
}

function eachData(data) {
	$("#channel_tbody").html("");
	var tbody = "";
	$.each(data.data,function(index, item) { //遍历返回的json
		var city = item.selectCity==null?"":item.selectCity;
		tbody += '<tr id="'+item.devNo+'">';
		if (item.state == 2){
			tbody += '<td class="project-status"><span class="label label-primary">已启用</span></td>';
		}else if (item.state == 1){
			tbody += '<td class="project-status"><span class="label label-default">未激活</span></td>';
		}else {
			tbody += '<td class="project-status"><span class="label label-danger">欠费停机</span></td>';
		}
		tbody += '<td class="project-title">'
			+ '<a href="'+rootPath+'router/detail/'+item.devNo+'" class="btn btn-link">'+ item.devName +'</a>'
			+ '<br/>'
			+ '<small>注册于 '+item.createTime+'</small></td>'
			+ '<td class="project-completion">'
			+ '<small>MAC地址</small><div>'+item.mac+'</div></td>'
			+ '<td class="project-completion"><small>安装地址</small><div>'+city+' '+item.installAddress+'</div></td>';
			+ '<td class="project-completion">';
		if (item.channel){
			tbody += '<small>所属渠道</small><div>'+item.channel.channelNameCh+'</div>';
		}
		tbody += '</td><td class="project-actions">';
		if (!item.channel){
			tbody += '<button id="binding_'+item.devNo+'" class="btn btn-warning btn-sm" onclick="bindingChannel(\''+item.devNo +'\');"> 渠道关联 </button>';
		}	
			
		tbody += '<a href="'+rootPath+'router/detail/'+item.devNo+'" class="btn btn-info btn-sm" target="_blank">	 详情 </a>';
		tbody += '<button class="btn btn-danger btn-sm" onclick="deleteRouter(\''+item.devNo +'\');"> 删除 </button>';
		tbody +=  '</td></tr>';
	});
	$("#channel_tbody").html(tbody);
	$(window).resize();
}

function deleteRouter(devNo){
	layer.confirm('删除后将无法恢复，确定删除吗？', {
	    btn: ['确定删除','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		parent.layer.closeAll('dialog');
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: rootPath + "router/delete/" + devNo,
	        success : function(result) {
				if (result['success']) {
					$('#'+devNo).remove();
					common.open(result['data']);
				}else {
					common.alert(result['msg']);
				}
			}
	    });
	}, function(){});
}