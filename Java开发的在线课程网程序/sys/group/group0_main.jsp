<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<%@ include file="../../public/jsp/style.jsp"%>
<head>
<%@ include file="../../public/jsp/meta.jsp"%>
<title>页面框架</title>
</head>
<frameset cols="180,*" frameborder=1>
	<frame name="tree" src="sysUserGroupAction0.do?method=tree"
		scrolling="auto" frameborder=0 marginwidth=0 marginheight=0>
	<frame name="mainRight" src="sysUserGroupAction0.do?method=list&parentno=0000"
	 scrolling="auto" frameborder=0 marginwidth=0 marginheight=0>
</frameset>
</html>
