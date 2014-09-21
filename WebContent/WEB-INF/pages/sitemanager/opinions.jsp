<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
</head>
<body>
<div class="lmainR ofh" style="text-align: center; height:64px;">
			<img src="img/logo.png" width="980" />
		</div>
	<form action="articlelist.do" id="Form" method="post">
		<div class="lmainR"
			style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr; height: 480px">
			<div>
				<table style="border: solid 1px #eee" valign="top">
					<tr>
						<td>
							<div class="m1ln h100">  </div>
						</td>
						<td>
							<div class="inputHolder">
								<input type="text" name="strcrtime" id="strcrtime"
									value="<c:out value="${queryDocForm.strcrtime}"/>"
									style="height: 40px;" class="modTxtTime" readonly="readonly"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> - <input
									type="text" name="endcrtime" id="endcrtime"
									value="<c:out value="${queryDocForm.endcrtime}"/>"
									style="height: 40px;" class="modTxtTime" readonly="readonly"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div class="m1ln h100">
								&nbsp;&nbsp;<a href="javascript:search();"></a>
							</div>
						</td>
						<td>
							<div class="m1ln h100">
								&nbsp;&nbsp;<a href="javascript:clearQueryForm();"></a>
							</div>
						</td>

					</tr>
				</table>
			</div>
			<div class="  artlistcell"
				style="width: 900px; height: 440px; overflow: scroll;">
				<div class="artListcell acell1" style="height: 410px; width: 880px;">
					<div class="anwsls1">
						<c:forEach items="${opinionValues}" var="opinionValue"
							varStatus="status">
							<div class="artList">
								<a
									href="viewopinion.do?opinionid=<c:out value="${opinionValue.opinionid}" />"><c:out
										value="${opinionValue.title}" /></a><a>&nbsp;&nbsp;&nbsp;&nbsp;<c:out
										value="${opinionValue.artname}" />
								</a>
							</div>

						</c:forEach>
						<input type="hidden" name="docids" id="docids" />
						</table>
					</div>
				</div>
			</div>
			<br>
	</form>
<!-- 	<div class="lmainR ofh" style="text-align: center;"> -->
<!-- 		<div class="tailCard"> -->
<%-- 			<%@ include file="../website/tail.jsp"%> --%>
<!-- 		</div> -->
<!-- 		<div class="cbt"></div> -->
<!-- 	</div> -->
</body>
</html>
<script type="text/javascript">
 var gotoPage=function(pageindex){
 $("#pageindex").val(pageindex);
 $("#queryForm").submit();
 }
</script>

