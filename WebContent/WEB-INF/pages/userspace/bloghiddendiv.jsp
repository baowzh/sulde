<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="messdiv" style="display: none">
	<div class="comment rotatesection">
		<form action="addmessage.do" name="addmessageform" id="addmessageform">

			<div class="m1ln"
				style="height: 20px; width: 400px; text-align: center;">
				</div>
			<textarea name="messagecontent" id="messagecontent"
				style="display: none">								    
								</textarea>
			<div class="flt mVsheet commentEdit mglcontent"
				contentEditable="true" id="messagediv"
				style="width: 420px; height: 200px; border: #CCC solid 1px; border-radius: 6px;"></div>
			<input type="hidden" name="senduserid" id="senduserid">
			<div class="m1ln" style="height: 110px; width: 400px">
				<%@ include file="messface.jsp"%>
			</div>
			<div class="m1ln"
				style="height: 20px; width: 400px; text-align: center;">
				<a
					href="javascript:sendmessage('<c:out value="${user.userid}" />');"></a>
			</div>

		</form>
	</div>
</div>
<div id="addfrienddiv" style="display: none; background-color: #fff;">
	<div class="comment rotatesection">
		<form action="addfriend.do" name="addfriendform" id="addfriendform">
			<div class="m1ln" contentEditable="false" id="addfriendmess"
				style="width: 170px; height: 70px !important"></div>
			<div class="m1ln" style="text-align: center;"></div>
			<div class="m1ln" style="text-align: center;">
				<a
					href="javascript:sendaddfriendmess('<c:out value="${user.userid}" />');">
				</a>
			</div>
		</form>
	</div>
</div>
<div id="addphotoalbum"
	style="width: 270px; height: 320px; display: none;">
	<div class="content rotatesection"
		style="height: 270px; width: 300px; background: white; padding: 5px; border-radius: 5px;">
		<form action="addimggroup.do" id="addphotoalbumform" class="mglForm"
			method="post" enctype="multipart/form-data">
			<div class="m1ln" style="text-align: center;">  :</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="text" name="imggroupname" id="imggroupname"
					style="-webkit-transform-origin: 10px 20px;" /> <input
					type="hidden" name="userid" id="userid"
					value="<c:out value="${user.userid}" />"></input>
			</div>

			<div class="m1ln" style="text-align: center;">  :</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="text" name="comm" id="comm"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="m1ln" style="text-align: center;"> :</div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input type="file" name="imgurl" id="imgurl"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="m1ln" style="width: 270px;"></div>
			<div class="m1ln"
				style="height: 24px; width: 200px; text-align: center;">
				<a href="javascript:addphotoalbum();"></a>

			</div>
		</form>
	</div>
</div>
<div class="content rotatesection" id="updpassdiv"
	style="padding-top: 8px; display: none; background: white; padding: 5px; border-radius: 5px;">
	<form class="mglForm" action="#" id="loginform" method="post">
		<c:if test="${maillogin==0}">
			<div class="m1ln"> </div>
			<div class="m1ln" style="height: 32px; width: 270px;">
				<input name="oldpassword" id="oldpassword" type="password"
					style="-webkit-transform-origin: 10px 20px;">
			</div>
		</c:if>
		<div class="m1ln">   </div>
		<div class="m1ln" style="height: 32px; width: 270px;">
			<input name="password" id="password" type="password"
				style="-webkit-transform-origin: 10px 20px;">
		</div>
		<div class="m1ln">
			  <a href="javascript:replaceverifycode();"></a> :
		</div>
		<div class="m1ln" style="height: 20px;">
			<img src="verifyCodeServlet" id="varifyimg" width="18" height="100">
		</div>
		<div class="m1ln" style="height: 32px; width: 270px;">
			<input name="validcode" id="varifycode"
				style="-webkit-transform-origin: 10px 20px;">
		</div>
		<div class="m1ln" style="text-align: center; width: 210px;">
			<a href="javascript:modifypass();">  </a>
		</div>
		<input type="hidden" name="maillogin" id="maillogin"
			value="<c:out value="${maillogin}" />">
	</form>
</div>
<div id="messlist" style="display: none"></div>
<style>
.divh3 {
	position: relative;
	padding-bottom: 0.5em;
	margin-top: 2px;
	min-height: 0px;
	padding-left: 0.7em;
	padding-right: 0.5em;
	cursor: pointer;
	padding-top: 0.5em;
	border-bottom: #fbd850 1px solid;
	border-left: #fbd850 1px solid;
	/*color:#eb8f00;*/
	border-top: #fbd850 1px solid;
	font-weight: bold;
	border-right: #fbd850 1px solid;
	-webkit-writing-mode: vertical-lr;
	writing-mode: tb-lr;
}
</style>
<!-- class="lmainR" -->
<!-- -webkit-writing-mode: vertical-lr; writing-mode: tb-lr;  -->
<div id="accordion" style="display: none">
	<div class="m1ln rotatesection" style="height: 20px;"
		onclick="javascript:switchdiv('receivediv','senddiv');">
		</div>
	<!--  -->
	<div class="  lcell" style="width: 800px; height: 450px; float: left;"
		id="receivediv">
		<c:if test="${notlogin==0}">
			<iframe width="100%" height="450px;" frameBorder="0" frameSpacing="0"
				scrolling="auto" src="getMessage.do?type=1"></iframe>
		</c:if>
	</div>
	<div class="m1ln rotatesection" style="height: 20px;"
		onclick="javascript:switchdiv('senddiv','receivediv');">
		 </div>
	<div class="  lcell"
		style="width: 800px; height: 450px; display: none; float: left;"
		id="senddiv">
		<c:if test="${notlogin==0}">
			<iframe width="100%" height="450px;" frameBorder="0" frameSpacing="0"
				scrolling="auto" src="getMessage.do?type=2"></iframe>
		</c:if>

	</div>
</div>
<div class="cardt1" id="userinfo"
	style="margin: 0px; height: 478px; width: 750px; display: none;">
	<div class="  lcell" style="width: 280px; height: 200px;">
		<div class="lcell userinfotab rotatesection"
			style="height: 790px; width: 100%;">
			<table class="m1ln h100">

				<tr>
					<td>
						<div class="m1ln h100">  :</div>
					</td>
					<td>
						<div class="m1ln">
							<c:out value="${user.bolgname}" />
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
						<div class="m1ln">
							<c:out value="${user.firstname}" />
						</div>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td><c:out value="${user.artname}" />
						</div></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td>
						<div class="m1ln">
							<c:out value="${user.birthday}" />
						</div>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td>
						<div class="m1ln">
							<c:choose>
								<c:when test="${user.sex==1}">
									<input type="radio" name="sex" checked="true" id="sex"></input>
									<input type="radio" name="sex" id="sex"></input>
									<input type="radio" name="sex" id="sex"></input>
								</c:when>
								<c:when test="${user.sex==0}">
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
						<div class="m1ln">
							<c:out value="${user.unit}" />
						</div>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td style="height: 90px">
						<div class="m1ln">
							<c:out value="${user.provincename}" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:out value="${user.hsienname}" />
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
						<div class="m1ln">

							<c:out value="${user.nowprovincename}" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:out value="${user.nowhsienname}" />
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
						<div class="m1ln">
							<c:out value="${user.phone}" />
						</div>
					</td>

				</tr>
				<tr>
					<td>
						<div class="m1ln h100">  (QQ) </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.qq}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> (Email) </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.email}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.hope}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.belief}" />
						</div>
					</td>
				</tr>

				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.idol}" />
						</div>
					</td>
				</tr>

				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.maxim}" />
						</div>
					</td>
				</tr>
				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.music}" />
						</div>
					</td>
				</tr>
				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td>
						<div class="m1ln">
							<c:out value="${user.book}" />
						</div>
					</td>
				</tr>
				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="m1ln">
							<c:out value="${user.singer}" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<div class="content" id="joinracediv"
	style="padding-left: 8px; display: none; background: white; padding: 5px; border-radius: 5px; z-index: 9999; position: relative;">
	<form class="mglForm" action="#" id="joinrace" method="post">

		<div class="inputHolder" style="width: 32px; height: 280px;">
			<div class="label" style="padding-left: 3px;"> 
				 </div>
			<input type="radio" name="jointype" value="1">
		</div>

		<div class="inputHolder" style="width: 32px; height: 280px;">
			<div class="label" style="padding-left: 3px;"> 
				 </div>
			<input type="radio" name="jointype" value="2">
		</div>
		<div class="label">
			  <a href="javascript:replaceverifycode('2');"></a>
			:
		</div>
		<div class="label">
			<a href="javascript:replaceverifycode('2');"><img
				src="verifyCodeServlet" id="varifyimg2" width="18" height="90"></a>
		</div>
		<div class="inputHolder" style="width: 32px; height: 170px;">
			<input name="raicevalidcode" id="raicevalidcode"
				style="-webkit-transform-origin: 10px 20px;">
		</div>
		<div class="mnlist" style="text-align: center; height: 170px;">

		</div>
		<div class="mnlist" style="text-align: center; height: 170px;">
			<a href="javascript:joinrace();"> </a>
		</div>
	</form>
</div>