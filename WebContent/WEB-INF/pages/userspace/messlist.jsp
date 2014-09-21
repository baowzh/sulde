<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/sitejs/messlist.js"></script>
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

	<div id="accordion" class="lmainR"
		style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; width: 740px; height: 450px;">
		<!--  -->
		<c:if test="${type==1}">
			<div class="  lcell" style="width: 740px; height: 450px;"
				id="receivediv">
				<c:forEach items="${messList}" var="messageValue" varStatus="status">
					<div class="xldgurg" style="width: 124px; height: 450px;">
						<div class="avtr">
							<a
								href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />">
								<img
								src="getsmheadimge.do?userid=<c:out value="${messageValue.messagesenderid}"/>"
								width="570" height="447" />
							</a> <input type="checkbox" name="selectbox"
								id="<c:out value="${messageValue.userid}" />" />
						</div>
						<div class="desc  "
							style="color: #000; height: 370px; widht: 120px;">
							<div class="m1ln" style="height: 350px;">
								<a
									href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"><c:out
										value="${messageValue.artname}" /> </a> 
								<c:if test="${messageValue.messtype==3}">
								  
								</c:if>
								<c:if test="${messageValue.messtype==4}">
								  
								</c:if>
							</div>
							<div class="m1ln" style="height: 350px; width: 100px;"
								id="<c:out
										value="${messageValue.messageid}" />">
								<c:if test="${messageValue.messtype==3}">
									    <c:out value="${messageValue.sendtimestr}" />
									<br>
									<a
										href="javascript:readmessage('<c:out
										value="${messageValue.messageid}" />',1);"></a> &nbsp;  &nbsp;
									<a href="#"></a>
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
			<div class="vpagenav" style="height: 400px;text-align:top;">
				<span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
				</span> <span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>1</a>&nbsp;
				</span> <span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
				</span>
			</div>
		</c:if>

		<c:if test="${type==2}">
			<div class="  lcell" style="width: 800px;" id="receivediv">
				<c:forEach items="${messList}" var="messageValue" varStatus="status">
					<div class="xldgurg" style="width: 124px; height: 470px;">
						<div class="avtr">
							<a
								href="gouserindex.do?userid=<c:out value="${messageValue.userid}" />">
								<img
								src="getsmheadimge.do?userid=<c:out value="${messageValue.userid}"/>"
								width="570" height="447" />
							</a> <input type="checkbox" name="selectbox"
								id="<c:out value="${messageValue.userid}" />" />
						</div>
						<div class="desc  "
							style="color: #000; height: 370px; widht: 120px;">
							<div class="m1ln" style="height: 350px;">
								<a
									href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"><c:out
										value="${messageValue.artname}" /> </a>    
							</div>
							<div class="m1ln" style="height: 350px; width: 100px;"
								id="<c:out
										value="${messageValue.messageid}" />">

								  
								<c:out value="${messageValue.sendtimestr}" />
								<br> <a
									href="javascript:readmessage('<c:out
										value="${messageValue.messageid}" />',1);"></a>
								&nbsp; &nbsp; <a href="#"></a>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="vpagenav" style="height: 400px;text-align:top;">
				<span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
				</span> <span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>1</a>&nbsp;
				</span> <span id="picbtn1"
					style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
				</span>
			</div>
		</c:if>

	</div>

</body>
</html>