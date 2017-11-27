<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<%@ include file="../../public/jsp/style.jsp"%>
<head>
<%@ include file="../../public/jsp/meta.jsp"%>
<title>页面框架</title>
</head>
<frameset cols="160,*">
	<frame name="tree" src="sysMyFavoritesAction.do?method=tree"
		scrolling="auto" frameborder=0 marginwidth=0 marginheight=0 >
	<frame name="mainRight"
		src="sysMyFavoritesAction.do?method=right&parentno=0000"
		scrolling="auto" frameborder=0 marginwidth=0 marginheight=0 >
</frameset>
</frameset>
</html>
