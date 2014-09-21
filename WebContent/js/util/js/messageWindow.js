
/*******************************************************************************
 * messageWindow.js
 ******************************************************************************/

var MessWindow = function() {
	this.showMess = function(mess,callfunction) { // 初始化窗口
		// alert(mess);
		showMessage(mess, callfunction) ;
	};
};
var MessageWindow =new MessWindow();