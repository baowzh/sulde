$(document).ready(function() {
	$("#jqGrid").jqGrid({
		url : 'getautoresponselist.do',
		mtype : "GET",
		rownumbers : true,
		rownumWidth : 25,
		rowNum : 0,
		multiselect : true,
		contentType : 'application/json',
		datatype : 'json',
		jsonReader : {
			total : "total",
			page : "page",
			records : "records",
			rows : "rows",
			repeatitems : false
		},
		page : 1,
		height : 230,
		width : 998,
		colModel : [ {
			label : 'id',
			name : 'id',
			width : 45,
			key : true
		}, {
			label : '消息类型',
			name : 'msgtype',
			formatter : formatMsgType,
			width : 75,
			key : true,
			editable : true,
			editrules : {
				required : true
			},
			edittype : "select",
			editoptions : {
				value : "1:图文消息;0:文本消息"
			}

		}, {
			label : '关键字',
			name : 'keyword',
			width : 90,
			editable : true,
			editrules : {
				required : true
			}
		}, {
			label : '创建时间',
			name : 'addtime',
			width : 100,
			editable : true,
			editrules : {
				required : true
			},
			edittype : "text",
			editoptions : {
				dataInit : function(element) {
					$(element).datepicker({
						id : 'orderDate_datePicker',
						dateFormat : 'yy-mm-dd',
						maxDate : new Date(2020, 0, 1),
						showOn : 'focus'
					});
				}
			}
		}, {
			label : '是否默认消息',
			name : 'defaultmess',
			formatter : formatRating,
			width : 80,
			editable : true,
			editrules : {
				required : true
			},
			edittype : "select",
			editoptions : {
				value : "1:是;0:否"
			}
		} ],
		viewrecords : true,
		caption : "关键字列表",
		pager : "#jqGridPager",
		loadComplete : function() {
			var defaultrow = $('#resid').val();
			if (defaultrow != null && defaultrow != '') {
				jQuery('#jqGrid').jqGrid('setSelection', $('#resid').val());
			}
		},
		onSelectRow : function() {
			var grid = $("#jqGrid");
			var rowKey = grid.jqGrid('getGridParam', "selrow");
			loadresdoc(rowKey);
		}
	});
	$('#jqGrid').navGrid('#jqGridPager', {
		edit : true,
		add : true,
		del : true,
		search : false,
		refresh : false,
		view : false,
		position : "left",
		cloneToTop : false
	}, {
		editCaption : "修改关键字",
		recreateForm : true,
		checkOnUpdate : true,
		checkOnSubmit : true,
		closeAfterEdit : true,
		afterSubmit : function(response, postdata) {
			var res = $.parseJSON(response.responseText);
			if(res.status==1){
				alert('成功');
				return true;
			}else{
				alert( res.mess);
				return true;
			}
		},
		height : 'auto',
		width : 450,
		url : "updAutoresponse.do"
	},
	// options for the Add Dialog
	{
		addCaption : "增加关键字",
		closeAfterAdd : true,
		recreateForm : true,
		checkOnSubmit : true,
		afterSubmit : function(response, postdata) {
			var res = $.parseJSON(response.responseText);
			if(res.status==1){
				alert('成功');
				return true;
			}else{
				alert( res.mess);
				return true;
			}
		},
//		errorTextFormat : function(data) {
//			return 'Error: ' + data.responseText
//		},
		height : 'auto',
		width : 450,
		url : "addAutoresponse.do"
	}, {
		url : "delAutoresponse.do"
	}, {
		errorTextFormat : function(data) {
			return 'Error: ' + data.mess
		}
	});
	// 选择文件table
	$("#seljqGrid").jqGrid({
		url : 'queryrecentdoc.do',
		mtype : "GET",
		rownumbers : true,
		rownumWidth : 25,
		rowNum : 0,
		// multiselect : true,
		contentType : 'application/json',
		datatype : 'json',
		jsonReader : {
			total : "total",
			page : "page",
			records : "records",
			rows : "rows",
			repeatitems : false
		},
		page : 1,
		height : 340,
		width : 870,
		colModel : [ {
			label : 'docid',
			name : 'docid',
			width : 45,
			key : true
		}, {
			label : '文章标题',
			name : 'doctitle',
			width : 75,
			key : true
		}, {
			label : '文章作者',
			name : 'docauthor',
			width : 90,
			editable : false
		}, {
			label : '创建时间',
			name : 'docreltime',
			width : 100,
			editable : false
		} ],
		viewrecords : true,
		caption : "文章列表",
		pager : "#seljqGridPager"
	});
	//
	$("#docjqGrid").jqGrid({
		url : 'queryresdoc.do',
		mtype : "GET",
		rownumbers : true,
		rownumWidth : 25,
		rowNum : 0,
		multiselect : true,
		contentType : 'application/json',
		datatype : 'json',
		postData : {
			autoresid : resid
		},
		jsonReader : {
			total : "total",
			page : "page",
			records : "records",
			rows : "rows",
			repeatitems : false
		},
		page : 1,
		height : 222,
		width : 950,
		colModel : [ {
			label : 'id',
			name : 'id',
			width : 45,
			key : true
		}, {
			label : 'responseid',
			name : 'responseid',
			width : 45,
			key : false
		},

		{
			label : 'docid',
			name : 'docid',
			width : 75,
			key : false

		}, {
			label : 'mgltitle',
			name : 'mgltitle',
			width : 75,
			key : false

		}, {
			label : 'doctitle',
			name : 'doctitle',
			width : 90
		}, {
			label : 'docimg',
			name : 'docimg',
			width : 100
		} ],
		viewrecords : true,
		caption : "关联文章",
		pager : "#docjqGridPager"
	});

});

var formatMsgType = function(cellValue, options, rowObject) {
	if (cellValue == 1) {
		return "图文消息";
	} else {
		return "文本消息";
	}
}

var formatRating = function(cellValue, options, rowObject) {
	if (cellValue == 1) {
		return "是";
	} else {
		return "否";
	}
};
var addwechatdoc = function() {
	var grid = $("#jqGrid");
	var rowKey = grid.jqGrid('getGridParam', "selrow");
	if (rowKey == null || rowKey == '') {
		alert('请选择自动回复设置!');
		return;
	}
	var rowData = grid.jqGrid('getRowData', rowKey);
	if (rowData.msgtype == '文本消息') {
		alert('文本消息不能维护关联文章!');
		return;
	}
	$('#responseid').val(rowKey);
	$("#adddocdiv").dialog({
		height : 420,
		width : 390,
		resizable : true,
		model : false
	});
};
var delwechatdoc = function() {
	var grid = $("#docjqGrid");
	var rowKey = grid.jqGrid('getGridParam', "selrow");
	var rowData = grid.jqGrid('getRowData', rowKey);
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "delWechatDoc.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		data : {
			docid : rowData.docid,
			autoresid : rowData.responseid
		},
		success : function(data) {
			loadresdoc(rowData.responseid);
		}
	});

};
var openseldiv = function() {
	$("#seldoctab").dialog({
		height : 510,
		width : 910,
		resizable : true,
		model : false
	});

};
var setdocidandtitle = function() {
	var grid = $("#seljqGrid");
	var rowKey = grid.jqGrid('getGridParam', "selrow");
	var rowData = grid.jqGrid('getRowData', rowKey);
	$('#seldoctitle').text(rowData.doctitle);
	$('#docid').val(rowData.docid);
	$("#seldoctab").dialog("close");
};
var uploadwechatdoc = function() {
	document.getElementById("adddocform").submit();
};
var loadresdoc = function(resid) {
	//
	$("#docjqGrid").jqGrid('setGridParam', {
		datatype : 'json',
		postData : {
			'autoresid' : resid
		}, // 发送数据
		page : 1
	}).trigger("reloadGrid"); // 重新载入
	//
}