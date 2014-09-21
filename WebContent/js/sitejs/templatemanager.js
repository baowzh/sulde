/**
 * 删除表格中的模板
 */
function deleteTemp() {
	var length = document.getElementsByName("checkbox").length;
	var deleteids = document.getElementById("deleteids");
	var checkCount = 0;
	for (var i = 0; i < length; i++) {
		if (document.getElementsByName("checkbox")[i].checked) {
			deleteids.value = deleteids.value
					+ document.getElementsByName("tempid")[i].value + "#";
			checkCount = checkCount + 1;
		}
	}
	if (checkCount == 0) {
		alert("请选择删除的模板!");
		return;
	}
	document.getElementById("manageForm").action = "deletetemplate.do";
	document.getElementById("manageForm").submit();
}

$(function() {
	var tempname = $("#tempname"), temptype = $("#temptype"), tempdesc = $("#tempdesc"), tempfile1 = $("#tempfile1"), allFields = $([])
			.add(tempname).add(temptype).add(tempdesc).add(tempfile1), tips = $(".validateTips");
	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
					tips.removeClass("ui-state-highlight", 1500);
				}, 500);
	}
	function checkLength(o, n, min, max) {
		if (o.val().length > max || o.val().length < min) {
			o.addClass("ui-state-error");
			updateTips(n + "的长度必须介于" + min + "至" + max + "字节.");
			return false;
		} else {
			return true;
		}
	}
	function checkRegexp(o, regexp, n) {
		if (!(regexp.test(o.val()))) {
			o.addClass("ui-state-error");
			updateTips(n);
			return false;
		} else {
			return true;
		}
	}
	$("#dialog-form").dialog(

	{
		autoOpen : false,
		height : 350,
		width : 400,
		modal : true,
		buttons : {
			"保存" : function() {
				var bValid = true;
				allFields.removeClass("ui-state-error");
				bValid = bValid && checkLength(tempname, "模板名称", 2, 80);
				bValid = bValid && checkLength(tempdesc, "模板描述", 2, 80);
				bValid = bValid
						&& checkRegexp(tempname,
								/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/i,
								"模板名字必须是中英文,下划线或数字组成.");
				bValid = bValid
						&& checkRegexp(tempdesc,
								/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/i,
								"模板显示名字必须是中英文,下划线或数字组成.");
			    
				bValid = bValid && checkLength(tempfile1, "模板文件", 1, 120);			
				if (bValid) {			
						$("#manageForm1")[0].action = "addtemplate.do";					
					$("#manageForm1").submit();
					$(this).dialog("close");
				};
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			allFields.val("").removeClass("ui-state-error");
		}
	});

});
/*
 * add channel
 */
function toTempAdd() {
	operType = 1;
	console.log("add template...");
	$('#dialog-form').dialog('open');
}
/*
 * $(document).ready(function() { jQuery("#jqgajax").jqGrid({ ajaxGridOptions : {
 * type : "POST" }, serializeGridData : function(postdata) { postdata.page = 1;
 * return postdata; }, url : 'gettemplates.do', datatype : "json", colNames :
 * ['模板名称', '模板描述', '模板类型'], colModel : [{ name : 'tempname', index :
 * 'tempname', width : 100 }, { name : 'tempdesc', index : 'tempdesc', width :
 * 100 }, { name : 'temptypestr', index : 'temptypestr', width : 100 }], //
 * rowNum : 10, width : 900, // rowList : [10, 20, 30], // pager : '#pjqgajax',
 * sortname : 'invdate', viewrecords : true, sortorder : "desc", multiselect :
 * true });
 * 
 * });
 */
