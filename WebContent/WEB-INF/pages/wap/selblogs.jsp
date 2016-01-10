<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,,height=device-height,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<title>tegri</title>
<link href="site/css/wapindex.css" rel="stylesheet" type="text/css" />
<link href="site/css/slider.css" rel="stylesheet" type="text/css" />
<link href="site/css/slider.default.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/wapjs/zepto.js"></script>
<script type="text/javascript" src="js/wapjs/touch.js"></script>
<script type="text/javascript" src="js/wapjs/zepto.extend.js"></script>
<script type="text/javascript" src="js/wapjs/zepto.ui.js"></script>
<script type="text/javascript" src="js/wapjs/slider.js"></script>
<script type="text/javascript" src="js/sitejs/wapindex.js"></script>
</head>
<body onload="q();">
	<div class="wrap header">
		<div style="float: left; width: 240px;">
<!-- 			<img src="site/img/phonehead.jpg" style="height: 90px; width: 104px;"> -->
		</div>
		<div class="mln"
			style="height: 80px; width: 30px; font-size: 18px; float: right; color: #fff;">
			<a href="phonebloglist.do"></a>
		</div>
		<div class="emptyItem" style="height: 70px; float: right;"></div>
		<div class="mln"
			style="height: 80px; font-size: 18px; float: right; color: #fff;">
			<a href="phoneindex.do"> </a>
		</div>
	</div>
	<div class="wrap content" style="min-height: 460px; padding-left: 8px;">
		<c:forEach items="${users}" var="userValue" varStatus="status">
			<div class="xldgurg">
				<div class="avtr">
					<a href="phoneuserindex.do?userid=<c:out value="${userValue.userid}" />"><img
						src="html/userhead/<c:out value="${userValue.headurl}" />  ">
					</a>
				</div>
				<div class="m1ln" style="float: left;height:90px;overflow:hidden;">
					<a href="phoneuserindex.do?userid=<c:out value="${userValue.userid}" />">
						&nbsp;<c:out value="${userValue.artname}" />
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="wrap content"
		style="height: 30px; text-align: center; background: #fff; color: #fff;">
		<div class="footer">
			<c:out value="${pagestr}" escapeXml="false" />
		</div>
	</div>

	<!--  
	<div class="wrap content"
		style="height: 3px; text-align: center; background: #fff; color: #fff;"></div>
	<div class="wrap content"
		style="height: 30px; text-align: center; background: #d29130; color: #fff; padding-top: 3px; font-size: 13px;">
		版权所有 © 2014-2015</div>
		-->
</body>
</html>
