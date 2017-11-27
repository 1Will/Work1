<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<HEAD>
<TITLE><bean:write name="s_sysunitinfo" property="unitname"/></TITLE>
<SCRIPT language=JavaScript>
 window.history.forward(1);
 self.moveTo(0,0);
 self.resizeTo(screen.availWidth,screen.availHeight);
 self.focus();
</SCRIPT>

<META content="MSHTML 6.00.3790.94" name=GENERATOR>
</HEAD>
<FRAMESET id=frame1 border=0 frameSpacing=0 rows=86,*,26 frameBorder=NO cols=*>
   <FRAME name="topFrame" src="sysFrameAction.do?method=frame_top" frameBorder=0 noResize scrolling=no>
   <FRAMESET id=frame2 border=0 frameSpacing=0 rows=* frameBorder=NO cols=184,10,*>
     <FRAME name="leftFrame" src="sysFrameAction.do?method=frame_left" frameBorder=0 noResize scrolling=no>
     <FRAME name="switchFrame" src="sysFrameAction.do?method=frame_left_switch" frameBorder=0 noResize scrolling=no>
     <FRAME name="mainFrame" src="sysFrameAction.do?method=frame_work" frameBorder=0>
   </FRAMESET>
   <FRAME name="bottomFrame" src="sysFrameAction.do?method=frame_bottom" frameBorder=0 noResize scrolling=no>
</FRAMESET>
</HTML>
