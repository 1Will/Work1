function mobile_device_detect(url) {
    var thisOS = navigator.platform;
    var os = new Array("iPhone", "iPod", "iPad", "android", "Nokia", "SymbianOS", "Symbian", "Windows Phone", "Phone", "Linux armv71", "MAUI", "UNTRUSTED/1.0", "Windows CE", "BlackBerry", "IEMobile");
    for (var i = 0; i < os.length; i++) {
        if (thisOS.match(os[i])) {
            //window.top.location = url;
            document.write("<style>#footer{width:1200px;height:240px;background:#1d1a34;margin:0 auto;} #header_1{width:1200px;height:70px;background:#1d1a34;margin:0 auto;} .flexslider{position:relative;height:550px;overflow:hidden;background:url(/skin/wkmk/images/loading.gif) 50% no-repeat;width:1200px;margin:0 auto;} #login_main{width:1200px;height:490px;margin:0 auto;background:url(/skin/wkmk/images/login_bg.jpg) no-repeat center top #e5e5e5}</style>");
            return;
        }
    }
    //因为相当部分的手机系统不知道信息,这里是做临时性特殊辨认
    if (navigator.platform.indexOf('iPad') != -1) {
    	//window.top.location = url;
    	document.write("<style>#footer{width:1200px;height:240px;background:#1d1a34;margin:0 auto;} #header_1{width:1200px;height:70px;background:#1d1a34;margin:0 auto;} .flexslider{position:relative;height:550px;overflow:hidden;background:url(/skin/wkmk/images/loading.gif) 50% no-repeat;width:1200px;margin:0 auto;} #login_main{width:1200px;height:490px;margin:0 auto;background:url(/skin/wkmk/images/login_bg.jpg) no-repeat center top #e5e5e5}</style>");
    	return;
    }
    //做这一部分是因为Android手机的内核也是Linux
    //但是navigator.platform显示信息不尽相同情况繁多,因此从浏览器下手，即用navigator.appVersion信息做判断
    var check = navigator.appVersion;
    if (check.match(/linux/i)) {
        //X11是UC浏览器的平台 ，如果有其他特殊浏览器也可以附加上条件
        if (check.match(/mobile/i) || check.match(/X11/i)) {
        	//window.top.location = url;
        	document.write("<style>#footer{width:1200px;height:240px;background:#1d1a34;margin:0 auto;} #header_1{width:1200px;height:70px;background:#1d1a34;margin:0 auto;} .flexslider{position:relative;height:550px;overflow:hidden;background:url(/skin/wkmk/images/loading.gif) 50% no-repeat;width:1200px;margin:0 auto;} #login_main{width:1200px;height:490px;margin:0 auto;background:url(/skin/wkmk/images/login_bg.jpg) no-repeat center top #e5e5e5}</style>");
        	return;
        }
    }
    //类in_array函数
    Array.prototype.in_array = function (e) {
        for (i = 0; i < this.length; i++) {
            if (this[i] == e)
                return true;
        }
        return false;
    }
}
mobile_device_detect("/index.html");