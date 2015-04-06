/**
 * 设置页面宽度
 */
var q = function() {
	setTimeout(scrollTo, 0, 0, 0);
	var availHeight = $(window).height();
	var availWidth = $(window).width();
	$('#headerbackground').css('width',availWidth-130)
	$('.wrap').css('width', availWidth);
	$('.listCardBody').css('width', availWidth - 36);
	// $('.footer').css({'width':availWidth});

};
/**
 * 
 */
var openpage = function(index, currentuserid, type) {
	var querurl = 'pagingdoc.do';
	if (type == 1) {
		querurl = 'pagingdoc.do';
	} else {
		querurl = 'pagingsharedoc.do';
	}
	$
			.ajax({
				async : true,
				cache : false,
				type : 'POST',
				dataType : "json",
				url : querurl,
				error : function() {
					alert('请求失败');
				},
				data : {
					userid : currentuserid,
					pageindex : index,
					pagetype : type,
					pagesize : 15
				},
				success : function(data) { // 请求成功后处理函数。
					var listbodydiv = $('<div></div>');
					for (i in data.doclist) {
						var listdiv = $('<div></div>').addClass('nwsl1');
						var docobj = $('<div></div>').addClass('title')
						if (type == 1) {
							docobj.css('height', '100%');
						} else {
							//docobj.css('height', '380');
						}
						var aobj = $('<a></a>').addClass('tit_text_overflow')
								.prop(
										'href',
										'phonedetail.do?docid='
												+ data.doclist[i].docid).prop(
										'target', '_blank').text(
										data.doclist[i].doctitle);
						docobj.append(aobj);
						listdiv.append(docobj);
						if (type == 2) {
							var authorobj = $('<div></div>').addClass('author');
							var authoraobj = $('<a></a>').prop(
									'href',
									'phoneuserindex.do?userid='
											+ data.doclist[i].userid).prop(
									'target', '_blank').text(
									data.doclist[i].docauthor);
							authorobj.append(authoraobj);
							listdiv.append(authorobj);
						}
						listbodydiv.append(listdiv);
					}
					if (type == 1) {
						$('#doclistdiv').html(listbodydiv.html());
						$("#docpagelist").html(data.pagingstr);
						// 设置当前 连接的
					} else if (type == 2) {
						// 设置当前 连接的
						$('#sharedoclistbody').html(listbodydiv.html());
						$("#sharedoclist").html(data.pagingstr);
					}

				}
			});
}