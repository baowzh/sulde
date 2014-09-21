<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css\comm.css" rel="stylesheet" type="text/css" />
<title>增加网页模板</title>
</head>
<body>
<form id="manageForm" method="post" enctype="multipart/form-data"><br>
<table width="60%" cellspacing=0 valing="" middel"  align="center">
	<tr>
		<td width="11%">模板名称</td>
	  <td width="33%"><input name="tempname" type="text" size="30"></td>
		<td width="11%">模板类型</td>
		<td width="45%"><select name="temptype" size="1">
			<option value="0">请选择</option>
			<option value="1">列表模板</option>
			<option value="2">概览模板</option>
			<option value="3">嵌套模板</option>
	  </select></td>
	</tr>
	<tr rowspan=2>
		<td>模板描述</td>
		<td><input name="tempdesc" type="textAear"></td>
		<td>模板文件</td>
		<td><input type="file" name=tempfile1></td>

	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
<table width="65%" cellspacing=0 valing="" middel"  align="center">
	<tr>
		<td align="center"><input type="button" value="保存"
			onclick="javascript:addprogram()"> <input type="button"
			value="返回"></td>
	</tr>
</table>
</form>
</body>
<SCRIPT LANGUAGE="JAVASCRIPT">
 function  addprogram(){ 
  document.getElementById("manageForm").action="addtemplate.do";
  document.getElementById("manageForm").submit();
 }
</SCRIPT>
</html>