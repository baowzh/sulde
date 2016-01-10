<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,,height=device-height,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<title>tegri</title>
<link href="site/css/userphoneindex.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/userphoneindex.js"></script>
</head>
<body onload="q();">
	<div class="wrap header">
		<div style="float: left; width: 240px;">
<!-- 			<img src="site/img/phonehead.jpg" style="height: 90px; width: 104px;"> -->
		</div>
		<div class="mln"
			style="height: 90px; width: 30px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phonebloglist.do"></a>
		</div>
		<div class="emptyItem" style="height: 70px; float: right;"></div>
		<div class="mln"
			style="height: 90px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phonechannel.do"></a>
		</div>
	</div>
	<div class="wrap content" style="height: 180px;">
		<div class="flt" style="padding-left: 10px;">
			<div class="avatar">
				<img src="html/userhead/<c:out value="${user.headurl}" />" />
			</div>
			<div class="m1ln name" style="height: 180px; overflow: hidden;">
				<c:out value="${user.artname}" />
			</div>
		</div>
		<div class="flt"
			style="padding-top: 10px; padding-left: 20px; width: 260px; margin: 0 auto">
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
		</div>

	</div>
	<div class="wrap content" style="padding-top: 2px; height: 390px;">
		<div class="listCardTtl ttlStl1" style="height: 390px;">
			<div class="mln" style="height: 146px; font-size: 21px;">
				</div>
		</div>
		<div class="listCardBody" style="height: 390px;">
			<div class="listBody" id="doclistdiv">
				<c:forEach items="${docList}" var="documentValue" varStatus="status">
					<div class="nwsl1">
						<div class="title" style="height: 100%;">
							<a
								href="phonedetail.do?docid=<c:out value="${documentValue.docid}"/>"
								target="_blank" class="tit_text_overflow"><c:out
									value="${documentValue.doctitle}" /></a>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="paginationArea">
				<div class="pagination" id="docpagelist">
					<a class="first disabled" id="firsta"
						href="javascript:openpage('1','<c:out value="${user.userid}" />',1,1);switchclass('first');">
						<span id="pagefirst" class="spanstyle">&lt;&lt; </span>
					</a> <a class="first disabled" id="previousa"
						href="javascript:openpage('<c:out value="${previousindex}" />','<c:out value="${user.userid}" />',1,1);switchclass('pageprevious');">
						<span id="pageprevious" class="spanstyle">&nbsp;&lt;&nbsp;
					</span>
					</a>
					<c:forEach items="${docpageIndexs}" var="pagingIndex"
						varStatus="status">
						<a class="first disabled" id="doclista"
							href="javascript:openpage('<c:out value="${pagingIndex.pageindex}" />','<c:out value="${user.userid}" />',1,1);switchclass('page<c:out value="${pagingIndex.pageindex}" />');">
							<c:if test="${status.index==0}">
								<span id="page<c:out value="${pagingIndex.pageindex}" />"
									class="curspanstyle">&nbsp;<c:out
										value="${pagingIndex.pageindex}" />&nbsp;
								</span>
							</c:if> <c:if test="${status.index!=0}">
								<span id="page<c:out value="${pagingIndex.pageindex}" />"
									class="spanstyle">&nbsp;<c:out
										value="${pagingIndex.pageindex}" />&nbsp;
								</span>
							</c:if>

						</a>
					</c:forEach>
					<a class="first disabled" id="nexta"
						href="javascript:openpage('2','<c:out value="${user.userid}" />',1,1);switchclass('pagenext');">
						<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp; </span>
					</a> <a class="first disabled" id="lasta"
						href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',1,1);switchclass('pagelast');">
						<span id="pagelast" class="spanstyle">&gt;&gt; </span>
					</a> <input type="hidden" name="pagecount" id="pagecount"
						value="<c:out value="${pageCount}" />" />
				</div>
			</div>
		</div>
	</div>
	<c:if test="${fn:length(sharePaingModel.modelList)!=0}">
		<div class="wrap content" style="padding-top: 2px; height: 390px;">
			<div class="listCardTtl ttlStl1" style="height: 350px;">
				<div class="mln" style="height: 146px; font-size: 21px;">
					</div>
			</div>
			<div class="listCardBody" style="height: 390px;">
				<div class="listBody" id="sharedoclistbody">
					<c:forEach items="${sharePaingModel.modelList}" var="documentValue"
						varStatus="status">
						<div class="nwsl1">
							<div class="title">
								<a
									href="phonedetail.do?docid=<c:out value="${documentValue.docid}"/>"
									target="_blank" class="tit_text_overflow"><c:out
										value="${documentValue.doctitle}" /></a>
							</div>
							<div class="author">
								<a
									href="phonedetail.do?docid=<c:out value="${documentValue.userid}"/>"
									target="_blank" class="tit_text_overflow"><c:out
										value="${documentValue.docauthor}" /></a>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="paginationArea">
					<div class="pagination" id="sharedoclist">
						<a class="first disabled" id="firsta"
							href="javascript:openpage('1','<c:out value="${user.userid}" />',2);switchclass('first');">
							<span id="pagefirst" class="spanstyle">&lt;&lt; </span>
						</a> <a class="first disabled" id="previousa"
							href="javascript:openpage('<c:out value="${previousindex}" />','<c:out value="${user.userid}" />',2);switchclass('pageprevious');">
							<span id="pageprevious" class="spanstyle">&nbsp;&lt;&nbsp;
						</span>
						</a>
						<c:forEach items="${sharepageIndexs}" var="pagingIndex"
							varStatus="status">
							<a class="first disabled"
								href="javascript:openpage('<c:out value="${pagingIndex.pageindex}" />','<c:out value="${user.userid}" />',2);switchclass('page<c:out value="${pagingIndex.pageindex}" />');">
								<c:if test="${status.index==0}">
									<span id="page<c:out value="${pagingIndex.pageindex}" />"
										class="curspanstyle">&nbsp;<c:out
											value="${pagingIndex.pageindex}" />&nbsp;
									</span>
								</c:if> <c:if test="${status.index!=0}">
									<span id="page<c:out value="${pagingIndex.pageindex}" />"
										class="sharespanstyle">&nbsp;<c:out
											value="${pagingIndex.pageindex}" />&nbsp;
									</span>
								</c:if>
							</a>
						</c:forEach>
						<a class="first disabled" id="nexta"
							href="javascript:openpage('2','<c:out value="${user.userid}" />',2);switchclass('pagenext');">
							<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp; </span>
						</a> <a class="first disabled" id="lasta"
							href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',2);switchclass('pagelast');">
							<span id="pagelast" class="spanstyle">&gt;&gt; </span>
						</a>

					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="wrap content"
		style="height: 3px; text-align: center; background: #fff; color: #fff;"></div>
	<div class="wrap content"
		style="height: 30px; text-align: center; background: #42A7D7; color: #fff; padding-top: 3px; font-size: 13px; positon: fixed:bottom:0px;">
		版权所有 © 2014-2015</div>
</body>
</html>
