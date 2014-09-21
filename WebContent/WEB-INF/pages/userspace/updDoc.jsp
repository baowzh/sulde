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
<!--<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>-->
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/sitejs/uploaddoc.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<!--<script type="text/javascript" src="js/sitejs/ckplugin/video/video.js"></script>-->
<!--<script type="text/javascript" src="js/sitejs/ckplugin/emface/emface.js"></script>-->
<!--<script type="text/javascript" src="js/sitejs/ckplugin/img/img.js"></script>-->
<style>
.cke_button__video_icon {
	display: inline !important;
}

.cke_button__video_label {
	display: inline !important;
}

.cke_button__emface_icon {
	display: inline !important;
}

.cke_button__emface_label {
	display: inline !important;
}

.cke_button__img_icon {
	display: inline !important;
}

.cke_button__img_label {
	display: inline !important;
}
</style>
</head>
<body>
	<form id="upddocform" action="updatedoc.do" method="post">
		<div class="lmainR ofh" style="text-align: center; height: 64px;">
			<img src="img/logo.png" width="980" />
		</div>
		<div class="lmainR ofh" style="text-align: center;">
			<%@ include file="bloghead.jsp"%>
		</div>
		<div class="lmainR  ">
			<div class="roundCornerFrame">
				<div class="content" id="contentdiv">

					<div class="flt"
						style="width: 90px; height: 600px; background: #def">
						<div class="label">  </div>
						<div class="label">   :</div>
						<div class="label">   :</div>
						<c:if test="${agentkind==1}">
							<div class="inputHolder">
								<input name="doctitle" id="doctitle" class="title"
									style="width: 210px; height: 16px;" />
							</div>
						</c:if>
						<c:if test="${agentkind==0}">
							<div class="inputHolder">
								<input name="doctitle" id="doctitle" class="title"
									style="height: 210px;" />
							</div>
						</c:if>
						<input type="hidden" name="opertype" id="opertype"
							value="<c:out value="${opertype}" />" /> <input type="hidden"
							name="docid" id="docid" value="<c:out value="${docid}" />" />
						<div class="inputHolder">
							<select name="docchannel" id="docchannel"
								style="writing-mode: tb-rl; -webkit-writing-mode: vertical-lr">
								<option>option 1</option>
								<option>option 1</option>
								<option>  </option>
							</select>
						</div>
						<c:if test="${agentkind==1}">
							<div class="inputHolder">
								<input name="docabstract" id="docabstract" class="title"
									style="width: 210px; height: 16px;" />
							</div>
						</c:if>
						<c:if test="${agentkind==0}">
							<div class="inputHolder">
								<input name="docabstract" id="docabstract" class="title"
									style="height: 210px;" />
							</div>
						</c:if>
					</div>

					<div class="richeditBox">
						<textarea id="editor1" name="htmlcon" class="ckeditor" row="1300"
							cols="1300"></textarea>
					</div>
					<div class="mnlist" style="width: 50px;">
						<div>
							<div class="inputHolder" style="width: 35px;">
								<a href="javascript:openimgwindow();"> </a> 
<!-- 								<a -->
<!-- 									href="javascript:openemfacewindow"> 1</a> -->
									<a href="JavaScript:void(0)" id="message_face">
								<img src="img/pl_bq.png"/>
								</a>
									 <a
									href="javascript:openvideowindow"> </a> <a
									href="javascript:openlinkwindow"> </a>
							</div>
						</div>
						<div>
							<div class="inputHolder" style="width: 15px;">
								<a href="javascript:checkAndSubmit();"></a> <a
									href="javascript:openemfacewindow"></a>

							</div>
						</div>
					</div>

					<!-- 					<div class="send"> -->
					<!-- 						<div class="inputHolder"> -->
					<!-- 							<div style="height: 1em; width: 30px;"></div> -->
					<!-- 							<input type="button" onclick="javascript:openimgwindow();" -->
					<!-- 								value=" " /> -->
					<!-- 							<div style="height: 1em; width: 30px;"></div> -->
					<!-- 							<input type="button" onclick="javascript:openemfacewindow();" -->
					<!-- 								value="1 " /> -->
					<!-- 							<div style="height: 1em; width: 30px;"></div> -->
					<!-- 							<input type="button" onclick="javascript:openvideowindow();" -->
					<!-- 								value=" " /> -->
					<!-- 							<div style="height: 1em; width: 30px;"></div> -->
					<!-- 							<input type="button" onclick="javascript:openlinkwindow();" -->
					<!-- 								value=" " /> -->
					<!-- 							<div style="height: 1em; width: 30px;"></div> -->
					<!-- 							<input type="button" onclick="javascript:checkAndSubmit();" -->
					<!-- 								value="" /> -->
					<!-- 							<div style="height: 1em; width: 30px;"></div> -->
					<!-- 							<input type="button" value=" " /> -->
					<!-- 						</div> -->
					<!-- 					</div> -->
					<div class="cbt"></div>
				</div>
			</div>
		</div>
		<div class="lmainR ofh" style="text-align: center;">
			<div class="tailCard">
				<div class="msheet" style="height: 100px; width: 800px;">
					<%@ include file="../website/tail.jsp"%>
				</div>
			</div>
			<div class="cbt"></div>
		</div>
		<%@ include file="dochiddendiv.jsp"%>
	</form>
</body>
<SCRIPT LANGUAGE="JAVASCRIPT">
	function checkAndSubmit() {
		// 判断新增还是修改
		var doctitle = $("#doctitle").val();
		var docabstract = $("#docabstract").val();
		var docchannel = $("#docchannel").val();
		if (doctitle == null || doctitle == '') {
			MessageWindow.showMess('          ');
			return;
		}
		if (docabstract == null || docabstract == '') {
			MessageWindow.showMess('        ');
			return;
		}
		if (docchannel == null || docchannel == '') {
			MessageWindow.showMess('         ');
			return;
		}
		var editor1 = CKEDITOR.instances.editor1.getData();
		if (editor1 == null || editor1 == '') {
			MessageWindow.showMess('         ');
			return;
		}
		$('#upddocform')[0].action = "updatedoc.do";
		document.getElementById("upddocform").submit();
	}
</SCRIPT>
</html>
<!--<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>-->