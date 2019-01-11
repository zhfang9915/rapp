$(function() {
	initData('');
});

function searchData(){
	var keywords = $("#search-keywords").val();
	initData(keywords);
}

function initData(keywords) {
	$.ajax({
		url : rootPath + "channel/list/1",
		type : "post",
		data : {'key' : keywords},
		success : function(data) {
			if (data != null) {
				var pageCount = data.pageCount; //取到pageCount的值(把返回数据转成object类型)
				if (pageCount > 0) {
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
									url: rootPath + "channel/list/" + page,
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
					var element = $('#bp-page-channel');
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
	var tbody = "<br/>";
	$.each(data.data,function(index, item) { //遍历返回的json
//		tbody += '<tr id="'+item.channelId+'">';
//			if (item.state == 1){
//				tbody += '<td class="project-status"><span class="label label-primary">已启用</td>';
//			}else {
//				tbody += '<td class="project-status"><span class="label label-default">已停用</td>';
//			}
//		tbody += '<td class="project-title">'
//			+ '<a href="'+rootPath+'channel/detail/'+item.channelId+'">'+ item.channelNameCh +'</a>'
//			+ '<br/>'
//			+ '<small>创建于 '+item.createTime+'</small>'
//			+ '</td>'
//			+ '<td class="project-actions">';
//		tbody += '<button class="btn btn-danger btn-sm" onclick="deleteChannel(\''+item.channelId +'\');"> 删除 </button>';
//		tbody += '<a href="'+rootPath+'channel/detail/'+item.channelId+'" class="btn btn-info btn-sm" target="_blank"><i class="fa fa-folder"></i> 详情 </a>'
//			+ '<a href="'+rootPath+'channel/index/update/'+item.channelId+'" class="btn btn-primary btn-sm" target="_blank"><i class="fa fa-folder"></i> 修改 </a>'
//			+ '<a href="'+rootPath+'router/binding/'+item.channelId+'" class="btn btn-warning btn-sm" target="_blank"><i class="fa fa-pencil"></i> 设备绑定 </a>'
//			+ '</td>'
//			+ '</tr>';
		tbody += '<div class="row" id="row_' +item.channelId+ '">'
		+ '	<div class="col-sm-3 b-r" onclick="window.open(\''+rootPath+'channel/detail/'+item.channelId+'\')" style="cursor:pointer">'
		+ '		<div class="form-group">'
		+ '			<div class="col-md-12"> '
		+ '				<p><b>' + item.channelName + '</b></p>'
		+ '			</div>'
		+ '		</div>'
		+ '		<div class="form-group">'
		+ '			<div class="col-md-12"> '
		+ '				<small>创建于</small> <time>' + item.createTime + '</time>'
		+ '			</div>'
		+ '		</div>'
		+ '	</div>'
		+ '	<div class="col-sm-2 b-r" onclick="window.open(\''+rootPath+'channel/detail/'+item.channelId+'\')" style="cursor:pointer">'
		+ '		<div class="text-center"> '
		+ '			<p class="text-logo"><b>' + item.countRouter + '</b> <small>台</small></p>'
		+ '		</div>'
		+ '		<div class="text-center">'
		+ '			<p>总设备数</p> '
		+ '		</div>'
		+ '	</div>'
		+ '	<div class="col-sm-2 b-r" onclick="window.open(\''+rootPath+'channel/detail/'+item.channelId+'\')" style="cursor:pointer">'
		+ '		<div class="text-center"> '
		+ '			<p><b class="text-logo">' + item.onlineRouter + ' <small>台</small></b> <small>(在线)</small></p> '
		+ '		</div>'
		+ '		<div class="text-center"> '
		+ '			<p><b class="text-logo">' + (item.countRouter - item.onlineRouter) + ' <small>台</small></b> <small>(离线)</small></p>'
		+ '		</div>'
		+ '	</div>'
		+ '	<div class="col-sm-2 b-r" onclick="window.open(\''+rootPath+'channel/detail/'+item.channelId+'\')" style="cursor:pointer">'
		+ '		<div class="text-center"> '
		+ '			<p class="text-logo"><b>' + item.connectPhone + '</b> <small>人</small></p>'
		+ '		</div>'
		+ '		<div class="text-center"> '
		+ '			<p>累计连接人数</p> '
		+ '		</div>'
		+ '	</div>'
		+ '	<div class="col-sm-2" onclick="window.open(\''+rootPath+'channel/detail/'+item.channelId+'\')" style="cursor:pointer">'
		+ '		<div class="text-center"> '
		+ '			<p class="text-logo"><b>' + item.countPush + '</b> <small>次</small></p>'
		+ '		</div>'
		+ '		<div class="text-center"> '
		+ '			<p>广告累计显示量</p> '
		+ '		</div>'
		+ '	</div>'
		+ '	<div class="col-sm-1">'
		+ '		<div class="text-center"> '
		+ '			<a class="btn btn-link btn-sm" href="'+rootPath+'router/binding/'+item.channelId+'">管理设备</a> '
		+ '			<a class="btn btn-link btn-sm" href="'+rootPath+'channel/index/update/'+item.channelId+'">编辑区域</a> '
		+ '			<button class="btn btn-link btn-sm" onclick="deleteChannel(\''+item.channelId +'\');">删除区域</button> '
		+ '		</div>'
		+ '	</div>'
		+ '</div> '
		+ '<hr/>';
	});
	$("#channel_tbody").html(tbody);
	$(window).resize();
}


function deleteChannel(channelId){
	layer.confirm('删除后将无法恢复，确定删除吗？', {
	    btn: ['确定删除','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		parent.layer.closeAll('dialog');
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: rootPath + "channel/delete/" + channelId,
	        success : function(result) {
	        	console.log(result);
				if (result['success']) {
					$('#row_'+channelId).remove();
					common.open(result['data']);
				}else {
					common.alert(result['msg']);
				}
			}
	    });
	}, function(){});
}