/**
 * 设置查询条件的选项
 */
$(document).ready(
		function() {
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "gechannels.do",// 请求的action路径
				data : {
					menutype : 1
				},
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					var options = "";
					for ( var i in data.treeNodes) {
						options = options + "<option value=\""
								+ data.treeNodes[i].channelid + "\">"
								+ data.treeNodes[i].chnlname + "</option>";
					}
					$('#channel').prepend(options);
				}
			});
		});
/**
 * 清空表单
 */
var clearQueryForm = function() {
	$("#channel").val("#");
	$("#status").val('0');
	$("#authorname").val("");
	$("#strcrtime").val("");
	$("#endcrtime").val("");
	$("#doctitle").val("");
};
/**
 * 查询内容
 */
var search = function() {

	$("#pageindex").val("");
	$("#Form").submit();
};

var gotoPage = function(pageindex) {
	$("#pageindex").val(pageindex);
	$("#Form").submit();
};
/**
 * 全选或者是取消选择
 * 
 * @param {}
 *            checkbox
 */
var selectAllDoc = function(checkbox) {
	var isselect = false;
	if (checkbox.checked) {
		isselect = true;
	} else {
		isselect = false;
	}
	var boxes = $("input[name='docnamecheckbox']");
	boxes.each(function() {
		$(this).attr("checked", isselect);
	});
}
/**
 * 审核
 */
var checkdoc = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var ids = "";
	boxes.each(function() {
		if (this.checked) {
			ids = ids + $(this).attr("id") + ',';
		}
	});
	if (ids != "") {
		// $("#docids").val(docids);
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "checkdocument.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			data : {
				docids : ids
			},
			success : function(data) { // 请求成功后处理函数。
				if (data.success == '1') {
					MessageWindow.showMess("    ", search());

				}
			}
		});
	} else {
		MessageWindow.showMess("     ");
	}
};
/**
 * 删除
 */
var deletedoc = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var ids = "";
	boxes.each(function() {
		if (this.checked) {
			ids = ids + $(this).attr("id") + ',';
		}
	});
	if (ids != '') {
		showConfirmMess("       ", function() {
			if (this.getValue() == true) {
				deldoc(ids);
			}
		});
	} else {

		MessageWindow.showMess("    ");
	}

};
var deldoc = function(ids) {

};
/**
 * 选入优秀作品
 */
var asTopWriting = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var checkedcount = 0;
	var selecteddocid = "";
	var seldoctitile = "";
	boxes.each(function() {
		if (this.checked) {
			checkedcount++;
			selecteddocid = this.id;
			var docid = "#title" + this.id;
			seldoctitile = $(docid).val();
			// alert(seldoctitile);
		}
	});
	if (checkedcount > 1) {
		MessageWindow.showMess("      ");
		return;
	} else if (checkedcount == 0) {
		MessageWindow.showMess("     ");
		return;
	}
	$("#title").val(seldoctitile);
	$("#docid").val(selecteddocid);
	$("#astopwriting").dialog();
};
var addtotopwriting = function() {
	var playimg = $("#playimg").val();
	if (playimg == null || playimg == '') {
		MessageWindow.showMess("   ");
		return;
	}
	var playindex = $("#playindex").val();
	if (playindex == null || playindex == '') {
		MessageWindow.showMess("    ");
		return;
	}
	$("#astopwritingform").submit();
};
