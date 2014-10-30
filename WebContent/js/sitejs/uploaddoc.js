var albumlistinitialize = false;
$(document).ready(
		function() {
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "gechannels.do",// 请求的action路径
				data : {
					menutype : 1
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
					// $('#docchannel').html(options);
				}
			});

			// 如果是修改
			if ($('#opertype').val() == 2 || $('#opertype').val() == 3) {
				$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					dataType : "json",
					url : "getdoc.do",// 请求的action路径
					data : {
						docid : $('#docid').val(),
						opertype : $('#opertype').val()
					},
					error : function() {// 请求失败处理函数
						alert('请求失败');
					},
					success : function(data) { // 请求成功后处理函数。
						$('#editor1').val(data.documentValue.htmlstr);
						$('#doctitle').val(data.documentValue.doctitle);
						$('#docabstract').val(data.documentValue.docabstract);
						$('#userid').val(data.documentValue.userid);
						$('#channel').val(data.documentValue.docchannelname);
					}
				});
			}

			//
			$("#message_face").jqfaceedit({
				txtAreaObj : $("#editor1"),
				containerObj : $('#contentdiv'),
				top : 25,
				left : -27
			});
			//

		});
/**
 * 插入图片
 */
var insertimg = function() {
	// 获取被选中的图片
	var imgid = "";
	imgid = $("[name=imgradio]:checked").attr("id");
	/*
	 * $("[name=imgradio]:checked").each( function(){ if(this.checked){
	 * imgid=this.id; } } );
	 */
	var img = "<img src=\"getimg.do?imgid=" + imgid + "\"/><br>";
	CKEDITOR.instances.editor1.insertHtml(img);
	$("#selectimg").dialog("close");
}
var openimgwindow = function() {
	// 设置photoalbum 里面的内容
	if (!albumlistinitialize) {
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "getalbumlist.do",
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			data : {
				userid : $('#userid').val()
			},
			success : function(data) { // 请求成功后处理函数。
				// 设置相册信息
				var albumlistHtml = "";
				for ( var i in data.photoAlbumList) {
					//
					albumlistHtml = albumlistHtml + '<div class=\"folder\">'
							+ '<a href=\"javascript:openPhotoAlbum(\''
							+ data.photoAlbumList[i].imggroupid + '\'\)"><img'
							+ ' width=\"600\" height=\"400\"'
							+ ' src=\"getphotoalbumface.do?albumid='
							+ data.photoAlbumList[i].imggroupid + '\" ></a>'
							+ '<div class=\"m1ln\">' + '<a title=\"'
							+ data.photoAlbumList[i].imggroupname + '\"'
							+ 'href=\"javascript:openPhotoAlbum(\''
							+ data.photoAlbumList[i].imggroupid + '\')\">'
							+ data.photoAlbumList[i].imggroupname + '</a>'
							+ '</div>' + '<div class=\"time\">2014-03-08</div>'
							+ '</div>';
				}
				albumlistinitialize = true;
				if ($("#albumlist").children().length == 1) {
					$("#albumlist").append(albumlistHtml);
					$("#selectimg").append($("#photoalbum"));
				} else {
					$("#imgbox").css("display", "none");
				}
			}
		});
	}
	$("#photoalbum").css("display", "block");
	$("#imgbox").css("display", "none");
	$("#selectimg").dialog({
		height : 500,
		width : 800,
		resizable : true,
		model : false
	});
}
/**
 * 
 */
var openvideowindow = function() {
	// 设置值
	$("#addflash").dialog({
		height : 370,
		width : 210,
		resizable : true,
		model : false
	});
	;
}

/**
 * 
 */
var openemfacewindow = function() {
	// 设置值
	$("#addemface").dialog({
		height : 390,
		width : 470,
		resizable : true,
		model : false
	});
	;
}
/**
 * 插入视频
 */
var insertvideo = function() {
	var matchstr = new RegExp(
			"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
	url = $("#flashurl").val();
	if (false == matchstr.test(url)) {
		alert("        ");
		return;
	} else {
		var embed = "[[" + url + "]]";
		CKEDITOR.instances.editor1.insertHtml(embed);
	}
	$("#addflash").dialog("close");
}
/**
 * 插入连接
 */
var openlinkwindow = function() {
	// 设置值
	$("#addlink").dialog({
		height : 370,
		width : 210,
		resizable : true,
		model : false
	});
	;
}
/**
 * 插入连接
 */
var inseralink = function() {
	var matchstr = new RegExp(
			"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
	url = $("#linkurl").val();
	if (false == matchstr.test(url)) {
		alert("    ");
		return;
	} else {
		var embed = "[[" + url + "]]";
		CKEDITOR.instances.editor1.insertHtml(embed);
	}
	$("#addlink").dialog("close");
}
/**
 * 添加表情
 * 
 * @param {}
 *            face
 */
var addemotion = function(face) {
	var img = "<img src=\"img/faces/" + face + ".gif\"/><br>";
	CKEDITOR.instances.editor1.insertHtml(img);
	$("#addemface").dialog("close");

}
var showselePanel = function(show, event) {
	if (show) {
		$("#citys").show(300);
	} else {
		$("#citys").hide();
	}

}
var changeCity = function(channelid, channelname) {
	$("#channel").text(channelname);
	$("#docchannel").val(channelid);
}
/**
 * 隐藏相册对话框并显示图片列表
 */
var openPhotoAlbum = function(imggroupid) {

	$("#photoalbum").css("display", "none");
	// $("#selectimg").dialog("close");
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : "getphotoList.do",
				data : {
					opergroupid : imggroupid,
					userid : $("#userid").val()
				},
				error : function() {// 请求失败处理函数
					alert('请求失败');
				},
				success : function(data) { // 请求成功后处理函数。
					// 设置相册信息
					var albumlistHtml = "";
					for ( var i in data.photoList) {
						albumlistHtml = albumlistHtml
								+ '<div class=\"displayfolder\" style=\"width:180px;height:180px;\"><a href=\"javaScript:photoDetail(\''
								+ data.photoList[i].imgid
								+ '\')\"><img  class=\"displayimg\" style=\"width:160px;height:160px;\"  src=\"getimg.do?imgid='
								+ data.photoList[i].imgid
								+ '\"></a><input type=\"radio\" name=\"imgradio\" id=\"'
								+ data.photoList[i].imgid + '\"></div>';

					}
					$("#imgbox").css("display", "block");
					if (albumlistHtml == '') {
						albumlistHtml = "<br><p style=\"-webkit-writing-mode: vertical-lr;writing-mode: tb-lr;\">              </p>"
					}
					$("#imglist").html(albumlistHtml);
					if ($("#selectimg").find("#imgbox").html() == undefined) {
						$("#selectimg").append($("#imgbox"));
					}
					// 设置值
					/*
					 * $("#selectimg").dialog({ height : 410, width : 800,
					 * resizable : true, model : false });;
					 */
				}
			});

}