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
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR ofh">
	<div class="lmainR"
		style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
		<div class="mnlist">   </div>
		<div class="mnlist" style="width: 965px; height: 600px;">
			<div class="voteDesc" contentEditable="true"
				style="width: 100px; height: 600px;" id="commentdiv">
				<c:if test="${votevalue!=null}">
					<c:out value="${votevalue.topic}" />
				</c:if>
			</div>
			<div class="voteList">
				<table id="questionlist">
					<c:if test="${votevalue!=null}">
						<c:forEach items="${votevalue.details}" var="voteDetailValue"
							varStatus="status">
							<tr
								name="name<c:out
										value="${voteDetailValue.questionid}" />">
								<td class="title" style="height: 100%;"><c:out
										value="${voteDetailValue.questiondesc}" /></td>
							</tr>
							<c:forEach items="${voteDetailValue.questionValues}"
								var="questionValue" varStatus="status">
								<tr
									name="name<c:out
										value="${voteDetailValue.questionid}" />">
									<c:if test="${voteDetailValue.questiontype==1}">
										<td><c:out value="${questionValue.charindex}" /> <input
											type="radio"
											name="sing<c:out value="${questionValue.questionid}" />"
											id="<c:out value="${questionValue.answerid}" />"> <c:out
												value="${questionValue.answername}" /></input></td>
									</c:if>
									<c:if test="${voteDetailValue.questiontype==2}">
										<td><c:out value="${questionValue.charindex}" /><input
											type="checkbox"
											name="multi<c:out value="${questionValue.questionid}" />"
											id="<c:out value="${questionValue.answerid}" />"> <c:out
												value="${questionValue.answername}" /></input></td>
									</c:if>
									<c:if test="${voteDetailValue.questiontype==3}">
										<td>
											<div class="voteDesc" contentEditable="true"
												style="width: 30px; height: 600px;" id="textanswer"></div>
										</td>
									</c:if>
								</tr>
							</c:forEach>
							<tr
								name="name<c:out
										value="${voteDetailValue.questionid}" />">
								<td><a
									href="javascript:deletequestion('<c:out value="${voteDetailValue.questionid}" />',<c:out value="${voteDetailValue.questiontype}" />);">
										   &nbsp;&nbsp;</a><a
									href="javascript:deleteselection('<c:out value="${voteDetailValue.questionid}" />',<c:out value="${voteDetailValue.questiontype}" />);">&nbsp;&nbsp;
										 </a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
		<div class="mnlist"
			style="width: 30px; height: 500px; text-align: center;">
			<a href="javascript:opensinquestionwin();">  
				 </a> <a href="javascript:openmultquestionwin();">
				  </a> <a href="javascript:openquestionwin();">
				 </a>
		</div>
		<div class="mnlist"
			style="width: 30px; height: 500px; text-align: center;">
			<a href="javascript:savevote();"> </a>
		</div>


	</div>
	</div>
	<div id="singquestionse"
		style="-webkit-writing-mode: vertical-lr; important !; display: none">
		<div class="mfl" style="float: left;">
			<input type="text" value="        !"
				name="questiondesc">
		</div>
		<div class="nwsls1">
			<div class="nwsl1" style="width: 35px;">
				<div class="mfl" style="width: 32px;">
					<input type="text" value="" name="sele">
				</div>
			</div>
			<div class="nwsl1" style="width: 35px;">
				<div class="mfl" style="width: 32px;">
					<input type="text" value="" name="sele">
				</div>
			</div>
			<div class="nwsl1" style="width: 35px;">
				<div class="mfl" style="width: 32px;">
					<input type="text" value="" name="sele">
				</div>
			</div>
			<div class="nwsl1" style="width: 35px;">
				<div class="mfl" style="width: 32px;">
					<input type="text" value="" name="sele">
				</div>
			</div>
			<div class="nwsl1" style="width: 35px;">
				<div class="mfl" style="width: 32px;">
					<input type="text" value="" name="sele">
				</div>
			</div>
		</div>
		<div class="mnlist">
			<a id="savebutton" href="javascript:addquestion();"> </a>
		</div>
		<input type="hidden" name="voteid" value="<c:out value="${voteid}" />"
			id="voteid"> <input type="hidden" name="questiontype"
			id="questiontype">
	</div>
	<div id="textquestionse"
		style="-webkit-writing-mode: vertical-lr; important !; display: none">
		<div class="mfl">
			<input type="text" value="        !"
				name="textesc" id="textesc">
		</div>
		<div class="mnlist">
			<a id="savebutton" href="javascript:addquestion();"> </a>
		</div>
		<input type="hidden" name="voteid" value="<c:out value="${voteid}" />"
			id="voteid">
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<div class="tailCard">
			<%@ include file="../website/tail.jsp"%>
		</div>
		<div class="cbt"></div>
	</div>
</body>
</html>