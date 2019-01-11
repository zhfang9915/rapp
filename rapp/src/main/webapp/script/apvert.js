var advert = {
		
		URL : {
			configAdvert : function (){
				return rootPath + "apvert/config";
			},
			uploadAdvertFileUrl : function (){
				return rootPath + "apvert/upload";
			},
			listAdvert : function () {
				return rootPath + "apvert/table";
			},
			listUnCheckAdvert : function () {
				return rootPath + "apvert/uncheck/list";
			},
			detailAdvert : function (id){
				return rootPath + "apvert/detail/" + id;
			},
			deleteAdvert : function () {
				return rootPath + "apvert/delete";
			},
			deleteAdvertByIdURL : function (id) {
				return rootPath + "apvert/delete/" + id;
			},
			showTemplate : function (id) {
				return rootPath + "apvert/temp/show/"+id;
			},
			checkAdvert : function (id) {
				return rootPath + "apvert/check/" + id;
			},
			enableOrDisable : function (id, method){
				return rootPath + "apvert/"+method+"/" + id;
			}
			
			
		},
		
		
		//打开上传文件modal
		openUploadModal : function (id) {
			$("#res-id").val(id);
			$('#upload_modal').modal({
				show:true,//显示弹出层
                backdrop:'static',//禁止位置关闭
                keyboard:false//关闭键盘事件
			});
		},
		
		//上传文件
		uploadAdvertFile : function () {
			var id = $("#res-id").val();
			$("#uploadForm").ajaxSubmit({  
		        url:advert.URL.uploadAdvertFileUrl(),
		        resetForm: true,  
		        dataType:  'json',
		        beforeSubmit: function (formData, jqForm, options){
		        	$("#btn-upload").addClass('disabled');
		        }, 
		        success:function (result){
		        	if (result['success']) {
		        		$('#upload_modal').modal("hide");
						$("#advertUrl" + id).val(result['data']);
		        	}else{
		        		$('#err_msg').hide().html('<label class="label label-danger">'+result['msg']+'</label>').show(300);
		        	}
		        	$("#btn-upload").removeClass('disabled');
		        },
		        complete:function(XMLHttpRequest, textStatus){
		        	$("#btn-upload").removeClass('disabled');
		        },
		        error: function(){
		        	$("#btn-upload").removeClass('disabled');
		        }
		    }); 
			return false;
		},
		
		tableInit : function() {
			$('#table_adverts').bootstrapTable({
				url : advert.URL.listAdvert(), // 请求后台的URL（*）
				method : 'post', // 请求方式（*）
				dataType: "json", 
				toolbar : '#toolbar', // 工具按钮用哪个容器
				striped : true, // 是否显示行间隔色
				cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, // 是否显示分页（*）
				queryParams : advert.queryParams,// 传递参数（*）
				queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求  
				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pageSize : 10, // 每页的记录行数（*）
				pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
				showColumns : true, // 是否显示所有的列
				showRefresh : true, // 是否显示刷新按钮
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				minimumCountColumns : 3, // 最少允许的列数
				idField : "advertId", // 主键列
				uniqueId : "advertId", // 每一行的唯一标识，一般为主键列
				onAll : common.changeBtnStyle,	//所有事件都触发toolbar样式控制
				detailView : true,
				detailFormatter : advert.viewTemplate,
				columns : [ {
	                checkbox: true,
	                align: 'center',
	                valign: 'middle'
				}, {
					field : 'advertId',
					title : '广告编码'
				}, {
					field : 'advertName',
					title : '广告名称'
				}, {
					field : 'weight',
					align: 'center',
					title : '推送权重'
				}, {
					field : 'state',
					align: 'center',
					title : '状态',
					formatter : function(value, row, index){
						if (row.state == 0) {
							return '<span class="text-danger"><strong>禁用</strong></span>';
						}else if (row.state == 1) {
							return '<span class="text-primary"><strong>待审核</strong></span>';
						}else if (row.state == 2) {
							return '<span class="text-info"><strong>审核通过</strong></span>';
						}else if (row.state == 3) {
							return '<span class="text-warning"><strong>审核未通过</strong></span>';
						}else if (row.state == 4) {
							return '<span class="text-success"><strong>启用</strong></span>';
						}else {
							return "未定义";
						}
					}
				}, {
					field : 'temp.tempName',
					align: 'center',
					title : '广告模版'
				}, {
					field : 'createBy',
					title : '创建人'
				}, {
					field : 'createTime',
					align: 'center',
					title : '创建时间'
				}, {
					title : '操作',
					align: 'center',
					formatter : function(value, row, index){
						var stateButton;
						if (row.state==0 || row.state==2){
							stateButton = '<button type="button" class="btn btn-success btn-sm" onclick="advert.enableOrDisable4Table(\''+row.advertId+'\',\'enable\');">启用</button> ';
						}else if (row.state==4){
							stateButton = '<button type="button" class="btn btn-danger btn-sm" onclick="advert.enableOrDisable4Table(\''+row.advertId+'\',\'disable\');">禁用</button> ';
						}
						return [
							stateButton,
				            '<a href="'+advert.URL.detailAdvert(row.advertId)+'"><button type="button" class="btn btn-default btn-sm">详情</button></a>'
				        ].join('');
					}
				} ]
			});
		},
		
		queryParams : function(params) {
			var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				limit : params.limit, // 页面大小
				offset : params.offset, // 页码
				advertId : $("#search-apvert-id").val(),
				channelId : $("#search-channel-id").val(),
				advertName : $("#search-apvert-name").val(),
				createBy : $("#search-channel-createBy").val(),
				state : $("#search-apvert-state option:selected").val()
			};
			return temp;
		},
		
		/*根据条件查询数据*/
		queryAdvert : function() {
			$('#table_adverts').bootstrapTable("refresh");
		},
		
		showChannelName : function(datas) {
			var result;
			datas.forEach(function(value, index, arr) {
				result += '<p class="form-control-static">' + arr[index].channelNameCh + '</p>';
			});
			return result;
		},
		
		deleteAdvert : function() {
			var arr = $('#table_adverts').bootstrapTable('getSelections');
			if (arr.length>0) {
				parent.layer.confirm('删除后将无法恢复，确定删除吗？', {
				    btn: ['确定删除','取消'], //按钮
				    shade: false //不显示遮罩
				}, function(){
					parent.layer.closeAll('dialog');
					$.ajax({
				        type: "post",     //提交方式
				        contentType: "application/json; charset=utf-8",   //内容类型
				        dataType: "json",     //类型
				        url: advert.URL.deleteAdvert(),    //提交的页面，方法名
				        data: JSON.stringify(arr),    //参数
				        success : function(result) {
							if (result['success']) {
								$('#table_adverts').bootstrapTable("refresh");
								common.alert(result['data']);
							}else {
								common.alert(result['msg']);
							}
						}
				    });
				}, function(){});
			}
			return false;
		},
		
		deleteAdvertById : function (id) {
			parent.layer.confirm('删除后将无法恢复，确定删除吗？', {
			    btn: ['确定删除','取消'], //按钮
			    shade: false //不显示遮罩
			}, function(){
				parent.layer.closeAll('dialog');
				$.ajax({
			        type: "post",     //提交方式
			        dataType: "json",     //类型
			        url: advert.URL.deleteAdvertByIdURL(id),    //提交的页面，方法名
			        success : function(result) {
						if (result['success']) {
							$('#aps-'+id).remove();
							$('#row-'+id).remove();
							common.open(result['data']);
						}else {
							common.alert(result['msg']);
						}
					}
			    });
			}, function(){});
		},
		
		//预览
		showTemplate : function (id) {
			var $advertUrls = $("input[name='advertUrl']");
			var temp = 0;
			for(i=0;i<$advertUrls.length;i++){
		        var $advertUrl = $advertUrls[i];
		        if (!$($advertUrl).val()){
		        	temp++; 
		        }
		    }
			if(temp == $advertUrls.length){
				common.alert('请至少配置一个广告！');
				return;
			}
			//生成模版
			$.ajax({
		        type: "post",
		        data : $("#configAdvertForm").serialize(),
		        url: advert.URL.showTemplate(id),
		        success : function(result) {
					if (result['success']) {
						$("#div_template").html(result['data']);
					}else {
						common.alert("生成预览失败，请检查配置并重试");
					}
				}
		    });
			
		},
		
		
		checkTableInit : function (){
			$('#table_unCheckAdverts').bootstrapTable({
				url : advert.URL.listUnCheckAdvert(), // 请求后台的URL（*）
				method : 'get', // 请求方式（*）
				striped : true, // 是否显示行间隔色
				cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, // 是否显示分页（*）
				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pageSize : 10, // 每页的记录行数（*）
				pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
				showColumns : true, // 是否显示所有的列
				showRefresh : true, // 是否显示刷新按钮
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				minimumCountColumns : 3, // 最少允许的列数
				idField : "advertId", // 主键列
				uniqueId : "advertId", // 每一行的唯一标识，一般为主键列
				detailView : true,
				detailFormatter : advert.viewTemplate,
				columns : [ {
	                checkbox: true,
	                align: 'center',
	                valign: 'middle'
				}, {
					field : 'advertName',
					title : '广告名称'
				}, {
					field : 'temp.tempName',
					align: 'center',
					title : '广告模版'
				}, 
				{
					field : 'createBy',
					align: 'center',
					title : '创建人'
				}, {
					field : 'createTime',
					align: 'center',
					title : '创建时间'
				}, {
					title : '审核',
					align: 'center',
					formatter : function(value, row, index){
						return [
				            '<button type="button" class="btn btn-success btn-sm" onclick="advert.checkAdvert(\''+row.advertId+'\',\''+row.advertName+'\', 2,\''+row.createBy+'\');">',
								'<span class="glyphicon glyphicon-ok"></span></button> ',
				            '<button type="button" class="btn btn-danger btn-sm" onclick="advert.checkAdvert(\''+row.advertId+'\',\''+row.advertName+'\', 3,\''+row.createBy+'\');">',
								'<span class="glyphicon glyphicon-remove"></span></button>'
				        ].join('');
					}
				} ]
			});
		},
		
		viewTemplate : function (index, row) {
			var html = '<form class="form-horizontal">' +
							'<div class="col-md-12">' +
						        '<div class="form-group">' +
						            '<div class="carousel slide" id="carousel'+row.advertId+'">' +
										'<div class="carousel-inner">';
											$.each(row.items, function(index, item) {
												console.log(index)
												console.log(item.advertId)
												html += '<div class="item ';
												if(index==0){
													html+='active';
												}
												html += '"><a href="'+item.toUrl+'" target="_blank">' +
															'<img class="img-responsive" src="'+item.advertUrl+'">' +
														'</a>' +
													'</div>';
											});
								html += '</div>' +
										'<a data-slide="prev" href="#carousel'+row.advertId+'" class="left carousel-control">' +
											'<span class="icon-prev"></span>' +
										'</a>' +
										'<a data-slide="next" href="#carousel'+row.advertId+'" class="right carousel-control">' +
											'<span class="icon-next"></span>' +
										'</a>' +
									'</div>' +
						        '</div>' +
						    '</div>' +
						'</form>';
			return html;
		},
		
		checkAdvert : function (id, name, state, createBy){
			var data = {};
			data.advertId = id;
			data.advertName = name;
			data.state = state;
			data.createBy = createBy;
			if (state == 3){
				layer.prompt({
				    title: '请说明不通过的原因，并确认',
				    formType: 2 //prompt风格，支持0-2
				}, function(text){
					data.text = text;
					advert.checkAdvertSubmit(data);
				});
			}else {
				advert.checkAdvertSubmit(data);
			}
			
		},
		
		checkAdvertSubmit : function (data){
			$.ajax({
		        type: "post",
		        data : data,
		        url: advert.URL.checkAdvert(data.advertId),
		        success : function(result) {
					if (result['success']) {
						common.alert(result['data']);
						$('#table_unCheckAdverts').bootstrapTable("refresh");
					}else {
						common.alert(result['msg']);
					}
				}
		    });
		},
		
		
		enableOrDisable : function (advertId, method){
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
					url: advert.URL.enableOrDisable(advertId, method),
					success : function(result) {
						if (result['success']) {
							window.location.reload();
						}else {
							common.alert(result['msg']);
						}
					}
				});
			}, function(){});
		},
		
		enableOrDisable4Table : function (advertId, method){
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
					url: advert.URL.enableOrDisable(advertId, method),
					success : function(result) {
						if (result['success']) {
							$('#table_adverts').bootstrapTable("refresh");
						}else {
							common.alert(result['msg']);
						}
					}
				});
			}, function(){});
		},
		
		addAdvertRes : function(){
			var count = $("input[name='advertUrl']").length;
			if(count >= 4){
				$(".add-advert-res").hide();
			}
			var res_id = 1;
			for(var i=1; i<=5; i++){
				if($(".res-"+i).length == 0){
					res_id = i;
					break;
				}
			}
			var res = '<div class="col-sm-11 res-'+res_id+'">'
			    		+ '<div class="form-group">'
			    		+ '<label class="col-sm-2 control-label">资源'+res_id+'：</label>'
			    		+ '<div class="col-sm-10">'
			    		+ '<div class="input-group">'
			    		+ '<span class="input-group-btn">'
			    		+ '<button type="button" class="btn btn-info" onclick="advert.openUploadModal('+res_id+');">'
			    		+ '本地上传'
			    		+ '</button>'
			    		+ '</span>'
			    		+ '<input type="text" id="advertUrl'+res_id+'" name="advertUrl" class="form-control" placeholder="必填:请输入广告图片或视频链接" onclick="advert.removeErrorClass(\'advertUrl'+res_id+'\');">'
			    		+ '</div>'
			    		+ '<span class="help-block m-b-none"></span>'
			    		+ '</div>'
			    		+ '</div>'
			    		+ '<div class="form-group">'
			    		+ '<label class="col-sm-2 control-label">链接'+res_id+'：</label>'
			    		+ '<div class="col-sm-10">'
			    		+ '<input type="text" id="toUrl'+res_id+'" name="toUrl" class="form-control" placeholder="选填:请输入广告点击后跳转的链接"> '
			    		+ '<span class="help-block m-b-none"></span>'
			    		+ '</div>'
			    		+ '</div>'
			    		+ '</div>'
			    		+ '<div class="col-sm-1 res-'+res_id+' text-center">'
			    		+ '<button type="button" class="btn btn-default btn-sm" onclick="advert.removeAdvertRes('+res_id+');" style="margin-top: 30px;">'
			    		+ '<span class="glyphicon glyphicon-minus"></span>'
			    		+ '</button>'
			    		+ '</div>';
			$("#advertRes").append(res);
		},
		
		removeAdvertRes : function (id){
			var count = $("input[name='advertUrl']").length;
			if(count == 1){
				common.alert('不能删除，至少配置一个广告！');
				return;
			}
			if(count <= 5){
				$(".add-advert-res").show();
			}
			$(".res-" + id).remove();
		},
		
		submitConfig : function(){
			var $advertUrls = $("input[name='advertUrl']");
			for(i=0;i<$advertUrls.length;i++){
		        var $advertUrl = $advertUrls[i];
		        if (!$($advertUrl).val()){
		        	common.alert('请配置广告资源！');
		        	$($advertUrl).parent().addClass("has-error");
					return;
		        }
		    }
			if(!$("#input-agree").is(':checked')){
				common.alert('请阅读并同意法律协议！');
				return;
			}
			
			$("#configAdvertForm").submit();
			
		},
		
		removeErrorClass : function(id){
			$("#"+id).parent().removeClass("has-error");
		}
		
		
	    

}