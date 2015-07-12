/**
 * jQuery's jqfaceedit Plugin
 * 
 * @author cdm
 * @version 0.2
 * @copyright Copyright(c) 2012.
 * @date 2012-08-09
 */
(function($) {
	var em = [ {
		'id' : 1,
		'phrase' : 'face1',
		'url' : 'face1.gif'
	}, {
		'id' : 2,
		'phrase' : 'face2',
		'url' : 'face2.gif'
	}, {
		'id' : 3,
		'phrase' : 'face3',
		'url' : 'face3.gif'
	}, {
		'id' : 4,
		'phrase' : 'face4',
		'url' : 'face4.gif'
	}, {
		'id' : 5,
		'phrase' : 'face5',
		'url' : 'face5.gif'
	}, {
		'id' : 6,
		'phrase' : 'face6',
		'url' : 'face6.gif'
	}, {
		'id' : 7,
		'phrase' : 'face7',
		'url' : 'face7.gif'
	}, {
		'id' : 8,
		'phrase' : 'face8',
		'url' : 'face8.gif'
	}, {
		'id' : 9,
		'phrase' : 'face9',
		'url' : 'face9.gif'
	}, {
		'id' : 10,
		'phrase' : 'face10',
		'url' : 'face10.gif'
	}, {
		'id' : 11,
		'phrase' : 'face11',
		'url' : 'face11.gif'
	}, {
		'id' : 12,
		'phrase' : 'face12',
		'url' : 'face12.gif'
	}, {
		'id' : 13,
		'phrase' : 'face13',
		'url' : 'face13.gif'
	}, {
		'id' : 14,
		'phrase' : 'face14',
		'url' : 'face14.gif'
	}, {
		'id' : 15,
		'phrase' : 'face15',
		'url' : 'face15.gif'
	}, {
		'id' : 16,
		'phrase' : 'face16',
		'url' : 'face16.gif'
	}, {
		'id' : 17,
		'phrase' : 'face17',
		'url' : 'face17.gif'
	}, {
		'id' : 18,
		'phrase' : 'face18',
		'url' : 'face18.gif'
	}, {
		'id' : 19,
		'phrase' : 'face19',
		'url' : 'face19.gif'
	}, {
		'id' : 20,
		'phrase' : 'face20',
		'url' : 'face20.gif'
	}, {
		'id' : 21,
		'phrase' : 'face21',
		'url' : 'face21.gif'
	}, {
		'id' : 22,
		'phrase' : 'face22',
		'url' : 'face22.gif'
	}, {
		'id' : 23,
		'phrase' : 'face23',
		'url' : 'face23.gif'
	}, {
		'id' : 24,
		'phrase' : 'face24',
		'url' : 'face24.gif'
	}, {
		'id' : 25,
		'phrase' : 'face25',
		'url' : 'face25.gif'
	}, {
		'id' : 26,
		'phrase' : 'face26',
		'url' : 'face26.gif'
	}, {
		'id' : 27,
		'phrase' : 'face27',
		'url' : 'face27.gif'
	}, {
		'id' : 28,
		'phrase' : 'face28',
		'url' : 'face28.gif'
	}, {
		'id' : 29,
		'phrase' : 'face29',
		'url' : 'face29.gif'
	}, {
		'id' : 30,
		'phrase' : 'face30',
		'url' : 'face30.gif'
	}, {
		'id' : 31,
		'phrase' : 'face31',
		'url' : 'face31.gif'
	}, {
		'id' : 32,
		'phrase' : 'face32',
		'url' : 'face32.gif'
	}, {
		'id' : 33,
		'phrase' : 'face33',
		'url' : 'face33.gif'
	}, {
		'id' : 34,
		'phrase' : 'face34',
		'url' : 'face34.gif'
	}, {
		'id' : 35,
		'phrase' : 'face35',
		'url' : 'face35.gif'
	}, {
		'id' : 36,
		'phrase' : 'face36',
		'url' : 'face36.gif'
	}, {
		'id' : 37,
		'phrase' : 'face37',
		'url' : 'face37.gif'
	}, {
		'id' : 38,
		'phrase' : 'face38',
		'url' : 'face38.gif'
	}, {
		'id' : 39,
		'phrase' : 'face39',
		'url' : 'face39.gif'
	}, {
		'id' : 40,
		'phrase' : 'face40',
		'url' : 'face40.gif'
	}, {
		'id' : 41,
		'phrase' : 'face41',
		'url' : 'face41.gif'
	}, {
		'id' : 42,
		'phrase' : 'face42',
		'url' : 'face42.gif'
	}, {
		'id' : 43,
		'phrase' : 'face43',
		'url' : 'face43.gif'
	}, {
		'id' : 44,
		'phrase' : 'face44',
		'url' : 'face44.gif'
	}, {
		'id' : 45,
		'phrase' : 'face45',
		'url' : 'face45.gif'
	}, {
		'id' : 46,
		'phrase' : 'face46',
		'url' : 'face46.gif'
	}, {
		'id' : 47,
		'phrase' : 'face47',
		'url' : 'face47.gif'
	}, {
		'id' : 48,
		'phrase' : 'face48',
		'url' : 'face48.gif'
	}, {
		'id' : 49,
		'phrase' : 'face49',
		'url' : 'face49.gif'
	}, {
		'id' : 50,
		'phrase' : 'face50',
		'url' : 'face50.gif'
	}, {
		'id' : 51,
		'phrase' : 'face51',
		'url' : 'face51.gif'
	}, {
		'id' : 52,
		'phrase' : 'face52',
		'url' : 'face52.gif'
	}, {
		'id' : 53,
		'phrase' : 'face53',
		'url' : 'face53.gif'
	}, {
		'id' : 54,
		'phrase' : 'face54',
		'url' : 'face54.gif'
	}, {
		'id' : 55,
		'phrase' : 'face55',
		'url' : 'face55.gif'
	}, {
		'id' : 56,
		'phrase' : 'face56',
		'url' : 'face56.gif'
	}, {
		'id' : 57,
		'phrase' : 'face57',
		'url' : 'face57.gif'
	}, {
		'id' : 58,
		'phrase' : 'face58',
		'url' : 'face58.gif'
	}, {
		'id' : 59,
		'phrase' : 'face59',
		'url' : 'face59.gif'
	}, {
		'id' : 60,
		'phrase' : 'face60',
		'url' : 'face60.gif'
	} ];
	// textarea设置光标位置
	function setCursorPosition(ctrl, pos) {
		if (ctrl.setSelectionRange) {
			ctrl.focus();
			ctrl.setSelectionRange(pos, pos);
		} else if (ctrl.createTextRange) {// IE Support
			var range = ctrl.createTextRange();
			range.collapse(true);
			range.moveEnd('character', pos);
			range.moveStart('character', pos);
			range.select();
		}
	}

	// 获取多行文本框光标位置
	function getPositionForTextArea(obj) {
		var Sel = document.selection.createRange();
		var Sel2 = Sel.duplicate();
		Sel2.moveToElementText(obj);
		var CaretPos = -1;
		while (Sel2.inRange(Sel)) {
			Sel2.moveStart('character');
			CaretPos++;
		}
		return CaretPos;

	}

	$.fn
			.extend({
				jqfaceedit : function(options) {
					var defaults = {
						txtAreaObj : '', // TextArea对象
						containerObj : 'commdiv', // 表情框父对象
						textareaid : 'editor1',// textarea元素的id
						popName : '', // iframe弹出框名称,containerObj为父窗体时使用
						emotions : em, // 表情信息json格式，id表情排序号
						// phrase表情使用的替代短语url表情文件名
						top : 0, // 相对偏移
						left : 0
					// 相对偏移
					};

					var options = $.extend(defaults, options);
					var cpos = 0;// 光标位置，支持从光标处插入数据
					var textareaid = options.textareaid;

					return this
							.each(function() {
								var Obj = $(this);
								var container = options.containerObj;
								if (document.selection) {// ie
									options.txtAreaObj
											.bind(
													"click keyup",
													function(e) {// 点击或键盘动作时设置光标值
														e.stopPropagation();
														cpos = getPositionForTextArea(document
																.getElementById(textareaid) ? document
																.getElementById(textareaid)
																: window.frames[options.popName].document
																		.getElementById(textareaid));
													});
								}
								$(Obj)
										.bind(
												"click",
												function(e) {
													e.stopPropagation();
													var faceHtml = '<div id="face" >';
													faceHtml += '<div id="texttb"><a class="f_close" title="关闭" href="javascript:void(0);"></a></div>';
													faceHtml += '<div id="facebox">';
													faceHtml += '<div id="face_detail" class="facebox clearfix rerotatesection" ><ul>';

													for (i = 0; i < options.emotions.length; i++) {
														faceHtml += '<li text='
																+ options.emotions[i].phrase
																+ ' type='
																+ i
																+ '><a href="javascript:addemotion(\''
																+ options.emotions[i].phrase
																+ '\')"><img title='
																+ options.emotions[i].phrase
																+ ' src="img/faces/'
																+ options.emotions[i].url
																+ '"  style="cursor:pointer; position:relative;"   /></a></li>';
													}
													faceHtml += '</ul></div>';
													faceHtml += '</div><div class="arrow arrow_t"></div></div>';

													container.find('#face')
															.remove();
													container.append(faceHtml);													
													container
															.find(
																	"#face_detail ul >li")
															.bind(
																	"click",
																	function(e) {
																		/*
																		 * var
																		 * txt =
																		 * $(this).attr("text");
																		 * var
																		 * faceText =
																		 * txt;
																		 * 
																		 * //options.txtAreaObj.val(options.txtAreaObj.val() +
																		 * faceText);
																		 * var
																		 * tclen =
																		 * options.txtAreaObj.val().length;
																		 * 
																		 * var
																		 * tc =
																		 * document.getElementById(textareaid);
																		 * if (
																		 * options.popName ) {
																		 * tc =
																		 * window.frames[options.popName].document.getElementById(textareaid); }
																		 * var
																		 * pos =
																		 * 0;
																		 * if(
																		 * typeof
																		 * document.selection !=
																		 * "undefined")
																		 * {//IE
																		 * options.txtAreaObj.focus();
																		 * setCursorPosition(tc,
																		 * cpos);//设置焦点
																		 * document.selection.createRange().text =
																		 * faceText;
																		 * //计算光标位置
																		 * pos =
																		 * getPositionForTextArea(tc); }
																		 * else
																		 * {//火狐
																		 * //计算光标位置
																		 * pos =
																		 * tc.selectionStart +
																		 * faceText.length;
																		 * options.txtAreaObj.val(options.txtAreaObj.val().substr(0,
																		 * tc.selectionStart) +
																		 * faceText +
																		 * options.txtAreaObj.val().substring(tc.selectionStart,
																		 * tclen)); }
																		 * cpos =
																		 * pos;
																		 * setCursorPosition(tc,
																		 * pos);//设置焦点
																		 */
																		 container.find("#face").remove();
																		
																		// alert("dd");
																	});
													// 关闭表情框
													container
															.find(".f_close")
															.bind(
																	"click",
																	function() {
																		container
																				.find(
																						"#face")
																				.remove();
																	});
													// 处理js事件冒泡问题
													$('body')
															.bind(
																	"click",
																	function(e) {
																		e
																				.stopPropagation();
																		container
																				.find(
																						'#face')
																				.remove();
																		$(this)
																				.unbind(
																						'click');
																	});
													if (options.popName != '') {
														$(
																window.frames[options.popName].document)
																.find('body')
																.bind(
																		"click",
																		function(
																				e) {
																			e
																					.stopPropagation();
																			container
																					.find(
																							'#face')
																					.remove();
																		});
													}
													container
															.find('#face')
															.bind(
																	"click",
																	function(e) {
																		e
																				.stopPropagation();
																	});
													var offset = $(e.target)
															.offset();
													offset.top += options.top;
													offset.left += options.left;
													container.find("#face")
															.css(offset).show();
												});
							});
				},
				//表情文字符号转换为html格式
				emotionsToHtml : function(options) {
					return this.each(function() {
						var msgObj = $(this);
						var rContent = msgObj.html();

						var regx = /(\[[\u4e00-\u9fa5]*\w*\]){1}/g;
						//正则查找“[]”格式
						var rs = rContent.match(regx);
						if (rs) {
							for (i = 0; i < rs.length; i++) {
								for (n = 0; n < em.length; n++) {
									if (em[n].phrase == rs[i]) {
										var t = "<img src='img/faces/"
												+ em[n].url + "' />";
										rContent = rContent.replace(rs[i], t);
										break;
									}
								}
							}
						}
						msgObj.html(rContent);
					});
				}
			})
})(jQuery);
