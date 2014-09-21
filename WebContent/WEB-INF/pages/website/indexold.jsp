<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的主页</title>
<head>
<link href="css\comm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js\jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="js\tween.js"></script>
</head>
<body>
<div id='contain'>
<div class="menu"><%@ include file="menu.jsp"%></div>
<div id="center"><monggol:programList listname="programList" /></div>
<!--<div id="bottom"><%@ include file="bottom.jsp"%></div>-->
</div>
</body>
<script> 
          ///  $("div",'#center').each(function(n){alert(n)}); 
			var p = $('#picplayer');
			var pics1 = [{url:'img/a.jpg',link:'http://www.jb51.net/#',time:5000},{url:'img/b.jpg',link:'http://www.jb51.net/#',time:3000},{url:'img/c.jpg',link:'http://www.jb51.net',time:3000},{url:'img/d.jpg',link:'http://www.jb51.net',time:3000},{url:'img/e.jpg',link:'http://www.jb51.net',time:3000}];
			// 通过局部刷新获取最新的图片新闻列表并更新（一下）
			$.post("getimagenews.do", { Action: "post", Name: "lulu" },
			function (data, textStatus){
              for(var i=0;i<data.data.length;i++){
               pics1[i].url=data.data[i].imgurl;
              }
            initPicPlayer(pics1,p.css('width').split('px')[0],p.css('height').split('px')[0]);  
            }, "json");
			// 通过局部刷新获取最新的图片新闻列表并更新(以上)			
			function initPicPlayer(pics,w,h)
			{
				//选中的图片
				var selectedItem;
				//选中的按钮
				var selectedBtn;
				//自动播放的id
				var playID;
				//选中图片的索引
				var selectedIndex;
				//容器
				var p = $('#picplayer');
				p.text('');
				p.append('<div id="piccontent"></div>');
				var c = $('#piccontent');
				for(var i=0;i<pics.length;i++)
				{
					//添加图片到容器中
					c.append('<a href="'+pics[i].link+'" target="_blank"><img id="picitem'+i+'" style="display: none;z-index:'+i+'" src="'+pics[i].url+'" /></a>');
				}
				//按钮容器，绝对定位在右下角				
				p.append('<div id="picbtnHolder" style="position:absolute;top:'+(h-20)+'px;width:'+w+'px;height:20px;z-index:10000;"></div>');
				//
				var btnHolder = $('#picbtnHolder');
				btnHolder.append('<div id="picbtns" style="float:right; padding-right:1px;"></div>');
				var btns = $('#picbtns');
				//
				for(var i=0;i<pics.length;i++)
				{
					//增加图片对应的按钮
					btns.append('<span id="picbtn'+i+'" style="cursor:pointer; border:solid 1px #ccc;background-color:#eee; display:inline-block;">&nbsp;'+(i+1)+'&nbsp;</span>&nbsp;');
					$('#picbtn'+i).data('index',i);
					$('#picbtn'+i).click(
						function(event)
						{
							if(selectedItem.attr('src') == $('#picitem'+$(this).data('index')).attr('src'))
							{
								return;
							}
							setSelectedItem($(this).data('index'));
						}
					);
				}
				btns.append('&nbsp;');
				///
				setSelectedItem(0);
				//显示指定的图片index
				function setSelectedItem(index)
				{
					selectedIndex = index;
					clearInterval(playID);
					//alert(index);
					if(selectedItem)selectedItem.fadeOut('fast');
					selectedItem = $('#picitem'+index);
					selectedItem.fadeIn('slow');
					//
					if(selectedBtn)
					{
						selectedBtn.css('backgroundColor','#eee');
						selectedBtn.css('color','#000');
					}
					selectedBtn = $('#picbtn'+index);
					selectedBtn.css('backgroundColor','#000');
					selectedBtn.css('color','#fff');
					//自动播放
					playID = setInterval(function()
					{
						var index = selectedIndex+1;
						if(index > pics.length-1)index=0;
						setSelectedItem(index);
					},pics[index].time);
				}
			}
	function readCookie(){
<!--	var the_cookie1 ="my_happy_cookie=happiness_and_joy";-->
<!--    document.cookie = the_cookie1;-->
<!--    var the_cookie = document.cookie;-->
<!--    alert("the cookies: " + the_cookie);-->
<!--    var broken_cookie = the_cookie.split(";");-->
<!--    for(var i=0;i<broken_cookie.length;i++){-->
<!--      -->
<!--      alert("the cookie name : "+ broken_cookie[i].split("=")[0]);-->
<!--      alert("the cookie value : "+ broken_cookie[i].split("=")[1]);-->
<!--    }-->
<!--  var employee=new Employee("dd",20,"kkkk",1000);-->
<!--  employee.add=function(a,b){-->
<!--    alert(a+b);-->
<!--  }-->
<!--  employee.add(3,4);  -->
 
}

function Employee(name, age, title, salary)
{
	this.name = name;
	this.age = age;
	this.title = title;
	this.salary = salary;
}
</script>
</html>
