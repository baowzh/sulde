/**
 * 添加朋友
 */
var addfriends = function(visiteduserid) {
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
					userid : visiteduserid
				},
				success : function(data) { // 请求成功后处理函数。
					alert(data.mess);
					$("#addfrienddiv").dialog("close");
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
					alert(data.mess);
					$("#messdiv").dialog("close");
				}
			});

};

var writemessage = function() {
	$("#messdiv").dialog({
				height : 480,
				width : 600,
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
	// $("#messagediv").html($("#messagediv").html()
	// + $('<p><img src=\"html/img/faces/' + emotionname + '.png\"/></p>')
	// .html());
	$("#messagediv").html($("#messagediv").html()
			+ $('<p>[' + emotionname + ']</p>').html());
};
/**
 * 列出全部别人写给自己的和自己发给别人的信
 */
var receivemessage = function() {
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getmessages.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
			var innerHTML = '';
			for (i in data.receiveMess) {
				innerHTML = innerHTML
						+ ' <div class=\"xldgurg\" style=\"width: 124px; height: 470px;\"> '
						+ ' <div class=\"avtr\"><a '
						+ ' href=\"gouserindex.do?userid= '
						+ data.receiveMess[i].messagesenderid
						+ ' \"> '
						+ ' <img '
						+ ' src=\"getsmheadimge.do?userid='
						+ data.receiveMess[i].messagesenderid
						+ '\"'
						+ ' width=\"570\" height=\"447\" />'
						+ ' </a> <input type=\"checkbox\" name=\"selectbox\"'
						+ ' id=\"'
						+ data.receiveMess[i].messagesenderid
						+ ' \"/>'
						+ ' </div>'
						+ ' <div class=\"desc  \" style=\"color: #000; height: 370px;widht:120px;\">'
						+ ' <div class=\"m1ln\" style=\"height: 350px;\">'
						+ ' <a'
						+ ' href=\"gouserindex.do?userid='
						+ data.receiveMess[i].messagesenderid
						+ ' \">'
						+ data.receiveMess[i].artname
						+ '  </a>'
						+ ' </div>'
						+ ' <div class=\"m1ln\" style=\"height: 350px;width:100px;\" >'
						+ data.receiveMess[i].contenthtml + '</div>' + '</div>'
						+ ' </div>';
			}
			$("#receivediv").html(innerHTML);
			//
			innerHTML = "";
			for (i in data.sendMess) {
				innerHTML = innerHTML
						+ ' <div class=\"xldgurg\" style=\"width: 124px; height: 470px; !important;\"> '
						+ ' <div class=\"avtr\"><a '
						+ ' href=\"gouserindex.do?userid= '
						+ data.sendMess[i].userid
						+ ' \"> '
						+ ' <img '
						+ ' src=\"getsmheadimge.do?userid='
						+ data.sendMess[i].userid
						+ '\"'
						+ ' width=\"570\" height=\"447\" />'
						+ ' </a> <input type=\"checkbox\" name=\"selectbox\"'
						+ ' id=\"'
						+ data.sendMess[i].userid
						+ ' \"/>'
						+ ' </div>'
						+ ' <div class=\"desc  \" style=\"color: #000; height: 370px;widht:120px;!important;\">'
						+ ' <div class=\"m1ln\" style=\"height: 350px;\">'
						+ ' <a'
						+ ' href=\"gouserindex.do?userid='
						+ data.sendMess[i].userid
						+ ' \">'
						+ data.sendMess[i].artname
						+ '   </a>'
						+ ' </div>'
						+ ' <div class=\"m1ln\" style=\"height: 350px;width:100px; !important;\" >'
						+ data.sendMess[i].contenthtml + '</div>' + '</div>'
						+ ' </div>';
			}
			$("#senddiv").html(innerHTML);
			$("#accordion").dialog({
						height : 510,
						width : 1000,
						resizable : false,
						modal : true

					});
		}
	});

};
/**
 * 切换div
 * @param {} currentdivid
 * @param {} nextdivid
 */
var switchdiv = function(currentdivid,nextdivid) {
	var display= $("#"+currentdivid).css("display");
	if(display!='none'){
	 $("#"+currentdivid).css("display","none");
	 $("#"+nextdivid).css("display","block");
	}else{
	 $("#"+currentdivid).css("display","block");
	 $("#"+nextdivid).css("display","none");	
	}
};
/**
 * 显示用户信息
 */
var showuserinfo = function(userid) {
	$("#userinfo").dialog({
				height : 480,
				width : 900,
				resizable : false,
				modal : true

			});

};
/**
 * 
 */
var openaddfrienddl = function() {
	$("#addfriendmess").val('          ');
	$("#addfrienddiv").dialog({// addfriendmess 13347126631
		height : 480,
		width : 190,
		resizable : false,
		modal : true

	});

};