var PAGE_EVENT = PAGE_EVENT || {
	EVENT_LIST : [],
	fireEvent : function(event, arg, sync) {
		var result = [], _arg = QZFL.lang.arg2arr(arguments).slice(1), found = false;
		for (var i = 0; i < this.EVENT_LIST.length; i++) {
			var ev = this.EVENT_LIST[i];
			if (ev && ev['event'] == event && ev['handler']) {
				if (sync) {
					setTimeout((function(fn, context) {
								return function() {
									fn.apply(context, _arg);
								}
							})(ev['handler'], this), 12);
				} else {
					result.push(ev['handler'].apply(this, _arg));
				}
				if (this.EVENT_LIST[i]) {
					this.EVENT_LIST[i].fired = true;
					this.EVENT_LIST[i].args = _arg;
				}
				found = true;
			}
		}
		if (!found) {
			this.EVENT_LIST.push({
						'handler' : null,
						'event' : event,
						'fired' : true,
						'args' : _arg
					});
		}
		if (!result.length) {
			return null;
		}
		return result;
	},
	addEvent : function(ev, handler) {
		if (ev == '' || !handler) {
			throw ("event params error");
		}
		this.EVENT_LIST.push({
					'handler' : handler,
					'event' : ev
				});
		return this;
	},
	triggerEvent : function(event, handler) {
		for (var i = 0; i < this.EVENT_LIST.length; i++) {
			var ev = this.EVENT_LIST[i];
			if (ev['event'] == event && ev.fired == true) {
				handler.apply(this, ev.args);
				return -1;
			}
		}
		return this.addEvent(event, handler);
	},
	removeEvent : function(ev, handler) {
		for (var i = 0; i < this.EVENT_LIST.length; i++) {
			if (this.EVENT_LIST[i] && (this.EVENT_LIST[i]['event'] == ev)) {
				var hdl = this.EVENT_LIST[i]['handler'];
				if (!handler) {
					this.EVENT_LIST[i] = null;
				} else if (handler == hdl) {
					this.EVENT_LIST[i] = null;
				}
			}
		}
	}
};
(function() {
	var typeIconMap = {
		0 : "none",
		1 : "icon_mobile",
		2 : "icon_img",
		3 : "none",
		4 : "none",
		5 : "none",
		7 : "icon_img",
		11 : "none",
		12 : "none",
		13 : "none",
		14 : "none",
		15 : "none",
		16 : "none",
		17 : "icon_pengyou",
		18 : "icon_pengyou",
		19 : "none",
		20 : "none"
	}
	var privIconMap = {
		1 : "none",
		2 : "icon_permission_lock",
		3 : "icon_permission_privacy",
		4 : "icon_permission_public",
		5 : "icon_permission_lock"
	};
	var imgUrlMapSmall = {
		"http://imgcache.qq.com/ac/qzone_v5/photo/need_pass.png" : "/qzone/photo/zone/static/need_pass_100.png?max_age=31536000",
		"http://imgcache.qq.com/ac/qzone_v5/photo/no_photo_s.png" : "/qzone/photo/zone/static/no_photo_100.png?max_age=31536000",
		"http://imgcache.qq.com/ac/qzone_v5/photo/no_cover_s.gif" : "/qzone/photo/zone/static/no_cover_100.png?max_age=31536000",
		"http://imgcache.qq.com/qzone/photo/zone/static/no_photo_135.png" : "/qzone/photo/zone/static/no_photo_100.png?max_age=31536000"
	}
	var imgUrlMap = {
		"http://imgcache.qq.com/ac/qzone_v5/photo/need_pass.png" : "/qzone/photo/zone/static/need_pass_135.png?max_age=31536000",
		"http://imgcache.qq.com/ac/qzone_v5/photo/no_photo_s.png" : "/qzone/photo/zone/static/no_photo_135.png?max_age=31536000",
		"http://imgcache.qq.com/ac/qzone_v5/photo/no_cover_s.gif" : "/qzone/photo/zone/static/no_cover_135.png?max_age=31536000",
		"http://imgcache.qq.com/qzone/photo/zone/static/no_photo_100.png" : "/qzone/photo/zone/static/no_photo_135.png?max_age=31536000"
	}
	var privMap = {
		1 : "",
		2 : "回答问题",
		3 : "自己可见",
		4 : "好友可见",
		5 : "回答问题",
		6 : "指定好友可见"
	}
	var s_privMap = {
		1 : "",
		2 : "问答",
		3 : "自己",
		4 : "好友",
		5 : "问答",
		6 : "指定好友"
	}
	var last = [];
	var isGetLasted = false;
	var isCsChange = false;
	var albumData = null;
	var locked = -1;
	if (window.PAGE_EVENT) {
		PAGE_EVENT.addEvent("NOTIFY_CHANGE_CSID", function(argv) {
					isCsChange = true;
				});
		PAGE_EVENT.addEvent("NOTIFY_ALBUM_CURRENTCSID", function(argv) {
					isCsChange = false;
				});
		PAGE_EVENT.addEvent("NOTIFY_ALBUM_LOADED", function(argv) {
					albumData = argv.data.album;
					isGetLasted = false;
				});
	}
	function _isNewAlbum(mt, data) {
		if (!isGetLasted && !isCsChange) {
			isGetLasted = true;
			last = [];
			for (var i = 0, len = albumData.length; i < len; i++) {
				var d = albumData[i];
				if (QZONE.FP._t.g_NowTime - d.lastuploadtime < 20 * 24 * 60
						* 60
						&& d.total != "0") {
					last.push(d.lastuploadtime);
				}
			}
			last.sort(function(a, b) {
						return b - a;
					})
			last = last.slice(0, 5);
		}
		return last.indexOf(mt) != -1;
	}
	var isOwner = QPHOTO.status.isOwner();
	var Q = QPHOTO;
	Q.nameSpace("albumtpl", {
		"checkUnbindPY" : function() {
			if (locked >= 0) {
				return;
			}
			if (QPHOTO.status.inPengyou) {
				QPHOTO.status.checkUnbindPY
						&& QPHOTO.status.checkUnbindPY(function(v) {
									locked = v ? 1 : 0;
								});
			}
		},
		"formatTypeIcon" : function(type) {
			type = parseInt(type, 10);
			if (typeIconMap[type] == "none") {
				return "";
			} else {
				return '<i class="icon ' + typeIconMap[type] + '"><i></i></i>';
			}
		},
		"checkEditable" : function(handset) {
			return [19, 20].indexOf(handset) == -1 && QPHOTO.status.isOwner()
					&& locked <= 0;
		},
		"checkDelable" : function(handset) {
			return [].indexOf(handset) == -1 && QPHOTO.status.isOwner()
					&& locked <= 0;
		},
		"checkSpecAlbum" : function() {
			return (tplid != '' || viewtype == 3) && QPHOTO.status.inQzone
		},
		"getAlbumLiClass" : function(id, d, noShowNew) {
			var c = [];
			if (Q.status.inQzone) {
				if (d.viewtype == 1) {
					if (parseInt(d.tplid, 10)) {
						c.push("photo_albumlist_mark_personalizedalbums");
					}
				} else if (d.viewtype == 3) {
					if (/.*\d[0-3]$/.test(QZONE.FP.getQzoneConfig().ownerUin) && false) {
						c.push("photo_albumlist_mark_photowall");
					}
				} else if (d.viewtype == 5) {
					c.push("photo_albumlist_mark_childalbums");
				} else if (d.viewtype == 6) {
					c.push("photo_albumlist_mark_travelalbums");
				}
			}
			if (!noShowNew) {
				if (_isNewAlbum(d.lastuploadtime, d)) {
					c.push("photo_albumlist_mark_new");
				}
			}
			return c.join(" ");
		},
		"getMusic" : function(d) {
			if (!parseInt(d.songid, 10)) {
				return "display:none";
			}
		},
		"imgFilter" : function(url, type) {
			var hashMap = type == 's' ? imgUrlMapSmall : imgUrlMap;
			if (hashMap[url]) {
				return hashMap[url];
			}
			return url;
		},
		"getPriv" : function(id, d, index) {
			var priv = "";
			var t = myView;
			if (Q.zoneStatus.isOwner()) {
				var privbit = d.priv;
				if (Q.status.inPengyou && d.pypriv) {
					privbit = d.pypriv;
				}
				priv = (t._info.preurlspec == 'a'
						? s_privMap[privbit]
						: privMap[privbit])
						|| "";
			}
			if (priv) {
				return '<span class="limit">' + priv + '</span>';
			}
		},
		"getComment" : function(comment) {
			if (comment) {
				return (comment > 99 ? '99+' : comment) + '评';
			}
		},
		"gernerateAlbumUrl" : function(aid) {
			if (Q.status.inPengyou) {
				return "http://baseapp.pengyou.com/"
						+ QZONE.FP.getQzoneConfig().ownerUin + "/photo/" + aid;
			} else {
				return "http://user.qzone.qq.com/"
						+ QZONE.FP.getQzoneConfig().ownerUin + "/photo/" + aid;
			}
		},
		"formatCoverTips" : function(desc) {
			desc = desc.trim();
			var result = "";
			if (desc.length > 14) {
				result += desc.substring(0, 14) + "\n";
				if (desc.length > 28) {
					result += desc.substring(14, 27) + "...";
				} else {
					result += desc.substring(14);
				}
			} else {
				result += desc;
			}
			return result;
		}
	});
})();
QPHOTO.album.listTpl = '<li  onmouseover="myView.coverFocus(this, <%=index%>);" onmouseout="myView.coverBlur(this, <%=index%>);" class="<%=QPHOTO.albumtpl.getAlbumLiClass(id, _self)%> " id="album_li_<%=id%>"><div class="bor2 photo_albumlist_bg bg"></div><div class="photo_albumlist_wrap"><div class="bd bg bor2"><p class="photo_albumlist_img"><a <%if(!QPHOTO.status.isOwner()){%>title="<%=trim(desc)%>"<%}%> href="<%=QPHOTO.albumtpl.gernerateAlbumUrl(id)%>" onclick="myView.enter(<%=index%>);return false;" class="tcisd_albumlist" tcisdkey="list.albumcover"><%if(myView._info.preurlspec == \'a\'){%><img src="/ac/b.gif" alt="<%=trim(desc)%>" onload="this.onload=null;QPHOTO.img.preLoadNoScale(this,\'<%=QPHOTO.albumtpl.imgFilter(QPHOTO.img.getImgUrl(pre),\'s\')%>\',100,100,true,null,null,function(){myView.firstLook()},true);" /><%}else{%><img src="/ac/b.gif" alt="<%=trim(desc)%>" onload="this.onload=null;QPHOTO.img.preLoadNoScale(this,\'<%=QPHOTO.albumtpl.imgFilter(mpre)%>\',135,135,true,null,null,function(){myView.firstLook()},true);" /><%}%></a></p><p class="photo_albumlist_op hover" style="display:none;"><a href="#" title="相册封面制作工具" class="author-display" onclick="myView.tcisdStats(&quot;list.zzfm&quot;);myView.callExt(&quot;albumEffect&quot;,[<%=index%>]);return false;"><i class="icon icon_edit_album"><i>制作封面</i></i></a></p></div><div class="ft"><p class="photo_albumlist_name"><a title="<%=name%>" href="<%=QPHOTO.albumtpl.gernerateAlbumUrl(id)%>" class="c_tx2" onclick="myView.enter(<%=index%>);return false;" hidefocus="true"><%=QPHOTO.albumtpl.formatTypeIcon(handset)%><%=trim(name)%></a></p><p class="c_tx3 photo_albumlist_photocount"><%=total%>张&nbsp;    <%=QPHOTO.albumtpl.getPriv(id, _self, index)%></p><p class="owner" style="display:none;"><%if(QPHOTO.albumtpl.checkEditable(handset)){%><a href="javascript:void(0);" title="修改相册信息" class="c_tx edit_album author-display tcisd_albumlist" tcisdkey="list.edit_bianjixiangce" tcisdhostonly="1" onclick="myView.callExt(&quot;modInfoAndPriv&quot;,[<%=index%>]);return false;">编辑</a><%}%><%if(QPHOTO.albumtpl.checkDelable(handset)){%><a href="javascript:void(0);" title="删除相册" class="c_tx del_album author-display tcisd_albumlist" tcisdkey="list.edit_deletalbum" tcisdhostonly="1" onclick="myView.callExt(&quot;delAlbum&quot;,[<%=index%>]);return false;">删除</a><%}%><%if((tplid!=\'\' || viewtype == 3) && QPHOTO.status.inQzone){%><a href="javascript:void(0);" onclick="myView.tcisdStats(&quot;list.edit_original&quot;);myView.enter(<%=index%>,null,true);return false;" title="进入普通模式查看" class="c_tx ordinary">普通</a><%}%></p></div></div><i class="icon icon_new_v3"><i></i></i><p class="marking mark_personalizedalbums">  个性相册<i></i><span class="icon_music" style="<%=QPHOTO.albumtpl.getMusic(_self)%>"></span></p><p class="marking mark_childalbums">亲子<i></i><span class="icon_child"></span></p><p class="marking mark_travelalbums">旅游<i></i><span class="icon_travel"></span></p><p class="marking mark_photowall">照片墙<i></i></p><p class="drag" id="drag_<%=index%>" style="display:none;" onmousedown="myView.doDrag(this, QZFL.event.getEvent());" title="拖动排序"><a href="##"><i></i></a></p></li>';
QPHOTO.album.bannerTpl = '<div class="act_hz bg bor2"  id="act_hz" style="display:none;"><div class="act_hz_tit bg"><a href="http://pay.qq.com/qzone/index.shtml?ch=self&aid=photo.act" target="_blank" class="btn btn_kt"><span>开通黄钻</span></a><span class="c_tx4">尊享相册特权功能</span></div><div class="mainlink"><ul><li class="rbor2"><a class="act_hz_link" title="超大容量" id="hz_photo_rongliang" href="http://pay.qq.com/ipay/index.shtml?n=3&c=xxjzghh,xxjzgw&aid=toucanl.zsite&ch=qdqb,kj" target="_blank"><span class="act_hz_title c_tx2">超大容量</span><span class="act_hz_desc c_tx3">送30G空间，黄钻最高1T</span><i class="act_hz_icon act_hz_icon_one">&nbsp;</i></a></li><li class="rbor2"><a class="act_hz_link" title="四重备份" id="hz_photo_beifen" href="javascript:void(0)" target="_blank"><span class="act_hz_title c_tx2">四重备份</span><span class="act_hz_desc c_tx3">保护您的点滴回忆</span><i class="act_hz_icon act_hz_icon_two">&nbsp;</i></a></li><li class="rbor2"><a class="act_hz_link" title="照片礼物" id="hz_photo_print" href="http://app.photo.qq.com/cgi-bin/app/cgi_redict_kadang?from=hd-hzsd-111130" target="_blank"><span class="act_hz_title c_tx2">照片礼物</span><span class="act_hz_desc c_tx3">黄钻用户最低7折</span><i class="act_hz_icon act_hz_icon_three">&nbsp;</i></a></li><li><a class="act_hz_link" title="视频存储" id="hz_photo_video" href="javascript:void(0)" target="_blank"><span class="act_hz_title c_tx2">视频存储</span><span class="act_hz_desc c_tx3">支持超大100G存储</span><i class="act_hz_icon act_hz_icon_four">&nbsp;</i></a></li></ul></div></div>';
QPHOTO.album.normalListFrameTpl = '<div class="clear photo_albumlist" id="album_list_div"><ul id="album_list_container"><p class="album_list_loading">正在获取相册列表，请稍候...</p></ul></div><div class="page_nav" id="page_turner" style="display:none;"></div>';
QPHOTO.album.csListFrameTpl = '<div class="bor3 photo_albumlist_tit js_albumclass_container" csId="<%=cs.id%>"><h4 class="bg js_albumclass_title"><%=cs.name%><span class="c_tx3">（<%=d.total%>）</span></h4></div><div class="clear photo_albumlist"><ul id="cs_list_<%=cs.id%>"><%=d.html.join("")%></ul></div><% if (d.total > csMax){%><p onclick="myView.callExt(&quot;csShowMore&quot;,[&quot;<%=cs.id%>&quot;]);return false;" id="cs_more_<%=cs.id%>"  class="photo_more"><a href="javascript:void(0);"onclick="QZONE.event.cancelBubble();myView.callExt(&quot;csShowMore&quot;,[&quot;<%=cs.id%>&quot;]);return false;" title="展开全部<%=d.total%>个相册" class="bor3 bg3 c_tx">展开全部<%=d.total%>个相册<span class="symbol">&darr;</span></a></p><% }%>';
QPHOTO.album.diskInfoTpl = '<% if (hasdiskinfo){%><div class="infomation"><div class="volume">   容量：<span><%=used%>/<%=total%></span>&nbsp;   <div class="volume_pop"><a href="javascript:void(0);" class="details" onclick="DiskSpace.switchCotentDetail();return false;">详情</a><div id="disk_detail_bubble" class="photo_bubble photo_progress_bubble" style="display:none;"><div class="bubble_i"><p class="">您当前拥有<%=totalalbums%>个相册</p><span class="line"></span><p>容量<%=total%>，已用<%=used%></p><% if (qzonevipStatus){%><p id="yellowlink"></p><a href="../link_admin.html" class="tcisd_home" tcisdkey="rongliang.guanliwailian">管理历史外链</a><% }%></div><b class="arrow_top"><b class="arw"></b><b class="arw_inner"></b></b></div></div></div><p>黄钻最大1T&nbsp;&nbsp;   <a href="http://pay.qq.com/qzone/index.shtml?aid=photo.upload" target="_blank" class="tcisd_home" tcisdkey="rongliang.kaitong"><% if (!qzonevipStatus){%>     开通黄钻    <% }else{%>     续费黄钻    <% }%></a></p></div><% }%>';
QPHOTO.album.emptyGuideTpl = '<div class="show_master" style="display:<%=(ownerDisplay?\'\':\'none\')%>"><h3 class="hide_indent">空间相册记录您的影像生活</h3><p class="hide_indent">超大容量：最大1T容量</p><p class="hide_indent">极速上传：每次可上传300张</p><p class="hide_indent">个性玩法：在线美化 、个性相册</p><p class="hide_indent">好友互动：批量圈人 、分享转载</p><a href="javascript:void(0)" onclick="AlbumEmptyGuide.hotClick(&quot;upload&quot;);AlbumEmptyGuide.upload();return false" title="上传照片" class="upload_photo hide_indent">上传照片</a></div><div class="show_guest" style="display:<%=(guestDisplay?\'\':\'none\')%>"><div class="no_photo"><p><i class="icon icon_nophoto"><i></i></i>唉~白来一趟，主人暂无公开照片</p></div></div><%if(friend){%><div class="recommended_act"><%if(ownerDisplay){%><p class="title">好友最近上传的照片<a href="/qzone/client/photo/pages/qzone_v4/fri_user_list.htm">查看更多</a></p><%}else{%><p class="title">没关系~去看看其他好友的照片吧:)<a href="http://user.qzone.qq.com/<%=loginUin%>/photo/friend" target="_blank">查看更多</a></p><%}%><ul><%for(var i=0,num=Math.min(friendmax,friend.length);i<num;i++){%><li><div class="li"><a href="http://user.qzone.qq.com/<%=friend[i].uin%>/photo/<%=friend[i].albumid%>/<%=friend[i].photo[0].lloc%>" onclick="AlbumEmptyGuide.hotClick(&quot;recommend&quot;)" target="_blank" class="img_link" title="<%=escHTML(friend[i].photo[0].title)%>"><img src="about:blank" onerror="AlbumEmptyGuide.loadImg(this,&quot;<%=QPHOTO.img.getImgUrl(friend[i].photo[0].smallurl,\'m\')%>&quot;,140,140)" alt="<%=escHTML(friend[i].photo[0].title)%>" style="display:none" /></a><div class="photo_act_layer"><p class="layer_bg"></p><p class="numline from">来自：<a href="http://user.qzone.qq.com/<%=friend[i].uin%>" onclick="AlbumEmptyGuide.hotClick(&quot;recommend&quot;)" target="_blank" class="text_link textoverflow q_namecard" link="nameCard_<%=friend[i].uin%>" title="<%=escHTML(friend[i].shownick)%>"><%=escHTML(friend[i].shownick)%></a></p></div></div></li><%}%></ul></div><%}else if(act){%><div class="recommended_act"><%if(ownerDisplay){%><p class="title">大家分享的主题照片<a href="/qzone/photo/activity/activity_list.html">查看更多</a></p><%}else{%><p class="title">没关系~去看看大家分享的照片吧:)<a href="http://user.qzone.qq.com/<%=loginUin%>/photo/activity" target="_blank">查看更多</a></p><%}%><ul><%for(var i=0,num=Math.min(actmax,act.length);i<num;i++){%><li><div class="li"><a href="http://rc.qzone.qq.com/photo/activity/<%=act[i].id%>" onclick="AlbumEmptyGuide.hotClick(&quot;recommend&quot;)" target="_blank" class="img_link" title="<%=escHTML(act[i].name)%>"><img src="about:blank" onerror="AlbumEmptyGuide.loadImg(this,&quot;<%=act[i].coverurl%>&quot;,140,140)" alt="<%=escHTML(act[i].name)%>" style="display:none" /></a><a href="http://rc.qzone.qq.com/photo/activity/<%=act[i].id%>" onclick="AlbumEmptyGuide.hotClick(&quot;recommend&quot;)" target="_blank" class="text_link textoverflow" title="<%=escHTML(act[i].name)%>"><%=escHTML(act[i].name)%></a><div class="photo_act_layer"><p class="layer_bg"></p><p class="numline"><span class="partake">参与<%=(act[i].joincount<=99999)?act[i].joincount:99999%></span><span class="photo_num">照片<%=(act[i].photocount<=99999)?act[i].photocount:99999%></span></p></div></div></li><%}%></ul></div><%}%>';
var News = (function() {
	var Q = QPHOTO;
	var cmtTypeMap = {
		0 : "评论",
		1 : "相册",
		2 : "圈圈",
		3 : "评论"
	}
	var _nodes = [];
	var _inited = false;
	var _numPerPage = 5;
	var _turner = null;
	var _loginUin = QZONE.FP.getQzoneConfig("loginUin");
	var tpl = [
			'<li id="recentcmt_<%=@uin|%>">',
			'<p class="info_bar">',
			'<a href="<%=@url|%>" target="_blank;" title="<%=@nick|escHTML%>" class="c_tx name <%=@remarkclass|%> q_namecard tcisd_home" tcisdkey="lastedcomment.nickname" link="<%=@linkUrl|%>"><%=@nick|escHTML%></a>',
			'<span class="c_tx3 time">&nbsp;&nbsp;<%=@datetime|News.formatFriendPhotoTime%>评论：</span>',
			'<span class="operate" style="display:none;">',
			'&nbsp;&nbsp;<a href="##" class="c_tx tcisd_home" tcisdkey="lastedcomment.reply" tilte="回复" onclick="News.reply(\'<%=index%>\');return false;">回复</a>',
			'&nbsp;&nbsp;<a href="##" class="c_tx author-display tcisd_home" tcisdkey="lastedcomment.delete" tilte="删除" onclick="News.del(\'<%=index%>\');return false;">删除</a>',
			'</span>',
			'</p>',
			'<div class="photo_panel_recentcomments_img">',
			'<a href="javascript:void(0)" target="_blank" title="" class="imglink tcisd_home" tcisdkey="lastedcomment.smallpic" onclick="News.enter(\'<%=@albumid|%>\',\'<%=@lloccode|%>\',\'<%=@type|%>\');return false;">',
			'<img src="/ac/b.gif" onload="this.onload=null;QPHOTO.img.newpreLoad(this,\'<%=@smallurl|%>\',49,37,1,false,null,(function(o){return function(){o.style.marginLeft = -Math.round((parseInt(o.style.width,10)-49)/2) + \'px\';}})(this));" alt="demo" />',
			'</a>',
			'<p class="description" title="<%=@trimcontent|News.formatCommentContentForTitle%>"><%=@trimcontent|News.formatCommentContent%></p>',
			'</div>', '</li>'].join("");
	var _emText = new Array('微笑', '撇嘴', '色', '发呆', '得意', '流泪', '害羞', '闭嘴', '睡',
			'大哭', '尴尬', '发怒', '调皮', '呲牙', '惊讶', '难过', '酷', '冷汗', '抓狂', '吐',
			'偷笑', '可爱', '白眼', '傲慢', '饥饿', '困', '惊恐', '流汗', '憨笑', '大兵', '奋斗',
			'咒骂', '疑问', '嘘', '晕', '折磨', '衰', '骷髅', '敲打', '再见', '擦汗', '抠鼻',
			'鼓掌', '糗大了', '坏笑', '左哼哼', '右哼哼', '哈欠', '鄙视', '委屈', '快哭了', '阴险',
			'亲亲', '吓', '可怜', '菜刀', '西瓜', '啤酒', '篮球', '乒乓', '咖啡', '饭', '猪头',
			'玫瑰', '凋谢', '示爱', '爱心', '心碎', '蛋糕', '闪电', '炸弹', '刀', '足球', '瓢虫',
			'便便', '月亮', '太阳', '礼物', '拥抱', '强', '弱', '握手', '胜利', '抱拳', '勾引',
			'拳头', '差劲', '爱你', 'NO', 'OK', '爱情', '飞吻', '跳跳', '发抖', '怄火', '转圈',
			'磕头', '回头', '跳绳', '挥手', '激动', '街舞', '献吻', '左太极', '右太极');
	function _ubbText(v) {
		try {
			v = v.replace(/\n/g, "").replace(/\[em\]e(\d{1,3})\[\/em\]/g,
					function() {
						return "\\" + _emText[arguments[1] - 100];
					});
			return v;
		} catch (e) {
		};
		return v;
	}
	function _ubbHtml(v) {
		try {
			v = v.replace(/\n/g, "").replace(/\[em\]e(\d{1,3})\[\/em\]/g,
					function($0, code) {
						return '<img class="emoji_img" widht="16" height="16" style="vertical-align:top" src="http://qzonestyle.gtimg.cn/qzone/em/e'
								+ code + '.gif" />';
					});
			return v;
		} catch (e) {
		};
		return v;
	}
	function $loadJs(url, sucCb, errCb, charset) {
		var l = new QZFL.JsLoader();
		if (typeof sucCb == "function") {
			l.onload = sucCb;
		}
		if (typeof errCb == "function") {
			l.onerror = errCb;
		}
		l.load(url, null, (charset || "utf-8"));
	};
	function $nameCard(dom) {
		var url = (QZONE.FP._t.g_OFPLite || QZONE.FP._t.g_isLite)
				? "/qzone/v5/namecardv2.js"
				: "/qzone/v5/namecard.js";
		if (QZFL.namecard) {
			QZFL.namecard.init(dom);
		} else {
			$loadJs(url, function() {
						QZFL.namecard.init(dom);
					});
		}
	}
	var inner;
	function isOwner() {
		return (QZONE.FP.getQzoneConfig().loginUin != 0)
				&& (QZONE.FP.getQzoneConfig().ownerUin == QZONE.FP
						.getQzoneConfig().loginUin);
	}
	function _showCmtList(d) {
		var container = $("news_container");
		if (!container) {
			return;
		}
		if (!(d && d.code == 0)) {
			_displayErr();
		}
		_nodes = d.data.comments || [];
		Page.hasRecentCmt = !!_nodes.length;
		if (_nodes.length > 0) {
			if (_nodes.length > _numPerPage) {
				_turner = new QPHOTO.widget.Turner({
					"type" : 2,
					"_c" : 1,
					"tn" : _nodes.length,
					"npp" : _numPerPage,
					"panel" : $("recentcmt_page_turner"),
					"content" : ('<div class="mod_pagenav" style="text-align:left;padding-left:0px;">'
							+ '<p class="mod_pagenav_main">'
							+ '{previous}{first}' + '{pager}' + '{last}{next}' + '</p>')
				}, {
					"onChange" : function(i) {
						var domain = "photoclick.qzone.qq.com";
						var path = "ablumlist_2012";
						if (QPHOTO.status.isOwner()) {
							QZONE.FP._t.TCISD.hotClick(
									"2012.ablumlist.rencentcomment.page",
									domain, path);
						} else {
							QZONE.FP._t.TCISD.hotClick(
									"2012.ablumlist.rencentcomment.page",
									domain, path);
						}
						_showPage(i - 1);
					}
				});
				$show("recentcmt_page_turner");
			} else {
				$hide("recentcmt_page_turner");
			}
			_showPage(0);
		} else {
			container.innerHTML = "<p>暂无最新评论</p>"
		}
		var isShowVisitor = false;
		QZONE.FP.getSecondaryBitMapFlag(function(a, b) {
					var rightBits = parseInt(a.substr(13, 1), 16);
					rightBits = rightBits & 0x0003;
					if (rightBits == 0 || rightBits == 1) {
						isShowVisitor = true;
					}
					var isThreeDay = !_nodes.length
							|| (new Date().getTime())
									- (new Date(_nodes[0].datetime.replace(
											/-/g, '/')).getTime()) > 1000 * 60
									* 60 * 24 * 3;
					var owner = isOwner();
					if ((owner && isThreeDay) || (!owner && isShowVisitor)) {
						if (owner) {
							Page.showRecentVisitor();
						} else {
							if (Page.hasRecentCmt) {
								Page.showRecentCmt();
							} else {
								Page.showRecentVisitor();
							}
						}
					}
				}, 0, 64);
	}
	function _showPage(pageIndex) {
		var html = [];
		var uinArray = [];
		var start = pageIndex * _numPerPage;
		var end = Math.min(start + _numPerPage, _nodes.length);
		for (var i = start; i < end; i++) {
			if (_nodes[i].source == 0) {
				_nodes[i].url = "http://user.qzone.qq.com/" + _nodes[i].uin;
				_nodes[i].linkUrl = "nameCard_" + _nodes[i].uin;
				uinArray.push(_nodes[i].uin);
			} else {
				_nodes[i].url = _nodes[i].posturl;
				_nodes[i].linkUrl = "";
			}
			_nodes[i].trimcontent = _nodes[i].content;
			html.push(Q.util.tplReplace(tpl, _nodes[i], i + 1));
		}
		var container = $("news_container");
		if (!container) {
			return;
		}
		$e(container).find('.tcisd_home').unBind("click", Page.tcisdStatsByDom);
		$e(container).find("li").unBind("hover");
		container.innerHTML = html.join("");
		$e(container).find('.tcisd_home').bind("click", Page.tcisdStatsByDom);
		$e(container).find("li").onHover(function() {
			if (QPHOTO.status.isOwner()
					|| (_loginUin != 0 && this.getAttribute("id") == "recentcmt_"
							+ _loginUin)) {
				$e(this).find(".time").hide();
				$e(this).find(".operate").show();
			}
		}, function() {
			if (QPHOTO.status.isOwner()
					|| (_loginUin != 0 && this.getAttribute("id") == "recentcmt_"
							+ _loginUin)) {
				$e(this).find(".time").show();
				$e(this).find(".operate").hide();
			}
		});
		$nameCard(container);
	}
	function _charTrim(str, len, needPostfix) {
		var halflen = Math.floor(len / 2);
		var result = [];
		var prefix;
		if (len && getRealLen(str) > len) {
			var prefix = str.substr(0, halflen);
			var curLen = getRealLen(prefix);
			var pad = str.substr(halflen);
			var a = pad.split("");
			var iLen = a.length;
			for (var i = 0; i < iLen; i++) {
				if (getRealLen(prefix + a[i]) > len) {
					return (needPostfix ? (prefix.substring(0, prefix.length
									- 1) + "...") : prefix);
				} else {
					prefix += a[i];
				}
			}
			return (needPostfix
					? (prefix.substring(0, prefix.length - 1) + "...")
					: prefix);
		} else {
			return str;
		}
	}
	function _displayErr() {
		var container = $("news_container");
		if (!container) {
			return;
		}
		container.innerHTML = "正在读取评论...";
	}
	function _getNews() {
		var url = Q.domain.getUrl_v2({
					"key" : "recent_cmt",
					"uin" : QZONE.FP.getQzoneConfig().ownerUin
				});
		var data = {
			"xId" : "recent_cmt_" + QZONE.FP.getQzoneConfig().ownerUin,
			"hostUin" : QZONE.FP.getQzoneConfig().ownerUin,
			"url" : url,
			"refresh" : false,
			"callback" : _showCmtList,
			"errCallback" : _displayErr
		};
		QPHOTO.util.dataLoader_v2(data);
	}
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(argv) {
				var argv = argv || {};
				var mod = argv.mod || mod;
				if (mod == "album" && !_inited) {
					_inited = true;
					News.get();
				}
			});
	PAGE_EVENT.addEvent("CALL_SHOW_RECENT_VISITOR", function() {
				$e('#title_recent_cmt span').setStyle("display", "");
				$e('#title_recent_cmt a').setStyle("display", "");
			});
	return inner = {
		get : function() {
			_getNews();
		},
		del : function(index) {
			if (!Q.zoneStatus.checkSessionUin()) {
				return;
			}
			if (index == null || index < 1 || index > _nodes.length) {
				return;
			}
			var currentNode = _nodes[index - 1];
			var albumid = currentNode.albumid;
			var lloccode = currentNode.lloccode;
			var type = currentNode.type;
			var orderid = currentNode.orderid;
			var strHTML = '<iframe id="recentDelTips" frameborder="0" src="/qzone/photo/zone/delRecentCmt.html" style="width:100%;height:165px"></iframe>';
			QZONE.FP.popupDialog("删除提醒", strHTML, 400, 165);
			News.getRecentInfo = function() {
				return {
					"aid" : albumid,
					"lloc" : lloccode,
					"type" : type
				}
			}
			News.doDel = function() {
				function sucCb(d) {
					QZONE.FP.showMsgbox("该评论已不在相册首页显示.", 4, 2000)
					Array.prototype.splice.call(_nodes, index - 1, 1);
					var len = _nodes.length;
					for (var i = index - 1; i < len; i++) {
						_nodes[i].orderid--;
					}
					if (index > 3) {
						showAllNews = true;
					}
					inner.get();
				}
				var data = {
					"hostUin" : QZONE.FP.getQzoneConfig().ownerUin,
					"url" : Q.domain.getUrl_v2({
								key : "recent_cmt_del"
							}),
					"data" : {
						orderid : orderid
					},
					"callback" : sucCb
				}
				QPHOTO.util.dataSender_v2(data);
			}
		},
		reply : function(index) {
			if (index == null || index < 1 || index > _nodes.length) {
				return;
			}
			var currentNode = _nodes[index - 1];
			function showReplayDialog() {
				QPHOTO.comment.openReplyDialog({
							uin : QZONE.FP.getQzoneConfig().loginUin,
							hostUin : QZONE.FP.getQzoneConfig().ownerUin,
							albumid : currentNode.albumid,
							lloc : currentNode.lloccode,
							dgyjId : currentNode.type === 3
									? currentNode.lloccode
									: null,
							type : currentNode.type === 3
									? QPHOTO.comment.type.FLASH
									: QPHOTO.comment.type.PHOTO,
							data : {
								commentId : currentNode.cmt_id,
								sloc : currentNode.sloccode,
								nick : QPHOTO.status.inPengyou ? QZONE.PY
										.getLoginName() : null
							},
							listeners : {
								onPosted : function(d) {
									QZONE.FP.showMsgbox("回复成功", 4, 1500);
								}
							}
						});
			}
			if (QPHOTO.status.isOwner()
					|| (_loginUin != 0 && currentNode.uin == _loginUin)) {
				if (QPHOTO.comment.popupCommentBox) {
					showReplayDialog();
				} else {
					QZFL.imports(["/qzone/photo/zone/new/script/comment.js"],
							function() {
								showReplayDialog();
							});
				}
			}
		},
		enter : function(aid, lloc, type) {
			if (type == 3) {
				go('flashView.html', null, null, "fid=" + lloc);
				return;
			} else if (type == 1) {
				go("photoList.html", aid);
				return;
			}
			myView.enter(aid, lloc, true);
		},
		formatNewsHint : function(type, node) {
			switch (type) {
				case 0 :
				case 3 :
					return "由[" + node.nick + "]于" + node.datetime + "发表";
				case 1 :
					return "新上传照片于" + node.datetime;
				case 2 :
					return "照片[" + node.nick + "]添加了圈圈";
			}
		},
		formatCmtType : function(type) {
			type = type - 0;
			return cmtTypeMap[type];
		},
		formatFriendPhotoTime : function(time) {
			var timeInfo = time.split(" ");
			var dateInfo = timeInfo[0].split("-");
			var dayInfo = timeInfo[1].split(":");
			time = new Date(dateInfo[0], dateInfo[1] - 0 - 1, dateInfo[2],
					dayInfo[0], dayInfo[1], dayInfo[2]).getTime()
					/ 1000;
			var nt;
			var nt = (new Date().getTime() / 1000);
			var ut = time;
			var vt = nt - ut;
			if (vt <= 0) {
				return '刚刚';
			}
			var date = new Date(ut * 1000);
			var now = new Date(nt * 1000);
			if (vt < 60) {
				return Math.floor(vt) + "秒前";
			} else if (vt < 60 * 60) {
				return Math.floor(vt / 60) + "分钟前";
			} else if (vt < 60 * 60 * 24) {
				if (date.getDate() == now.getDate()) {
					return "今天"
							+ date.getHours()
							+ ":"
							+ (date.getMinutes() > 9 ? date.getMinutes() : ("0"
									+ "" + date.getMinutes()));
				} else {
					return "昨天"
							+ date.getHours()
							+ ":"
							+ (date.getMinutes() > 9 ? date.getMinutes() : ("0"
									+ "" + date.getMinutes()));
				}
			} else if (vt < 60 * 60 * 24 * 2) {
				if (date.getDate() == (new Date(nt * 1000 - 60 * 60 * 24 * 1000
						- 1)).getDate()) {
					return "昨天"
							+ date.getHours()
							+ ":"
							+ (date.getMinutes() > 9 ? date.getMinutes() : ("0"
									+ "" + date.getMinutes()));
				} else {
					return "前天"
							+ date.getHours()
							+ ":"
							+ (date.getMinutes() > 9 ? date.getMinutes() : ("0"
									+ "" + date.getMinutes()));
				}
			} else if (vt < 60 * 60 * 24 * 3) {
				if (date.getDate() == (new Date(nt * 1000 - 60 * 60 * 24 * 2
						* 1000 - 1)).getDate()) {
					return "前天"
							+ date.getHours()
							+ ":"
							+ (date.getMinutes() > 9 ? date.getMinutes() : ("0"
									+ "" + date.getMinutes()));
				} else if ((new Date(nt * 1000).getFullYear()) == date
						.getFullYear()) {
					return (date.getMonth() + 1) + "月" + date.getDate() + "日";
				} else {
					return (date.getFullYear() + "-" + (date.getMonth() + 1)
							+ "-" + date.getDate());
				}
			} else if ((new Date(nt * 1000).getFullYear()) == date
					.getFullYear()) {
				return (date.getMonth() + 1) + "月" + date.getDate() + "日";
			} else {
				return (date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date
						.getDate());
			}
		},
		formatCommentContentForTitle : function(v, data) {
			var reg = /@{uin:(\w+),nick:(.+?)(?:,who:\d)?(?:,auto:\d)?}/g;
			RegExp.lastIndex = 0;
			var includeFriendInfo = false;
			v = escHTML(v).replace(reg, function(a, b, c) {
						includeFriendInfo = true;
						if (isNaN(b)) {
							return '@' + c.trueEntityReplace() + '';
						} else {
							return '@' + c.trueEntityReplace() + '';
						}
					});
			v = _ubbText(v);
			return v;
		},
		formatCommentContent : function(v, data) {
			var reg = /@{uin:(\w+),nick:(.+?)(?:,who:\d)?(?:,auto:\d)?}/g;
			RegExp.lastIndex = 0;
			var includeFriendInfo = false;
			v = escHTML(v).replace(reg, function(a, b, c) {
				includeFriendInfo = true;
				if (isNaN(b)) {
					return '<a href="http://pengyou.qq.com/index.php?mod=profile&u='
							+ b
							+ '" target="_blank">@'
							+ c.trueEntityReplace()
							+ '</a>';
				} else {
					return '<a href="http://user.qzone.qq.com/' + b
							+ '" target="_blank">@' + c.trueEntityReplace()
							+ '</a>';
				}
			});
			v = _ubbHtml(v);
			return v;
		}
	}
})();
var AlbumRecentVisitors = (function() {
	var visitorNumPerLine = 9;
	var maxVisitorNum = 24;
	var totalVisitorNum = 0;
	var visitorModule = null;
	function init() {
		var ownerUin = QZONE.FP.getQzoneConfig().ownerUin;
		var aid = QPHOTO.util.getParameter("aid");
		var jsLoader = new QZONE.jsLoader();
		jsLoader.onload = function() {
			QZONE.module.Visitor.setup({
						"type" : "photo",
						"container" : $('visitor_list'),
						"first_page_item" : visitorNumPerLine,
						"onLoad" : getVisitorCallback,
						"onDelete" : changeVisitotCallback
					});
		};
		jsLoader.load("/qzone/v6/friend_manage/module/visitor.js", document,
				"utf-8");
	};
	function getVisitorCallback(ins) {
		visitorModule = ins;
		var visitorNum = ins.length();
		totalVisitorNum = ins.length();
		if (!QPHOTO.status.isOwner() && visitorNum == 0
				&& Page.hasRecentCmt === false) {
			$e('.photo_panel_recentvisitor').hide();
			$e('.photo_panel_recentcomments').hide();
		}
	};
	function changeVisitotCallback(ins) {
		visitorModule = ins;
		var visitorNum = ins.length();
		totalVisitorNum = ins.length();
	};
	function handleInit() {
		AlbumRecentVisitors.init();
	}
	PAGE_EVENT.addEvent("NOTIFY_SHOW_VISITOR", function() {
				PAGE_EVENT.removeEvent("NOTIFY_SHOW_VISITOR", handleInit);
				handleInit();
			});
	return {
		"init" : init,
		"handleInit" : handleInit
	}
})();
var AuditComment = (function() {
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(argv) {
				var argv = argv || {};
				var mod = argv.mod || "";
				if (QPHOTO.status.isOwner()) {
					AuditComment.init();
				}
			});
	return {
		"init" : function() {
			QZONE.FP.getSecondaryBitMapFlag(function(w, v) {
				if (v != 0) {
					var url = "http://b.qzone.qq.com/cgi-bin/blognew/blog_get_unverifynum?uin="
							+ QZONE.FP.getQzoneConfig().ownerUin
							+ "&needshuoshuo=0&needblog=0&needmsgb=0&needphoto=1&t="
							+ Math.random();
					QPHOTO.util.loadJsonData("photoAuditCommentCount", url,
							function(d) {
								if (d.error) {
								}
								if (d.data) {
									$e('#audit_info_p .tcisd_home').unBind(
											"click", Page.tcisdStatsByDom);
									$e("#audit_info_p")
											.setHtml('<a class="c_tx tcisd_home" tcisdkey="lastedcomment.verifycommnet" href="/qzone/photo/zone/photoAdmin.html">待审核评论('
													+ d.data.photo_num
													+ ')</a>');
									$e('#audit_info_p .tcisd_home').bind(
											"click", Page.tcisdStatsByDom);
								}
							}, QZFL.emptyFn, false, "GB2312", "_Callback");
				} else {
					$e("#audit_info_p")
							.setHtml('您暂未开启<a class="unline" href="/qzone/newblog/v5/auditcanvas.html?type=photo">评论审核功能</a>');
				}
			}, 24, 1, QZONE.FP.getQzoneConfig().ownerUin)
		}
	}
})();
var csSelector = (function() {
	var Q = QPHOTO;
	var uin = QZONE.FP.getQzoneConfig().ownerUin;
	var t = this;
	var _a = new Q.album.MyAlbumList(uin, {
				"onRequest" : QZFL.emptyFn,
				"onSuccess" : QZFL.emptyFn
			});
	function handle_me_init(argv) {
		csSelector.init();
	}
	PAGE_EVENT.removeEvent("NOTIFY_ALBUM_LOADED", handle_me_init);
	PAGE_EVENT.addEvent("NOTIFY_ALBUM_LOADED", handle_me_init);
	function handle_change_csid(argv) {
		return;
	}
	PAGE_EVENT.removeEvent("NOTIFY_ALBUM_CURRENTCSID", handle_change_csid);
	PAGE_EVENT.addEvent("NOTIFY_ALBUM_CURRENTCSID", handle_change_csid);
	PAGE_EVENT.addEvent("NOTIFY_ALBUM_DELETE", function(id) {
				_delAlbum(id);
				_update();
				_bindAction();
			});
	PAGE_EVENT.addEvent("NOTIFY_ALBUM_MODIFY", function(id) {
				_update();
				_bindAction();
			});
	t.album = [];
	t.loaded = false;
	function _delAlbum(id) {
		for (var i = 0, len = t.album.length; i < len; ++i) {
			if (t.album[i].id == id) {
				t.album.splice(i, 1);
				return;
			}
		}
	}
	function _init() {
		_bindAction();
	}
	function _update() {
		return;
	}
	function _showList() {
		return;
	}
	function _hideList(e) {
		return;
	}
	function _setFilter(csid) {
		changeShowAll(csid);
		PAGE_EVENT.fireEvent("NOTIFY_CHANGE_CSID", {
					csid : csid
				});
	}
	function changeShowAll(csid) {
		var cs = QPHOTO.album.getClassList(QZONE.FP.getQzoneConfig('ownerUin'));
		var isShowAll = !!csid;
		for (var i = 0, len = cs.length; i < len; ++i) {
			csSelector.isShowAll[cs[i].id] = isShowAll;
		}
	}
	function _bindAction() {
		return;
	}
	function _get() {
		_a.get({
					"cb" : function(d) {
						t.album = d.album;
						t.loaded = true;
						_update();
					},
					"ecb" : function(d) {
						t.loaded = false;
					},
					"type" : (Q.zoneStatus.isOwner() ? 7 : 3),
					"preurlspec" : frameElement.g_preurlspec
				});
	}
	return inner = {
		"init" : _init,
		changeShowAll : changeShowAll,
		'isShowAll' : []
	}
})();
var HotSearch = (function() {
	function search(tag) {
		if (tag == "输入照片关键词" || trim(tag) == "") {
			QZONE.FP.showMsgbox("请先输入要搜的词", 3, 1500);
			return false;
		}
		QPHOTO.data.save("hotsearch", tag);
		QPHOTO.data.save("searchType", searchType);
		QPHOTO.util.setScroll(0);
		QPHOTO.util.go("search.html");
	}
	function bindEvent() {
		var btn = $("search_btn"), input = $("search_input");
		QZFL.event.addEvent(btn, "click", function() {
					QZFL.event.cancelBubble();
					QZFL.event.preventDefault();
					Page.tcisdStats('rightsidebar.activesearch');
					searchType = "search";
					search(trim(input.value));
				});
		QZFL.event.addEvent(input, "focus", function() {
					if (trim(input.value) == "输入照片关键词") {
						input.value = ""
					}
					$e(input).getParent().addClass("input_focus");
					$e(input).removeClass("default")
				});
		QZFL.event.addEvent(input, "blur", function() {
					if (trim(input.value) == "") {
						input.value = "输入照片关键词"
					}
					$e(input).getParent().removeClass("input_focus");
					$e(input).addClass("default")
				});
		QZFL.event.addEvent(input, "keydown", function(e) {
					var e = QZFL.event.getEvent(e);
					if (e.keyCode == 13) {
						Page.tcisdStats('rightsidebar.activesearch');
						searchType = "search";
						search(trim(input.value));
					}
				});
	}
	function init() {
		try {
			bindEvent();
		} catch (e) {
		};
	}
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(argv) {
				var argv = argv || {};
				var mod = argv.mod || "";
				if (QPHOTO.status.inQzone) {
					HotSearch.init();
				}
			});
	return {
		"init" : init,
		"search" : search
	}
})();
var DiskSpace = (function() {
	var Q = QPHOTO;
	var qqVipMap = {
		0 : 0,
		1 : 5,
		2 : 10,
		3 : 15,
		4 : 20,
		5 : 25,
		6 : 50
	};
	var qzoneVipMap = {
		0 : 0,
		1 : 5,
		2 : 10,
		3 : 15,
		4 : 20,
		5 : 25,
		6 : 30,
		7 : 50,
		8 : 100
	}
	function _getYellowLinkInfo(refresh, cb) {
		var data = {
			"hostUin" : QZONE.FP.getQzoneConfig().ownerUin,
			"xId" : "yellow_link_info",
			"url" : QPHOTO.domain.getUrl_v2({
						key : "yurl_get"
					}),
			"refresh" : refresh,
			"data" : {
				"noreg" : 1,
				"refresh" : 1
			},
			"callback" : function(d) {
				cb(d.data);
			},
			"errCallback" : function(d) {
				if (d.code == -909) {
				} else {
				}
			}
		}
		QPHOTO.util.dataLoader_v2(data);
	}
	function _refreshDiskInfo() {
		var data = {
			"type" : 1,
			"quota" : _info.total
		};
		var url = QPHOTO.domain.getUrl_v2({
					key : "refresh_space",
					uin : QZONE.FP.getQzoneConfig().loginUin
				});
		QZONE.FP.showMsgbox("正在刷新容量信息，请稍候...", 3, 2000);
		QPHOTO.util.dataSender_v2({
			url : url,
			data : data,
			hostUin : QZONE.FP.getQzoneConfig().loginUin,
			callback : function(d) {
				var _d = d.data || {};
				QZONE.FP.showMsgbox("刷新成功!", 4, 2000);
				_info.disktotal = _d.quota;
				_info.diskused = _d.used;
				_info.qqvip = _d.qqvip;
				_info.qzonevip = _d.qzonevip;
				QPHOTO.data.del("yellow_link_info");
				_setDiskInfo();
				if (QZONE.FP.getVipStatus()) {
					_getYellowLinkInfo(true, function(data) {
								_updateYellowLinkInfo(data);
							});
				}
			},
			errCallback : function(d) {
				QZONE.FP.showMsgbox(d.message.replace(/错误码:(.)+/, ""), 5, 2000);
			}
		});
	}
	function _setDiskInfo() {
		DiskSpace.render();
	}
	function _updateYellowLinkInfo(yellowInfo) {
		$('yellowlink').innerHTML = '流量'
				+ (Math.round(yellowInfo.total * 100) / 100)
				+ 'G/月，已用'
				+ (Math.round(yellowInfo.count / (1024 * 1024 * 1024) * 100) / 100)
				+ 'G';
	}
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(argv) {
				if (QPHOTO.status.isOwner()) {
					DiskSpace.render();
					$e("body").unBind("click", DiskSpace.hideCotentDetail);
					$e("body").bind("click", DiskSpace.hideCotentDetail);
				}
			});
	PAGE_EVENT.addEvent("NOTIFY_ALBUM_LOADED", function(argv) {
				var argv = argv || {};
				_info.totalalbums = argv.total;
				_info.hastotalalbums = true;
				if (QPHOTO.status.isOwner()) {
					setTimeout(function() {
								DiskSpace.get();
							}, 0);
				}
			});
	var _info = {};
	return inner = {
		get : function() {
			Q.util.getCapacityInfo(function(data) {
						_info.diskused = data.diskUsed;
						_info.disktotal = data.diskTotal;
						_info.qqvip = data.qqVip;
						_info.qzonevip = data.qzoneVip;
						_info.hasdiskinfo = true;
						PAGE_EVENT.fireEvent("NOTIFY_GET_DISKINFO", {
									info : _info
								});
						DiskSpace.render();
						seajs.use('photo.v7/module/albumList/diskSpaceReport',
								function(dsr) {
									dsr.report(_info);
								});
					});
		},
		render : function() {
			var template = QPHOTO.album.diskInfoTpl;
			var diskused = _info.diskused || 0;
			var disktotal = _info.disktotal || 0;
			var used = "";
			var total = "";
			var percent = disktotal
					? (Math.round(diskused / disktotal * 10000) / 100)
					: 0;
			function fixCapacityNum(num) {
				num = num > 0 ? num : 0;
				if (num < 10) {
					return 0.01 + 'G';
				} else if (num < 1024 * 1024) {
					return Math.round(num / 1024 * 100) / 100 + 'G';
				} else {
					return Math.round(num / 1024 / 1024 * 100) / 100 + 'T';
				}
			}
			total = fixCapacityNum(disktotal);
			used = fixCapacityNum(diskused);
			var vipclass = "qz_vip_icon_s qz_vip_icon_s_0";
			_info.total = total;
			setTimeout(function() {
				if (!$('disk_info')) {
					return;
				}
				$e('#disk_info .tcisd_home').unBind("click",
						Page.tcisdStatsByDom);
				$('disk_info').innerHTML = QPHOTO.util.tmpl(template, {
							used : used,
							total : total,
							percent : percent,
							percentLength : percent == 0 ? percent : Math.max(
									percent, 12),
							qzonevip : _info.qzonevip,
							qzonevipStatus : QZONE.FP.getVipStatus(),
							showviplink : (_info.qzonevip < 8 || !QZONE.FP
									.getVipStatus()) ? '' : 'none',
							totalalbums : _info.totalalbums,
							hasdiskinfo : (_info.hastotalalbums && _info.hasdiskinfo)
						});
				$e('#disk_info .tcisd_home')
						.bind("click", Page.tcisdStatsByDom);
			}, 0);
		},
		showCotentDetail : function() {
			var bubble = $("disk_detail_bubble");
			if (!bubble) {
				return;
			}
			bubble.style.display = "block";
			if (QZONE.FP.getVipStatus()) {
				_getYellowLinkInfo(false, function(data) {
							_updateYellowLinkInfo(data);
						});
			}
		},
		hideCotentDetail : function() {
			var bubble = $("disk_detail_bubble");
			if (!bubble) {
				return;
			}
			bubble.style.display = "none";
		},
		switchCotentDetail : function() {
			QZFL.event.cancelBubble();
			QZFL.event.preventDefault();
			var bubble = $("disk_detail_bubble");
			if (!bubble) {
				return;
			}
			if (bubble.style.display == "none") {
				inner.showCotentDetail();
			} else {
				inner.hideCotentDetail();
			}
		},
		refresh : function() {
			_refreshDiskInfo();
		}
	}
})();
var AlbumEmptyGuide = (function() {
	var _isOwner = QZONE.FP.getQzoneConfig('isOwner'), _role = _isOwner
			? 'host'
			: 'guest', _hasFrndPhoto = false, _container = null;
	function showGuide(container) {
		if (!container) {
			return;
		}
		_container = container;
		_hasFrndPhoto = false;
		if (QPHOTO.status.inPengyou) {
			showPengyouGuide();
			return;
		}
		_container.style.visibility = 'hidden';
		var data = {
			friend : null,
			friendmax : 4,
			act : null,
			actmax : 4,
			loginUin : QZONE.FP.getQzoneConfig('loginUin'),
			ownerDisplay : _isOwner,
			guestDisplay : !_isOwner
		};
		_container.innerHTML = QPHOTO.util.tmpl(QPHOTO.album.emptyGuideTpl,
				data);
		_container.style.visibility = 'visible';
	}
	function showGuideFrnd(data) {
		_container.innerHTML = QPHOTO.util.tmpl(QPHOTO.album.emptyGuideTpl,
				data);
		_container.style.visibility = 'visible';
		_hasFrndPhoto = true;
		hotClick('pv');
		showNameCard(_container);
	}
	function showGuideAct(data) {
		var dataCenter = QZONE.FP._t.QZONE.dataCenter;
		if (!dataCenter.get('hot_act')) {
			var loader = new QZFL.JSONGetter('http://sh.photo.qq.com/photoshare/hot_act.json');
			loader.onSuccess = function(json) {
				if (json && json.act) {
					dataCenter.save('hot_act', json);
					QPHOTO.extend(data, json);
				}
				_container.innerHTML = QPHOTO.util.tmpl(
						QPHOTO.album.emptyGuideTpl, data);
				_container.style.visibility = 'visible';
				hotClick('pv');
			}
			loader.send("_Callback");
		} else {
			QPHOTO.extend(data, dataCenter.get('hot_act'));
			_container.innerHTML = QPHOTO.util.tmpl(QPHOTO.album.emptyGuideTpl,
					data);
			_container.style.visibility = 'visible';
			hotClick('pv');
		}
	}
	function showPengyouGuide() {
		if (QPHOTO.zoneStatus.isOwner()) {
			_container.innerHTML = '<p class="album_list_loading">您还没有相册，快点<a href="javascript:void(0)" onclick="AlbumEmptyGuide.upload();return false;">上传照片</a>和朋友们一起分享。</p>';
		} else {
			_container.innerHTML = '<div class="album_list_loading">主人还没有传过照片哦</div>';
		}
	}
	function loadImg(obj, src, width, height) {
		width = width || 140;
		height = height || 140;
		function adjustPos(realw, realh) {
			obj.style.width = realw + "px";
			obj.style.height = realh + "px";
			obj.style.display = "";
			obj.style.marginLeft = Math.round((width - realw) / 2) + "px";
			if (realh * 0.8 < height) {
				obj.style.marginTop = Math.round((height - realh) / 2) + "px";
			} else {
				obj.style.marginTop = Math.round(-realh * 0.1) + "px";
			}
		}
		var img = new Image();
		src = src.replace(/\s+$/, '');
		img.onload = function() {
			obj.onload = null;
			obj.onerror = null;
			obj.src = this.src;
			adjustPos(this.width, this.height);
			img = null;
		}
		img.onerror = function() {
			obj.onload = null;
			obj.onerror = null;
			obj.src = '/ac/qzone_v5/photo/photo_none.gif';
			adjustPos(140, 140);
			img = null;
		}
		img.src = src;
	}
	function showNameCard(dom) {
		function loadJs(url, sucCb, errCb, charset) {
			var l = new QZFL.JsLoader();
			if (typeof sucCb == "function") {
				l.onload = sucCb;
			}
			if (typeof errCb == "function") {
				l.onerror = errCb;
			}
			l.load(url, null, (charset || "utf-8"));
		}
		var url = (QZONE.FP._t.g_OFPLite || QZONE.FP._t.g_isLite)
				? "/qzone/v5/namecardv2.js"
				: "/qzone/v5/namecard.js";
		if (QZFL.namecard) {
			QZFL.namecard.init(dom);
		} else {
			loadJs(url, function() {
						QZFL.namecard.init(dom);
					});
		}
	}
	function hotClick(tag) {
		QZONE.FP._t.TCISD.hotClick('blank_album.' + _role + '_'
						+ (_hasFrndPhoto ? 'friend' : 'activity') + '.' + tag,
				'photoclick.qzone.qq.com', 'albumList.html');
	}
	var photoUploadHotClick = function(tag, domain, url, opt) {
		opt = opt || {};
		var _s = QZONE.FP._t.TCISD.hotClick, x = opt.x || 9999, y = opt.y
				|| 9999, doc = opt.doc || document, w = doc.parentWindow
				|| doc.defaultView, p = w._hotClick_params || {};
		url = url || p.url || w.location.pathname || "-";
		domain = domain || p.domain || w.location.hostname || "-";
		QZONE.FP._t.QZFL.pingSender([_s.config.webServerInterfaceURL, "?dm=",
				domain + ".hot", "&url=", escape(url), "&tt=-", "&hottag=",
				tag, "&hotx=", x, "&hoty=", y, "&rand=", Math.random()]
				.join(""));
	};
	function upload() {
		var browser = "others";
		if (ua.ie) {
			browser = "ie";
		} else if (ua.webkit) {
			browser = "webkit";
		} else if (ua.firefox) {
			browser = "firefox";
		}
		var tail = "." + ["albumlist", "unknown", -1, browser, -1].join(".");
		photoUploadHotClick("step1_upload" + tail, "photostream.qzone.qq.com",
				"/upload");
		photoUploadHotClick("step1_uploadnew" + tail,
				"photostream.qzone.qq.com", "/uploadnew");
		QPHOTO.media.upload(null, 'from=albumlist');
	}
	return {
		init : showGuide,
		loadImg : loadImg,
		hotClick : hotClick,
		upload : upload
	}
})();
var FriendPhoto = (function() {
	String.prototype.htmlReplace = function() {
		return this.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g,
				"&gt;").replace(/\'/g, "&#39;").replace(/\"/g, "&quot;");
	};
	String.prototype.unHtmlReplace = function() {
		var s = (this).replace(/&amp;/g, "&").replace(/&lt;/g, "<").replace(
				/&gt;/g, ">").replace(/&nbsp;/g, " ").replace(/&quot;/g, "\"");
		return s.replace(/&#(\d{2});/g, function($0, $1) {
					return unescape("%" + parseInt($1).toString(16));
				});
	};
	var data = {};
	var showData = {
		friend : [],
		index : 0,
		showDataIndexes : []
	};
	function _initData(d, cb) {
		if (d && d.data) {
			d.data.friend = d.data.album;
		}
		showDataIndexs = []
		if (d && d.data && d.data.friend && d.data.friend.length > 0) {
			data.data = d.data;
			showData.index = 0;
			showData.showDataIndexes = [];
			var startIndex = Math
					.floor(Math.random() * data.data.friend.length);
			for (var i = startIndex; i < data.data.friend.length; i++) {
				showData.showDataIndexes.push(i);
			}
			for (var i = 0; i < startIndex; i++) {
				showData.showDataIndexes.push(i);
			}
			showData.friend = [];
			for (var i = 0; i < data.data.friend.length && i < 2; i++) {
				showData.friend
						.push(data.data.friend[showData.showDataIndexes[i]]);
			}
			setTimeout(function() {
						var list = [];
						for (var i = 0; i < d.data.friend.length; i++) {
							var uin = d.data.friend[i].uin;
							if (list[uin] != null) {
								d.data.friend[i].shownick = list[uin];
							} else {
								d.data.friend[i].shownick = (d.data.friend[i].nick)
										.unHtmlReplace();
							}
						}
						if (cb) {
							cb.apply(this)
						};
					}, 0);
		} else {
			if (cb && AlbumEmptyGuide.initialized) {
				cb.apply(this)
			};
		}
	}
	function initData(cb) {
		var dataCenter = QZONE.FP._t.QZONE.dataCenter;
		var _d = dataCenter.get("friend_photo_v6");
		if (typeof _d != 'undefined') {
			_initData.apply(this, [_d, cb]);;
		} else {
			var url = "http://" + QPHOTO.domain.appDomain
					+ "/cgi-bin/app/cgi_list_suggest_friendphoto_v2";
			QPHOTO.util.dataLoader_v2({
						xId : "friend_photo_v6",
						url : url,
						data : {
							"t" : Math.random()
						},
						uin : QZONE.FP.getQzoneConfig().loginUin,
						hostUin : QZONE.FP.getQzoneConfig().ownerUin,
						callback : function(d) {
							dataCenter.save("friend_photo_v6", d);
							_initData.apply(this, [d, cb]);
						},
						errCallback : function(d) {
							_initData.apply(this, [d, cb]);
						}
					});
		}
	}
	return {
		"initData" : initData
	}
})();
(function() {
	var Q = QPHOTO;
	$show = Q.util.$show;
	$hide = Q.util.$hide;
	var _o = Q.nameSpace("album");
	var photoUploadHotClick = function(tag, domain, url, opt) {
		opt = opt || {};
		var _s = QZONE.FP._t.TCISD.hotClick, x = opt.x || 9999, y = opt.y
				|| 9999, doc = opt.doc || document, w = doc.parentWindow
				|| doc.defaultView, p = w._hotClick_params || {};
		url = url || p.url || w.location.pathname || "-";
		domain = domain || p.domain || w.location.hostname || "-";
		QZONE.FP._t.QZFL.pingSender([_s.config.webServerInterfaceURL, "?dm=",
				domain + ".hot", "&url=", escape(url), "&tt=-", "&hottag=",
				tag, "&hotx=", x, "&hoty=", y, "&rand=", Math.random()]
				.join(""));
	};
	function _uploadPhoto(goweb, params) {
		var browser = "others";
		if (ua.ie) {
			browser = "ie";
		} else if (ua.webkit) {
			browser = "webkit";
		} else if (ua.firefox) {
			browser = "firefox";
		}
		var tail = "." + ["albumlist", "unknown", -1, browser, -1].join(".");
		photoUploadHotClick("step1_upload" + tail, "photostream.qzone.qq.com",
				"/upload");
		photoUploadHotClick("step1_uploadnew" + tail,
				"photostream.qzone.qq.com", "/uploadnew");
		if (goweb) {
			Q.media.webUpload(null, 'from=albumlist'
							+ (params ? ("&" + params) : ""));
			return;
		}
		Q.media.upload(null, 'from=albumlist' + (params ? ("&" + params) : ""));
	}
	function _addAlbum(cfg) {
		Q.dialog.addAlbum(null, function(d) {
					setTimeout(function() {
								QZONE.FP
										.confirm(
												"创建成功",
												"相册<strong>"
														+ escHTML(d.album.name)
														+ "</strong>创建成功，是否马上上传照片到这个相册？",
												function() {
													var browser = "others";
													if (ua.ie) {
														browser = "ie";
													} else if (ua.webkit) {
														browser = "webkit";
													} else if (ua.firefox) {
														browser = "firefox";
													}
													var tail = "."
															+ ["createalbum",
																	"unknown",
																	-1,
																	browser, -1]
																	.join(".");
													photoUploadHotClick(
															"step1_upload"
																	+ tail,
															"photostream.qzone.qq.com",
															"/upload");
													photoUploadHotClick(
															"step1_uploadnew"
																	+ tail,
															"photostream.qzone.qq.com",
															"/uploadnew");
													Q.media.upload(d.album.id,
															'from=createalbum');
												}, function() {
													go("photoList.html",
															d.album.id);
												});
							}, 0);
				}, null, cfg);
	}
	function _showSettingBubble() {
		var bubble = $("toolbar_display_bubble");
		if (!bubble) {
			return;
		}
		bubble.style.display = "block";
		$e("#toolbar_display_setting").addClass("photo_toolbar_set_hover");
	}
	function _hideSettingBubble() {
		var bubble = $("toolbar_display_bubble");
		if (!bubble) {
			return;
		}
		bubble.style.display = "none";
		$e("#toolbar_display_setting").removeClass("photo_toolbar_set_hover");
	}
	function _switchSettingBubble() {
		QZFL.event.cancelBubble();
		QZFL.event.preventDefault();
		var bubble = $("toolbar_display_bubble");
		if (!bubble) {
			return;
		}
		if (bubble.style.display == "none") {
			_showSettingBubble();
		} else {
			_hideSettingBubble();
		}
	}
	function _changeViewMode() {
		QZFL.event.cancelBubble();
		QZFL.event.preventDefault();
		var type = this.getAttribute("_type");
		PAGE_EVENT.fireEvent("NOTIFY_CHANGE_VIEWMODE", {
					type : type
				});
	}
	function _changeSortMode() {
		QZFL.event.cancelBubble();
		QZFL.event.preventDefault();
		var type = this.getAttribute("_type");
		PAGE_EVENT.fireEvent("NOTIFY_CHANGE_SORTMODE", {
					type : type
				});
	}
	function _changeCoverMode() {
		QZFL.event.cancelBubble();
		QZFL.event.preventDefault();
		var type = this.getAttribute("_type");
		PAGE_EVENT.fireEvent("NOTIFY_CHANGE_COVERMODE", {
					type : type
				});
	}
	var inner = _o.ToolBar = {
		init : function(mod) {
			if (mod != "album") {
				Q.util.$hide("album_toolbar_div");
				return;
			}
			$e("#album_toolbar_div .js_bindclick").unBind("click");
			$e("#toolbar_add_album").bind("click", function(e) {
						QZFL.event.cancelBubble();
						QZFL.event.preventDefault();
						_addAlbum();
					});
			$e("#toolbar_display_setting").bind("click", _switchSettingBubble);
			$e("#toolbar_display_bubble #toolbar_display_setting_view a").bind(
					"click", _changeViewMode);
			$e("#toolbar_display_bubble #toolbar_display_setting_sort a").bind(
					"click", _changeSortMode);
			$e("#toolbar_display_bubble #toolbar_display_setting_cover a")
					.bind("click", _changeCoverMode);
			$e("#toolbar_display_bubble .item_detail li").unBind();
			$e("#toolbar_display_bubble .item_detail li").onHover(function() {
						$e(this).addClass("bg2");
					}, function() {
						$e(this).removeClass("bg2");
					});
			$e(".tcisd_toolbar").bind("click", inner.tcisdStatsByDom);
			$e("body").unBind("click", _hideSettingBubble);
			$e("body").bind("click", _hideSettingBubble);
		},
		uninit : function() {
			$e("#album_toolbar_div .js_bindclick").unBind("click");
			$e("body").unBind("click", _hideSettingBubble);
			_hideSettingBubble();
		},
		upload : function(goweb, params) {
			_uploadPhoto(goweb, params);
		},
		addAlbum : function(cfg) {
			_addAlbum(cfg);
		},
		refreshBubble : function(key, value) {
			$e("#toolbar_display_bubble #toolbar_display_setting_" + key + " i")
					.each(function() {
								if (this.parentNode.getAttribute("_type") == value) {
									this.style.display = "";
								} else {
									this.style.display = "none";
								}
							});
		},
		tcisdStats : function(key, hostOnly) {
			var domain = "photoclick.qzone.qq.com";
			var path = "ablumlist_2012";
			if (true) {
				var reportkey = key;
				if (hostOnly) {
					reportkey = '2012.ablumlist.operationbar.' + key;
				} else if (QPHOTO.status.isOwner()) {
					reportkey = '2012.ablumlist.operationbar.' + key + ".host";
				} else {
					reportkey = '2012.ablumlist.operationbar.' + key + ".guest";
				}
				QZONE.FP._t.TCISD.hotClick(reportkey, domain, path);
			}
		},
		tcisdStatsByDom : function() {
			inner.tcisdStats(this.getAttribute('tcisdkey'), parseInt(this
							.getAttribute('tcisdhostonly')) == 1);
		}
	};
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(arg) {
				var mod = arg.mod || "album";
				inner.init(mod);
			});
	PAGE_EVENT.addEvent("NOTIFY_PAGE_UNINIT", function(arg) {
				inner.uninit();
			});
	PAGE_EVENT.addEvent("NOTIFY_REFRESH_VIEWMODE", function(arg) {
				inner.refreshBubble("view", arg.type);
			});
	PAGE_EVENT.addEvent("NOTIFY_REFRESH_SORTMODE", function(arg) {
				inner.refreshBubble("sort", arg.type);
			});
	PAGE_EVENT.addEvent("NOTIFY_REFRESH_COVERMODE", function(arg) {
				inner.refreshBubble("cover", arg.type);
			});
	PAGE_EVENT.addEvent("NOTIFY_CLICK_UPLOAD", function(arg) {
				inner.upload();
			});
	PAGE_EVENT.addEvent("NOTIFY_CLICK_ADDALBUM", function(arg) {
				inner.addAlbum(arg);
			});
})();
(function() {
	var Q = QPHOTO;
	$show = Q.util.$show;
	$hide = Q.util.$hide;
	var _o = Q.nameSpace("album");
	var _firstLook = false;
	var numPerPage = (QZONE.FP.getQzoneConfig().loginUin == 468214434)
			? 20
			: 40;
	var taskSystem = Q.util.getParameter("system");
	function _sortFnFactory(attr, desc) {
		if (desc) {
			return function(p1, p2) {
				if (p1[attr] < p2[attr]) {
					return 1;
				} else if (p1[attr] > p2[attr]) {
					return -1;
				} else {
					return 0;
				}
			}
		} else {
			return function(p1, p2) {
				if (p1[attr] > p2[attr]) {
					return 1;
				} else if (p1[attr] < p2[attr]) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	};
	var dragData = {};
	function _initDrag() {
		var t = myView;
		dom = $e('.photo_albumlist li');
		var rect = QZFL.dom.getPosition(dom.elements[0]);
		dragData.top = rect.top;
		dragData.left = rect.left;
		dragData.width = rect.width;
		dragData.height = rect.height;
		dragData.cell = 0;
		dragData.topArr = [rect.top];
		dragData.rowDom = [[]];
		var top = rect.top;
		var top2 = top;
		for (var i = 0, item; item = dom.elements[i++];) {
			if ((top = (QZFL.dom.getPosition(item)).top) == top2) {
				dragData.rowDom[dragData.cell].push(item);
			} else {
				++dragData.cell;
				dragData.rowDom[dragData.cell] = [];
				dragData.rowDom[dragData.cell].push(item);
				top2 = top;
				dragData.topArr.push(top);
			}
		}
		if (t._info.ordermode == 1 && !Q.status.inPengyou) {
			var csid;
			var map = [];
			for (var i = 0, len = t._d.length; i < len; ++i) {
				csid = t._d[i].classid;
				map[csid] = map[csid] || [];
				t._d[i].csIndex = map[csid].length;
				map[csid].push(t._d[i]);
			}
			dragData.map = map;
		}
		if (myView._info.preurlspec == 'i') {
			dragData.bw = 19;
		} else {
			dragData.bw = 20;
		}
		document.onselectstart = function() {
			return false
		}
		document.selection ? document.selection.empty() : window.getSelection()
				.removeAllRanges();
	}
	function _getPosition(top, left) {
		var moveTo = [];
		for (var i = 0, len = dragData.topArr.length; i < len; i++) {
			var tops = dragData.topArr[i];
			var topNext = dragData.topArr[i + 1] || 0;
			if (top <= tops) {
				moveTo[1] = i;
				break;
			} else {
				if (i == len - 1) {
					moveTo[1] = i;
					break;
				} else if (top < topNext) {
					if (top < (tops + topNext) / 2) {
						moveTo[1] = i;
						break;
					} else {
						moveTo[1] = i + 1;
						break;
					}
				}
			}
		}
		moveTo[0] = Math.min(Math.max(Math.ceil((left - dragData.left)
								/ (dragData.width + dragData.bw)), 0),
				dragData.rowDom[moveTo[1]].length);
		return moveTo;
	}
	function _scroll(e) {
		var y = QZFL.event.mouseY(e);
		var p = QZFL.dom.getPosition(QZONE.FP._t.$("tphoto"));
		var s = QZONE.FP.getScrollTop();
		y = (y + p.top - QZONE.FP.getScrollTop());
		var d = dragData.height / 3;
		if (y < d) {
			QZONE.FP.setScrollTop(s - d);
		} else if (y + d > QZONE.FP._t.QZFL.dom.getClientHeight()) {
			QZONE.FP.setScrollTop(s + d);
		}
	}
	var focusTimer = blurTimer = null;
	_o.ListView = new Class({
		Extends : QPHOTO.View,
		options : {
			extUrl : "script/albumExt.js?" + frameElement.g_photoVersion
					+ ".js",
			npp : numPerPage,
			tpl : QPHOTO.album.listTpl
		},
		initialize : function(uin, options) {
			var t = this;
			t._uin = uin;
			t.setOptions(options);
			t._d = [];
			t._info = {};
			t._idx = 1;
			t._csid = 0;
			t.bindEvent();
		},
		bindEvent : function() {
			var t = this;
			PAGE_EVENT.addEvent("NOTIFY_GET_DISKINFO", function(argv) {
						var _info = argv.info;
						if (_info) {
							t._info.diskused = _info.diskused;
							t._info.disktotal = _info.disktotal;
							t._info.qqvip = _info.qqvip;
							t._info.qzonevip = _info.qzonevip;
						}
					});
			PAGE_EVENT.addEvent("NOTIFY_PAGE_UNINIT", function(argv) {
						if (argv.mod == "album") {
							$e('.tcisd_albumlist').unBind("click",
									t.tcisdStatsByDom);
						}
					});
			$e('.tcisd_albumlist').bind("click", t.tcisdStatsByDom);
		},
		display : function(data, aid, csid, orderType, idx) {
			var t = this;
			if (t.needCheckUnbind && QPHOTO.status.inPengyou) {
				QPHOTO.albumtpl.checkUnbindPY
						&& QPHOTO.albumtpl.checkUnbindPY();
			}
			t._csid = csid;
			t._d = data.album;
			if (csid != 0) {
				var tmp = [];
				for (var i = 0; i < data.album.length; i++) {
					if (data.album[i].classid == csid) {
						tmp.push(data.album[i]);
					}
				}
				t._d = tmp;
			}
			if (!Q.status.isOwner()) {
				var tmp = [];
				for (var i = 0; i < t._d.length; i++) {
					if (t._d[i] && t._d[i].total) {
						tmp.push(t._d[i]);
					}
				}
				t._d = tmp;
			}
			t._info = data.info;
			if (typeof orderType != "undefined") {
				switch (orderType) {
					case 1 :
						t._d.sort(_sortFnFactory("lastuploadtime", true));
						break;
					case 2 :
						t._d.sort(_sortFnFactory("createtime", false));
						break;
					case 3 :
						t._d.sort(_sortFnFactory("createtime", true));
						break;
					case 4 :
						t._d.sort(_sortFnFactory("realIndex", false));
						break;
					default :
						break;
				}
			}
			idx = idx || 1;
			if (aid) {
				var i = QPHOTO.util.getIdx(t._d, "id", aid);
				if (i != null) {
					idx = Math.ceil((i + 1) / t.options.npp);
				}
			}
			t.showList(idx);
			if (QPHOTO.noAlbum) {
				t.fireEvent('onAlbumEmpty');
				return;
			}
			t.showInfo();
			t.fireEvent('onDisplay');
			if (!QPHOTO.status.inPengyou && Q.status.isOwner()) {
				t.showSortMode();
				t.showViewMode();
				t.showCoverMode();
			}
		},
		"showViewMode" : function() {
			var t = this;
			var type = t._info.ordermode;
			var strmap = {
				0 : '普通视图',
				1 : '分类视图'
			}
			if (!strmap[type]) {
				type = 0;
			}
			PAGE_EVENT.fireEvent("NOTIFY_REFRESH_VIEWMODE", {
						type : type
					});
		},
		"showCoverMode" : function() {
			var t = this;
			var type = +(t._info.preurlspec == 'a');
			var strmap = {
				1 : '小封面',
				0 : '大封面'
			}
			if (!strmap[type]) {
				type = 0;
			}
			PAGE_EVENT.fireEvent("NOTIFY_REFRESH_COVERMODE", {
						type : type
					});
		},
		"showSortMode" : function() {
			var info = Q.album.getUserInfo(myView._uin);
			var type = info.ordertype;
			var map = {
				4 : 1,
				0 : 2,
				1 : 3,
				2 : 4
			}
			var strmap = {
				4 : '最近上传在前',
				0 : '最新创建在后',
				1 : '最新创建在前',
				2 : (QPHOTO.status.isOwner()) ? '自定义排序' : '主人自定义'
			}
			PAGE_EVENT.fireEvent("NOTIFY_REFRESH_SORTMODE", {
						type : map[type]
					});
		},
		firstLook : function() {
			var t = this;
			if (!_firstLook) {
				_firstLook = true;
				t.fireEvent('onFirstLook');
			}
		},
		showHzLight : function() {
			var t = this;
			if (!$ || !$('act_hz')) {
				setTimeout(function() {
							t.showHzLight();
						}, 500);
			}
			if (QZONE.FP.checkIsDeepColor()) {
				$e('#act_hz').removeClass('act_hz_light');
				$e('#act_hz').addClass('act_hz_dark');
			} else {
				$e('#act_hz').removeClass('act_hz_dark');
				$e('#act_hz').addClass('act_hz_light');
			}
			function hzLoginBack(callback) {
				window.LoginBack = function() {
					if (QZONE.FP.getQzoneConfig().loginUin) {
						if (callback) {
							callback();
						}
					}
				}
				QZONE.FP.showLoginBox("photo")
			}
			var isVip = QZONE.FP.getLoginBitmap(27, 1);
			$e('#act_hz').each(function() {
						this.style.display = "";
					})
			$e('#act_hz .act_hz_tit a').each(function() {
						if (isVip) {
							$e(this).removeClass("btn_kt");
							$e(this).addClass("btn_xf");
						} else {
							$e(this).removeClass("btn_xf");
							$e(this).addClass("btn_kt");
						}
						QZFL.event.addEvent(this, 'click', function() {
									function clickHZ() {
										if (isVip) {
											t
													.tcisdStats('temp.huangzuan.kaitong');
										} else {
											t
													.tcisdStats('temp.huangzuan.xufei');
										}
									}
									clickHZ();
								});
					});
			$e('#act_hz #hz_photo_rongliang').each(function() {
						QZFL.event.addEvent(this, 'click', function() {
									t.tcisdStats('temp.huangzuan.rongliang');
								});
					});
			$e('#act_hz #hz_photo_beifen').each(function() {
				var loginUin = QZONE.FP.getQzoneConfig().loginUin;
				if (!loginUin) {
					this.href = 'http://rc.qzone.qq.com/photo/upload';
				} else {
					this.href = 'http://user.qzone.qq.com/' + loginUin
							+ '/photo/upload';
				}
				QZFL.event.addEvent(this, 'click', function() {
							t.tcisdStats('temp.huangzuan.beifen');
						});
			});
			$e('#act_hz #hz_photo_print').each(function() {
						QZFL.event.addEvent(this, 'click', function() {
									t.tcisdStats('temp.huangzuan.print');
								});
					});
			$e('#act_hz #hz_photo_video').each(function() {
				var loginUin = QZONE.FP.getQzoneConfig().loginUin;
				if (!loginUin) {
					this.href = 'http://rc.qzone.qq.com/qzvideo';
				} else {
					this.href = 'http://user.qzone.qq.com/' + loginUin
							+ '/qzvideo';
				}
				QZFL.event.addEvent(this, 'click', function() {
							t.tcisdStats('temp.huangzuan.video');
						});
			});
		},
		showList : function(idx) {
			var t = this;
			t._idx = idx;
			if (t._d.length == 0) {
				QPHOTO.noAlbum = true;
				t.empty();
				return;
			}
			if (Q.status.isOwner()) {
				Q.util.$show("album_toolbar_div");
				var popupid = Q.util.getParameter('popup');
				var startup = Q.util.getParameter('startup');
				var supportMap = {
					upload20130501 : true,
					baby : true,
					travel : true
				};
				var checkAutoPop = function(callback) {
					if (Q.status.inPengyou) {
						return;
					}
					callback = callback || QZFL.emptyFn;
					seajs
							&& seajs.use(
									['photo.v7/common/api/qboss/ajax.get'],
									function(qboss) {
										qboss && qboss.get({
											board_id : 2195,
											uin : QZONE.FP
													.getQzoneConfig('loginUin')
										}).done(function(res) {
											callback(res.data
													&& res.data.count > 0
													&& res.data['2195']
													&& res.data['2195'].items
													&& res.data['2195'].items.length > 0
													? res.data['2195'].items[0]
													: null);
										});
									});
				};
				var showPopup = function(popupid, checkViewtype) {
					checkViewtype = checkViewtype
							? parseInt(checkViewtype)
							: -1;
					if (checkViewtype > 0) {
						for (var i = 0, l = t._d.length; i < l; i++) {
							if (t._d[i].viewtype == checkViewtype) {
								return;
							}
						}
					}
					location.hash = location.hash.replace('popup=' + popupid,
							'popup=');
					myView.callExt('showPopup', [popupid || "baby"]);
				};
				if (!startup) {
					if (popupid) {
						showPopup(popupid);
					} else {
						checkAutoPop(function(ad) {
									if (ad && ad.extdata) {
										var data = new Function('return '
												+ restHTML(ad.extdata))();
										if (data.text) {
											var infos = data.text.split('_');
											if (infos.length > 0
													&& supportMap[infos[0]]) {
												showPopup(infos[0],
														infos.length == 2
																? infos[1]
																: -1);
											}
										}
									}
								});
					}
				}
			} else {
				Q.util.$hide("album_toolbar_div");
				$e('.js-popup').hide();
			}
			if (t._info.preurlspec == 'a') {
				$e('div.photo_v3').addClass("s_cover");
			} else {
				$e('div.photo_v3').removeClass("s_cover");
			}
			if (t._info.ordermode == 1 && !Q.status.inPengyou) {
				t.csShow();
			} else {
				t.normalShow();
			}
			if (Q.status.isOwner() && Q.status.inQzone) {
			}
		},
		csShow : function() {
			var t = this;
			var map = {};
			var csid;
			t.csMax = t._info.preurlspec == 'a' ? 10 : 8;
			for (var i = 0, len = t._d.length; i < len; ++i) {
				csid = t._d[i].classid;
				map[csid] = map[csid] || {
					total : 0,
					html : [],
					modifytime : 0
				};
				if (map[csid].modifytime < t._d[i].modifytime) {
					map[csid].modifytime = t._d[i].modifytime;
				}
				if (map[csid].total < t.csMax) {
					t._d[i].index = i;
					t._d[i]._self = t._d[i];
					map[csid].html.push(QPHOTO.util.tmpl(t.options.tpl,
							this._d[i], i));
				}
				++map[csid].total;
			}
			var cs = QPHOTO.album.getClassList(t._uin);
			for (var i = 0, len = cs.length; i < len; ++i) {
				csid = cs[i].id;
				if (map[csid]) {
					cs[i].modifytime = map[csid].modifytime;
				} else {
					cs[i].modifytime = 0;
				}
			}
			if (false && t._info.ordertype != 2) {
				cs.sort(function(a, b) {
							return b.modifytime - a.modifytime;
						});
			}
			var result = [], d;
			for (var i = 0, len = cs.length; i < len; ++i) {
				csid = cs[i].id;
				d = map[csid];
				if (d) {
					result = result.concat(QPHOTO.util.tmpl(
							QPHOTO.album.csListFrameTpl, {
								cs : cs[i],
								d : d,
								index : i,
								csMax : t.csMax
							}));
				}
			}
			$e('#album_list_div .tcisd_albumlist').unBind("click",
					t.tcisdStatsByDom);
			result.push(QPHOTO.util.tmpl(QPHOTO.album.bannerTpl, {
						loginUin : QZONE.FP.getQzoneConfig('loginUin'),
						g_tk : QZONE.FP.getACSRFToken()
					}));
			$("album_list_div").innerHTML = result.join('');
			$e('#album_list_div .tcisd_albumlist').bind("click",
					t.tcisdStatsByDom);
			for (var i = 0, len = cs.length; i < len; ++i) {
				csid = cs[i].id;
				if (csSelector.isShowAll[csid]) {
					$('cs_more_' + csid)
							&& myView.callExt("csShowMore", [csid]);
				}
			}
			if (!QPHOTO.status.inPengyou
					&& QZONE.FP.getQzoneConfig("version") >= 6) {
				setTimeout(function() {
							t.showHzLight();
						}, 100);
			}
			this.defineClassName(map, cs);
		},
		defineClassName : function(map, css) {
			if (Q.status.isOwner() && seajs) {
				seajs.use(['photo.v7/lib/jquery',
								'photo.v7/common/albumClassName/index',
								'photo.v7/common/domHelper/input'], function($,
								albumClassName, inputJs) {
							var albumClassContainers = $('.js_albumclass_container');
							for (var i = 0, len = css.length; i < len; ++i) {
								var csid = css[i].id;
								d = map[csid];
								var dom = $('.js_albumclass_container[csId="'
										+ csid + '"]');
								if (d && dom.length != 0) {
									var one = new albumClassName(dom, css[i]);
								}
							}
						});
			}
		},
		normalShow : function() {
			var t = this;
			$e("#album_list_div").setHtml(QPHOTO.album.normalListFrameTpl
					+ QPHOTO.util.tmpl(QPHOTO.album.bannerTpl, {
								loginUin : QZONE.FP.getQzoneConfig('loginUin'),
								g_tk : QZONE.FP.getACSRFToken()
							}));
			t._render(t._idx);
			var turn = new QPHOTO.widget.Turner({
						"_c" : t._idx,
						"tn" : t._d.length,
						"npp" : t.options.npp,
						"panel" : $("page_turner")
					}, {
						"onChange" : (function(_o) {
							return function(_i, _p) {
								_o.tcisdStats("list.changepage");
								_o._render(_i);
								var w = QZONE.FP._t;
								QPHOTO.status.inQzone
										&& QZONE.FP
												.setScrollTop(w.QZFL.dom
														.getPosition('pageApp').top
														- (ua.ie == 6
																? 0
																: w.QZFL.dom
																		.getPosition('QZ_Toolbar_Container').height));
							}
						})(this)
					});
			if (QPHOTO.status.isOwner() && t._d.length > 20
					&& QPHOTO.status.inQzone) {
			}
			if (!QPHOTO.status.inPengyou
					&& QZONE.FP.getQzoneConfig("version") >= 6) {
				setTimeout(function() {
							t.showHzLight();
						}, 100);
			}
		},
		_render : function(idx) {
			var t = this;
			t._idx = idx;
			var npp = t.options.npp;
			var s = (idx - 1) * npp;
			var n = Math.min(idx * npp, t._d.length);
			var result = [];
			for (var i = s; i < n; i++) {
				t._d[i].index = i;
				t._d[i]._self = t._d[i];
				if (!Q.status.isOwner() && !t._d[i].total) {
					continue;
				}
				result.push(QPHOTO.util.tmpl(t.options.tpl, this._d[i], i));
			}
			$e('#album_list_container .tcisd_albumlist').unBind("click",
					t.tcisdStatsByDom);
			$("album_list_container").innerHTML = result.join("");
			$e('#album_list_container .tcisd_albumlist').bind("click",
					t.tcisdStatsByDom);
		},
		showInfo : function() {
			var t = this;
			t.showMenu();
			if (t._info.hasPerAlbum && Q.status.isOwner()
					&& QZONE.FP.getVipStatus()) {
				t.callExt("remind");
			}
		},
		empty : function() {
			Q.util.$hide("album_toolbar_div");
			$e('.js-popup').hide();
			var container = $("album_list_div");
			container.className = "new_user_guide";
			AlbumEmptyGuide.init(container);
		},
		enter : function(idx, lloc, isNormalView) {
			if (QPHOTO.policy.viewRight() == 1) {
				QZONE.FP.showMsgbox("因网络故障，该相册暂时无法查看。", 5, 2000);
				return;
			}
			var t = this;
			if (isNaN(idx) || !t._d[idx]) {
				idx = Q.util.getIdx(t._d, "id", idx);
			}
			var d = t._d[idx];
			if (!d) {
				QZONE.FP.showMsgbox("对不起，该相册不存在或者已经被删除！", 3, 2000);
				return;
			}
			if (QPHOTO.status.inPengyou) {
				isNormalView = true;
				if (lloc) {
					parent.showPhoto(d.id, QZONE.FP.getQzoneConfig('ownerUin'),
							lloc);
					return;
				}
			}
			function _enter() {
				QPHOTO.data.save("listnormalview", false);
				if (lloc) {
					if (QZONE.FP.getQzoneConfig('version') >= 6) {
						go("./new/photoNewView.html", d.id, lloc);
					} else {
						go("photoView.html", d.id, lloc);
					}
				} else {
					if (d.viewtype == 3
							&& !isNormalView
							&& (/.*\d[0-3]$/.test(QZONE.FP
									.getQzoneConfig('ownerUin'))) && false) {
						go("photoWall.html", d.id);
					} else if (d.viewtype == 5 && !isNormalView) {
						~function() {
							var uin = QZONE.FP.getQzoneConfig('ownerUin');
							var domain = QPHOTO.data.get("user_domain_" + uin);
							var idcno = domain.idcno;
							var idc = ['sz', 'xa', 'hz', 'gz'][idcno];
							var cssId = (QZONE && QZONE.FP.getQzoneConfig().styleId)
									|| 'v6/7';
							var isDeep = QZONE.FP.checkIsDeepColor() ? 1 : 0;
							var url = 'http://sz.n.photo.qq.com/timeline/baby/'
									+ uin + '/' + idc + '?skin='
									+ cssId.replace('v6/', '') + '&deepcolor='
									+ isDeep + '&priv=' + d.priv + '&aid='
									+ d.id;
							if (true) {
								go(
										"http://"
												+ QZONE.FP._t.imgcacheDomain
												+ '/qzone/photo/v7/page/photo.html#init=photo.v7/module/timeline/baby/index2&priv='
												+ d.priv, d.id);
							} else {
								if (domain
										&& !/async/
												.test(QZONE.FP._t.location.href)
										&& idc) {
									go(url);
								} else {
									go(
											"http://"
													+ QZONE.FP._t.imgcacheDomain
													+ '/qzone/photo/v7/page/photo.html#init=photo.v7/module/timeline/baby/index&priv='
													+ d.priv, d.id);
								}
							}
						}();
					} else if (d.viewtype == 6 && !isNormalView) {
						~function() {
							go(
									"http://"
											+ QZONE.FP._t.imgcacheDomain
											+ '/qzone/photo/v7/page/photo.html#init=photo.v7/module/timeline/travel/index&priv='
											+ d.priv, d.id);
						}();
					} else {
						QPHOTO.data.save("listnormalview", true);
						if (frameElement.g_newPhotoListView) {
							var url = 'http://'
									+ QZONE.FP._t.imgcacheDomain
									+ '/qzone/photo/v7/page/photo.html?useqzfl=1#init=photo.v7/module/photoList/index&';
							go(url, d.id);
						} else {
							go("photoList.html", d.id, null, "system="
											+ taskSystem);
						}
					}
				}
			}
			var isCustom = !isNormalView && QZONE.FP.getVipStatus()
					&& d.viewtype == 1 && parseInt(d.tplid, 10);
			if (!isCustom && (d.priv == 1 || Q.zoneStatus.isOwner())
					&& QZFL.userAgent.ie != 9) {
				_enter();
				if (d.viewtype !== 5 && !frameElement.g_newPhotoListView) {
					Q.util.getPhoto(d.id);
				}
			} else {
				var list = new Q.photo.PhotoList(t._uin, d.id, {
					onSuccess : function(albumData) {
						if (isCustom) {
							setTimeout(function() {
										Q.util.enterCustomSlide(albumData);
									}, 0);
						} else {
							_enter();
						}
					},
					onFailure : function(d) {
						QZONE.FP.showMsgbox(d.msg, 5, 2000);
					},
					onQuestion : function(d) {
						Q.dialog.qa(d, function(ret) {
									list.get(d.priv, ret.question, ret.answer,
											ret.verifyCode);
								});
					},
					onWrongAnswer : function(d) {
						Q.dialog.qa(d, function(ret) {
									list.get(d.priv, ret.question, ret.answer,
											ret.verifyCode);
								});
					},
					onLogin : function(d) {
						QZONE.FP.showMsgbox(d.msg, 3, 2000);
						window.LoginBack = function() {
							list.get();
						};
						QZONE.FP.showLoginBox("photo");
					},
					onNoRight : function(d) {
						window.LoginBack = function() {
							if (QZONE.FP.getQzoneConfig().loginUin == QZONE.FP
									.getQzoneConfig().ownerUin) {
								Q.album
										.refresh(QZONE.FP.getQzoneConfig().loginUin);
							}
						}
						QZONE.FP.showLoginBox("photo")
					}
				});
				var tempriv = QPHOTO.status.inPengyou ? d.pypriv : d.priv;
				list.get(tempriv, d.question);
			}
		},
		goUserPersonal : function(id, remark) {
			var t = this;
			var loginUin = QZONE.FP.getQzoneConfig().loginUin;
			if (loginUin > 1000) {
				if (QPHOTO.zoneStatus.isOwner()) {
					if (t._d.length == 0) {
						QZONE.FP.showMsgbox("您还没有相册，请先创建一个！", 3, 2000);
						return;
					}
					location.href = "/qzone/client/photo/pages/qzone_v4/personal_album.html#tpl_id="
							+ id + "&remark=" + remark;
				} else {
					window.open("http://user.qzone.qq.com/" + loginUin
							+ "/photo/personal/0/" + id + "/" + remark);
				}
			} else {
				window.loginCallback = function() {
					myView.goUserPersonal(id, remark);
				}
				QZONE.FP.showLoginBox("photo");
			}
		},
		coverFocus : function(dom, idx) {
			QZONE.event.cancelBubble();
			var t = myView;
			if (!t._d.length)
				return;
			clearTimeout(focusTimer);
			focusTimer = setTimeout(function() {
				var editAble = QPHOTO.status.isOwner()
						&& QPHOTO.albumtpl.checkEditable(t._d[idx].handset);
				var delAble = QPHOTO.status.isOwner()
						&& QPHOTO.albumtpl.checkDelable(t._d[idx].handset);
				var simpleViewAble = (t._d[idx].tplid != '' || t._d[idx].viewtype == 3)
						&& QPHOTO.status.inQzone;
				if (editAble || simpleViewAble || delAble) {
					$e(dom).find("p.owner").show();
					$e(dom).find("p.photo_albumlist_photocount").hide();
				}
				if (QPHOTO.status.isOwner() && QPHOTO.status.inQzone) {
					$e(dom).find("p.photo_albumlist_op").show();
					$e(dom).find('p.drag').show();
				}
			}, 500)
		},
		coverBlur : function(dom, idx) {
			QZONE.event.cancelBubble();
			var t = myView;
			if (!t._d.length)
				return;
			var relatedTarget = QZFL.event.getRelatedTarget();
			clearTimeout(focusTimer);
			if (!relatedTarget
					|| (relatedTarget != dom && !QZFL.dom.contains(dom,
							relatedTarget))) {
				$e(dom).find("p.photo_albumlist_op, p.owner").hide();
				$e(dom).find("p.photo_albumlist_photocount").show();
				$e(dom).find('p.drag').hide();
			}
		},
		tcisdStats : function(key, hostOnly) {
			var domain = "photoclick.qzone.qq.com";
			var path = "ablumlist_2012";
			if (true) {
				var reportkey = key;
				if (hostOnly) {
					reportkey = '2012.ablumlist.' + key;
				} else if (QPHOTO.status.isOwner()) {
					reportkey = '2012.ablumlist.' + key + ".host";
				} else {
					reportkey = '2012.ablumlist.' + key + ".guest";
				}
				QZONE.FP._t.TCISD.hotClick(reportkey, domain, path);
			}
		},
		tcisdStatsByDom : function() {
			myView.tcisdStats(this.getAttribute('tcisdkey'), parseInt(this
							.getAttribute('tcisdhostonly')) == 1);
		},
		doDrag : function(dom, e) {
			if (dragData.isDrag)
				return;
			QZFL.event.cancelBubble(e);
			QZFL.event.preventDefault(e);
			_initDrag();
			var t = myView;
			var rect = QZFL.dom.getPosition(dom);
			dragData.dl = rect.left;
			dragData.dt = rect.top;
			dragData.cl = e.clientX - rect.left;
			dragData.ct = e.clientY - rect.top;
			dragData.current = dragData.moveTo = _getPosition(rect.top,
					rect.left);
			$e(dom.parentNode).addClass('photo_albumlist_drag');
			dom.parentNode.parentNode.parentNode.style.zIndex = 2;
			dragData.span = QZFL.dom.createElementIn("div",
					$e('div.photo_v3').elements[0], false, {
						className : "photo_placeholder",
						style : 'top:'
								+ dragData.topArr[dragData.current[1]]
								+ 'px;left:'
								+ (dragData.width + dragData.bw + rect.left - dragData.bw
										/ 2) + 'px'
					});
			dragData.isDrag = true;
			QZFL.event.addEvent(document.body, 'mousemove', t.onDrag);
			QZFL.event.addEvent(document.body, 'mouseup', t.doDrop);
			t.tcisdStats("moveablum");
		},
		onDrag : function(e) {
			QZFL.event.cancelBubble(e);
			QZFL.event.preventDefault(e);
			var top = QZFL.event.mouseY(e) - dragData.ct;
			var left = QZFL.event.mouseX(e) - dragData.cl;
			$e('div.photo_albumlist li').setStyle('left', 0);
			var moveTo = dragData.moveTo = _getPosition(top, left);
			if (moveTo[0] == 0) {
				dragData.rowDom[moveTo[1]][0].style.left = '10px';
			} else {
				dragData.rowDom[moveTo[1]][moveTo[0] - 1].style.left = '-10px';
				if (moveTo[0] < dragData.rowDom[moveTo[1]].length)
					dragData.rowDom[moveTo[1]][moveTo[0]].style.left = '10px';
			}
			var before = dragData.rowDom[dragData.current[1]][dragData.current[0]];
			before.style.left = left - dragData.dl + 'px';
			before.style.top = Math.min(top - dragData.dt, QZFL.dom
							.getClientHeight()
							- dragData.height
							- dragData.topArr[dragData.current[1]])
					+ 'px';
			dragData.span.style.top = dragData.topArr[dragData.moveTo[1]]
					+ 'px';
			dragData.span.style.left = (dragData.width + dragData.bw)
					* dragData.moveTo[0] + dragData.left - dragData.bw / 2
					+ 'px';
			_scroll(e);
			if (QZFL.userAgent.ie) {
				document.body.setCapture();
			} else if (window.captureEvents) {
				window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
			}
		},
		doDrop : function(e) {
			var t = myView;
			t.tcisdStats("list.sortsomeone");
			QZFL.event.cancelBubble(e);
			QZFL.event.preventDefault(e);
			var before = dragData.rowDom[dragData.current[1]][dragData.current[0]];
			var beforeIdx = $e(before).find('p.drag').elements[0].id.slice(5);
			var next, nextIdx, insertIdx;
			var isMove = false;
			var csIdx = 0;
			if (dragData.rowDom[dragData.moveTo[1]].length == dragData.moveTo[0]) {
				next = dragData.rowDom[dragData.moveTo[1]][dragData.moveTo[0]
						- 1];
				nextIdx = $e(next).find('p.drag').elements[0].id.slice(5);
				if (beforeIdx - nextIdx <= 0) {
					insertIdx = nextIdx;
				} else {
					insertIdx = nextIdx - 0 + 1;
				}
				csIdx = 1;
			} else {
				next = dragData.rowDom[dragData.moveTo[1]][dragData.moveTo[0]];
				nextIdx = $e(next).find('p.drag').elements[0].id.slice(5);
				if (beforeIdx - nextIdx >= 0) {
					insertIdx = nextIdx;
				} else {
					insertIdx = nextIdx != 0 ? nextIdx - 1 : nextIdx;
				}
			}
			if (t._info.ordermode == 1 && !Q.status.inPengyou) {
				if (t._d[beforeIdx].classid != t._d[nextIdx].classid) {
					isMove = true;
				} else {
					csIdx += t._d[nextIdx].csIndex;
					isMove = t._d[beforeIdx].csIndex != csIdx
							&& t._d[beforeIdx].csIndex != csIdx - 1;
				}
			} else {
				isMove = insertIdx != beforeIdx;
			}
			if (isMove) {
				if (t._d[beforeIdx].classid != t._d[nextIdx].classid
						&& t._info.ordermode == 1 && !Q.status.inPengyou) {
					t._d[beforeIdx].classid = t._d[nextIdx].classid;
				}
				t._d.splice(insertIdx, 0, t._d.splice(beforeIdx, 1)[0]);
				QZONE.FP.showMsgbox("正在保存中，请稍候…", 6, 20000);
				var url = QPHOTO.domain.getUrl_v2({
							key : "album_sort",
							uin : t._uin
						});
				var codelist = [];
				for (var i = 0, item; item = t._d[i++];) {
					codelist.push(item.id + '|' + i + '|' + item.classid);
				}
				var data = {
					uin : t._uin,
					mode : t._info.ordermode,
					sort : 2,
					codeList : codelist.join("_")
				};
				QPHOTO.util.dataSender_v2({
							url : url,
							data : data,
							hostUin : t._uin,
							callback : function(d) {
								QZONE.FP.showMsgbox("排序成功", 4, 2000);
								if (t._info.ordertype != 2) {
									t._info.ordertype = 2;
									t.showSortMode();
								}
								QPHOTO.album.refresh(t._uin);
								t.showList(t._idx);
							},
							errCallback : function(d) {
								QZONE.FP.showMsgbox(d.message, 5, 2000);
								t.showList(t._idx);
							}
						});
			}
			$e('div.photo_albumlist').setStyle('zIndex', '');
			$e('div.photo_albumlist li').removeClass('photo_albumlist_drag')
					.setStyle({
								'left' : 0,
								'top' : 0
							});
			QZFL.dom.removeElement(dragData.span);
			dragData = {};
			QZFL.event.removeEvent(document.body, 'mousemove', t.onDrag);
			QZFL.event.removeEvent(document.body, 'mouseup', t.doDrop);
			if (QZFL.userAgent.ie) {
				document.body.releaseCapture();
			} else if (window.releaseEvents) {
				window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP);
			}
		}
	});
	PAGE_EVENT.addEvent("NOTIFY_CHANGE_VIEWMODE", function(arg) {
				myView.callExt('switchViewMode', [arg.type, true]);
			});
	PAGE_EVENT.addEvent("NOTIFY_CHANGE_SORTMODE", function(arg) {
				myView.callExt('sort', [arg.type, true]);
			});
	PAGE_EVENT.addEvent("NOTIFY_CHANGE_COVERMODE", function(arg) {
				myView.callExt('switchCoverMode', [(arg.type == 1) ? "a" : "i",
								true]);
			});
})();
var speedReport = (function() {
	var Q = QPHOTO;
	var speed = new Q.report.Speed();
	var newSpeed = new Q.report.Speed();
	var retValue = new Q.report.RetValue();
	var retValueNoCache = new Q.report.RetValue();
	speed.setStart(g_startTime);
	function _setTime() {
		speed.setTime();
		newSpeed.setTime();
	}
	var uin = QZONE.FP.getQzoneConfig().ownerUin;
	function _getCDNIdx() {
		return Q.domain.getIdc(uin);
	}
	function _getNumLevel() {
		if (!myView._d || myView._d.length == 0) {
			return 1;
		} else if (myView._d.length >= 1 && myView._d.length <= 10) {
			return 2;
		} else if (myView._d.length >= 11 && myView._d.length <= 100) {
			return 3;
		} else if (myView._d.length >= 101 && myView._d.length <= 500) {
			return 4;
		} else {
			return 5;
		}
	}
	function _newReport() {
		if (myView._isCacheRead) {
			return;
		}
		var lv = _getNumLevel();
		var cdnNo = _getCDNIdx();
		newSpeed.report(0 + cdnNo * 5 + lv, 0.1, 3);
		var baseflag1 = 110063;
		var num = myView._d ? myView._d.length : 0;
		retValueNoCache.report(baseflag1 + cdnNo * 4 + 1, 1, num, 0.02);
	}
	function _bindEvent() {
		myView.addEvent("onAlbumEmpty", function() {
					speed.setTime();
					newSpeed.setTime(null, 1);
					var pid = Q.domain.getIdc(uin) == 0 ? 27 : 29;
					_newReport();
				});
		myView.addEvent("onDisplay", function() {
					speed.setTime();
					newSpeed.setTime(null, 2);
				});
		myView.addEvent("onFirstLook", function() {
					speed.setTime();
					newSpeed.setTime(null, 3);
					var pid = Q.domain.getIdc(uin) == 0 ? 27 : 29;
					_newReport();
				});
	}
	return {
		_bindEvent : _bindEvent,
		_setTime : _setTime,
		_newReport : _newReport
	}
})();
(function() {
	var Q = QPHOTO;
	$show = Q.util.$show;
	$hide = Q.util.$hide;
	var _o = Q.nameSpace("album");
	var _inited = false;
	var inner = _o.Nav = {
		init : function(mod) {
			var a;
			if (!QPHOTO.status.isOwner()) {
				a = $('nav_a_album');
				if (a) {
					a.innerHTML = "主人相册";
					a.title = "主人相册";
				}
				a = $('nav_a_video');
				if (a) {
					a.innerHTML = "主人视频";
					a.title = "主人视频";
				}
			}
			if (QPHOTO.status.inPengyou) {
				if (!QPHOTO.status.isOwner()) {
					a = $('nav_a_aboutme');
					if (a) {
						a.innerHTML = "关于主人的照片";
						a.title = "关于主人的照片";
					}
				}
				$e('#nav_aboutme').show();
			} else if (QZFL.userAgent.ie == 6) {
				if (!QPHOTO.status.isOwner()) {
					$e('#nav_friend a,#nav_activity a').hide();
					$e('#nav_friend,#nav_activity').show();
				}
			}
			var loginUin = QZONE.FP.getQzoneConfig().loginUin, ownerUin = QZONE.FP
					.getQzoneConfig().ownerUin;
			if ((loginUin == ownerUin) && !QPHOTO.status.inPengyou) {
				this.getFriendPhotoNumber();
			}
			var whiteList = [1020120420, 1022012089, 105006991, 1093637452,
					115624496, 118105451, 120372940, 14132373, 150300479,
					1545103473, 17212915, 184678062, 187285230, 19851503,
					2202081919, 2224235613, 233466906, 236228991, 2365026997,
					2390082233, 243993276, 25040912, 251276331, 256141,
					2605325504, 270187885, 27958950, 287447279, 32219500,
					34115813, 351623084, 362908968, 365726572, 371321878,
					378400834, 395774740, 396783511, 421013644, 45380645,
					45393095, 4590546, 464631920, 47372860, 476923298,
					501254725, 60008, 649205125, 709702370, 7366377, 770583738,
					77223181, 814623217, 857432358, 86435728, 873688683,
					89355719, 94631283];
			if ((loginUin == ownerUin) && !QPHOTO.status.inPengyou) {
				if (QZFL.FP.isAlphaUser(true)) {
					this.getFacemarkFriends();
					return;
				}
				for (var i = 0; i < whiteList.length; i++) {
					if (whiteList[i] == loginUin) {
						this.getFacemarkFriends();
						break;
					}
				}
			}
		},
		changeTab : function(mod) {
			$e("#tab_nav li a").removeClass("bg bor3");
			$e("#nav_a_" + mod).addClass("bg bor3");
		},
		getFriendPhotoNumber : function() {
			function setNum(numStr, $) {
				var num = parseInt(numStr);
				if (num > 0) {
					$('.js-friendphoto-num').html(num > 99 ? '99+' : num)
							.show().attr('title', '有' + num + '人更新');
				} else {
					$('.js-friendphoto-num').html('0').hide();
				}
			}
			seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo'], function(
					$, PSY) {
				$('#nav_friend').click(function() {
							$(this).find('.js-friendphoto-num').hide()
									.attr('好友照片');
						});
				var mainData = QZONE.FP._t._csfeedsdata
						&& QZONE.FP._t._csfeedsdata.main;
				if (mainData
						&& mainData.hasOwnProperty('friendFeeds_newphoto_cnt')) {
					setNum(mainData.friendFeeds_newphoto_cnt, $);
					return
				}
				PSY.ajax.request({
					type : 'get',
					requestType : 'jsonp',
					scriptCharset : 'gbk',
					jsonpCallback : 'callback',
					cache : false,
					url : 'http://base.'
							+ (QZONE.FP._t.g_Set || '')
							+ 'qzone.qq.com/cgi-bin/feeds/cgi_get_feeds_count.cgi',
					data : {
						uin : QZONE.FP.getQzoneConfig().loginUin,
						lockese : Math.random(),
						where : 1
					},
					success : function(ret) {
						if (ret && ret.data) {
							var num = parseInt(ret.data.friendFeeds_newphoto_cnt);
							setNum(num, $);
						}
					},
					error : function() {
					}
				})
			});
		},
		getFacemarkFriends : function() {
			function setNum(numStr, $) {
				var num = parseInt(numStr);
				if (num > 0) {
					$('.js-facemark-num').html(num > 99 ? '99+' : num).show();
				} else {
					$('.js-facemark-num').html('0').hide();
				}
			}
			seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo'], function(
					$, PSY) {
				PSY.ajax.request({
					type : 'get',
					requestType : 'jsonp',
					scriptCharset : 'gbk',
					qzoneCoolCbName : true,
					cache : false,
					url : 'http://app.photo.qq.com/cgi-bin/app/cgi_list_face_annotation',
					data : {
						uin : PSY.user.getLoginUin(),
						hostUin : PSY.user.getOwnerUin(),
						faceflag : 1,
						start : 0,
						num : 1,
						faUin : 0,
						confirm : 100,
						inCharset : 'GBK',
						outCharset : 'GBK',
						source : 'qzone',
						plat : 'qzone'
					},
					success : function(ret) {
						if (ret && ret.data && ret.data.status) {
							if (ret.data.status == 3) {
								$('#nav_facemark').hide();
							} else {
								$('#nav_facemark').show();
								if (PSY.user.getLoginUin() == PSY.user
										.getOwnerUin()
										&& ret.data.status == 1) {
									setNum(ret.data.unknownfacenum || 0, $);
								}
							}
						} else {
							$('#nav_facemark').hide();
						}
					},
					error : function() {
					}
				})
			});
		}
	};
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(arg) {
				var mod = arg.mod || "album";
				if (!_inited) {
					_inited = true;
					inner.init(mod);
				}
				inner.changeTab(mod);
			})
})();
(function() {
	var Q = QPHOTO;
	$show = Q.util.$show;
	$hide = Q.util.$hide;
	var _o = Q.nameSpace("album");
	var _inited = false;
	var _numPerPage = 8;
	var _turner = null;
	var _appCfg = [{
				key : "share-album",
				title : "共享相册",
				iconclass : "share-album",
				tcisdkey : "app.share-album",
				hostonly : true
			}, {
				key : "qying",
				title : "Q影影集",
				iconclass : "qying",
				tcisdkey : "app.qying",
				hostonly : true
			}, {
				key : "personal",
				title : "个性相册",
				iconclass : "iphoto",
				tcisdkey : "app.gxxc",
				hostonly : true
			}, {
				key : "meitu",
				title : "照片美化",
				iconclass : "beauty",
				tcisdkey : "app.zpmh",
				hostonly : true
			}, {
				key : "diy",
				title : "照片装扮",
				iconclass : "photodiy",
				tcisdkey : "app.diy",
				hostonly : true
			}, {
				key : "camera",
				title : "摄像头拍照",
				iconclass : "camera",
				tcisdkey : "app.sxt",
				hostonly : true
			}, {
				key : "vphoto",
				title : "动感影集",
				iconclass : "video",
				tcisdkey : "app.dgyj",
				hostonly : false
			}, {
				key : "weiyun",
				title : "微云相册",
				iconclass : "cloud",
				tcisdkey : "app.weiyun",
				hostonly : true
			}, {
				key : "activity",
				title : "照片活动",
				iconclass : "photoact",
				tcisdkey : "app.activity",
				hostonly : true
			}, {
				key : "kadang",
				title : "卡当照片冲印",
				iconclass : "kadang",
				tcisdkey : "app.kadangchongyin",
				hostonly : true
			}, {
				key : "qqshow",
				title : "QQ秀相册",
				iconclass : "qqshow",
				tcisdkey : "app.qqshow",
				hostonly : false
			}, {
				key : "bighead",
				title : "大头贴",
				iconclass : "datou",
				tcisdkey : "app.bighead",
				hostonly : false
			}, {
				key : "leyin",
				title : "乐印冲印",
				iconclass : "leyin",
				tcisdkey : "app.leyinchongyin",
				hostonly : true
			}];
	var inner = _o.App = {
		init : function(mod) {
			if (!Q.zoneStatus.isOwner()) {
				$("app_title").innerHTML = "主人应用";
				for (var i = _appCfg.length - 1; i >= 0; i--) {
					if (_appCfg[i].hostonly) {
						_appCfg.splice(i, 1);
					}
				};
			}
			if (_appCfg.length > _numPerPage) {
				_turner = new QPHOTO.widget.Turner({
					"type" : 2,
					"_c" : 1,
					"tn" : _appCfg.length,
					"npp" : _numPerPage,
					"panel" : $("app_page_turner"),
					"content" : ('<div class="mod_pagenav" style="text-align:left;padding-left:0px;">'
							+ '<p class="mod_pagenav_main">'
							+ '{previous}{first}' + '{pager}' + '{last}{next}' + '</p>')
				}, {
					"onChange" : function(i) {
						inner.showPage(i - 1);
					}
				});
				$show("app_page_turner");
			} else {
				$hide("app_page_turner");
			}
			inner.showPage(0);
		},
		showPage : function(pageIndex) {
			$e("#app_list a").unBind("click", inner.openPhotoAppByDom);
			var str = "";
			var start = pageIndex * _numPerPage;
			var end = Math.min(start + _numPerPage, _appCfg.length);
			for (var i = start; i < end; i++) {
				var app = _appCfg[i];
				str += [
						'<li>',
						'<a href="javascript:void(0)" class="c_tx" tcisdkey="'
								+ app.tcisdkey + '" tcisdhostonly="'
								+ (app.hostonly ? 1 : 0) + '" _key="' + app.key
								+ '" title="' + app.title + '">',
						'<span class="photo_panel_photoapp_img '
								+ app.iconclass + '"></span>',
						'<span class="photo_panel_photoapp_name">' + app.title
								+ '</span>', '</a>', '</li>'].join("");
			}
			$("app_list").innerHTML = str;
			$e("#app_list a").bind("click", inner.openPhotoAppByDom);
		},
		openPhotoAppByDom : function() {
			QZFL.event.cancelBubble();
			QZFL.event.preventDefault();
			Page.tcisdStats(this.getAttribute("tcisdkey"), parseInt(this
							.getAttribute("tcisdhostonly")) == 1);
			inner.openPhotoApp(this.getAttribute("_key"));
		},
		openPhotoApp : function(appkey) {
			var ownerUin = QZONE.FP.getQzoneConfig("ownerUin");
			switch (appkey) {
				case "personal" :
					window.open("http://rc.qzone.qq.com/photo/personal");
					break;
				case "meitu" :
					inner.editPhotoWithMeitu();
					break;
				case "diy" :
					window.open("http://rc.qzone.qq.com/mall?target=diy");
					break;
				case "weiyun" :
					window.open("http://rc.qzone.qq.com/photo/weiyun");
					break;
				case "vphoto" :
					window.open("http://user.qzone.qq.com/" + ownerUin
							+ "/photo/vphoto");
					break;
				case "share-album" :
					window.open("http://rc.qzone.qq.com/board?via=albumlist");
					break;
				case "qying" :
					window.open("http://rc.qzone.qq.com/26154?via=albumlist");
					break;
				case "camera" :
					inner.goCameraShooter();
					break;
				case "activity" :
					window.open("http://rc.qzone.qq.com/photo/activity");
					break;
				case "qqshow" :
					window.open("http://user.qzone.qq.com/" + ownerUin
							+ "/photo/qqshow");
					break;
				case "bighead" :
					window.open("http://user.qzone.qq.com/" + ownerUin
							+ "/photo/bighead");
					break;
				case "tuya" :
					window.open("http://rc.qzone.qq.com/myhome/100645029");
					break;
				case "kadang" :
					window.open("http://rc.qzone.qq.com/100657548");
					break;
				case "lomo" :
					window.open("http://rc.qzone.qq.com/100637456");
					break;
				case "leyin" :
					window.open("http://rc.qzone.qq.com/100642819");
					break;
				case "rexiangsheying" :
					window
							.open("http://home.photo.qq.com/index.php?mod=activity&act=detail&subject_id=V128rLAK3eyrYi");
					break;
				case "freeprint" :
					window
							.open("http://qzs.qq.com/qzone/app/mood_v6/html/wall/externalAct.html?actTopic=photo_163");
					break;
			}
		},
		editPhotoWithMeitu : function() {
			QZONE.event.preventDefault();
			function _goEdit() {
				var data = [
						"uin=" + QZONE.FP.getQzoneConfig().loginUin,
						"g_tk=" + QZONE.FP.getACSRFToken(),
						"alurl=" + encodeURIComponent(Q.domain.getUrl_v2({
									uin : QZONE.FP.getQzoneConfig('loginUin'),
									key : "album_list"
								})),
						"aaurl=" + encodeURIComponent(Q.domain.getUrl_v2({
									uin : QZONE.FP.getQzoneConfig('loginUin'),
									key : "album_add"
								})),
						"plurl=" + encodeURIComponent(Q.domain.getUrl_v2({
									uin : QZONE.FP.getQzoneConfig('loginUin'),
									key : "pic_list"
								})),
						"uurlnew="
								+ encodeURIComponent(Q.util
										.getUrl("upload_image"))].join("&");
				window.open("http://" + QZONE.FP._t.imgcacheDomain
						+ "/qzone/photo/zone/flashPhotoEditorMeitu.html?"
						+ data);
			}
			inner.goFlashApp(function(createAlbum, album) {
						if (createAlbum) {
							QZONE.FP.confirm("创建成功", "相册<strong>"
											+ escHTML(album.name)
											+ "</strong>创建成功，是否继续？", {
										"type" : 2,
										"icontype" : "help",
										"okfn" : function() {
											_goEdit();
										},
										"nofn" : QZFL.emptyFn,
										"cancelfn" : QZFL.emptyFn
									});
						} else {
							_goEdit();
						}
					}, {
						noAlbumTip : "请先创建相册，以保存编辑的照片"
					});
		},
		goCameraShooter : function() {
			QZONE.event.preventDefault();
			function _goShooter() {
				var popupHeight = 475;
				if (ua.firefox) {
					popupHeight += 15;
				}
				var strHTML = '<iframe id="camera_shooter_frame" frameborder="0" src="/qzone/photo/zone/cameraShooter.html" style="width:100%;height:'
						+ popupHeight + 'px"></iframe>';
				QZONE.FP.popupDialog("摄像头拍照", strHTML, 715, popupHeight);
				QZONE.FP.appendPopupFn(function() {
							try {
								QZONE.FP._t.$('camera_shooter_frame').contentWindow
										.releaseCamera();
							} catch (e) {
							}
						});
			}
			inner.goFlashApp(function(createAlbum, album) {
						if (createAlbum) {
							QZONE.FP.confirm("创建成功", "相册<strong>"
											+ escHTML(album.name)
											+ "</strong>创建成功，是否继续拍照？", {
										"type" : 2,
										"icontype" : "help",
										"okfn" : function() {
											_goShooter();
										},
										"nofn" : QZFL.emptyFn,
										"cancelfn" : QZFL.emptyFn
									});
						} else {
							_goShooter();
						}
					}, {
						noAlbumTip : "请先创建相册，以保存拍摄的照片"
					});
		},
		goFlashApp : function(succCb, opts) {
			opts = opts || {};
			var fv = QZFL.media.getFlashVersion();
			if (!fv || fv.toNumber() < 10) {
				var h = 138;
				if (ua.firefox) {
					h = 135;
				}
				QZONE.FP.popupDialog("安装提示", {
					src : '/qzone/photo/zone/flashVersionCheck.html#type=install'
				}, 214, h);
				QZONE.FP.appendPopupFn(QZFL.emptyFn)
				return;
			}
			var TOP = QZONE.FP._t;
			var cookieUin = parseInt(QZONE.cookie.get("uin").replace(/o?(0?)+/,
							""), 10);
			if ((TOP.checkLogin() == TOP.g_iUin)
					&& (TOP.g_iUin == TOP.g_iLoginUin)
					&& (TOP.g_iLoginUin == cookieUin)
					&& (QZONE.cookie.get("skey"))) {
				Q.util.getAlbums(function(album) {
							if (album.length > 0) {
								succCb(false);
							} else {
								var noAlbumTip = opts.noAlbumTip
										|| "请先创建相册，以保存编辑的照片";
								QZONE.FP.confirm("温馨提示", noAlbumTip,
										function() {
											Q.dialog.addAlbum(null,
													function(d) {
														PAGE_EVENT
																.fireEvent(
																		"NOTIFY_ALBUM_ADD",
																		{
																			aid : d.album.id
																		});
														succCb(true, d.album);
													});
										}, QZFL.emptyFn);
							}
						});
			} else {
				QZONE.FP.showLoginBox("photo");
			}
		}
	};
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(arg) {
				var mod = arg.mod || "album";
				if (Q.zoneStatus.inQzone && mod == "album" && !_inited) {
					_inited = true;
					inner.init(mod);
				}
			});
})();
var myView = {
	"callExt" : QZFL.emptyFn
};
var Page = (function() {
	var Q = QPHOTO;
	Q.nameSpace("album");
	var uin = QZONE.FP.getQzoneConfig().ownerUin;
	var mod = "";
	var albumlist_state = 0;
	var start_albumlist_time = new Date();
	var save_albumlist_state = function(value) {
		albumlist_state = value;
		var startTime = window['g_startTime'] || start_albumlist_time;
		var saveText = albumlist_state + (new Date() - startTime);
		QPHOTO.data.save("albumlistget_error", saveText);
	};
	var tabCfg = {
		"album" : {
			ctnClassName : "",
			jsUrl : "",
			checkLoaded : function() {
				return true;
			},
			doInit : function(uin, container, param) {
				window.g_album_init_time = new Date();
				save_albumlist_state("page_doinit");
				inner.showAlbumList(param.csid);
			}
		},
		"friend" : {
			ctnClassName : "page_albumlist_mod_friendsphoto",
			jsUrl : "/qzone/photo/zone/new/script/fpListView.js?"
					+ frameElement.g_photoVersion + ".js",
			checkLoaded : function() {
				return QPHOTO.album && QPHOTO.album.FPListView;
			},
			doInit : function(uin, container, param) {
				myView = new QPHOTO.album.FPListView(uin, container);
				myView.init();
				return;
			},
			seajs : {
				checkLoaded : function(checkModCb) {
					var hasPhotoV4 = function($, cb) {
						var divCon = document.createElement('div');
						divCon.className = 'friends_piclist';
						divCon = $(divCon);
						$(document.body).append(divCon);
						setTimeout(function() {
							var hasCss = $(divCon).css('position') == 'relative';
							cb(hasCss);
							$(divCon).remove();
						}, 0);
					}
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/friendphoto/index',
									'cssBase/qzone_v6/photo_v4.css'], function(
									$, psy, tmpl, css) {
								if (tmpl && $ && psy) {
									hasPhotoV4($, function(bo) {
										if (bo) {
											checkModCb(true);
										} else {
											psy.oz
													.reportText(
															('photo_v4.css load error:' + psy._domains['imgcache']),
															1, {
																type : 'css_error'
															});
											var cssPath = 'http://qzonestyle.gtimg.cn/qzone_v6/photo_v4.css?t='
													+ new Date().getTime();
											seajs.use([cssPath], function(css) {
												hasPhotoV4($, function(bo) {
													if (bo) {
														checkModCb(true);
													} else {
														psy.oz
																.reportText(
																		('photo_v4.css load error2:' + psy._domains['imgcache']),
																		1, {
																			type : 'css_error'
																		});
														if (mod == 'friend') {
															inner.showErr({
																		ret : -1
																	});
														}
													}
												});
											});
										}
									});
								} else {
									checkModCb(false);
								}
							});
				},
				doInit : function(uin, container, param) {
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/friendphoto/index'],
							function($, psy, index) {
								var fn = function() {
									var mod = index;
									if (mod && !mod.init && mod.get) {
										mod = mod.get('./init');
									}
									mod && mod.init && mod.init({
												container : container
											});
								}
								fn();
							});
				}
			}
		},
		"new" : {
			ctnClassName : "page_albumlist_mod_friendsphoto",
			jsUrl : "/qzone/photo/zone/new/script/npListView.js?"
					+ frameElement.g_photoVersion + ".js",
			checkLoaded : function() {
				return QPHOTO.album && QPHOTO.album.NPListView;
			},
			doInit : function(uin, container, param) {
				myView = new QPHOTO.album.NPListView(uin, container);
				myView.init();
			},
			seajs : {
				appModClass : '',
				checkLoaded : function(checkModCb) {
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/newphoto/index',
									'cssBase/qzone_v6/photo_v4.css'], function(
									$, photo, tmpl, css) {
								if (tmpl && $ && photo) {
									checkModCb(true);
								} else {
									checkModCb(false);
								}
							});
				},
				doInit : function(uin, container, param) {
					top.tmp_startClickNewPhoto = +new Date();
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/newphoto2/index'],
							function($, photo, newphoto) {
								newphoto.get('./init').init({
											div : container,
											params : {
												T0 : top.tmp_startClickNewPhoto,
												uin : uin,
												lloc : Q.util
														.getParameter('lloc')
											}
										});
							});
				}
			}
		},
		"facemark" : {
			seajs : {
				appModClass : '',
				checkLoaded : function(checkModCb) {
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/facemark/index',
									'cssBase/aoi/photo-face.css'], function($,
									photo, modulejs, css) {
								if (modulejs && $ && photo) {
									checkModCb(true);
								} else {
									checkModCb(false);
								}
							});
				},
				doInit : function(uin, container, param) {
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/facemark/index'],
							function($, photo, modulejs) {
								modulejs.init(uin, container);
							});
				}
			}
		},
		"footprint" : {
			seajs : {
				appModClass : '',
				checkLoaded : function(checkModCb) {
					seajs
							.use(
									['photo.v7/lib/jquery',
											'photo.v7/lib/photo',
											'photo.v7/module/footprint/index',
											'cssBase/qzone_v6/photo-management-v2.css'],
									function($, photo, modulejs, css) {
										if (modulejs && $ && photo) {
											checkModCb(true);
										} else {
											checkModCb(false);
										}
									});
				},
				doInit : function(uin, container, param) {
					seajs.use(['photo.v7/lib/jquery', 'photo.v7/lib/photo',
									'photo.v7/module/footprint/index'],
							function($, photo, modulejs) {
								modulejs = modulejs.get('./init');
								modulejs.init(uin, container);
							});
				}
			}
		},
		"marked" : {
			ctnClassName : "page_albumlist_mod_quanquan photo_quanquan",
			jsUrl : "/qzone/photo/zone/new/script/markedListView.js?"
					+ frameElement.g_photoVersion + ".js",
			checkLoaded : function() {
				return QPHOTO.marked && QPHOTO.marked.ListView;
			},
			doInit : function(uin, container, param) {
				myView = new QPHOTO.marked.ListView(uin, container);
				myView.init();
			}
		},
		"aboutme" : {
			ctnClassName : "page_albumlist_mod_aboutme",
			jsUrl : "/qzone/photo/zone/new/script/aboutListView.js?"
					+ frameElement.g_photoVersion + ".js",
			checkLoaded : function() {
				return QPHOTO.album && QPHOTO.album.aboutListView;
			},
			doInit : function(uin, container, param) {
				myView = QPHOTO.album.aboutListView;
				myView.init(uin, container);
			}
		},
		"video" : {
			ctnClassName : "",
			jsUrl : "",
			checkLoaded : function() {
				return true;
			},
			doInit : function(uin, container, param) {
				QZONE.FP.toApp('/qzvideo/fromphoto');
			}
		}
	};
	var inner = {
		commonInit : function() {
			$e('.tcisd_home').unBind("click", inner.tcisdStatsByDom);
			$e('.tcisd_home').bind("click", inner.tcisdStatsByDom);
			PAGE_EVENT.fireEvent("NOTIFY_PAGE_INIT", {
						mod : mod
					});
			if (!QZONE.FP.getQzoneConfig('loginUin')) {
				$e('#ads_yellow_li').show();
			} else {
				var isVip = QZONE.FP.getLoginBitmap(27, 1);
				if (!isVip) {
					$e('#ads_yellow_li').show();
				} else {
					if (QZONE.FP.isUserVIPExpress()) {
						$e('#ads_yellow_li2 a.yearvip_tipslink')
								.setHtml('续费年费,送200点成长值');
					}
					$e('#ads_yellow_li2').show();
				}
			}
		},
		commonUnInit : function() {
			$('album_list_div').className = "";
			$e('.tcisd_home').unBind("click", inner.tcisdStatsByDom);
			PAGE_EVENT.fireEvent("NOTIFY_PAGE_UNINIT", {
						mod : mod
					});
		},
		goKaDang : function(obj) {
			if (QZONE.FP.getQzoneConfig().loginUin < 10000) {
				window.LoginBack = function() {
					inner.goKaDang();
				}
				QZONE.FP.showLoginBox("photo");
				return false;
			}
			obj.target = "_blank";
			return true;
		},
		showErr : function(d, notip) {
			var msg = d.msg || "对不起，网络繁忙，请稍后再试！";
			if (!notip) {
				QZFL.FP.showMsgbox(msg, 5, 2000);
			}
			$("album_list_div").innerHTML = '<p class="album_list_loading">'
					+ msg + '</p>';
		},
		"showRecentVisitor" : function() {
			$e('.photo_panel_recentcomments').hide();
			$e('.photo_panel_recentvisitor').show();
			PAGE_EVENT.fireEvent("NOTIFY_SHOW_VISITOR", {});
		},
		"showRecentCmt" : function() {
			$e('.photo_panel_recentvisitor').hide();
			$e('.photo_panel_recentcomments').show();
			PAGE_EVENT.fireEvent("NOTIFY_SHOW_RECENTCMT", {});
		},
		"showAlbumList" : function(csid, idx) {
			save_albumlist_state("showAlbumList_start");
			if (!Q.album.ListView) {
				return;
			}
			save_albumlist_state("showAlbumList_listview");
			mod = "album";
			window.myView = new Q.album.ListView(uin);
			myView.needCheckUnbind = inner.needCheckUnbind;
			speedReport._bindEvent();
			var _a = new Q.album.MyAlbumList(uin, {
						"onRequest" : function() {
							speedReport._setTime(null, 0);
						},
						"onSuccess" : function() {
							speedReport._setTime(null, 1);
						}
					});
			var getAlbumsCb = function(d, params) {
				if (mod != 'album') {
					return;
				}
				params = params || {};
				save_albumlist_state("showAlbumList_cb");
				var aid = Q.util.getParameter("aid");
				myView._isCacheRead = d._isCacheRead;
				PAGE_EVENT.fireEvent("NOTIFY_ALBUM_LOADED", {
							total : d.album ? d.album.length : '',
							csid : csid,
							data : d
						});
				window.timeseq.push((new Date()).getTime() / 1000);
				myView.display(d, aid, csid, void 0, idx);
				window.timeseq.push((new Date()).getTime() / 1000);
				if (window.timeseq.length > 8 && !params.isBakAlist) {
					setTimeout(function() {
								var pageSpeed = new QPHOTO.report.Speed();
								pageSpeed.setSeq(window.timeseq);
								pageSpeed.report(5, 0.001, 11);
							}, 1000)
				}
				setTimeout(function() {
					var now = new Date().getTime();
					seajs.use('photo.v7/lib/photo', function(PSY) {
								var startTime = window.g_startTime;
								if (Q.domain._getRouteHasFail) {
									return;
								}
								if (window.g_album_speed_report) {
									startTime = window.g_album_init_time;
								}
								window.g_album_speed_report = true;
								PSY.oz.speedSet('177-11-5-14', now, startTime);
								if (Q.zoneStatus.isOwner()) {
									PSY.oz.speedSet('177-11-5-16', now,
											startTime);
								} else {
									PSY.oz.speedSet('177-11-5-15', now,
											startTime);
								}
								PSY.oz.speedSend('177-11-5', {
											sampling : 10,
											reportSampling : false
										});
							});
					PAGE_EVENT.fireEvent("NOTIFY_ALBUM_CURRENTCSID", {
								csid : csid || 0
							})
					if (QPHOTO.status.inQzone) {
						seajs.use('/qzone/v6/v6_config/photo/albumBanner.js',
								function() {
									seajs
											.use('photo.v7/module/album/photo_promo');
								});
					}
					if (QPHOTO.noAlbum) {
						return;
					}
					if (Q.util.getParameter("to") == "editor"
							&& Q.zoneStatus.isOwner()) {
						myView.callExt('editPhotoWithFlash');
					}
					setTimeout(function() {
								seajs.use('photo.v7/module/albumList/preload',
										function(preload) {
											preload.init();
										});
							}, 0);
				}, 0);
			};
			var getAlbumsEcb = function(d) {
				d = d || {};
				save_albumlist_state("showAlbumList_errorcb_" + d.ret);
				if (d.ret == -963) {
					d.msg = "由于该相册设置了访问权限，当前无法访问";
					inner.showErr(d, true);
				} else if (d.ret == -505) {
					d.msg = "糟糕... 网络繁忙，请稍后再试";
					inner.showErr(d, true);
				} else if (d.ret == -961) {
					d.msg = "由于该相册设置了访问权限，当前无法访问";
					inner.showErr(d, true);
				} else {
					inner.showErr(d);
				}
			};
			var normalGetAlbums = function() {
				var seajs = window['seajs'];
				var isUseXdr = seajs && QZFL.FP.isAlphaUser(true);
				_a.get({
							"cb" : getAlbumsCb,
							"xdr" : isUseXdr,
							"ecb" : getAlbumsEcb,
							"type" : (Q.zoneStatus.isOwner() ? 7 : 3),
							"preurlspec" : frameElement.g_preurlspec
						});
			}
			normalGetAlbums();
		},
		"showNavTab" : function(newMod, param, options) {
			save_albumlist_state("page_showNavTab_" + newMod);
			$('album_list_div').innerHTML = '';
			options = options || {};
			if (options['newPhotoV2'] == undefined) {
				options['newPhotoV2'] = true;
			}
			var cfg = tabCfg[newMod];
			var ownerUin = QZONE.FP.getQzoneConfig().ownerUin;
			if (!cfg) {
				return;
			}
			mod = newMod;
			inner.sendPV(mod);
			var container = $("album_list_div");
			if (cfg && cfg.ctnClassName) {
				container.className = cfg.ctnClassName;
			}
			if (mod == 'facemark') {
				var appMod = $('app_mod');
				appMod.className = 'photo_v3 page_facemark';
			} else {
				var appMod = $('app_mod');
				appMod.className = 'photo_v3 page_albumlist';
			}
			if (mod == 'new' && options.newPhotoV2 && cfg.seajs && window.seajs
					&& !QPHOTO.status.inPengyou && PSY) {
				var startTime = +new Date();
				cfg.seajs.checkLoaded(function(bo) {
							if (bo && mod == newMod) {
								PSY.oz.speedSetBase('177-11-19', startTime, 6);
								PSY.oz.speedSet('177-11-19-1');
								cfg.seajs.doInit(uin, container, param);
							} else {
								if (mod == newMod) {
									inner.showErr({
												ret : -1
											});
								}
							}
						});
			} else if (mod == 'friend' && cfg.seajs && window.seajs
					&& !QPHOTO.status.inPengyou && PSY) {
				var startTime = +new Date(), isOwner = QZONE.FP
						.getQzoneConfig().isOwner, undefined;
				cfg.seajs.checkLoaded(function(bo) {
							if (bo && mod == newMod) {
								PSY.oz.speedSetBase('177-11-20', startTime, 8);
								PSY.oz.speedSet('177-11-20-1');
								if (isOwner) {
									PSY.oz.speedSet('177-11-20-5');
								} else {
									PSY.oz.speedSet('177-11-20-6');
								}
								cfg.seajs.doInit(uin, container, param);
							} else {
								if (mod == newMod) {
									inner.showErr({
												ret : -1
											});
								}
							}
						});
			} else if (mod == 'facemark' && cfg.seajs && window.seajs
					&& !QPHOTO.status.inPengyou && PSY) {
				var startTime = +new Date();
				PSY && PSY.loadTimes
						&& (PSY.loadTimes['clickToFace'] = startTime);
				cfg.seajs.checkLoaded(function(bo) {
							if (bo && mod == newMod) {
								cfg.seajs.doInit(uin, container, param);
							} else {
								if (mod == newMod) {
									inner.showErr({
												ret : -1
											});
								}
							}
						});
			} else if (mod == 'footprint' && cfg.seajs && window.seajs
					&& !QPHOTO.status.inPengyou && PSY) {
				var startTime = +new Date();
				cfg.seajs.checkLoaded(function(bo) {
							if (bo && mod == newMod) {
								cfg.seajs.doInit(uin, container, param);
							} else {
								if (mod == newMod) {
									inner.showErr({
												ret : -1
											});
								}
							}
						});
			} else {
				if (cfg.checkLoaded()) {
					save_albumlist_state("page_showNavTab_checkloaded");
					cfg.doInit(uin, container, param);
				} else {
					save_albumlist_state("page_showNavTab_nocheckloaded");
					QZFL.imports(cfg.jsUrl, function() {
								if (mod == newMod) {
									cfg.doInit(uin, container, param);
								}
							}, {
								charset : "utf-8",
								errCallback : function() {
									if (mod == newMod) {
										inner.showErr({
													ret : -1
												});
									}
								}
							});
				}
			}
		},
		tcisdStats : function(key, hostOnly) {
			var domain = "photoclick.qzone.qq.com";
			var path = "home_2012";
			if (true) {
				var reportkey = key;
				if (hostOnly) {
					reportkey = '2012.home.' + key;
				} else if (QPHOTO.status.isOwner()) {
					reportkey = '2012.home.' + key + ".host";
				} else {
					reportkey = '2012.home.' + key + ".guest";
				}
				QZONE.FP._t.TCISD.hotClick(reportkey, domain, path);
			}
		},
		tcisdStatsByDom : function() {
			inner.tcisdStats(this.getAttribute('tcisdkey'), parseInt(this
							.getAttribute('tcisdhostonly')) == 1);
		},
		sendPV : function(tabname) {
			if (!QPHOTO.status.inQzone) {
				return;
			}
			var reportTab = tabname;
			if (inner.pvTimer) {
				clearTimeout(inner.pvTimer);
				inner.pvTimer = null;
			}
			inner.pvTimer = setTimeout(function() {
						if (mod == reportTab) {
							var tc = QZONE.FP.getStatPackage();
							var url = location.pathname
									+ '.'
									+ reportTab
									+ (QPHOTO.status.isOwner()
											? '.host'
											: '.guest');
							tc.pv('photo.qzone.qq.com', url);
						}
						if (reportTab == 'album') {
							var tc = QZONE.FP.getStatPackage();
							var url = location.pathname
									+ (QPHOTO.status.isOwner()
											? '.host'
											: '.guest');
							tc.pv('photoclick.qzone.qq.com', url);
						}
					}, 0);
		}
	};
	QPHOTO.extend(inner, {
		init : function(newMod, options) {
			save_albumlist_state("page_init");
			options = options || {};
			if (!options.refresh && newMod == mod) {
				save_albumlist_state("page_init_refresh");
				return;
			}
			inner.commonUnInit();
			if (QPHOTO.status.inPengyou) {
				$e('.photo_panel_recentcomments').setStyle('border-width', 0);
				$e('.photo_panel_recentvisitor').setStyle('border-width', 0);
			}
			newMod = newMod || Q.util.getParameter("mod");
			if (!tabCfg[newMod]) {
				newMod = "album";
			}
			if (newMod !== "album") {
				window.g_album_speed_report = true;
			}
			var param;
			if (newMod == "album") {
				$('contianer_div').style.width = '100%';
				$e('.photo_layout_aside').show();
				var clinicLink = $("clinicLink");
				var display = clinicLink ? clinicLink.style.display : "none";
				$("album_list_div").innerHTML = [
						'<p class="album_list_loading">',
						'正在获取相册列表，请稍候...<a class="c_tx4" id="clinicLink" href="http://user.qzone.qq.com/20050606/clinic/?business=photo" onclick="statClinic();" target="_blank" style="display:'
								+ display + '">打开相册慢？点这里试试</a>', '</p>']
						.join("");
				PAGE_EVENT.fireEvent("NOTIFY_SHOW_ALBUM");
				param = {
					csid : parseInt(Q.util.getParameter("csid")) || 0
				};
			} else if (newMod == "facemark") {
				$('contianer_div').style.width = '';
				$e('.photo_layout_aside').hide();
			} else {
				$('contianer_div').style.width = '860px';
				$e('.photo_layout_aside').hide();
			}
			inner.showNavTab(newMod, param, options);
			setTimeout(function() {
						inner.commonInit();
					}, 0);
			var footAlphaArr = [381918212, 80991078, 857432358, 906612969,
					1213617849, 103074526, 445271, 3516230843, 2559845997,
					401934437, 4590546, 50216220, 554561241, 65504380,
					81709043, 240848437, 869534419, 136126009, 359230418,
					282259696, 345680969, 229298434, 25040912];
			var checkFootAlpha = function(uin) {
				for (var i = 0; i < footAlphaArr.length; i++) {
					var item = footAlphaArr[i];
					if (item == uin) {
						return true;
					}
				}
				return false;
			};
			if ($('nav_footprint')
					&& checkFootAlpha(QZONE.FP.getQzoneConfig().loginUin)) {
				$('nav_footprint').style.display = "";
			}
		},
		newPhotoV2 : true,
		needCheckUnbind : true
	});
	PAGE_EVENT.addEvent("NOTIFY_CHANGE_CSID", function(argv) {
				var csid = argv.csid || 0;
				location.hash = "#mod=album&csid=" + csid;
				if (mod == "album") {
					inner.showAlbumList(csid);
				} else {
					inner.init("album");
				}
			});
	PAGE_EVENT.addEvent("NOTIFY_ALBUM_ADD", function(argv) {
				if (mod == "album") {
					var csid = parseInt(Q.util.getParameter("csid")) || 0;
					inner.showAlbumList(csid);
				}
			});
	PAGE_EVENT.addEvent("NOTIFY_PAGE_INIT", function(argv) {
				if (argv.mod == "album") {
					startClinicTimeout();
				}
			});
	PAGE_EVENT.addEvent("NOTIFY_PAGE_UNINIT", function(argv) {
				if (argv.mod) {
					stopClinicTimeout();
				}
			});
	window.onunload = function() {
		try {
			PAGE_EVENT.fireEvent("NOTIFY_PAGE_UNINIT", {
						mod : mod
					});
		} catch (err) {
		}
	};
	return inner;
})();/*  |xGv00|dd3bd66b1a974777c9503aa598e6765a */