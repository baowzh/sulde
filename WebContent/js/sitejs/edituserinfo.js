var dosubmit = function() {
	// 全部通过则保存用户信息
	var bolgname = $("#bolgname").val();
	if (bolgname == null || bolgname == '') {
		MessageWindow.showMess("'  '      ");
		return;
	}
	var firstname = $("#firstname").val();
	if (firstname == null || firstname == '') {
		MessageWindow.showMess("''      ");
		return;
	}
	var artname = $("#artname").val();
	if (artname == null || artname == '') {
		MessageWindow.showMess("''      ");
		return;
	}
	/*
	 * var birthday = $("#birthday")[0]; if (birthday.value == null ||
	 * birthday.value == '') { MessageWindow.showMess("' ' 
	 *  "); return; }
	 */
	var sex = $("#sex").val();
	if (sex == null || sex == '') {
		MessageWindow.showMess("''      ");
		return;
	}
	/*
	 * var unit = $("#unit")[0]; if (unit.value == null || unit.value == '') {
	 * MessageWindow.showMess("''   "); return; }
	 */
	/*
	 * var province = $("#province")[0]; if (province.value == null ||
	 * province.value == '') { MessageWindow.showMess("''  
	 * "); return; } var nowprovince = $("#nowprovince")[0]; if
	 * (nowprovince.value == null || nowprovince.value == '') {
	 * MessageWindow.showMess("'  '   "); return; }
	 * var phone = $("#phone")[0]; if (phone.value == null || phone.value == '') {
	 * MessageWindow.showMess("' '   "); return; }
	 * var qq = $("#qq")[0]; if (qq.value == null || qq.value == '') {
	 * MessageWindow.showMess("'qq'   "); return; } var phone =
	 * $("#phone")[0]; if (phone.value == null || phone.value == '') {
	 * MessageWindow.showMess("' '   "); return; }
	 */
	var varifycode = $("#varifycode").val();
	if (varifycode == null || varifycode == '') {
		MessageWindow.showMess("' '      ");
		return;
	}
	$("#userinfoform").submit(); // 表单提交时乱码
	// 用局部刷新的方式提交
};
/**
 * 监听录入框输入事件避免输入过大的字符串
 * 
 * @param {}
 *            obj
 */
var onChange = function(mess, obj, length) {
	if (obj.value.length > length) {
		MessageWindow
				.showMess(mess
						+ '             ');
		obj.value = '';
		return;
	}

};
/**
 * 加载子集区划
 */
var loadChildDistrict = function(selid, type) {
	var selectedcode = $("#" + selid).val();
	var optionhtml = '<option value="99"></option>';
	for (district in districtsdata.districts) {
		if (selectedcode == districtsdata.districts[district].parentcode) {
			optionhtml = optionhtml + '<option value="'
					+ districtsdata.districts[district].districtcode + '">'
					+ districtsdata.districts[district].districtname
					+ '</option>'
		}
	}
	if (type == 1) {
		$("#hsien").html(optionhtml);

	} else {
		$("#nowhsien").html(optionhtml);
	}

};