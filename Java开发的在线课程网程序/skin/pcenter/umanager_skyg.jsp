<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-用户管理</title>
<link href="/public/css/pcenter/pcenter.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/public/js/pcenter/sdmenu.js"></script>
<script type="text/javascript">
function SetCwinHeight(){
	var iframeid=document.getElementById("mainFrame"); //iframe id
	if (document.getElementById){
	   if (iframeid && !window.opera){
		   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
		     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
		   	 iframeid.height = height1>500?height1:500;
		   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
		     var height2 = iframeid.Document.body.scrollHeight;
		  	 iframeid.height = height2>500?height2:500;
		   }
	   }
	}
}
function setLocation(url,cur,total){
	for(var i=1; i<=total; i++){
		document.getElementById('menu_'+i).className = '';
	}
	document.getElementById('menu_'+cur).className = 'sdmenu_onclick';
	if(url != ''){
		document.getElementById("mainFrame").src = url;
	}
}

function changeBg(flag,id){
	var className = document.getElementById(id).className;
	if(flag == '1' && className != 'sdmenu_onclick'){
		document.getElementById(id).className = 'sdmenu_hover';
	}
	if(flag == '2' && className != 'sdmenu_onclick'){
		document.getElementById(id).className = '';
	}
}
// <![CDATA[
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
// ]]>
</script>
</head>
<body>
<script type="text/javascript" id="login_top" src="/skin/pcenter/top.js?uid=${s_unitid}"></script>
<!--#top-->
<div id="box">
<%@ include file="menu.jsp"%>
<!--#daohang-->
<div id="left">
<h3><a href="/pcenter.do?method=ucenter"><img src="/public/images/pcenter/c01.jpg"/></a></h3>
<div style="float: left" id="my_menu" class="sdmenu">
	<p style="height:10px;line-height:10px;">&nbsp;</p>
	<div>
        <span id="title">用户管理</span>
		<a id="menu_1" class="sdmenu_onclick" onclick="setLocation('sysUserInfoAction.do?method=list','1','7')" onmouseover="changeBg('1','menu_1')" onmouseout="changeBg('2','menu_1')">用户管理</a>
		<a id="menu_2" onclick="setLocation('sysUserInfoCheckAction.do?method=list','2','7')" onmouseover="changeBg('1','menu_2')" onmouseout="changeBg('2','menu_2')">用户审核</a>
		<a id="menu_3" onclick="setLocation('sysUserInfoCheckAction.do?method=delList','3','7')" onmouseover="changeBg('1','menu_3')" onmouseout="changeBg('2','menu_3')">删除用户</a>
    </div>
    <div>
        <span id="title">系统管理</span>
		<a id="menu_4" onclick="setLocation('sysRoleInfoAction.do?method=list','4','7')" onmouseover="changeBg('1','menu_4')" onmouseout="changeBg('2','menu_4')">角色管理</a>
		<a id="menu_5" onclick="setLocation('sysUserGroupAction0.do?method=main','5','7')" onmouseover="changeBg('1','menu_5')" onmouseout="changeBg('2','menu_5')">机构管理</a>
		<a id="menu_6" onclick="setLocation('gpwKeywordFilterAction.do?method=beforeUpdate','6','7')" onmouseover="changeBg('1','menu_6')" onmouseout="changeBg('2','menu_6')">关键字过滤</a>
		<a id="menu_7" onclick="setLocation('sysUserLogAction.do?method=list','7','7')" onmouseover="changeBg('1','menu_7')" onmouseout="changeBg('2','menu_7')">系统日志</a>
    <br/>
    </div>
</div><!--#left导航结束-->
<span style="float:left; "><img src="/public/images/pcenter/c05.gif" /></span>
</div>
<!--#left-->

<div id="right">
<div id="right_top"><span style="width:5px; float:left;"><img src="/public/images/pcenter/c07.gif" /></span><span style="width:800px; background:url(/public/images/pcenter/c08.gif) repeat-x; float:left; line-height:2px;">&nbsp;</span><span style="width:5px; float:right;"><img src="/public/images/pcenter/c06.gif" /></span></div><!--#right_top-->

<div id="right_center">
<iframe id="mainFrame" name="mainFrame" width="100%" height="50" onload="SetCwinHeight()" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/sysUserInfoAction.do?method=list"></iframe>
</div><!--#right_center-->

<div id="right_bottom"><span style="width:5px; float:left; "><img src="/public/images/pcenter/c03.gif" /></span><span style="width:800px; background:url(/public/images/pcenter/c02.gif) repeat-x left bottom; float:left; line-height:5px;">&nbsp;</span><span style="width:5px; float:right;"><img src="/public/images/pcenter/c04.gif" /></span></div><!--#right_bottom-->

</div>
<!--#right-->
</div>
<!--#box-->
<iframe id="footerFrame" name="footerFrame" width="100%" height="45" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/plogin.do?method=footer"></iframe>
</body>
</html>
