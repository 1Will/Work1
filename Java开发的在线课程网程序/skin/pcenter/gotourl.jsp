<%@page import="com.util.encrypt.Base64Utils"%>
<%@page import="com.util.date.DateTime"%>
<%@page import="com.util.encrypt.MD5"%>
<%
String requestStr = MD5.getEncryptPwd(DateTime.getDate()).toUpperCase().substring(0, 8) + Base64Utils.base64Encoder(DateTime.getDate("yyyy-MM-dd HH"));
%>
<form name="reloadFrom" method="post">
<input type="hidden" name="reback" value="<%=requestStr %>"/>
</form>
<script type="text/javascript">
document.reloadFrom.action = 'http://60.31.142.75:81/sysUserLoginAction.fo?method=loginDefault';
document.reloadFrom.submit();
</script>