var q = function() {
	setTimeout(scrollTo, 0, 0, 0);
	var availHeight = $(window).height();
	var availWidth = $(window).width();
	$('.header').css('background-size', '' + availWidth + 'px 90px')
	$('#main').css('height', availHeight - 105);
	$('#content').css('width', availWidth + 10);
	$('#headimgdiv').css({
		width : availWidth - 150
	});
	var sheetwidth = 0;
	$('.phonesheet').each(function(i, val) {

		// alert('scrollWidth:'+$(val)[0].scrollWidth);
		// alert('clientWidth:'+$(val)[0].clientWidth);
		sheetwidth = sheetwidth + $(val)[0].scrollWidth + 10;
	});
	var width1 = sheetwidth + availWidth + 1300;
	$('#main').css({
		width : width1
	});
	var u = navigator.userAgent;
	if(u.indexOf('iPhone') > -1){
		 $('#content').css('width', width1 + 5);
		 $('.header').css('width', width1+5);
	 }
	if (width1 > availWidth) {
		// $('.header').css('background-size', '' + width1 + 'px 90px');
		// $('.header').css('width', width1);
	}
	$('#main')[0].clientWidth = width1;
	$('iframe').prop('height', 390);
	$('iframe').prop('width', 410);

};
/**
 * 
 */
var checklogin = function(raceid, docid) {
	var islogin = $('#islogin').val();
	if (islogin == 0) {
		return false;
	} else {
		return true;
	}
};
/**
 * 给文章打分
 */
var scoreracedoc = function(raceid, docid) {
	var islogin = checklogin();
	if (islogin == false) {
		$('.header').css('display', 'none');
		$('#content').css('display', 'none');
		$('#logindiv').css('display', 'block');
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
						window.location.href = 'phonedetail.do?docid='
								+ $('#docid').val();
					} else if (data.mess == '1') {
						MessageWindow
								.showMess('      <br/>      ');
					} else if (data.mess == '3') {
						MessageWindow
								.showMess('            ');
					} else if (data.mess == '4') {
						MessageWindow
								.showMess('          ');
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

};
var phonelogin = function() {
	var usernamei = $('#username').val();
	var passwordi = $('#password').val();
	var validcodei = $('#varifycode').val();
	if (usernamei == null || usernamei == '') {
		MessageWindow.showMess('    ');
		return;

	}
	if (passwordi == null || passwordi == '') {
		MessageWindow.showMess('    ');
		return;

	}
	if (validcodei == null || validcodei == '') {
		MessageWindow.showMess('    ');
		return;

	}
	$
			.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : 'login.do',// 请求的action路径
				data : {
					username : $('#username').val(),
					password : $('#password').val(),
					validcode : $('#varifycode').val()
				},
				error : function() {// 请求失败处理函数
					MessageWindow
							.showMess('         ');
				},
				success : function(data) { // 请求成功后处理函数。
					if (data.success == 'true') {
						window.location.href = 'phonedetail.do?docid='
								+ $('#docid').val();
					} else {
						if (data.mess == '1') {
							MessageWindow
									.showMess('       ');
						}
						if (data.mess == '2') {
							MessageWindow
									.showMess('    <br>     ');
						} else if (data.mess == '3') {
							MessageWindow
									.showMess('       ');
						}
						else if (data.mess == '5') {
							MessageWindow
									.showMess('  email    <br>         ');
						} 
					}
				}
			});

};