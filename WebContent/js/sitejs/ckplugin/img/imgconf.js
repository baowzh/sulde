/**
 * Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
/**
 * 保存用户当前点击的表情列表
 */
var htmlStr = "";
var selectedimgid = "";
$.ajax({
	async : false,
	cache : false,
	type : 'POST',
	dataType : "json",
	url : "getphotoList.do",// 请求的action路径
	data : {
		menutype : 1
	},
	error : function() {// 请求失败处理函数
		alert('请求失败');
	},
	success : function(data) { // 请求成功后处理函数。
		htmlStr = htmlStr + "<table width=\"600px\"><tr>"
		for (var i in data.imgList) {
			if (i % 3 != 0) {
				htmlStr = htmlStr
						+ "<td><div><input type=\"radio\" name=\"selimg\" onclick=\"javascript:selectimg('"
						+ data.imgList[i].docid + "')\" />"
						+ "<img src=\"getimg.do?imgid=" + data.imgList[i].docid
						+ "\" style=\"width:60px;height:60px\"/></div></td>";
			} else {
				htmlStr = htmlStr
						+ "<td><div><input type=\"radio\" name=\"selimg\" onclick=\"javascript:selectimg('"
						+ data.imgList[i].docid
						+ "')\" />"
						+ "<img src=\"getimg.do?imgid="
						+ data.imgList[i].docid
						+ "\" style=\"width:60px;height:60px\" /></div></td></tr>";
			}

		}
		htmlStr = htmlStr + "</table><div>";
		alert(data.pageindexs);
		var length = data.pageindexs.length;
		for (var i in data.pageindexs[i]) {
			// alert(data.pageindexs[i]);
			htmlStr = htmlStr + '<a href=\"javascript:openpage(\''
					+ data.pageindexs[i] + '\')\">' + data.pageindexs[i]
					+ '</a>';
		}
		if (length == 1) {
			htmlStr = htmlStr + '<a href=\"javascript:openpage(\''
					+ data.pageindexs + '\')\">' + data.pageindexs + '</a>';
		}
		htmlStr = htmlStr + "</div>"
	}
});
CKEDITOR.dialog.add('img', function(editor) {
			return {
				title : '  ',
				minWidth : 610,
				minHeight : 400,
				contents : [{
							id : 'img',
							label : 'First Tab',
							title : 'First Tab',
							elements : [{
										id : 'htmlcontent',
										type : 'html',
										html : htmlStr
									}
							]
						}
				],
				onOk : function() {
					var img = "<img src=\"getimg.do?imgid=" + selectedimgid
							+ "\"/><br>";
					CKEDITOR.instances.editor1.insertHtml(img);
				},
				openpage : function(pageindex) {
					alert(pageindex);
					var items=this.contents;
				}

			};
		});
/**
 * 选择表情
 * 
 * @param {}
 *            face
 */
var selectimg = function(imgid) {
	selectedimgid = imgid;

}
/*
 * var openpage=function(pageindex){ alert(pageindex); var
 * instance=CKEDITOR.dialog; }
 */
