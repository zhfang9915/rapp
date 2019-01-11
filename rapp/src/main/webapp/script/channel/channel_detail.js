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
					alert(result['data']);
					window.location.href=rootPath+"channel/list/index";
				}else {
					common.alert(result['msg']);
				}
			}
	    });
	}, function(){});
}