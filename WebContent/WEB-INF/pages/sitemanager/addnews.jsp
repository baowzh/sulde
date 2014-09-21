<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="content-type" />
<link href="css\comm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="ckfinder/ckfinder.js"></script>
<title>增加新闻</title>
</head>
<body>
<form id="addnews" action="addnews.do" method="post">
<table align="center" width="1100">
	<tr>
		<td>新闻标题</td>
		<td><input name="title" id="title" value="" size="160"></td>
	</tr>
	<tr>
		<td>新闻摘要</td>
		<td><input name="absct" id="absct" value="" size="160"></td>
	</tr>
	<tr>
		<td>类型</td>
		<td><select name="programkind" id="programkind" onchange="javascript:select()">
			<option value="11">图片新闻</option>
			<option value="12">新闻</option>
			<option value="13">动态</option>
			<option value="14">法制宣传</option>
			<option value="15">公告</option>
			<option value="16">政策文件</option>
		</select></td>
	</tr>
	  <tr id="imgtr">
		<td>图片地址</td>
		<td><input name="imgurl" id="imgurl" value="" size="160"></td>
	</tr>
	<tr>
		<td colspan=2>内容</td>
	</tr>
	<tr>
		<td colspan=2><textarea id="editor1" name="editor1"
			class="ckeditor" row="100" cols="100">&nbsp;</textarea></td>
	</tr>
	<tr>
		<td><input type="button" value="保存"
			onclick="javascript:checkAndSubmit()"></td>
		<td></td>
	</tr>
</table>
</form>
</body>
<SCRIPT LANGUAGE="JAVASCRIPT">
 <c:if test="${programid!=null}">
      var selectObject=document.getElementById("programkind");
      var length=selectObject.options.length;
      for(var i=0;i<length;i++){
        if(selectObject.options[i].value==<c:out value="${programid}" />){
        selectObject.options[i].selected=true;
        }
      }
 </c:if>
 function checkAndSubmit(){
  var absct=document.getElementById("absct");
  var title=document.getElementById("title");
  if(absct.value==null||absct.value==''){
    alert("请输入摘要!");
    return ;
  }
   if(title.value==null||title.value==''){
    alert("请输入标题!");
    return ;
  }
  document.getElementById("addnews").submit();
 }
 function select(){
    var selectObject=document.getElementById("programkind");
    var imgtr=  document.getElementById("imgtr");
    if(selectObject.value==11){
      document.getElementById("imgtr").style.display="";
    }else{
     document.getElementById("imgtr").style.display="none";
    }
    
 }
</SCRIPT>
</html>