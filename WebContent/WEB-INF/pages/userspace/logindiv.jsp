<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="logindiv" style="display: none">
	<div class="content rotatesection"
		style="padding-top: 30px; height: 230px; width: 270px;">
		<form action="addfriend.do" name="addfriendform" id="addfriendform"
			class="mglForm">
			<div class="m1ln" style="text-align: center;">  </div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="text" name="username" id="username"
					style="" />
			</div>
			<div class="m1ln" style="text-align: center;">  </div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="password" name="password" id="password1"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="m1ln" style="text-align: center;"> 
				<a href="javascript:replaceverifycode(1);"><img
					src="verifyCodeServlet" id="varifyimg1" style="width:18px;height:50px;"/> </a>
			</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="text" name="varifycode" id="varifycode1"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="m1ln" style="width: 270px;"></div>
			<div class="m1ln" style="width: 270px; text-align: center;">
				<a href="javascript:login();"> </a>
			</div>
		</form>
	</div>
</div>



