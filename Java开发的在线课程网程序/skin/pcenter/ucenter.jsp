<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
<link href="/public/css/pcenter/pcenter.css" rel="stylesheet" type="text/css" />
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
function setLocation(url){
	if(url != ''){
		document.getElementById("mainFrame").src = url;
	}
}
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
	<p style="margin:19px; margin-bottom:0px; border:1px solid #ccc; padding:2px; width:135px; height:135px;"><img src='<bean:write name="s_sysuserinfo" property="photo" scope="session"/>' width="135" height="135"/></p>
    <p style="text-align:center;"><input type="button" onclick="setLocation('sysUserInfoAction.do?method=beforeUpdateSelf')" style="background:url(/public/images/pcenter/btn_xx.gif) no-repeat left top; width:70px; height:21px; margin:15px 5px; cursor:pointer;" /><input type="button" onclick="setLocation('sysUserInfoAction.do?method=modifyPassword')" style="background:url(/public/images/pcenter/btn_mm.gif) no-repeat left top; width:70px; height:21px; margin:15px 5px;cursor:pointer;" /></p>
    <ul>
    <dt>用&nbsp;户&nbsp;名：</dt><dd><bean:write name="s_sysuserinfo" property="loginname" scope="session"/></dd>
    <dt>真实姓名：</dt><dd><bean:write name="s_sysuserinfo" property="username" scope="session"/></dd>
    <dt>性&nbsp;&nbsp;&nbsp;&nbsp;别：</dt><dd><java2code:value codetype="sex" valuename="s_sysuserinfo" property="sex" scope="session"></java2code:value></dd>
    <dt>出生日期：</dt><dd><bean:write name="s_sysuserinfo" property="birthday" scope="session"/></dd>
    <dt>联系电话：</dt><dd><bean:write name="s_sysuserinfo" property="telephone" scope="session"/></dd>
    <dt>手&nbsp;机&nbsp;号：</dt><dd><bean:write name="s_sysuserinfo" property="mobile" scope="session"/>&nbsp;</dd>
    <%-- 
    <dt>民&nbsp;&nbsp;&nbsp;&nbsp;族：</dt><dd><java2code:value codetype="nation" valuename="s_sysuserinfo" property="nation" scope="session"></java2code:value></dd>
    <dt>籍&nbsp;&nbsp;&nbsp;&nbsp;贯：</dt><dd><bean:write name="s_sysuserinfo" property="nativeplace1" scope="session"/><bean:write name="s_sysuserinfo" property="nativeplace2" scope="session"/><bean:write name="s_sysuserinfo" property="nativeplace3" scope="session"/></dd>
    
    <dt>年&nbsp;&nbsp;&nbsp;&nbsp;级：</dt><dd><java2code:value codetype="grade" valuename="s_sysuserinfo" property="grade" scope="session"></java2code:value></dd>
    <dt>课&nbsp;&nbsp;&nbsp;&nbsp;程：</dt><dd><java2code:value codetype="course" valuename="s_sysuserinfo" property="course" scope="session"></java2code:value></dd>
    --%>
    <dt>电子邮件：</dt><dd><bean:write name="s_sysuserinfo" property="email" scope="session"/></dd>
    <%-- 
    <dt>邮政编码：</dt><dd><bean:write name="s_sysuserinfo" property="postcode" scope="session"/></dd>
    <dt>联系地址：</dt><dd><bean:write name="s_sysuserinfo" property="address" scope="session"/></dd>
    --%>
    <dt>个人简介：</dt><dd><bean:write name="s_sysuserinfo" property="descript" filter="false" scope="session"/></dd>
    </ul>
</div><!--#left导航结束-->
<span style="float:left; "><img src="/public/images/pcenter/c05.gif" /></span>
</div>
<!--#left-->

<div id="right">
<div id="right_top"><span style="width:5px; float:left;"><img src="/public/images/pcenter/c07.gif" /></span><span style="width:800px; background:url(/public/images/pcenter/c08.gif) repeat-x; float:left; line-height:2px;">&nbsp;</span><span style="width:5px; float:right;"><img src="/public/images/pcenter/c06.gif" /></span></div><!--#right_top-->

<div id="right_center">
<logic:notPresent name="tag" scope="request">
<iframe id="mainFrame" name="mainFrame" width="100%" height="50" onload="SetCwinHeight()" frameborder="0" marginheight="0" marginwidth="0" scrolling=auto src="/sysFrameAction.do?method=frame_welcome&tag=big"></iframe>
</logic:notPresent>
<logic:present name="tag" scope="request">
<logic:notEqual value="1" name="tag" scope="request">
<iframe id="mainFrame" name="mainFrame" width="100%" height="50" onload="SetCwinHeight()" frameborder="0" marginheight="0" marginwidth="0" scrolling=auto src="/sysFrameAction.do?method=frame_welcome&tag=big"></iframe>
</logic:notEqual>
<logic:equal value="1" name="tag" scope="request">
<iframe id="mainFrame" name="mainFrame" width="100%" height="50" onload="SetCwinHeight()" frameborder="0" marginheight="0" marginwidth="0" scrolling=auto src="/pcenter.do?method=nopower"></iframe>
</logic:equal>
</logic:present>
</div><!--#right_center-->

<div id="right_bottom"><span style="width:5px; float:left;"><img src="/public/images/pcenter/c03.gif" /></span><span style="width:800px; background:url(/public/images/pcenter/c02.gif) repeat-x left bottom; float:left; line-height:5px;">&nbsp;</span><span style="width:5px; float:right;"><img src="/public/images/pcenter/c04.gif" /></span></div><!--#right_bottom-->

</div>
<!--#right-->
</div>
<!--#box-->
<iframe id="footerFrame" name="footerFrame" width="100%" height="45" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/plogin.do?method=footer"></iframe>
<%-- 
<!--弹出式提示框（分离版）start-->
<link href="/public/css/pcenter/skins/blue/reset.css" rel="stylesheet" type="text/css"/>
<link href="/public/css/pcenter/skins/blue/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/public/js/pcenter/messager.js"></script>
<!--弹出式提示框（分离版）end-->
<script type="text/javascript">
function openMessager(){
	<logic:notEqual value="1" name="tag" scope="request">
	var msgheight = 85;
	var msgstr = '<li style="line-height:22px;"><a href="javascript:readMsg(1)"><span>未读消息：<bean:write name="unreadcount"/>条</span></a></li>';
	<logic:present name="unreadcount2">
	msgheight = msgheight + 17;
	msgstr = msgstr + '<li style="line-height:22px;"><a href="javascript:readMsg(2)"><span>未读通知：<bean:write name="unreadcount2"/>条</span></a></li>';
	</logic:present>
	<logic:present name="unreadcount3">
	msgheight = msgheight + 17;
	msgstr = msgstr + '<li style="line-height:22px;"><a href="javascript:readMsg(3)"><span>未阅公文：<bean:write name="unreadcount3"/>条</span></a></li>';
	</logic:present>
   	$.messager.lays(180, msgheight);
	$.messager.show('信息提示','<ul style="padding-top:15px;">' + msgstr + '</ul>',10000);
	</logic:notEqual>
}
function readMsg(type){
	window.location = '/pcenter.do?method=index&mproduct=msg&msg=' + type;
}
$(document).ready(function(){
    openMessager();
});

//判断是否必须修改登录名
var autoUpdate = "<%=request.getAttribute("autoUpdate") %>";
if(autoUpdate == '1'){
	setLocation('sysUserInfoAction.do?method=beforeUpdateSelf');
}
</script>
--%>
</body>
</html>
