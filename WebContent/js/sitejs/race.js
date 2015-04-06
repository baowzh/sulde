/**
 * 显示参加比赛界面
 */
var showjoinracediv = function() {
	$("#joinracediv").dialog({
		height : 340,
		width : 230,
		resizable : true,
		model : false
	});
};
/**
 * 
 */
var joinrace = function() {
	var type = $('input[name="jointype"]:checked').val();
	var validcode = $('#raicevalidcode').val();
	if (type == null || type == '') {
		MessageWindow
				.showMess('    ');
		return;
	}
	if (validcode == null || validcode == '') {
		MessageWindow.showMess('    ');
		return;
	}
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				data : {
					raceid : $('#raceid').val(),
					docid : $('#docid').val(),
					jointype : $('input[name="jointype"]:checked').val(),
					raicevalidcode : $('#raicevalidcode').val()
				},
				url : 'addRaceDocument.do',// 请求的action路径
				error : function() {// 请求失败处理函数
					MessageWindow
							.showMess('    ');
				},
				success : function(data) {
					if (data.mess == '0') {
						$("#joinracediv").dialog("close");
						MessageWindow.showMess('   ');	
						window.location.href='raceindex.do';
					} else if (data.mess == '1') {
						MessageWindow
								.showMess('       ');
					} else if (data.mess == '2') {
						MessageWindow
								.showMess(raceModelJson.raceModel.round+'        <br/>       ');
					} else if (data.mess == '3') {
						MessageWindow
								.showMess('        ');
					} else if (data.mess == '4') {
						MessageWindow
								.showMess('      ');
					}
					else if (data.mess == '7') {
						MessageWindow
								.showMess('        ');
					}
					else if (data.mess == '5') {
						MessageWindow
								.showMess(' '
										+ raceModelJson.raceModel.round
										+ '     <br/>     ');
					}
				}
			});
}
/**
 * 
 */
var delfromrace = function(raceid, docid) {
	showConfirmMess("        ",
			function() {
				if (this.getValue() == true) {
					deldocfromrace(raceid, docid);
				}
			});
};
/**
 * 
 */
var deldocfromrace = function(raceid, docid) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
			raceid : raceid,
			docid : docid
		},
		url : 'delRaceDocument.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			MessageWindow.showMess('    ');
		},
		success : function(data) {
			if (data.mess == '0') {
				MessageWindow.showMess('   ');
				window.location.href = 'getuserdocdetail.do?docid='
						+ $('#docid').val();
			} else {
				MessageWindow.showMess('    ');
			}

		}
	});

};
/**
 * 给文章打分
 */
var scoreracedoc = function(raceid, docid) {
	var islogin = checklogin();
	if (islogin == false) {
		openloginwin();
		return;
	}
	var racescore = $('#racescore').val();
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				data : {
					raceid : raceid,
					docid : docid,
					score : racescore
				},
				url : 'addRaceScoreLogValue.do',// 请求的action路径
				error : function() {// 请求失败处理函数
					MessageWindow
							.showMess('    ');
				},
				success : function(data) {
					if (data.mess == '0') {
						MessageWindow.showMess('    ');
						window.location.href = 'getuserdocdetail.do?docid='
								+ $('#docid').val();
					} else if (data.mess == '1') {
						MessageWindow
								.showMess('        <br/>      ');
					} else if (data.mess == '3') {
						MessageWindow
								.showMess('       <br/>      ');
					} else if (data.mess == '4') {
						MessageWindow
								.showMess('      ');
					} else if (data.mess == '5') {
						MessageWindow
								.showMess('    <br>'
										+ raceModelJson.raceModel.raceRound.begindatestr
										+ '    ');
					} else if (data.mess == '6') {
						MessageWindow
								.showMess('    <br>'
										+ raceModelJson.raceModel.raceRound.enddatestr
										+ '     ');
					}
					else if (data.mess == '7') {
						MessageWindow
								.showMess('        ');
					}
					else if (data.mess == '8') {
						MessageWindow
								.showMess('        ');
					}
					else if (data.mess == '9') {
						MessageWindow
								.showMess('      <br>        ');
					}


				}
			});

}