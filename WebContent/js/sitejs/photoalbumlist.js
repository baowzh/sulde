/**
 * 响应点击相册
 */
var openPhotoAlbum = function(albumid) {
	if (albumid == null || albumid == '') {
		return;
	}
	var userid = $("#userid").val();
	if (userid == null || userid == '') {
		return;
	}
	$("#imggroupid").val(albumid);
	$("#openalbumform").submit();
};
var openaddphotoalbumdialog = function() {
	$("#addphotoalbum").dialog({
		height : 410,
		width : 170,
		resizable : false
	});
};
var addphotoalbum = function() {
	var imggroupname = $("#imggroupname").val();
	var comm = $("#comm").val();
	var faceimgurl = $("#imgurl").val();
	if (imggroupname == null || imggroupname == '') {
		MessageWindow.showMess('     ');
		return;
	}
	if (comm == null || comm == '') {
		MessageWindow.showMess('   ');
		return;
	}
	if (faceimgurl == null || faceimgurl == '') {
		MessageWindow.showMess('      ');
		return;
	}

	$("#addphotoalbumform").submit();
};
/**
 * 删除相册
 */
var deletephotoalbum = function() {
	//
	var radios = $("input[name='selectedradio']");
	var checkedcount = 0;
	var selecteddocid = '';
	radios.each(function() {
		if (this.checked) {
			checkedcount++;
			selecteddocid = this.id;
		}
	});
	if (selecteddocid == '') {
		MessageWindow.showMess('      ');
		return;
	} else {
		showConfirmMess("         ", function() {
			if (this.getValue() == true) {
				delphotoalbum(selecteddocid);
			}

		});
	}
};
var delphotoalbum = function(selecteddocid) {

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : 'dodeleteimggroup.do',// 请求的action路径
		data : {
			imggroupid : selecteddocid
		},
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('         ');
		},
		success : function(data) {
			if (data.success == '1') {
				MessageWindow.showMess('   ');
				window.location.href="photoAlbumList.do?userid="+data.userid;
			} else {
				MessageWindow.showMess('    ');
			}
		}
	});

};
