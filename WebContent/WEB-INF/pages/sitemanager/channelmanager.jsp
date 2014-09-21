<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>栏目列表</title>

</head>
<link rel="stylesheet"
	href="js/jqui/css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" href="css/channel/channel.css" />
<script type="text/javascript" src="js/jqGrid/js/jquery-1.9.1.js"></script>
<script src="js/jqui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="js/channel/channel.js"></script>


<body>

	<!--现有栏目列表-->
	<form id=manageForm>
		<table id="channelList" class=channels>
			<tr>
				<th style="width: 2em;">序号</th>
				<th style="width: 4em;">选择</th>

				<th>栏目名字</th>
				<th>栏目显示名字</th>
				<th style="width: 4em;">操作</th>
			</tr>
			<c:forEach items="${channelList}" var="chnl" varStatus="status">
				<tr class="rec">
					<td align="left">${status.index+1}</td>
					<td align="center"><input type="checkbox" name="channel"
						value="<c:out value="${chnl.channelid}"/>" /></td>
					<td><c:out value="${chnl.chnlname}" /></td>
					<td><c:out value="${chnl.chnldesc}" /></td>
					<td><a href=" javascript:void(0);"
						onClick="modifyChannel('${chnl.channelid}')">修改</a></td>

					<input type="hidden" id="deleteids" name="deleteids" value="" />
					<input type="hidden" id="chnlid" name="chnlid"
						value="<c:out value="${chnl.channelid}"/>" />
				</tr>

			</c:forEach>
			<input type="hidden" id="channelids" name="channelids" value="" />
			<tr>
				<td>&nbsp;</span></td>
				<td colspan="1"><INPUT NAME="hehe" TYPE="button" VALUE="删除"
					onclick="javascript:deleteItem();" /></td>
				<td colspan="4"><INPUT NAME="hehe" TYPE="button" VALUE="创建栏目"
					onclick="addChannel()" /></td>
			</tr>
		</table>
	</form>
	<!--现有栏目列表-->





	<!--栏目增改对话框-->
	<div id="dialog-form" title="修改栏目">
		<p class="validateTips">所有字段必填。</p>
		<form id="modifyForm" method="post"">
			<fieldset>
				<label for="name">栏目名字</label> <input type="text" name="chnlname"
					id="chnlname" class="text ui-widget-content ui-corner-all" /><label
					for="name">栏目显示名字</label> <input type="text" name="chnldesc"
					id="chnldesc" class="text ui-widget-content ui-corner-all" /> <input
					type="hidden" name="channelid" id="channelid" />
			</fieldset>

		</form>
	</div>
	<!--栏目增改对话框-->

</body>
</html>