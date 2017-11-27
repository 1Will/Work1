<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.bo.SysRoleInfo"%>
<%@page import="com.bzt.sys.bo.SysProductInfo"%>
<%@page import="com.bzt.sys.bo.SysUnitProduct"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<html:html>
<HEAD>
<TITLE>角色权限</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%
List hasroleids = (List)request.getAttribute("hasroleids");
//所有产品例表
List allproductList = (List)request.getAttribute("allproductList");
%>
<SCRIPT language=javascript>
function setReadState(flag) {
	var theForm = document.pageForm;
	for(var z=0; z<theForm.length;z++){
	  if(theForm[z].type == 'checkbox'){
	    theForm[z].checked = flag
	  }
	}
}

function updateUserRole(){
  document.pageForm.action="sysUserInfoAction.do?method=updateUserRole";
  document.pageForm.submit();
}

</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<FORM name="pageForm" method="post" >
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
<tr>
  <td align="center" width="100%">
    <INPUT class="btn_all" onclick="javascript:setReadState(true)" type="button" value="全选" name="selectall">
    <INPUT class="btn_none"  onclick="javascript:setReadState(false)" type="button" value="全不选" name="selectnone">

     <INPUT class="btn_save"  onclick="javascript:updateUserRole();" type="button" value="确定" name="btnok">
     <INPUT class="btn_del"  onclick="top.Dialog.close();" type="button" value="关闭" name="btnclose">
  </td>
</tr>
<TR>
  <TD>&nbsp;&nbsp;</td>
</tr>
<%
if(allproductList != null && allproductList.size() > 0){
for(int i=0;i<allproductList.size();i++){
  SysUnitProduct unitProduct = (SysUnitProduct) allproductList.get(i);
  SysProductInfo productInfo = unitProduct.getSysProductInfo();
%>
<%-- 
<tr>
  <td style="color:green;">
    <%=productInfo.getProductname() %>
  </td>
</tr>
--%>
<%
List roleList = (List)request.getAttribute("roleList_" + productInfo.getProductid());
if(roleList != null && roleList.size() > 0){
for(int j=0;j<roleList.size();j++){
  SysRoleInfo roleInfo = (SysRoleInfo)roleList.get(j);
  if(!hasroleids.contains(roleInfo.getRoleid().toString()) && "0000".equals(roleInfo.getRoleno())){
  	continue;
  }
%>
<tr>
  <td>
    &nbsp;<!-- &nbsp;&nbsp;&nbsp; -->
    <%if(hasroleids.contains(roleInfo.getRoleid().toString()) && "0000".equals(roleInfo.getRoleno())){%>
      <input title="选择" type="checkbox" checked="checked" disabled="disabled" value="<%=roleInfo.getRoleid() %>"><%=roleInfo.getRolename() %>
      <input type="hidden" name="checkid" checked="checked" value="<%=roleInfo.getRoleid() %>">
    <%}else if(hasroleids.contains(roleInfo.getRoleid().toString())){%>
      <input title="选择" type="checkbox" name="checkid" checked="checked" value="<%=roleInfo.getRoleid() %>"><%=roleInfo.getRolename() %>
    <%}else{%>
    <input title="选择" type="checkbox" name="checkid" value="<%=roleInfo.getRoleid() %>"><%=roleInfo.getRolename() %>
    <%} %>
  </td>
</tr>
<%}}%>

<%}}%>
<TR>
  <TD colspan="2">&nbsp;&nbsp;</td>
</tr>
</table>
<input type="hidden" name="userid" value="<bean:write name="userid"/>">
</form>
</BODY>
</html:html>
