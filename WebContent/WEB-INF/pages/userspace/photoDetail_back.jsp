<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<link href="site/css/main.css" rel="stylesheet" type="text/css" />
<link href="site/css/index.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/custom.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userdocdetail.js"></script>
<script type="text/javascript" src="js/sitejs/userlogin.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
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
<script src="js/sitejs/galleria-1.4.2.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
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
</head>
<body>
	<div class="wrp m0a logo">
		<div class="naveFrame">
			<%@ include file="bloghead.jsp"%>
		</div>
		<div class="cbt"></div>
	</div>
	<div class="wrp m0a ribbon"></div>
	<div class="lmainR">
		<div class="  lcell"
			style="width: 990px; margin-top: 196px; overflow: visible;">
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
								<h1>
									<div id="imgtitle">
										<c:out value="${documentValue.doctitle}" />
									</div>

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
											value="${documentValue.markcount}" default="0" /></span>  <br />
									  <span id="readcount"><c:out
											value="${documentValue.readcount}" default="0" /></span> <br />
									   <span id="doctime"><c:out
											value="${documentValue.docRelTimeStr}" /> </span> <br /> <a
										href="#comment"> </a> <span id="commentCount">
										<c:out value="${documentValue.commentCount}" default="0" />
									</span> 
								</div>

								<div class="content"
									style="-webkit-writing-mode: horizontal-tb; writing-mode: lr-tb;">
									<div id="galleria" style="width: 500px; height: 410px;">

										<c:forEach items="${imgs}" var="imgValue" varStatus="status">
											<a href="html/img/<c:out value="${imgValue.imgid}"/>.jpg">
												<img
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
								<div class="clear"></div>
								<nobr>
									<div class="comment"
										style="display: inline; width: 360px; height: 400px;"
										id="commentcontainer">
										<a name="comment"></a>
										<form action="addCommentOnResource.do" name="commentform"
											id="commentform">
											<!-- 
											<c:if test="${agentkind==0}">
												<div style="display: block; z-index: 10">
													<textarea name="commentdiv" id="commentdiv"
														style="width: 210px; height: 400px; writing-mode: tb-lr;">								    
								                </textarea>
												</div>
											</c:if>
                                            -->
											<textarea name="comment" id="comment" style="display: none">								    
							          	</textarea>

											<%-- 											<c:if test="${agentkind==1}"> --%>

											<textarea id="editor1" name="commentdiv" class="ckeditor"
												style="width: 210px; height: 100px;"></textarea>
											<%-- 											</c:if> --%>

											<input type="hidden" name="agentkind" id="agentkind"
												value="<c:out value="${agentkind}" />"> <input
												type="hidden" name="userid" id="userid"
												value="<c:out value="${imgValue.userid}" />"> <input
												type="hidden" name="doctype" id="doctype" value="2">
											<input type="hidden" name="docid" id="docid"
												value="<c:out value="${imgValue.imgid}" />">
											<div class="commoper" style="display: block;">
												<div class="mnlist"
													style="width: 50px; height: 210px; padding-left: 10px;">
													<a href="javascript:addcomment(2,0);" style="height: 80px;">
														&nbsp;  </a>&nbsp; <a
														href="javascript:addcomment(2,1);"> </a>
												</div>
												<a href="JavaScript:void(0)" id="message_face"><img
													src="img/pl_bq.png" /></a> <a
													href="javascript:replaceverifycode();"><img
													src="verifyCodeServlet" id="varifyimg" width="21"
													height="100" /></a>&nbsp;&nbsp;

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
											</div>

										</form>
									</div>
								</nobr>

								<div class="clear"></div>
								<br>
								<!-- 加载留言区域 -->
								<div id="commentlist">
									<c:forEach items="${comments}" var="messageValue"
										varStatus="status">
										<div class="postSheet">
											<div class="posterInf">
												<div class="avtThumb flt">
													<img
														src="html/userhead/<c:out value="${messageValue.messagesenderurl}" />"
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
		<%@ include file="../website/tail.jsp"%>
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
							<input type="text" name="albumname" id="albumname" />
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
	<div id="logindiv" style="display: none">
		<div class="content1"
			style="padding-left: 30px; width: 230px; height: 270px; background: white; padding: 5px; border-radius: 5px;">
			<form action="addfriend.do" name="addfriendform" id="addfriendform"
				class="mglForm">
				<div class="label" style="text-align: center;"> 
					</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="text" name="username" id="username"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="label" style="text-align: center;">  </div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="password" name="password" id="password1"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="label" style="text-align: center;">
					<a href="javascript:replaceverifycode(1);"><img
						src="verifyCodeServlet" id="varifyimg1" width="18" height="100" />
					</a>
				</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="text" name="varifycode" id="varifycode1"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="mnlist" style="height: 270px;"></div>
				<div class="mnlist" style="height: 270px; text-align: center;">
					<a href="javascript:login();"> </a>
				</div>
			</form>
		</div>
	</div>
	<!-- 隐藏的div -->
	<%@ include file="bloghiddendiv.jsp"%>
	<!-- 隐藏的div -->
</body>
</html>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
<script>
	// Load the classic theme
	Galleria.loadTheme('js/sitejs/galleria.classic.js');
	// Initialize Galleria
	Galleria.run('#galleria');
</script>