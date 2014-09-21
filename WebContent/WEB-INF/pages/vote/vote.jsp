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
		<form name="calform" action="calandview.do" method="post" id="calform">
			<div class="lmainR"
				style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
				<div class="mnlist">   </div>
				<div class="mnlist" style="width: 958px; height: 600px;">
					<div class="voteDesc" style="width: 90px; height: 600px;"
						id="commentdiv">
						<c:if test="${votevalue!=null}">
							<c:out value="${votevalue.topic}" />
						</c:if>
					</div>
					<div class="voteList">
						<table id="questionlist">
							<c:if test="${votevalue!=null}">
								<c:forEach items="${votevalue.details}" var="voteDetailValue"
									varStatus="status">
									<tr>
										<td class="title" style="height: 100%;"><c:out
												value="${voteDetailValue.questiondesc}" /></td>
									</tr>
									<c:forEach items="${voteDetailValue.questionValues}"
										var="questionValue" varStatus="status">
										<tr>
											<c:if test="${voteDetailValue.questiontype==1}">
												<td><c:out value="${questionValue.charindex}" /> <input
													type="radio"
													name="sing<c:out value="${questionValue.questionid}" />"
													id="<c:out value="${questionValue.answerid}" />"> <c:out
														value="${questionValue.answername}" /></input></td>
											</c:if>
											<c:if test="${voteDetailValue.questiontype==2}">
												<td><c:out value="${questionValue.charindex}" /> <input
													type="checkbox"
													name="multi<c:out value="${questionValue.questionid}" />"
													id="<c:out value="${questionValue.answerid}" />"> <c:out
														value="${questionValue.answername}" /></input></td>
											</c:if>
											<c:if test="${voteDetailValue.questiontype==3}">
												<td>
													<div class="voteDesc" contentEditable="true"
														style="width: 30px; height: 600px;"
														id="<c:out value="${questionValue.questionid}" />"></div>
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:if>
						</table>
						<input type="hidden" name="singglequestionrel"
							id="singglequestionrel" /> <input type="hidden"
							name="textquestionrel" id="textquestionrel" /> <input
							type="hidden" name="multiquestionrel" id="multiquestionrel" /> <input
							type="hidden" name="voteid" id="voteid"
							value="<c:out value="${votevalue.voteid}" />" />
					</div>
				</div>
				<div class="mnlist"
					style="width: 30px; height: 500px; text-align: center;">
					<!-- 			<a -->
					<%-- 				href="viewvoteresult.do?voteid=<c:out value="${votevalue.voteid}" />"> --%>
					<!-- 				   dung  </a> -->
					<a href="javascript:checkdate();">   
						 </a>

				</div>
			</div>
		</form>
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<div class="tailCard">
			<%@ include file="../website/tail.jsp"%>
		</div>
		<div class="cbt"></div>
	</div>
</body>

<SCRIPT LANGUAGE="JAVASCRIPT">
	var jsonvote = <c:out value="${jsonvote}" escapeXml="false"   />;
	var checkdate = function() {
		//校验每个问题是否已经选择或者填写，没有则提示
		var singglequestionrel = "";
		var multiquestionrel = "";
		var textquestionrel = "";
		for (i in jsonvote.vote[0].details) {
			var questiontype = jsonvote.vote[0].details[i].questiontype;
			var questionid = jsonvote.vote[0].details[i].questionid;
			if (questiontype == 1) {// 单选
				singglequestionrel = singglequestionrel + questionid;
				var selectid = "sing" + questionid;
				//	alert(selectid);
				var selectstr = 'input[name=\'' + selectid + '\']';
				var seles = $(selectstr);
				var seleccount = 0;
				seles.each(function(i, o) {
					if (o.checked) {
						singglequestionrel = singglequestionrel + ","
								+ $(o).attr("id");
						seleccount++;
					}
				});
				if (seleccount == 0) {
					var answerindex = i + 1;
					MessageWindow.showMess(" " + answerindex
							+ "    ");
					return;
				}
				singglequestionrel = singglequestionrel + "##";
			} else if (questiontype == 2) {//多选
				multiquestionrel = multiquestionrel + questionid + "@";
				var selectid = "multi" + questionid;
				//alert(selectid);
				var seleccount = 0;
				var selectstr = 'input[name=\'' + selectid + '\']';
				var seles = $(selectstr);
				seles.each(function(i, o) {
					if (o.checked) {
						multiquestionrel = multiquestionrel + ","
								+ $(o).attr("id");
						seleccount++;
					}
				});
				if (seleccount == 0) {
					var answerindex = i + 1;
					MessageWindow.showMess(" " + answerindex
							+ "    ");
					return;
				}
				multiquestionrel = multiquestionrel + "##";

			} else {//填写
				if ($("#" + questionid).text() == null
						|| $("#" + questionid).text() == '') {
					var answerindex = i + 1;
					MessageWindow.showMess(" " + answerindex
							+ "    ");
					return;
				}
				textquestionrel = textquestionrel + questionid + ","
						+ $("#" + questionid).text() + "##";

			}

		}
		$("#singglequestionrel").val(singglequestionrel);
		$("#multiquestionrel").val(multiquestionrel);
		$("#textquestionrel").val(textquestionrel);
		$("#calform").submit();
		//
	}
</SCRIPT>
</html>