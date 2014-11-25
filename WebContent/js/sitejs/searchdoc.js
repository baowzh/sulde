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
					type : 2
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

