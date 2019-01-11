function bindingChannel(devNo){
	$('#binding-devNo').val(devNo);
	$('#binding-channelId').val("");
	$('#binding-channel-modal').modal({
		show:true,//显示弹出层
        backdrop:'static',//禁止位置关闭
        keyboard:false//关闭键盘事件
	});
}

function bindingSubmit(){
	$('#binding-channel-modal').modal("hide");
	var devNo = $("#binding-devNo").val();
	$.ajax({
        type: "post",
        dataType: "json",
        url: rootPath + "router/binding/" + $("#binding-channelId").val() + "/" + devNo,
        success : function(result) {
			if (result['success']) {
				window.location.reload();
				common.open(result['data']);
			}else {
				common.alert(result['msg']);
			}
		}
    });
	
}


function removeBinding(cid, rid){
	layer.confirm('您确定将与该渠道接触绑定关系吗？', {
	    btn: ['确定解除','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		parent.layer.closeAll('dialog');
		$.ajax({
	        type: "post",
	        contentType: "application/json;charset=utf-8",   //内容类型
	        dataType: "json",
	        url: rootPath + "router/unbind/" + cid,
	        data: '["' + rid + '"]',    //参数
	        success : function(result) {
				if (result['success']) {
					window.location.reload();
					common.open(result['data']);
				}else {
					common.alert(result['msg']);
				}
			}
	    });
	}, function(){});
}
