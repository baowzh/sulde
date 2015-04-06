<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片审核</title>
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
		<div style="background: #1b6dd2;">
			<div class="lmainR" style="text-align: center;">
				<img src="img/logo.png" width="990" height="64">
			</div>
		</div>
		<div class="lmainR">
			<div class="flt glryBox" style="width: 986px; margin-top: 0px;">
				<c:if test="${isempty==1}">
					<div class="mnlist">    </div>
				</c:if>
				<c:forEach items="${imgList}" var="imgValue" varStatus="status">
					<div class="displayfolder"
						style="width: 159px; height: 170px; margin: 2px; 2 px; 2 px; 2 px; overflow: visible; padding-bottom: 2px;">
						<a
							href="javaScript:photoDetail('<c:out value="${imgValue.docid}" />')"><img
							class="displayimg" style="width: 152px; height: 148px;"
							src="html/img/<c:out value="${imgValue.imgurl}" />"></a>
						<c:if test="${imgValue.docstatus==2}">
							已审核<input type="checkbox" name="docnamecheckbox"
								id="<c:out value="${imgValue.docid}" />">
						</c:if>
						<c:if test="${imgValue.docstatus==1}">
							未审核<input type="checkbox" name="docnamecheckbox"
								id="<c:out value="${imgValue.docid}" />">
						</c:if>

					</div>

				</c:forEach>
			</div>
			<div class=" pagenav">
				<c:forEach items="${pagingindexs}" var="pagingindex"
					varStatus="status">
					<a
						href="pagingimglist.do?pageindex=<c:out value="${pagingindex.pageindex}" />&imgstatus=<c:out value="${imgstatus}" />">
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
			<div class=" pagenav">
				<c:choose>
					<c:when test="${imgstatus==2}">
						<select name="imgstatus" id="imgstatus">
							<option value="1">未审核</option>
							<option value="2" selected="selected">已审核</option>
						</select>

					</c:when>
					<c:when test="${imgstatus==1}">
						<select name="imgstatus" id="imgstatus">
							<option value="1" selected="selected">未审核</option>
							<option value="2">已审核</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="imgstatus" id="imgstatus">
							<option value="1">未审核</option>
							<option value="2">已审核</option>
						</select>
					</c:otherwise>
				</c:choose>
				<a href="javascript:queryimgs();">查询</a> <input type="checkbox"
					name="checkall" onclick="javascript:selectAllDoc(this)"
					style="width: 20px; height: 20px;" /> </span> <span class="spanstyle"><a
					href="javascript:checkdoc();"><img src="img/auditimg.png" /></a></span> <span
					class="spanstyle"><a href="javascript:deletedoc();"><img
						src="img/deleteimg.png" /></a></span> <span class="spanstyle"><a
					href="javascript:addindexdoc();"><img src="img/seltoindex.png" /></a></span>
				<span class="spanstyle"><a href="sitemanagerindex.do"><img
						src="img/goindex.png" /></a></span>

			</div>
		</div>
	</form>
</body>
<script>
 var queryimgs=function(){
	window.location.href='pagingimglist.do?pageindex=1&imgstatus='+$('#imgstatus').val();
 };
</script>
</html>
