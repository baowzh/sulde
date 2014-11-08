<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
</head>
<body>
	<div class="news1">
		<a name="svrgal"></a>
		<div class="ttl1 m1ln">
			<a href="#">   </a>
		</div>
		<div class=mnlist>
			<a href="articlelist.do">  </a>
		</div>
		<div class=mnlist>
			<a href="pagingimglist.do">  </a>
		</div>
		<div class="mnlist">
			<a href="userlist.do">   </a>
		</div>
		<div class="mnlist">
			<a href="commentlist.do"> </a>
		</div>
		<div class="mnlist">
			<a href="getopinions.do"> </a>
		</div>
		<div class="mnlist">
			<a href="javascript:password()">  </a>
		</div>
		<div class="mnlist">
			<a href="advert.do">   </a>
		</div>
		<div class="mnlist">
			<a href="votemanager.do"> </a>
		</div>
		<div class="mnlist">
			<a href="createsitepage.do"> </a>
		</div>
	</div>

	<div style="display: none">
		<div class="lcell" style="width: 140px; height: 430px;"
			id="astopwriting">
			<table border="0" style="margin: 1em auto;">
				<tr>
					<td height="100">
						<div class="m1ln h100" style="height: 140px;"> 
							</div>
					</td>
					<td>
						<div class="m1ln h100" style="height: 140px;">  
							:</div>
					</td>
					<td>
						<div class="m1ln h100" style="height: 140px;"> 
							:</div>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<div class="mfl">
							<input type="text" name="username" id="username"
								value="<c:out value="${userValue.username}"/>"
								readonly="readonly" />
						</div>
					</td>
					<td>
						<div class="mfl">
							<input type="password" name="oldpass" id="oldpass" value="" />
						</div>
					</td>
					<td>
						<div class="mfl">
							<input type="password" name="password" id="password" value="" />
						</div>
					</td>
					<td>
						<div class="m1ln h100">
							<a href="javascript:modifypass();"></a>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
<script language="javascript">
	function password() {
		$("#astopwriting").dialog({
			height : 450,
			width : 200,
			resizable : false,
			modal : true
		});
	}
	function modifypass() {
		// 校验工作及修改都放后台了
		var username = $("#username").val();
		if (username == null || username == '') {
			MessageWindow.showMess("     ");
			return;
		}
		var oldpass = $("#oldpass").val();
		if (oldpass == null || oldpass == '') {
			MessageWindow.showMess("     ");
			return;
		}
		var password = $("#password").val();
		if (password == null || password == '') {
			MessageWindow.showMess("      ");
			return;
		}

		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "modifyuserpass.do",// 请求的action路径
			data : {
// 				username : $("#username").val(),
				pass : $("#password").val(),
				oldpass : $("#oldpass").val()
			},
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { // 请求成功后处理函数。
				if (data.mess == '1') {
					MessageWindow.showMess("  ");
					window.location.href = "tologin.do";
				} else if (data.mess == '2') {

				} else if (data.mess == '3') {

				}
			}
		});
	}
</script>