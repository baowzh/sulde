/**
 * 操作类型
 * 
 * @type Number
 */
var opertype = 1;
/**
 * 保存编辑好的页面信息
 */
function savepageinfo() {
	if (opertype == 1) {
		document.getElementById("manageForm").action = "addpage.do";
	} else {
		document.getElementById("manageForm").action = "updatepage.do";
	}

	document.getElementById("manageForm").submit();
}
/**
 * 新增页面配置信息
 */
function addpage() {
	opertype = 1;
	document.getElementById("pagename").value = "";
	document.getElementById("pagename").disabled = "";
	// 把对应的控件改为selected
	$(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "gepages.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { // 请求成功后处理函数。
				// 把 parentpageidtd 列内部设置为select 元素
				$("#parentpageid").remove();
				$("#parentpageidtd")
						.append("<select width=\"160px\" name=\"parentpageid\" id=\"parentpageid\"><option value=\"0\">请选择</option></select>");
				for (var i in data.treeNodes) {
					$("#parentpageid").append("<option value=\""
							+ data.treeNodes[i].pageid + "\">"
							+ data.treeNodes[i].pagename + "</option>");
				}
				$("#savebutton")[0].style.display = "block";
			}
		});
	});
	$(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "gettemplates.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { // 请求成功后处理函数。
				// 把 parentpageidtd 列内部设置为select 元素
				$("#showtemplateid").remove();
				$("#showtemplateidtd")
						.append("<select width=\"160px\" name=\"showtemplateid\" id=\"showtemplateid\"><option value=\"0\">请选择</option></select>");
				for (var i in data.rows) {
					$("#showtemplateid").append("<option value=\""
							+ data.rows[i].tempid + "\">"
							+ data.rows[i].tempdesc + "</option>");
				}
			}
		});
	});
	document.getElementById("comment").value = "";
	document.getElementById("comment").disabled = "";
}
/**
 * 修改页面配置信息
 */
function modifypageinfo() {
	opertype = 2;
	document.getElementById("pagename").disabled = "";
	// 把对应的控件改为selected
	$(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "gepages.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { // 请求成功后处理函数。
				// 把 parentpageidtd 列内部设置为select 元素
				var v_parentid = $("#parentpageid")[0].value;
				$("#parentpageid").remove();
				$("#parentpageidtd")
						.append("<select width=\"160px\" name=\"parentpageid\" id=\"parentpageid\"><option value=\"0\">请选择</option></select>");
				for (var i in data.treeNodes) {
					if (v_parentid == data.treeNodes[i].pageid) {
						$("#parentpageid").append("<option value=\""
								+ data.treeNodes[i].pageid
								+ "\" selected=\"true\">"
								+ data.treeNodes[i].pagename + "</option>");
					} else {
						$("#parentpageid").append("<option value=\""
								+ data.treeNodes[i].pageid + "\">"
								+ data.treeNodes[i].pagename + "</option>");
					}
				}
				$("#savebutton")[0].style.display = "block";
			}
		});
	});

	$(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "gettemplates.do",// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { // 请求成功后处理函数。
				// 把 showtemplateidtd 列内部设置为select 元素
				var v_showtemplateid = $("#showtemplateid")[0].value;
				$("#showtemplateid").remove();
				$("#showtemplateidtd")
						.append("<select width=\"160px\" name=\"showtemplateid\" id=\"showtemplateid\"><option value=\"0\">请选择</option></select>");
				for (var i in data.rows) {
					if (v_showtemplateid == data.rows[i].tempname) {
						$("#showtemplateid").append("<option value=\""
								+ data.rows[i].tempid + "\" selected=\"true\">"
								+ data.rows[i].tempdesc + "</option>");
					} else {
						$("#showtemplateid").append("<option value=\""
								+ data.rows[i].tempid + "\">"
								+ data.rows[i].tempdesc + "</option>");
					}
				}
			}
		});
	});
	document.getElementById("comment").disabled = "";
}
/**
 * 获取当前页面id
 * 
 * @return {}
 */
function getCurrentPageId() {
	return $("#pageid")[0].value;
}
/**
 * 获取当前页面中的栏目列表
 */
function getRelatedChannels() {
	var pageid = this.getCurrentPageId();
	var grid = {
		ajaxGridOptions : {
			type : "POST"
		},
		serializeGridData : function(postdata) {
			postdata.page = 1;
			return postdata;
		},
		url : 'getrelatedchannels.do',
		datatype : "json",
		colNames : ['77', '66', '栏目名称', '栏目描述', '显示明细', '列表名称'],
		colModel : [{
					name : 'channelid',
					index : 'channelid',
					width : 10,
					hidden : true
				}, {
					name : 'pageid',
					index : 'pageid',
					width : 10,
					hidden : true
				}, {
					name : 'pagename',
					index : 'pagename',
					width : 40
				}, {
					name : 'chnlname',
					index : 'chnlname',
					width : 100
				}, {
					name : 'channeldisplaytype',
					index : 'channeldisplaytype',
					width : 30
				}, {
					name : 'channeldoccount',
					index : 'channeldoccount',
					width : 30
				}],
		// rowNum : 10,
		width : 620,
		// rowList : [10, 20, 30],
		// pager : '#pjqgajax',
		sortname : 'invdate',
		viewrecords : true,
		sortorder : "desc",
		multiselect : true
	};
	grid.url = grid.url + '?pageid=' + pageid;
	jQuery("#jqgajax").jqGrid(grid);
}
$(document).ready($(function() {
			getRelatedChannels();
		}));
function deletechannel() {
	var selectedId = $("#jqgajax").jqGrid("getGridParam", "selrow");
	var rowData = $("#jqgajax").jqGrid("getRowData", selectedId);
	var ajaxParams = {
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "deletechannelfrompage.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
			refreshPage();
		}
	};
	ajaxParams.url = ajaxParams.url + "?pageid=" + rowData.pageid
			+ "&channelid=" + rowData.channelid + "&displayType="
			+ rowData.channeldisplaytype;
	$.ajax(ajaxParams);

}
function refreshPage() {
	// 此处可以添加对查询数据的合法验证
	var pageid = this.getCurrentPageId();
	$("#jqgajax").jqGrid('setGridParam', {
				datatype : 'json',
				postData : {
					'pageid' : pageid
				}, // 发送数据
				page : 1
			}).trigger("reloadGrid"); // 重新载入

}
