<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blog Home</title>
<link href="img/css/main.css" type="text/css"  rel="stylesheet"/>
<link href="img/css/listpages.css" type="text/css"  rel="stylesheet"/>
<link href="img/css/blog.css" type="text/css"  rel="stylesheet"/>
<link href="img/css/custom.css" type="text/css"  rel="stylesheet"/>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/sitejs/viewoptinion.js"></script>
<script type="text/javascript" src="img/css/mSheetAutoHeight.js"></script>
</head>
<body>
<div class="lmainR ofh" style="text-align:center;  height:300px; "> <img src="img/01.jpg" width="980"   /></div>
<div class="lmainR ofh" style="text-align:center;   " >
	<%@ include file="../userspace/bloghead.jsp"%>  
</div>
<div class="lmainR">
<div id="nameCard">
	<div class="  lcell" style="width:990px;  overflow:visible;  ">
		<div class=" blogbody blogbodyC">
			<div class="blgmain bglMainC" id="blgMain" style="margin-left:0px;width:970px;">
				<div style=" width:690px; margin:0px 10px 10px 10px ;   ">					
					<div class="recentAcvtyBox contentArea" style="width:940px;">
						<div class="mVsheet " id="vs">
						<div class="ScrollToView"><div > </div></div>
						<h1>    					
						<c:out value="${opinion.title}" />						
						</h1>
						
						<div id="docdetail" style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr">
					    
                        </div>
						<input type="hidden" name="opinionid" id="opinionid" value="<c:out value="${opinion.opinionid}" />">				 
						</div>						
					</div>								
					<div class="cbt"></div>
				</div>
			</div>
			<div class="cbt"> </div>
		</div>
	</div>
	</div>
</div>
<div class="lmainR ofh" style="text-align: center;">
		<div class="tailCard">
			<%@ include file="../website/tail.jsp"%>
		</div>
		<div class="cbt"></div>
	</div>
</body>
</html>