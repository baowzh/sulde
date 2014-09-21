var opertype = 0;
function createimggroup() {
	opertype = 0;
	$("#dialog").dialog();
}
function checkAndSubmit() {
	var imggroupname = $("#imggroupname").val();
	var comment = $("#comment").val();
	var imggroupkind = $("#imggroupkind").val();
	var faceimgurl = $("#imgurl").val();

	if (imggroupname == null || imggroupname == '') {
		$("#validateTips")
				.text("              ");
		return;
	}
	if (comment == null || comment == '') {
		$("#validateTips")
				.text("                ");
		return;
	}
	if (imggroupkind == null || imggroupkind == '') {
		$("#validateTips")
				.text("                  ");
		return;
	}
	if (faceimgurl == null || faceimgurl == '') {
		$("#validateTips").text("           ");
		return;
	}
	if (opertype == 0) {
		$("#addimggroup")[0].action = "addimggroup.do";
	} else {
		$("#addimggroup")[0].action = "updimggroup.do";

	}
	$("#addimggroup")[0].submit();
}
function deleteimggroup(ids) {
	/*
	 * var checkboxs = $("[name='groupcheckbox']"); var groupids =
	 * $("[name='groupid']"); var opergroupid = $("#opergroupid"); var ids = "";
	 * for (var i = 0; i < checkboxs.length; i++) { var checkbox = checkboxs[i];
	 * if (checkbox.checked) { ids = ids + groupids[i].value + ","; } }
	 */
	var opergroupid = $("#opergroupid");
	opergroupid[0].value = ids
	if (ids != '') {
		$("#manageimggroup")[0].action = 'dodeleteimggroup.do';
		$("#manageimggroup").submit();

	}
}
function updimggroup(ids) {
	opertype = 1;
	/*
	 * var checkboxs = $("[name='groupcheckbox']"); var groupids =
	 * $("[name='groupid']");
	 */
	var id = ids;
	/*
	 * for (var i = 0; i < checkboxs.length; i++) { var checkbox = checkboxs[i];
	 * if (checkbox.checked) { id = groupids[i].value; } }
	 */
	// 获取相关信息并打开修改对话框
	$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "getimggroupinfo.do",// 请求的action路径
				data : {
					imggroupid : id
				},
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					var imgGrpupValue = data.imgGrpupValue;
					$("#imggroupid").val(imgGrpupValue.imggroupid);
					$("#imggroupname").val(imgGrpupValue.imggroupname);
					$("#comment").val(imgGrpupValue.comment);
					$("#imggroupkind").val(imgGrpupValue.imggroupkind);
					$("#imgurl").val(imgGrpupValue.faceimgurl);
				}
			});
	$("#dialog").dialog();
}
function showoperdiv(objid, oper) {
	var obj = $("#" + objid);
	if (oper == 1) {
		obj.css("display", "block");
	} else {
		obj.css("display", "none");
	}

}
function openupdimgdl(imggroup) {
	// 获取相册相关信息
	$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "getimggroupinfo.do",// 请求的action路径
				data : {
					imggroupid : imggroup
				},
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					for (var i in data.list) {
					$("#groupid").append("<option value=\""
							+ data.list[i].imggroupid + "\">"
							+data.list[i].imggroupname + "</option>");
				}
				}
			});
	$("#addimgdialog").dialog();

}
function updimg() {
	var imgcomm = $("#imgcomm").val();
	var groupid = $("#groupid").val();
	var url = $("#url").val();
	if (groupid == null || groupid == '') {
		$("#validateTips1")
				.text("              ");
		return;
	}
	if (imgcomm == null || imgcomm == '') {
		$("#validateTips1")
				.text("                ");
		return;
	}
	if (url == null || url == '') {
		$("#validateTips1").text("           ");
		return;
	}
	$("#addimg")[0].action = "addimg.do";
	$("#addimg")[0].submit();
}
function getImgeList(imggroupid){
	$("#opergroupid").val(imggroupid);
	$("#manageimggroup")[0].action = "getimglist.do";
	$("#manageimggroup")[0].submit();
	
}
