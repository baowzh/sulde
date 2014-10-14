<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="messdiv" style="display: none">
	<div class="comment">
		<form action="addmessage.do" name="addmessageform" id="addmessageform">
			<div class="mnlist"
				style="width: 20px; height: 400px; text-align: center;">
				</div>
			<textarea name="messagecontent" id="messagecontent"
				style="display: none">								    
								</textarea>
			<div class="flt mVsheet commentEdit" contentEditable="true"
				id="messagediv" style="height: 400px;"></div>
			<input type="hidden" name="senduserid" id="senduserid">
			<div class="m1ln" style="width: 110px; height: 400px">
				<%@ include file="messface.jsp"%>
			</div>
			<div class="mnlist"
				style="width: 20px; height: 400px; text-align: center;">
				<a
					href="javascript:sendmessage('<c:out value="${user.userid}" />');"></a>
			</div>
		</form>
	</div>
</div>
<div id="addfrienddiv" style="display: none">
	<div class="comment">
		<form action="addfriend.do" name="addfriendform" id="addfriendform">
			<div class="flt mVsheet commentEdit" contentEditable="true"
				id="addfriendmess" style="height: 300px; width: 170px !important"></div>
			<div class="mnlist">
				<a
					href="javascript:sendaddfriendmess('<c:out value="${user.userid}" />');">
				</a>
			</div>
		</form>
	</div>
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
<div id="accordion" class="lmainR"
	style="-webkit-writing-mode: vertical-lr; writing-mode: tb-lr; display: none">
	<div class="mnlist"
		onclick="javascript:switchdiv('receivediv','senddiv');">
		</div>
	<!--  -->
	<div class="  lcell" style="width: 800px; height: 450px;"
		id="receivediv">
		<c:if test="${notlogin==0}">
			<iframe width="100%" height="450px;" frameBorder="0" frameSpacing="0"
				scrolling="auto" src="getMessage.do?type=1"></iframe>
		</c:if>
	</div>
	<div class="mnlist"
		onclick="javascript:switchdiv('senddiv','receivediv');">
		 </div>
	<div class="  lcell"
		style="width: 800px; height: 450px; display: none;" id="senddiv">
		<c:if test="${notlogin==0}">
			<iframe width="100%" height="450px;" frameBorder="0" frameSpacing="0"
				scrolling="auto" src="getMessage.do?type=2"></iframe>
		</c:if>

	</div>
</div>
<div class="cardt1" id="userinfo"
	style="margin: 0px; height: 478px; width: 750px; display: none;">
	<div class="  lcell" style="width: 600px; height: 470px;">
		<div class="lcell userinfotab" style="width: 790px; height: 100%;">
			<table class="m1ln h100">

				<tr>
					<td>
						<div class="m1ln h100">  :</div>
					</td>
					<td>
						<div class="mfl">
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
						<div class="mfl">
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
						<div class="mfl">
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
						<div class="mfl">
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
						<div class="mfl">
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
						<div class="mfl">
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
						<div class="mfl">

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
						<div class="mfl">
							<c:out value="${user.phone}" />
						</div>
					</td>

				</tr>
				<tr>
					<td>
						<div class="m1ln h100">  (QQ) </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.qq}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> (Email) </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.email}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.hope}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1ln h100"> </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.belief}" />
						</div>
					</td>
				</tr>

				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.idol}" />
						</div>
					</td>
				</tr>

				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.maxim}" />
						</div>
					</td>
				</tr>
				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.music}" />
						</div>
					</td>
				</tr>
				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td>
						<div class="mfl">
							<c:out value="${user.book}" />
						</div>
					</td>
				</tr>
				<tr style="width: 50px">
					<td>
						<div class="m1ln h100">  </div>
					</td>
					<td colspan="3">
						<div class="mfl">
							<c:out value="${user.singer}" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>