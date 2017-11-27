<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/public/css/pcenter/pcenter1.css" rel="stylesheet" type="text/css" />
<title>快捷菜单</title>
<script type="text/javascript">
function setLocation(url){
	if(url.indexOf("beiKeAction") >= 0){
		if(!+[1,] || "msDoNotTrack" in window.navigator){
			window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
		}else{
			alert("请用IE浏览器撰写教案和课件，WebOffice控件只支持IE浏览器!");
			return false;
		}
	}else{
		window.parent.document.getElementById("right_center").style.width = "788px";
		window.parent.document.getElementById("right_center").style.padding = "10px";
		window.parent.document.getElementById("mainFrame").src = url;
	}
}
</script>
</head>

<body style="background:none;">
<logic:equal value="0" name="isAdmin" scope="session">
<div style="background:url(/public/images/pcenter/menu_bg2.jpg) no-repeat right bottom;  width:100%; height:552px; position:relative;">
</logic:equal>
<logic:notEqual value="0" name="isAdmin" scope="session">
<div style="background:url(/public/images/pcenter/menu_bg1.jpg) no-repeat right bottom;  width:100%; height:552px; position:relative;">
</logic:notEqual>

	<div id="my_source">
    	<a class="my_source_link" onmousemove="document.getElementById('my_source_box').style.display='block'" onmouseout="document.getElementById('my_source_box').style.display='none'"><img src="/public/images/pcenter/menu_13.gif" width="190" height="40" /></a>
        <div id="my_source_box" onmousemove="this.style.display='block'" onmouseout="this.style.display='none'">
        	<ul>
        	<logic:iterate id="teaching" name="teachinglist">
                <li><a href='/res-rl-<bean:write name="s_unitid"/>-1-<bean:write name="teaching" property="gradeid"/>.htm' target="_blank"><bean:write name="teaching" property="flags"/>&nbsp;>>&nbsp;<bean:write name="teaching" property="flago"/></a></li>
            </logic:iterate>
            </ul>
       	    <div><img src="/public/images/pcenter/menu_13_bg2.gif" width="190" height="3" /></div>
        </div>
    </div>
    
	<logic:equal value="0" name="isAdmin" scope="session">
	<ul class="menus">
        <li><a href="javascript:setLocation('/eduResInfoAction.do?method=beforeAddDirect');" class="menu2"></a></li>
        <div class="clear"></div>
    </ul>
	</logic:equal>
	<logic:equal value="3" name="isAdmin" scope="session">
	<ul class="menus">
		<!-- 
    	<li><a href="javascript:setLocation('/beiKeAction.do?method=jiaoan');" class="menu12"></a></li>
    	<li><a href="javascript:setLocation('/beiKeAction.do?method=kejian');" class="menu1"></a></li>
    	 -->
        <li><a href="javascript:setLocation('/eduResInfoAction.do?method=beforeAddDirect');" class="menu2"></a></li>
        <div class="clear"></div>
    </ul>
	</logic:equal>
	<logic:equal value="1" name="isAdmin" scope="session">
	<ul class="menus">
		<!-- 
		<li><a href="javascript:setLocation('/beiKeAction.do?method=jiaoan');" class="menu12"></a></li>
    	<li><a href="javascript:setLocation('/beiKeAction.do?method=kejian');" class="menu1"></a></li>
    	 -->
        <li><a href="javascript:setLocation('/eduResInfoAction.do?method=beforeAddDirect');" class="menu2"></a></li>
        <li><a href="javascript:setLocation('/eduLargeResInfoUploadAction.do?method=beforeAdd');" class="menu6"></a></li>
        <li><a href="javascript:setLocation('/eduResInfoAction.do?method=beforeAddBatch');" class="menu3"></a></li>
        <li><a href="javascript:setLocation('/eduHtmlResInfoUploadAction.do?method=beforeAdd');" class="menu11"></a></li>
        <li><a href="javascript:setLocation('/eduResInfoCheckAction.do?method=list');" class="menu5"></a></li>
        <li><a href="javascript:setLocation('/eduBulletinInfoAction.do?method=list&subjectid=0');" class="menu8"></a></li>
        <li><a href="javascript:setLocation('/eduNewsInfoAction.do?method=main&subjectid=0');" class="menu10"></a></li>
        <li><a href="javascript:setLocation('/eduBulletinInfoAction.do?method=main');" class="menu7"></a></li>
        <li><a href="javascript:setLocation('/eduNewsInfoAction.do?method=main');" class="menu4"></a></li>
        <li><a href="javascript:setLocation('/sysStatisticsAction.do?method=xktj');" class="menu9"></a></li>
        <div class="clear"></div>
    </ul>
	</logic:equal>
	<logic:equal value="2" name="isAdmin" scope="session">
	<ul class="menus">
		<!-- 
		<li><a href="javascript:setLocation('/beiKeAction.do?method=jiaoan');" class="menu12"></a></li>
    	<li><a href="javascript:setLocation('/beiKeAction.do?method=kejian');" class="menu1"></a></li>
    	 -->
        <li><a href="javascript:setLocation('/eduResInfoAction.do?method=beforeAddDirect');" class="menu2"></a></li>
        <li><a href="javascript:setLocation('/eduLargeResInfoUploadAction.do?method=beforeAdd');" class="menu6"></a></li>
        <li><a href="javascript:setLocation('/eduResInfoAction.do?method=beforeAddBatch');" class="menu3"></a></li>
        <li><a href="javascript:setLocation('/eduHtmlResInfoUploadAction.do?method=beforeAdd');" class="menu11"></a></li>
        <li><a href="javascript:setLocation('/eduResInfoCheckAction.do?method=list');" class="menu5"></a></li>
        <li><a href="javascript:setLocation('/eduBulletinInfoAction.do?method=main');" class="menu7"></a></li>
        <li><a href="javascript:setLocation('/eduNewsInfoAction.do?method=main');" class="menu4"></a></li>
        <div class="clear"></div>
    </ul>
	</logic:equal>
</div>
</body>
</html>
