<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.bo.SysUserGroup"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<html:html>
<HEAD>
<TITLE>角色权限</TITLE>
<%@ include file="../../public/jsp/style.jsp"%>
<%
List userallgroupids = (List)request.getAttribute("userallgroupids");//用户已关联组id
//所有产品例表
List parentList = (List)request.getAttribute("parentList");
Map childMap = (Map)request.getAttribute("childMap");
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

function updateUserGroup(){
  document.pageForm.action="sysUserGroupMemberAction0.do?method=deal";
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
     <INPUT class="btn_save"  onclick="javascript:updateUserGroup();" type="button" value="确定" name="btnok">
     <INPUT class="btn_del"  onclick="javascript:window.close();" type="button" value="关闭" name="btnclose">
  </td>
</tr>
<TR>
  <TD>&nbsp;&nbsp;</td>
</tr>
<%
SysUserGroup group = null;
SysUserGroup sug = null;
for(int i=0;i<parentList.size();i++){
  group = (SysUserGroup) parentList.get(i);
%>
<tr>
  <td>
    <input title="选择" type="checkbox" name="checkid" <%if(userallgroupids.contains(group.getGroupid())){ %>checked="checked"<%} %> value="<%=group.getGroupid() %>"><%=group.getGroupname() %>
  </td>
</tr>
<%
List child = (List)childMap.get(group.getGroupno());
if(child != null && child.size() > 0){
for(int j=0;j<child.size();j++){
  sug = (SysUserGroup)child.get(j);
%>
<tr>
  <td>
    <img src="/public/images/comm/bullet_end.gif"/>
    <input title="选择" type="checkbox" name="checkid" <%if(userallgroupids.contains(sug.getGroupid())){ %>checked="checked"<%} %> value="<%=sug.getGroupid() %>"><%=sug.getGroupname() %>
  </td>
</tr>
<%
List child0 = (List)childMap.get(sug.getGroupno());
if(child0 != null && child0.size() > 0){
for(int k=0;k<child0.size();k++){
  sug = (SysUserGroup)child0.get(k);
%>
<tr>
  <td>
    &nbsp;&nbsp;&nbsp;&nbsp;<img src="/public/images/comm/bullet_end.gif"/>
    <input title="选择" type="checkbox" name="checkid" <%if(userallgroupids.contains(sug.getGroupid())){ %>checked="checked"<%} %> value="<%=sug.getGroupid() %>"><%=sug.getGroupname() %>
  </td>
</tr>
<%}}%>
<%}}%>

<%}%>
<TR>
  <TD colspan="2">&nbsp;&nbsp;</td>
</tr>
</table>
<input type="hidden" name="userid" value="<bean:write name="userid"/>">
</form>
</BODY>
</html:html>
