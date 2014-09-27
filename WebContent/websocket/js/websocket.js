/**
 * websocket 链接实例
 */
var ws = null;
/**
 * 链接url
 */
var url = 'ws://127.0.0.1:8080/echo';
/**
 * 
 */
var transports = [];

var setConnected = function(connected) {
	document.getElementById('connect').disabled = connected;
	document.getElementById('disconnect').disabled = !connected;
	document.getElementById('echo').disabled = !connected;
};

var connect = function() {
	if (!url) {
		alert('没有找到链接地址。');
		return;
	}

	ws = (url.indexOf('sockjs') != -1) ? new SockJS(url, undefined, {
		protocols_whitelist : transports
	}) : new WebSocket(url);

	ws.onopen = function() {
		// setConnected(true);
		// log('Info: connection opened.');
	};
	ws.onerror = function(event) {
		// alert(event);
		// alert(readyState);
	}
	ws.onmessage = function(event) { // 当收到消息时候
		logreceivemess(event.data);
	};
	ws.onclose = function(event) {
		// setConnected(false);
		// log('Info: connection closed.');
		// log(event);
	};
};

var disconnect = function() {
	if (ws != null) {
		ws.close();
		ws = null;
	}
	// setConnected(false);
};

var echo = function() {
	if (ws != null) {
		var message = document.getElementById('message').value;
		logsendmess('Sent: ' + message);
		var jsonMess = '{"message":{"sourceid":"'
				+ currentsessioninfo.managerInfo.managerid + '","destid":"'
				+ currentsessioninfo.currentuser + '","content":"' + message
				+ '"}}';
		ws.send(jsonMess);
		document.getElementById('message').value = '';
	} else {
		alert('connection not established, please connect.');
	}
};

var updateUrl = function(urlPath) {
	if (urlPath.indexOf('sockjs') != -1) {
		url = urlPath;
		document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
	} else {
		if (window.location.protocol == 'http:') {
			url = 'ws://' + window.location.host + urlPath;
		} else {
			url = 'wss://' + window.location.host + urlPath;
		}
		document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
	}
};

var updateTransport = function(transport) {
	transports = (transport == 'all') ? [] : [ transport ];
};

var logsendmess = function(message) {
	var htmlStr = '<p><em class=\"lan\">'
			+ currentsessioninfo.managerInfo.managerid + '</em><span>'
			+ startTime() + '</span></p><p class=\"indent\">' + message
			+ '</p></li>';
	$("#console").append(htmlStr)
	var console = document.getElementById('console');
	console.scrollTop = console.scrollHeight;
};
var logreceivemess = function(message) {
	// 接受来自服务端的消息看是否有新用户加入
	var messjson = (new Function("return" + message))();
	var exist = false;
	// 如果数据里面携带了在线用户列表则更新列表 userids
	var userindex = '';
	if (messjson.userids != null && messjson.userids != undefined) {
		// 更新用户列表
		currentsessioninfo.onLineUserList = new Array();
		var userliststr = "";
		var indexi = 0;
		for (i in messjson.userids) {
			if (messjson.userids[i] != currentsessioninfo.managerInfo.managerid) {
				currentsessioninfo.onLineUserList.push(messjson.userids[i]);
				userliststr = userliststr
						+ '<li><a href="javascript:currentsessioninfo.setcurrentuserinfo('
						+ indexi + ');"> <span class="picture02"></span><span>'
						+ messjson.userids[i] + '</span></a></li>';
				indexi++;
			}
		}
		$("#userlist").html(userliststr);
	}
	for (i in currentsessioninfo.onLineUserList) {
		if (currentsessioninfo.onLineUserList[i] == messjson.message.sourceid) {
			userindex = i;
		}
	}
	if (userindex != '') {
		currentsessioninfo.setcurrentuserinfo(userindex);
		var htmlStr = '<p><em class=\"lv\">' + messjson.message.sourceid
				+ '</em><span>' + startTime()
				+ '</span></p><p class=\"indent\">' + messjson.message.content
				+ '</p></li>';
		$("#console").append(htmlStr)
		var console = document.getElementById('console');
		console.scrollTop = console.scrollHeight;
	}

};

/**
 * 校验用户是否已经登陆系统并且设置用户相关信息
 */
$(document).ready($(function() {
	checklogin();
}));

var checklogin = function() {

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : '../checklogin.jhtml',// 请求的action路径
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('链接服务异常，请稍后在试。');
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.login == '0') {
				$("#loginform").css("display", "block");
				$("#chatroom").css("display", "none");
			} else if (data.login == '1') {
				currentsessioninfo = new sessionfin();
				currentsessioninfo.setManagerInfo(data.managerValue);
				$("#loginform").css("display", "none");
				$("#chatroom").css("display", "block");
				url = url + "?managerid=" + data.managerValue.managerid;
				connect();
			}
		}
	})
};

/**
 * 客服用户登陆系统
 */
var login = function() {
	var checkcode = $("#checkcode").val();
	var username = $("#username").val();
	var pass = $("#pass").val();
	if (username == null || username == '') {
		alert('请输入用户名称。');
	}
	if (pass == null || pass == '') {
		alert('请输入密码。');
	}
	if (checkcode == null || checkcode == '') {
		alert('请输入验证码。');
	}
	// 用局部刷新登陆系统并生成相关的客户端session信息
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : '../chatroomlog.jhtml',// 请求的action路径
		data : {
			username : $("#username").val(),
			pass : $("#pass").val(),
			checkcode : $("#checkcode").val()
		},
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('链接服务异常，请稍后在试。');
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '0') {
				alert(data.mess);
			} else if (data.success == '1') {
				currentsessioninfo = new sessionfin();
				currentsessioninfo.setManagerInfo(data.managerValue);
				$("#loginform").css("display", "none");
				$("#chatroom").css("display", "block");
				url = url + "?managerid=" + data.managerValue.managerid;
				connect();
			}

		}
	});

	$("#loginform").submit();
};

var currentsessioninfo = null;
/**
 * 用户登陆以后的相关信息
 */
var sessionfin = function() {
	this.managerInfo = null;
	this.currentuser = null;
	this.setManagerInfo = function(managerInfo) {
		this.managerInfo = managerInfo;
	};
	this.users = null;
	this.setUsers = function(usernames) {
		this.users = usernames;
	};
	this.status = null;
	this.setStatus = function(userStatus) {
		this.status = userStatus;
	};
	this.refreshuserlist = function() {
		var userliststr = "";
		for (i in this.managerInfo.chatusers) {
			userliststr = userliststr
					+ '<li><a href="javascript:currentsessioninfo.setcurrentuserinfo('
					+ i + ')"> <span class="picture01"></span><span>'
					+ this.managerInfo.chatusers[i].phonenumber
					+ '</span></a></li>';
		}
		if (userliststr != '') {
			this.setcurrentuserinfo(0);
		}
		$("#userlist").html(userliststr);
	};
	this.setcurrentuserinfo = function(index) {// 设置当前用户信息
		$("#currentphone").html(
				'<em>手机号：</em>' + this.onLineUserList[index] + '');
		this.currentuser = this.onLineUserList[index];
	};
	this.onLineUserList = new Array();
};

function startTime() {
	var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	m = checkTime(m);
	s = checkTime(s);
	return '' + h + ":" + m + ":" + s;
}
function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}
