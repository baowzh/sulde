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
				// alert(currentsessioninfo.managerInfo.managername);
				$("#loginform").css("display", "none");
				$("#chatroom").css("display", "block");
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
		this.managerInfo = managerInfo
		this.refreshuserlist();
		// 出发用户列表更新程序
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
					+ '<li><a href="javascript:setcurrentuserinfo(' + i
					+ ')"> <span class="picture01"></span><span>'
					+ this.managerInfo.chatusers[i].phonenumber
					+ '</span></a></li>';
		}
		$("#userlist").html(userliststr);
	};
	this.setcurrentuserinfo = function(index) {// 设置当前用户信息
		$("#currentphone").html(
				'<em>手机号：</em>' + this.managerInfo.chatusers[index].phonenumber
						+ '');
		this.currentuser = this.managerInfo.chatusers[index];
	};
};
