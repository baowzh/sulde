/**
 * 浏览文章
 */
var divwindow;
var viewdoc = function() {
	$("input[name='docid']").each(function(i, val) {
		if (val.checked) {
			$("#viewdocbutton")[0].href = "getuserdocdetail.do?docid="
					+ val.value;
			return true;
		}
	});
};
/**
 * 对文章进行分组
 * 
 * @return {Boolean}
 */
var groupting = function() {
	var docids = "";
	var channelids = "";
	// 被选择的文章
	$("input[name='docid']").each(function(i, val) {
				if (val.checked) {
					docids = docids + val.value + ",";
				}
				if (docids != '') {
					// $("#viewdocbutton")[0].href =
					// "getuserdocdetail.do?docid="
					// + val.value;
					// return true;
				}
			});
	// 被选择的栏目
	$("input[name='deschannel']").each(function(i, val) {
				var checkedChennelnum = 0;
				if (val.checked) {
					channelids = channelids + val.value + ",";
					checkedChennelnum++;
				}
				if (checkedChennelnum > 1) {
					alert("           ");
					return false;
				}
			});
	// 如果都选择了则进行分组
	if (docids.length == 0) {
		alert('        ');
		return false;
	}
	if (docids.length != 0 && channelids.length == 0) {
		alert('       ');
		return false;
	}
	if (docids.length == 0 && channelids.length == 0) {
		return false;
	}
	
	// 保存数据
	$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "groupinguserdoc.do",// 请求的action路径
				data : {
					docids : docids,
					channelid : channelids
				},
				error : function() {// 请求失败处理函数
					alert('         ');
				},
				success : function(data) { // 请求成功后处理函数。

				}
			});

};

/**
 * 获取所有栏目列表
 */
var getchannels = function() {
	$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "getChannel.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					// 获取所有栏目并在一个div中进行显示，显示效果为dialog似的

					// 写入并显示
					writedata(data);
					divwindow = new DivWindow('popup', 'popup_drag',
							'popup_exit', 'exitButton', '500', '700', 4);

				}
			});
};

var writedata = function(data) {
	var innerHTML = "";
	for (i in data.treeNodes) {// the channel
		// 给div popDetail添加子div元素
		innerHTML = innerHTML
				+ '<div  style=\"writing-mode: tb-lr;\"><input type=\"checkbox\" name=\"deschannel\" value=\"'
				+ data.treeNodes[i].channelid + '\"> '
				+ data.treeNodes[i].chnlname + '</div>'
	}
	$("#popDetail").html(innerHTML);
};
var okAction = function() {
	groupting();
	divwindow.exit();

};
var cancelAction = function() {

};
var paingAction=function(){
  	 	$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "pagingdata.do",// 请求的action路径
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					alert("测试分页")
				}
			});
};