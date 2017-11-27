<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserGroup"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="size"/>

function addRecord(){
  if(num>0){
  var checkids="";
  if (num>1){
    for(i=0;i<num;i++){
      if (document.pageForm.checkid[i].checked==true){
        checkids=checkids+document.pageForm.checkid[i].value+",";
      }
    }
  }
  else{
   if (document.pageForm.checkid.checked==true)	{
	checkids=document.pageForm.checkid.value;
   }
  }
  if (checkids=="") {
     alert("您还没有选择要发送邮件的用户呢!")
  }
  else{
    if(confirm("确定给选中用户发送邮件信息?")){
	    document.pageForm.action="/oaEmailInfoAction.do?method=addUser";
	    document.pageForm.submit();
    }else{
     return false;
    }
  }
 }
 else{
  alert("未找到可操作记录!")
 }
}

function searchRecord(){
  document.pageForm.action="/oaEmailInfoAction.do?method=selectUserFrame";
  document.pageForm.submit();
}
</SCRIPT>
<title>产品管理</title>
<base target="_self"/>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">用户列表</TD>
 </TR>
 <tr height="30">
   <td colspan="2">
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>用户信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">所属学校:</td>
                     <td colspan="3">
                     <select name="unitid" onchange="searchRecord()">
                     <option value="">请选择学校...</option>
                     <%
                     List unitList = (List)request.getAttribute("unitList");
                     String unitid = (String)request.getAttribute("unitid");
                     SysUnitInfo sui = null;
	               	 for(int i=0, size=unitList.size(); i<size; i++){
	               	 	sui = (SysUnitInfo)unitList.get(i);
                     %>
                     <option value="<%=sui.getUnitid() %>" <%if(sui.getUnitid().toString().equals(unitid)){ %>selected="selected"<%} %>><%=sui.getUnitname() %></option>
                     <%} %>
                     </select>
                     </td>
                  </tr>
                  <tr>
                    <td width="80" height="25" align="right">用户名:</td>
                    <td><input type="text" name="username" value="<bean:write name="username"/>"/></td>
                    <td width="80" height="25" align="right">邮箱:</td>
                    <td><input type="text" name="email" value="<bean:write name="email"/>"/></td>
                  </tr>
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
              </tr>
        </table>
   </td>
 </tr>
 <TR>
    <TD class="page_blank" colspan="2"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_enable"  onclick="addRecord()" type="button" value="确定" name="btnadd">
    </td>
     <TD >
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">登录名</TD>
    <TD class="table_title" width="150">用户名</TD>
    <TD class="table_title" width="50">性别</TD>
    <TD class="table_title" width="120">联系电话</TD>
    <TD class="table_title" width="220">邮箱</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="userList" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center">
       <input type="checkbox" name="checkid" value='<bean:write property="userid" name="model" />'>
     </TD>
     <TD align="left" >&nbsp;<bean:write name="model" property="loginname"/></TD>
     <td align="center"><bean:write name="model" property="username"/></td>
     <td align="center"><java2code:value codetype="sex" property="sex"></java2code:value></td>
     <td align="center"><bean:write name="model" property="mobile"/></td>
     <td align="center"><bean:write name="model" property="email"/></td>
     </tr>
     </logic:iterate>
</TABLE>
</FORM>
</body>
</html>
