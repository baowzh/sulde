<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="lmainR" style="width: 100%; background: #1b6fbd;">
	<div class="loginSheet">
		<div class="border" style="background:none;">
			<div class="loginWindow" style="margin:0px auto">
				<div class="content"  style="width: 150px;">
					<form class="mglForm" action="checkandlogin.do" id="loginform"
						method="post">
						<div class="label" style="width: 35px;">  </div>
						<div class="label" style="width: 35px;">  </div>
						<div class="label" style="width: 35px;">
							  <a href="javascript:replaceverifycode();"></a>
							:
						</div>
						<div class="label" style="width: 35px;">
							<img src="verifyCodeServlet" id="varifyimg" width="18"
								height="100" />
						</div>
						<div class="inputHolder" style="width: 35px;">
							<input name="username" id="username" value="" />
						</div>
						<div class="inputHolder" style="width: 35px;">
							<input name="password" id="password" type="password" />
						</div>
						<div class="inputHolder" style="width: 35px;">
							<input name="varifycode" id="varifycode" value="" />
						</div>
						<div class="mnlist">
							<!-- 						<input  type="button" onclick="javascript:dosubmit();" value=" "/> -->
							<a href="javascript:phonelogin();"></a> <a
								id="registlink" href="registe.do">&nbsp;&nbsp;
								&nbsp;&nbsp;</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	<c:choose>
	<c:when test="${mess==1}">
	MessageWindow.showMess("           ");
	</c:when>
	<c:when test="${mess==2}">
	MessageWindow.showMess("    ");
	</c:when>
	<c:otherwise>
	</c:otherwise>
	</c:choose>
</script>