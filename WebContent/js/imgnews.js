var imgUrl = new Array();
var imgLink = new Array();
var adNum = 0;
imgUrl[1] = "img/a.jpg";
imgUrl[2] = "img/b.jpg";
imgUrl[3] = "img/c.jpg";
imgUrl[4] = "img/d.jpg";
imgUrl[5] = "img/e.jpg";
imgUrl[6] = "img/f.jpg";
imgUrl[7] = "img/g.jpg";
var imgPre = new Array();
for (i = 1; i < 8; i++) {
	imgPre[i] = new Image();
	imgPre[i].src = imgUrl[i];
}

function setTransition() {
	if (document.all) {
		imgUrlrotator.filters.revealTrans.Transition = Math.floor(Math.random()
				* 20);
		imgUrlrotator.filters.revealTrans.apply();
	}
}

function playTransition() {
	if (document.all)
		imgUrlrotator.filters.revealTrans.play()
}

function nextAd() {
	if (adNum < imgUrl.length - 1)
		adNum++;
	else
		adNum = 1;
	setTransition();
	document.images.imgUrlrotator.src = imgUrl[adNum];
	playTransition();
	theTimer = setTimeout("nextAd()", 6000);
}

function jump2url() {
	jumpUrl = imgLink[adNum];
	jumpTarget = '_blank';
	if (jumpUrl != '') {
		if (jumpTarget != '')
			window.open(jumpUrl, jumpTarget);
		else
			location.href = jumpUrl;
	}
}
function displayStatusMsg() {
	status = imgLink[adNum];
	document.returnValue = true;
}

document
		.write("<center> <img src='javascript:nextAd()'name='imgUrlrotator' width='1000' height='150' border='0' id='imgUrlrotator' style='FILTER: revealTrans(duration=2,transition=50)' /></center>");