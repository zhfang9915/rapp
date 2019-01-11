$(function() {
	initData('');
	
	$("#data_body").on("click",".contact-box",function(){
		$(this).find(".icon").toggleClass("on");
	});
	
	$("#checkAll").click(function(){
		$("#data_body").find(".icon").addClass("on");
	});
	
	$("#checkNull").click(function(){
		$("#data_body").find(".icon").removeClass("on");
	});
	
	$("#unbindSubmit").click(function(){
		var routerIDs='';  
		routerIDs=$('#data_body .icon.on input[name=checkbox-routerId]').map(function(){return $(this).val();}).get();  
		if (routerIDs.length != '') {
			layer.confirm('确定从该渠道中解除绑定所选设备吗？', {
			    btn: ['确认解除','取消'], //按钮
			    shade: false //不显示遮罩
			}, function(){
				layer.closeAll('dialog');
				$.ajax({
			        type: "post",     //提交方式
			        contentType: "application/json; charset=utf-8",   //内容类型
			        dataType: "json",     //类型
			        url: rootPath + 'router/unbind/'+$("#hidden-channelId").val(),    //提交的页面，方法名
			        data: JSON.stringify(routerIDs),    //参数
			        success : function(result) {
						if (result['success']) {
							var keywords = $("#search-keywords").val();
							initData(keywords);
							common.open(result['data']);
						}else {
							common.alert(result['msg']);
						}
					}
			    });
			}, function(){});
		}else{
			common.alert('您还没有选择任何设备，请选择设备后操作！');
		}
	});
});

function searchData(){
	var keywords = $("#search-keywords").val();
	eachData(keywords, 1);
}

function initData(keywords) {
	$.ajax({
		url : rootPath + "router/binding/list/1",
		type : "post",
		data : {'key' : keywords, 'channelId':$("#hidden-channelId").val()},
		success : function(data) {
			if (data['data'].length != 0) {
				eachData(data, 1);
				var pageCount = data.pageCount; //取到pageCount的值(把返回数据转成object类型)
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
						if(options.currentPage != page){
							eachData(keywords, page);
						}
					}
				};
				var element = $('#bp-page-router');
				element.bootstrapPaginator(options);
				$("#search-router").show();
				$("#binded-edit").show();
   			}else{
   				$("#no-router").show();
   				$("#data_body").html("");
   				$("#search-router").hide();
				$("#binded-edit").hide();
				$('#bp-page-router').hide();
   			}
			
		}
	});
}

function eachData(keywords,page) {
	$("#data_body").html("");
	$.ajax({
		  url: rootPath + "router/binding/list/" + page,
		  type: "post",
		  data : {'key' : keywords, 'channelId':$("#hidden-channelId").val()},
		  success: function (data) {
			   if (data != null) {
				   var tbody = "";
					$.each(data.data,function(index, item) { //遍历返回的json
						tbody += '<div class="col-sm-4">'
			                + '<div class="contact-box" style="cursor:pointer;">'
			                + '<div class="col-sm-12">'
			                + '<h3><strong>'+item.devName+'</strong>';
				            if(item.state==1){
				            	tbody += ' <span class="label label-warning pull-right">未激活</span>';
				            }else if (item.state == 2) {
				            	tbody += ' <span class="label label-success pull-right">正常</span>';
							}else if (item.state == 3) {
								tbody += ' <span class="label label-danger pull-right">欠费停机</span>';
							}else {
								tbody += ' <span class="label pull-right">无效</span>';
							}
			            	tbody += '</h3><p><i class="fa fa-map-marker"></i>'+item.installAddress+'</p>'
			                + '<address><strong>mac：'+item.mac+'</strong><br>注册于：'+item.createTime
			                + '<a href="'+rootPath+'router/detail/'+item.devNo+'" class="btn btn-link btn-xs pull-right" target="_blank">设备详情</a>'
			                +'</address>'
			                + '</div>'
			                + '<div class="clearfix"></div>'
			                + '<div class="icon" style="position: absolute;bottom: 20px; right: 16px;">'
			                + '<input name="checkbox-routerId" type="hidden" value="'+item.devNo+'">'
			                + '<img style="display: block;" src="'+rootPath+'img/gou.png" width="50px"/></div>'
			                + '</div>'
			                + '</div>'
			                + '</div>';
					});
					$("#data_body").html(tbody);
					$(window).resize();
			   }
	  		}
  	});
	
}