<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/votejs/voteindex.js"></script>
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<title></title>
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR ofh">
	<form id="actionform" method="post">
		<div class="lmainR"
			style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
			<div class="  artlistcell"
				style="width: 960px; height: 600px; overflow: scroll;">
				<div class="artListcell acell1">
					<div class="anwsls1" style="height: 480px;">
						<table style="height: 480px;">
							<tr>
								<th>
									<div class="artList">
										<input type="checkbox" name="checkall"
											onclick="javascript:selectAllDoc(this)" /> 
									</div>
								</th>
								<th>
									<div class="artList"></div>
								</th>
								<th>
									<div class="artList"> </div>
								</th>
								<th>
									<div class="artList">
										<br />
									</div>
								</th>
							</tr>
							<c:forEach items="${votes}" var="voteValue" varStatus="status">
								<tr>
									<td>
										<div class="artList" style="height: 310px;">
											<input type="checkbox" name="docnamecheckbox"
												id="<c:out value="${voteValue.voteid}" />" />
											<c:out value="${voteValue.topic}" />
										</div>
									</td>
									<td>
										<div class="artList" style="height: 100px;">
											<c:out value="${voteValue.artname}" />
										</div>
									</td>

									<td>
										<div class="artList">
											<c:out value="${voteValue.createtimestr}" />
										</div>
									</td>
									<td>
										<div class="artList">
											<c:if test="${voteValue.status==0}">
			                               
			                             </c:if>
											<c:if test="${voteValue.status==1}">
			                                
			                             </c:if>
											<c:if test="${voteValue.status==2}">
			                                
			                             </c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
							<input type="hidden" name="voteid" id="voteid">
						</table>
					</div>

				</div>
			</div>

			<div class="mnlist" style="text-align: center; height: 560px;">
				<a href="javascript:checkvote();"></a>  <a
					href="addvote.do">    </a> <a
					href="javascript:updvote();">   </a> <a
					href="javascript:deletevote();"> </a> <a
					href="javascript:viewresult();">   dung  </a> 
				<a href="javascript:join();"> </a>  <a
					href="sitemanagerindex.do"></a>
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
</html>