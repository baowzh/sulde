<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:out value="${documentValue.doctitle}" /></title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="css/blog.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="js\messagebox\jquery.rotmsgbox.css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<link href="site/css/waplist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/sitejs/userdocdetail.js"></script>
<script type="text/javascript" src="js/sitejs/race.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userlogin.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript" src="js/sitejs/photolist.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link rel="stylesheet" type="text/css"
	href="plugins/jquery.jqGrid-4.4.3/css/jquery-ui.css" media="screen" />
<script src="plugins/jquery.jqGrid-4.4.3/js/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="js/messagebox/jquery.rotmsgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<script type="text/javascript" src="ckeditorrot/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function() {
		function setheight() {
			var sidebar = document.getElementById('myDIV');
			sidebar.style.width = document.documentElement.clientHeight - 0
					+ 'px';
		}
		setheight();
		onresize = setheight;
	};
</script>
</head>

<body onmousewheel="wheel(event)"
	style="background-color: #fff; scroll: none; height: 780px;">
	<div class="rotatesection" style="width: 630px;" id="myDIV">
		<div class="blogcon">
			<div class="titlebar">
				<div class="mglsection" style="width: 50px; padding-right: 15px;">
					<a href="tologin.do"></a>
				</div>
				<div class="mglsection" style="width: 90px;">
					<a href="index.do"> </a>
				</div>
				<div class="mglsection" style="width: 90px;">
					<a href="gouserindex.do?userid=<c:out value="${user.userid}" />"> 
						 </a>
				</div>
				<div class="mglsection" style="width: 80px;">
					<a href="registe.do"></a>
				</div>
				<div class="mglsection" style="width: 100px;">
					<a href="gouserindex.do?userid=<c:out value="${loginuserid}" />"> 
						 </a>
				</div>
				<div class="mglsection" style="width: 100px;">
					<a href="toadddoc.do">  </a>
				</div>
			</div>
			<!--  -->
			<div class="flt" style="width: 100%; height: 160px;">
				<div class="persooninfo" style="margin: 6px;">
					<div class="headimg rotate">
						<img src="html/userhead/<c:out value="${user.headurl}" />" />
					</div>
					<div class="m1ln" style="padding-right: 20px;">
						<c:out value="${user.artname}" />
					</div>
				</div>
				<div class="flt"
					style="padding-top: 5px; padding-right: 10px; height: 130px; margin: 0 auto; float: right;">
					<div class="m1ln">
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
					<div class="m1ln">
						 
						<c:out value="${user.age}" default="  " />
					</div>
					<div class="m1ln">
						  
						<c:out value="${user.nowprovincename}" default="  " />
					</div>
					<div class="m1ln">
						 
						<c:out value="${user.regdatestr}" default="  " />
					</div>
					<div class="m1ln">
						 
						<c:out value="${totalVisitCount}" />
					</div>
					<div class="m1ln">
						 
						<c:out value="${currentDateVisitCount}" />
					</div>
					<div class="m1ln">
						    
						<c:out value="${user.logindatestr}" default="" />
					</div>
					<div class="m1ln">
						<a
							href="javascript:writemessage('<c:out value="${user.userid}" />');">
							&nbsp;&nbsp;&nbsp;</a>
						<c:if test="${self==1}">
							<a href="javascript:receivemessage();">(<span
								style="color: #f00;"><c:out value="${messageCount}" /></span>)
							</a>
						</c:if>
					</div>
					<div class="m1ln">
						<c:if test="${self==1}">
							<a href="doedituserinifo.do">  &nbsp;&nbsp;&nbsp; </a>
							<a href="javascript:showpassdialog();">   </a>
						</c:if>
						<c:if test="${self==0}">
							<a
								href="javascript:showuserinfo('<c:out value="${user.userid}" />');">
								 &nbsp;&nbsp;&nbsp; </a>
						</c:if>


						<c:if test="${self==0}">
							<a href="javascript:openaddfrienddl();"> </a>

						</c:if>
					</div>
				</div>
			</div>
			<div style="clear: both"></div>
			<div class="titlebar" style="text-align: right;">
				<div class="mglsection" style="width: 180px; padding-right: 15px;">
					<a href="friendlist.do?userid=<c:out value="${user.userid}" />">
						 :</a>
				</div>
			</div>
			<div class="flt"
				style="width: 100%; min-height: 230px; height: auto !important;">
				<c:forEach items="${fvalues}" var="friendValue" varStatus="status">
					<div class="persooninfo" style="height: 100px; width: 105px;">
						<div class="headimg rotate" style="height: 75px; width: 90px;">
							<a
								href="gouserindex.do?userid=<c:out value="${friendValue.friendid}" />">
								<img
								src="html/userhead/<c:out value="${friendValue.headurl}" />"
								style="width: 75px; height: 75px;">
							</a>
						</div>
						<div class="m1ln" style="margin: 5px; padding-right: 5px;">
							<a
								href="gouserindex.do?userid=<c:out value="${friendValue.friendid}" />">
								<c:out value="${friendValue.friendname}" />
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<div style="clear: both"></div>
			<div class="titlebar" style="text-align: right;">
				<div class="mglsection" style="width: 180px; padding-right: 15px;">
					<a href="photoAlbumList.do?userid=<c:out value="${user.userid}" />">   </a>
				</div>
			</div>
			<div class="flt"
				style="width: 100%; margin: 2px; min-height: 230px; height: auto !important;">
				<c:forEach items="${imgList}" var="imgValue" varStatus="status">
					<div class="photoalbum rotate">
						<a
							href="javaScript:photoDetail('<c:out value="${imgValue.docid}" />')">
							<img src="html/img/<c:out value="${imgValue.docid}" />.jpg" />
						</a>
						<c:if test="${self==1}">
							<input type="checkbox" name="selectedimg"
								id="<c:out value="${imgValue.docid}"/>">
						</c:if>
					</div>
				</c:forEach>
			</div>
			<div style="clear: both"></div>
			<div class="flt" style="width: 100%; margin: 2px; min-height: 40px;">
				<div class="shareBookmark mgldiv"
					style="margin: 10px 260px 0px 0px;">
					<div class="mgldiv">
						<c:if test="${self==1}">
							<a href="javascript:addnewimg();">    </a>
							<a href="javascript:deleteimg();">  </a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="paginationArea" style="margin: 5px;">
				<div class=" pagenav">
					<c:out value="${pagingimgStr}" escapeXml="false" />
					<input type="hidden" name="userid" id="userid"
						value="<c:out value="${user.userid}" />" /> <input type="hidden"
						name="imggroupid" id="imggroupid"
						value="<c:out value="${imggroupid}" />" /> <input type="hidden"
						name="idAndIndexrel" id="idAndIndexrel"
						value="<c:out value="${idAndIndexrel}" />" /> <input
						type="hidden" name="imgid" id="imgid" value="" />

				</div>
			</div>
			<div style="clear: both"></div>
			<c:if test="${fn:length(visitors)!=0}">
				<div class="titlebar" style="text-align: right;">
					<div class="mglsection" style="width: 180px; padding-right: 15px;">
						</div>
				</div>
				<div class="flt" style="width: 100%; max-height: 230px;">
					<c:forEach items="${visitors}" var="visitorValue"
						varStatus="status">
						<div class="persooninfo" style="height: 100px; width: 105px;">
							<div class="headimg rotate" style="height: 75px; width: 90px;">
								<a
									href="gouserindex.do?userid=<c:out value="${visitorValue.visitorid}" />">
									<img
									src="html/userhead/<c:out value="${visitorValue.headurl}" />"
									style="width: 75px; height: 75px;">
								</a>
							</div>
							<div class="m1ln" style="margin: 5px; padding-right: 5px;">
								<a
									href="gouserindex.do?userid=<c:out value="${visitorValue.visitorid}" />">
									<c:out value="${visitorValue.visitorname}" />
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<div style="clear: both"></div>
			<div class="titlebar"
				style="text-align: right; height: 60px; padding-top: 20px;">
				<div class="mglsection" style="width: 100%; padding-right: 15px;">
					  ************** 
					     </div>
				<div class="mglsection rotate">
					<script language="javascript" type="text/javascript"
						src="http://js.users.51.la/17667713.js"></script>
					<noscript>
						<a href="http://www.51.la/?17667713" target="_blank"> <img
							alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"
							src="http://img.users.51.la/17667713.asp" style="border: none;" />
						</a>
					</noscript>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none" id="addimg"
		style="height:270px;width:300px;">
		<div class="content rotatesection"
			style="height: 280px; width: 310px; background: white; padding: 5px; border-radius: 5px;">
			<form action="addimg.do" id="addimgform" method="post"
				class="mglForm" enctype="multipart/form-data" />
			<div class="m1ln" style="padding-right: 80px;">  :</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="text" name="albumname" id="albumname" readonly="true"
					value="<c:out value="${albumValue.imggroupname}" />" />
			</div>

			<div class="m1ln" style="padding-right: 80px;">:</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="text" name="imgcomm" id="imgcomm"></input>
			</div>

			<div class="m1ln" style="padding-right: 80px; width: 210px;">
				   :<input type="checkbox" name="cover"
					id="cover"></input>
			</div>
			<c:if test="${racemodel!=null&&sessionuser.managerflag==1}">
				<div class="m1ln" style="padding-right: 80px; width: 210px;">
					   :<input type="checkbox" name="forrace"
						id="forrace"></input>
				</div>
			</c:if>
			<div class="m1ln" style="padding-right: 80px;">:</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="file" name="img" id="img" style="width: 210px;"></input>
			</div>
			<input type="hidden" name="imggroupid" id="imggroupid"
				value="<c:out value="${albumValue.imggroupid}" />"></input> <input
				type="hidden" name="userid" id="userid"
				value="<c:out value="${user.userid}" />"></input>
			<div class="m1ln" style="text-align: center;">
				<a href="javascript:uploadimg();"></a>
			</div>
			</form>
		</div>
	</div>
	<%@ include file="bloghiddendiv.jsp"%>
	<%@ include file="logindiv.jsp"%>
</body>
<script>
	// 	var raceModelJson = <c:out value="${raceModelJson}" escapeXml="false" />;
</script>
</html>