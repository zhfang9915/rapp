var d2000s1080c699_cW699 = document.documentElement.clientWidth
		|| document.body.clientWidth;
var d2000s1080c699_cH699 = document.documentElement.clientHeight
		|| document.body.clientHeight;
var eleFixedWrap = document.querySelector('.d2000s1080c699_be1');
var eleClose = document.querySelector('.d2000s1080c699_close1');
var eleImg = document.getElementById('d2000s1080c699_img1');
var dataW = eleImg.getAttribute("dataw");
var dataH = eleImg.getAttribute("datah");
var cRatio = d2000s1080c699_cW699 / d2000s1080c699_cH699;
var imgRatio = dataW / dataH;
var bSetH = (cRatio - imgRatio) > 0;
// 居中
if (bSetH) {
	// console.log("H-H");
	eleFixedWrap.style.height = d2000s1080c699_cH699 * 0.9 + 'px';
	eleFixedWrap.style.width = imgRatio * d2000s1080c699_cH699 * 0.9 + 'px';
} else {
	eleFixedWrap.style.width = d2000s1080c699_cW699 * 0.95 + 'px';
	eleFixedWrap.style.height = d2000s1080c699_cW699 * 0.95 / imgRatio + 'px';
}
eleClose.onclick = function() {
	// interface
	eleFixedWrap.style.height = 0;
	eleFixedWrap.style.overflow = 'hidden';
	eleFixedWrap.innerHTML = "";
};