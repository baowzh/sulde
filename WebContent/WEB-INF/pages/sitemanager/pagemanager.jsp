<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="js/ztree/css/zTreeStyle.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="js/jqGrid/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/sitejs/pagemanager.js" type="text/javascript"> </script>
<script src="js/ztree/js/jquery.ztree.all-3.5.min.js"
	type="text/javascript"> </script>
</style>
<style>
body {
	font-size: 62.5%;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<title>后台管理</title>
</head>
<body>
<table width="97%" height="636">
	<tr>
		<td width="10%" height="630" valign="top">
		<div class="zTreeDemoBackground left">
		<ul id="pagestree" class="ztree"></ul>
		</div>
		</td>
		<td width="90%" height="630" valign="top">
		<table width="100%" height="626">
			<tr height="5%">
				<td width="100%" height="34">
				<table width="20%" cellspacing=0 valing="middel">
					<tr>

						<td align="center"><input type="button" value="新建页面"
							onclick="javascript:addpage()"></td>
						<td align="center"><input type="button" value="修改页面"
							onclick="javascript:modifypageinfo()"></td>
						<td align="center"><input type="button" value="删除页面"
							onclick="javascript:deletepage()"></td>
						<td align="center" id="create-user"><input type="button"
							value="添加栏目"></td>
						<td align="center" id="delete-channel"><input type="button"
							onclick="javascript:deletechannel()" value="删除栏目"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="90%">
				<td width="100%" valign="top"><iframe id="pageinfo"
					width="100%" height="580" frameborder="0" src="getpageinfo.do">
				</iframe></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="dialog-form" title="添加栏目">
<p class="validateTips"></p>
<form id="addChannelForm" method="post">
<fieldset><label for="name">栏目名称</label> <input type="text"
	name="chnlname" id="chnlname"
	class="text ui-widget-content ui-corner-all" />&nbsp;<a id="menuBtn"
	href="#" onclick="showChannelTree(); return false;">选择</a> <label
	for="email">栏目描述</label> <input type="text" name="chnldesc"
	id="chnldesc" value="" class="text ui-widget-content ui-corner-all" />
<label for="channeldisplaytype">展现方式</label> <select
	name="channeldisplaytype" id="channeldisplaytype">
	<option value="1">列表</option>
	<option value="2">详细</option>
</select><label for="channeldoccount">展现个数</label> <input type="text"
	name="channeldoccount" id="channeldoccount" value=""
	class="text ui-widget-content ui-corner-all" /> <label
	for="variablename">要素名称</label> <input type="text" name="variablename"
	id="variablename" value="" class="text ui-widget-content ui-corner-all" />
<input type="hidden" name="channelid" id="channelid"> <input
	type="hidden" id="pageid" name="pageid"> <input type="hidden"
	id="pagename" name="pagename"></fieldset>
</form>
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute;">
<ul id="cityTree" class="ztree"
	style="margin-top: 0; width: 160px; z-index: -1; background: #eee"></ul>
</div>
</div>

</body>
<SCRIPT LANGUAGE="JAVASCRIPT">
 function  addpage(){ 
  document.getElementById('pageinfo').contentWindow.addpage(); 
 }
 function modifypageinfo(){
  document.getElementById('pageinfo').contentWindow.modifypageinfo(); 
 } 
 function deletechannel(){
  document.getElementById('pageinfo').contentWindow.deletechannel(); 
 } 
 
 
 
</SCRIPT>
</html>