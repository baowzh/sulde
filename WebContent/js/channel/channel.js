/*
 * delete a channel
 */
var operType = 1;
function deleteItem() {
	var channels = "";
	$("#channelList  input:checkbox:checked").each(function() {
				channels += "" + this.value + ",";
				console.log(this.value);
			})
	channels = channels.substring(0, channels.lastIndexOf(","));

	$("#channelids")[0].value = channels;
	$("#manageForm")[0].action = 'deleteChannel.do';
	if (channels.length > 0)
		$("#manageForm")[0].submit();

	/*
	 * var dt = { type : 'POST', contentType : 'application/json;
	 * charset=utf-8', dataType : 'text', success : function(xhr, textStatus) {
	 * 
	 * $("#channelList input:checkbox:checked").each(function() { this.checked =
	 * false; }) }, error : function(xhr, textStatus, errorThrown) {
	 * console.log("**error**" + errorThrown); } }
	 * 
	 * dt.url = "deleteChannel.do?channelids=" + channels; $.ajax(dt);
	 */

}

/*
 * channel modification
 */

/*
 * update channel
 */
function modifyChannel(id) {
	operType = 2;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : "getChannel.do?channelid=" + id,// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
			// 把 showtemplateidtd 列内部设置为select 元素

			$("#dialog-form #chnlname")[0].value = data.treeNodes[0].chnlname;
			$("#dialog-form #chnldesc")[0].value = data.treeNodes[0].chnldesc;
			$("#dialog-form input[name=channelid]")[0].value = data.treeNodes[0].channelid;

			$('#dialog-form').dialog('open');

		}
	});

}

/*
 * channel add/modify dialog
 */
$(function() {
			var chnlname = $("#chnlname");
			var chnldesc = $("#chnldesc");
			var allFields = $([]).add(chnlname).add(chnldesc);
			var channelid = $("#channelid");

			var strChnlnameOriginal;
			var strChnldescOriginal;

			var tips = $(".validateTips");
			function updateTips(t) {
				tips.text(t).addClass("ui-state-highlight");
				setTimeout(function() {
							tips.removeClass("ui-state-highlight", 1500);
						}, 500);
			}
			function checkLength(o, n, min, max) {
				if (o.val().length > max || o.val().length < min) {
					o.addClass("ui-state-error");
					updateTips(n + "的长度必须介于" + min + "至" + max + "字节.");
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
			function checkNameExists(name, msg) {
				$.ajax({
							type : 'POST',
							dataType : 'json',
							url : 'getChannel.do',
							data : {
								chnlname : name[0].value
							},
							success : function(xhr, textStatus) {
								if (xhr.treeNodes.size > 0) {// the channel
									// exists.
									name.addClass("ui-state-error");
									updateTips(msg);
									return true;
								} else {// the channel name is available.
									return false;
								}
							},
							error : function(xhr, textStatus, errorThrown) {
								console.log("**error**" + errorThrown);
							}
						});

			}
			$("#dialog-form").on("dialogopen", function(event, ui) {
						strChnlnameOriginal = chnlname[0].value;
						strChnldescOriginal = chnldesc[0].value;
					});
			$("#dialog-form").dialog(

			{

				autoOpen : false,
				height : 350,
				width : 400,
				modal : true,
				buttons : {
					"保存" : function() {
						var bValid = true;
						allFields.removeClass("ui-state-error");
						bValid = bValid && checkLength(chnlname, "栏目名字已存在!");
						bValid = bValid
								&& checkLength(chnldesc, "栏目显示名字", 2, 80);
						bValid = bValid
								&& !checkNameExists(chnlname, "该栏目已存在!");
						bValid = bValid
								&& checkRegexp(chnlname,
										/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/i,
										"栏目名字必须是中英文,下划线或数字组成.");
						// From jquery.validate.js (by joern), contributed by
						// Scott
						// Gonzalez:
						// http://projects.scottsplayground.com/email_address_validation/
						bValid = bValid
								&& checkRegexp(chnldesc,
										/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/i,
										"栏目显示名字必须是中英文,下划线或数字组成.");

						if (chnldesc[0].value == strChnldescOriginal
								&& chnlname[0].value == strChnlnameOriginal) {
							$(this).dialog("close");
						} else {
							if (bValid) {

								$(this).dialog("close");
							}
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

		});

/*
 * add channel
 */
function addChannel() {
	operType = 1;
	console.log("add channel...");

	$('#dialog-form').dialog('open');

}