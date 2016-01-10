<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--HTML5 doctype-->
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
<link href="site/css/waplist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/waplist.js"></script>
</head>
<body onload="q();">
	<div class="header">
		<div style="width: 230px; height: 90px; float: left;">
<!-- 			<img src="site/img/phonehead.jpg" style="width: 104px; height: 90px;"> -->
		</div>
		<div class="moldiv" style="float: right;">
			<div class="navitem">
				<a href="phoneindex.do" style="font-size:18px;"> </a>
			</div>
			<div class="emptyItem"></div>
			<div class="navitem">
				<a href="phonechannel.do" style="font-size:18px;"> </a>
			</div>
			<div class="emptyItem"></div>
			<div class="navitem">
				<a href="phonebloglist.do" style="font-size:18px;"> </a>
			</div>
		</div>
	</div>
	<div class="wrap" id="wrap">
		<div class="main" id="main">
			<div class="mon_div">
				<ul class="montd" id="montdlist">
					<c:forEach items="${selecteddocs}" var="documentValue"
						varStatus="status">
						<li class="mon_title"><a
							href="phonedetail.do?docid=<c:out value="${documentValue.docid}"/>">
								<c:out value="${documentValue.doctitle}" />
						</a></li>
					</c:forEach>
				</ul>
			</div>
			<input type="hidden" name="docchannel" id="docchannel"
				value="<c:out value="${docchannel}"/>" />
		</div>
		<div class="footer">
			<div class="paginationArea">
				<div class="pagination" id="docpagelist">
					<c:out value="${pagingstr}" escapeXml="false" />
				</div>
			</div>
		</div>
	</div>
</body>

</html>
