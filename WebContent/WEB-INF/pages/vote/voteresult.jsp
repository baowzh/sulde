<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> </title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/voteDesign.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/votejs/votedesign.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<!-- 	<div class="news1"> -->
	<div class="lmainR">
		<div class="mnlist"
			style="width: 990px; height: 600px; margin-left: 4px;">
			<div class="voteDesc" style="width: 90px; height: 600px;"
				id="commentdiv">
				<c:if test="${votevalue!=null}">
					<c:out value="${votevalue.topic}" />
					<br>       
					<c:out value="${votevalue.joinercount}" />
				</c:if>
			</div>
			<div class="voteList">
				<table id="questionlist">
					<c:if test="${votevalue!=null}">
						<c:forEach items="${votevalue.details}" var="voteDetailValue"
							varStatus="status">
							<tr>
								<td class="title" style="height: 100%;"><c:out
										value="${voteDetailValue.questiondesc}" /> <c:if
										test="${voteDetailValue.questiontype==1}">(  )</c:if>
									<c:if test="${voteDetailValue.questiontype==2}">( )</c:if>
									<c:if test="${voteDetailValue.questiontype==3}">( )</c:if></td>
							</tr>
							<c:choose>
								<c:when test="${voteDetailValue.questiontype==1}">
									<tr>
										<td class="title" style="height: 100%;"><img
											src="getchar.do?voteid=<c:out value="${voteDetailValue.voteid}" />&questionid=<c:out value="${voteDetailValue.questionid}" />"
											widht=300 height=300></td>
									</tr>
									<c:forEach items="${voteDetailValue.questionValues}"
										var="questionValue" varStatus="status">
										<tr>
											<c:if test="${voteDetailValue.questiontype==1}">
												<td><c:out value="${questionValue.charindex}" /> <c:out
														value="${questionValue.answername}" /></td>
											</c:if>
											<c:if test="${voteDetailValue.questiontype==2}">
												<td><c:out value="${questionValue.charindex}" /> <c:out
														value="${questionValue.answername}" /></td>
											</c:if>
											<c:if test="${voteDetailValue.questiontype==3}">
												<td><c:out value="${questionValue.answername}" /></td>
											</c:if>
										</tr>
									</c:forEach>
								</c:when>
								<c:when test="${voteDetailValue.questiontype==2}">
									<tr>
										<td class="title" style="height: 100%;"><img
											src="getchar.do?voteid=<c:out value="${voteDetailValue.voteid}" />&questionid=<c:out value="${voteDetailValue.questionid}" />"
											widht=300 height=300></td>
									</tr>
									<c:forEach items="${voteDetailValue.questionValues}"
										var="questionValue" varStatus="status">
										<tr>
											<c:if test="${voteDetailValue.questiontype==1}">
												<td><c:out value="${questionValue.charindex}" /> <c:out
														value="${questionValue.answername}" /></td>
											</c:if>
											<c:if test="${voteDetailValue.questiontype==2}">
												<td><c:out value="${questionValue.charindex}" /> <c:out
														value="${questionValue.answername}" /></td>
											</c:if>
											<c:if test="${voteDetailValue.questiontype==3}">
												<td><c:out value="${questionValue.answername}" /></td>
											</c:if>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td>
											<!-- 文本回复问题iframe 中显示 --> <iframe width="100%" height="100%"
												frameBorder="0" frameSpacing="0" scrolling="auto"
												src="gettextquestioresult.do?voteid=<c:out value="${votevalue.voteid}" />&questionid=<c:out value="${voteDetailValue.questionid}" />"></iframe>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<div class="tailCard">
			<%@ include file="../website/tail.jsp"%>
		</div>
		<div class="cbt"></div>
	</div>
</body>
</html>