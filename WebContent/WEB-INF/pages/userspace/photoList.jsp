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
<link href="js/messagebox/dialog.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/sitejs/photolist.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
</head>
<body>
	<form id="openphotoform" action="getimginfo.do" method="post">
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
								<c:if test="${self==1}">
									<div class="m0a"
										style="width: 650px; height: 945px; margin: 0px;">
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

									</div>
								</c:if>
								<c:forEach items="${imgList}" var="imgValue" varStatus="status">
									<c:if test="${self==1}">
										<div class="displayfolder"
											style="width: 150px; margin: 2px; 2 px; 2 px; 2 px; overflow: visible; padding-bottom: 2px;">
											<a
												href="javaScript:photoDetail('<c:out value="${imgValue.docid}" />')"><img
												class="displayimg" style="width: 150px;"
												src="getimg.do?imgid=<c:out value="${imgValue.docid}" />"></a>
										</div>
									</c:if>
									<c:if test="${self==0}">
										<div class="displayfolder"
											style="width: 163px; margin: 2px; 2 px; 2 px; 2 px; overflow: visible; padding-bottom: 2px;">
											<a
												href="javaScript:photoDetail('<c:out value="${imgValue.docid}" />')"><img
												class="displayimg" style="width: 163px;"
												src="getimg.do?imgid=<c:out value="${imgValue.docid}" />"></a>
										</div>
									</c:if>
								</c:forEach>
							</div>
							<div class=" pagenav">

								<c:out value="${pagingimgStr}" escapeXml="false" />
								<!--  
								<c:forEach items="${pagingindexs}" var="pagingindex"
									varStatus="status">
									<a
										href="getimglist.do?userid=<c:out value="${user.userid}" />&imggroupid=<c:out value="${imggroupid}" />&pageindex=<c:out value="${pagingindex.pageindex}" />">
										<c:if test="${pagingindex.doc==1}">
											<c:if test="${pagingindex.front==1}">									 
									              ..									 
									            </c:if>
										</c:if> <c:if test="${pagingindex.current==1}">
											<span id="picbtn1" class="curspanstyle"> &nbsp;<c:out
													value="${pagingindex.pageindex}" default="" />&nbsp;
											</span>
										</c:if> <c:if test="${pagingindex.current==0}">
											<span id="picbtn1" class="spanstyle"> &nbsp;<c:out
													value="${pagingindex.pageindex}" default="" />&nbsp;
											</span>
										</c:if> <c:if test="${pagingindex.doc==1}">
											<c:if test="${pagingindex.front==0}">									 
								             	 ..									 
									            </c:if>

										</c:if>
									</a>

								</c:forEach>
								-->
								<%-- 								( <a class="photo-page-nav"><c:out value="${imgcount}" --%>
								<%-- 										default="0" /></a> ) --%>
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
			<div class="tailCard">
				<div class="msheet" style="height: 100px; width: 800px;"><%@ include
						file="../website/tail.jsp"%></div>
			</div>
			<div class="cbt"></div>
		</div>
	</form>
	<div style="display: none">
		<div class="lcell" style="width: 140px; height: 340px;" id="addimg">
			<form action="addimg.do" id="addimgform" method="post"
				enctype="multipart/form-data" />
			<table border="0" style="margin: 1em auto;">
				<tr>
					<td height="100">
						<div class="m1ln h100">  :</div>
					</td>
					<td>
						<div class="m1ln h100">:</div>
					</td>
					<td>
						<div class="m1ln h100">  :</div>
					</td>
					<td>
						<div class="m1ln h100">:</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="mfl">
							<input type="text" name="albumname" id="albumname"
								readonly="true"
								value="<c:out value="${albumValue.imggroupname}" />" /> <input
								type="hidden" name="imggroupid" id="imggroupid"
								value="<c:out value="${albumValue.imggroupid}" />"></input> <input
								type="hidden" name="userid" id="userid"
								value="<c:out value="${user.userid}" />"></input>
						</div>
					</td>
					<td>
						<div class="mfl">
							<input type="text" name="imgcomm" id="imgcomm"></input>
						</div>
					</td>
					<td>
						<div class="mfl">
							<input type="radio" name="cover" id="cover"></input>
						</div>
					</td>
					<td>
						<div class="mfl">
							<input type="file" name="img" id="img" style="height: 210px;"></input>
						</div>
					</td>
					<td>
						<div class="m1ln h100">
							<a href="javascript:uploadimg();"></a>
						</div>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	<!-- 隐藏的div -->
	<%@ include file="bloghiddendiv.jsp"%>
	<!-- 隐藏的div -->
</body>
</html>
