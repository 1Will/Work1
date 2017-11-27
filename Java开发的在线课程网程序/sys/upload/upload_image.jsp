<%@ page contentType = "text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<html:html>
<title>插入图片</title>
<head>
</head>
<body leftmargin="0" topmargin="0" >
<iframe frameborder=0 marginheight=0 marginwidth=0 scrolling=no src="sysImageUploadAction.do?method=uploadimageframe&filetype=&savepath=<bean:write name="savepath"/>&filename=<bean:write name="filename"/>&sketch=<bean:write name="sketch"/>&pathtype=<bean:write name="pathtype"/>" width="350"  height="180" style="z-index: -100" ></iframe>
</body>
</html:html>
