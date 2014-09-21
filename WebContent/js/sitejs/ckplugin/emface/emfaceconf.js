/**
 * Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
 /**
  * 保存用户当前点击的表情列表
  */
var array = new Array();

CKEDITOR.dialog.add('emface', function(editor) {
	return {
		title : '',
		minWidth : 400,
		minHeight : 200,
		contents : [{
			id : 'tab1',
			label : 'First Tab',
			title : 'First Tab',
			elements : [{
				// id: 'input1',
				type : 'html',
				html : '<table>'
						+ '<tr>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face1\');"><img'
						+ ' src="img/faces/face1.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face2\');"><img'
						+ ' src="img/faces/face2.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face3\');"><img'
						+ ' src="img/faces/face3.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face4\');"><img'
						+ ' src="img/faces/face4.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face5\');"><img'
						+ ' src="img/faces/face5.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face6\');"><img'
						+ ' src="img/faces/face6.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face7\');"><img'
						+ ' src="img/faces/face7.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face8\');"><img'
						+ ' src="img/faces/face8.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face9\');"><img'
						+ ' src="img/faces/face9.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face10\');"><img'
						+ ' src="img/faces/face10.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face11\');"><img'
						+ ' src="img/faces/face11.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face12\');"><img'
						+ ' src="img/faces/face12.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face13\');"><img'
						+ ' src="img/faces/face13.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face14\');"><img'
						+ ' src="img/faces/face14.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face15\');"><img'
						+ ' src="img/faces/face15.png" width="40" height="40" /></a>'
						+ '</div></td>'
						+ '<td><div class="i" style="width: 42px; height: 42px">'
						+ '<a href="javascript:addemotion(\'face16\');"><img'
						+ ' src="img/faces/face16.png" width="40" height="40" /></a>'
						+ '</div></td>' + '</tr>' + '</table>'
			}
			
			]
		}
		
		],
		onOk : function() {
			for (var face in array) {
				var img = "<img src=\"img/faces/" + array[face] + ".png\"/>";
				var p = "<p>[" + array[face] + "]</p>";
				CKEDITOR.instances.editor1.insertHtml(img);
			}
			array = new Array();
		},
		onHide : function() {
			array = new Array();
			delete this._.selectedElement
		}
	};
});
/**
 * 选择表情
 * @param {} face
 */
var addemotion = function(face) {
	array.push(face);

}
