<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/huh.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
</head>
<body>
	<form action="pagingquery.do" id="queryForm" method="post">
		<%@ include file="head.jsp"%>
		<form action="pagingquery.do" method="post">
			<div class=" listSheet">
				<div class="ttl1 m1ln" style="height: 480px;">
					<a href="#"><c:out value="${channel.chnlname}" /></a>
				</div>
				<div style="width: 958px; margin: 0px;" class="ofh">

					<div class="flt" style="width: 970px">
						<div style="height: 410px; margin: 10px;">
							<c:forEach items="${paingModel.modelList}" var="documentValue"
								varStatus="status">
								<div class="nwsl1" style="height: 410px;">
									<div class="title" style="height: 320px;">
										<a
											href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}"/>"
											style="color: #575a5b"><c:out
												value="${documentValue.doctitle}" /> </a>
									</div>
									<div class="author">
										<a
											href="gouserindex.do?userid=<c:out value="${documentValue.userid}" /> "><c:out
												value="${documentValue.docauthor}" /></a>
									</div>
								</div>

							</c:forEach>

						</div>
						<div class="cbt"></div>
						<div class=" pagenav">

							<span id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 20px; width: 20px;">&nbsp;<a>&lt;</a>&nbsp;
							</span>
							<c:forEach items="${pagingindexs}" var="pagingindex"
								varStatus="status">
								<a
									href="javascript:gotoPage(<c:out value="${pagingindex.pageindex}"
										default="" />)"><c:if
										test="${pagingindex.doc==1}">
										<c:if test="${pagingindex.front==1}">									 
									 ..									 
									</c:if>
									</c:if> <c:if test="${pagingindex.current==1}">
										<span id="picbtn1"
											style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(f, f, f); line-height: 20px; width: 20px;">&nbsp;<c:out
												value="${pagingindex.pageindex}" default="" />&nbsp;
										</span>
									</c:if> <c:if test="${pagingindex.current==0}">
										<span id="picbtn1"
											style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 20px; width: 20px;">&nbsp;<c:out
												value="${pagingindex.pageindex}" default="" />&nbsp;
										</span>
									</c:if> <c:if test="${pagingindex.doc==1}">
										<c:if test="${pagingindex.front==0}">									 
									 ..									 
									</c:if>

									</c:if> </a>
							</c:forEach>

							<span id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 20px; width: 20px;">&nbsp;<a>&gt;</a>&nbsp;
							</span> (
							<c:out value="${paingModel.rowcount}" default="0" />
							)
						</div>
					</div>

					<input type="hidden" name="docchannel" id="docchannel"
						value="<c:out value="${paingModel.docchannel}"/>"> <input
						type="hidden" name="pageindex" id="pageindex"
						value="<c:out value="${paingModel.pageindex}"/>">
							<div class="cbt"></div>
				</div>
			</div>
		</form>
		<div class="lmainR ofh" style="text-align: center;">
			<%@ include file="../website/tail.jsp"%>
			<div class="cbt"></div>
		</div>
	</form>
</body>
</html>
<script type="text/javascript">
	var gotoPage = function(pageindex) {
		$("#pageindex").val(pageindex);
		$("#queryForm").submit();
	}
</script>

