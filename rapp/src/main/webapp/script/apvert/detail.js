$(function() {
	ds.pushAdvertByHours($('#advert-id').val());

	initData();
});

var currentPage = 1;
function initData() {
	var advertId = $("#advert-id").val();
	$.ajax({
		url : rootPath + "aplog/detail/"+advertId+"/"+currentPage,
		type : "post",
		success : function(data) {
			if (data != null) {
				eachData(data);
				currentPage ++;
				var pageCount = data.pageCount; //取到pageCount的值(把返回数据转成object类型)
				if(pageCount <= currentPage){
					$("#show-more").hide();
					$("#show-history").show();
				}else{
					$("#show-more").show();
				}
			}
		}
	});
}

function eachData(data) {
	var tbody = "";
	if(data.data.length == 0){
		tbody += '<div class="ibox-content m-b-sm border-bottom">'
			+ '<div class="text-center p-lg">'
			+ '<h2>未查询到近3天的推送记录</h2>'
			+ '<span>您可以点击下方按钮显示更多历史明细</span>'
			+ '</div>'
			+ '</div>';
	}else{
		$.each(data.data,function(index, item) { //遍历返回的json
			tbody += '<div class="feed-element">'
	                 + '<div class="media-body ">'
	                 + '<small class="pull-right text-navy">'+item.pushTime+'</small>'
	                 + '<strong><a href="'+rootPath+'router/detail/'+item.devNo+'" target="_blank">'+item.devName+'</a> </strong> '
	                 + '推送了此广告至 <strong>'+item.phoneMac+'</strong>.'
	                 + '<div style="padding-left: 25px;">'
	                 + '<small class="text-muted"><i class="fa fa-map-marker"></i> '+item.installAddress+'</small>'
	                 + '</div>'
	                 + '</div>'
	                 + '</div>';
		});
	}
	$("#pushData_tbody").append(tbody);
}

function enableOrDisable(advertId, method){
	var name = "启用";
	if ("disable"==method){
		name = "禁用";
	}
	parent.layer.confirm('确定'+name+'该广告配置吗？', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		parent.layer.closeAll('dialog');
		$.ajax({
			type: "post",
			url: rootPath + "apvert/"+method+"/" + advertId,
			success : function(result) {
				if (result['success']) {
					window.location.reload();
				}else {
					common.alert(result['msg']);
				}
			}
		});
	}, function(){});
}

function deleteAdvertById(id) {
	parent.layer.confirm('删除后将无法恢复，确定删除吗？', {
	    btn: ['确定删除','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		parent.layer.closeAll('dialog');
		$.ajax({
	        type: "post",     //提交方式
	        dataType: "json",     //类型
	        url: rootPath + "apvert/delete/" + id,    //提交的页面，方法名
	        success : function(result) {
				if (result['success']) {
					alert(result['data']);
					window.location.href=rootPath+"apvert/list";
				}else {
					common.alert(result['msg']);
				}
			}
	    });
	}, function(){});
}