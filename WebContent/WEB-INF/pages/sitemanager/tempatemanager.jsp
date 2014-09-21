<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" href="css/channel/channel.css" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/sitejs/templatemanager.js" type="text/javascript"> </script>
<title>网站模板管理</title>
</head>
<body onload="javascript:showmess()">
	<form id="manageForm" method="post">
		<br>
		<table id="channelList" class=channels>
			<tr>
				<th style="width: 2em;">序号</th>
				<th style="width: 4em;">选择</th>
				<th>模板名称</th>
				<th>模板描述</th>
				<th>模板类型</th>
			</tr>
			<c:forEach items="${templates}" var="templateValue"
				varStatus="status">
				<tr class="rec">
					<td align="center"><c:out value="${status.index+1}" /></td>
					<td align="center"><input type="checkbox" name="checkbox"
						id="checkbox<c:out
							value="${templateValue.tempid}" />" /></td>
					<td align="center"><c:out value="${templateValue.tempname}" /></td>
					<td align="center"><c:out value="${templateValue.tempdesc}" /></td>
					<td align="center"><c:out value="${templateValue.temptypestr}" /></td>
					<input type="hidden" id="deleteids" name="deleteids" value="" />
					<input type="hidden" id="tempid" name="tempid"
						value="<c:out value="${templateValue.tempid}"/>" />
				</tr>
			</c:forEach>
		</table>
		<br>
		<table width="100" cellspacing=2 cellpadding=2 align="center">
			<tr>
				<td><input type="button" value="新增"
					onclick="javascript:toTempAdd()"></td>
				<td><input type="button" value="修改"
					onclick="javascript:toTempModify()"></td>
				<td><input type="button" value="删除"
					onclick="javascript:deleteTemp()"></td>
			</tr>
		</table>
	</form>
	<!--网页模板增加-->
	<div id="dialog-form" title="增加网页模板">
		<p class="validateTips">所有字段必填。</p>
		<form id="manageForm1" method="post" enctype="multipart/form-data">
			<fieldset>
				<label for="name">模板名称</label> <input type="text" name="tempname"
					id="tempname" class="text ui-widget-content ui-corner-all" /> <label
					for="name">模板类型</label> <select name="temptype" size="1">
					<option value="0">请选择</option>
					<option value="1">列表模板</option>
					<option value="2">概览模板</option>
					<option value="3">嵌套模板</option>
				</select> <label for="name">模板描述</label> <input type="textAear"
					name="tempdesc" id="tempdesc"
					class="text ui-widget-content ui-corner-all" /> <label for="name">模板文件</label>
				<input type="file" name="tempfile1" id="tempfile1"
					class="text ui-widget-content ui-corner-all" />
		</form>
	</div>
</body>
<SCRIPT LANGUAGE="JAVASCRIPT">
/**
 function  toTempAdd(){ 
   document.getElementById("manageForm").action="toTempAdd.do";
   document.getElementById("manageForm").submit();
 }
 function  toTempModify(){
 
 }*/
 
 function  toProgramManage(){
  document.getElementById("manageForm").action="toProgramManage.do";
  document.getElementById("manageForm").submit();
 }
 
 function showmess(){
   <c:if test="${mess!=null}">
      alert("<c:out value="${mess}"/>");
      
   </c:if>
 }
</SCRIPT>
</html>