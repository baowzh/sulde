
var q = function() {
	setTimeout(scrollTo, 0, 0, 0);
	var availHeight=$(window).height();
	var availWidth=$(window).width();
	$('.header').css({width:availWidth});
	$('#wrap').css('height', availHeight-90);
	$('#wrap').css('width', 384);
	$('#main').css('height', availHeight - 90);
	$('#main').css('width', 384);
	$('#montdlist').css('width', availHeight - 92);
};

/*
/**
 * 
 */
var openpage = function(pageindex) {
	var docchannel = $('#docchannel').val();
	window.location.href = 'phonelist.do?docchannel=' + docchannel
			+ '&pageindex=' + pageindex;
}