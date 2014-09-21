<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/listpages.css" type="text/css" rel="stylesheet" />
<link href="img/css/blog.css" type="text/css" rel="stylesheet" />
<link href="img/css/custom.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/userdocdetail.js"></script>
<script type="text/javascript" src="js/sitejs/userhomeindex.js"></script>
<script type="text/javascript"
	src="js/sitejs/emotion/jquery.emoticons.js"></script>
<link href="js/sitejs/emotion/emoticon.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>

<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<style>
.clear {
	clear: both
}
</style>
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<%@ include file="bloghead.jsp"%>
	</div>
	<div class="lmainR">
		<div class="  lcell"
			style="width: 990px; margin-top: 200px; overflow: visible;">
			<div class=" blogbody blogbodyC">
				<div class="blgL blgLC" id="blgL">
					<%@ include file="blogpage.jsp"%>
				</div>
				<div class="blgmain bglMainC" id="blgMain">
					<div style="width: 690px; margin: 0px 10px 10px 10px;">

						<div class="recentAcvtyBox contentArea">
							<div class="mVsheet " id="vs">
								<div class="ScrollToView">
									<div></div>
								</div>
								<h1>
									<!--              -->
									<c:out value="${documentValue.doctitle}" />

								</h1>
								<input type="hidden" id="hiddensharecount"
									value="<c:out value="${documentValue.sharecount}" />">
								<div class="shareBookmark">
									<a href="javascript:sharedocument()"> </a> <span
										id="sharecount"><c:out
											value="${documentValue.sharecount}" default="0" /></span>   <a
										href="javascript:markdocument()">  </a> <span
										id="markcount"><c:out
											value="${documentValue.markcount}" default="0" /></span>  <br />
									  <span id="readcount"><c:out
											value="${documentValue.readcount}" default="0" /></span> <br />
									  
									<c:out value="${documentValue.docRelTimeStr}" />
									<br /> <a href="#comment"> </a><span id="commentCount">
										<c:out value="${documentValue.commentCount}" default="0" />
									</span> 
								</div>
								<!--    -->
								<c:out value="${documentValue.htmlstr}" escapeXml="false" />
								<!--    -->
								<br>
								<!--        -->
								<div class="operator" style="text-align: center;">
									<a href="javascript:sharedocument()"> </a> <a
										href="javascript:markdocument()">  </a>
									<c:if test="${self==1}">
										<a
											href="toupddoc.do?docid=<c:out value="${documentValue.docid}" />">
											   </a>
										<a href="javascript:deldoc(1);">   </a>
										<a href="javascript:deldoc(1);">  </a>
									</c:if>
								</div>
								<!--    -->
								<br>
								<!--留言区域-->
								<div class="clear"></div>
								<nobr>
									<div class="comment"
										style="display: inline; width: 360px; height: 400px;"
										id="commentcontainer">
										<a name="comment"></a>
										<form action="addCommentOnResource.do" name="commentform"
											id="commentform">

											<c:if test="${agentkind==0}">
												<!-- IE浏览器 -->
												<div style="display: block; z-index: 10">
													<textarea name="commentdiv" id="commentdiv"
														style="width: 210px; height: 400px; writing-mode: tb-lr;">								    
								</textarea>
												</div>
											</c:if>

											<textarea name="comment" id="comment" style="display: none">								    
								</textarea>
											<c:if test="${agentkind==1}">
												<!-- 谷歌浏览器 -->

												<div class="flt mVsheet commentEdit" contentEditable="true"
													id="commentdiv" style="display: block"></div>
											</c:if>
											<input type="hidden" name="agentkind" id="agentkind"
												value="<c:out value="${agentkind}" />"> <input
												type="hidden" name="userid" id="userid"> <input
												type="hidden" name="doctype" id="doctype" value="1">
											<input type="hidden" name="docid" id="docid"
												value="<c:out value="${documentValue.docid}" />">
											<div class="commoper" style="display: block;">
												<!-- 												<table -->
												<!-- 													style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; align: center; width: 20px; height: 400px;"> -->
												<!-- 													<tr> -->
												<!-- 														<td style="width: 18px; height: 102px;"> -->
												<a href="JavaScript:void(0)" id="message_face"><img
													src="img/pl_bq.png" /></a> <a
													href="javascript:replaceverifycode();"><img
													src="verifyCodeServlet" id="varifyimg" width="21"
													height="100" /></a>&nbsp;&nbsp;
												<!-- 																</td> -->
												<!-- 														<td style="width: 18px; height: 60px;"> -->
												<c:if test="${agentkind==1}">
													<!-- 谷歌浏览器 -->
													<input type="text" name="validcode" id="validcode"
														style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; height: 15px; width: 60px; -webkit-transform: rotate(90deg); -webkit-transform-origin: 10px 10px;" />
												</c:if>
												<c:if test="${agentkind==0}">
													<!-- 谷歌浏览器 -->
													<input type="text" name="validcode" id="validcode"
														style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; height: 60px; width: 18px;" />
												</c:if>
												&nbsp;&nbsp;
												<!-- 															</td> -->
												<!-- 														<td style="width: 18px; height: 120px;"> -->

												<!-- 																</td> -->
												<!-- 														<td style="width: 18px; height: 120px;"> -->

												<a href="javascript:addcomment(1,0);" style="height: 80px;">
													&nbsp;  </a>&nbsp; <a
													href="javascript:addcomment(1,1);"> </a>
												<!-- 															</td> -->

												<!-- 													</tr> -->
												<!-- 												</table> -->
											</div>

										</form>
									</div>
								</nobr>

								<br>
								<div class="clear"></div>
								<!-- 加载留言区域 -->
								<div id="commentlist" style="padding-left: 10px; z-index: 10;">
									<div class="mnlist">   
										</div>
									<br>
									<c:forEach items="${comments}" var="messageValue"
										varStatus="status">
										<div class="postSheet" style="z-index: 10">
											<div class="posterInf" style="z-index: 10">
												<div class="avtThumb flt" style="z-index: 10">
													<img
														src="getsmheadimge.do?userid=<c:out value="${messageValue.messagesenderid}" />"
														width="300" height="400" />
												</div>
												<div class="inf flt" style="z-index: 10">
													<div class="row">
														<a
															href="gouserindex.do?userid=<c:out value="${messageValue.messagesenderid}" />"><c:out
																value="${messageValue.messagesendername}" /> </a><br /> <br />
														<c:out value="${messageValue.sendtimestr}" />
													</div>
													<div class="row"></div>
												</div>
											</div>
											<p>
												<c:if test="${messageValue.hidden==0}">
													<c:out value="${messageValue.contenthtml}"
														escapeXml="false" />
												</c:if>
												<c:if test="${messageValue.hidden==1}">
												        
												</c:if>

											</p>
											<c:if test="${self==1}">
												<p>
													<a
														href="javascript:delcomment('<c:out value="${messageValue.messageid}" />');"></a><a
														href="javascript:writemess('<c:out value="${messageValue.messagesenderid}" />','
									<c:out value="${messageValue.messagesendername}" />');">&nbsp;&nbsp;&nbsp;&nbsp;
														</a>
												</p>
											</c:if>
										</div>
									</c:forEach>
								</div>
								<!-- 加载留言区域 -->
							</div>
							<!-- 条横位置 -->

							<!-- 调整位置 -->

						</div>
						<div class="flt txtBlogList">
							<!-- 进度条 -->
							<div class="loadingbox" style="display: none;"></div>
							<!--  进度条-->
							<div class="artclList" id="artclList">
								<c:forEach items="${docList}" var="documentValue"
									varStatus="status">
									<div class="m1ln">
										<a><img src="img/dot.gif"></a><a
											href="getuserdocdetail.do?docid=<c:out value="${documentValue.docid}" />"><c:out
												value="${documentValue.doctitle}" escapeXml="false" /> </a>
									</div>
								</c:forEach>
							</div>
							<div class="paginationArea">
								<div class="pagination">
									<c:forEach items="${docpageIndexs}" var="pagingIndex"
										varStatus="status">
										<a class="first disabled"
											href="javascript:openpage('<c:out value="${pagingIndex.pageindex}" />','<c:out value="${user.userid}" />',1,true);switchclass('page<c:out value="${pagingIndex.pageindex}" />');">
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
								</div>
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
	<div class="lmainR ofh" style="text-align: center;">
		<div class="tailCard">
			<div class="msheet" style="height: 100px; width: 800px;">
				<%@ include file="../website/tail.jsp"%></div>
		</div>
		<div class="cbt"></div>
	</div>
	<!-- 隐藏的div -->
	<%@ include file="bloghiddendiv.jsp"%>
	<!-- 隐藏的div -->
	<!-- JiaThis Button BEGIN -->
	<div class="jiathis_share_slide jiathis_share_32x32"
		id="jiathis_share_slide">
		<div class="jiathis_share_slide_top" id="jiathis_share_title"></div>
		<div class="jiathis_share_slide_inner">
			<div class="jiathis_style_32x32">
				<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tsina"></a>
				<a class="jiathis_button_tqq"></a> <a class="jiathis_button_weixin"></a>
				<a class="jiathis_button_renren"></a> <a
					href="http://www.jiathis.com/share"
					class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
				<script type="text/javascript">
					var jiathis_config = {
						slide : {
							divid : 'jiathis_main',//设定分享按钮的位置在哪个DIV的边缘，一般是主体内容的外层DIV框架ID,
							pos : 'right'
						}
					};
				</script>
				<script type="text/javascript"
					src="http://v3.jiathis.com/code/jia.js?uid=1408369689443257"
					charset="utf-8"></script>
				<script type="text/javascript"
					src="http://v3.jiathis.com/code/jiathis_slide.js" charset="utf-8"></script>
			</div>
		</div>
	</div>
	<!-- JiaThis Button END -->
	<!-- UJian Button BEGIN -->
	<script type="text/javascript"
		src="http://v1.ujian.cc/code/ujian.js?type=slide"></script>
	<!-- UJian Button END -->
</body>
</html>