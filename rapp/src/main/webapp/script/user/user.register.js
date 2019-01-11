function sendEmailAgain(username,email){
	var $curCount=60;
	var $obj=$("#btn-sendAgain");
    var $timer = window.setInterval(function(){
    	if ($curCount == 0) {
            window.clearInterval($timer);//停止计时器
            $obj.attr("onclick","sendEmailAgain('"+username+"','"+email+"');");//启用按钮
            $obj.removeAttr('disabled');
            $obj.html("重新发送");
            $curCount=60;
        }else {
        	$obj.removeAttr('onclick');
        	$obj.attr("disabled","disabled");//禁用按钮
        	$obj.html($curCount+"秒");
        	$curCount--;
        }
    }, 1000);
    
	$.ajax({
		type : "post",
		data : {'username':username,'email':email},
		url : rootPath + "user/register/sendAgain"
	});
}

//对电子邮件的验证
function isEmail(s) {
	 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 return myreg.test(s);
}