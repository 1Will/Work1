<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<%@ include file="../../public/jsp/style.jsp"%>
<head>
<%@ include file="../../public/jsp/meta.jsp"%>
<title>页面框架</title>
</head>
<frameset cols="160,*" frameborder=1>
	<frame name="tree" src="sysModuleInfoAction.do?method=tree&productid=<bean:write name="productid" scope="request"/>" scrolling="auto" frameborder=0 marginwidth=0 marginheight=0>
	<frame name="mainRight" src="sysMainFrameAction.do?method=frame_welcome" scrolling="no" frameborder=0 marginwidth=0 marginheight=0>
</frameset>
</html>
