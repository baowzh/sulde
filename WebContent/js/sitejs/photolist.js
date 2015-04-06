/**
 * 响应点击相册
 */
var photoDetail = function(imgid) {
	if (imgid == null || imgid == '') {
		return;
	}
	var userid = $("#userid").val();
	if (userid == null || userid == '') {
		return;
	}
	$("#imgid").val(imgid);
	// $("#openphotoform").submit();
	window.location.href = "getimginfo.do?userid=" + userid + "&imgid=" + imgid;
};
var addnewimg = function() {
	$("#addimg").dialog({
		height : 390,
		width : 360,
		resizable : true
	});
}
var uploadimg = function() {
	var groupid = $("#imggroupid").val();
	var desc = $("#imgcomm").val();
	var imgurl = $("#img").val();
	if (desc == null || desc == '') {
		MessageWindow.showMess('       ');
		return;
	}
	if (imgurl == null || imgurl == '') {
		MessageWindow.showMess('    ');
		return;
	}

	$("#addimgform")[0].submit();
}

var deleteimg = function() {
	var boxes = $("input[name='selectedimg']");
	var ids = "";
	boxes.each(function() {
		if (this.checked) {
			ids = ids + $(this).attr("id") + ',';
		}
	});
	if (ids == '') {
		return;
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "deleteimgs.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			imgids : ids
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '1') {
				window.location.href = 'getimglist.do?userid='
						+ $('#userid').val();
			}
		}
	});

}
