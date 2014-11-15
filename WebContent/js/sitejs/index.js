/**
 * 用户登录
 */
var login = function() {
	var queryurl = 'login.do';
	var username = $("input[name='username']").val();
	var password = $("input[name='password']").val();
	var validcode = $("input[name='validcode']").val();
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
					// 修改用户登录界面信息
					// 显示用户名称
					// 显示进入博客等连接
					var imgurl = 'getsmheadimge.do';
					var homdurl = 'gouserindex.do';
					if (data.success == 'true') {
						var innerHTML = '<div class=\"loin\" style=\"padding-left:10px;\">'
								+ '<div class=\"avtr\"><img src=\"'
								+ imgurl
								+ '?userid='
								+ data.userinfo.userid
								+ '\"  style=\"width:80px;\" /></div>'
								+ '<div class=\"desc  \" style=\"width:120px\">'
								+ '<div class=\"m1ln\">   '
								+ data.userinfo.artname
								+ '</div>'
								+ '<div class=\"m1ln\"><a href=\"'
								+ homdurl
								+ '\">   </a></div>'
								+ '<div class=\"m1ln\"><a href=\"'
								+ 'javascript:logout();'
								+ '\"> </a></div>'
								+ '<div class=\"m1ln\"><a href=\"#\">     : '
								+ data.userinfo.logindate
								+ '</a></div>'
								+ '</div>' + '</div>';
						// $("#loginform").html(innerHTML);
						$("#logindiv").html(innerHTML);

					} else {
						if (data.mess == '1') {
							MessageWindow
									.showMess('           ');
						}
						if (data.mess == '2') {
							$("#loginform")
									.html(
											'<div style=\"writing-mode: tb-lr; -webkit-writing-mode: vertical-lr\"> <br>&nbsp;&nbsp; &nbsp;          '
													+ '<br><a href=\"registe.do\">&nbsp;&nbsp; &nbsp&nbsp;&nbsp; &nbsp </a>   </div>');
						} else if (data.mess == '3') {

							MessageWindow
									.showMess('            ');
							$('#registlink').attr('href',
									"javascript:getpass();")
							$('#registlink').attr('style', 'font-size:10px;');
							$('#registlink')
									.html(
											'<font color="#f00">  </font>');

						} else {
							//
						}

					}

				}
			});

};

$(document)
		.ready(
				$(function() {
					// 根据浏览器不同设置登录框效果
					var iswebkit = /webkit/.test(navigator.userAgent
							.toLowerCase());
					if (!iswebkit) {
						// 设置ie 下的效果
						$("#varifyimgdiv")
								.html(
										'<table style=\"-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; widht: 20px !important; height: 170px;\">'
												+ '<tr>'
												+ '<td style=\"height:100px;width:20px;align:center\">'
												+ '<div class=\"mfl\" style=\"height:100px;width:22px;\">'
												+ '<img src=\"verifyCodeServlet\" id=\"varifyimg\" width=\"21\"'
												+ 'height=\"100\" />&nbsp;&nbsp;'
												+ '</div>'
												+ '</td>'
												+ '<td style=\"height:70px;width:20px;align:center\">	'
												+ '<input type=\"text\" name=\"validcode\"  style=\"-webkit-writing-mode: vertical-lr; writing-mode: tb-lr;height:40px;width:15px;\" />'
												+ '</td>'
												+ '</tr>'
												+ '</table>');
					}
					var username = $("input[name='username']").val();
					var password = $("input[name='password']").val();
					var validcode = $("input[name='validcode']").val();
					var checkurl = 'checklogin.do';
					var homdurl = 'gouserindex.do';
					var imgurl = 'getsmheadimge.do';

					$
							.ajax({
								async : false,
								cache : false,
								type : 'POST',
								dataType : "json",
								url : checkurl,// 请求的action路径
								data : {
									username : username,
									password : password
								},
								error : function() {// 请求失败处理函数
									MessageWindow
											.showMess('         ');
								},
								success : function(data) { // 请求成功后处理函数。
									//
									// MessageWindow.showMess('aa');
									if (data.userinfo == null) {
										return;
									}
									var innerHTML = '<div class=\"loin\" style=\"padding-left:10px;\">'
											+ '<div class=\"avtr\"><img src=\"'
											+ imgurl
											+ '?userid='
											+ data.userinfo.userid
											+ '\"  style=\"width:80px;\" /></div>'
											+ '<div class=\"desc  \" style=\"width:120px\">'
											+ '<div class=\"m1ln\">  '
											+ data.userinfo.artname
											+ '</div>'
											+ '<div class=\"m1ln\"><a href=\"'
											+ homdurl
											+ '\">   </a></div>'
											+ '<div class=\"m1ln\"><a href=\"'
											+ 'javascript:logout();'
											+ '\"> </a></div>'
											+ '<div class=\"m1ln\">   : '
											+ data.userinfo.logindate
											+ '</div>' + '</div>' + '</div>';
									// $("#loginform").html(innerHTML);
									$("#logindiv").html(innerHTML);

								}
							});
					// 显示顶部广告
					// displaytoad();
					// showsideadver();

				}));
/**
 * 退出系统
 */
var logout = function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : 'userlogout.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('    ');
		},
		success : function(data) { // 请求成功后处理函数。
			window.location.href = 'index.html';
		}
	});

};
var search = function(action) {
	var searchtext = $("#searchtext").val();
	if (searchtext == null || searchtext == '') {
		MessageWindow.showMess('      ');
		return;
	}
	$("#searchform").attr("action", action);
	$("#searchform").submit();
};
/**
 * 播放顶部广告
 */
var displaytoad = function() {
	$("#banner").slideDown("slow");
	$("#rightOpen").hide();

	$("#rightOpen").click(function() {
		$("#banner").slideDown("slow");
		setTimeout("displayimg()", 15000);
		$("#rightOpen").hide();
	})
	setTimeout("displayimg()", 10000);
};

var displayimg = function() {
	$("#banner").slideUp("slow");
	$("#rightOpen").show();
};

var showsideadver = function() {
	var duilian = $("div.duilian");
	var duilian_close = $("a.duilian_close");

	var window_w = $(window).width();
	if (window_w > 1000) {
		duilian.show();
	}
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		duilian.stop().animate({
			top : scrollTop + 100
		});
	});
	duilian_close.click(function() {
		$(this).parent().hide();
		return false;
	});
};
/**
 * 通过邮箱找回密码
 */
var getpass = function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : 'getpasswithmail.do',// 请求的action路径
		data : {
			username : $("input[name='username']").val(),
			validcode : $("input[name='validcode']").val()
		},
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('    ');
		},
		success : function(data) { // 请求成功后处理函数。
			// window.location.href = 'index.html';
			if (data.mess == 1) {
				MessageWindow.showMess('    email:'
						+ data.mailaddress + '   ');
			}
		}
	});

};