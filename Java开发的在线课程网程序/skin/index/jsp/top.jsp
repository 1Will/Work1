<%@ page contentType="text/html;charset=utf-8"%>
<div id="head">
	<div class="head_left">
    	<a href="/default.html"><img src="/skin/index/images/logo.png" class="head_left_logo" /></a>
        <div class="head_left_search">
        	<input class="head_left_search_input" type="text" name="keywords" id="keywords" value="${keywords }" onKeyPress="return search_onkeypress(event)"/>
            <a href="javascript:search('1')" style="cursor:pointer;">
            	<img src="/skin/index/images/search_1.png" />
            </a>
        </div>
    </div>
    <div class="head_right" id="login_div">
    	<a href="#" class="header_right_font">官方公众号</a>
        <a href="/index.html"><span class="head_right_font2">首页</span></a>
        <span>|</span>
        <a href="javascript:login();"><span>登录</span></a>
        <span>|</span>
        <a href="/v.bo?method=register" target="_blank"> <span>注册</span></a>
    </div>
</div>
<script type="text/javascript">
	function login(){
		var topurl = window.top.location + "";
		topurl = replaceStr(topurl);
		window.top.location = '/plogin.do?method=slogin&redirecturl=' + topurl;
	};
	function logout(){
	    var topurl = window.top.location + "";
	    topurl = replaceStr(topurl);
	    window.top.location = '/plogin.do?method=userLogout&redirecturl=' + topurl;
	};
	function replaceStr(url){
		url = url.replace(/&/g, "%26");
		return url;
	}
	function ajaxLogin(){
		$.ajax({
	        type: "get",
	        url: "/v.bo?method=alogin1&ram=" + Math.random(),
	        dataType: "text",
	        success: function(data){
	        	if(data != ''){
	        		document.getElementById("login_div").innerHTML = data;
	        	}
	         }
	    });
	};
	ajaxLogin();
</script>
<script type="text/javascript">
function search_onkeypress(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	search('1');
  	}
};
function search(searchButton){
	var keywords = document.getElementById('keywords').value;
	if(keywords == ''){
		return;
	}else{
		if(searchButton){
			if(searchButton == '1'){
				document.getElementById('searchButton').value = '1';
			}
		}
		document.getElementById('startcount').value = 0;
		document.searchForm.action = "/search.action";
		document.searchForm.submit();
	}
};
function move(){
	var shuxue=document.getElementsByName("shuxue");
    for(var i=0;i<shuxue.length;i++){
		shuxue[i].style.backgroundColor="#efefef";
	}
};
function out(){
	var shuxue=document.getElementsByName("shuxue");
    for(var i=0;i<shuxue.length;i++){
		shuxue[i].style.backgroundColor="#f8f8f8";
	}
};
</script>