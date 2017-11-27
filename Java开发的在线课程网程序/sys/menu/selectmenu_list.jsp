<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function changeMenu(menuid){
  document.pageForm.action="sysMenuInfoAction.do?method=selectMenuDeal&menuid="+menuid;
  document.pageForm.submit();
}
</SCRIPT>
<title>菜单管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body style="overflow-x:hidden;">
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">菜单列表</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
</table>
<TABLE class="table_search_title" width="97%" align="center" cellSpacing=1 cellPadding=1  >
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>
<TABLE cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr bgcolor="#ffffff">
	<logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
	<td width="20%" align="center">
	<table width="100%" align="center">
		<tr>
		<td align="center"><img src="<bean:write property="sketch" name="model"/>" width="82" height="82" onclick="changeMenu('<bean:write property="menuid" name="model"/>')"/></td>
		</tr>
		<tr>
		<td align="center">
		</td>
		</tr>
	</table>
	</td>
	
	<%if((index+1)%5 == 0){ %>
	</tr>
	<tr bgcolor="#ffffff">
	<%} %>
	</logic:iterate>
	
</tr>
</TABLE>


<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysMenuInfoAction.do?method=selectMenuList" name="pagelist" />
      <input type="hidden" name="typeno" id="typeno" value="<bean:write name="typeno"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>

