var selectedPage;
/**
 * 打开页面的同时刷新左侧树
 */
$(document).ready(function() {
			refreshTree();
		});
/**
 * 刷新左侧树
 */
function refreshTree() {
	var setting = {
		view : {
			showLine : true,
			selectedMulti : false,
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "pageid",
				pIdKey : "parentpageid"
			},
			key : {
				id : "pageid",
				pId : "parentpageid",
				name : "pagename",
				open : false,
				enable : true
			}
		},
		callback : {
			onClick : onClick1
		}
	};

	function onClick1(event, treeId, treeNode, clickFlag) {
		var url1 = "getpageinfo.do?pageid=" + treeNode.pageid;
		selectedPage = treeNode;
		document.getElementById("pageinfo").src = url1;
		document.getElementById('pageinfo').contentWindow.refreshPage();
	};
	var zTree;
	var treeNodes1;
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
								treeNodes1 = data.treeNodes;
							}
						});

				$.fn.zTree.init($("#pagestree"), setting, treeNodes1);
			});

}
/**
 *  删除页面
 */		
function  deletepage(){ 
  var pageid=document.getElementById('pageinfo').contentWindow.getCurrentPageId(); 
  var ajsxparams={
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url:"deletepage.do",
				data:{pageid:pageid},
				error : function(XMLHttpRequest, textStatus, errorThrown) {// 请求失败处理函数
						console.log(errorThrown);
				},
				success : function(data) { // 请求成功后处理函数。
					if(data.success==1){					
					 refreshTree();
					}else{
					 alert(data.errorMess);
					}
				}
	};
  $.ajax(ajsxparams);
 }
$(function() {
	var chnlname = $("#chnlname"), channeldisplaytype = $("#channeldisplaytype"), channeldoccount = $("#channeldoccount"), variablename = $("#variablename"), allFields = $([])
			.add(chnlname).add(channeldisplaytype).add(channeldoccount)
			.add(variablename), tips = $(".validateTips");
	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
					tips.removeClass("ui-state-highlight", 1500);
				}, 500);
	}
	function checkLength(o, n, min, max) {
		if (o.val().length > max || o.val().length < min) {
			o.addClass("ui-state-error");
			updateTips("Length of " + n + " must be between " + min + " and "
					+ max + ".");
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
	$("#dialog-form").dialog({
		autoOpen : false,
		height : 400,
		width : 500,
		modal : true,
		buttons : {
			"保存" : function() {
				var thisObject = $(this);
				allFields.removeClass("ui-state-error");
				var bValid = true;
				if (selectedChanel == null) {
					updateTips("请选择栏目！");
					bValid = false;
				}
				if (chnlname == null || chnlname == '') {
					updateTips("请选择栏目！");
					bValid = false;
				}
				bValid = bValid
						&& checkRegexp(channeldoccount, /^[1-9]\d*$/i,
								"栏目展现个数必须是数字");
				bValid = bValid && checkLength(variablename, "填写要素名字", 1, 80);
				bValid = bValid
						&& checkRegexp(variablename,
								/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/i,
								"栏目名字必须是中英文,下划线或数字组成.");
				if (bValid) {
					var pageObj = $("#pageid");
					pageObj.attr("value", selectedPage.pageid);
					pageObj = $("#pagename");
					pageObj.attr("value", selectedPage.pagename);
					// ajax 方式提交数据
					$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						dataType : "json",
						url : "addchanneltopage.do",// 请求的action路径
						data : {
							pageid : $("#pageid")[0].value,
							channelid : $("#channelid")[0].value,
							pagename : $("#pagename")[0].value,
							pageid : $("#pageid")[0].value,
							chnlname : $("#chnlname")[0].value,
							channeldisplaytype : $("#channeldisplaytype")[0].value,
							channeldoccount : $("#channeldoccount")[0].value,
							variablename : $("#variablename")[0].value,
							chnldesc : $("#chnldesc")[0].value
						},
						error : function() {// 请求失败处理函数
							alert('请求失败');
						},
						success : function(data) { // 请求成功后处理函数。
							// 数据保存成功以后刷新列表
							if (data.success == 1) {
								document.getElementById('pageinfo').contentWindow
										.refreshPage();
								thisObject.dialog("close");
							} else {
								updateTips(data.errorMess);
							}

						}
					});

				}
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			allFields.val("").removeClass("ui-state-error");
		}
	});
	$("#create-user").button().click(function() {
				$("#dialog-form").dialog("open");
			});
});
/**
 * 显示栏目树用于跟页面关联
 * 
 * @type
 */
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "channelid",
			pIdKey : "parentid"
		},
		key : {
			id : "channelid",
			pId : "parentid",
			name : "chnldesc",
			open : false,
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
/**
 * 所有栏目集合
 * 
 * @type
 */
var zNodes1;
/**
 * 被选择的栏目
 * 
 * @type
 */
var selectedChanel;
/**
 * 
 * @param {}
 *            treeId
 * @param {}
 *            treeNode
 * @return {}
 */
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("请选择栏目...");
	return check;
}
/**
 * 当双击时候
 * 
 * @param {}
 *            e
 * @param {}
 *            treeId
 * @param {}
 *            treeNode
 */
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("cityTree"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
				return a.id - b.id;
			});
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].channelid + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#chnlname");
	// 从列表中获取栏目信息
	for (var i in zNodes1) {
		if (zNodes1[i].channelid == v) {
			selectedChanel = zNodes1[i];
		}

	}
	cityObj.attr("value", selectedChanel.chnlname);
	cityObj = $("#chnldesc");
	cityObj.attr("value", selectedChanel.chnldesc);
	cityObj = $("#channelid");
	cityObj.attr("value", selectedChanel.channelid);
}
/**
 * 显示栏目树
 */
function showChannelTree() {
	var cityObj = $("#chnlname");
	var cityOffset = $("#chnlname").offset();
	$("#menuContent").css({
				// left : cityOffset.left + "px",
				// top : cityOffset.top + cityObj.outerHeight() + "px"
				left : "10px",
				top : "70px"
			}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
/**
 * 隐藏栏目树
 */
function hideTree() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target)
			.parents("#menuContent").length > 0)) {
		hideTree();
	}
}
/**
 * 通过局部刷新获取所有栏目信息
 */
$(document).ready(function() {
			$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						dataType : "json",
						url : "gechannels.do",// 请求的action路径
						error : function() {// 请求失败处理函数
							alert('请求失败');
						},
						success : function(data) { // 请求成功后处理函数。
							zNodes1 = data.treeNodes;
							$.fn.zTree.init($("#cityTree"), setting, zNodes1);
						}
					});
			$.fn.zTree.init($("#cityTree"), setting, zNodes1);
		});

