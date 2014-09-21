/**
 * 审核问卷调查
 */
var checkvote = function() {

};
/**
 * 修改问卷调查
 */
var updvote = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var checkedcount = 0;
	var selecteddocid = "";
	boxes.each(function() {
		if (this.checked) {
			checkedcount++;
			selecteddocid = this.id;
		}
	});
	if (checkedcount > 1) {
		MessageWindow.showMess('        ');
		return;
	}
	$("#voteid").val(selecteddocid);
	$("#actionform").attr("action", "updvote.do")
	$("#actionform").submit();
};

/**
 * 参与调查
 */
var join = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var checkedcount = 0;
	var selecteddocid = "";
	boxes.each(function() {
		if (this.checked) {
			checkedcount++;
			selecteddocid = this.id;
		}
	});
	if (checkedcount > 1) {
		MessageWindow.showMess('        ');
		return;
	}
	$("#voteid").val(selecteddocid);
	$("#actionform").attr("action", "joinvote.do")
	$("#actionform").submit();
};
/**
 * 删除问卷调查
 */
var deletevote = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var checkedcount = 0;
	var selecteddocid = "";
	boxes.each(function() {
		if (this.checked) {
			checkedcount++;
			selecteddocid = this.id;
		}
	});
	if (checkedcount > 1) {
		MessageWindow.showMess('        ');
		return;
	}
	// 先校验是否可以删除

	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "checkvotestatus.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					voteid : $("#voteid").val()
				},
				success : function(data) { // 临时保存数据并把结果写入界面上的表格
					if (data.checkresult.joinercount > 0
							|| data.checkresult.status == 2) {
						MessageWindow
								.showMess('              ');
					} else {
						$("#voteid").val(selecteddocid);
						$("#actionform").attr("action", "delvote.do")
						$("#actionform").submit();
					}
				}
			});

};
/**
 * 发行问卷调查
 */
var viewresult = function() {
	var boxes = $("input[name='docnamecheckbox']");
	var checkedcount = 0;
	var selecteddocid = "";
	boxes.each(function() {
		if (this.checked) {
			checkedcount++;
			selecteddocid = this.id;
		}
	});
	if (checkedcount > 1) {
		MessageWindow.showMess('        ');
		return;
	}
	$("#voteid").val(selecteddocid);
	$("#actionform").attr("action", "viewvoteresult.do")
	$("#actionform").submit();

};