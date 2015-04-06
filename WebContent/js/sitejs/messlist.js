var switchdiv = function(currentdivid, nextdivid) {
	var display = $("#" + currentdivid).css("display");
	if (display != 'none') {
		$("#" + currentdivid).css("display", "none");
		$("#" + nextdivid).css("display", "block");
	} else {
		$("#" + currentdivid).css("display", "block");
		$("#" + nextdivid).css("display", "none");
	}
};
var setHtml = function(divid, html) {
	$("#" + divid).html("<p>" + html + "</p>");
};
/**
 * 
 */
var delMessage = function(id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "delMessage.do",// 请求的action路径
		data : {
			messid : id
		},
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == '1') {
				MessageWindow.showMess("  ");
				$('#xldgurg_' + id).remove();
			}
		}
	});
};
/**
 * 
 */
var readmess = function(listdivid, detailid, messid) {
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "readmessage.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					messageid : messid
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.success == '1') {
						var content = data.messvalue.contenthtml
								+ '<div class="mnlist" style="float:left;"><a style="color:#f00;" href="javascript:backlist(\''+listdivid+'\',\''+detailid+'\');"> </a><div>';
						$("#" + detailid).html(content);
						$('#' + listdivid).css('display', 'none');
						$('#' + detailid).css('display', 'block');

					} else if (data.success == '0') {
						// alert('     ');
						MessageWindow
								.showMess('        ');
					}
				}
			});

};
var backlist=function(listdivid,detailid){
	$('#' + listdivid).css('display', 'block');
	$('#' + detailid).css('display', 'none');
	
};
/**
 * 
 */
var readcomm=function(docid){
	parent.window.location.href='getuserdocdetail.do?docid='+docid;
}