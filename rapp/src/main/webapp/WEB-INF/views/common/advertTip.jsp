<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<h4><span class="text-danger pull-right">声明：禁止发布黄赌毒以及违反任何国家相关法律法规的信息</span></h4>
<hr/>
<h4> 温馨提示</h4>
<p>1.您可以通过本页配置推送的广告信息
	<c:choose>
      	<c:when test="${backUser.role.advertMax != -1}">
   			<b>(限${backUser.role.advertMax}条)</b>
   		</c:when>
   </c:choose>;
</p>
<p>2.广告资源图片可以可以配置网络地址图片，也可通过本地上传配置;</p>
<p>3.广告配置后需要通过审核后才能被您名下的路由器所推送;</p>
<p>4.为了保证您的广告能在网络较差的情况下不影响显示，请保持上传的图片大小 <b class="text-danger">小于2M</b>,
<b class="text-danger">插屏广告的宽高比例为4：3</b></p>
<p>5.配置成功的广告需要通过管理员审核，审核通过后您可以在导航<span class="text-danger">广告->我的广告</span>页面启用/禁用您的广告信息;</p>
<p>6.您的所有广告配置信息可以在 <span class="text-danger">广告->我的广告</span>页面查看;</p>
