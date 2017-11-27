<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.bo.SysModuleInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<html:html>
<HEAD>
<TITLE>角色权限</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%
List usermoduleids = (List)request.getAttribute("usermoduleids");
Integer selectsize = (Integer)request.getAttribute("selectsize");
//所有模块例表
List allList = (List)request.getAttribute("allList");
//没有子模块的模块列表
List nosubList = (List)request.getAttribute("nosubList");
//当前用户拥有的权限模块
List permissionlist = (List)request.getAttribute("permissionlist");
%>
<SCRIPT language=javascript>
var ssize = <%=selectsize %>;
var size = 0;
function checkSelect(id) {
	var box = document.getElementById(id);
	if(box.checked){
		if(size < ssize){
			size = size + 1;
		}else{
			document.getElementById(id).checked = false;
			alert("用户自定义菜单已选满，最多选择不能再超过"+ssize+"个菜单。");
		}
	}else{
		size = size - 1;
	}
}

function updateModule(){
  document.pageForm.action="sysUserMenuAction.do?method=deal&selectsize=" + ssize;
  document.pageForm.submit();
}

</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0 scroll=auto>
<FORM name="pageForm" method="post" >
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
<tr>
  <td align="center" width="100%">
     <INPUT class="btn_save"  onclick="javascript:updateModule();" type="button" value="确定" name="btnok">
     <INPUT class="btn_del"  onclick="javascript:window.close();" type="button" value="关闭" name="btnclose">
  </td>
</tr>
<TR>
  <TD>&nbsp;&nbsp;</td>
</tr>
<%
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
%>
<%if(permissionlist.contains(module) && mproductids[m].equals(module.getMproduct())){%>
<tr>
  <td>
    <%for(int j=0;j<(module.getModuleno().length()-6)/2;j++){%>&nbsp;<%}%>
    <%if(nosubList.contains(module.getModuleid())){%>
      <img src="/public/images/comm/bullet_end.gif"/>
      <input title="选择" type="checkbox" name="checkid" id="m_<%=module.getModuleid() %>" <%if(usermoduleids.contains(module.getModuleid())){ %>disabled="disabled"<%} %> value="<%=module.getModuleid()%>" onclick="checkSelect('m_<%=module.getModuleid() %>')"><%=module.getModulename()%>
    <%}
    else{%>
     <img src="/public/images/comm/plus.gif"/>&nbsp;<%=module.getModulename()%>
    <%}%>
  </td>
</tr>
<%} %>
<%}}%>
<TR>
  <TD colspan="2">&nbsp;&nbsp;</td>
</tr>
</table>
</form>
</BODY>
</html:html>
