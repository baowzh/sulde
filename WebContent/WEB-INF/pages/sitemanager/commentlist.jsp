<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/sitejs/doccheck.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR ofh">
		<form action="articlelist.do" id="Form" method="post">
			<div class="lmainR"
				style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
				<div class="  artlistcell"
					style="width: 975px; height: 598px; overflow: scroll;">
					<div class="artListcell acell1" style="width: 800px;">
						<div class="anwsls1" style="width: 800px;">
							<table>
								<tr>
									<td>
										<div class="artList">
											<input type="checkbox" name="checkall"
												onclick="javascript:selectAllDoc(this)" />
										</div>
									</td>
									<td>
										<div class="artList">  </div>
									</td>
									<td>
										<div class="artList">  </div>
									</td>
									<td>
										<div class="artList"> </div>
									</td>

								</tr>
								<c:forEach items="${messagePaingModel.modelList}"
									var="documentValue" varStatus="status">
									<tr>
										<td>
											<div class="artList" style="height: 60px;">
												<input type="checkbox" name="docnamecheckbox"
													id="<c:out value="${documentValue.messageid}"/>" />
											</div>
										</td>
										<td>
											<div class="artList" style="height: 100px;">
												<c:if test="${documentValue.status==1}">
			                                 
			                                </c:if>
												<c:if test="${documentValue.status==0}">
			                                    
			                                </c:if>

											</div>
										</td>
										<td>
											<div class="artList" style="overflow: hidden; height: 310px;">
												<a
													href="getuserdocdetail.do?docid=<c:out value="${documentValue.resourceid}"/>">
													<c:out value="${documentValue.contenthtml}"
														escapeXml="false" />
												</a>
											</div>
										</td>
										<td>
											<div class="artList">
												<c:out value="${documentValue.sendtimestr}" />
											</div>
										</td>

									</tr>
								</c:forEach>
								<input type="hidden" name="docids" id="docids" />
							</table>
						</div>
						<div class="vpagenav">
							<input type="hidden" id="pageindex" name="pageindex" /> <span
								id="picbtn1"
								style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
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
								style="cursor: pointer; border: 1px solid rgb(204, 204, 204); display: inline-block; color: rgb(0, 0, 0); background-color: rgb(238, 238, 238); line-height: 15px; width: 15px;">&nbsp;<a>&lt;</a>&nbsp;
							</span> (
							<c:out value="${doccount}" default="0" />
							)
						</div>
					</div>
				</div>

				<div class="mnlist" style="text-align: center; height: 400px;">
					<a href="javascript:checkdoc()"></a>  <a
						href="javascript:checkdoc()"> </a>  <a
						href="javascript:deletedoc()"> </a> <a
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
