document.write("<script src='"+rootPath+"js/jquery.blockUI.js'></script>");
$(function(){
	// ajax处理未登录请求
	$.ajaxSetup({
		complete : function(XMLHttpRequest, textStatus) { 
			var sessionTimeOut = XMLHttpRequest.getResponseHeader("SessionTimeOut"); 
			// 通过XMLHttpRequest取得响应头，sessionTimeOut， 
			if (sessionTimeOut == "timeout") { 
				// 如果未登录就处理 ，指定要跳转的页面 
				window.location.replace(rootPath + "login/index"); 
				return false;
			}
			var Permission = XMLHttpRequest.getResponseHeader("Permission"); 
			// 通过XMLHttpRequest取得响应头，sessionTimeOut， 
			if (Permission == "NoPermission") { 
				// 如果未登录就处理 ，指定要跳转的页面 
				window.location.replace(rootPath + "401"); 
				return false;
			}
		}
	});
	
	$(document).ajaxStart(function(){
		common.blockUI();
    });
	$(document).ajaxStop($.unblockUI);
	
});

var common = {
		URL:{
	        randCode:function(){
	            return rootPath + "base/randcode?" + (new Date()).valueOf();
	        }
	    },
	    
	    //刷新验证码
		clickImage : function() {
			$(".img-randconde").attr("src", common.URL.randCode());
		},
	    
		//toolbar 工具栏样式控制
	    changeBtnStyle : function(row) {
			var checks = $('.grid-table').bootstrapTable('getSelections');
			if (checks.length==0) {
				$("#btn-edit").addClass("disabled");
				$("#btn-del").addClass("disabled");
			}else if (checks.length==1) {
				$("#btn-edit").removeClass("disabled");
				$("#btn-del").removeClass("disabled");
			}else {
				$("#btn-edit").addClass("disabled");
				$("#btn-del").removeClass("disabled");
			}
	    },
	    
	    codeSelectInit : function () {
	    	$("#select-jscode").html('');
            $.ajax({  
                url: rootPath + "code/select",
                type: "post",
                dataType: "json",
                success: function (result) {
                	if(result['success']){
                        $.each(result['data'], function (index, code) {  
                            $("#select-jscode").append("<option value="+code.codeId+">" + code.codeName + "</option>");  
                        });  
                	}else{
                		$("#select-jscode").append("<option disabled>"+result['msg']+"</option>"); 
                	}
                },  
                error: function (XMLHttpRequest, textStatus, errorThrown) {  
                	$("#select-jscode").append("<option disabled>数据加载失败...</option>");  
                }  
            });
	    },
	    
	    alert : function (msg) {
	    	layer.alert(msg, {
			    shade: false //不显示遮罩
			});
	    },
	    
	    open : function (msg) {
	    	layer.open({
	    	  type: 1,
	    	  offset: 'rb',
	    	  time:3000,
	    	  content: '<div style="padding: 20px 80px;">'+msg+'</div>',
	    	  btn: '知道了',
	    	  btnAlign: 'c', //按钮居中
	    	  shade: 0, //不显示遮罩
	    	  yes: function(){
	    	    layer.closeAll();
	    	  }
	    	});
	    },
	    
	    blockUI : function(){
	    	$.blockUI({
	        	message:'<h4><img src="' + rootPath + 'img/loading.gif" /><br/><br/>正在处理，请稍候...</h4>',
	            overlayCSS: {
	                opacity:"0"
	            },
	            css: { 
	            	border: 'none', 
	            	backgroundColor:'none'
	            }
	        });
	    }
	    
};