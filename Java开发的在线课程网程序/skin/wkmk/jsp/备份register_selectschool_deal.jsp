<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@ page contentType = "text/html;charset=utf-8"%>
<%SysUnitInfo sui = (SysUnitInfo)request.getAttribute("model"); %>
<script language="javascript">
  var retValue = new Array();
  retValue[0] ="<%=sui.getUnitid().toString() %>";
  retValue[1] ="<%=sui.getUnitname() %>";
  window.parent.window.returnValue=retValue;
  window.parent.window.close();
</script>
