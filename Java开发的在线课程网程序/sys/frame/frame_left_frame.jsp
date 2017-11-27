<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>
<HEAD>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<LINK href="public/css/style.css" type="text/css" rel="stylesheet">
<SCRIPT language=JavaScript src="public/js/module_menu.js"></SCRIPT>
<SCRIPT language=JavaScript>
function moduleClick(obj,url,open){
  <logic:iterate id="model" name="datalist">
    document.getElementById("tb_<bean:write property="moduleid" name="model" />").className="menu_table_mouseout";
  </logic:iterate>
  document.getElementById("tb_"+obj).className="menu_table_mousedown";
  if(1==open){
     parent.parent.switchFrame.leftmenu_close();
  }
  parent.parent.mainFrame.main.location=url;
}
</SCRIPT>
</HEAD>
<BODY leftMargin="0" topMargin="0" marginheight="0">
<TABLE align="center" cellspacing="0" cellpadding="0" style="width:166px;">
<logic:iterate id="model" name="datalist">
<tr>
  <td height="2"></td>
</tr>
<TR>
<TD align="center" vAlign="middle" height="30" class="menu_table_mouseout" style="cursor:pointer;" title="→"
onclick="javascript:moduleClick('<bean:write property="moduleid" name="model" />','<bean:write property="linkurl" name="model" />','<bean:write property="autoopen" name="model" />')">
<table id="tb_<bean:write name="model" property="moduleid"/>" width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
  <td align="center"  height="30" width="50"><IMG height=18 src="/public/images/menu/<bean:write property="moduleicon" name="model" />" width=18 border=0></td>
  <td align="left"><bean:write name="model" property="modulename"/></td>
</tr>
</table>
</TD>
</TR>
</logic:iterate>
</table>
</html:html>
