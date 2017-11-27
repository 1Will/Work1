<%@ page contentType = "text/html;charset=utf-8"%>
<script language="javascript">
  var retValue = new Array();
  retValue[0] ="<%=(String)request.getAttribute("receivers")%>";
  retValue[1] ="<%=(String)request.getAttribute("userids")%>";
  window.parent.window.returnValue=retValue;
  window.parent.window.close();
</script>
