<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/createDoc.css" type="text/css" rel="stylesheet" />
<link href="img/css/selectpanel.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/uploaddoc.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link rel="stylesheet" type="text/css"
	href="plugins/jquery.jqGrid-4.4.3/css/jquery-ui.css" media="screen" />
<script src="plugins/jquery.jqGrid-4.4.3/js/jquery-ui.min.js"
	type="text/javascript"></script>
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
		<div class="wrp m0a logo">
			<div class="naveFrame">
				<%@ include file="bloghead.jsp"%>
			</div>
			<div class="cbt"></div>
		</div>
<!-- 		<div class="wrp m0a ribbon"></div> -->
		<div class="lmainR  " style="width: 1000px;">
			<div class="roundCornerFrame"
				style="width: 1000px; height: 620px; margin: 0px;">
				<div class="content" id="contentdiv"
					style="width: 970px; height: 570px; padding-top: 30px;">
					<div class="flt"
						style="width: 95px; height: 520px; background: #def; border-radius: 5px; border: solid 1px #def; padding-top: 1px;">
						<div class="label">  </div>
						<div class="label">   :</div>
						<div class="label" style="height:150px;font-size:15px;">  (用汉子写微信转发使用):</div>
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
							<div id="channel" class="channel"></div>
							<div class="selch">
								<a href="javascript:showselePanel(true);"></a>
							</div>
						</div>
						<input type="hidden" id="docchannel" name="docchannel" />
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
						<textarea id="editor1" name="htmlcon" class="ckeditor" row="1200"
							cols="1200"></textarea>
					</div>
					
					<div class="mnlist" style="width: 70px; height: 570px;">
						<div>
							<div class="mnlist"
								style="padding-left: 10px; width: 40px; height: 570px;">
								<!--  
								 <a href="javascript:openimgwindow();"> &nbsp;</a>
								 -->
<!-- 								  <a -->
<!-- 									href="javascript:openaddimgwindow();"> &nbsp;</a> -->
								
								<iframe name="ad" src="toinderrtimg.do" width="25" height="340"
									frameborder=0 allowtransparency="true" scrolling=no></iframe>
								<a href="JavaScript:void(0)" id="message_face"> <img
									src="img/pl_bq.png" />
								</a> &nbsp; <a href="javascript:openvideowindow();">
									&nbsp;</a>
							</div>
						</div>
						<div>
							<div class="mnlist"
								style="width: 24px; height: 400px; text-align: center;">
								<a href="javascript:openlinkwindow();"> &nbsp;</a>
								<a href="javascript:openmp3window();">MP3 </a>&nbsp;&nbsp; <a
									href="javascript:submit();"></a>&nbsp;&nbsp;
								<a href="javascript:openemfacewindow">&nbsp;&nbsp; </a>

							</div>
						</div>
					</div>

					<div class="cbt"></div>
				</div>
			</div>
		</div>
		<div class="lmainR ofh" style="text-align: center; width: 1000px;">
			<%@ include file="../website/tail.jsp"%>
		</div>
		
		<div id="validcodediv"
			style="width: 140px; height: 210px; display: none;z-index:99;">
			<div class="content" style="width: 150px; height: 270px;">
				<form action="addfriend.do" name="addfriendform" id="addfriendform"
					class="mglForm">
					<!--  
					<div class="label" style="text-align: center;">
						<a href="javascript:replaceverifycode();"><img
							src="verifyCodeServlet" id="varifyimg" width="21" height="100" /></a>
					</div>
					<div class="label" style="text-align: center;"> :
					</div>
					<div class="inputHolder" style="width: 32px; height: 270px;">
						<input type="text" name="validcode" id="validcode"
							style="-webkit-transform-origin: 10px 20px;" />
					</div>
					-->
					<div class="mnlist" style="height: 270px;"></div>
					<div class="mnlist"
						style="width: 24px; height: 200px; text-align: center;">
						<a href="javascript:submit();"> </a>&nbsp;&nbsp; <a
							href="javascript:openemfacewindow">&nbsp;&nbsp; </a>

					</div>
				</form>
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
				<div class="mnlist"
					style="text-indent: 0px; height: 120px; width: 24px;">
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
			MessageWindow.showMess('       ');
			return false;
		}
		if (docabstract == null || docabstract == '') {
			MessageWindow.showMess('    ');
			return false ;
		}
		if (docchannel == null || docchannel == '') {
			MessageWindow.showMess('     ');
			return false ;
		}
		var editor1 = CKEDITOR.instances.editor1.getData();
		if (editor1 == null || editor1 == '') {
			MessageWindow.showMess('     ');
			return false ; 
		}
		return true;
		// 弹出验证码框
		/*
		$("#validcodediv").dialog({
			height : 370,
			width : 210,
			resizable : false,
			modal : true

		});*/
		// 		if ($('#opertype').val() == 2) {
		// 			$('#addnews').action = "updatedoc.do";
		// 		} else {
		// 			$('#addnews').action = "adddoc.do";
		// 		}
		// 		document.getElementById("addnews").submit();
	}
	function submit() {
        if(checkAndSubmit()){
        	if ($('#opertype').val() == 2 || $('#opertype').val() == 3) {
    			$('#addnews')[0].action = "updatedoc.do";
    		} else {
    			$('#addnews')[0].action = "adddoc.do";
    		}
    		document.getElementById("addnews").submit();
        }
		
	}
</SCRIPT>
</html>