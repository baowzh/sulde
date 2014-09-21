$(document).ready($(function() {
			var p = $('#picplayer');
			initPicPlayer(pics1, p.css('width').split('px')[0], p.css('height')
							.split('px')[0]);
		}));
var pics1 =${pics1};
function initPicPlayer(pics, w, h) {
	// 选中的图片
	var selectedItem;
	// 选中的按钮
	var selectedBtn;
	// 自动播放的id
	var playID;
	// 选中图片的索引
	var selectedIndex;
	// 容器
	var p = $('#picplayer');
	p.text('');
	p
			.append('<div id=\"piccontent\" style=\"width:475px;height:330px;-webkit-writing-mode: vertical-lr;writing-mode: tb-lr;\"><div id="imgcontent" style=\"width:455px;height:310px;" ></div><div id="title" style="width:30px;color:#454545;height:330px;padding:5px;" class="mnlist"></div></div>');
	var c = $('#imgcontent');
	for (var i = 0; i < pics.length; i++) {
		// 添加图片到容器中
		c.append('<a href=\"' + pics[i].link
				+ '\" target=\"_blank\" style=\"color:#09c;\"><img id=\"picitem' + i
				+ '\" style=\"display: none;z-index:' + i
				+ 'height:329px;width:455px;height:329px;\" src=\"'
				+ pics[i].url + '\" /></a>');
	}
	// 按钮容器，绝对定位在右下角
	p.append('<div id=\"picbtnHolder\" style=\"position:absolute;top:' + (538)
			+ 'px;width:465px;height:20px;z-index:88;\"></div>');
	//
	var btnHolder = $('#picbtnHolder');
	btnHolder
			.append('<div id=\"picbtns\" style=\"float:right; padding-right:1px;\"></div>');
	var btns = $('#picbtns');
	//
	for (var i = 0; i < pics.length; i++) {
		// 增加图片对应的按钮
		btns
				.append('<span id="picbtn'
						+ i
						+ '" style="cursor:pointer; border:solid 1px #ccc;background-color:#eee; display:inline-block;">&nbsp;'
						+ (i + 1) + '&nbsp;</span>&nbsp;');
		$('#picbtn' + i).data('index', i);
		$('#picbtn' + i).click(function(event) {
			if (selectedItem.attr('src') == $('#picitem'
					+ $(this).data('index')).attr('src')) {
				return;
			}
			setSelectedItem($(this).data('index'));
		});
	}
	btns.append('&nbsp;');
	// /
	setSelectedItem(0);
	// 显示指定的图片index
	function setSelectedItem(index) {
		selectedIndex = index;
		clearInterval(playID);
		// alert(index);
		if (selectedItem)
			selectedItem.fadeOut('fast');
		selectedItem = $('#picitem' + index);
		selectedItem.fadeIn('slow');
		//
		if (selectedBtn) {
			selectedBtn.css('backgroundColor', '#eee');
			selectedBtn.css('color', '#000');
		}
		selectedBtn = $('#picbtn' + index);
		selectedBtn.css('backgroundColor', '#000');
		selectedBtn.css('color', '#fff');
		$("#title").html('<a href="'+pics[index].link+'">'+pics[index].title+'</a>');	
		// 自动播放
		playID = setInterval(function() {
					var index = selectedIndex + 1;
					if (index > pics.length - 1)
						index = 0;
					setSelectedItem(index);
				}, pics[index].time);
	}
}