$(function(){
	initMap();
	
	$('#channelTree').jstree({
		'core' : {
			'data' : {
				'url' : rootPath + 'channel/query/tree', // 底层封装url
				'type' : 'post',
				'data' : function(node) {
					return {
						'parentId' : node.id
					};
				}
			}
		},
		'plugins' : [ 'wholerow' ]
	}).bind("activate_node.jstree", function(obj, e) {
		var node = e.node;
		if("#" == node.parent){
			addMarker(1, node.id);
		}else if ("c_001" == node.parent) {
			addMarker(2, node.id);
		}else{
			addMarker(3, node.id);
		}
	});
	
	
});

function initMap(){
	var geolocation = new BMap.Geolocation();  
	var map = new BMap.Map("container");
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({ 
		isOpen: true, 
		anchor: BMAP_ANCHOR_BOTTOM_RIGHT 
	})); 
	geolocation.getCurrentPosition(function (r) {  
	    if (this.getStatus() == BMAP_STATUS_SUCCESS) {  
	    	map.centerAndZoom(r.point,12);
	        map.panTo(r.point);  
	    }  
	}); 
}
function addMarker(type, node_id){
	var map = new BMap.Map("container");
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl({ 
		isOpen: true, 
		anchor: BMAP_ANCHOR_BOTTOM_RIGHT 
	})); 
	var opts = {
			title : "设备信息"  // 信息窗口标题
	};
//	var myIcon = new BMap.Icon("../../img/r_on_icon.png", new BMap.Size(23, 25), {  
//        offset: new BMap.Size(10, 25), // 指定定位位置  
//        imageOffset: new BMap.Size(0, 0) // 设置图片偏移  
//    });
	map.clearOverlays()//移除之前添加的所有覆盖物
	$.ajax({
		type : "post",
//		async:false,
		url : rootPath + "channel/query4map/"+type+"/"+node_id,
		dataType : "json", //返回数据形式为json
		success : function(result) {
			if(result['success']){
				var point = new BMap.Point(result['data'][0].lng,result['data'][0].lat);
				map.centerAndZoom(point,15);
				map.panTo(point); 
				$.each(result['data'],function(index, item) {
					console.log(item);
					var marker = new BMap.Marker(new BMap.Point(item.lng,item.lat));  // 创建标注
//					var marker = new BMap.Marker(new BMap.Point(item.lng,item.lat),{icon:myIcon});  // 创建标注
					var content = "<table class='table table-condensed'>"
									 + " <tr>"
									 + "	<td>设备号</td>"
									 + "<td colspan='3'>"+item.devNo+"</td>"
									 + "</tr>"
									 + "<tr>"
									 + "<td>mac地址</td>"
									 + "<td colspan='3'>"+item.mac+"</td>"
									 + "</tr>"
									 + "<tr>"
									 + "<td>安装地址</td>"
									 + "<td colspan='3'>"+item.selectCity+item.installAddress+"</td>"
									 + "</tr> "
									 + "<tr>"
									 + "<td style='text-align: center'></td>"
									 + "<td style='text-align: center'></td>"
									 + "<td style='text-align: center'><a href='"+rootPath+"router/detail/"+item.devNo+"' class='btn btn-link' target='_blank'>详情</a></td>"
									 + "<td style='text-align: center'><button class='btn btn-link' onclick=\"deleteRouter('"+item.devNo +"');\">删除</button></td>"
									 + " </tr>"
									 + "</table>";
//					var content = '设备号：'+item.devNo+'<br/>'
//					+ 'MAC地址：'+item.mac+'<br/>'
//					+ '安装地址：'+item.installAddress+'<br/>';//item.installAddress+","+item.lng+","+item.lat;
					map.addOverlay(marker);               // 将标注添加到地图中
					addClickHandler(map,content,opts,marker);
				});
			}else{
				var geolocation = new BMap.Geolocation();  
				geolocation.getCurrentPosition(function (r) {  
				    if (this.getStatus() == BMAP_STATUS_SUCCESS) {  
				    	map.centerAndZoom(r.point,12);
				        map.panTo(r.point);  
				    }  
				}); 
			}
		}
	});
	
//	var data_info = [[121.405957,31.03341,"地址：北京市东城区王府井大街88号乐天银泰百货八层"],
//		[121.406811,31.033171,"地址：北京市东城区东华门大街"],
//		[121.416526,31.051039,"地址：北京市东城区正义路甲5号"]
//	];
	
	
}

function addClickHandler(map,content,opts,marker){
	marker.addEventListener("click",function(e){
		openInfo(map,content,opts,e)
	});
}
function openInfo(map,content,opts,e){
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
	map.openInfoWindow(infoWindow,point); //开启信息窗口
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
					//从地图上移除marker
					common.open(result['data']);
				}else {
					common.alert(result['msg']);
				}
			}
	    });
	}, function(){});
}

	
 
