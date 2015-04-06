/**
 * 打开新增界面
 */
var openadddialog = function() {
	$("#addphotoalbum").dialog({
		height : 420,
		width : 390,
		resizable : true,
		model : false
	});
};
/**
 * 
 */
var addbook = function() {
	$('#addbookform').submit();
}
/**
 * 
 */
var deletebook = function() {
	var boxes = $("input[name='bookcheckbox']");
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
		url : "delbooks.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			bookids : ids
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '1') {
				window.location.href = 'booklist.do';
			}
		}
	});

}
var sendtoindex = function() {
	var boxes = $("input[name='bookcheckbox']");
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
		url : "selectedBook.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			bookids : ids,
			type : 8
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '1') {
				MessageWindow.showMess("   ");

			}
		}
	});
}
