<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="site/css/index.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<title>上传图片</title>
</head>
<body onload="javascript:insertimg();">
	<form action="inderrtimg.do" id="insertimgform" class="mglForm"
		method="post" enctype="multipart/form-data">
		<div class="content" style="width: 40px; height: 340px;">
			<div class="label" style="text-align: center; height: 60px;">
				:</div>
			<div class="inputHolder" style="width: 32px; height: 230px;">
				<input type="file" name="imgurl" id="imgurl"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="label" style="text-align: center; height: 40px;">
				<a href="javascript:submitimg();"></a>
			</div>
		</div>
	</form>
</body>
<script>
	var pics1 = '<c:out value="${picurl}" escapeXml="false" />';
	var submitimg = function() {
		document.forms[0].submit();
	};
	var insertimg = function() {
		if (pics1 != '') {
			window.parent.insertlocalimg(pics1);
		}
	}
</script>
</html>