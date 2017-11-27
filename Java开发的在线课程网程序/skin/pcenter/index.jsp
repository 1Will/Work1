<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
<link href="/public/css/pcenter/pcenter.css" rel="stylesheet" type="text/css" />
<link href="/skin/vod001/css/logo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/public/js/pcenter/sdmenu.js"></script>
<script type="text/javascript">
function SetCwinHeight(){
	var iframeid=document.getElementById("mainFrame"); //iframe id
	if (document.getElementById){
	   if (iframeid && !window.opera){
		   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
		     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
		   	 iframeid.height = height1>552?height1:552;
		   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
		     var height2 = iframeid.Document.body.scrollHeight;
		  	 iframeid.height = height2>552?height2:552;
		   }
	   }
	}
}
// <![CDATA[
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
	
	<%--
	<logic:equal value="admin" name="s_sysuserinfo" property="loginname">
	//自动隐藏左侧最后两组按钮
	/**/
	if(document.getElementById('hidden_div_1')){
		document.getElementById('hidden_div_1').click();
	}
	if(document.getElementById('hidden_div_2')){
		document.getElementById('hidden_div_2').click();
	}
	if(document.getElementById('hidden_div_3')){
		document.getElementById('hidden_div_3').click();
	}
	if(document.getElementById('hidden_div_4')){
		document.getElementById('hidden_div_4').click();
	}
	</logic:equal>
	<logic:equal value="skyg" name="s_sysuserinfo" property="loginname">
	//自动隐藏左侧最后两组按钮
	/**/
	if(document.getElementById('hidden_div_1')){
		document.getElementById('hidden_div_1').click();
	}
	if(document.getElementById('hidden_div_2')){
		document.getElementById('hidden_div_2').click();
	}
	if(document.getElementById('hidden_div_3')){
		document.getElementById('hidden_div_3').click();
	}
	if(document.getElementById('hidden_div_4')){
		document.getElementById('hidden_div_4').click();
	}
	</logic:equal>
	--%>
	//自动隐藏左侧最后两组按钮
	/**/
	if(document.getElementById('hidden_div_1')){
		document.getElementById('hidden_div_1').click();
	}
	if(document.getElementById('hidden_div_2')){
		document.getElementById('hidden_div_2').click();
	}
	if(document.getElementById('hidden_div_3')){
		document.getElementById('hidden_div_3').click();
	}
	if(document.getElementById('hidden_div_4')){
		document.getElementById('hidden_div_4').click();
	}
	if(document.getElementById('hidden_div_5')){
		document.getElementById('hidden_div_5').click();
	}
	if(document.getElementById('hidden_div_6')){
		document.getElementById('hidden_div_6').click();
	}
};
// ]]>
</script>
</head>
<body>
<script type="text/javascript" id="login_top" src="/skin/pcenter/top.js?uid=${s_unitid}"></script>
<!--#top-->
<div id="box">
<%--@ include file="/skin/vod001/jsp/logo.jsp"--%>
<!--#daohang-->
<%@ include file="left.jsp"%>
<!--#left-->

<div id="right">
<div id="right_top"><span style="width:5px; float:left;"><img src="/public/images/pcenter/c07.gif" /></span><span style="width:800px; background:url(/public/images/pcenter/c08.gif) repeat-x; float:left; line-height:2px;">&nbsp;</span><span style="width:5px; float:right;"><img src="/public/images/pcenter/c06.gif" /></span></div><!--#right_top-->

<div id="right_center">
<iframe id="mainFrame" name="mainFrame" width="100%" height="50" onload="SetCwinHeight()" frameborder="0" marginheight="0" marginwidth="0" scrolling=auto src="/sysFrameAction.do?method=frame_welcome"></iframe>
</div><!--#right_center-->

<div id="right_bottom"><span style="width:5px; float:left; "><img src="/public/images/pcenter/c03.gif" /></span><span style="width:800px; background:url(/public/images/pcenter/c02.gif) repeat-x left bottom; float:left; line-height:5px;">&nbsp;</span><span style="width:5px; float:right;"><img src="/public/images/pcenter/c04.gif" /></span></div><!--#right_bottom-->

</div>
<!--#right-->
</div>
<!--#box-->
<iframe id="footerFrame" name="footerFrame" width="100%" height="45" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/plogin.do?method=footer"></iframe>
</body>
<script type="text/javascript">
/*
//【个人中心点击查看未读消息跳转过来的】
var msg = '<%=request.getAttribute("msg") %>';
if(msg == '1' || msg == '2' || msg == '3' || msg == '4' || msg == '0' || msg == '00'){
	setLocation('<%=request.getAttribute("curlinkurl") %>', '<%=request.getAttribute("curmodule") %>', '<%=request.getAttribute("childsize") %>');
}
*/
</script>
</html>
