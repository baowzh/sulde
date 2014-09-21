<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>添加栏目</title>
	<style>
.cnladd table {
	border-collapse: collapse;
	border: #999 1px solid;
}

.cnladd td,th {
	border: #999 1px solid;
	background: ;
}
</style>
</head>

<body onload="javascript:showmess()">
	<form action="addchannel.do" method=post id="chnlAdd">
		<div class="cnladd">
			<TABLE WIDTH="500" calss="">
				<TR>
					<TH WIDTH="200" ALIGN="RIGHT" SCOPE="col">&#26639;&#30446;&#21807;&#19968;&#21517;&#31216;</TH>
					<TH WIDTH="861" ALIGN="LEFT" SCOPE="col"><INPUT TYPE="text"
						NAME="chnlname" ID="chnlname"></TH>
				</TR>
				<TR>
					<TD ALIGN="RIGHT">&#26174;&#31034;&#21517;&#23383;</TD>
					<TD ALIGN="LEFT"><LABEL FOR="CHNLDESC"></LABEL> <INPUT
						TYPE="text" NAME="chnldesc" ID="chnldesc"></TD>
				</TR>
				<TR>
					<TD ALIGN="RIGHT">&#26639;&#30446;&#23384;&#25918;&#20301;&#32622;</TD>
					<TD ALIGN="LEFT"><LABEL FOR="CHNLDATAPATH"></LABEL> <INPUT
						TYPE="text" NAME="chnldatapath" ID="chnldatapath"></TD>
				</TR>
				<TR>
					<TD ALIGN="RIGHT">&#26639;&#30446;&#31867;&#22411;</TD>
					<TD ALIGN="LEFT"><LABEL FOR="CHNLTYPE"></LABEL> <INPUT
						TYPE="text" NAME="chnltype" ID="CHNLTYPE"></TD>
				</TR>
				<TR>
					<TD ALIGN="RIGHT"><INPUT NAME="" TYPE="submit" VALUE="创建"></TD>
					<TD ALIGN="CENTER">&nbsp;</TD>
				</TR>
			</TABLE>
		</div>
	</form>
</body>

</html>
<script>
	function showmess() {
		<c:if test="${addsucess==1}">
		alert("栏目添加成功!");
		document.getElementById("chnlAdd").action = "channel.do";
		document.getElementById("chnlAdd").submit();
		</c:if>

	}
</script>