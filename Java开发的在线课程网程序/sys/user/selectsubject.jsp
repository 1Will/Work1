<%@ page contentType = "text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<html:html>
<title>选择<bean:write name="SC_szxysubjectname"/>栏目</title>
<head>
</head>
<body leftmargin="0" topmargin="0" >
<iframe frameborder=0 marginheight=0 marginwidth=0 scrolling=auto src="/sysUserInfoAction.do?method=selectSubjectFrame&flag=<bean:write name="flag"/>&objid=<bean:write name="objid"/>" width="100%" height="100%"></iframe>
</body>
</html:html>
