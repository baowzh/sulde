<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博主的主页</title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="site/css/main.css" rel="stylesheet" type="text/css" />
<link href="site/css/index.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/custom.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
<script type="text/javascript" src="js/sitejs/photoalbumlist.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
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
						<div class="flt glryBox">
							<div class="m0a"
								style="width: 690px; height: 900px; margin: 0px;">
								<c:if test="${self==1}">
									<div class="addNewAlbum">
										<div class="m1ln borderOuter" style="height: 90px;">
											<a href="javascript:writemessage()">  </a>
										</div>
										<div class="m1ln borderOuter"
											style="height: 80px; background: #eee url(img/delete.png) center top no-repeat;">
											<a href="javascript:delfriend()"> </a>
										</div>
									</div>
								</c:if>

								<c:forEach items="${friendList}" var="friendValue"
									varStatus="status">
									<div class=" friendL">
										<div class="i">
											<a
												href="gouserindex.do?userid=<c:out value="${friendValue.friendid}" />"><img
												src="html/userhead/<c:out value="${friendValue.headurl}" />" /></a>

											<div class="frt" style="width: 20px;">
												<div class="m1ln">
													<a
														href="gouserindex.do?userid=<c:out value="${friendValue.friendid}" />">
														<c:out value="${friendValue.friendname}" />
													</a>
												</div>
											</div>
											<div class="frt" style="width: 20px; height: 10px;">
												<c:if test="${self==1}">
													<div>
														<input type="radio" name="selectedradio"
															id="<c:out value="${friendValue.friendid}" />" />
													</div>
												</c:if>
											</div>

										</div>
									</div>
								</c:forEach>

							</div>
							<div class=" pagenav">
								<c:out value="${pagestr}" escapeXml="false" />
								<input type="hidden" name="userid" id="userid"
									value="<c:out value="${user.userid}" />" />
							</div>
						</div>
						<div style="height: 10px; width: 100%; float: left"></div>
						<div class="cbt"></div>
					</div>
				</div>
				<div class="cbt"></div>
			</div>
		</div>
		</div>
		<div class="lmainR ofh" style="text-align: center;">
			<%@ include file="../website/tail.jsp"%>
		</div>
		<%@ include file="bloghiddendiv.jsp"%>
</body>
</html>
