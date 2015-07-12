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
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<link href="site/css/waplist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/sitejs/userdocdetail.js"></script>
<script type="text/javascript" src="js/sitejs/race.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userlogin.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link rel="stylesheet" type="text/css"
	href="plugins/jquery.jqGrid-4.4.3/css/jquery-ui.css" media="screen" />
<script src="plugins/jquery.jqGrid-4.4.3/js/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<script type="text/javascript" src="ckeditorrot/ckeditor.js"></script>
<script src="js/sitejs/galleria-1.4.2.min.js"></script>
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
					<c:out value="${documentValue.doctitle}" />
				</div>
			</div>
			<div class="flt rerotatesection"
				style="width: 100%; min-height: 230px;">
				<div id="galleria" style="height: 500px; width: 410px;">

					<c:forEach items="${imgs}" var="imgValue" varStatus="status">
						<a href="html/img/<c:out value="${imgValue.imgid}"/>.jpg"> <img
							src="html/img/<c:out value="${imgValue.imgid}"/>.jpg" />
						</a>
					</c:forEach>
					<input type="hidden" name="imgsharecount" id="imgsharecount"
						value="11" />
				</div>
			</div>
			<c:forEach items="${imgs}" var="imgValue" varStatus="status">

				<input type="hidden" name="imgtitle" id="imgtitle"
					value="<c:out value="${imgValue.imgdesc}"/>">
				<input type="hidden" name="imgsharecount"
					id="imgsharecount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.sharecount}"/>">
				<input type="hidden" name="imgmarkcount"
					id="imgmarkcount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.markcount}"/>">
				<input type="hidden" name="imgreadcount"
					id="imgreadcount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.readcount}"/>">
				<input type="hidden" name="imgcommentCount"
					id="imgcommentCount<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.commcount}"/>">
				<input type="hidden" name="cimgid"
					id="cimgid<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.imgid}"/>">
				<input type="hidden" name="crtime"
					id="crtime<c:out value="${status.index}"/>"
					value="<c:out value="${imgValue.crtimestr}"/>">
			</c:forEach>
			<div class="titlebar" style="text-align: right;">
				<div class="mglsection" style="width: 180px; padding-right: 15px;">
					 </div>
			</div>
			<div class="flt"
				style="width: 100%; min-height: 230px; height: auto !important;">
				<c:forEach items="${visitors}" var="visitorValue" varStatus="status">
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
			<div class="titlebar" style="text-align: right;">
				<div class="mglsection" style="width: 180px; padding-right: 15px;">
					  </div>
			</div>
			<c:if test="${fn:length(comments)!=0}">
				<div style="clear: both"></div>
				<div class="flt"
					style="width: 98%; min-height: 230px; height: auto !important;">
					<c:forEach items="${comments}" var="messageValue"
						varStatus="status">
						<div class="postSheet" style="width: 600px;">
							<div class="posterInf" style="width: 590px;">
								<div class="avtThumb flt" style="float: right;">
									<img
										src="html/userhead/<c:out value="${messageValue.messagesenderurl}" />"
										width="30" height="40" />
								</div>
								<div class="inf flt" style="float: right;">
									<div class="m1ln" style="color: #fff;">
										<a
											href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"
											style="color: #fff;"><c:out
												value="${messageValue.messagesendername}" /> </a>
									</div>
									<div class="m1ln" style="color: #fff;">

										<c:out value="${messageValue.sendtimestr}" />
									</div>
								</div>
							</div>
							<p>
								<c:if test="${messageValue.hidden==0}">
									<div class="blogarea mglcontent"
										style="line-height: 25px; border-radius: 10px 10px 10px 10px;">
										<c:out value="${messageValue.contenthtml}" escapeXml="false" />
									</div>
								</c:if>
								<c:if test="${messageValue.hidden==1}">
									<div class="blogarea mglcontent"
										style="line-height: 25px; border-radius: 10px 10px 10px 10px;">
										      </div>
								</c:if>
							</p>
							<c:if test="${messageValue.showdel==1}">
								<div class="m1ln" style="color: #fff; margin-right: 210px;">

									<a
										href="javascript:delcomment('<c:out value="${messageValue.messageid}" />');"></a><a
										href="javascript:writemess('<c:out value="${messageValue.messagesenderid}" />','
									<c:out value="${messageValue.messagesendername}" />');">&nbsp;&nbsp;&nbsp;&nbsp;
										</a>
								</div>

							</c:if>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<div style="clear: both"></div>
			<form action="addCommentOnResource.do" name="commentform"
				id="commentform">
				<div class="comment"
					style="display: inline; height: 260px; padding: 20px; -webkit-transform-origin: right top; -webkit-transform: translate(-100%, 0px) rotate(270deg);"
					id="commentcontainer">
					<a name="comment"></a>
					<textarea id="editor1" name="commentdiv" class="ckeditor"
						style="height: 80px; width: 100px;"></textarea>
					<input type="hidden" name="agentkind" id="agentkind"
						value="<c:out value="${agentkind}" />"> <input
						type="hidden" name="userid" id="userid"> <input
						type="hidden" name="doctype" id="doctype" value="1"> <input
						type="hidden" name="docid" id="docid"
						value="<c:out value="${documentValue.docid}" />">
				</div>
				<div style="display: block; float: right; height: 50px;">
					<div class="commoper" style="height: 40px; width: 100%">
						<div class="m1ln"
							style="height: 50px; width: 240px; padding-top: 10px; float: right;">
							<a href="JavaScript:void(0)" id="message_face"
								style="display: block; float: right;"> <img
								src="img/pl_bq.png" />
							</a> 
							<!--  <a href="javascript:replaceverifycode();"
								style="display: block; float: right; width: 80px; height: 30px;">
								<img src="verifyCodeServlet" id="varifyimg" />
							</a>&nbsp;&nbsp;
							
							<c:if test="${agentkind==1}">
								<input type="text" name="validcode" id="validcode"
									style="width: 100px; height: 20px;" />

							</c:if>
							<c:if test="${agentkind==0}">
								<input type="text" name="validcode" id="validcode"
									style="width: 100px; height: 20px;" />
							</c:if>
							-->
						</div>
						<div class="m1ln"
							style="height: 40px; width: 150px; padding-top: 10px; float: right;">
							<a href="javascript:addcomment(1,0);" style="width: 80px;">
								&nbsp;  </a>&nbsp; <a href="javascript:addcomment(1,1);">
							</a>
						</div>
						&nbsp;&nbsp;
					</div>
				</div>
			</form>
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
	<%@ include file="bloghiddendiv.jsp"%>
	<%@ include file="logindiv.jsp"%>
</body>
<script>
	// Load the classic theme
	Galleria.loadTheme('js/sitejs/galleria.classic.js');
	// Initialize Galleria
	Galleria.run('#galleria');
</script>
</html>