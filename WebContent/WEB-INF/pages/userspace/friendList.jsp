<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博主的主页</title>
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
						<div class="flt glryBox">
							<div class="m0a"
								style="width: 660px; height: 900px; margin: 0px;">
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
												src="getsmheadimge.do?userid=<c:out value="${friendValue.friendid}" />" /></a>

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
								<!--  
								<c:forEach items="${pageindexs}" var="index" varStatus="status">
									<a
										href="getimglist.do?userid=<c:out value="${user.userid}" />&imggroupid=<c:out value="${imggroupid}" />&pageindex=<c:out value="${index}" />">

										<c:if test="${status.index==0}">
											<span id="page<c:out value="${pagingIndex.pageindex}" />"
												class="curspanstyle">&nbsp;<c:out
													value="${index}" />&nbsp;
											</span>
										</c:if> <c:if test="${status.index!=0}">
											<span id="page<c:out value="${index}" />"
												class="spanstyle">&nbsp;<c:out
													value="${pagingIndex.pageindex}" />&nbsp;
											</span>
										</c:if>
									</a>
								</c:forEach>
								-->
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
		<div class="lmainR ofh" style="text-align: center;">
			<div class="tailCard">
				<div class="msheet" style="height: 100px; width: 800px;"><%@ include
						file="../website/tail.jsp"%></div>
			</div>
			<div class="cbt"></div>
		</div>
		<%@ include file="bloghiddendiv.jsp"%>
</body>
</html>
