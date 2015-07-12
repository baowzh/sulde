<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="site/css/main.css" rel="stylesheet" type="text/css" />
<link href="site/css/index.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/custom.css" type="text/css" rel="stylesheet" />
<link href="js/messagebox/dialog.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/sitejs/photolist.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
<script language="javascript" type="text/javascript" src="http://js.users.51.la/17667713.js"></script>
<noscript><a href="http://www.51.la/?17667713" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/17667713.asp" style="border:none" /></a></noscript>
</head>
<body>
	<form id="openphotoform" action="getimginfo.do" method="post">
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
								<c:if test="${self==1}">
									<div class="m0a"
										style="width: 690px; height: 945px; margin: 0px;">
								</c:if>
								<c:if test="${self==0}">
									<div class="m0a"
										style="width: 680px; height: 945px; margin: 0px;">
								</c:if>
								<c:if test="${self==1}">
									<div class="addNewAlbum">
										<div class="m1ln borderOuter" style="height: 110px;">
											<a href="javascript:addnewimg();"> </a>
										</div>
										<div class="m1ln borderOuter"
											style="height: 80px; margin-top: 5px; background: #eee url(img/delete.png) center top no-repeat;">
											<a href="javascript:deleteimg();"> </a>
										</div>

									</div>
								</c:if>
								<c:forEach items="${imgList}" var="imgValue" varStatus="status">
									<c:if test="${self==1}">
										<div class="displayfolder"
											style="width: 164px; height: 190px; margin: 2px; 2 px; 2 px; 2 px; overflow: visible; padding-bottom: 2px;">
											<a
												href="javaScript:photoDetail('<c:out value="${imgValue.docid}" />')"><img
												class="displayimg" style="width: 160px; height: 170px;"
												src="html/img/<c:out value="${imgValue.docid}" />.jpg"></a>
											<input type="checkbox" name="selectedimg"
												id="<c:out value="${imgValue.docid}"/>">
										</div>
									</c:if>
									<c:if test="${self==0}">
										<div class="displayfolder"
											style="width: 163px; height: 190px; margin: 2px; 2 px; 2 px; 2 px; overflow: visible; padding-bottom: 2px;">
											<a
												href="javaScript:photoDetail('<c:out value="${imgValue.docid}" />')"><img
												class="displayimg" style="width: 163px; height: 100%;"
												src="html/img/<c:out value="${imgValue.docid}" />.jpg"></a>
										</div>
									</c:if>
								</c:forEach>
							</div>
							<div class=" pagenav">
								<c:out value="${pagingimgStr}" escapeXml="false" />
								<input type="hidden" name="userid" id="userid"
									value="<c:out value="${user.userid}" />" /> <input
									type="hidden" name="imggroupid" id="imggroupid"
									value="<c:out value="${imggroupid}" />" /> <input
									type="hidden" name="idAndIndexrel" id="idAndIndexrel"
									value="<c:out value="${idAndIndexrel}" />" /> <input
									type="hidden" name="imgid" id="imgid" value="" />

							</div>
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
			<%@ include file="../website/tail.jsp"%>
		</div>
	</form>
	<div style="display: none" id="addimg"
		style="width:270px;height:300px;">
		<div class="content"
			style="width: 280px; height: 310px; background: white; padding: 5px; border-radius: 5px;">
			<form action="addimg.do" id="addimgform" method="post"
				class="mglForm" enctype="multipart/form-data" />
			<div class="label" style="text-align: center;">  :</div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="albumname" id="albumname" readonly="true"
					value="<c:out value="${albumValue.imggroupname}" />" />
			</div>

			<div class="label" style="text-align: center;">:</div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="imgcomm" id="imgcomm"></input>
			</div>

			<div class="label" style="text-align: center; height: 210px;">
				   :<input type="checkbox" name="cover"
					id="cover"></input>
			</div>
			<c:if test="${racemodel!=null&&sessionuser.managerflag==1}">
				<div class="label" style="text-align: center; height: 210px;">
					   :<input type="checkbox" name="forrace"
						id="forrace"></input>
				</div>
			</c:if>
			<div class="label" style="text-align: center;">:</div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="file" name="img" id="img" style="height: 210px;"></input>
			</div>
			<input type="hidden" name="imggroupid" id="imggroupid"
				value="<c:out value="${albumValue.imggroupid}" />"></input> <input
				type="hidden" name="userid" id="userid"
				value="<c:out value="${user.userid}" />"></input>
			<div class="label" style="text-align: center;">
				<a href="javascript:uploadimg();"></a>
			</div>
			</form>
		</div>
	</div>
	<!-- 隐藏的div -->
	<%@ include file="bloghiddendiv.jsp"%>
	<!-- 隐藏的div -->
	<!-- 	<div class="wrp m0a ribbon"></div> -->
</body>
</html>
