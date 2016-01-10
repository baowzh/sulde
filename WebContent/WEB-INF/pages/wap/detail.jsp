<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<!--HTML5 doctype-->
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,,height=device-height,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<title><c:out value="${documentValue.docabstract}"
		default="tegri" /></title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="site/css/wapdetail.css" rel="stylesheet" type="text/css" />
<link href="img/css/login.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script type="text/javascript" src="js/sitejs/wapdetail.js"></script>
<script type="text/javascript" src="js/sitejs/login.js"></script>
<script type="text/javascript" src="js/sitejs/regist.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
</head>
<body onload="q();">
	<div class="header">
		<div id="headimgdiv" style="height: 90px; float: left;">
<!-- 			<img src="site/img/phonehead.jpg" style="width: 104px; height: 90px;"> -->
		</div>
		<div class="mln"
			style="height: 90px; font-size: 18px; width: 22px; float: right; color: #fff; padding-top: 0px;">
			<a href="tologin.do"></a>
		</div>
		<div class="emptyItem" style="height: 70px; float: right;"></div>
		<div class="mln"
			style="height: 90px; font-size: 18px; width: 22px; float: right; color: #fff; padding-top: 0px;">
			<a href="registe.do"> </a>
		</div>
		<div class="emptyItem" style="height: 70px; float: left;"></div>
		<div class="mln"
			style="height: 90px; width: 22px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phonechannel.do" style="font-size: 18px;"> </a>
		</div>
		<div class="emptyItem" style="height: 70px; float: left;"></div>
		<div class="mln"
			style="height: 90px; width: 22px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phonebloglist.do" style="font-size: 18px;"> </a>
		</div>
		<div class="emptyItem" style="height: 70px; float: right;"></div>
		<div class="mln"
			style="height: 90px; width: 22px; font-size: 18px; float: right; color: #fff; padding-top: 0px;">
			<a href="phoneindex.do" style="font-size: 18px;"> </a>
		</div>
	</div>
	<div id="content" style="overflow: scroll;">
		<div class="main" id="main">
			<div
				style="float: left; padding-top: 20px; height: 95%; width: 180px;">
				<div class="phonesheet flt" style="padding-left: 10px;">
					<div class="avatar" style="width: 144px; height: 180px;">
						<a
							href="phoneuserindex.do?userid=<c:out value="${user.userid}" />"><img
							src="html/userhead/<c:out value="${user.headurl}" />"
							style="width: 144px; height: 180px;" /> </a>
					</div>
					<div class="m1ln name"
						style="height: 100px; overflow: hidden; font-size: 20px; color: #f00;">
						<a style="text-decoration: none; color: #f00; font-size: 20px;"
							href="phoneuserindex.do?userid=<c:out value="${user.userid}" />"><c:out
								value="${user.artname}" /> </a>
					</div>
				</div>
				<div class="phonesheet flt"
					style="padding-top: 10px; padding-left: 10px; width: 140px; margin: 0 auto">
					<div>

						 
						<c:choose>
							<c:when test="${user.sex==1}">
							         
							       </c:when>
							<c:when test="${user.sex==0}">
							          
							       </c:when>
							<c:otherwise>
							          
							       </c:otherwise>
						</c:choose>
					</div>
					<div>
						 
						<c:out value="${user.age}" default="  " />
					</div>
					<div>
						  
						<c:out value="${user.nowprovincename}" default="  " />
					</div>

					<div>
						 
						<c:out value="${user.regdatestr}" default="  " />
					</div>

					<div>
						 
						<c:out value="${totalVisitCount}" />
					</div>
				</div>
			</div>
			<div class="phonesheet"
				style="float: left; padding-top: 30px; height: 95%;">
				<div id="doctitle" class="msheet titlediv"
					style="line-height: 100%; margin: 20px;">
					<c:out value="${documentValue.doctitle}" />
				</div>
				<h2>
					 &nbsp;
					<c:out value="${documentValue.docchannelname}" />
					**   &nbsp;
					<c:out value="${documentValue.docRelTimeStr}" />
					**  &nbsp; <span id="readcount"><c:out
							value="${documentValue.readcount}" default="0" /></span> 

				</h2>
			</div>
			<div class="phonesheet"
				style="padding: 15px; float: left; height: 95%;">
				<c:out value="${documentValue.htmlstr}" escapeXml="false" />
				<div style="clear: both"></div>
			</div>
			
			<div class="phonesheet"
				style="float: left; height: 340px; margin-top: 0px; padding-top: 80px;">
				<img
					src="getQRCode.do?docid=<c:out value="${documentValue.docid}" />"
					width="200" height="240">
			</div>
			<div class="phonesheet"
				style="float: left; height: 340px; margin-top: 0px; padding-top: 80px;">
				         <br>       
			</div>
			<div class="phonesheet"
				style="float: left; height: 340px; margin-top: 0px; padding-top: 100px;">
				<c:if test="${self==1}">
					<a
						href="toupddoc.do?docid=<c:out value="${documentValue.docid}" />">
						  </a>
					<a href="javascript:deldoc(1);"> &nbsp;  </a>
					<br />
					<c:if test="${raceModelValue!=null&&isjoin==0}">
						<a
							href="javascript:showjoinracediv('<c:out value="${raceModelValue.raceid}" />','<c:out value="${documentValue.docid}" />')"><c:out
								value="${raceModelValue.racename}" />  </a>
						<input type="hidden" id="raceid" name="raceid"
							value="<c:out value="${raceModelValue.raceid}" />">
					</c:if>
					<c:if test="${raceModelValue!=null&&isjoin==1}">
						<a
							href="javascript:delfromrace('<c:out value="${raceModelValue.raceid}" />','<c:out value="${documentValue.docid}" />')"><c:out
								value="${raceModelValue.racename}" />  </a>
						<input type="hidden" id="raceid" name="raceid"
							value="<c:out value="${raceModelValue.raceid}" />">
					</c:if>
				</c:if>
				<c:if test="${raceModelValue!=null&&isjoin==1}">
					<a href="#"><c:out value="${raceModelValue.racename}" /> 
						  </a>
					<br>
					<a href="#">  :<fmt:formatNumber
							value="${raceDocumentValue.nettotalscore}" type="NUMBER"
							pattern="#0.00" />
					</a>
					<br>
					<a href="#">     :<c:out
							value="${raceDocumentValue.netscorecount}" default="0" />
					</a>
					<br>
					<a href="#">   <fmt:formatNumber
							value="${raceDocumentValue.netaveragescore}" type="NUMBER"
							pattern="#0.00" />
					</a>
					<br>
					<a href="#">   :<c:out
							value="${raceDocumentValue.spetotalscore}" default="0" />
					</a>
					<br>
					<a href="#">     :<c:out
							value="${raceDocumentValue.spescorecount}" default="0" />
					</a>
					<br>
					<a href="#">    <fmt:formatNumber
							value="${raceDocumentValue.speaveragescore}" type="NUMBER"
							pattern="#0.00" />
					</a>
					<br>
					<a href="#"> :   <fmt:formatNumber
							value="${raceDocumentValue.netaveragescore}" type="NUMBER"
							pattern="#0.00" /> X20%+    <fmt:formatNumber
							value="${raceDocumentValue.speaveragescore}" type="NUMBER"
							pattern="#0.00" /> X80%= <fmt:formatNumber
							value="${raceDocumentValue.finalscore}" type="NUMBER"
							pattern="#0.00" />

					</a>
					<br>
					<a style="color: #f00;"
						href="raceScoreDetail.do?raceid=<c:out value="${raceModelValue.raceid}"/>&docid=<c:out value="${documentValue.docid}"/>&round=<c:out value="${raceModelValue.round}"/>">
						   </a>

					<br>
					<c:if test="${self==0}">
						<div style="height: 40px; width: 20px; float: left;">
							<select id="racescore" name="racescore"
								style="height: 20px; font-size: 20px; -webkit-writing-mode: horizontal-tb">
								<option value="8.5"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">8.5</option>
								<option value="8.6"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">8.6</option>
								<option value="8.7"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">8.7</option>
								<option value="8.8"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">8.8</option>
								<option value="8.9"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">8.9</option>
								<option value="9.0"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.0</option>
								<option value="9.1"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.1</option>
								<option value="9.2"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.2</option>
								<option value="9.3"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.3</option>
								<option value="9.4"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.4</option>
								<option value="9.5"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.5</option>
								<option value="9.6"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.6</option>
								<option value="9.7"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.7</option>
								<option value="9.8"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.8</option>
								<option value="9.9"
									style="display: block; padding: 0px 2px 1px; min-height: 1.2em;">9.9</option>
							</select>
						</div>
						<div style="float: left;">
							<a style="color: #f00;"
								href="javascript:scoreracedoc('<c:out value="${raceModelValue.raceid}" />','<c:out value="${documentValue.docid}" />');">
								  </a>
						</div>
					</c:if>
					<br>
				</c:if>
			</div>
		</div>
	</div>
	<input type="hidden" id="islogin" name="islogin"
		value="<c:out value="${login}" />">
	<input type="hidden" id="docid" name="docid"
		value="<c:out value="${documentValue.docid}" />">
	<div id="logindiv"
		style="float: left; display: none; width: 100%; margin: 0px auto;">
		<%@ include file="../userspace/phonelogin.jsp"%>
	</div>
</body>
<script>
	var raceModelJson = <c:out value="${raceModelJson}" escapeXml="false" />;
</script>
<script language="javascript" type="text/javascript"
	src="http://js.users.51.la/17667713.js"></script>
<noscript>
	<a href="http://www.51.la/?17667713" target="_blank"><img
		alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"
		src="http://img.users.51.la/17667713.asp" style="border: none" /></a>
</noscript>
</html>