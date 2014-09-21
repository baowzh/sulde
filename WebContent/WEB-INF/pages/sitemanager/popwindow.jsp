<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" href="js/util/css/popwindow.css" />
<script type="text/javascript" src="js/util/js/popwindow.js"></script>
<title></title>
</head>
<body>
<hr>
<div><input type="button" id="show"
	onclick="javascript:new DivWindow('popup','popup_drag','popup_exit','exitButton','500','700',4);"
	value='点击弹出窗口' /></div>
  <!-- 遮罩层 -->
<div id="mask" class="mask"></div>
<!-- 弹出基本资料详细DIV层 -->
<div class="sample_popup" id="popup"
	style="visibility: hidden; display: none; writing-mode: tb-lr">
<div class="menu_form_header" id="popup_drag"
	style='cursor: pointer; writing-mode: tb-lr;'>


<div style="float: left;">  </div>

</div>
<div class="menu_form_body">
<div id="popDetail" style="writing-mode: tb-lr;">
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
<div style="writing-mode: tb-lr;">    
 </div>
</div>
</div>
<div id="popup_button" style="float: left; writing-mode: tb-lr;">
<div style="float: center;"> </div>
</div>
<div id="popup_exit" style="float: left; margin-right: 10px;">X</div>
</div>
<!-- 弹出基本资料详细DIV层 -->
<div class="sample_popup" id="Div1"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="Div2"><input type="button"
	id="exit2" value="退出" /></div>
<div class="menu_form_body">
<div id="Div3"></div>
</div>
</div>
</body>
</html>
