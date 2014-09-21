/**
 * 打开设置单选题对话框
 */
var opensinquestionwin = function() {
	// 
	$("#questiontype").val("1");
	$("#singquestionse").dialog({
		height : 400,
		width : 600,
		resizable : false,
		modal : true

	});
};
/**
 * 打开多选题设置框
 */
var openmultquestionwin = function() {
	$("#questiontype").val("2");
	$("#singquestionse").dialog({
		height : 400,
		width : 600,
		resizable : false,
		modal : true

	});
};
/**
 * 打开回复框
 */
var openquestionwin = function() {
	$("#questiontype").val("3");
	$("#textquestionse").dialog({
		height : 400,
		width : 600,
		resizable : false,
		modal : true

	});
};

/**
 * 保存问卷调差
 */
var savevote = function() {
	var desc = $("#commentdiv").text();
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "savevote.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			voteid : $("#voteid").val(),
			topic : desc
		},
		success : function(data) { // 临时保存数据并把结果写入界面上的表格
			if (data.success == '1') {
				MessageWindow.showMess("    ");
			} else {
				MessageWindow.showMess("      ");
			}
		}
	});

}
/**
 * 保存题目
 */
var addquestion = function() {
	var desc = $("[name='questiondesc']").val();
	var type = $("#questiontype").val();
	if (type == 3) {
		desc = $("#textesc").val();
	}
	var questionsels = "";
	var seles = $("input[name='sele']").each(function(i, o) {
		questionsels = questionsels + $(o).val() + "##";
	});
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "addquestion.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				data : {
					voteid : $("#voteid").val(),
					questiontype : type,
					questiondesc : desc,
					selections : questionsels
				},
				success : function(data) { // 临时保存数据并把结果写入界面上的表格
					var trhtml = "";
					for (i in data.questions) {
						var inputtype = "";
						if (type == 1) {
							inputtype = "radio"
						} else if (type == 2) {
							inputtype = "checkbox"
						} else {

						}
						var trname = "name" + data.questions[i].questionid;
						trhtml = trhtml
								+ '<tr name=\"'
								+ trname
								+ '\">'
								+ '<td class=\"title\" style=\"height: 100%;\">'
								+ data.questions[i].questiondesc + '</td></tr>';
						for (j in data.questions[i].questionValues) {
							var name = "";
							if (type == 1) {
								name = "sing"
										+ data.questions[i].questionValues[j].questionid;
							} else if (type == 2) {
								name = "multi"
										+ data.questions[i].questionValues[j].questionid;
							}
							if (type == 1 || type == 2) {
								trhtml = trhtml
										+ '<tr name=\"'
										+ trname
										+ '\"><td><input type=\"'
										+ inputtype
										+ '\" name=\"'
										+ name
										+ '\" id=\"'
										+ data.questions[i].questionValues[j].answerid
										+ '\">'
										+ data.questions[i].questionValues[j].answername
										+ '</input></td></tr>';
							} else {

								trhtml = trhtml
										+ '<tr name=\"'
										+ trname
										+ '\"><td><div class=\"voteDesc\" contentEditable=\"true\" style=\"width: 30px; height: 600px;\"></div></td></tr>';
							}
						}
						trhtml = trhtml
								+ '<tr name=\"'
								+ trname
								+ '\">'
								+ "<td><a>      </a><a>     </></td></tr>";
					}
					$("#questionlist").append($(trhtml));

					if (type == 1 || type == 2) {
						$("#singquestionse").dialog("close");
					} else {
						$("#textquestionse").dialog("close");
					}
				}
			});

};
/**
 * 删除问题
 */
var deletequestion = function(questionid, questiontype) {
	// 删除问卷调调查问题
	showConfirmMess("      ", function() {
		if (this.getValue() == true) {
			delquestion(questionid, questiontype);
		}
	});
	//
};
var delquestion = function(questionid, questiontype) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "deltevotequestion.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			voteid : $("#voteid").val(),
			questionid : questionid
		},
		success : function(data) {
			if (data.success == 1) {
				MessageWindow.showMess('    ');
				var selectid = "name" + questionid;
				var selectstr = 'tr[name=\'' + selectid + '\']';
				var seles = $(selectstr);
				seles.each(function(i, o) {
					// alert($(o).html());
					$(o).remove();
				});
			} else {
				// alert(data.mess);
				MessageWindow.showMess(data.mess);
			}
		}
	});
}
/**
 * 删除问题选项
 */
var deleteselection = function(questionid, questiontype) {
	//
	var selectid = "";
	if (questiontype == 1) {
		selectid = selectid + "sing" + questionid;
	} else if (questiontype == 2) {
		selectid = selectid + "multi" + questionid;
	}
	var singglequestionrel = '';
	var selectstr = 'input[name=\'' + selectid + '\']';
	var seles = $(selectstr);
	seles.each(function(i, o) {
		if (o.checked) {
			singglequestionrel = singglequestionrel + $(o).attr("id");
		}
	});
	//
	if (singglequestionrel != '') {
		showConfirmMess("     ", function() {
			if (this.getValue() == true) {
				delselection(questionid, singglequestionrel);
			}
		});
	} else {
		MessageWindow.showMess('    ');
	}

};
var delselection = function(questionid, singglequestionrel) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "delvotequestionsel.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			questionid : questionid,
			answerid : singglequestionrel
		},
		success : function(data) {
			if (data.success == 1) {
				var td = $("#" + singglequestionrel).parent();
				$(td).parent().remove();
			} else {
				MessageWindow.showMess(data.mess);
			}

		}
	});

}