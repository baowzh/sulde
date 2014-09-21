<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css\comm.css" rel="stylesheet" type="text/css" />
<link href="js/ztree/css/zTreeStyle.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script src="js/sitejs/managerindex.js" type="text/javascript">
	
</script>
<script src="js/ztree/js/jquery.ztree.all-3.5.min.js"
	type="text/javascript">
	
</script>
<style type="text/css">
.ztree li a.level0 {
	width: 200px;
	height: 20px;
	text-align: center;
	display: block;
	background-color: #0B61A4;
	border: 1px silver solid;
}

.ztree li a.level0.cur {
	background-color: #66A3D2;
}

.ztree li a.level0 span {
	display: block;
	color: white;
	padding-top: 3px;
	font-size: 12px;
	font-weight: bold;
	word-spacing: 2px;
}

.ztree li a.level0 span.button {
	float: right;
	margin-left: 10px;
	visibility: visible;
	display: none;
}

.ztree li span.button.switch.level0 {
	display: none;
}
</style>
<title>后台管理</title>
</head>
<body>
	<table height="900" widht="90%">
		<tr>
			<td width="10%" height="100%" valign="top">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</td>
			<td width="90%" height="100%" valign="top"><iframe height="100%"
					id="workarea" frameborder="0" width="100%" src="towelcome.do"></iframe></td>
		</tr>
	</table>
</body>
</html>