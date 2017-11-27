<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserGroup"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>

<head>
<TITLE>待选用户</TITLE>
<base target="_self">
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
</head>

<SCRIPT language=javascript>
  var num=<bean:write name="pagelist" property="pageCount" />;
  
function doOnLoad(){
 	window.parent.frames["right"].location = "/sysUserRoleAction.do?method=inroleuser&roleid=<bean:write name="roleid"/>";
}
function addUser(){
  if(num>0){
	  var str="此操作将给选中用户授权，真的要继续么？";
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
	     alert("您还没有选择待授权用户呢!")
	}
	else{
	 if(confirm(str)){
	   document.pageForm.action="/sysUserRoleAction.do?method=addroleuser";
	   document.pageForm.submit();
	 }
	}
	}
	else{
	  alert("未选择待授权用户!")
	}
}

function addUserAll(){
  if(num>0){
	  var str="此操作将给左侧所有用户授权，真的要继续么？";
	  if(confirm(str)){
	    document.pageForm.action="/sysUserRoleAction.do?method=addroleuserall";
	    document.pageForm.submit();
	  }
  }
}
function searchRecord(){
  document.getElementById("startcount").value="0";
  document.pageForm.action="/sysUserRoleAction.do?method=outroleuser";
  document.pageForm.submit();
}
function selectGroup(groupid){
	document.pageForm.action="/sysUserRoleAction.do?method=outroleuser";
 	document.pageForm.submit();
}
</SCRIPT>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>

<BODY leftMargin=0 topMargin=0 scroll=auto <logic:present name="reload">onload="javascript:doOnLoad()"</logic:present>>
<FORM name="pageForm" method=post>
<TABLE class="page_maintable">
  
	  <TR>
	    <TD class="page_title" style="font-size:14px;">待选用户</TD>
	  </TR>  
  
  <tr height="30">
   <td>
       <TABLE class="table_search" width="98%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                  	<%
                  	List lst=(List)request.getAttribute("list");
                  	if(lst != null && lst.size() > 0){
                  	%>
                    <td width="50" height="25" align="right">机构:</td>
                    <td>
                    <select name="groupid" onchange="selectGroup(this.value)">
                     <option value="">选择机构</option>
	                 <%
	                 String groupid=(String)request.getAttribute("groupid");
	                 SysUserGroup group = null;
			         for(int i=0;i<lst.size();i++) {
	                   group=(SysUserGroup)lst.get(i);
	                   int len = (group.getGroupno().length() - 8)/4;
	                   if(len < 0) len = 0;
	                 %>
	                    <option value="<%=group.getGroupid()%>" <%if(groupid.equals(group.getGroupid().toString())){ %>selected="selected"<%} %>><%for(int j=0; j<len; j++){%>&nbsp;├<%}%><%=group.getGroupname()%></option>
	                  <%}%>
	                </select>
                    </td>
                    <%} %>
                    <td width="70" height="25" align="right">真实姓名:</td>
                    <td><input type="text" value='<bean:write name="username" />' size="15" name="username"/></td>
                    <td><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
                  </tr>
                </table>
              </td>              
            </tr>
        </table>
   </td>
 </tr>
  
  <TR>
    <TD vAlign=top align="center">
       <TABLE width="100%" align=center border=0><!--serach end-->
              <TR>
                <TD vAlign=top align="center">
                  <TABLE class="page_table" cellpadding="1" cellspacing="1" bgcolor="#CECFCE" width="100%">
                    <tr class="table_title">
                       <TD align="center" width="10%" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
                       <TD align="center" width="40%">登录名</TD>
                       <TD align="center" width="40%">真实姓名</TD>
                       <TD align="center" width="10%">性别</TD>
                    </tr>
                      <logic:iterate id="model" property="datalist" name="pagelist" >
                       <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
                         <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="userid" name="model"/>'></TD>
                         <TD align="left">&nbsp;<bean:write name="model" property="loginname"/></TD>
                         <TD align="center"><bean:write name="model" property="username"/></TD>
                         <TD align="center"><java2code:value codetype="sex" property="sex"/></TD>
                       </TR>
                     </logic:iterate>
                 </TABLE>
                </TD>
              </TR>
              
              <!--page start-->
              <TR>
                <TD vAlign=top borderColor=#ffffff align="center">
                   <java2page:pager url="sysUserRoleAction.do?method=outroleuser" style="2" name="pagelist"/>
                   <input type="hidden" id="startcount" name="startcount" value='<bean:write name="startcount"/>' />
                   <input type="hidden" name="roleid" value='<bean:write name="roleid"/>' />
               </TD>
              </TR>                          
              <!--page end-->
            </TABLE>
    </TD>
    </TR>
</TABLE>
</FORM>
</BODY>
</html>