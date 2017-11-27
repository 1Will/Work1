<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.bo.SysModuleInfo"%>
<%@page import="com.bzt.sys.bo.SysRoleModule"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<html:html>
<HEAD>
<TITLE>角色权限</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%
List hasList = (List)request.getAttribute("hasList");
//所有模块例表
List allList = (List)request.getAttribute("allList");
//没有子模块的模块列表
List nosubList = (List)request.getAttribute("nosubList");
%>
<SCRIPT language=javascript>
/*
 var num=<%=nosubList.size()%>;
 function setReadState(flag) {
  if (num>1){
   for(i=0;i<num;i++)	{
    document.pageForm.checkid[i].checked = flag;
   }
 }
 else{
   document.pageForm.checkid.checked = flag;
 }
}
*/
function setReadState(flag) {
	var theForm = document.pageForm;
	for(var z=0; z<theForm.length;z++){
	  if(theForm[z].type == 'checkbox'){
	    theForm[z].checked = flag
	  }
	}
}

function updateModule(){
  document.pageForm.action="sysRoleModuleAction1.do?method=updateRoleModule";
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

     <INPUT class="btn_save"  onclick="javascript:updateModule();" type="button" value="确定" name="btnok">
     <INPUT class="btn_del"  onclick="javascript:window.close();" type="button" value="关闭" name="btnclose">
  </td>
</tr>
<TR>
  <TD>&nbsp;&nbsp;</td>
</tr>
<%
String productid = (String)request.getAttribute("productid");
if("1".equals(productid)){
String[] mproductids = Constants.getCodeTypeid("mproduct");
String[] mproductnames = Constants.getCodeTypename("mproduct");
for(int m=0; m<mproductids.length; m++){
%>
<TR>
  <TD style="color:green;">【<%=mproductnames[m] %>】</td>
</tr>
<%
for(int i=0;i<allList.size();i++){
  SysModuleInfo module = (SysModuleInfo)allList.get(i);
   if(mproductids[m].equals(module.getMproduct())){
%>
<tr>
  <td>
    <%for(int j=0;j<(module.getModuleno().length()-6)/2;j++){%>&nbsp;<%}%>
    <%if(nosubList.contains(module.getModuleid())){%>
      <img src="/public/images/comm/bullet_end.gif"/>
      <%
      	String checked = "";
      	String add = "";
      	String del = "";
      	String mod = "";
      	String view = "";
      	for(int k=0;k<hasList.size();k++){
      		SysRoleModule roleModule = (SysRoleModule)hasList.get(k);
      		if(module.getModuleid().equals(roleModule.getSysModuleInfo().getModuleid())){
      			checked = "checked";
      			if("1".equals(roleModule.getAdd())) add = "checked";
      			if("1".equals(roleModule.getDel())) del = "checked";
      			if("1".equals(roleModule.getMod())) mod = "checked";
      			if("1".equals(roleModule.getView())) view = "checked";
      			hasList.remove(k);
      			break;
      		}
      	}
      %>
      
      <input title="选择" type="checkbox" name="checkid" <%=checked %> value="<%=module.getModuleid()%>"><%=module.getModulename()%>
      &nbsp;<input title="增" type="hidden" name="<%=module.getModuleid()%>a" value="1" <%=add %>>
      &nbsp;<input title="删" type="hidden" name="<%=module.getModuleid()%>d" value="1" <%=del %>>
      &nbsp;<input title="改" type="hidden" name="<%=module.getModuleid()%>m" value="1" <%=mod %>>
      &nbsp;<input title="查" type="hidden" name="<%=module.getModuleid()%>v" value="1" <%=view %>>
    <%}
    else{%>
     <img src="/public/images/comm/plus.gif"/>&nbsp;<%=module.getModulename()%>
    <%}%>
  </td>
</tr>
<%}}%>
<%}}else{ %>
<%
for(int i=0;i<allList.size();i++){
  SysModuleInfo module = (SysModuleInfo)allList.get(i);
%>
<tr>
  <td>
    <%for(int j=0;j<(module.getModuleno().length()-6)/2;j++){%>&nbsp;<%}%>
    <%if(nosubList.contains(module.getModuleid())){%>
      <img src="/public/images/comm/bullet_end.gif"/>
      <%
      	String checked = "";
      	String add = "";
      	String del = "";
      	String mod = "";
      	String view = "";
      	for(int k=0;k<hasList.size();k++){
      		SysRoleModule roleModule = (SysRoleModule)hasList.get(k);
      		if(module.getModuleid().equals(roleModule.getSysModuleInfo().getModuleid())){
      			checked = "checked";
      			if("1".equals(roleModule.getAdd())) add = "checked";
      			if("1".equals(roleModule.getDel())) del = "checked";
      			if("1".equals(roleModule.getMod())) mod = "checked";
      			if("1".equals(roleModule.getView())) view = "checked";
      			hasList.remove(k);
      			break;
      		}
      	}
      %>
      
      <input title="选择" type="checkbox" name="checkid" <%=checked %> value="<%=module.getModuleid()%>"><%=module.getModulename()%>
      &nbsp;<input title="增" type="hidden" name="<%=module.getModuleid()%>a" value="1" <%=add %>>
      &nbsp;<input title="删" type="hidden" name="<%=module.getModuleid()%>d" value="1" <%=del %>>
      &nbsp;<input title="改" type="hidden" name="<%=module.getModuleid()%>m" value="1" <%=mod %>>
      &nbsp;<input title="查" type="hidden" name="<%=module.getModuleid()%>v" value="1" <%=view %>>
    <%}
    else{%>
     <img src="/public/images/comm/plus.gif"/>&nbsp;<%=module.getModulename()%>
    <%}%>
  </td>
</tr>
<%}%>
<%} %>
<TR>
  <TD colspan="2">&nbsp;&nbsp;</td>
</tr>
</table>
<input type="hidden" name="roleid" value="<bean:write name="roleid"/>">
<input type="hidden" name="productid" value="<bean:write name="productid"/>">
<input type="hidden" name="unitid" value="<bean:write name="unitid"/>">
</form>
</BODY>
</html:html>
