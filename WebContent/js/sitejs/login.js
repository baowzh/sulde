var dosubmit = function() {
	// 校验用户是否存在
	var username = $("#username").val();
	var password = $("#password").val();
	var varifycode = $("#varifycode").val();
	if (username == null || username == '') {
		MessageWindow.showMess("        ");
		return;
	}
	if (password == null || password == '') {
		MessageWindow.showMess("      ");
		return;
	}
	if (varifycode == null || varifycode == '') {
		MessageWindow.showMess("    ");
		return;
	}
	var checkresult = checkusernameandpassword();
	if (checkresult == '0') {
		MessageWindow
				.showMess("            ");
		return;
	} else if (checkresult == '1') {
		MessageWindow
				.showMess("               ");
		return;
	} else {
		// 校验验证码是否正确
		//var sysverifycode = getverifycode()
		var verifycode = $("#varifycode").val();
		//alert(sysverifycode+'  '+verifycode);
		/*if (sysverifycode != verifycode) {
			MessageWindow.showMess("          ");
			return;
		}*/
		var username = $("#username").val();
		var password = $("#password").val();
		$("#loginform").submit();
		/*$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					dataType : "json",
					url : "login.do",// 请求的action路径
					error : function() {// 请求失败处理函数
						alert('请求失败');
					},
					data : {
						username : username,
						password : password,
						verifycode:verifycode
					},
					success : function(data) { // 请求成功后处理函数。
						if (data.success == 'true') {
							// 页面跳转到用户主页
							if(data.user.userkind==2){						
							$("#loginform")[0].action = 'sitemanagerindex.do';
							}else{
							$("#loginform")[0].action = 'gouserindex.do';	
							}
							
							$("#loginform").submit();
						}
					}
				});*/
	}

};
var checkusernameandpassword = function() {
	var exists = '2';
	var username = $("#username").val();
	var password = $("#password").val();
	$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "checkusernameandpassword.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					username : username,
					password : password
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.result == '0') {
						exists = '0';
					} else if (data.result == '1') {
						exists = '1';
					} else {
						exists = '2';
					}
				}
			});
	return exists;
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