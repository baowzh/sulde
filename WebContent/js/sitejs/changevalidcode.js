/**
 * 更新验证码
 */
var replaceverifycode = function(id) {
	var imgSrc='';
	if(id!=null&&id!=undefined){
		 imgSrc = $("#varifyimg"+id);
	}else{
		 imgSrc = $("#varifyimg");
	}
	var src = imgSrc.attr("src");
	imgSrc.attr("src", chgUrl(src));

};
// 时间戳
// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	url = url.substring(0, 17);
	if ((url.indexOf("&") >= 0)) {
		url = url + "¡Átamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	return url;
};