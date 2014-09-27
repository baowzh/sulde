/**
 * 手机APP
 */
var App = {
    changeProvince : function() {
        // TODO:省份展示
        var url = document.location.href;
        var arr = url.match(/goodsId=(\d{2})(\d+)/);
        if (arr[1] != "99") {
            $("#province_detail option[value=" + arr[1] + "]").attr("selected", "selected");
        }
    },
    getVersionType : function() {
        if (this.version && this.version.length > 0) {
            return this.version.indexOf("iphone") >= 0 ? 'iphone'
                    : this.version.indexOf("android") >= 0 ? 'android' : this.version;
        }
        return null;
    },
    /**
	 * URL中加入ticket以及version参数
	 * 
	 * @param currUrl
	 *            原始URL
	 * @returns {String} 封装后的URL
	 */
    getParamStr : function(currUrl) {
        if (this.ticket && this.ticket.length > 0) {
            var sep1 = currUrl.indexOf('?') > 0 ? '&' : '?';
            currUrl += sep1 + "ticket=" + this.ticket;
        }
        if (this.version && this.version.length > 0) {
            var sep2 = currUrl.indexOf('?') > 0 ? '&' : '?';
            currUrl += sep2 + "version=" + this.version;
        }

        return currUrl;
    },
    /**
	 * 拉起登录页
	 * 
	 * @param storeUrl
	 *            登录后的回调地址
	 * @param loginUrl
	 *            网页版使用的登录页
	 */
    showLoginPage : function(storeUrl, loginUrl, callbackUrl) {
        // alert("versionType: " + this.versionType + " storeUrl:" + storeUrl);
        var storeUrl4Client = callbackUrl + storeUrl;
        if ('android' == this.versionType) {// 安卓
            js_invoke.interact("{\"type\":\"shoplogin\",\"shopUrl\":\"" + storeUrl4Client + "\"}");
            return;
        } else if ('iphone' == this.versionType) { // iphone
            window.location.href = "clientAction={\"type\":\"shoplogin\",\"shopUrl\":\"" + storeUrl4Client
                    + "\"}";
            return;
        }
        var redirectUrl = decodeURIComponent(loginUrl) + "&state=" + storeUrl;
        // 网页版
        window.location.href = redirectUrl;
    },

    /**
	 * 返回到商城首页 下方5个按钮
	 */
    showIndexPage : function(homeUrl) {
        if ('android' == this.versionType) { // 安卓
            js_invoke.interact("{\"type\":\"close\",\"msg\":\"\",\"url\":\"\"}");
            return;
        } else if ('iphone' == this.versionType) { // iphone
            window.location = "clientAction={\"type\":\"close\",\"msg\":\"\",\"url\":\"\"}";
            return;
        }
        // 网页版
        window.location.href = homeUrl;
    },
    checkLogin : function(callBackUrl) {
        var LoginState = false;
        var param = {};
        if (callBackUrl != null) {
            param.storeUrl = encodeURIComponent(callBackUrl);
        }
        $.ajax({
            type : "POST",
            url : Esf.contextPath + "/MCheckLogin/checkLogin",
            data : param,
            async : false,
            success : function(retMessage) {
                LoginState = true;
            }
        });
        return LoginState;
    }
};
function getParamVal(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return '';
}

(function() {
    App.ticket = getParamVal("ticket");
    App.version = getParamVal("version");
    if (App.version == null || App.version == '') {
        App.version = LS.item("_m_version");
    } else {
        LS.item("_m_version", App.version);
    }
    // 将verson写入cookie

    $.cookie("MUT_V", App.version, {
        path : "/"
    });
    // 将ticket写入cookie
    if(App.ticket == null || App.ticket == '' ){
        App.ticket = $.cookie("MUT_T");
    }else{
        // 让cookies在5分钟后过期
        var now = new Date();
        now = new Date(now.getTime() + (5 * 60 * 1000));
        
        $.cookie("MUT_T", App.ticket, {path : "/" ,expires: now});
    }
    /**
	 * App类型 android， iphone，其他（网页访问）
	 */
    App.versionType = App.getVersionType();
    /**
	 * 是否为通过网页访问
	 */
    App.isWeb = App.versionType != 'android' && App.versionType != 'iphone'
            && App.versionType != 'ap';

})();
// 为页面中每个Form表单自动添加隐藏域
$(function() {
    if (App.version && App.version.length > 0) {
        $("form").each(function() {
            $(this).append("<input type='hidden' name='version' value='" + App.version + "' />");
        });
    }
    if (App.ticket && App.ticket.length > 0) {
        $("form").each(function() {
            $(this).append("<input type='hidden' name='ticket' value='" + App.ticket + "' />");
        });
    }

    // ajax调用全局处理
    var ajaxErrorHandler = function(XMLHttpRequest, textStatus, errorThrown) {
        var rspMsg = $.trim(XMLHttpRequest.responseText);
        // 兼容tomcat封装403返回responseText
        var index = rspMsg.indexOf("<u>");
        if (index > 0) {
            rspMsg = rspMsg.substr(index + 3);
            rspMsg = rspMsg.substr(0, rspMsg.indexOf("</u>"))
        }

        var rspArr = rspMsg.split("^_^");

        // -- 未登录用户跳转到登陆页面
        if (403 == XMLHttpRequest.status) {
            // 更改自动提交标记
            LS.item("autoSubmit", "1");
            // 删除cookie中的MUT
            $.cookie("MUT", "", {
                path : "/"
            });
            App.showLoginPage(rspArr[0], rspArr[1], rspArr[2]);
            return;
        }
        var msg = "当前访问用户过多，请稍后再试！";
        if (rspArr[1] != null && rspArr[1] != undefined) {
            msg = rspArr[1];
        }
        if (rspArr[1] == null || rspArr[1] == undefined) {
            // window.location.href="${e.res('/front/html/error_ye.htm')}";
            return;
        }
        alert(msg);
    };
    $.ajaxSetup({
        beforeSend : function() {
            $(".thickdiv,.loading").show();
        },
        complete : function() {
            $(".thickdiv,.loading").hide();
        },
        error : ajaxErrorHandler
    });

});
// 重写弹出框，统一风格
function alert(msg,mode,head,callback) { // mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮，head为提示标题
											// ,callback点击按钮返回函数，0确定 1取消
    msg = msg || '';
    mode = mode || '0';
    head = head || '提示';
    var top = document.body.scrollTop || document.documentElement.scrollTop;
    var isIe = (document.all) ? true : false;
    var isIE6 = isIe && !window.XMLHttpRequest;
    var sTop = document.documentElement.scrollTop || document.body.scrollTop;
    var sLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
    var winSize = function(){
        var xScroll, yScroll, windowWidth, windowHeight, pageWidth, pageHeight;
        // innerHeight获取的是可视窗口的高度，IE不支持此属性
        if (window.innerHeight && window.scrollMaxY) {
            xScroll = document.body.scrollWidth;
            yScroll = window.innerHeight + window.scrollMaxY;
        } else if (document.body.scrollHeight > document.body.offsetHeight) { // all
																				// but
																				// Explorer
																				// Mac
            xScroll = document.body.scrollWidth;
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict,
					// Mozilla and Safari
            xScroll = document.body.offsetWidth;
            yScroll = document.body.offsetHeight;
        }

        if (self.innerHeight) {    // all except Explorer
            windowWidth = self.innerWidth;
            windowHeight = self.innerHeight;
        } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer
																						// 6
																						// Strict
																						// Mode
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        } else if (document.body) { // other Explorers
            windowWidth = document.body.clientWidth;
            windowHeight = document.body.clientHeight;
        }

        // for small pages with total height less then height of the viewport
        if (yScroll < windowHeight) {
            pageHeight = windowHeight;
        } else {
            pageHeight = yScroll;
        }

        // for small pages with total width less then width of the viewport
        if (xScroll < windowWidth) {
            pageWidth = windowWidth;
        } else {
            pageWidth = xScroll;
        }

        return{
            'pageWidth':pageWidth,
            'pageHeight':pageHeight,
            'windowWidth':windowWidth,
            'windowHeight':windowHeight
        }
    }();
    // alert(winSize.pageWidth);
    // 遮罩层
    var styleStr = 'top:0;left:0;position:absolute;z-index:10000;background:#666;width:' + winSize.pageWidth + 'px;height:' +  (winSize.pageHeight + 30) + 'px;';
    styleStr += (isIe) ? "filter:alpha(opacity=80);" : "opacity:0.8;";
    var shadowDiv = document.createElement('div'); // 添加阴影DIV
    shadowDiv.id = "shadowDiv";
    shadowDiv.style.cssText = styleStr; // 添加样式
    document.body.appendChild(shadowDiv);
    // document.body.insertBefore(shadowDiv, document.body.firstChild);
	// //遮罩层加入文档
    // document.body.scroll="no";
    // 弹出框
    var styleStr1 = 'display:block;position:fixed;_position:absolute;_top:' + winSize.windowHeight*0.3 + top+ 'px;z-index: 99999;';
    var alertBox = document.createElement('div');
    alertBox.className = 'popup';
    alertBox.style.cssText = styleStr1;
    // 创建关闭按钮
    var closebtn = document.createElement('div');
    closebtn.className = 'close';
    closebtn.innerHTML = '<a href="javascript:void(0)">X</a>';
    closebtn.onclick = function () {
        document.body.removeChild(alertBox);
        document.body.removeChild(shadowDiv);
        return false;
    }
    alertBox.appendChild(closebtn);
    // 创建弹出框标题h2
    var alert_head = document.createElement('H2');
    alert_head.id = 'alert_head';
    alert_head.innerHTML = head;
    alertBox.appendChild(alert_head);
    // 创建弹出框里面的内容P标签
    var alertMsg_info = document.createElement('P');
    alertMsg_info.innerHTML = msg;
    alertBox.appendChild(alertMsg_info);
    // 创建按钮
    var btns_box = document.createElement('div');
    btns_box.className = 'btns-box';
    alertBox.appendChild(btns_box);
    if (mode == '1') {
        var btn1 = document.createElement('a');
        btn1.id = 'alertMsg_btn1';
        btn1.href = 'javascript:void(0)';
        btn1.className = 'org-btn w-49p';
        btn1.innerHTML = '确定';
        btn1.onclick = function () {
            document.body.removeChild(alertBox);
            document.body.removeChild(shadowDiv);
            callback('0');
            return true;
        };
        btns_box.appendChild(btn1);
        var btn0 = document.createElement('span');
        btn0.innerHTML = '  ';
        btn0.style.cssText = 'width:1%';
        btns_box.appendChild(btn0);
        var btn2 = document.createElement('a');
        btn2.id = 'alertMsg_btn2';
        btn2.href = 'javascript:void(0)';
        btn2.className = 'org-btn w-49p';
        btn2.innerHTML = '取消';
        btn2.onclick = function () {
            document.body.removeChild(alertBox);
            document.body.removeChild(shadowDiv);
            callback('1');
            return false;
        };
        btns_box.appendChild(btn2);
    }else if(mode == '0'){
        var btn1 = document.createElement('a');
        btn1.id = 'alertMsg_btn1';
        btn1.href = 'javascript:void(0)';
        btn1.className = 'org-btn w-full';
        btn1.innerHTML = '确定';
        btn1.onclick = function () {
            document.body.removeChild(alertBox);
            document.body.removeChild(shadowDiv);
            return true;
        };
        btns_box.appendChild(btn1);
    }
    document.body.appendChild(alertBox);

}