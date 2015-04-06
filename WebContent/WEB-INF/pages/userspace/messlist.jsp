<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/sitejs/messlist.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<style>
.divh3 {
	position: relative;
	padding-bottom: 0.5em;
	margin-top: 2px;
	min-height: 0px;
	padding-left: 0.7em;
	padding-right: 0.5em;
	cursor: pointer;
	padding-top: 0.5em;
	border-bottom: #fbd850 1px solid;
	border-left: #fbd850 1px solid;
	/*color:#eb8f00;*/
	border-top: #fbd850 1px solid;
	font-weight: bold;
	border-right: #fbd850 1px solid;
}
</style>
</head>
<body>
<body>
	<c:if test="${type==1}">
		<div id="reclist">
			<div class="  lcell"
				style="width: 760px; height: 450px; background-color: #fff; float: left;"
				id="receivediv">
				<c:forEach items="${messList}" var="messageValue" varStatus="status">
					<div class="xldgurg" style="width: 85px; height: 450px;"
						id="xldgurg_<c:out value="${messageValue.messagesenderid}" />">
						<div class="avtr">
							<a
								href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />">
								<img
								src="html/userhead/<c:out value="${messageValue.messagesenderurl}"/>"
								width="570" height="447" />
							</a>
						</div>
						<div class="desc  "
							style="color: #000; height: 370px; width: 85px;">
							<div class="m1ln" style="height: 350px; float: left;">
								<a
									href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"><c:out
										value="${messageValue.artname}" /> </a> 
								<c:if test="${messageValue.messtype==1}">
								          
								</c:if>
								<c:if test="${messageValue.messtype==2}">
								  
								</c:if>
								<c:if test="${messageValue.messtype==4}">
								 
								</c:if>
							</div>
							<div class="m1ln"
								style="height: 350px; width: 65px; float: left;"
								id="<c:out
										value="${messageValue.messageid}" />">
								<c:if test="${messageValue.messtype==2}">
									    <c:out value="${messageValue.sendtimestr}" />
									<br>
									<a style="color:#f00;"
										href="javascript:readmess('reclist','recdetail','<c:out
										value="${messageValue.messageid}" />',1);"></a> &nbsp;  &nbsp;
									<a style="color:#f00;"
										href="javascript:delMessage('<c:out value="${messageValue.messageid}" />');"></a>
								</c:if>
								<c:if test="${messageValue.messtype==1}">
									    <c:out value="${messageValue.sendtimestr}" />
									<br>
									<a style="color:#f00;"
										href="javascript:readcomm('<c:out value="${messageValue.resourceid}" />')"></a> &nbsp;  &nbsp;
									<a style="color:#f00;"
										href="javascript:delMessage('<c:out value="${messageValue.messageid}" />');"></a>
								</c:if>
								<c:if test="${messageValue.messtype==4}">
								   
									<c:out value="${messageValue.sendtimestr}" />
									<br>
								  :<c:out value="${messageValue.contenthtml}"
										escapeXml="false" />
									<br>
									<a
										href="javascript:window.parent.addfriends('<c:out
										value="${messageValue.messagesenderid}" />',1,'<c:out
										value="${messageValue.messageid}" />');"
										style="color: #f00;"></a>  <a href="#"
										style="color: #f00;"> </a>
								</c:if>

							</div>
						</div>
					</div>
				</c:forEach>

			</div>
			<div class="vpagenav mnlist"
				style="height: 300px; width: 20px; float: left; padding-top: 0px; padding-bottom: 0px; text-indent: 0px;">
				<span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
				</span>
				<c:forEach items="${pageindexs}" var="pagingindex"
					varStatus="status">
					<a
						href="getMessage.do?type=1&pageIndex=<c:out value="${pagingindex.pageindex}"
										default="" />"><c:if
							test="${pagingindex.doc==1}">
							<c:if test="${pagingindex.front==1}">									 
									 ..									 
									</c:if>
						</c:if> <c:if test="${pagingindex.current==1}">
							<span id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(204, 0, 0); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<c:out
									value="${pagingindex.pageindex}" default="" />&nbsp;
							</span>
						</c:if> <c:if test="${pagingindex.current==0}">
							<span id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(100, 100, 100); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<c:out
									value="${pagingindex.pageindex}" default="" />&nbsp;
							</span>
						</c:if> <c:if test="${pagingindex.doc==1}">
							<c:if test="${pagingindex.front==0}">									 
									 ..									 
									</c:if>

						</c:if> </a>
				</c:forEach>

				<span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&gt;</a>&nbsp;
				</span> (
				<c:out value="${doccount}" default="0" />
				)
			</div>
		</div>
		<div id="recdetail"
			style="display: none; -webkit-writing-mode: vertical-lr; writing-mode: tb-lr; line-height: 23px; word-break: break-all; text-align: left; -webkit-text-orientation: sideways-right; padding: 10px; float: left; height: 95%; background-color: #fff; width: 760px;">

		</div>
	</c:if>

	<c:if test="${type==2}">
		<div id="sendlistdiv">
			<div class="  lcell"
				style="width: 760px; float: left; height: 450px; background-color: #fff;">
				<c:forEach items="${messList}" var="messageValue" varStatus="status">
					<div class="xldgurg" style="width: 85px; height: 470px;">
						<div class="avtr">
							<a
								href="gouserindex.do?userid=<c:out value="${messageValue.userid}" />">
								<img
								src="html/userhead/<c:out value="${messageValue.userurl}"/>"
								width="570" height="447" />
							</a>
						</div>
						<div class="desc  "
							style="color: #000; height: 370px; width: 85px;">
							<div class="m1ln" style="height: 350px; float: left;">
								<a
									href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"><c:out
										value="${messageValue.artname}" /> </a>    
							</div>
							<div class="m1ln"
								style="height: 350px; width: 65px; float: left;"
								id="<c:out
										value="${messageValue.messageid}" />">

								  
								<c:out value="${messageValue.sendtimestr}" />
								<br> <a style="color:#f00;"
									href="javascript:readmess('sendlistdiv','senddetail','<c:out
										value="${messageValue.messageid}" />',1);"></a>
								&nbsp; &nbsp; <a style="color:#f00;"
									href="javascript:delMessage('<c:out value="${messageValue.messageid}" />');"></a>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="vpagenav mnlist"
				style="height: 300px; width: 20px; float: left; padding-top: 0px; padding-bottom: 0px; text-indent: 0px;">
				<span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
				</span>
				<c:forEach items="${pageindexs1}" var="pagingindex"
					varStatus="status">
					<a
						href="getMessage.do?type=2&pageIndex=<c:out value="${pagingindex.pageindex}"
										default="" />"><c:if
							test="${pagingindex.doc==1}">
							<c:if test="${pagingindex.front==1}">									 
									 ..									 
									</c:if>
						</c:if> <c:if test="${pagingindex.current==1}">
							<span id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(204, 0, 0); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<c:out
									value="${pagingindex.pageindex}" default="" />&nbsp;
							</span>
						</c:if> <c:if test="${pagingindex.current==0}">
							<span id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(100, 100, 100); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<c:out
									value="${pagingindex.pageindex}" default="" />&nbsp;
							</span>
						</c:if> <c:if test="${pagingindex.doc==1}">
							<c:if test="${pagingindex.front==0}">									 
									 ..									 
									</c:if>

						</c:if> </a>
				</c:forEach>

				<span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&gt;</a>&nbsp;
				</span> (
				<c:out value="${doccount}" default="0" />
				)
			</div>
		</div>
		<div id="senddetail"
				style="display: none; -webkit-writing-mode: vertical-lr; writing-mode: tb-lr; line-height: 23px; word-break: break-all; text-align: left; -webkit-text-orientation: sideways-right; padding: 10px; float: left; height: 95%; background-color: #fff;width: 760px;"></div>
	</c:if>
</body>
</html>