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
					var imgurl = 'html/userhead/';
					var homdurl = 'gouserindex.do';
					if (data.success == 'true') {
						iniuserinfocard(data);
					} else {
						if (data.mess == '1') {
							MessageWindow
									.showMess('       ');
						}
						if (data.mess == '2') {
							$("#loginform")
									.html(
											'<div style=\"writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;-webkit-text-orientation: sideways-right;\"> <br>&nbsp;&nbsp; &nbsp;     <br>     '
													+ '<br><br><a href=\"registe.do\" style=\"text-decoration: none;color: #f00;\">&nbsp;&nbsp; &nbsp&nbsp;&nbsp; &nbsp </a>   </div>');

						} else if (data.mess == '3') {
							MessageWindow
									.showMess('       ');
							$('#registlink').attr('href',
									"javascript:getpass();")
							$('#registlink').attr('style', 'font-size:10px;');
							$('#registlink')
									.html(
											'<font color="#f00">  </font>');
						} else if (data.mess == '5') {
							MessageWindow
									.showMess('  email    <br>         ');
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
					var imgurl = 'html/userhead/';

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
									iniuserinfocard(data);
								}
							});
					// 显示顶部广告
					// displaytoad();
					// showsideadver();
					// 设置搜索框的大小
					if (iswebkit) {
						$('#searchtext').addClass('webkitsearchtext');
					} else {
						$('#searchtext').addClass('iesearchtext');
					}
				}));
/**
 * 
 */
var iniuserinfocard = function(data) {
	var imgurl = 'html/userhead/';
	var homdurl = 'gouserindex.do';
	var logindiv = $('<div></div>').addClass('loin').css('padding-left', 10);
	var avtrdiv = $('<div></div>').addClass('avtr');
	avtrdiv = avtrdiv.append($('<img>').prop('src',
			imgurl + data.userinfo.headurl));
	avtrdiv = avtrdiv.css('width', 80);
	var descdiv = $('<div></div>').addClass('desc').css('width', 120);
	descdiv.append($('<div></div>').addClass('m1ln').text(
			'  ' + data.userinfo.artname));
	descdiv.append($('<div></div>').addClass('m1ln').append(
			$('<a></a>').prop('href', homdurl).text(' ')));
	descdiv.append($('<div></div>').addClass('m1ln').append(
			$('<a></a>').prop('href', 'javascript:logout();').text(
					' ')));
	descdiv.append($('<div></div>').addClass('m1ln').append(
			$('<a></a>').prop('href', '#').text(
					'   :' + data.userinfo.logindate)));
	logindiv = logindiv.append(avtrdiv);
	logindiv = logindiv.append(descdiv);
	$("#logindiv").html(
			$('<div></div>').addClass('loin').append(logindiv.html()));

};
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
			// window.location.href = 'index.do';
			initloginform();
		}
	});

};
var initloginform = function() {
	var loginform = $('<form></form>').addClass('mglForm').prop('action',
			'checkandlogin.do').prop('id', 'loginform').prop('method', 'post');
	var label1 = $('<div></div>').addClass('label').css('width', 27).text(
			'  ');
	loginform.append(label1);
	var label2 = $('<div></div>').addClass('label').css('width', 27).text(
			'  ');
	loginform.append(label2);
	var label3 = $('<div></div>').addClass('label').css('width', 27).text(
			'').append(
			$('<a></a>').text('  :').prop('href',
					'javascript:replaceverifycode();'));
	loginform.append(label3);
	var label4 = $('<div></div>').addClass('label').css('width', 27).append(
			$('<a></a>').prop('href', 'javascript:replaceverifycode();')
					.append(
							$('<img>').prop('src', 'verifyCodeServlet').prop(
									'id', 'varifyimg').css({
								width : 18,
								height : 100
							})));
	loginform.append(label4);
	var inputHolder1 = $('<div></div>').addClass('inputHolder').append(
			$('<input>').prop('name', 'username').prop('id', 'username'));
	loginform.append(inputHolder1);
	var inputHolder2 = $('<div></div>').addClass('inputHolder').append(
			$('<input>').prop('name', 'password').prop('id', 'password').prop(
					'type', 'password'));
	loginform.append(inputHolder2);
	var inputHolder3 = $('<div></div>').addClass('inputHolder').append(
			$('<input>').prop('name', 'validcode').prop('id', 'varifycode'));
	loginform.append(inputHolder3);
	var operdiv = $('<div></div>').addClass('mnlist').css({
		height : 150,
		width : 30
	});
	operdiv = operdiv.append($('<a></a>').prop('id', 'registlink').prop('href',
			'registe.do').text(''));
	operdiv.append('&nbsp;&nbsp;&nbsp;&nbsp;');
	operdiv = operdiv.append($('<a></a>').prop('href', 'javascript:login();')
			.text(''));
	loginform.append(operdiv);
	var contentdiv = $('<div></div>').addClass('content').append(loginform);
	$('#logindiv').html('<div class="content">' + contentdiv.html() + '<div>');
};
var search = function(action) {
	var searchtext = $("#searchtext").val();
	if (searchtext == null || searchtext == '') {
		MessageWindow.showMess('      ');
		return;
	}
	window.location.href = action + '?searchtext=' + searchtext;
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
	$
			.ajax({
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
					MessageWindow
							.showMess('    ');
				},
				success : function(data) { // 请求成功后处理函数。
					// window.location.href = 'index.do';
					if (data.mess == 1) {
						MessageWindow
								.showMess('    email:'
										+ data.mailaddress
										+ '<br>   email    ');
					}
				}
			});

};

lastScrollY = 0;
var heartBeat = function() {
	var diffY;
	if (document.documentElement && document.documentElement.scrollTop)
		diffY = document.documentElement.scrollTop;
	else if (document.body)
		diffY = document.body.scrollTop
	else {
	}
	percent = .1 * (diffY - lastScrollY);
	if (percent > 0)
		percent = Math.ceil(percent);
	else
		percent = Math.floor(percent);
	document.getElementById("ALayer1").style.top = parseInt(document
			.getElementById("ALayer1").style.top)
			+ percent + "px";
	//document.getElementById("ALayer2").style.top = parseInt(document
		//	.getElementById("ALayer2").style.top)
			//+ percent + "px";
	lastScrollY = lastScrollY + percent;
};
var suspendcode12 = "<DIV id=\"ALayer1\" style='left:0px;PosITION:absolute;TOP:10px;FILTER: alpha(opacity=85);'><div align=left><img src='img/close.gif' border=0 onclick='closeBanner();' /></div><a style='/display:block; margin-top:5px;'/ href='browserlist.do' target='_blank'><img src='img/haigur.png' border='0' width='110'></a></div>";
//var suspendcode14 = "<div id=\"ALayer2\" style='right:0px;PosITION:absolute;TOP:10px;FILTER: alpha(opacity=85);'><div align=right><img src='img/close.gif' border=0 onclick='closeBanner();' /></div><a href='/ad/2014/mgl/mglpx.jsp' target='_blank'><img src='img/haigur.png' border='0'></a></div>"
// document.write(suspendcode12);
//document.write(suspendcode14);
// window.setInterval("heartBeat()", 1);
var closeBanner=function () {
	document.getElementById("ALayer1").style.display = 'none';
	//document.getElementById("ALayer2").style.display = 'none';
};
