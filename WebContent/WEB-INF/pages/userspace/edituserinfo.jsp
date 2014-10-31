<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<script type="text/javascript" src="js/sitejs/edituserinfo.js"></script>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<link href="img/css/selectpanel.css" type="text/css" rel="stylesheet" />
<title>  </title>
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="982" />
	</div>
	<div class="lmainR ofh" style="text-align: center; height: 510px;">
		<form action="edituserinfo.do" id="userinfoform" method="post"
			enctype="multipart/form-data">
			<div class="lmainR">
				<div class="mnlist" style="height: 450px;">   
					          </div>
				<div class="mnlist" style="height: 450px;">   
					        </div>
				<div class="mnlist" style="height: 450px;"> 
					      </div>
				<div class="mnlist" style="height: 450px;">   
					       </div>
				<div class="mnlist" style="height: 450px;"> 
					  </div>

				<!-- 				<hr> -->
				<div class="  lcell" style="width: 890px; height: 510px;">
					<div class="lcell userinfotab" style="width: 890px; height: 100%;">
						<table class="m1ln h100">

							<tr>
								<td>
									<div class="m1ln h100">  :</div>
								</td>
								<td>
									<div class="mfl">
										<input type="text" name="bolgname" id="bolgname"
											value="<c:out value="${userinfo.bolgname}"/>" />
									</div>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100">:</div>
								</td>
								<td>
									<div class="mfl">
										<input type="text" name="firstname" id="firstname"
											value="<c:out value="${userinfo.firstname}"/>" />
									</div>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td>
									<div class="mfl">
										<input type="text" name="artname" id="artname"
											value="<c:out value="${userinfo.artname}"/>" />
									</div>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td>
									<div class="mfl">
										<input type="text" name="birthday" id="birthday"
											value="<c:out value="${userinfo.birthday}"/>" />
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td>
									<div class="mfl">
										<c:choose>
											<c:when test="${userinfo.sex==1}">
												<input type="radio" name="sex" checked="true" id="sex"></input>
												<input type="radio" name="sex" id="sex"></input>
												<input type="radio" name="sex" id="sex"></input>
											</c:when>
											<c:when test="${userinfo.sex==0}">
												<input type="radio" name="sex id="sex"></input>
												<input type="radio" name="sex" checked="true" id="sex"></input>
												<input type="radio" name="sex" id="sex"></input>
											</c:when>
											<c:otherwise>
												<input type="radio" name="sex" id="sex"></input>
												<input type="radio" name="sex" id="sex"></input>
												<input type="radio" name="sex" checked="true" id="sex"></input>
											</c:otherwise>
										</c:choose>
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td>
									<div class="mfl">
										<input type="text" name="unit" id="unit"
											value="<c:out value="${userinfo.unit}"/>" />
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td style="height: 90px">
									<!-- 									<div class="preselpanel" style="z-index: 8;"> -->
									<!-- 										<div class="changecity" style="width: 24px;"> -->
									<div class="mfl">
										<a id="provincename"
											href="javascript:showselpanel(true,'provinces')"> <c:out
												value="${userinfo.provincename}" default=" " />
										</a> <input type="hidden" id="province" name="province" value="" />


										<!-- 										</div> -->

										<!-- 										<div id="selprovince"> -->

										<!-- 										</div> -->



										<!-- 
										<select name="province" id="province"
											onchange="javascript:loadChildDistrict('province',1);"
											style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;" />
										<c:forEach items="${districts}" var="districtValue"
											varStatus="status">
											<c:if test="${districtValue.districtcode==userinfo.province}">
												<option
													value="<c:out value="${districtValue.districtcode}"/>"
													selected="selected"><c:out
														value="${districtValue.districtname}" /></option>
											</c:if>
											<c:if test="${districtValue.districtcode!=userinfo.province}">
												<option
													value="<c:out value="${districtValue.districtcode}" />"><c:out
														value="${districtValue.districtname}" /></option>
											</c:if>


										</c:forEach>

										</select>
										 -->
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<!-- 										<div id="selhsien"> -->
										<a id="hsienname"
											href="javascript:showselpanel(true,'hsiens')"> <c:out
												value="${userinfo.hsienname}" default=" " /></a>
										<input type="hidden" id="hsien" name="hsien" value="" />
										<!-- 										</div> -->


										<!--  
										<select
											name="hsien" id="hsien"
											style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">

											<c:forEach items="${hsiens}" var="districtValue"
												varStatus="status">
												<c:if test="${districtValue.districtcode==userinfo.hsien}">
													<option
														value="<c:out value="${districtValue.districtcode}"/>"
														selected="selected"><c:out
															value="${districtValue.districtname}" /></option>
												</c:if>
												<c:if
													test="${districtValue.districtcode!=userinfo.province}">
													<option
														value="<c:out value="${districtValue.districtcode}" />"><c:out
															value="${districtValue.districtname}" /></option>
												</c:if>
											</c:forEach>

										</select>
										-->
										<!-- 									</div> -->
									</div>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100">   </div>
								</td>
								<td>
									<div class="mfl">
										<a id="nowprovincename"
											href="javascript:showselpanel(true,'nowprovinces')"> <c:out
												value="${userinfo.nowprovincename}"
												default=" " />
										</a> <input type="hidden" id="nowprovince" name="nowprovince"
											value="" /> <input type="hidden" id="nowprovince"
											name="nowprovince" value="" />
										<!-- 
										<select name="nowprovince" id="nowprovince"
											onchange="javascript:loadChildDistrict('nowprovince',2);"
											style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
											<c:forEach items="${districts}" var="districtValue"
												varStatus="status">
												<option
													value="<c:out value="${districtValue.districtcode}"/>"><c:out
														value="${districtValue.districtname}" /></option>
											</c:forEach>
										</select>
										 -->
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
											id="nowhsienname"
											href="javascript:showselpanel(true,'nowhsiens')"><c:out
												value="${userinfo.nowhsienname}" default=" " /></a>
										<input type="hidden" id="nowhsien" name="nowhsien" value="" />
										<!-- 
                                        <select
											name="nowhsien" id="nowhsien"
											style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;">
											<c:forEach items="${nowhsien}" var="districtValue"
												varStatus="status">
												<c:if test="${districtValue.districtcode==userinfo.hsien}">
													<option
														value="<c:out value="${districtValue.districtcode}"/>"
														selected="selected"><c:out
															value="${districtValue.districtname}" /></option>
												</c:if>
												<c:if
													test="${districtValue.districtcode!=userinfo.province}">
													<option
														value="<c:out value="${districtValue.districtcode}" />"><c:out
															value="${districtValue.districtname}" /></option>
												</c:if>
											</c:forEach>

										</select>
										 -->
									</div>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="phone" id="phone"
											value="<c:out value="${userinfo.phone}"/>"></input>
									</div>
								</td>

							</tr>
							<tr>
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<a id="professioncodename"
											href="javascript:showselpanel(true,'professions')"><c:out
												value="${userinfo.professionname}" default=" " /></a>
										<input type="hidden" id="professioncode" name="professioncode"
											value="" />
										<!-- 
										<select name="professioncode" id="professioncode"
											onchange="javascript:loadChildDistrict('province',1);"
											style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;" />
										<c:forEach items="${professions}" var="professionValue"
											varStatus="status">
											<c:if
												test="${professionValue.professioncode==userinfo.professioncode}">
												<option
													value="<c:out value="${professionValue.professioncode}"/>"
													selected="selected"><c:out
														value="${professionValue.professionname}" /></option>
											</c:if>
											<c:if
												test="${professionValue.professioncode!=userinfo.professioncode}">
												<option
													value="<c:out value="${professionValue.professioncode}" />"><c:out
														value="${professionValue.professionname}" /></option>
											</c:if>
										</c:forEach>
										</select>
										 -->
									</div>
								</td>

							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<!-- 										<input type="text" name="blogclass" id="blogclass" -->
										<%-- 											value="<c:out value="${userinfo.blogclass}"/>"></input> --%>
										<c:choose>
											<c:when test="${userinfo.blogclass==1}">
												<input type="radio" name="blogclass" checked="true"
													id="blogclass"></input>
												<input type="radio" name="blogclass" id="blogclass">
													</input>
												<input type="radio" name="blogclass" id="blogclass"></input>
											</c:when>
											<c:when test="${userinfo.blogclass==0}">
												<input type="radio" name="blogclass id="blogclass"></input>
												<input type="radio" name="blogclass" checked="true"
													id="blogclass"></input>
												<input type="radio" name="blogclass" id="blogclass"></input>
											</c:when>
											<c:otherwise>
												<input type="radio" name="blogclass" id="blogclass"></input>
												<input type="radio" name="blogclass" id="blogclass"></input>
												<input type="radio" name="blogclass" checked="true"
													id="blogclass"></input>
											</c:otherwise>
										</c:choose>
									</div>
								</td>

							</tr>
							<tr>
								<td>
									<div class="m1ln h100">  (QQ) </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="qq" id="qq"
											value="<c:out value="${userinfo.qq}"/>"></input>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> (Email) </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="email" id="email"
											value="<c:out value="${userinfo.email}"/>"></input>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="textArea" name="hope" id="hope"
											onchange="onChange('',this,120)"
											value="<c:out value="${userinfo.hope}"/>"></input>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="belief" id="belief"
											value="<c:out value="${userinfo.belief}"/>"
											onchange="onChange('',this,120)"></input>
									</div>
								</td>
							</tr>

							<tr style="width: 50px">
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="idol" id="idol"
											onchange="onChange(' ',this,120)"
											value="<c:out value="${userinfo.idol}"/>"></input>
									</div>
								</td>
							</tr>

							<tr style="width: 50px">
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="maxim" id="maxim"
											onchange="onChange(' ',this,120)"
											value="<c:out value="${userinfo.maxim}"/>"></input>
									</div>
								</td>
							</tr>
							<tr style="width: 50px">
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="music" id="music"
											onchange="onChange(' ',this,120)"
											value="<c:out value="${userinfo.music}"/>"></input>
									</div>
								</td>
							</tr>
							<tr style="width: 50px">
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td>
									<div class="mfl">
										<input type="text" name="book" id="book"
											onchange="onChange(' ',this,120)"
											value="<c:out value="${userinfo.book}"/>"></input>
									</div>
								</td>
							</tr>
							<tr style="width: 50px">
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td colspan="3">
									<div class="mfl">
										<input type="text" name="singer" id="singer"
											onchange="onChange(' ',this,120)"
											value="<c:out value="${userinfo.singer}"/>"></input>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100">  </div>
								</td>
								<td align="center">
									<div class="mfl">
										<img src="verifyCodeServlet" id="varifyimg" width="21"><input
											type="text" name="varifycode" id="varifycode"></input>
									</div>
								</td>
								<td colspan="2" valgin="top"></td>
							</tr>
							<tr>
								<td>
									<div class="m1ln h100"> </div>
								</td>
								<td>
									<div class="mfl">
										<input type="file" name="img" id="img"></input>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center" valign="middle">
									<div class="mnlist" style="text-align: center;">
										<a href="javascript:dosubmit();"></a>
									</div>

								</td>

							</tr>
						</table>
					</div>
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
				<div class="mnlist" style="text-indent: 0px; height: 98px;">
					<a
						onclick="javascript:changeSel('<c:out value="${districtValue.districtcode}"/>','<c:out value="${districtValue.districtname}"/>','province','provincename','provinces');loadChildDistrict('province',1);"
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
			<c:forEach items="${hsiens}" var="districtValue" varStatus="status">
				<div class="mnlist" style="text-indent: 0px; height: 120px;">
					<a
						onclick="javascript:changeSel('<c:out value="${districtValue.districtcode}"/>','<c:out value="${districtValue.districtname}"/>','hsien','hsienname','hsiens');"
						style="cursor: pointer" class=""><c:out
							value="${districtValue.districtname}" /> </a>
				</div>
			</c:forEach>

			<div class="clear"></div>
		</div>
	</div>
	<!-- 选择现在的住地（省份）-->
	<div class="channelpanel" id="nowprovinces" style="display: none">
		<div class="paneltitle">
			<a href="javascript:showselpanel(false,'nowprovinces');"
				style="cursor: pointer"> <img src="img/gbchange.png" width="15"
				height="16"></a>
		</div>
		<div class="channellist">
			<c:forEach items="${districts}" var="districtValue"
				varStatus="status">
				<div class="mnlist" style="text-indent: 0px; height: 120px;">
					<a
						onclick="javascript:changeSel('<c:out value="${districtValue.districtcode}"/>','<c:out value="${districtValue.districtname}"/>','nowprovince','nowprovincename','nowprovinces');loadChildDistrict('nowprovince',2)"
						style="cursor: pointer" class=""><c:out
							value="${districtValue.districtname}" /> </a>
				</div>
			</c:forEach>

			<div class="clear"></div>
		</div>
	</div>
	<!-- 选择现在的住地（旗县）-->
	<div class="channelpanel" id="nowhsiens" style="display: none">
		<div class="paneltitle">
			<a href="javascript:showselpanel(false,'nowhsiens');"
				style="cursor: pointer"> <img src="img/gbchange.png" width="15"
				height="16"></a>
		</div>
		<div id="nowhsienlist" class="channellist">
			<c:forEach items="${nowhsien}" var="districtValue" varStatus="status">
				<div class="mnlist" style="text-indent: 0px; height: 120px;">
					<a
						onclick="javascript:changeSel('<c:out value="${districtValue.districtcode}"/>','<c:out value="${districtValue.districtname}"/>','nowhsien','nowhsienname','nowhsiens')"
						style="cursor: pointer" class=""><c:out
							value="${districtValue.districtname}" /> </a>
				</div>
			</c:forEach>
			<div class="clear"></div>
		</div>
	</div>
	<!-- 选择职业 -->
	<div class="channelpanel" id="professions" style="display: none">
		<div class="paneltitle">
			<a href="javascript:showselpanel(false,'professions');"
				style="cursor: pointer"> <img src="img/gbchange.png" width="15"
				height="16"></a>
		</div>
		<div class="channellist">
			<c:forEach items="${professions}" var="professionValue"
				varStatus="status">
				<div class="mnlist" style="text-indent: 0px; height: 120px;">
					<a
						onclick="javascript:changeSel('<c:out value="${professionValue.professioncode}"/>','<c:out value="${professionValue.professionname}"/>','professioncode','professioncodename','professions')"
						style="cursor: pointer" class=""><c:out
							value="${professionValue.professionname}" /> </a>
				</div>
			</c:forEach>

			<div class="clear"></div>
		</div>
	</div>
</body>
<script>
	var districtsdata = <c:out value="${districtsdata}" escapeXml="false" />;
	//var professions = <c:out value="${professions}" escapeXml="false" />;
</script>
</html>