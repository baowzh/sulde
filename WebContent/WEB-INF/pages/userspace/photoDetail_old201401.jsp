<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/custom.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userdocdetail.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<%@ include file="bloghead.jsp"%>
	</div>
	<div class="lmainR">
		<div class="  lcell"
			style="width: 990px; margin-top: 200px; overflow: visible;">
			<div class=" blogbody blogbodyC">
				<div class="blgL blgLC" id="blgL">
					<%@ include file="blogpage.jsp"%>
				</div>
				<div class="blgmain bglMainC" id="blgMain">
					<div style="width: 690px; margin: 0px 10px 10px 10px;">

						<div class="recentAcvtyBox contentArea">
							<div class="mVsheet " id="vs">
								<div class="ScrollToView">
									<div></div>
								</div>
								<h1 >
									<div id="imgtitle"><c:out value="${documentValue.doctitle}" /></div>

								</h1>
								<input type="hidden" id="hiddensharecount"
									value="<c:out value="${documentValue.sharecount}" />">
								<input type="hidden" id="imggroupid" name="imggroupid"
									value="<c:out value="${imggroupid}" />">

								<div class="shareBookmark" style="margin: 200px 10px 10px 10px;">
									<a href="javascript:sharedocument()"> </a> <span
										id="sharecount"><c:out
											value="${documentValue.sharecount}" default="0" /></span>   <a
										href="javascript:markdocument()"> </a> <span
										id="markcount"><c:out
											value="${documentValue.markcount}" default="0" /></span>  <br /> 
									 <span id="readcount"><c:out
											value="${documentValue.readcount}"  default="0"/></span> <br /> 
									 
									<c:out value="${documentValue.docRelTimeStr}" />
									<br /> <a href="#comment"> </a><span id="commentCount">
										<c:out value="${documentValue.commentCount}" default="0" />
									</span> 
								</div>

								<div class="folder" style="width: 492px; height: 400px;">
									<img id="disimg"
										src="getimg.do?imgid=<c:out value="${imgValue.imgid}" />"
										style="width: 485px; height: 400px;" />
								</div>
								<div class="mnlist" style="text-align: center;">
									<a href="javascript:previousdoc();"> &nbsp; &nbsp; </a>
									<a href="javascript:nextdoc();"> &nbsp; &nbsp;</a>
									<c:if test="${self==1}">
										<a href="javascript:deldoc(2);"> &nbsp;&nbsp;</a>
									</c:if>
								</div>
								<div></div>
								<div class="comment" id="commentcontainer">
									<a name="comment"></a>
									<form action="addCommentOnResource.do" name="commentform"
										id="commentform">
										<textarea name="comment" id="comment" style="display: none">								    
								</textarea>
										<div class="flt mVsheet commentEdit" contentEditable="true"
											id="commentdiv"></div>
										<input type="hidden" name="userid" id="userid"> <input
											type="hidden" name="doctype" id="doctype" value="2">
										<input type="hidden" name="docid" id="docid"
											value="<c:out value="${imgValue.imgid}" />"> <input
											type="hidden" name="idAndIndexrel" id="idAndIndexrel"
											value="<c:out value="${idAndIndexrel}" />">

										<!-- 										<div class="m1ln" style="width:100px; height: 400px"> -->
										<%-- 											<%@ include file="emface.jsp"%> --%>
										<!-- 										</div> -->
										<div class="mnlist"
											style="height: 300px; padding-top: 10px; text-align: center; width: 40px;">
											<a href="javascript:addcomment(2,1);" style="height: 50px;">
												 &nbsp;&nbsp; </a>&nbsp;&nbsp;<a
												href="javascript:addcomment(2,0);"> </a> <a
												href="JavaScript:void(0)" id="message_face"><img
												src="img/pl_bq.png" /></a>
												
										</div>
									</form>
								</div>
								<br>
								<!-- 加载留言区域 -->
								<div id="commentlist">
									<c:forEach items="${comments}" var="messageValue"
										varStatus="status">
										<div class="postSheet">
											<div class="posterInf">
												<div class="avtThumb flt">
													<img
														src="html/userhead/<c:out value="${messageValue.headurl}" />"
														width="300" height="400" />
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
												<c:out value="${messageValue.contenthtml}" escapeXml="false" />
											</p>
											<c:if test="${self==1}">
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
								<!-- 加载留言区域 -->


							</div>
							<div style="height: 10px; width: 100%; float: left"></div>
							<div class="cbt"></div>
						</div>
					</div>
					<div class="cbt"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<div class="tailCard">
			<div class="msheet" style="height: 100px; width: 800px;"><%@ include
					file="../website/tail.jsp"%></div>
		</div>
		<div class="cbt"></div>
	</div>
	<div class="lcell cardlogin" style="display: none">
		<form id="addimgform" method="post">
			<table border="0" style="margin: 1em auto;">
				<tr>
					<td height="100"><div class="m1ln h100">  
							ᠲ</div></td>
					<td><div class="m1ln h100"></div></td>
				</tr>
				<tr>
					<td><div class="mfl">
							<input type="text" name="username" />
						</div></td>
					<td><div class="mfl">
							<input type="text" name="text" />
						</div></td>
					<td><div class="mfl">
							<input type="file" name="imgfile" />
						</div></td>
					<td><div class="mfl">
							<input type="button" value=" "
								onclick="javascript:upload();" />
						</div></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 隐藏的div -->
	<%@ include file="bloghiddendiv.jsp"%>
	<!-- 隐藏的div -->
</body>
</html>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>