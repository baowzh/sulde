var openloginwin = function() {
	$("#logindiv").dialog({
		height : 380,
		width : 310,
		resizable : true,
		model : false
	});
};
var login = function() {
	var queryurl = 'login.do';
	var username = $("#username").val();
	var password = $("#password1").val();
	var validcode = $("#varifycode1").val();
	if (username == null || username == '') {
		MessageWindow.showMess('    ');
		return;

	}
	if (password == null || password == '') {
		MessageWindow.showMess('    ');
		return;

	}
	if (validcode == null || validcode == '') {
		MessageWindow.showMess('    ');
		return;

	}
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : queryurl,// 请求的action路径
				data : {
					username : username,
					password : password,
					validcode : validcode
				},
				error : function() {// 请求失败处理函数
					MessageWindow
							.showMess('         ');
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.success == 'true') {
						$("#logindiv").dialog("close");
						MessageWindow.showMess('   ');
					} else {
						if (data.mess == '1') {
							MessageWindow
									.showMess('           ');
						}
						if (data.mess == '2') {

						} else if (data.mess == '3') {
							MessageWindow
									.showMess('            ');

						} else if (data.mess == '5') {
							MessageWindow
									.showMess('  email  <br>     ');
						} else {
							//
						}

					}

				}
			});

};