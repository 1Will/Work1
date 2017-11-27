<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<HEAD>
<TITLE>统一用户管理系统</TITLE>

<META content="MSHTML 6.00.3790.94" name=GENERATOR>
</HEAD>
<logic:equal value="1" name="tag" scope="request">
<FRAMESET id=frame2 border=1 frameSpacing=0 rows=* frameBorder=NO cols=*>
   <FRAME name="leftFrame" id="leftFrame" src="sysProductInfoAction.do?method=list" frameBorder=0>
</FRAMESET>
</logic:equal>
<logic:equal value="2" name="tag" scope="request">
<FRAMESET id=frame2 border=1 frameSpacing=0 rows=* frameBorder=NO cols=60%,40%>
   <FRAME name="leftFrame" id="leftFrame" src="sysUnitInfoAction.do?method=list" frameBorder=0>
   <FRAME name="rightFrame" id="rightFrame" src="sysUnitProductAction.do?method=productList" frameBorder=0>
</FRAMESET>
</logic:equal>
</HTML>
