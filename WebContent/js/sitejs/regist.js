var doregist = function() {
	// 校验数据完整性
	var username = $("#username").val();
	if (username == null || username == '') {
		MessageWindow.showMess("' '    ");
		return;
	}
	if (username.length < 6) {
		MessageWindow.showMess("' '     ");
		return;
	}
	if (!username.match(/[^\u4e00-\u9fa5]/g)) {
		MessageWindow.showMess("' '      ");
		return;
	}
	var artname = $("#artname").val();
	if (artname == null || artname == '') {
		MessageWindow.showMess("'  '    ");
		return;
	}
	/*
	if (artname.length < 8) {
		MessageWindow.showMess("'  '     ");
		return;
	}*/
	var password = $("#password").val();
	if (password == null || password == '') {
		MessageWindow.showMess("' '     ");
		return;
	}
	var varifycode = $("#varifycode").val();
	if (varifycode == null || varifycode == '') {
		MessageWindow.showMess("' '     ");
		return;
	}
	var email = $("#email").val();
	if (email == null || email == '') {
		MessageWindow.showMess("'email'     ");
		return;
	}
	var reg=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if(!reg.test(email)){
		MessageWindow.showMess("'email'      ");
		return;	
	}
	var agree = $("#agree")[0].checked;// checkbox.checked
	if (agree == false || agree == undefined) {
		MessageWindow
				.showMess("        <br>  ");
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
								.showMess("' '       ");
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

var getverifycode = function() {
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
				MessageWindow
				.showMess("' '       <br>    ");
				exists = true;
			}
			if (data.exists == '2') {
				MessageWindow
				.showMess("'你已经注册过用户 ");
				exists = true;
			}
		}
	});
	return exists;
};
