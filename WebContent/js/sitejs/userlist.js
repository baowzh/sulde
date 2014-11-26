/**
 * 暂停用户使用
 */
var stopuser = function() {
	var boxes = $("input[name='selectbox']");
	var ids = "";
	boxes.each(function() {
		if (this.checked) {
			ids = ids + $(this).attr("id") + ',';
		}
	});
	if (ids != "") {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "stopuser.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			data : {
				userids : ids
			},
			success : function(data) { // 请求成功后处理函数。
				if (data.success == 'true') {
					MessageWindow.showMess("   ");
					$("#queryForm").submit();
					// 提示信息并刷新页面
				}
			}
		});
	} else {
		MessageWindow.showMess("      ");
	}
};
/**
 * 重启用户
 */
var reuseuser = function() {
	var boxes = $("input[name='selectbox']");
	var ids = "";
	boxes.each(function() {
		if (this.checked) {
			ids = ids + $(this).attr("id") + ',';
		}
	});
	if (ids != "") {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "reuseuser.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			data : {
				userids : ids
			},
			success : function(data) { // 请求成功后处理函数。
				if (data.success == 'true') {
					// 提示信息并刷新页面
					MessageWindow.showMess("      ");
					$("#queryForm").submit();
				}
			}
		});
	} else {
		MessageWindow
				.showMess("     ");
	}
};
/**
 * 删除用户
 */
var deleteuser = function() {
	var boxes = $("input[name='selectbox']");
	var ids = '';
	boxes.each(function() {
		if (this.checked) {
			ids = ids + $(this).attr("id") + ',';
		}
	});
	if (ids != '') {
		showConfirmMess("       ", function() {
			if (this.getValue() == true) {
				deluser(ids);
			}
		});

	} else {
		MessageWindow.showMess("     ");
	}
};
var deluser = function(ids) {

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "deleteuser.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			userids : ids
		},
		success : function(data) { // 请求成功后处理函数。
			if (data.success == 'true') {
				// 提示信息并刷新页面
				MessageWindow.showMess("");
				$("#queryForm").submit();
			}
		}
	});

}
/**
 * 根据查询条件查询用户
 */
var searchusers = function() {
	$("#queryForm").submit();
};
var paginguser = function(pageindex) {
	$("#pageindex").val(pageindex);
	$("#queryForm").submit();
};
/**
 * 清空查询表单
 */
var clearQueryForm = function() {
	$("#district").val("");
	$("#qx").val("");
	$("#username").val("");
	$("#strregtime").val("");
	$("#endregtime").val("");
};

/**
 * 加载子集区划
 */
var loadChildDistrict = function(selid, type) {
	var selectedcode = $("#" + selid).val();
	var optionhtml = '';
	for (district in districtsdata.districts) {
		if (selectedcode == districtsdata.districts[district].parentcode) {
			if (type == 1) {
				optionhtml = optionhtml
						+ '<div  class=\"mnlist\" style=\"text-indent: 0px; height: 120px;width:25px;\"><a onclick=\"javascript:changeSel(\''
						+ districtsdata.districts[district].districtcode
						+ '\',\''
						+ districtsdata.districts[district].districtname
						+ '\',\'qx\',\'hsienname\',\'hsiens\')\"	style=\"cursor: pointer\" >'
						+ districtsdata.districts[district].districtname
						+ '</a></div>';
			} else {
				optionhtml = optionhtml
						+ '<div  class=\"mnlist\" style=\"text-indent: 0px; height: 120px;width:25px;\"><a onclick=\"javascript:changeSel(\''
						+ districtsdata.districts[district].districtcode
						+ '\',\''
						+ districtsdata.districts[district].districtname
						+ '\',\'nowhsien\',\'nowhsienname\',\'nowhsiens\')\"	style=\"cursor: pointer\" >'
						+ districtsdata.districts[district].districtname
						+ '</a></div>';
			}
		}
	}
	if (type == 1) {
		$("#hsienlist").html('');
		$("#hsienlist").html(optionhtml);

	} else {
		$("#nowhsienlist").html('');
		$("#nowhsienlist").html(optionhtml);
	}

};
var showselpanel = function(show, divid) {
	
	if (show) {
		$("#" + divid).css("left", document.body.scrollLeft + x);
		$("#" + divid).css("top", document.body.scrollTop + y);
		$("#" + divid).show();
	} else {
		$("#" + divid).hide();
	}

};
var changeSel = function(code, name, codeid, nameid, divid) {
	$("#" + nameid).text(name);
	$("#" + codeid).val(code);
	$('#' + divid).hide();
}
var x = 0;
var y = 0;
$(document).ready($(function() {
	$(document).mousemove(function(event) {
		// alert(event);
		x = event.clientX;
		y = event.clientY;
	});
}));