<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="t" uri="/css-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:out value="${user.artname}" /></title>
<t:font_css type="jquery,easyui,tools"></t:font_css>
<link href="site/css/main.css" rel="stylesheet" type="text/css" />
<link href="site/css/index.css" rel="stylesheet" type="text/css" />
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/doccheck.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/custom.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="img/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript" src="js/sitejs/userlogin.js"></script>
<script type="text/javascript" src="js/sitejs/photoalbumlist.js"></script>
<script type="text/javascript" src="js/sitejs/changevalidcode.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
</head>
<body>
	<form method="post" id="alinkform">
		<%@ include file="bloghead.jsp"%>
		<div class="lmainR">
			<div class="  lcell"
				style="width: 1000px; margin-top: 196px; overflow: visible;">
				<div class=" blogbody blogbodyC">
					<!--   通过引用页面代替-->
					<div class="blgL blgLC" id="blgL">
						<%@ include file="blogpage.jsp"%>
					</div>
					<!--  	-->
					<div class="blgmain bglMainC" id="blgMain">
						<div style="width: 690px; margin: 0px 10px 10px 10px;">
							<div class="loadingbox" style="display: none"></div>
							<div class="flt txtBlogList" style="display: block;"
								id="doclistidv">
								<c:if test="${self==1}">
									<div class="addNewArtcl">
										<div class="m1ln borderOuter"
											style="height: 90px; text-align: center;">
											<a href="toadddoc.do">  </a>
										</div>
									</div>
								</c:if>							
								<div class="channelname">
									<c:if test="${currentchannel!=null}">
										<a href="#"> <c:out value="${currentchannel.chnlname}" />
										</a>
									</c:if>
									<c:if test="${currentchannel==null}">
										<a href="#">   </a>										
									</c:if>

								</div>
								<div class="artclList" id="artclList" style="height: 480px;">
									<c:forEach items="${docList}" var="documentValue"
										varStatus="status">
										<div class="m1ln">
											<a><img src="img/qig_1_v.png"></a>&nbsp;&nbsp; <a
												href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}" />"><c:out
													value="${documentValue.doctitle}" escapeXml="false" /> </a>

											&nbsp;
										</div>
									</c:forEach>
								</div>
								<div class="cbt"></div>
								<c:if
									test="${fn:length(docList)!=0&&fn:length(docpageIndexs)!=0}">
									<div class="paginationArea">
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
												<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp;
											</span>
											</a> <a class="first disabled" id="lasta"
												href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',1,1);switchclass('pagelast');">
												<span id="pagelast" class="spanstyle">&gt;&gt; </span>
											</a> <input type="hidden" name="pagecount" id="pagecount"
												value="<c:out value="${pageCount}" />" />
										</div>
									</div>
								</c:if>
							</div>
							<hr>
								<!-- 分享作品 -->
								<c:if test="${fn:length(sharePaingModel.modelList)!=0}">
									<div class="flt txtBlogList" id="sharedoclistidv">
										<c:if test="${self==1}">
											<div class="addNewArtcl"></div>
										</c:if>
										<div class="channelname">
											<a href="#"> &nbsp; &nbsp; </a>
										</div>
										<div class="artclList" id="shareartclList"
											style="height: 480px;">
											<c:forEach items="${sharePaingModel.modelList}"
												var="documentValue" varStatus="status">
												<div class="sharenwsl1">
													<div class="title" style="height: 380px;">
														<a
															href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}" />"><c:out
																value="${documentValue.doctitle}" escapeXml="false" />
														</a>
													</div>
													<div class="author">
														<a
															href="gouserindex.do?userid=<c:out value="${documentValue.userid}"/>"><c:out
																value="${documentValue.docauthor}" escapeXml="false" /></a>
													</div>
												</div>
											</c:forEach>
										</div>
										<div class="cbt"></div>
										<div class="paginationArea">
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
													<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp;
												</span>
												</a> <a class="first disabled" id="lasta"
													href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',2);switchclass('pagelast');">
													<span id="pagelast" class="spanstyle">&gt;&gt; </span>
												</a>

											</div>
										</div>
									</div>
									<hr>
								</c:if>

								<div class="flt glryBox">
									<div class="m0a" style="width: 690px; margin: 0px;">
										<c:if test="${self==1}">
											<div class="addNewAlbum">
												<div class="m1ln borderOuter"
													style="height: 100px; text-align: center;">
													<a href="javascript:openaddphotoalbumdialog();">
														 </a>
												</div>

											</div>

										</c:if>
										<div class="folder photoAlbumC">
											<div style="width: 80px; margin: 4em 2em;">
												<div class=" msheet">
													<a href="#">  <br /><br /> <br />  <br><br /></a>
												</div>
											</div>
										</div>

										<c:forEach items="${imggroupValues}" var="imgGrpupValue"
											varStatus="status">
											<div class="folder" style="width: 162px; height: 180px;">
												<a
													href="javascript:openPhotoList('<c:out value="${imgGrpupValue.imggroupid}"/>')">
													<c:if test="${imgGrpupValue.faceurl!=null}">
														<img style="width: 132px; height: 150px;"
															src="html/photoalbum/<c:out value="${imgGrpupValue.faceurl}"/>">
													</c:if> <c:if test="${imgGrpupValue.faceurl==null}">
														<img style="width: 132px; height: 150px;"
															src="html/photoalbum/imgface.jpg">
													</c:if>
												</a>
												<div class="m1ln" style="text-align: center; height: 145px;">
													<a title="<c:out value="${imgGrpupValue.imggroupname}"/>"
														href="javascript:openPhotoList('<c:out value="${imgGrpupValue.imggroupid}"/>')"><c:out
															value="${imgGrpupValue.imggroupname}" escapeXml="false" /></a>
												</div>
												<div class="time">
													<c:out value="${imgGrpupValue.createdtimestr}" />
													<!-- 											2014-03-08 -->
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<hr>
									<div class="flt txtBlogList" id="sharedoclistidv">
										<c:if test="${self==1}">
											<div class="addNewArtcl">
												<div class="m1ln borderOuter"
													style="height: 90px; text-align: center;">
													<a href="addvote.do">  </a>
												</div>
											</div>
										</c:if>
										<div class="channelname">
											<a href="#"> &nbsp; &nbsp;</a>
										</div>
										<div class="artclList" id="shareartclList"
											style="height: 480px;">
											<c:forEach items="${votelist}" var="documentValue"
												varStatus="status">
												<div class="sharenwsl1">
													<div class="title" style="height: 380px;">
														<a
															href="getuserdocdetail.do?docid=<c:out value="${documentValue.voteid}" />"><c:out
																value="${documentValue.topic}" escapeXml="false" /> </a>
													</div>
												</div>
											</c:forEach>
										</div>
										<div class="cbt"></div>
										<div class="paginationArea">
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
													<span id="pagenext" class="spanstyle">&nbsp;&gt;&nbsp;
												</span>
												</a> <a class="first disabled" id="lasta"
													href="javascript:openpage('<c:out value="${pageCount}" />','<c:out value="${user.userid}" />',2);switchclass('pagelast');">
													<span id="pagelast" class="spanstyle">&gt;&gt; </span>
												</a>

											</div>
										</div>
									</div>
									<hr>
										<div class="recentAcvtyBox  ">
											<div class="mVsheet " id="vs" style="float: left;">
												<div class="ScrollToView">
													<div></div>
												</div>
												<c:forEach items="${blogNews}" var="friendNews"
													varStatus="status">
													<c:if test="${self==1}">
														<div class="flt">
															<div class="avatar" style="width: 75px; height: 75px">
																<img
																	src="html/userhead/<c:out value="${friendNews.headurl}" />"
																	style="width: 75px; height: 75px" />
															</div>
															<a
																href="gouserindex.do?userid=<c:out value="${friendNews.userid}" />"><c:out
																	value="${friendNews.artname}" /></a>
															<c:choose>
																<c:when test="${friendNews.doctype==1}">
				                     
			                   </c:when>
																<c:when test="${friendNews.doctype==1}">
				                    
			                   </c:when>
																<c:otherwise>
				                     
			                    </c:otherwise>
															</c:choose>
															<a
																href="getuserdocdetail.do?docid=<c:out value="${friendNews.docid}" />">
																<c:out value="${friendNews.doctitle}" escapeXml="false" />
																
															</a>   
															<p class="newsAbs">
																<c:out value="${friendNews.docabstract}"
																	default="            " />
															</p>
														</div>
													</c:if>
													<c:if test="${self==0}">
														<a
															href="getuserdocdetail.do?docid=<c:out value="${friendNews.docid}" />"><c:out
																value="${friendNews.docauthor}" /> </a>   <a
															href="getuserdocdetail.do?docid=<c:out value="${friendNews.docid}" />">
															<c:out value="${friendNews.doctitle}" escapeXml="false" />
															 
														</a>    
							<p class="newsAbs">
															<c:out value="${friendNews.docabstract}"
																default="            "
																escapeXml="false" />
														</p>
													</c:if>
												</c:forEach>
											</div>
										</div>
										<div style="height: 10px; width: 100%; float: left"></div>
										<div class="cbt"></div>
						</div>
					</div>
					<div class="cbt"></div>
				</div>
			</div>
		</div>
		<input type="hidden" name="imggroupid" id="imggroupid"></input> <input
			type="hidden" name="userid" id="userid"
			value="<c:out value="${user.userid}" />"></input>
		<div class="lmainR ofh" style="text-align: center;">
			<%@ include file="../website/tail.jsp"%>
		</div>
	</form>
	<div id="addphotoalbum"
		style="width: 270px; height: 320px; display: none;">
		<div class="content"
			style="width: 270px; height: 300px; background: white; padding: 5px; border-radius: 5px;">
			<form action="addimggroup.do" id="addphotoalbumform" class="mglForm"
				method="post" enctype="multipart/form-data">
				<div class="label" style="text-align: center;"> 
					:</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="text" name="imggroupname" id="imggroupname"
						style="-webkit-transform-origin: 10px 20px;" /> <input
						type="hidden" name="userid" id="userid"
						value="<c:out value="${user.userid}" />"></input>
				</div>

				<div class="label" style="text-align: center;">:</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="text" name="comm" id="comm"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="label" style="text-align: center;"> 
					:</div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input type="file" name="imgurl" id="imgurl"
						style="-webkit-transform-origin: 10px 20px;" />
				</div>
				<div class="mnlist" style="height: 270px;"></div>
				<div class="mnlist"
					style="width: 24px; height: 200px; text-align: center;">
					<a href="javascript:addphotoalbum();"></a>

				</div>
			</form>
		</div>
	</div>
	<!-- 隐藏的div -->
	<%@ include file="bloghiddendiv.jsp"%>
	<div class="content" id="updpassdiv"
		style="padding-left: 8px; display: none; background: white; padding: 5px; border-radius: 5px;">
		<form class="mglForm" action="#" id="loginform" method="post">
			<c:if test="${maillogin==0}">
				<div class="label"> </div>
				<div class="inputHolder" style="width: 32px; height: 270px;">
					<input name="oldpassword" id="oldpassword" type="password"
						style="-webkit-transform-origin: 10px 20px;">
				</div>
			</c:if>
			<div class="label">   </div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input name="password" id="password" type="password"
					style="-webkit-transform-origin: 10px 20px;">
			</div>
			<div class="label">
				  <a href="javascript:replaceverifycode();"></a> :
			</div>
			<div class="label">
				<img src="verifyCodeServlet" id="varifyimg" width="18" height="100">
			</div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input name="validcode" id="varifycode"
					style="-webkit-transform-origin: 10px 20px;">
			</div>
			<div class="mnlist" style="text-align: center; height: 210px;">
				<a href="javascript:modifypass();">  </a>
			</div>
			<input type="hidden" name="maillogin" id="maillogin"
				value="<c:out value="${maillogin}" />">
		</form>
	</div>
	<%@ include file="logindiv.jsp"%>
	
</body>
</html>