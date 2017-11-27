<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="sysRoleInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}
function getRoleModule(roleid){
	var diag = new top.Dialog();
	diag.Title = "角色权限";
	diag.URL = "sysRoleModuleAction.do?method=main&roleid="+roleid;
	diag.Width = 400;
	diag.Height = 520;
	diag.CancelEvent = function(){
		document.pageForm.action="sysRoleInfoAction.do?method=list";
		document.pageForm.submit();
		diag.close();
	};
	diag.show();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysRoleInfoAction.do?method=list";
  document.pageForm.submit();
}

function addUserRole(roleid) {
	var diag = new top.Dialog();
	diag.Title = "角色人员";
	diag.URL = "/sysUserRoleAction.do?method=main&roleid="+roleid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		document.pageForm.action="sysRoleInfoAction.do?method=list";
		document.pageForm.submit();
		diag.close();
	};
	diag.show();
}
</SCRIPT>
<title>角色管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body >
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">角色管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>角色管理</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">角色名称:</td>
                    <td><input type="text" value='<bean:write name="rolename" />' size="30" name="rolename"/></td>
                  </tr>
                </table>
              </td>
              <td width="88" class="bg_basecolor"><input type="button" value="搜索" onclick="searchRecord()" class="btn_search" /></td>
              </tr>
        </table>
   </td>
 </tr>
 <TR>
    <TD class="page_blank"></TD>
  </TR>

 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('sysRoleInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr class="table_title">
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title" width="100">角色编号</TD>
    <TD class="table_title">角色名称</TD>
    <TD class="table_title" width="60">角色状态</TD>
    <TD width="100" class="table_title">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="roleid" name="model"/>" <bean:write property="flags" name="model"/>></TD>
     <TD align="center"><bean:write name="model" property="roleno"/>
     <TD align="left">&nbsp;<bean:write name="model" property="rolename"/></TD>
     <TD align="center">
       <logic:equal value="0" name="model" property="state">禁用</logic:equal>
       <logic:equal value="1" name="model" property="state">在用</logic:equal>
     </TD>
     <td>
        <java2page:button url="/sysRoleInfoAction.do" property="roleid" readonly="E"/>
        <a style="cursor:pointer;" title="角色模块" onclick="javascript:getRoleModule('<bean:write name="model" property="roleid"/>')"><img border="0"  src="/public/images/main/module.gif"/></a>&nbsp; 
        <logic:present name="s_unitid" scope="session">  
        <a style="cursor:pointer;" onclick="addUserRole('<bean:write name="model" property="roleid"/>')" title="角色人员"><img alt="角色人员" border="0" src="/public/images/main/icon2.gif"/></a>    
        </logic:present>
      </TD>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0  align="center">
  <TR>
    <TD align=center>
       <java2page:pager url="sysRoleInfoAction.do?method=list" name="pagelist"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
