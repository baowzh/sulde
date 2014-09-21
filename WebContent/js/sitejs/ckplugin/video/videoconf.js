/**
 * Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
var url = "";
CKEDITOR.dialog.add('video', function(editor) {
	var d = function(a) {
		this._.selectedElement = a;
		this.setValueOf("info", "txtName", a.data("cke-saved-name") || "")
	};
	return {
		title : '',
		minWidth : 400,
		minHeight : 200,
		contents : [{
					id : 'video',
					label : 'First Tab',
					title : 'First Tab',
					elements : [{
								id : 'swurl',
								type : 'text',
								label : '  '
							}]
				}],
		onOk : function() {
			// http://player.youku.com/player.php/sid/XNzI2Mzg4OTky/v.swf
			// var url=$("swurl").val();
			// alert(this.getValueOf("info", "txtName"));
			// var a = CKEDITOR.tools.trim(this.getValueOf("info",
			// "txtName"));
			// alert(a);
			/*
			 * a = { id : a, name : a, "data-cke-saved-name" : a };
			 */
			// var iframe="<iframe height=498 width=510
			// src=\"http://player.youku.com/embed/XNzI2Mzg4OTky\"
			// frameborder=0
			// allowfullscreen></iframe>";
			// var embed = "<embed
			// pluginspage=\"http://www.macromedia.com/go/getflashplayer\"
			// src=\"http://player.youku.com/player.php/sid/XNzI2Mzg4OTky/v.swf\"
			// allowFullScreen=\"true\" quality=\"high\" width=\"480\"
			// height=\"400\" align=\"middle\"
			// allowScriptAccess=\"always\"
			// type=\"application/x-shockwave-flash\"></embed>";
			var matchstr = new RegExp("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
			url = this.getContentElement("video", "swurl").getValue();
			if (false == matchstr.test(url)) {
				alert("        ");
				return;
			} else {
				var embed = "[[" + url + "]]";
				CKEDITOR.instances.editor1.insertHtml(embed);
			}

		}
	};
});
