<%@ page contentType="text/html;charset=utf-8"%>
<script language="javascript">
 var retValue = new Array();
 retValue[0] = '<%=request.getAttribute("sketch") %>';
 window.parent.window.parent.window.returnValue=retValue;
 window.parent.window.parent.window.close();
</script>
