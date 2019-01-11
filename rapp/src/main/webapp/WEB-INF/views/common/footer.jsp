<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<link href="${rapp.nginx }css/public.css" type="text/css" rel="stylesheet" media="all">
<!--底部-->
<footer>
	<div class="container-fluid">
		<span>Copyright © 2017 qcwifi. All Rights Reserved. 粤ICP备00001号</span></br>
		<span>版权所有 深圳市前辰科技有限公司</span>
	</div>
</footer>
<div class="im clearfix" id="customer">
	<div class="ly-side" style="right: 14.5px;">
		<ul>
			<li id="im_495" class="qqkf">
				<div class="sidebar_qq">
					<img alt="在线客服" src="${rapp.nginx }img/r1.png" width="50" height="50">
				</div>

				<div id="im_main_495" class="ly-side-main"
					style="right: 70px; opacity: 0; display: none;">
					<div class="jiao"></div>
					<div>
						<div class="im-head">在线客服</div>
						<div class="im-list">
							<div class="im-item">
								<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2997982847&site=qq&menu=yes"><img
									style="CURSOR: pointer" border="0" src="${rapp.nginx }img/qq.png"> <br>
								</a> 
							</div>
						</div>
					</div>
					<div class="infobox">
						我们营业的时间 <br>9:00-18:00
					</div>
				</div>
			</li>



			<li style="border: none;"><a class="back-to-top" href="#top">
					<img alt="回到顶部" src="${rapp.nginx }img/r4.png" width="50" height="50">
			</a></li>
		</ul>

	</div>

</div>
<script type="text/javascript" src="${rapp.nginx }js/bootstrap-hover-dropdown.js"></script>
<script type="text/javascript" src="${rapp.nginx }js/kefu.js"></script>
<script type="text/javascript">
	$(function() {
		 $('.js-activated').dropdownHover().dropdown();
		 $("#top").hide();
	  	$(window).scroll(function(){
	  		if ($(window).scrollTop()>0) {
	  			$("#top").fadeIn(500);
	  		} else{
	  			$("#top").fadeOut(500);
	  		};
	  	});
	  	$("#top").click(function(){
	  		$("html,body").animate({scrollTop:0},500);
	  		return false;
	  	});
	});
</script>