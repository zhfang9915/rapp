$(function(){
	$("input[name='installAddress']").focus(function(){
		if(!$("#address").val()){
			common.alert("请选择地区");
		}
	});
	$("input[name='installAddress']").keyup(function(){
		searchByStationName();
	});

});

var geolocation = new BMap.Geolocation();  
var map = new BMap.Map("container");
map.centerAndZoom("徐家汇", 18);
geolocation.getCurrentPosition(function (r) {  
    if (this.getStatus() == BMAP_STATUS_SUCCESS) {  
        var mk = new BMap.Marker(
    		new BMap.Point(r.point.lng, r.point.lat)
    	); 
        map.addOverlay(mk);  
        map.panTo(r.point);  
		$("#lng").val(r.point.lng);
		$("#lat").val(r.point.lat);
		console.log(r.address.province+ r.address.city +r.address.district);
		$("input[name='installAddress']").val(r.address.street+r.address.street_number);
    }  
});  
var localSearch = new BMap.LocalSearch(map);
	
function searchByStationName() {
	var keyword = $("#address").val() + $("input[name='installAddress']").val();
    map.clearOverlays();//清空原来的标注
    localSearch.setSearchCompleteCallback(function (searchResult) {
        var poi = searchResult.getPoi(0);
        $("#lng").val(poi.point.lng);
		$("#lat").val(poi.point.lat);
        map.centerAndZoom(poi.point, 18);
        var marker = new BMap.Marker(
        		new BMap.Point(poi.point.lng, poi.point.lat)
        	);  // 创建标注，为要查询的地方对应的经纬度
        map.addOverlay(marker);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    });
    localSearch.search(keyword);
} 
