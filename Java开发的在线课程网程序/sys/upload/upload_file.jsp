<%@ page contentType = "text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<html:html>
<title>上载资源</title>
<head>
</head>
<body leftmargin="0" topmargin="0" >
<iframe frameborder=0 marginheight=0 marginwidth=0 scrolling=auto src="sysFileUploadAction.do?method=uploadfileframe&savepath=<bean:write name="savepath"/>&foldertype=<bean:write name="foldertype"/>" width="100%"  height="100%" style="z-index: -100" ></iframe>
</body>
</html:html>
