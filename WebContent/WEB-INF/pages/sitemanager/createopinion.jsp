<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/createDoc.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
</head>
<body>
	<form id="addnews" action="addopinions.do" method="post">
		<div class="lmainR ofh" style="text-align: center; height: 300px;">
			<img src="img/01.jpg" width="980" />
		</div>
		<div class="lmainR ofh" style="text-align: center;">
			<%@ include file="../userspace/bloghead.jsp"%>
		</div>
		<div class="lmainR  ">
			<div class="roundCornerFrame">
				<div class="content">

					<div class="flt"
						style="width: 50px; height: 600px; background: #def">
						<div class="label"></div>
						<div class="inputHolder">
							<input name="title" id="title" class="title" />
						</div>
					</div>
					<div class="richeditBox">
						<textarea id="editor1" name="htmlcon" class="ckeditor" row="1300"
							cols="1300"></textarea>
					</div>
					<div class="send">
						<div class="inputHolder">
							<div style="height: 5em; width: 30px;"></div>
							<input type="button" onclick="javascript:checkAndSubmit();"
								value="" />
							<div style="height: 3em; width: 30px;"></div>
							<input type="button" value=" " />
						</div>
					</div>
					<div class="cbt"></div>
				</div>
			</div>
		</div>
		<div class="lmainR ofh" style="text-align: center;">
			<div class="tailCard">
				<div class="msheet" style="height: 100px; width: 800px;">
					        
					        
					        
					         
					         
					        
					        
					        </div>
			</div>
			<div class="cbt"></div>
		</div>
	</form>
</body>
<SCRIPT LANGUAGE="javaScript">
function checkAndSubmit(){
  // 判断新增还是修改
   document.getElementById("addnews").submit();
 }
 </SCRIPT>
</html>
