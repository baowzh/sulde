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
<link href="img/css/selectpanel.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/sitejs/uploaddoc.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
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
	<form id="addnews" action="adddoc.do" method="post">
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
						style="width: 90px; height: 520px; background: #def">
						<div class="label">  </div>
						<div class="label">   :</div>
						<div class="label">   :</div>
						<c:if test="${agentkind==1}">
							<div class="inputHolder">
								<input name="doctitle" id="doctitle" class="title"
									style="width: 210px; height: 15px;" />
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
							name="docid" id="docid" value="<c:out value="${docid}" />" /> <input
							type="hidden" name="userid" id="userid" value="" />
						<!-- 						<div class="inputHolder"> -->
						<div class=selectchannel>
							<div id="channel" class="channel"></div>
							<div class="selch">
								<a href="javascript:showselePanel(true);"></a>
							</div>
						</div>
						<input type="hidden" id="docchannel" name="docchannel" />
						<!-- 
							<select name="docchannel" id="docchannel"
								style="writing-mode: tb-rl; -webkit-writing-mode: vertical-lr; height: 210">
								<option>option 1</option>
								<option>option 1</option>
								<option>  </option>
							</select>
							 -->
						<!-- 						</div> -->
						<c:if test="${agentkind==1}">
							<div class="inputHolder">
								<input name="docabstract" id="docabstract" class="title"
									style="width: 210px; height: 15px;" />
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
					<div class="mnlist" style="width: 50px; height: 440px;">
						<div>
							<div class="mnlist"
								style="width: 35px; height: 440px; text-align: center;">
								<a href="javascript:openimgwindow();"> </a>&nbsp;&nbsp;
								&nbsp;&nbsp;
								<!-- 								<a href="javascript:openemfacewindow();"> -->
								<a href="JavaScript:void(0)" id="message_face"> <img
									src="img/pl_bq.png" />
								</a>
								<!-- 								</a> -->
								&nbsp;&nbsp; &nbsp;&nbsp; <a
									href="javascript:openvideowindow();"> </a>&nbsp;&nbsp;
								 &nbsp;&nbsp;<a href="javascript:openlinkwindow();">
									</a>
							</div>
						</div>
						<div>
							<div class="mnlist"
								style="width: 20px; height: 400px; text-align: center;">
								<a href="javascript:checkAndSubmit();"></a>&nbsp;&nbsp;
								<a href="javascript:openemfacewindow">&nbsp;&nbsp; </a>

							</div>
						</div>
					</div>

					<div class="cbt"></div>
				</div>
			</div>
		</div>
		<div class="lmainR ofh" style="text-align: center;">
			<div class="tailCard">
				<div class="msheet" style="height: 100px; width: 800px;">
					<%@ include file="../website/tail.jsp"%></div>
			</div>
			<div class="cbt"></div>
		</div>
		<div id="validcodediv" class="mnlist"
			style="width: 140px; height: 210px; display: none;">
			<table>
				<tr>
					<td>:</td>
					<td><img src="verifyCodeServlet" id="varifyimg" width="21"
						height="100" /></td>
				</tr>
				<tr>
					<td> :</td>
					<td><input type="input" name="validcode" id="validcode"
						style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; height: 100px; width: 18px;" /></td>
				</tr>
			</table>
			<br> <br>
					<div class="mnlist"
						style="width: 20px; height: 200px; text-align: center;">
						<a href="javascript:submit();"></a>&nbsp;&nbsp; <a
							href="javascript:openemfacewindow">&nbsp;&nbsp; </a>

					</div>
		</div>
		<%@ include file="dochiddendiv.jsp"%>
	</form>
	<!-- 	<div style="display: none;"> -->
	<div class="channelpanel" id="citys" style="display: none">
		<div class="paneltitle">
			<a href="javascript:showselePanel(false);" style="cursor: pointer">
				<img src="img/gbchange.png" width="15" height="16">
			</a>
		</div>
		<div class="channellist">

			<c:forEach items="${chanels}" var="channel" varStatus="status">
				<div class="mnlist" style="text-indent: 0px; height: 120px;">
					<a
						onclick="javascript:changeCity('<c:out value="${channel.channelid}"/>','<c:out value="${channel.chnlname}"/>')"
						style="cursor: pointer" class=""><c:out
							value="${channel.chnlname}" /></a>
				</div>
			</c:forEach>

			<div class="clear"></div>
		</div>
	</div>
	<!-- 	</div> -->
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
		// 弹出验证码框
		$("#validcodediv").dialog({
			height : 300,
			width : 210,
			resizable : false,
			modal : true

		});
		// 		if ($('#opertype').val() == 2) {
		// 			$('#addnews').action = "updatedoc.do";
		// 		} else {
		// 			$('#addnews').action = "adddoc.do";
		// 		}
		// 		document.getElementById("addnews").submit();
	}
	function submit() {

		if ($('#opertype').val() == 2 || $('#opertype').val() == 3) {
			$('#addnews')[0].action = "updatedoc.do";
		} else {
			$('#addnews')[0].action = "adddoc.do";
		}
		document.getElementById("addnews").submit();
	}
</SCRIPT>
</html>