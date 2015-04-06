<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/login.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/regist.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,,height=device-height,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<title> </title>
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR  ">
		<div class="loginSheet">
			<div class="iconContainer"></div>
			<div class="border">
				<div class="loginWindow">
					<div class="content" style="width: 190px;">
						<form class="mglForm" action="doregiste.do" id="userinfoform"
							method="post">
							<div class="label">  </div>
							<div class="label">:</div>
							<div class="label">
								 (Email)<font color="#f00">*</font> 
							</div>
							<div class="label">  </div>
							<div class="label">
								  <a href="javascript:replaceverifycode();"></a>
								:
							</div>
							<div class="label">
								<a href="javascript:replaceverifycode();"><img
									src="verifyCodeServlet" id="varifyimg" width="18" height="100" />
								</a>
							</div>
							<div class="inputHolder">
								<input name="username" id="username" value="" maxlength="20"/>
							</div>
							<div class="inputHolder">
								<input name="artname" id="artname" value="" maxlength="30"/>
							</div>
							<div class="inputHolder">
								<input type="text" name="email" id="email"
									value="<c:out value="${userinfo.email}"/>" maxlength="40"></input>
							</div>
							<div class="inputHolder">
								<input name="password" id="password" type="password" maxlength="10"/>
							</div>
							<div class="inputHolder">
								<input name="varifycode" id="varifycode" value="" maxlength="10"/>
							</div>
							<div class="mnlist"
								style="height: 210px; text-indent: 0px; font-size: 16px;">
								<input name="agree" id="agree" type="checkbox"> 
								    
							</div>
							<div class="mnlist">
								<a href="javascript:doregist();"></a> &nbsp;&nbsp;<a
									href="index.do"></a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>