// JavaScript Document

$(window).on("load", function(e) {
	setheight();
}

);
var setheight = function() {

	$("#nameCard ")[0].style.height = ($("#nameCard div")[0].scrollHeight - 178)
			+ "px";
	var embeds = document.getElementsByTagName('embed');
	if (navigator.appVersion.indexOf("MSIE 8.") != -1
			|| navigator.appVersion.indexOf("MSIE 9.") != -1
			|| embeds.length!=0) {// Older
		// IE

		$(".mVsheet").toggleClass("mVsheetIE89");
		$(".mVsheet").toggleClass("mVsheet");

		$(".mVsheetIE89")
				.bind(
						"scroll",
						null,
						function() {
							jQuery(this).children("div.ScrollToView")[0].style.left = this.scrollLeft
									+ 600 + "px"
						})
		$(".ScrollToView").bind("click", null, function() {
			$(this).parent(".mVsheetIE89")[0].scrollIntoView();
		});

		$('#vs').scroll(function(e) {
			e.stopPropagation();
			e.preventDefault();
			e.returnValue = false;
		})

	} else { // newer IE webkit

		for (i = 0; i < $(".mVsheet").length; i++) {

			$(".mVsheet")[i].style.height = $(".mVsheet")[i].scrollHeight
					+ "px";
			$(".mVsheet")[i].style.width = '680' + "px";
		}

		$(".ScrollToView").css("display", "none")
	}

};
