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
	// document.getElementById("userinfoform").submit();
	$("#userinfoform").submit(); //表单提交时乱码
	// 用局部刷新的方式提交
	/*
	$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "edituserinfo.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			data : {
				bolgname : $("#bolgname").val(),
				firstname : $("#firstname").val(),
				artname : $("#artname").val(),
				birthday : $("#birthday").val(),
				sex : $("#sex").val(),
				unit : $("#unit").val(),
				province : $("#province").val(),
				nowprovince : $("#nowprovince").val(),
				phone : $("#phone").val(),
				qq : $("#qq").val(),
				email:$("#email").val(),
				hope:$("#hope").val(),
				belief:$("#belief").val(),
				idol:$("#idol").val(),
				maxim:$("#maxim").val(),
				music:$("#music").val(),
				book:$("#book").val(),
				singer:$("#singer").val()
			},
			success : function(data) { // 请求成功后处理函数。
				if (data.success == '1') {
					//window.location.href = "userheadimgedit.do";
					 $("#userinfoform")[0].submit();
				}
			}
		});*/

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