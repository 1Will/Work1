<%@page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<Head>
</head>
<BODY >
<logic:present name="tag" scope="request">
<logic:equal value="big" name="tag" scope="request">
<table width="100%" height="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="middle"> 
      <img src="/public/images/comm/welcome_middle.gif"/>  
  </td>
  </tr>
</table>
</logic:equal>
<logic:notEqual value="big" name="tag" scope="request">
<table width="100%" height="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="middle"> 
      <img src="/public/images/comm/welcome.jpg"/>
  </td>
  </tr>
</table>
</logic:notEqual>
</logic:present>
<logic:notPresent name="tag" scope="request">
<table width="100%" height="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="middle"> 
      <img src="/public/images/comm/welcome.jpg"/>
  </td>
  </tr>
</table>
</logic:notPresent>
</BODY>
</html:html>
