$(function(){
	initMyChannel();
});

/**
 * 我的区域初始化
 * @returns
 */
function initMyChannel(){
	$.ajax({
		type : "post",
		url : rootPath + "channel/query/my",
		dataType : "json", //返回数据形式为json
		success : function(result) {
			if (result['success']) {
				var str = "";
				$.each(result['data'],function(index, item) {
					console.log(item);
					str += '<div class="row" id="row_' +item.channelId+ '">'
					+ '	<div class="col-sm-3 b-r">'
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
					+ '	<div class="col-sm-2 b-r">'
					+ '		<div class="text-center"> '
					+ '			<p class="text-logo"><b>' + item.countRouter + '</b> <small>台</small></p>'
					+ '		</div>'
					+ '		<div class="text-center">'
					+ '			<p>总设备数</p> '
					+ '		</div>'
					+ '	</div>'
					+ '	<div class="col-sm-2 b-r">'
					+ '		<div class="text-center"> '
					+ '			<p><b class="text-logo">' + item.onlineRouter + ' <small>台</small></b> <small>(在线)</small></p> '
					+ '		</div>'
					+ '		<div class="text-center"> '
					+ '			<p><b class="text-logo">' + (item.countRouter - item.onlineRouter) + ' <small>台</small></b> <small>(离线)</small></p>'
					+ '		</div>'
					+ '	</div>'
					+ '	<div class="col-sm-2 b-r">'
					+ '		<div class="text-center"> '
					+ '			<p class="text-logo"><b>' + item.connectPhone + '</b> <small>人</small></p>'
					+ '		</div>'
					+ '		<div class="text-center"> '
					+ '			<p>累计连接人数</p> '
					+ '		</div>'
					+ '	</div>'
					+ '	<div class="col-sm-2">'
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
					+ '		</div>'
					+ '		<div class="text-center"> '
					+ '			<button class="btn btn-link btn-sm" onclick="deleteChannel(\''+item.channelId +'\');">删除区域</button> '
					+ '		</div>'
					+ '	</div>'
					+ '</div> '
					+ '<hr/>';
				});
				$("#myChannelPannel").html(str);
			}else {
				$("#myChannelPannel").html('<div class="row">'
						+ '<div class="col-sm-12">'
						+ '<div class="form-group text-center">'
						+ '<h2>非常抱歉，您还没有创建区域！</h2>'
						+ '<span>您可以点击</span>'
						+ '<a class="btn btn-primary btn-sm" href="${rapp.rootPath }channel/index/add">'
						+ '<i class="fa fa-plus"></i> <span class="bold">创建区域</span>'
						+ '</a>'
						+ '</div>'
						+ '</div>'
						+ '</div>');
			}
		},
		error : function(errorMsg) {
			console.log(errorMsg);
		}
	});
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
