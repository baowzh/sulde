var curMenu = null, zTree_Menu = null;
var setting = {
	view : {
		showLine : true,
		selectedMulti : false,
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "code",
			pIdKey : "parentcode"
		},
		key : {
			id : "code",
			pId : "parentcode",
			name : "name",
			open : false,
			enable : true
		}
	},
	callback : {
		onNodeCreated : this.onNodeCreated,
		beforeClick : this.beforeClick,
		onClick : this.onClick
	}
};
function beforeClick(treeId, node) {
	if (node.isParent) {
		if (node.level === 0) {
			var pNode = curMenu;
			while (pNode && pNode.level !== 0) {
				pNode = pNode.getParentNode();
			}
			if (pNode !== node) {
				zTree_Menu.expandNode(pNode, false);
			}

			var isOpen = false;
			for (var i = 0, l = node.children.length; i < l; i++) {
				if (node.children[i].open) {
					isOpen = true;
					break;
				}
			}
			if (isOpen) {
				zTree_Menu.expandNode(node, true);
				curMenu = node;
			} else {
				zTree_Menu.expandNode(node.children[0].isParent
								? node.children[0]
								: node, true);
				curMenu = node.children[0];
			}
		} else {
			alert(node.name);
			zTree_Menu.expandNode(node);
		}
	}
	return !node.isParent;
}
function onClick(e, treeId, node) {
	var src = document.getElementById("workarea").src;
	document.getElementById("workarea").src = node.pageurl;
}

$(document).ready(function() {
			//

			$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						dataType : "json",
						url : "getsitemanagermenus.do",// 请求的action路径
						data : {
							menutype : 1
						},
						error : function() {// 请求失败处理函数
							alert('请求失败');
						},
						success : function(data) { // 请求成功后处理函数。
							zNodes = data.treeNodes;
							// 设置默认节点

						}
					});

			//
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree_Menu.getNodeByParam("code","11");
			//curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
			zTree_Menu.selectNode(node);
			////var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
			var a=$("#"+node.tId + "_a") //
			a.addClass("cur");
		    onClick('','',node);
		});