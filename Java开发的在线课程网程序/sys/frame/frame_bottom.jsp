
<%@page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<script LANGUAGE="Javascript" src="/public/js/comm.js"></script>
</HEAD>
<BODY style="overflow-y:hidden;overflow-x:hidden"  >
<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="4" height="25" align="left" valign="top"><img src="/public/images/frame/botton001.gif" width="4" height="25"></td>
    <td align="left" valign="middle" background="/public/images/frame/botton002.gif">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前单位：<bean:write name="s_sysunitinfo" property="unitname"/>&nbsp;&nbsp;
      　&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td width="62" height="25" align="left" valign="top"><img src="/public/images/frame/botton003.gif" width="62" height="25"></td>
  </tr>
</table>
</BODY>
</html:html>
