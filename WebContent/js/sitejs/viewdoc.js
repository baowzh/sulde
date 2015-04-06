$(window).on("load", function(e) {
	setpagewidth();
})
var setpagewidth = function() {
	var height = $(window).height();
	var width = $('#condiv')[0].scrollWidth;
	// alert(width);
	var sheetwidth = 0;
	var count = 0;
	var postSheets = 0;
	var postSheetscount = 0;
	var agentkind = 0;
	agentkind = $('#agentkind').val();
	var isie8 = false;
	if (navigator.appName == "Microsoft Internet Explorer"
			&& navigator.appVersion.match(/8./i) == "8.") {
		isie8 = true;
	}
	$('.postSheet').each(function(i, val) {
		postSheets = postSheets + $(val)[0].scrollWidth + 30;
		postSheetscount++;
	});
	var realwidth = document.getElementById("commentlist").style.width;
	if (realwidth == undefined || realwidth == '') {
		realwidth = 0;
	}
	if (isie8 && realwidth - postSheets < 0) {
		document.getElementById("commentlist").style.width = postSheets;
	}
	$('.msheet').each(function(i, val) {
		sheetwidth = sheetwidth + $(val)[0].scrollWidth + 10;
		count++;
	});

	var width1 = width + sheetwidth;
	// alert(isie8);
	if (count == 0) {
		if (!isie8) {
			width1 = width + sheetwidth + 1430;
		}

	} else {
		// if(agentkind==1){
		if (!isie8) {
			width1 = width + sheetwidth + 1430;
		}

		// }

	}
	if (!isie8) {
		$('#condiv').css({
			height : height - 60,
			width : width1
		});
		width = $('#condiv')[0].scrollWidth;
		$('.viewhead').css({
			width : width
		});
	} else {
		document.getElementById("condiv").style.width = width1 + 'px';
		document.getElementById("condiv").style.height = height - 60 + 'px';
		width = $('#condiv')[0].scrollWidth;
		$('.viewhead').css({
			width : width
		});
	}
	$('iframe').css('z-index', 10);
}
