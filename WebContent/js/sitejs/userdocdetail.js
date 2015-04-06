$(document).ready(function() {
	// 放新浪微博表情
	$("#message_face").jqfaceedit({
		txtAreaObj : $("#commentdiv"),
		containerObj : $('#commentcontainer'),
		top : 25,
		left : -27
	});
	CKEDITOR.config.height = 400;
});
/**
 * 给文章增加留言
 */
var addcomment = function(dtype, hidden) {
	var islogin = checklogin();
	if (islogin == false) {
		openloginwin();
		return;
	}
	//
	var validcode = $("#validcode").val();
	if (validcode == null || validcode == '') {
		MessageWindow.showMess('     ');
		return;
	}
	var commentdiv = '';
	var agentkind = $('#agentkind').val();
	// if(agentkind=='1'){
	commentdiv = CKEDITOR.instances.editor1.getData();
	// }
	// if(agentkind=='0'){
	// commentdiv= $('#commentdiv').val();
	// }

	// var commentdiv = $("#commentdiv").html();
	// var text = $('<div></div>').html('' + commentdiv).text();
	// $("#comment").val(text)
	// var comment = $("#comment").val();
	if (commentdiv == null || commentdiv == '') {
		MessageWindow.showMess("        ");
		return;
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "addCommentOnResource.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : $("#docid").val(),
			// comment : $("#comment").val(),
			comment : commentdiv,
			userid : $("#userid").val(),
			doctype : dtype,
			ishidden : hidden,
			validcode : validcode
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == 1) {
				MessageWindow.showMess('     ');
				$("#comment").val('');
				// $("#commentdiv").html('');
				if (agentkind == '0') {
					$('#commentdiv').val('');
				} else {
					$('#editor1').val('');
				}
				CKEDITOR.instances.editor1.setData(' ')
				// CKEDITOR.instances.editor1.body.innerText='';
				loaddoccomment();
				$("#userid").val('');
				$("#validcode").val('')
				setwindowheight();
			} else {
				if (data.errorcode == 0) {
					MessageWindow
							.showMess('        ');
				} else if (data.errorcode == 1) {
					MessageWindow.showMess('      ');
				}
			}

		}
	});

};
var readdoc = function(documentid) {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "readdoc.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : documentid
		},
		success : function(data) { // 请求成功后处理函数。

			$("#docid").val(data.documentValue.docid);
			$("#doctitle").html(data.documentValue.doctitle);

			if (data.documentValue.sharecount == null) {
				$("#sharecount").text(0);
			} else {
				$("#sharecount").text(data.documentValue.sharecount);
			}
			if (data.documentValue.markcount == null) {
				$("#markcount").text(0);
			} else {
				$("#markcount").text(data.documentValue.markcount);
			}
			$("#readcount").text(data.documentValue.readcount);
			$("#commentCount").text(data.documentValue.commentCount);

			$("#doccontent").html(data.documentValue.htmlstr);
			loaddoccomment();
			setheight();
		}
	});

};
/**
 * 加载文章对应的留言信息
 */
var loaddoccomment = function() {

	$
			.ajax({
				async : true,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "getDocComment.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					doctype : $("#doctype").val(),
					docid : $("#docid").val()
				},
				success : function(data) { // 请求成功后处理函数。
					var innerHTML = '';
					for (i in data.comments) {// the channel
						// 给div popDetail添加子div元素
						innerHTML = innerHTML
								+ '<div class=\"postSheet\" style=\"float: left; height: 500px;\">'
								+ '<div class=\"posterInf\" style=\"float: left; height: 500px;\">'
								+ '<div class=\"avtThumb flt\"><img src=\"html/userhead/'
								+ data.comments[i].messagesenderid
								+ '.jpg \" width=\"300\" height=\"500\" /></div>'
								+ '<div class=\"inf flt\">'
								+ '<div class=\"row\"> <a href=\"gouserindex.do?userid='
								+ data.comments[i].messagesenderid + '\">'
								+ data.comments[i].messagesendername
								+ '</a><br />' + '<br />'
								+ data.comments[i].sendtime + '</div>'
								+ '<div class=\"row\"> </div>' + '</div>'
								+ '</div>' + '<p>'
								+ data.comments[i].contenthtml + '</p>';
						if (data.self == '1') {
							innerHTML = innerHTML
									+ '<p><a href=\"javascript:delcomment(\''
									+ data.comments[i].messageid
									+ '\');\"></a><a href=\"javascript:writemess(\''
									+ data.comments[i].messagesenderid
									+ '\',\''
									+ data.comments[i].messagesendername
									+ '\');\">&nbsp;&nbsp; </a></p>';

						}
						innerHTML = innerHTML + '</div>';
					}
					$("#commentlist").html(innerHTML);
					setpagewidth();
				}
			});

};
/**
 * 加载文章对应的留言信息
 */
var sharedocument = function() {
	var islogin = checklogin();
	if (islogin == false) {
		openloginwin();
		return;
	}
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "shareUserresource.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			resouceid : $("#docid").val(),
			resoucekind : $("#doctype").val()
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '0') {
				MessageWindow.showMess(data.message);
			} else {
				MessageWindow.showMess('   ');
				// 更新文章分享次数
				$("#sharecount").html(data.documentValue.sharecount);
			}
		}
	});

};
/**
 * 加载文章对应的留言信息
 */
var markdocument = function() {
	var islogin = checklogin();
	if (islogin == false) {
		openloginwin();
		return;
	}
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "markUserresource.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			resouceid : $("#docid").val(),
			resoucekind : $("#doctype").val()
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '0') {
				MessageWindow.showMess(data.message);
			} else {
				MessageWindow.showMess('   ');
				$("#markcount").html(data.documentValue.markcount);
			}
		}
	});

};

var deldoc = function(docType) {
	// 缺少提示信息
	var mess = "";
	if (docType == 1) {
		mess = "          <br>"
				+ "        ";
	} else {
		mess = "          <br>"
				+ "       ";
	}

	showConfirmMess(mess, function() {
		if (this.getValue() == true) {
			deletedo(docType);
		}
	});
};
var deletedo = function(docType) {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "deletedoc.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : $("#docid").val(),
			doctype : docType
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '0') {
				MessageWindow.showMess(' ');
				// $("#markcount").html(data.documentValue.markcount);
				window.location.href = "gouserindex.do";
			} else {
				MessageWindow.showMess(data.message);
			}
		}
	});
}
/**
 * 
 */
var delcomment = function(messid) {
	// 缺少提示信息
	showConfirmMess("      ", function() {
		if (this.getValue() == true) {
			deleteComm(messid);
		}
	});

};
var deleteComm = function(messid) {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "delresourcecomment.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : $("#docid").val(),
			messid : messid
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '0') {
				MessageWindow.showMess(data.message);
			} else {
				MessageWindow.showMess(' ');
				// $("#markcount").html(data.documentValue.markcount);
				loaddoccomment();
			}
		}
	});

};
var writemess = function(senderid, sendername) {
	// 缺少提示信息
	$("#userid").val(senderid);
	$("#comment").val(sendername + '  ');
};
var addemotion = function(emotionname) {
	var img = "<img src=\"img/faces/" + emotionname + ".gif\"/>";
	CKEDITOR.instances.editor1.insertHtml(img);
	/*
	 * var agentkind = $("#agentkind").val(); if (agentkind == '1') {
	 * $("#commentdiv").html( $("#commentdiv").html() + $('<p>[' + emotionname + ']</p>').html()); }
	 * else { $("#commentdiv") .text($("#commentdiv").text() + '[' + emotionname +
	 * ']'); }
	 */

};
/**
 * 打开后一个图片
 */
var nextdoc = function() {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getnextdoc.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : $("#docid").val(),
			imggroupid : $("#imggroupid").val(),
			idAndIndexrel : $("#idAndIndexrel").val()
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.exists == '1') {
				// $("#displayimg").html('<img
				// src=\"getimg.do?imgid='+data.nextDoc.docid+'\" />');
				$("#imgtitle").text(data.nextDoc.doctitle);
				$("#disimg").attr("src",
						"getimg.do?imgid=" + data.nextDoc.docid);
				$("#docid").val(data.nextDoc.docid);
				if (data.nextDoc.sharecount == null) {
					$("#sharecount").text(0);
				} else {
					$("#sharecount").text(data.nextDoc.sharecount);
				}
				if (data.nextDoc.markcount == null) {
					$("#markcount").text(0);
				} else {
					$("#markcount").text(data.nextDoc.markcount);
				}

				$("#readcount").text(data.nextDoc.readcount);
				$("#commentCount").text(data.nextDoc.commentCount);
				loaddoccomment();
			} else {
				MessageWindow.showMess(data.message);
			}
		}
	});

};
/**
 * 获取上一个图片
 */

var previousdoc = function() {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getpreviousdoc.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : $("#docid").val(),
			imggroupid : $("#imggroupid").val(),
			idAndIndexrel : $("#idAndIndexrel").val()
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.exists == '1') {
				// $("#displayimg").html('<img
				// src=\"getimg.do?imgid='+data.previosdoc.docid+'\" />');
				$("#imgtitle").text(data.previosdoc.doctitle);
				$("#disimg").attr("src",
						"getimg.do?imgid=" + data.previosdoc.docid);
				$("#docid").val(data.previosdoc.docid);
				// 修改分享数和收藏数
				if (data.previosdoc.sharecount == null) {
					$("#sharecount").text(0);
				} else {
					$("#sharecount").text(data.previosdoc.sharecount);
				}
				if (data.previosdoc.markcount == null) {
					$("#markcount").text(0);
				} else {
					$("#markcount").text(data.previosdoc.markcount);
				}

				$("#readcount").text(data.previosdoc.readcount);
				$("#commentCount").text(data.previosdoc.commentCount);
				loaddoccomment();
			} else {
				MessageWindow.showMess(data.message);
			}
		}
	});

};
/*
 * var chgUrl = function(imgid) { var timestamp = (new Date()).valueOf(); url =
 * 'getimg.do?imgid=' + imgid + '&timestamp=' + timestamp; alert(url); return
 * url; };
 */
var test = function() {
	//
	showConfirmMess("             "
			+ "        <br>     "
			+ "         ", function() {
		if (this.getValue() == true) {

		}

	});
	//

	//
};
/*
 * var replaceverifycode = function(id) { var imgSrc='';
 * if(id!=null&&id!=undefined){ imgSrc = $("#varifyimg"+id); }else{ imgSrc =
 * $("#varifyimg"); } var src = imgSrc.attr("src"); imgSrc.attr("src",
 * changeurl(src)); };
 * 
 * function changeurl(url) { var timestamp = (new Date()).valueOf(); url =
 * url.substring(0, 17); if ((url.indexOf("&") >= 0)) { url = url + "¡Átamp=" +
 * timestamp; } else { url = url + "?timestamp=" + timestamp; } return url; };
 */
function refreshdata(index) {
	var obj1 = $('#imgsharecount' + index).val();
	$("#imgtitle").text($("#imgtitle" + index).val());
	$("#docid").val($("#cimgid" + index).val());
	$("#sharecount").text($("#imgsharecount" + index).val());
	$("#markcount").text($("#imgmarkcount" + index).val());
	$("#readcount").text($("#imgreadcount" + index).val());
	$("#commentCount").text($("#imgcommentCount" + index).val());
	$("#doctime").text($("#crtime" + index).val());
	loaddoccomment();

};
/**
 * 
 */
var upload = function() {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "uploaddoc.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : $("#docid").val()
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '1') {
				MessageWindow.showMess('    ');
				// $("#markcount").html(data.documentValue.markcount);
				window.location.href = "gouserindex.do";
			} else {
				MessageWindow.showMess(data.message);
			}
		}
	});
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

