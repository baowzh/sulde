var doregist = function() {
	// 校验数据完整性
	var username = $("#username").val();
	if (username == null || username == '') {
		MessageWindow.showMess("' '      ");
		return;
	}
	var artname = $("#artname").val();
	if (artname == null || artname == '') {
		MessageWindow.showMess("'  '      ");
		return;
	}
	var password = $("#password").val();
	if (password == null || password == '') {
		MessageWindow.showMess("' '      ");
		return;
	}
	var varifycode = $("#varifycode").val();
	if (varifycode == null || varifycode == '') {
		MessageWindow.showMess("' '      ");
		return;
	}
	var agree = $("#agree")[0].checked;//checkbox.checked
	if (agree==false||agree==undefined) {
		MessageWindow.showMess("        ");
		return;
	}
	var error = false;
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "getverifycode.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {},
				success : function(data) { // 请求成功后处理函数。
					var remotecode = data.validateCode;
					if (remotecode != varifycode) {
						MessageWindow
								.showMess("' '            ");
						error = true;
						return;
					}
				}
			});
	if (error) {
		return;
	}
	// 校验是否存在同名的用户
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "checkuserisexists.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					username : username
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.exists == '1') {
						exists = true;
						MessageWindow
								.showMess("' '          ");
						error = true;
					}
				}
			});
	if (error) {
		return;
	}
	$("#userinfoform").submit();
	// 全部通过则保存用户信息
};
/**
 * 更新验证码
 */
var replaceverifycode = function() {
	var imgSrc = $("#varifyimg");
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
var getverifycode = function() {
	// 校验 getverifycode 是否跟图片相符
	var remotecode = '';
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getverifycode.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {},
		success : function(data) { // 请求成功后处理函数。
			remotecode = data.validateCode;
			return;
		}
	});
	return remotecode;
};
var checkusreisexists = function() {
	var exists = false;
	var username = $("#username").val();
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "checkuserisexists.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			username : username
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.exists == '1') {
				exists = true;
				/*
				 * MessageWindow .showMess("' '  
				 *        "); return;
				 */
			}
		}
	});
	return exists;
};
