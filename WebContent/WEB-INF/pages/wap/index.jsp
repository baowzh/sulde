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
			style="height: 90px; width: 30px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phonebloglist.do"></a>
		</div>
		<div class="emptyItem" style="height: 70px; float: right;"></div>
		<div class="mln"
			style="height: 90px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phonechannel.do"></a>
		</div>
	</div>
	<div id="slider" style="width: 100%;">
		<c:forEach items="${imgnews}" var="topDocumentValue"
			varStatus="status">
			<div>
				<a
					href="phonedetail.do?docid=<c:out value="${topDocumentValue.docid}"/>"><img
					class="tab_img"
					src="html/img/<c:out value="${topDocumentValue.docimg}"/>" /></a>
			</div>
		</c:forEach>
	</div>
	<div class="wrap content" style="padding-top: 2px; height: 350px;">
		<div class="listCardTtl ttlStl1" style="height: 350px;">
			<div class="mln" style="height: 146px; font-size: 21px;">
				</div>
		</div>
		<div class="listCardBody">
			<c:forEach items="${selecteddocs}" var="documentValue"
				varStatus="status">
				<div class="nwsl1">
					<div class="title">
						<a
							href="phonedetail.do?docid=<c:out value="${documentValue.docid}"/>"
							target="_blank" class="tit_text_overflow"><c:out
								value="${documentValue.title}" /></a>
					</div>
					<div class="author">
						<a
							href="phoneuserindex.do?userid=<c:out value="${documentValue.userid}" />">
							<c:out value="${documentValue.docauthor}" />
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="wrap content" style="padding-top: 2px; height: 350px;">
		<div class="listCardTtl ttlStl1" style="height: 350px;">
			<div class="mln" style="height: 146px; font-size: 21px;">
				</div>
		</div>
		<div class="listCardBody">
			<c:forEach items="${recentdocs}" var="documentValue"
				varStatus="status">
				<div class="nwsl1">
					<div class="title">
						<a
							href="phonedetail.do?docid=<c:out value="${documentValue.docid}"/>"
							target="_blank" class="tit_text_overflow"><c:out
								value="${documentValue.doctitle}" /></a>
					</div>
					<div class="author">
						<a
							href="phoneuserindex.do?userid=<c:out value="${documentValue.userid}" />">
							<c:out value="${documentValue.docauthor}" />
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="wrap content"
		style="height: 3px; text-align: center; background: #fff; color: #fff;"></div>
	<div class="wrap content"
		style="height: 30px; text-align: center; background: #42A7D7; color: #fff; padding-top: 3px; font-size: 13px; positon: fixed:bottom:0px;">
		版权所有 © 2014-2015</div>
</body>
</html>
