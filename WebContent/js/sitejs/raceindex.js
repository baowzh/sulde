/**
 * 投入到下一轮比赛
 */
var switchtonextround = function(userid, raceid, jointype) {
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				data : {
					joinuserid : userid,
					raceid : raceid,
					jointype : jointype
				},
				url : 'switchDocToNextRound.do',// 请求的action路径
				error : function() {// 请求失败处理函数
					MessageWindow
							.showMess('    ');
				},
				success : function(data) {
					if (data.mess == '0') {
						MessageWindow.showMess('   ');
					} else if (data.mess == '3') {
						MessageWindow
								.showMess('     ');
					} else if (data.mess == '4') {
						MessageWindow
								.showMess('  '
										+ raceModelJson.raceModel.round
										+ '     <br/>    <br/>    ');
					} else if (data.mess == '5') {
						MessageWindow
								.showMess('       ');
					} else if (data.mess == '6') {
						MessageWindow.showMess('    ');
					} else if (data.mess == '7') {
						MessageWindow.showMess('   ');
					} else if (data.mess == '8') {
						MessageWindow.showMess(' '
								+ (raceModelJson.raceModel.round + 1)
								+ '      ');
					}
				}
			});
};
/**
 * 设置时间间隔
 */
$(document).ready(function() {
	setInterval(function() {
		refreshracestatus();
	}, 30000);
});
/**
 * 自动刷新活动页面
 */
var refreshracestatus = function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : 'refreshRaceList.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			// MessageWindow.showMess('链接服务异常，请稍后在试。');
		},
		success : function(data) {
			setRaceList(data.raceUsers, 'box3_list');
			setRaceList(data.raceUsers2, 'box3_list1');
		}
	})
};
var setRaceList = function(raceUsers, id) {
	if (raceUsers.length == 0) {
		return;
	}
	$('#' + id).empty();
	for (i in raceUsers) {
		var raceuseri = $('<div></div>').addClass('raceuser');
		var avtri = $('<div></div>').addClass('avtr');
		avtri.append($('<a></a>').prop('href',
				'gouserindex.do?userid=' + raceUsers[i].uservalue.userid)
				.append(
						$('<img>').prop(
								'src',
								'html/userhead/'
										+ raceUsers[i].uservalue.headurl)));
		var desci = $('<div></div>').addClass('desc');
		var desitem1 = $('<div></div>').addClass('desitem').css('height', 320);
		var text1 = '';
		// alert(raceUsers[i].maxscore*1 -0);
		if (raceUsers[i].maxscore*1 -0>0) {
			text1 = ' :' + raceUsers[i].uservalue.artname
					+ '  ';
			desitem1
					.append($('<div></div>').addClass('author').text(text1+' ')
							.append(
									$('<a></a>').prop('href', '#').css('color',
											'#f00').text(
											(i * 1 + 1)
													+ '    ')));
			desci.append(desitem1);

		} else {
			text1 = ' :' + raceUsers[i].uservalue.artname;
			desitem1.append($('<div></div>').addClass('author').text(text1));
			desci.append(desitem1);
		}
		var desitem2 = $('<div></div>').addClass('desitem').css('height', 320);
		var text2 = '   :';
		if (raceUsers[i].uservalue.jointype == 1) {
			text2 = text2 + '  ';
		} else if (raceUsers[i].uservalue.jointype == 2) {
			text2 = text2 + '    ';
		} else {
			text2 = text2 + '  ';
		}
		desitem2.append($('<div></div>').addClass('author').text(text2));
		desci.append(desitem2);
		var desitem3 = $('<div></div>').addClass('nwsl1');
		var titlei = $('<div></div>').addClass('title').css("height", 240).css(
				"color", "#f00");
		titlei.append($('<a></a>').prop(
				'href',
				'getuserdocdetail.do?docid='
						+ raceUsers[i].raceDocumentValues[0].docid).prop(
				"target", "_blank").addClass('tit_text_overflow').css("color",
				"#f00").text(raceUsers[i].raceDocumentValues[0].doctitle));
		desitem3.append(titlei);
		// 添加评分按钮
		var linkdiv = $('<div></div>').addClass('author').append(
				$('<a></a>').prop(
						'href',
						'getuserdocdetail.do?docid='
								+ raceUsers[i].raceDocumentValues[0].docid)
						.text(' ').css("color", "#f00"));

		desitem3.append(linkdiv);
		desci.append(desitem3);
		var desitem4 = $('<div></div>').addClass('desitem').css('height', 320);
		var text4 = ' :' + Math.round(raceUsers[i].maxscore * 1000)
				/ 1000;
		desitem4.append($('<div></div>').addClass('author').text(text4).css(
				"color", "#f00"))
		desci.append(desitem4);
		var desitem5 = $('<div></div>').addClass('desitem').css('height', 320);
		var text5 = '   ';
		desitem5.append($('<div></div>').addClass('author').append(
				$('<a></a>').prop(
						'href',
						'raceScoreDetail.do?raceid='
								+ raceUsers[i].raceDocumentValues[0].raceid
								+ '&docid='
								+ raceUsers[i].raceDocumentValues[0].docid
								+ '&round='
								+ raceUsers[i].raceDocumentValues[0].raceround)
						.text(text5).css('color','#036')).css('color','#036'));
		desci.append(desitem5);
		//
		/*
		var desitem6 = $('<div></div>').addClass('desitem').css('height', 320);
		var text6 = '   ';
		desitem6.append($('<div></div>').addClass('author').append(
				$('<a></a>').prop(
						'href',
						'javascript:switchtonextround('''+ raceUsers[i].uservalue.userid+ ''','''+ raceUsers[i].raceDocumentValues[0].raceid+ ''','''+ raceUsers[i].raceDocumentValues[0].jointype+''')').text(text6).css('color','#036')).css('color','#036'));
		desci.append(desitem6);
		*/
		raceuseri.append(avtri);
		raceuseri.append(desci);
		$('#' + id).append(raceuseri);
	}
};