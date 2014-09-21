<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/sitejs/regist.js"></script>
<script type="text/javascript" src="js/util/js/messageWindow.js"></script>
<link href="img/css/main.css" type="text/css" rel="stylesheet" />
<script src="js/messagebox/jquery.msgbox.js"></script>
<script src="js/messagebox/jquery.dragndrop.min.js"></script>
<link rel="stylesheet" href="js\messagebox\jquery.msgbox.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> </title>
</head>
<body>
	<div class="lmainR ofh" style="text-align: center; height: 64px;">
		<img src="img/logo.png" width="980" />
	</div>
	<div class="lmainR ofh" style="text-align: center;">
		<form action="doregiste.do" id="userinfoform" method="post">
			<div class="lmainR"
				style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr; height: 430px;">
				<div class="mnlist">  </div>
				<div class="mnlist" style="height: 430px;">  
					          </div>
				<div class="mnlist" style="height: 430px;">   
					       </div>
				<div class="mnlist" style="height: 430px;">  
					     </div>
				<div class="mnlist" style="height: 430px;">  
					         </div>
				<div class="mnlist" style="height: 430px;">  
					      </div>
				<div class="mnlist" style="height: 430px;">   
					       </div>
				<div class="mnlist" style="height: 430px;">  
					      </div>
				<table class="m1ln h100" style="height:270px; width:120px;">
					<tr>
						<td> :</td>
						<td colspan="3">
							<div class="mfl" >
								<input type="text" name="username" id="username" >
							</div>
						</td>
					</tr>
					<tr>
						<td>  :</td>
						<td colspan="3">
							<div class="mfl" >
								<input type="text" name="artname" id="artname" >
							</div>
						</td>
						
					</tr>
					<tr>
						<td>  </td>
						<td colspan="3">
							<div class="mfl">
								<input type="password" name="password" id="password" >
							</div>
						</td>
						
					</tr>
					<tr>
						<td>  </td>
						<td colspan="3">
							<table
								style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr; widht: 100%; height: 100%;">
								<tr>
									<td height=75><a href="javascript:replaceverifycode();"><img
											src="verifyCodeServlet" id="varifyimg" width="23"></a></td>
									<td>
										<div class="mfl" style="height: 80px;">
											<c:if test="${agentkind==0}">
												<input type="text" name="varifycode" id="varifycode"
													style="height: 74px;">
											</c:if>
											<c:if test="${agentkind==1}">
												<input type="text" name="varifycode" id="varifycode"
													style="width: 78px;">
											</c:if>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<img src="img/turim.gif">
				<div class="mnlist" style="height: 430px;">
					<input name="agree" id="agree" type="checkbox">  
					   
				</div>
				<div class="mnlist" style="height: 210px; text-align: center;">
					<a href="javascript:doregist();">  </a> &nbsp;&nbsp;<a
						href="index.html">  </a>
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
</body>
</html>