<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>  blog</title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/huh.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/sitejs/searchuser.js"></script>
<link href="img/css/selectpanel.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
</head>
<body>
	<%@ include file="head.jsp"%>
	<div class="lmainR"
		style="border: solid 1px #1b6dd2; width: 990px; margin: 1px auto;">
		<form action="searchblog.do" method="post" id="queryForm">
			<div class="lmainR"
				style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr; height: 600px; width: 990px; background: #1b6dd2; border-radius: 5px 5px 5px 5px;">

				<table
					style="border: solid 1px #eee; height: 600px; float: left; background: #fff;"
					valign="top">
					<tr>
						<td>
							<div class="m1ln h100">  </div>
						</td>
						<td>
							<div class="m1ln h100" style="height: 160px">
								<a id="provincename"
									href="javascript:showselpanel(true,'provinces')"> 
									 </a> <input type="hidden" id="district" name="district" />
								 <a id="hsienname"
									href="javascript:showselpanel(true,'hsiens')"></a>
								<input type="hidden" id="qx" name="qx" />
							</div>
						</td>
						<td>
							<div class="m1ln h100"> </div>
						</td>
						<td>
							<div class="inputHolder">
								<input type="text" name="searchtext" id="searchtext" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="m1ln h100">  </div>
						</td>
						<td>
							<div class="inputHolder" style="padding-left: 5px;">
								<input type="text" name="strregtime" id="strregtime"
									style="height: 40px;" class="modTxtTime" readonly="readonly"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> - <input
									type="text" name="endregtime" id="endregtime"
									style="height: 40px;" class="modTxtTime" readonly="readonly"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div class="m1ln h100">
								&nbsp;&nbsp;<a href="javascript:searchusers();"></a>
							</div>
						</td>
						<td>
							<div class="m1ln h100">
								&nbsp;&nbsp;<a href="javascript:clearQueryForm();"></a>
							</div>
						</td>

					</tr>
				</table>
				<div class="lcell "
					style="width: 889px; height: 600px; float: left; background: #fff;">
					<div class=" " style="height: 600px;">
						<div class="ttl1 m1ln" style="height: 598px;">
							<a href="#"></a>
						</div>
						<div style="width: 820px; margin: 0px; height: 500px" class="ofh">
							<c:forEach items="${users}" var="userValue" varStatus="status">
								<div class="xldgurg" style="width: 124px; height: 470px;">
									<div class="avtr">
										<a
											href="gouserindex.do?userid=<c:out value="${userValue.userid}" />">
											<img
											src="html/userhead/<c:out value="${userValue.headurl}"/>"
											width="570" height="447" />
										</a> <input type="checkbox" name="selectbox"
											id="<c:out value="${userValue.userid}" />" />
									</div>
									<div class="desc  " style="color: #000; height: 390px;">
										<div class="m1ln" style="height: 390px;">
											<a
												href="gouserindex.do?userid=<c:out value="${userValue.userid}" />">
												<span class="label1"><c:out
														value="${userValue.artname}" /> </span> : <c:out
													value="${userValue.regdateStr}" />
											</a>
										</div>
										<div class="m1ln" style="height: 390px;">
											<span class="label1">  : </span>
											<c:out value="${userValue.nowprovince}" default="  " />
										</div>
										<div class="m1ln" style="height: 390px;">
											<span class="label1">  :</span>
											<c:choose>
												<c:when test="${userValue.sex==1}">
							         
							       </c:when>
												<c:when test="${userValue.sex==0}">
							          
							       </c:when>
												<c:otherwise>
							          
							       </c:otherwise>
											</c:choose>
											<span class="label1">    :</span>
											<c:out value="${userValue.age}" default="  " />
											<span class="label1">   :</span>
											<c:out value="${userValue.regdatestr}" default="  " />
										</div>
										<div class="m1ln" style="height: 390px;">
											<span class="label1">   : </span>
											<c:out value="${totalVisitCount}" />
											<span class="label1">   :</span> <span
												class="label1">   :<span>
										</div>
										<div class="m1ln" style="height: 390px;">
											<span class="label1">    :</span>
											<c:out value="${userValue.logindatestr}" default="" />
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<input type="hidden" name="pageindex" id="pageindex">

						<div class="vpagenav">
							<span id="picbtn1" class="picbtn">&nbsp;<a>&lt;</a>&nbsp;
							</span>
							<c:forEach items="${pagingindexs}" var="pagingindex"
								varStatus="status">
								<a
									href="javascript:paginguser(<c:out value="${pagingindex.pageindex}"
										default="" />)"><c:if
										test="${pagingindex.doc==1}">
										<c:if test="${pagingindex.front==1}">									 
									 ..									 
									</c:if>
									</c:if> <c:if test="${pagingindex.current==1}">
										<span id="picbtn1" class="picbtn">&nbsp;<c:out
												value="${pagingindex.pageindex}" default="" />&nbsp;
										</span>
									</c:if> <c:if test="${pagingindex.current==0}">
										<span id="picbtn1" class="picbtn">&nbsp;<c:out
												value="${pagingindex.pageindex}" default="" />&nbsp;
										</span>
									</c:if> <c:if test="${pagingindex.doc==1}">
										<c:if test="${pagingindex.front==0}">									 
									 ..									 
									</c:if>

									</c:if> </a>
							</c:forEach>
							<span id="picbtn1" class="picbtn">&nbsp;<a>&gt;</a>&nbsp;
							</span> (
							<c:out value="${usercount}" default="0" />
							)
						</div>

					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<%@ include file="../website/tail.jsp"%>
		<div class="cbt"></div>
	</div>
	<!-- 选择住地（省）-->
	<div class="channelpanel" id="provinces" style="display: none">
		<div class="paneltitle">
			<a href="javascript:showselpanel(false,'provinces');"
				style="cursor: pointer"> <img src="img/gbchange.png" width="15"
				height="16"></a>
		</div>
		<div class="channellist">
			<c:forEach items="${districts}" var="districtValue"
				varStatus="status">
				<div class="mnlist"
					style="text-indent: 0px; height: 98px; width: 25px;">
					<a
						onclick="javascript:changeSel('<c:out value="${districtValue.districtcode}"/>','<c:out value="${districtValue.districtname}"/>','district','provincename','provinces');loadChildDistrict('district',1);"
						style="cursor: pointer" class=""><c:out
							value="${districtValue.districtname}" /> </a>
				</div>
			</c:forEach>

			<div class="clear"></div>
		</div>
	</div>
	<!-- 选择住地（旗县）-->
	<div class="channelpanel" id="hsiens" style="display: none">
		<div class="paneltitle">
			<a href="javascript:showselpanel(false,'hsiens');"
				style="cursor: pointer"> <img src="img/gbchange.png" width="15"
				height="16"></a>
		</div>
		<div id="hsienlist" class="channellist">
			<c:forEach items="${districts}" var="districtValue"
				varStatus="status">
				<div class="mnlist"
					style="text-indent: 0px; height: 120px; width: 25px;">
					<a
						onclick="javascript:changeSel('<c:out value="${districtValue.districtcode}"/>','<c:out value="${districtValue.districtname}"/>','qx','hsienname','hsiens');"
						style="cursor: pointer" class=""><c:out
							value="${districtValue.districtname}" /> </a>
				</div>
			</c:forEach>

			<div class="clear"></div>
		</div>
	</div>
</body>
<script>
	var districtsdata = <c:out value="${districtsdata}" escapeXml="false" />;
</script>
</html>