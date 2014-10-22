<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="flt" style="width: 250px;">
	<div class="flt" style="width: 250px;">
		<div class="flt"
			style="position: relative; top: -190px; padding: 8px;" id="nameCard">
			<div class=" flt nameCard nameCardC">
				<div class="flt">
					<div class="m1ln name">
						<!--   -->
						<c:out value="${user.artname}" />
					</div>
					<div class="avatar">
						<img src="getheadimge.do?userid=<c:out value="${user.userid}" />"
							width="334" height="446" />
					</div>
				</div>
				<div class="cbt"></div>
				<div class=" "
					style="padding-top: 10px; width: 180px; margin: 0 auto">
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
					<!--  <div class="m1ln">     </div>-->
					<div class="m1ln">
						 
						<c:out value="${user.age}" default="  " />
					</div>
					<!--  <div class="m1ln">       </div>-->
					<div class="m1ln">
						  
						<c:out value="${user.nowprovincename}" default="  " />
					</div>
					<!--  <div class="m1ln">   2010-1-1</div>-->
					<div class="m1ln">
						 
						<c:out value="${user.regdatestr}" default="  " />
					</div>
					<!-- <div class="m1ln">   1000</div> -->
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
							href="javascript:writemessage('<c:out value="${user.userid}" />');"> &nbsp;&nbsp;&nbsp;</a>
						<c:if test="${self==1}">
							<a href="javascript:receivemessage();">(<c:out
									value="${messageCount}" />)
							</a>
						</c:if>
					</div>
					<div class="m1ln">
					<!--  
						<a
							href="doedituserinifo.do?userid=<c:out value="${user.userid}" />">
							  &nbsp;&nbsp;&nbsp; </a>
							-->
							
							<c:if test="${self==1}">
							<a
							href="doedituserinifo.do">
							  &nbsp;&nbsp;&nbsp; </a>
							</c:if>
							<c:if test="${self==0}">
							<a
							href="javascript:showuserinfo('<c:out value="${user.userid}" />');">
							  &nbsp;&nbsp;&nbsp; </a>
							</c:if>
						
							
						<c:if test="${self==0}">
							<!--  
							<a
								href="javascript:addfriends('<c:out value="${user.userid}" />');">
								 </a>
								-->
							<a
								href="javascript:openaddfrienddl();">
								 </a>
								
						</c:if>
					</div>
					<div class="frt" style="width: 0px; height: 0px;">
						<div class="pin"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="frt blgNav blgNavC" style="width: 20px;">
			<div class=" m1ln " style="padding-top: 8px;">
				<a href="gouserindex.do?userid=<c:out value="${user.userid}" />">
					<c:if test="${self==1}"> </c:if>
					<c:if test="${self==0}">  </c:if>
				</a> 

				<!--							  <a href="#">  </a>   -->
				<!--							  <a href="#">   </a>   -->

				<a href="photoAlbumList.do?userid=<c:out value="${user.userid}" />"></a>
				 <a href="friendlist.do?userid=<c:out value="${user.userid}" />"></a>  <a href="index.html"> 
				</a>
			</div>
		</div>
		<div class=" flt"
			style="width: 225px; margin: 0px 8px 8px 8px; padding: 5px; background: white;">
			<div class="friendL">
				<div class="i">
					<div class="m0a" style="width: 40px;">
						<div class=" msheet" style="padding-top: 10px;">
							<a href="#"> <br />   :
							</a>
						</div>
					</div>
				</div>
			</div>
			<c:forEach items="${fvalues}" var="friendValue" varStatus="status">
				<div class=" friendL">
					<div class="i">
						<a
							href="gouserindex.do?userid=<c:out value="${friendValue.friendid}" />"><img
							src="getsmheadimge.do?userid=<c:out value="${friendValue.friendid}" />"
							width="334" height="446" /></a>
						<div class="frt" style="width: 20px;">
							<div class="m1ln">
								<a
									href="gouserindex.do?userid=<c:out value="${friendValue.friendid}" />">
									<c:out value="${friendValue.friendname}" />
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class=" flt"
			style="width: 225px; margin: 0px 8px 8px 8px; padding: 5px; background: white;">
			<div class="friendL">
				<div class="i">
					<div class="m0a" style="width: 40px;">
						<div class=" msheet" style="padding-top: 10px;">
							<a href="#"> <br />  :
							</a>
						</div>
					</div>
				</div>
			</div>
			<!-- visitors -->
			<c:forEach items="${visitors}" var="visitorValue" varStatus="status">
				<div class=" friendL">
					<div class="i">
						<img
							src="getsmheadimge.do?userid=<c:out value="${visitorValue.visitorid}" />"
							width="334" height="446" />
						<div class="frt" style="width: 20px;">
							<div class="m1ln">
								<a
									href="gouserindex.do?userid=<c:out value="${visitorValue.visitorid}" />">
									<c:out value="${visitorValue.visitorname}" />
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
