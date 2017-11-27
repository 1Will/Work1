<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/lanrentuku.css" rel="stylesheet" type="text/css" />
<script src="/skin/desktop/js/iepng.js" type="text/javascript"></script>
<script src="/skin/desktop/js/jquery-1.2.6.min.js" type=text/javascript></script>
<SCRIPT src="/skin/desktop/js/dicengComm_pack_v1.0.1.js" type=text/javascript></SCRIPT>
<script src="/skin/desktop/js/tab.js" type=text/javascript></script>
<SCRIPT type=text/javascript>
TencentArticl.onload();
</SCRIPT>
</head>
<body style="background:none;">
<form name="menuForm" method="post" target="_top">
<DIV class="mod-left bottom-Article-QQ sildPic-Article-QQ">
	<DIV class=hd>
		<H2>快捷菜单</H2>
		<DIV class=sildPicBar id=sildPicBar>
			<SPAN class=pre></SPAN>
			<UL id=dot></UL>
        		<SPAN class=next></SPAN>
		</DIV>

	</DIV>
	<DIV class=bd>
		<DIV class=cnt-wrap id=cnt-wrap>
			<DIV class=cnt id=cnt>
                <UL>
                <logic:iterate id="menu" name="menuList">
                  <LI><A href=javascript:setLocation('<bean:write name="menu" property="sysModuleInfo.mproduct"/>','<bean:write name="menu" property="sysModuleInfo.linkurl"/>') title='<bean:write name="menu" property="sysModuleInfo.modulename"/>'><IMG src="<bean:write name="menu" property="sketch"/>" width="82" height="81" border="0"></A></LI>
                </logic:iterate>  
                </UL>
  			</DIV>
		</DIV>
	</DIV>
  
</DIV>
<input type="hidden" name="onclickmenu" id="onclickmenu" value=""/>
</form>
</body>
</html>
