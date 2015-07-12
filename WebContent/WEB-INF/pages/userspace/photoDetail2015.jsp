<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="site/css/index.css" rel="stylesheet" type="text/css" />
<link href="site/css/main.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" rel="stylesheet" type="text/css" />
<link href="img/css/blog.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/viewdoc.js"></script>
<script type="text/javascript" src="js/sitejs/userdocdetail.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userlogin.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script src="js/sitejs/galleria-1.4.2.min.js"></script>
<style>
.content {
	color: #777;
	font: 12px/1.4 "helvetica neue", arial, sans-serif;
	width: 510px;
	margin: 0px auto;
}

.clear {
	clear: both
}
</style>
<script language="javascript" type="text/javascript" src="http://js.users.51.la/17667713.js"></script>
<noscript><a href="http://www.51.la/?17667713" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/17667713.asp" style="border:none" /></a></noscript>
</head>
<body style="background-color: #fff; scroll: none;">
	<div id="viewhead" class="viewhead"
		style="height: 30px; background-color: #dfa64f"></div>
	<div id="condiv">
		<div class="mln"
			style="float: left; width: 5px; height: 100%; background-color: #dfa64f;">
			<!-- 			#f2967b -->
		</div>
			<div class="mln"
			style="float: left; width: 109px; height: 100%; font-size: 19px; background-color: #dfa64f; background: url(site/img/phonehead.jpg) center top no-repeat;">
			<br> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			     <br> <br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			     <br>
		</div>
		<div class="mln"
			style="width: 30px; padding-left: 10px; float: left; background-color: #dfa64f; height: 100%;">
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="tologin.do"> &nbsp;
				&nbsp;</a> <a href="index.do"> &nbsp; &nbsp; </a> <a
				href="gouserindex.do?userid=<c:out value="${user.userid}" />"> 
				  &nbsp; &nbsp;</a> <a href="registe.do"> </a>
			<c:if test="${self==0&&islogin==1}">
				<a href="gouserindex.do?userid=<c:out value="${loginuserid}" />">&nbsp;
					&nbsp;  &nbsp; &nbsp;</a>
				<!-- 				<a></a> -->
			</c:if>
		</div>
		<div class="flt" style="top: 0px; padding: 0px; height: 100%;"
			id="nameCard">
			<div class=" flt nameCard nameCardC"
				style="border: 0px; background: #dfa64f; height: 100%;">
				<div class="flt">
					<div class="avatar">
						<img src="html/userhead/<c:out value="${user.headurl}" />"
							width="334" height="446" />
					</div>
					<div class="m1ln name"
						style="padding-top: 30px; color: #fff; font-size: 23px;">
						<c:out value="${user.artname}" />
					</div>
				</div>
				<div class="cbt"></div>
				<div class=" "
					style="padding-top: 10px; width: 180px; margin: 0 auto; color: #fff;">
					<div class="m1ln">
						 
						<c:choose>
							<c:when test="${user.sex==1}">
							         
							       </c:when>
							<c:when test="${user.sex==0}">
							          
							       </c:when>
							<c:otherwise>
							          
							       </c:otherwise>
						</c:choose>
					</div>
					<div class="m1ln">
						 
						<c:out value="${user.age}" default="  " />
					</div>
					<div class="m1ln">
						  
						<c:out value="${user.nowprovincename}" default="  " />
					</div>
					<div class="m1ln">
						 
						<c:out value="${user.regdatestr}" default="  " />
					</div>
					<div class="m1ln">
						 
						<c:out value="${totalVisitCount}" />
					</div>
					<div class="m1ln">
						 
						<c:out value="${currentDateVisitCount}" />
					</div>
					<div class="m1ln">
						    
						<c:out value="${user.logindatestr}" default="" />
					</div>
				</div>
			</div>
		</div>
		<!--  -->
		<div class="mln" style="float: left; padding-top: 30px; height: 95%;">
			<div id="doctitle" class="msheet titlediv"
				style="line-height: 100%; margin: 20px;">
				<c:out value="${documentValue.doctitle}" />
			</div>
		</div>
		<div class="msheet shareBookmark" style="float: left; height: 300px;margin-top:0px;padding-top:150px;">
			<div style="margin: 0px 20px 20px 20px;">
				   <br> <a href="javascript:sharedocument()">
					</a> <span id="sharecount"><c:out
						value="${documentValue.sharecount}" default="0" /></span>   <a
					href="javascript:markdocument()">  </a> <span
					id="markcount"><c:out value="${documentValue.markcount}"
						default="0" /></span>  <br />   <span id="readcount"><c:out
						value="${documentValue.readcount}" default="0" /></span> <br />
				  
				<c:out value="${documentValue.docRelTimeStr}" />
				<br /> <a href="#comment"> </a><span id="commentCount">
					<c:out value="${documentValue.commentCount}" default="0" />
				</span> 
			</div>
		</div>
		<div class="msheet" style="padding: 10px; float: left; height: 95%;">
			<div class="content"
				style="-webkit-writing-mode: horizontal-tb; writing-mode: lr-tb;">
				<div id="galleria" style="width: 500px; height: 410px;">

					<c:forEach items="${imgs}" var="imgValue" varStatus="status">
						<a href="html/img/<c:out value="${imgValue.imgid}"/>.jpg"> <img
							src="html/img/<c:out value="${imgValue.imgid}"/>.jpg" />
						</a>
					</c:forEach>
					<input type="hidden" name="imgsharecount" id="imgsharecount"
						value="11" />
				</div>
			</div>
			<c:forEach items="${imgs}" var="imgValue" varStatus="status">

				<input type="hidden" name="imgtitle" id="imgtitle"
					value="<c:out value="${imgValue.imgdesc}"/>">
				<input type="hidden" name="imgsharecount"
					id="imgsharecount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.sharecount}"/>">
				<input type="hidden" name="imgmarkcount"
					id="imgmarkcount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.markcount}"/>">
				<input type="hidden" name="imgreadcount"
					id="imgreadcount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.readcount}"/>">
				<input type="hidden" name="imgcommentCount"
					id="imgcommentCount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.commcount}"/>">
				<input type="hidden" name="cimgid"
					id="cimgid<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.imgid}"/>">
				<input type="hidden" name="crtime"
					id="crtime<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.crtimestr}"/>">
			</c:forEach>
		</div>
		<div class="msheet shareBookmark" style="float: left; height: 390px;margin-top:0px;padding-top:150px;">
			<div style="margin: 0px 20px 20px 20px;">
				<a href="javascript:sharedocument()"> </a> <span
					id="sharecount"><c:out value="${documentValue.sharecount}"
						default="0" /></span>   <a href="javascript:markdocument()">
					 </a> <span id="markcount"><c:out
						value="${documentValue.markcount}" default="0" /></span>  <br />
				  <span id="readcount"><c:out
						value="${documentValue.readcount}" default="0" /></span> <br />
				  
				<c:out value="${documentValue.docRelTimeStr}" />
				<br /> <a href="#comment"> </a><span id="commentCount">
					<c:out value="${documentValue.commentCount}" default="0" />
				</span> 
			</div>
		</div>
		<div class="mnlist"
			style="background-color: #dfa64f;; width: 30px; color: #fff; padding-left: 10px; height: 100%;"></div>

		<div class=" flt"
			style="float: left; width: 270px; height: 98%; margin: 0px 8px 8px 8px; padding: 5px; background: white; border: solid 1px #dfa64f;">

			<div class="readerl">
				<c:forEach items="${visitors}" var="visitorValue" varStatus="status">
								<div class="i" style="float: left;">
<!-- 					class="frt" -->
						<div  style="width: 90px;height:85">
							<div style="width:60px;height:85px;float:left;">
							 <img src="html/userhead/<c:out value="${visitorValue.headurl}" />"
							width="60" height="85" />
							</div>
							<div class="m1ln"
								style="padding-top: 8px; overflow: hidden; height: 85px;width:22px;float:left;">
								<a
									href="gouserindex.do?userid=<c:out value="${visitorValue.visitorid}" />">
									<c:out value="${visitorValue.visitorname}" />
								</a>
							</div>
						</div>
						<div class="time" style="width:70px;">
							<font size="1px;"><c:out
									value="${visitorValue.visitdatestr}" /></font>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>

		<div class="mnlist"
			style="background-color: #dfa64f;; width: 30px; color: #fff; padding-left: 10px; height: 100%;">
			 </div>
		<div class="msheet" id="commentlist"
			style="float: left; overflow: auto;; padding-top: 10px; height: 100%;">
			<c:forEach items="${comments}" var="messageValue" varStatus="status">
				<div class="postSheet" style="float: left; height: 540px;">
					<div class="posterInf" style="float: left; height: 540px;">
						<div class="avtThumb flt">
							<img
								src="html/userhead/<c:out value="${messageValue.messagesenderurl}" />"
								width="30" height="40" />
						</div>
						<div class="inf flt">
							<div class="row">
								<a
									href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"><c:out
										value="${messageValue.messagesendername}" /> </a><br /> <br />
								<c:out value="${messageValue.sendtimestr}" />
							</div>
							<div class="row"></div>
						</div>
					</div>
					<p>
						<c:if test="${messageValue.hidden==0}">
							<c:out value="${messageValue.contenthtml}" escapeXml="false" />
						</c:if>
						<c:if test="${messageValue.hidden==1}">
												        
												</c:if>

					</p>
					<c:if test="${messageValue.showdel==1}">
						<p>
							<a
								href="javascript:delcomment('<c:out value="${messageValue.messageid}" />');"></a><a
								href="javascript:writemess('<c:out value="${messageValue.messagesenderid}" />','
									<c:out value="${messageValue.messagesendername}" />');">&nbsp;&nbsp;&nbsp;&nbsp;
								</a>
						</p>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<nobr>
			<form action="addCommentOnResource.do" name="commentform"
				id="commentform">
				<div class="comment"
					style="display: inline; width: 260px; float: left; padding: 20px;"
					id="commentcontainer">
					<a name="comment"></a>


					<textarea id="editor1" name="commentdiv" class="ckeditor"
						style="width: 80px; height: 100px;"></textarea>
					<input type="hidden" name="agentkind" id="agentkind"
						value="<c:out value="${agentkind}" />"> <input
						type="hidden" name="userid" id="userid"> <input
						type="hidden" name="doctype" id="doctype" value="1"> <input
						type="hidden" name="docid" id="docid"
						value="<c:out value="${documentValue.docid}" />">

				</div>
				<div style="display: block; float: left; padding-top: 20px;width: 50px;">
					<div class="commoper">
						<div class="mnlist"
							style="width: 50px; height: 210px; padding-left: 10px;">
							<a href="javascript:addcomment(1,0);" style="height: 80px;">
								&nbsp;  </a>&nbsp; <a href="javascript:addcomment(1,1);">
							</a>
						</div>
						<a href="JavaScript:void(0)" id="message_face"><img
							src="img/pl_bq.png" /></a> <a href="javascript:replaceverifycode();"><img
							src="verifyCodeServlet" id="varifyimg" width="18" height="90" /></a>&nbsp;&nbsp;

						<c:if test="${agentkind==1}">
							<!-- 谷歌浏览器 -->
							<input type="text" name="validcode" id="validcode"
								style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; height: 15px; width: 60px; -webkit-transform: rotate(90deg); -webkit-transform-origin: 10px 10px;" />

						</c:if>
						<c:if test="${agentkind==0}">
							<!-- 谷歌浏览器 -->
							<input type="text" name="validcode" id="validcode"
								style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; height: 60px; width: 18px;" />
						</c:if>
						&nbsp;&nbsp;

					</div>
				</div>
			</form>
		</nobr>
		<div class="mln"
			style="float: right; width: 30px; height: 100%; background-color: #dfa64f;color:#fff;">
		</div>
	</div>
	<div id="viewhead" class="viewhead"
		style="height: 30px; background-color: #dfa64f"></div>
	<%@ include file="bloghiddendiv.jsp"%>
	<%@ include file="logindiv.jsp"%>
</body>
<script>
	// Load the classic theme
	Galleria.loadTheme('js/sitejs/galleria.classic.js');
	// Initialize Galleria
	Galleria.run('#galleria');
</script>
</html>