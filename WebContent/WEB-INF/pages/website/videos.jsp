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
<script type="text/javascript" src="js/sitejs/doccheck.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
</head>
<body>
	<form id="Form" action="pagingimglist.do" method="post">
		<!-- 		<div class="lmainR ofh" style="text-align: center; height: 64px;"> -->
		<!-- 			<img src="img/logo.png" width="980" /> -->
		<!-- 		</div> -->
		<div class="wrp m0a logo">
			<div class="naveFrame">
				<%@ include file="../website/head.jsp"%>
			</div>
			<div class="cbt"></div>
		</div>
		<div class="wrp m0a ribbon"></div>
		<div class="layer m0a">
			<!-- 		<div class="lmainR ofh"> -->
			<div class="flt glryBox"
				style="width: 1000px; margin-top: 0px; padding: 0px;">
				<c:forEach items="${paingModel.modelList}" var="imgValue"
					varStatus="status">
					<div class="folder" style="width: 240px; height: 250px;border:0px;">
						<c:if test="${imgValue.facepath!=null}">
							<a
								href="getuserdocdetail.do?docid=<c:out value="${imgValue.docid}"/>"><img
								src="<c:out value="${imgValue.facepath}"/>"
								style="width: 201px; height: 250px;background:#fff;" /></a>
						</c:if>
						<c:if test="${imgValue.facepath==null}">
							<a
								href="getuserdocdetail.do?docid=<c:out value="${imgValue.docid}"/>"><img
								src="img/vido.jpg" style="width: 201px; height: 250px;background:#fff;" /></a>
						</c:if>
						<div class="m1ln" style="height: 250px;">
							<c:out value="${imgValue.doctitle}" />
						</div>
					</div>
				</c:forEach>
			</div>
			<div class=" pagenav">
				<c:forEach items="${pagingindexs}" var="pagingindex"
					varStatus="status">
					<a
						href="videos.do?pageindex=<c:out value="${pagingindex.pageindex}" />">
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
			</div>
		</div>
	</form>
<!-- 	<div class="wrp m0a ribbon"></div> -->
	<%@ include file="tail.jsp"%>
</body>
</html>
