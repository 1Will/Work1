<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<%@ include file="../../public/jsp/style.jsp"%>
<head>
<%@ include file="../../public/jsp/meta.jsp"%>
<title>地区管理</title>
</head>
<frameset cols="180,*" frameborder=0>
	<frame name="tree" src="gpwAreaInfoAction.do?method=tree"
		scrolling="auto" style="border-style:dotted;border-width: 0px 1px 0px 0px" frameborder=0 marginwidth=0 marginheight=0>
	<frame name="mainRight" src="/sysFrameAction.do?method=frame_welcome"
	 scrolling="auto" frameborder=0 marginwidth=0 marginheight=0>
</frameset>
</html>
