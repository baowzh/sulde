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
	$("#openphotoform").submit();
};
var addnewimg = function() {
	$("#addimg").dialog({
		height : 410,
		width : 230,
		resizable : false
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
