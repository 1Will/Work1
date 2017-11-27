<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>所属机构</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<iframe frameborder="0" marginheight="0" marginwidth="0" scrolling="yes" width="100%" height="100%" src="sysUserGroupMemberAction0.do?method=userframe&userid=<bean:write name="userid"/>">
</iframe>
</BODY>
</html:html>
