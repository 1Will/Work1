<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<TITLE>角色权限</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<iframe frameborder="0" marginheight="0" marginwidth="0" scrolling="yes" width="100%" height="100%" src="sysRoleModuleAction1.do?method=frame&roleid=<bean:write name="roleid"/>&productid=<bean:write name="productid"/>&unitid=<bean:write name="unitid"/>">
</iframe>
</BODY>
</html:html>
