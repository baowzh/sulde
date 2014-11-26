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
				<div style="height: 598px; border: solid 1px #eee;">
					<table style="padding-top: 2em;" valign="top">
						<tr>
							<td>
								<div class="m1ln h100"> </div>
							</td>
							<td>
								<div class="inputHolder">
									<select id="channel" name="channel"
										style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
										<option value="#"> </option>
									</select>
								</div>
							</td>
							<td>
								<div class="m1ln h100"> </div>
							</td>
							<td>
								<div class="inputHolder">
									<select name="status" id="status"
										style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
										<option value="2"> </option>
										<option value="1"> </option>
										<option value="0" selected="true"></option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="m1ln h100"> </div>
							</td>
							<td style="background: #eee;">
								<div class="inputHolder">
									<input type="text" name="authorname" id="authorname"
										value="<c:out value="${queryDocForm.authorname}"/>" />
								</div>
							</td>
							<td>
								<div class="m1ln h100">  </div>
							</td>
							<td>
								<div class="inputHolder" style="background: #eee;">
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
							<td>
								<div class="m1ln h100"> </div>
							</td>
							<td>
								<div class="inputHolder">
									<input type="text" name="doctitle" id="doctitle"
										value="<c:out value="${queryDocForm.doctitle}"/>" />
								</div>
							</td>
							<td>
								<div class="m1ln h100">  </div>
							</td>
							<td>
								<div class="inputHolder">
									<select name="top" id="top"
										style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
										<option value="3">  </option>
										<option value="2">  </option>
										<option value="1">  </option>
										<option value="0" selected="true"></option>
									</select>
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
					style="width: 840px; height: 630px; overflow: scroll;">
					<div class="artListcell acell1">
						<div class="anwsls1">
							<table>
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
										<div class="artList"></div>
									</th>
									<th>
										<div class="artList"></div>
									</th>
									<th>
										<div class="artList">
											<br />
										</div>
									</th>
								</tr>
								<c:forEach items="${docs}" var="documentValue"
									varStatus="status">
									<tr>
										<td>
											<div class="artList" style="height: 310px;">
												<input type="checkbox" name="docnamecheckbox"
													id="<c:out value="${documentValue.docid}"/>" /> <a
													<c:if test="${documentValue.doctype==1}">
			   href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}"/>"
			  </c:if>
													<c:if test="${documentValue.doctype==2}">
			   href="getimginfo.do?imgid=<c:out value="${documentValue.docid}"/>&userid=<c:out value="${documentValue.userid}"/>"
			   </c:if>>
													<c:out value="${documentValue.doctitle}" />
												</a>
											</div> <input type="hidden"
											id="title<c:out value="${documentValue.docid}"/>"
											value="<c:out value="${documentValue.doctitle}" />">
										</td>
										<td>
											<div class="artList" style="height: 100px;">
												<a><c:out value="${documentValue.docauthor}" /></a>
											</div>
										</td>
										<td>
											<div class="artList">
												<a><c:out value="${documentValue.docRelTimeStr}" /></a>
											</div>
										</td>
										<td>
											<div class="artList">
												<c:out value="${documentValue.chnaname}" />
											</div>
										</td>
										<td>
											<div class="artList">
												<a
													href="toupddoc.do?docid=<c:out value="${documentValue.docid}" />&opertype=3"></a>
											</div>
										</td>
										<td>
											<div class="artList">
												<c:if test="${documentValue.docstatus==1}">
													<input type="checkbox" checked="false" disabled="true" />
												</c:if>
												<c:if test="${documentValue.docstatus==2}">
													<input type="checkbox" checked="true" disabled="true" />
												</c:if>
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

				<div class="mnlist" style="text-align: center; height: 550px;">
					<a href="javascript:checkdoc();"></a>&nbsp;&nbsp;<a
						href="javascript:addselecteddoc();"></a>&nbsp;&nbsp;<a
						href="javascript:delselecteddoc();"> </a>&nbsp;&nbsp;<a
						href="javascript:addindexdoc();">  </a>&nbsp;&nbsp;<a
						href="javascript:deletedoc();"> </a>&nbsp;&nbsp;<a
						href="javascript:asTopWriting();">  
						&nbsp;&nbsp;</a> <a href="sitemanagerindex.do"></a>
				</div>
			</div>
		</form>
	</div>
	<div style="display: none">
		<div class="lcell" style="width: 140px; height: 340px;"
			id="astopwriting">
			<form action="astopwriting.do" id="astopwritingform" method="post"
				enctype="multipart/form-data" />
			<table border="0" style="margin: 1em auto;">
				<tr>
					<td height="100">
						<div class="m1ln h100" style="height: 110px;"> 
							 </div>
					</td>
					<td>
						<div class="m1ln h100">:</div>
					</td>
					<td>
						<div class="m1ln h100"> :</div>
					</td>
					<td>
						<div class="m1ln h100"> :</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="mfl">
							<input type="text" name="title" id="title" /> <input
								type="hidden" name="docid" id="docid" />
						</div>
					</td>
					<td>
						<div class="inputHolder">
							<select name="toptype" id="toptype"
								style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
								<option value="3">  </option>
								<option value="2">  </option>
								<option value="1">  </option>
							</select>
						</div>
					</td>
					<td>
						<div class="mfl">
							<input type="file" name="playimg" id="playimg"
								style="height: 210px;"></input>
						</div>
					</td>
					<td>
						<div class="inputHolder">
							<input type="text" name="playindex" id="playindex"
								></input>
						</div>
					</td>
					<td>
						<div class="m1ln h100">
							<a href="javascript:addtotopwriting();"></a>
						</div>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<!-- 		<div class="tailCard"> -->
		<%@ include file="../website/tail.jsp"%>
		<!-- 		</div> -->
		<div class="cbt"></div>
	</div>
</body>
</html>
