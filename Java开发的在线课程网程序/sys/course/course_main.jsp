<%@ page contentType = "text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<html:html>
<title>课程表设置</title>
<head>
</head>
<body leftmargin="0" topmargin="0" >
<iframe frameborder=0 marginheight=0 marginwidth=0 scrolling=auto src="/sysCourseTableAction.do?method=updateMyCourseTableFrame&week=<bean:write name="week"/>&noon=<bean:write name="noon"/>&orderindex=<bean:write name="orderindex"/>" width="100%" height="100%"></iframe>
</body>
</html:html>
