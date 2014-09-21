$(document).ready(function() {
			$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						dataType : "json",
						url : "getOpinionContent.do",// 请求的action路径
						error : function() {// 请求失败处理函数
							alert('请求失败');
						},
						data : {
							opinionid : $("#opinionid").val()
						},
						success : function(data) { // 请求成功后处理函数。
								//alert(data.opinion.htmlstr);													
								$("#docdetail").html(data.opinion.htmlstr);																					
						}
					});
		});
