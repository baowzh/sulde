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
<script type="text/javascript" src="js/sitejs/race.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userlogin.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript" src="js/sitejs/photoalbumlist.js"></script>
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

<body onmousewheel="wheel(event)" onload="setmglconimgWidht();"
	style="background-color: #fff; scroll: none; height: 780px;">
	<div class="rotatesection" style="width: 630px;" id="myDIV">
		<div class="blogcon" id="blogcon" style="position: relative;">
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
			<div class="titlebar" style="text-align: right;">
			   <div class="mglsection" style="width: 180px; padding-right: 15px;">
				<a href="friendlist.do?userid=<c:out value="${user.userid}" />">
						 </a>
				</div>
				
				<div class="mglsection" style="width: 180px; padding-right: 15px;">
					<a href="friendlist.do?userid=<c:out value="${user.userid}" />">     </a>
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
				<c:if test="${self==1}">
					<div class="mglsection" style="width: 180px; padding-right: 15px;">
						<a href="javascript:openaddphotoalbumdialog();"> 
							  </a>
					</div>
				</c:if>
			</div>
			<div class="flt"
				style="width: 100%; margin: 2px; min-height: 185px; height: auto !important;">
				<c:forEach items="${imggroupValues}" var="imgGrpupValue"
					varStatus="status">
					<div class="photoalbum rotate" style="position: relative;">
						<a
							href="javascript:openPhotoList('<c:out value="${imgGrpupValue.imggroupid}"/>')">
							<c:if test="${imgGrpupValue.faceurl!=null}">
								<img
									src="html/photoalbum/<c:out value="${imgGrpupValue.faceurl}"/>">
							</c:if> <c:if test="${imgGrpupValue.faceurl==null}">
								<img src="html/photoalbum/imgface.jpg">
							</c:if>

						</a>
						<div class="m1ln"
							style="text-align: center; width: 180px; margin: 5px; position: absolute; bottom: 0px; background: #000; opacity: 0.6;">
							<a title="<c:out value="${imgGrpupValue.imggroupname}"/>"
								href="javascript:openPhotoList('<c:out value="${imgGrpupValue.imggroupid}"/>')"
								style="color: #fff;"><c:out
									value="${imgGrpupValue.imggroupname}" escapeXml="false" /></a>
						</div>
						<c:if test="${self==1}">
							<div style="position:absolute;bottom:3px;left:6px;">
								<input type="radio" name="selectedradio"
										id="<c:out value="${friendValue.friendid}" />" />
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
			<div style="clear: both"></div>
			<div class="flt" style="width: 100%; margin: 2px; min-height: 40px;">
				<div class="shareBookmark mgldiv"
					style="margin: 10px 360px 0px 0px;">
					<div class="mgldiv">
						<c:if test="${self==1}">
							<a href="javascript:deletephotoalbum();"> </a>
						</c:if>
					</div>
					
				</div>

			</div>
			<!-- 			<div class="flt" style="width: 100%; margin: 2px; min-height: 40px;"> -->
			<!-- 				<div class="shareBookmark mgldiv" -->
			<!-- 					style="margin: 10px 360px 0px 0px;"> -->
			<!-- 					<div class="mgldiv"> -->
			<%-- 						<c:if test="${self==1}"> --%>
			<!-- 							<a href="javascript:openaddphotoalbumdialog();">  -->
			<!-- 								  </a> -->
			<%-- 						</c:if> --%>
			<!-- 					</div> -->
			<!-- 				</div> -->

			<!-- 			</div> -->

			<div style="clear: both"></div>

			<div class="titlebar" style="text-align: right;">
				<div class="mglsection" style="width: 180px; padding-right: 15px;">
					</div>
				<c:if test="${self==1}">
					<div class="mglsection" style="width: 180px; padding-right: 15px;">
						<a href="toadddoc.do">    </a>
					</div>
				</c:if>
					
			</div>
			<div class="flt" style="width: 100%; height: 25px;"></div>
			<div class="flt"
				style="width: 100%; min-height: 300px; height: auto !important;"
				id="doclist">

				<c:forEach items="${docList}" var="documentValue" varStatus="status">
					<div class="cont_box">
						<div class="title mglcontent">
							<a  style="color:#333;text-decoration: none;font-size:25px;" href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}" />"><c:out value="${documentValue.doctitle}" escapeXml="false" /></a>
						</div>
						<div class="shareBookmark mgldiv">
							<div class="mgldiv" style="margin: 0px 20px 20px 20px;">
								 
								<c:out value="${documentValue.chnaname}" />
								<br> <a href="javascript:sharedocument()"> </a>
								
								<c:out value="${documentValue.sharecount}" default="0" />
								  <a href="javascript:markdocument()">  </a> <span
									id="markcount"><c:out value="${documentValue.markcount}"
										default="0" /></span>  <br>   <span
									id="readcount"><c:out value="${documentValue.readcount}"
										default="0" /></span> <br>   
								<c:out value="${documentValue.docRelTimeStr}" />
								<br> <a href="#comment"> </a><span
									id="commentCount"> <c:out
										value="${documentValue.commentCount}" default="0" />
								</span> 
							</div>
						</div>
						<div class="blogarea mglcontent">
							<c:out value="${documentValue.htmlabc}" escapeXml="false" />
							<div class="shareBookmark mgldiv"
								style="margin: 10px 360px 0px 0px;">
								<div class="mgldiv">
									<a
										href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}" />">
										     </a>
								</div>
							</div>
						</div>
					</div>
					<div style="clear: both"></div>
				</c:forEach>
			</div>
			<div style="clear: both"></div>
			<c:if test="${fn:length(docList)!=0&&fn:length(docpageIndexs)!=0}">
				<div class="paginationArea" style="margin: 5px;">
					<div class="pagination" id="docpagelist">
						<a class="first disabled" id="firsta"
							href="javascript:openpage('1','<c:out value="${user.userid}" />',1,1);switchclass('first');">
							<span id="pagefirst" class="spanstyle">&lt;&lt; </span>
						</a> <a class="first disabled" id="previousa"
							href="javascript:openpage('<c:out value="${previousindex}" />','<c:out value="${user.userid}" />',1,1);switchclass('pageprevious');">
							<span id="pageprevious" class="spanstyle">&nbsp;&lt;&nbsp;
						</span>
						</a>
						<c:forEach items="${docpageIndexs}" var="pagingIndex"
							varStatus="status">
							<a class="first disabled" id="doclista"
								href="javascript:openpage('<c:out value="${pagingIndex.pageindex}" />','<c:out value="${user.userid}" />',1,1);switchclass('page<c:out value="${pagingIndex.pageindex}" />');">
								<c:if test="${status.index==0}">
									<span id="page<c:out value="${pagingIndex.pageindex}" />"
										class="curspanstyle">&nbsp;<c:out
											value="${pagingIndex.pageindex}" />&nbsp;
									</span>
								</c:if> <c:if test="${status.index!=0}">
									<span id="page<c:out value="${pagingIndex.pageindex}" />"
										class="spanstyle">&nbsp;<c:out
											value="${pagingIndex.pageindex}" />&nbsp;
									</span>
								</c:if>

							</a>
						</c:forEach>
						<a class="first disabled" id="nexta"
							href="javascript:openpage('2','<c:out value="${user.userid}" />',1,1);switchclass('pagenext');">
							<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp; </span>
						</a> <a class="first disabled" id="lasta"
							href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',1,1);switchclass('pagelast');">
							<span id="pagelast" class="spanstyle">&gt;&gt; </span>
						</a> <input type="hidden" name="pagecount" id="pagecount"
							value="<c:out value="${pageCount}" />" />
					</div>
				</div>
			</c:if>
			<div style="clear: both"></div>
			<c:if test="${fn:length(sharePaingModel.modelList)!=0}">
				<div class="titlebar" style="text-align: right;">
					<div class="mglsection" style="width: 180px; padding-right: 15px;">
						</div>
				</div>
				<div style="clear: both"></div>
				<div class="flt" style="width: 100%; max-height: 520px;"
					id="sharedoclist">
					<c:forEach items="${sharePaingModel.modelList}" var="documentValue"
						varStatus="status">
						<div class="sharenwsl1">
							<div class="m1ln"
								style="width: 10px; height: 10px; float: right; padding-top: 5px;">
								<img src="site/img/qig_1_v.png">
							</div>
							<div class="m1ln" style="width: 480px; float: right;">
								<a
									href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}" />"><c:out
										value="${documentValue.doctitle}" escapeXml="false" /> </a>
							</div>
							<div class="m1ln" style="width: 100px; float: right;">
								<a
									href="gouserindex.do?userid=<c:out value="${documentValue.userid}"/>"><c:out
										value="${documentValue.docauthor}" escapeXml="false" /></a>
							</div>
						</div>
					</c:forEach>
					<div class="paginationArea" style="margin: 5px;">
						<div class="pagination" id="sharedoclist">
							<a class="first disabled" id="firsta"
								href="javascript:openpage('1','<c:out value="${user.userid}" />',2);switchclass('first');">
								<span id="pagefirst" class="spanstyle">&lt;&lt; </span>
							</a> <a class="first disabled" id="previousa"
								href="javascript:openpage('<c:out value="${previousindex}" />','<c:out value="${user.userid}" />',2);switchclass('pageprevious');">
								<span id="pageprevious" class="spanstyle">&nbsp;&lt;&nbsp;
							</span>
							</a>
							<c:forEach items="${sharepageIndexs}" var="pagingIndex"
								varStatus="status">
								<a class="first disabled"
									href="javascript:openpage('<c:out value="${pagingIndex.pageindex}" />','<c:out value="${user.userid}" />',2);switchclass('page<c:out value="${pagingIndex.pageindex}" />');">
									<c:if test="${status.index==0}">
										<span id="page<c:out value="${pagingIndex.pageindex}" />"
											class="cursharespanstyle">&nbsp;<c:out
												value="${pagingIndex.pageindex}" />&nbsp;
										</span>
									</c:if> <c:if test="${status.index!=0}">
										<span id="page<c:out value="${pagingIndex.pageindex}" />"
											class="sharespanstyle">&nbsp;<c:out
												value="${pagingIndex.pageindex}" />&nbsp;
										</span>
									</c:if>
								</a>
							</c:forEach>
							<a class="first disabled" id="nexta"
								href="javascript:openpage('2','<c:out value="${user.userid}" />',2);switchclass('pagenext');">
								<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp; </span>
							</a> <a class="first disabled" id="lasta"
								href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',2);switchclass('pagelast');">
								<span id="pagelast" class="spanstyle">&gt;&gt; </span>
							</a>

						</div>
					</div>
				</div>
			</c:if>
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
	<%@ include file="bloghiddendiv.jsp"%>
	<%@ include file="logindiv.jsp"%>
</body>
<script>
	// 	var raceModelJson = <c:out value="${raceModelJson}" escapeXml="false" />;
</script>
</html>