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
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/sitejs/photoalbumlist.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<script language="javascript" type="text/javascript" src="http://js.users.51.la/17667713.js"></script>
<noscript><a href="http://www.51.la/?17667713" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/17667713.asp" style="border:none" /></a></noscript>
</head>
<body>
	<form id="openalbumform" action="getimglist.do" method="post">
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
						<!-- 通过引用页面代替 -->
						<%@ include file="blogpage.jsp"%>
						<!--  -->
					</div>
					<div class="blgmain bglMainC" id="blgMain">
						<div style="width: 692px; margin: 0px 10px 10px 10px;">
							<div class="flt glryBox">
								<div class="m0a"
									style="width: 692px; height: 900px; margin: 0px;">
									<c:if test="${self==1}">
										<div class="addNewAlbum">
											<div class="m1ln borderOuter" style="height: 110px;">
												<a href="javascript:openaddphotoalbumdialog();">
													   </a>
											</div>
											<div class="m1ln borderOuter"
												style="height: 80px; margin-top: 5px; background: #eee url(img/delete.png) center top no-repeat;">
												<a href="javascript:deletephotoalbum();"> </a>
											</div>
										</div>
									</c:if>
									<div class="folder photoAlbumC">
										<div style="width: 80px; margin: 4em 2em;">
											<div class=" msheet">
												<a href="#">
												 <br /><br /> <br />  <br>
												</a>
											</div>
										</div>
									</div>

									<c:forEach items="${photoAlbumList}" var="imgGrpupValue"
										varStatus="status">
										<div class="folder" style="width:162px;height:180px;">
											<a
												href="javascript:openPhotoAlbum('<c:out value="${imgGrpupValue.imggroupid}"/>')">
												<c:if test="${imgGrpupValue.faceurl!=null}">
												<img
												style="width:132px;height:150px;"
												src="html/photoalbum/<c:out value="${imgGrpupValue.faceurl}"/>">
												</c:if>
												<c:if test="${imgGrpupValue.faceurl==null}">
												<img
												style="width:132px;height:150px;"
												src="html/photoalbum/imgface.jpg">
												</c:if>
												</a>
											<div class="m1ln" style="text-align: center;height:145px;">
												<a title="<c:out value="${imgGrpupValue.imggroupname}"/>"
													href="javascript:openPhotoAlbum('<c:out value="${imgGrpupValue.imggroupid}"/>')"><c:out
														value="${imgGrpupValue.imggroupname}" /></a>
											</div>

											<div class="time">
												<c:if test="${self==1}">
													<input type="radio" name="selectedradio"
														id="<c:out value="${imgGrpupValue.imggroupid}"/>">
												</c:if>
												<c:out value="${imgGrpupValue.createdtimestr}" />
											</div>
										</div>
									</c:forEach>

<!-- 									<div class="folder photoAlbumC"> -->
<!-- 										<div style="width: 80px; margin: 4em 2em;"> -->
<!-- 											<div class=" msheet" style="font-size: 14px;"> -->
<!-- 												<a href="#"> <br> <br> <br /> <br /> -->
<!-- 												</a> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->

								</div>

								<input type="hidden" name="userid" id="userid"
									value="<c:out value="${user.userid}" />" /> <input
									type="hidden" name="imggroupid" id="imggroupid" value="" />
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
	<div id="addphotoalbum"
		style="width: 270px; height: 320px; display: none;">
		<div class="content" style="width: 270px; height: 300px;background: white;padding: 5px;border-radius: 5px;">
			<form action="addimggroup.do" 
				id="addphotoalbumform" class="mglForm" method="post"
				enctype="multipart/form-data" >
				<div class="label" style="text-align: center;">  :</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="text" name="imggroupname" id="imggroupname"
						style="-webkit-transform-origin: 10px 20px;" /> <input
						type="hidden" name="userid" id="userid"
						value="<c:out value="${user.userid}" />"></input>
				</div>

				<div class="label" style="text-align: center;">:</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="text" name="comm" id="comm"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="label" style="text-align: center;"> :</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="file" name="imgurl" id="imgurl"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="mnlist" style="height: 270px;"></div>
				<div class="mnlist"
					style="width: 24px; height: 200px; text-align: center;">
					<a href="javascript:addphotoalbum();"></a>

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