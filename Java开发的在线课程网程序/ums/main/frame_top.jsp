<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<head>
<title>统一用户管理系统</title>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<style type="text/css">
@charset "utf-8";
/*-------常见样式----------*/
*{padding:0px; margin:0px;}
body{font-size:12px; color:#000; background-color:#fff; font:Arial, Helvetica, sans-serif;}
img {border:0px;}
table{border:0px;}
ul,li{list-style:none;}
/*个人爱好类*/
.high01{font-size:1px; line-height:1px;  clear:both; padding:0px; margin:0px; border:0px;height:1px;}
/*--------main--------*/
#main{ width:100%; margin:0 auto; background:#fff url(/public/images/index/main2_top_bg.gif) repeat-x left top; padding:0 2px;}
/*标题、列表*/
.main_title{ float:left;background:url(/public/images/index/main2_top_title.gif) no-repeat left top; width:136px; height:28px; line-height:30px; text-align:center; font-size:14px; font-weight:bold; color:#4d4d4d;  cursor:pointer; margin-top:4px; margin-right:-25px;  position:relative; }
.main_title2{ float:left;background:url(/public/images/index/main2_top_title2.gif) no-repeat left top; width:136px; height:28px; line-height:30px;  text-align:center; font-size:14px; font-weight:bold; color:#4d4d4d;  cursor:pointer;margin-top:4px;margin-right:-25px; }
.main_title_name{ float:left; font-size:12px; font-weight:bold; color:red; padding-top:6px; padding-left:15px;}
.main_title_list{ float:right; padding-right:30px;_padding-right:15px; height:32px; line-height:32px;color:#0000FF;}
.alist1{ background:url(/public/images/index/top_icon1.gif) no-repeat left center; padding-left:22px;}
.alist2{ background:url(/public/images/index/top_icon2.gif) no-repeat left center; padding-left:22px;}
.alist3{ background:url(/public/images/index/top_icon3.gif) no-repeat left center; padding-left:22px;}
.main_title_list a:link,.main_title_list a:visited{ color:#666; text-decoration:none; display:block; float:left; font-size:13px;}
.main_title_list a:hover{ color:#000; text-decoration:none;}
.alistge{ background:url(/public/images/index/top_ge.gif) no-repeat left top; width:2px; height:32px; margin:0 10px; float:left;}
/*--------header--------*/
#header{ width:100%; margin:0 auto;background:#fff url(/public/images/index/header_bg.jpg) repeat-y left top; height:80px;}
.logo{ width:265px; height:80px; float:left;margin-left:35px;_margin-left:13px;}
.tel_list{ float:right; text-align:left; line-height:16px; font-size:12px; font-weight:bold; color:#000; margin-right:15px;_margin-right:8px; margin-top:8px;}
</style>
<SCRIPT language=javascript>
function gotoUrl(url){
	parent.mainFrame.location=url;
}
function setTab(name,cursel,n,url){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);	
		menu.className=i==cursel?"main_title":"main_title2";
	}
	parent.mainFrame.location=url;
}
</SCRIPT>
</head>
<body style="overflow-y:hidden;overflow-x:hidden">
<div id="header">
	<div class="logo"><img src="/public/images/index/logo.jpg" width="315" height="80"/></div>
    <div class="tel_list"></div>
</div>
<div id="main">
  <div>
  	<logic:equal value="0" name="s_userid" scope="session">
    <div id="topmenu1" class="main_title" onClick="setTab('topmenu',1,2,'sysProductInfoAction.do?method=list')">产品管理</div>
    <div id="topmenu2" class="main_title2" onClick="setTab('topmenu',2,2,'sysProductInfoAction.do?method=list1')">产品模块</div>
    </logic:equal>
    <logic:notEqual value="0" name="s_userid" scope="session">
    <div id="topmenu1" class="main_title" onClick="gotoUrl('/sysProductInfoAction.do?method=list')">产品管理</div>
    </logic:notEqual>
    <div class="main_title_list"><a href="/index.html" target="_blank" class="alist1">首页</a><a class="alistge"></a><a href="http://www.edutech.com.cn" target="_blank" class="alist2">技术支持</a><a class="alistge"></a><a href="/plogin.do?method=userLogout" target="_parent" class="alist3">退&nbsp;出</a></div>
  </div>
  <div class="high01"></div>
</div>
</body>
</html>
