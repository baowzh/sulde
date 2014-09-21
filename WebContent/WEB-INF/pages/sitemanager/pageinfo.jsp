<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css\comm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/sitejs/pageinfo.js"></script>
<script src="js/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"> </script>
<link href="css\comm.css" rel="stylesheet" type="text/css" />
<link href="js\jqGrid\css\ui.jqgrid.css" rel="stylesheet"
	type="text/css" />
<title>页面信息</title>
</head>
<body onload="showmess();">
<form id="manageForm" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td width="585" align="center">
		<table width="100%" cellspacing=0 valing="middel">
			<tr>
				<td width="13%">页面名称</td>
				<td width="38%"><input name="pagename" id="pagename"
					type="text" size="30" value="<c:out value="${pageInfo.pagename}"/>"
					disabled="disabled"></td>
				<td width="12%">父级页面</td>
				<td width="37%" id="parentpageidtd"><input name="parentpageid"
					id="parentpageid" type="text" size="30"
					value="<c:out value="${pageInfo.parentpageid}"/>"
					disabled="disabled"></td>
			</tr>
			<tr>
				<td>展现模板</td>
				<td id="showtemplateidtd"><input name="showtemplateid"
					id="showtemplateid" type="textAear" size="30"
					value="<c:out value="${pageInfo.showtemplateid}"/>"
					disabled="disabled"></td>
				<td>页面描述</td>
				<td><input name="comment" id="comment" type="text" size="30"
					value="<c:out value="${pageInfo.comment}"/>" disabled="disabled"></td>
				<a id="hhref" href=""></a>
				<input type="hidden" id="pageid" name="pageid"
					value="<c:out value="${pageInfo.pageid}"/>">
			</tr>
		</table>
		</td>
		<td width="10"></td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr stype="disply:none">
		<td align="center"><input type="button" value="保存"
			id="savebutton" onclick="savepageinfo()" style="display: none"></td>
	</tr>
</table>
<table id="jqgajax" width="585" cellspacing=0 cellpadding=0
			align="left"></table>
		<div id="pjqgajax"></div>
</form>

</body>
<SCRIPT LANGUAGE="JAVASCRIPT">
 
 function showmess(){
 <c:if test="${sucess==1}">
   alert("保存成功!");
   window.parent.refreshTree();
</c:if>
 }
 function addChannel(){
   toAddChannel();
 }
 
</SCRIPT>
</html>