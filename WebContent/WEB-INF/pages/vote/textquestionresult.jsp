<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> </title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="lmainR"
		style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr; width: 210px; height: 400px;">
		<c:forEach items="${paingModel.results}" var="voteResultValue"
			varStatus="status">
			<div class="mnlist">
			<c:out value="${voteResultValue.artname}" /> :<c:out value="${voteResultValue.questiondesc}" />
			</div>
			<br>
		</c:forEach>
		<div class="vpagenav" style="padding-top:0px;text-align:left">
			<input type="hidden" id="pageindex" name="pageindex" /> <span
				id="picbtn1"
				style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
			</span>
			<c:forEach items="${pagingindexs}" var="pagingindex"
				varStatus="status">
				<a
					href="gettextquestioresult.do?voteid=<c:out value="${voteid}" />&questionid=<c:out value="${questionid}" />&index=<c:out value="${pagingindex.pageindex}" />"><c:if
						test="${pagingindex.doc==1}">
						<c:if test="${pagingindex.front==1}">									 
									 ..									 
									</c:if>
					</c:if> <span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<c:out
							value="${pagingindex.pageindex}" default="" />&nbsp;
				</span> <c:if test="${pagingindex.doc==1}">
						<c:if test="${pagingindex.front==0}">									 
									 ..									 
									</c:if>

					</c:if> </a>
			</c:forEach>

			<span id="picbtn1"
				style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
			</span> (<c:out value="${paingModel.resultcount}" default="0" />)
		</div>
	</div>

</body>