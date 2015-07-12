/**
 * 打开页面
 * 
 * @param {}
 *            pageurl
 */
var openpage = function(index, currentuserid, type, pagetype, neebarlocation) {
	var querurl = 'pagingsharedoc.do';
	pagetype = 1;
	if (type == 1) {
		querurl = 'pagingdoc.do';
	} else if (type == 2) {
		querurl = 'pagingsharedoc.do';
	} else {

	}
	$
			.ajax({
				async : true,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : querurl,// 请求的action路径
				beforeSend : function() {
					// 启动进度条
					if (neebarlocation) {
						var sheetheight = $(".mVsheet").css("height");
						if (sheetheight == undefined) {
						} else {
							var pxindex = sheetheight.indexOf('px');
							sheetheight = sheetheight.substring(0, pxindex);
							sheetheight = sheetheight - 400 + 900;
							$(".loadingbox")
									.css("top", "" + sheetheight + "px");
						}
					}
					$(".loadingbox").show();
				},
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					userid : currentuserid,
					pageindex : index,
					pagetype : pagetype,
					pagesize : 24
				},
				complete : function() {
					$(".loadingbox").hide();
				},
				success : function(data) { // 请求成功后处理函数。
					var htmlstrr = "";
					for (i in data.doclist) {
						if (type == 1) {

							if (pagetype == 1) {
								// 一片文章
								var cont_boxdiv = $('<div></div>').addClass(
										'cont_box');
								// 标题
								var doctitlediv = $('<div></div>').addClass(
										'title mglcontent');
								doctitlediv.text(data.doclist[i].doctitle);
								cont_boxdiv.append(doctitlediv);
								// 相关信息
								var sharediv = $('<div></div>').addClass(
										'shareBookmark mgldiv');
								cont_boxdiv.append(sharediv);
								// 相关信息
								var shareBookmarkdiv = $('<div></div>')
										.addClass('mgldiv');
								shareBookmarkdiv.css('margin-top', 0);
								shareBookmarkdiv.css('margin-right', 20);
								shareBookmarkdiv.css('margin-bottom', 20);
								shareBookmarkdiv.css('margin-left', 20);
								shareBookmarkdiv.append(' '
										+ data.doclist[i].chnaname + '<br>');
								var sharedoca = $('<a></a>').prop('href',
										'javascript:sharedocument()').text(
										'');
								shareBookmarkdiv.append(sharedoca);
								shareBookmarkdiv.append(''
										+ data.doclist[i].sharecount + ' ');
								var marka = $('<a></a>').prop('href',
										'javascript:markdocument()').text(
										'');
								shareBookmarkdiv.append(marka);
								shareBookmarkdiv.append(''
										+ data.doclist[i].markcount + ' <br>');
								shareBookmarkdiv.append('  '
										+ data.doclist[i].readcount + '<br>');
								shareBookmarkdiv.append('   '
										+ data.doclist[i].docRelTimeStr
										+ '<br>');
								shareBookmarkdiv.append($('<a></a>').prop(
										'href', '#comment').text(''));
								shareBookmarkdiv.append(''
										+ data.doclist[i].commentCount + '');
								sharediv.append(shareBookmarkdiv);
								// 摘要
								var mglcontent = $('<div></div>').addClass(
										'blogarea mglcontent').append(
										data.doclist[i].htmlabc);

								// 打开
								var opendiv = $('<div></div>').addClass(
										'shareBookmark mgldiv');
								mglcontent.append(opendiv);
								cont_boxdiv.append(mglcontent);
								opendiv.css('margin-top', 10);
								opendiv.css('margin-right', 360);
								opendiv.css('margin-bottom', 0);
								opendiv.css('margin-left', 0);
								opendiv
										.append($('<div></div>')
												.addClass('mgldiv')
												.append(
														$('<a></a>')
																.prop(
																		'href',
																		'getuserdocdetail.do?docid='
																				+ data.doclist[i].docid)
																.text(
																		'     ')));
								htmlstrr = htmlstrr
										+ '<div class="cont_box">'
										+ cont_boxdiv.html()
										+ '</div><div style="clear: both"></div>';
							} else {
								htmlstrr = htmlstrr
										+ '<div class=\"m1ln\"><a><img src="img/dot.gif"></a>'
										+ '<a '
										+ 'href=\"javascript:readdoc(\''
										+ data.doclist[i].docid + '\');\">'
										+ data.doclist[i].doctitle
										+ '</a>&nbsp;' + '</div>';
							}

						} else if (type == 2) {
							var listdiv = $('<div></div>');
							var dotimgdiv = $('<div></div>').addClass('"m1ln"');
							dotimgdiv.css({
								"width" : "10px",
								"height" : "10px",
								"float" : "right",
								" padding-top" : 5
							});
							dotimgdiv.append($('img').prop('src',
									'site/img/qig_1_v.png'))
							listdiv.append(dotimgdiv);
							// 标题
							var m1lndiv = $('<div></div>').addClass('m1ln');
							m1lndiv.css({
								"width" : "480px",
								"float" : "right"
							});
							m1lndiv.append($('<a></a>').prop(
									'href',
									'getuserdocdetail.do?docid='
											+ data.doclist[i].docid).text(
									data.doclist[i].doctitle));
							listdiv.append(m1lndiv);
							var authdiv = $('<div></div>').addClass('m1ln');
							authdiv.css({
								"width" : " 100px",
								" float" : "right"
							});
							authdiv.append($('<a></a>').prop(
									'href',
									'gouserindex.do?userid='
											+ data.doclist[i].userid).text(
									data.doclist[i].docauthor));
							listdiv.append(authdiv);
							htmlstrr = htmlstrr + '<div class="sharenwsl1">'
									+ listdiv.html() + '</div>';
						}
					}
					if (type == 1) {
						$("#doclist").html(htmlstrr);
						setmglconimgWidht();
					} else if (type == 2) {
						// $("#sharedoclist").html(htmlstrr);
					} else {

					}
					if (type == 1) {
						$("#docpagelist").html(data.pagingstr);
						// 设置当前 连接的
					} else if (type == 2) {
						// 设置当前 连接的
						// $("#sharedoclist").html(data.pagingstr);
					}

				}
			});

}
var openPhotoList = function(albumid) {
	if (albumid == null || albumid == '') {
		return;
	}
	var userid = $("#userid").val();
	if (userid == null || userid == '') {
		return;
	}
	$("#imggroupid").val(albumid);
	$("#alinkform").attr('action', 'getimglist.do');
	// $("#alinkform").submit();
	window.location.href = "getimglist.do?userid=" + userid + "&imggroupid="
			+ albumid;
};
/**
 * 添加朋友
 */
var addfriends = function(visiteduserid, isagree, messid) {
	// 少一个弹出对话框，等ui做好以后进行补充
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "addfriend.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			userid : visiteduserid,
			agree : isagree,
			messageid : messid
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.mess == '01') {
				// alert('  ');
				MessageWindow.showMess("   ");
			} else {
				// alert('    ');
				MessageWindow.showMess("    ");
			}

			$("#accordion").dialog("close");
		}
	});
};
/**
 * 给博主写信
 * 
 * @param {}
 *            visiteduserid
 */
var sendmessage = function(visiteduserid) {
	// 
	var message = $("#messagediv").text();
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "sendMessage.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			userid : visiteduserid,
			mess : message,
			messtype : 2
		},
		success : function(data) { // 请求成功后处理函数。
			// alert(data.mess);
			if (data.mess == 'success') {
				MessageWindow.showMess('      ');
			} else {
				MessageWindow.showMess(data.mess);
			}

			$("#messdiv").dialog("close");
		}
	});

};

var writemessage = function() {
	var islogin = false;
	islogin = checklogin();
	if (islogin == false) {
		// MessageWindow
		// .showMess('        
		// ');
		openloginwin();
		return;
	}
	$("#messdiv").dialog({
		height : 480,
		width : 460,
		resizable : false,
		modal : true

	});
};
/**
 * 
 * @param {}
 *            emotionname
 */
var addmessface = function(emotionname) {
	$("#messagediv").html(
			$("#messagediv").html() + $('<p>[' + emotionname + ']</p>').html());
};
/**
 * 列出全部别人写给自己的和自己发给别人的信
 */
var receivemessage = function() {

	$("#accordion").dialog({
		height : 530,
		width : 900,
		resizable : false,
		modal : true

	});

};
/**
 * 切换div
 * 
 * @param {}
 *            currentdivid
 * @param {}
 *            nextdivid
 */
var switchdiv = function(currentdivid, nextdivid) {
	var display = $("#" + currentdivid).css("display");
	if (display != 'none') {
		$("#" + currentdivid).css("display", "none");
		$("#" + nextdivid).css("display", "block");
	} else {
		$("#" + currentdivid).css("display", "block");
		$("#" + nextdivid).css("display", "none");
	}
};
/**
 * 显示用户信息
 */
var showuserinfo = function(userid) {
	$("#userinfo").dialog({
		height : 480,
		width : 900,
		resizable : true,
		modal : true

	});

};
/**
 * 
 */
var openaddfrienddl = function() {
	var islogin = false;
	islogin = checklogin();
	if (!islogin) {
		// MessageWindow
		// .showMess('       
		// ');
		openloginwin();
		return;
	}
	$("#addfriendmess").text('     ');
	$("#addfrienddiv").dialog({// addfriendmess 13347126631
		height : 490,
		width : 300,
		resizable : false,
		modal : true

	});

};
/**
 * 
 * @param {}
 *            visiteduserid
 */
var sendaddfriendmess = function(visiteduserid) {
	// addfrienddiv
	var mess = $("#addfriendmess").text();
	// alert(mess);
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "sendMessage.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					userid : visiteduserid,
					mess : mess,
					messtype : 4
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.mess == '01') {
						// alert('   ');
						MessageWindow.showMess('    ');
					} else if (data.mess == '02') {
						// alert('        
						//   ');
						MessageWindow
								.showMess('            ');
					}
					$("#addfrienddiv").dialog("close");
				}
			});

};
var delfriend = function() {
	var ids = $("[name=selectedradio]").filter(':checked');
	if (ids.length > 0) {
		showConfirmMess("      ", function() {
			if (this.getValue() == true) {
				delfriend();
			}
		});
	}else{
		MessageWindow.showMess('    ');
	}

}
var deletefriend = function() {
	var id = $("[name=selectedradio]").filter(':checked').attr("id");
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "delfriend.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			friendid : id
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.mess == '01') {
				// alert('  ');
				MessageWindow.showMess('   ');
			} else if (data.mess == '02') {
				// alert(' ');
				MessageWindow.showMess('       ');
			}
		}
	});
}

var readmessage = function(messid, type) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "readmessage.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			messageid : messid
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '1') {
				// alert(data.messvalue.contenthtml);
				var content = "<div class=\"mVsheet\">"
						+ data.messvalue.contenthtml + "</div>";
				if (type == 1) {
					$("#receivediv").html(content);
				} else {
					$("#senddiv").html(content);
				}
			} else if (data.success == '0') {
				// alert('     ');
				MessageWindow.showMess('        ');
			}
		}
	});

};
var switchclass = function(a) {
	$("#" + a).addClass("curspanstyle");
	$("#" + a).removeClass("spanstyle");
};
var checklogin = function() {
	var islogin = false;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : 'checklogin.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('         ');
		},
		success : function(data) {
			if (data.login == 'true') {
				islogin = true;
			} else {
				islogin = false;
			}
		}
	});
	return islogin;
};
/**
 * 
 */
var showdiv = function(id1, id2, id3) {
	$("#" + id1).css("display", "block");
	$("#" + id2).css("display", "none");
	$("#" + id3).css("display", "none");
};
var showpassdialog = function() {
	$('#updpassdiv').dialog({// addfriendmess 13347126631
		width : 300,
		height : 350,
		resizable : false,
		modal : true
	});
};
var modifypass = function() {
	// 校验工作及修改都放后台了
	var maillogin = $("#maillogin").val();
	var oldpass = '';
	if (maillogin == 0) {
		oldpass = $("#oldpassword").val();
		if (oldpass == null || oldpass == '') {
			MessageWindow.showMess("     ");
			return;
		}
	}
	var password = $("#password").val();
	if (password == null || password == '') {
		MessageWindow.showMess("      ");
		return;
	}
	var varifycode = $("#varifycode").val();
	if (varifycode == null || varifycode == '') {
		MessageWindow.showMess("    ");
		return;
	}

	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "modifyuserpass.do",// 请求的action路径
				data : {
					// username : $("#username").val(),
					pass : $("#password").val(),
					oldpass : $("#oldpassword").val(),
					varifycode : $("#varifycode").val()
				},
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.mess == '1') {
						MessageWindow.showMess("   ");
						$("#updpassdiv").dialog("close");

					} else if (data.mess == '2') {
						MessageWindow
								.showMess("       ");

					} else if (data.mess == '3') {

						MessageWindow
								.showMess("        ");
					}
				}
			});
};
$(document).ready(function() {
	$('.rotate img').each(function(i, val) {
		var distance = val.clientHeight - val.clientWidth;
		if (distance != 0 && distance > 0) {
			$(val).css('width', val.clientWidth + distance);
		}
	});
	var availWidth = $(window).height();
	var availheight = $(window).width();
	$('body').css('height', availWidth);
	$('#myDIV').css('width', availWidth - 10);
	$('#myDIV').css('min-height', availheight);
	$('#blogcon').css('min-height', availheight);
	//
	/*
	 * $("#message_face").jqfaceedit({ txtAreaObj : $("#commentdiv"),
	 * containerObj : $('#commentcontainer'), top : 25, right : -27 });
	 */
});
var setmglconimgWidht = function() {

	$(".mglcontent img").each(
			function() {
				// $(this).addClass("rotateimg");
				var height = this.height;
				var width = this.width;
				if (height == undefined || height == null) {
					height = this.clientHeight;
				}
				if (width == undefined || width == null) {
					width = this.clientWidth;
				}
				$(this).attr('height', height);
				$(this).attr('width', width);
				if (width - height > 0) {
					$(this).after(
							'<div style="height:' + (width - height)
									+ 'px;"></div>');
				}

			});
	$(".mglcontent iframe").each(
			function() {
				// $(this).addClass("rotateimg");
				var height = this.height;
				var width = this.width;
				if (height == undefined || height == null) {
					height = this.clientHeight;
				}
				if (width == undefined || width == null) {
					width = this.clientWidth;
				}
				$(this).attr('height', height);
				$(this).attr('width', width);
				if (width - height > 0) {
					$(this).after(
							'<div style="height:' + (width - height)
									+ 'px;"></div>');
				}

			});
	$(".mglcontent embed").each(
			function() {
				// $(this).addClass("rotateimg");
				var height = this.height;
				var width = this.width;
				if (height == undefined || height == null) {
					height = this.clientHeight;
				}
				if (width == undefined || width == null) {
					width = this.clientWidth;
				}
				$(this).attr('height', height);
				$(this).attr('width', width);
				if (width - height - 0 > 0) {
					$(this).after(
							'<div style="height:' + (width - height)
									+ 'px;"></div>');
				}

			});
};
