<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-选择学校</title>
<head>
</head>
<body>
<iframe frameborder=0 marginheight=0 marginwidth=0 scrolling=auto src="/v.bo?method=selectSchoolList" width="100%" height="96%"></iframe>
</body>
</html:html>
