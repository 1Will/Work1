<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="sysUserGroupAction.do?method=beforeAdd";
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysUserGroupAction.do?method=list";
  document.pageForm.submit();
}

function addGroupMember(groupid){
	var url = '/sysUserGroupMemberAction.do?method=main&groupid=' + groupid;
	var retValue=showModalDialog(url,'部门成员',"dialogWidth:800px;dialogHeight:550px;scroll=auto;border=thin;help=0;status=no");
	document.pageForm.action="sysUserGroupAction.do?method=list";
    document.pageForm.submit();
}

function reloadTree(){
  <logic:present name="reloadtree">
    parent.tree.location ='/sysUserGroupAction.do?method=tree&parentno=<bean:write name="parentno"/>&mproduct=<bean:write name="mproduct"/>';
  </logic:present>  
}
</SCRIPT>
<title>角色管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body onload="javascript:reloadTree()">
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>管理</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>名称:</td>
                    <td><input type="text" value='<bean:write name="groupname" />' size="30" name="groupname"/></td>
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
      <INPUT class="btn_del"  onclick="delRecord('sysUserGroupAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr class="table_title">
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>名称</TD>
    <TD class="table_title" width="120"><logic:equal value="baoxiu" name="mproduct">部门</logic:equal><logic:notEqual value="baoxiu" name="mproduct">用户组</logic:notEqual>编号</TD>
    <logic:equal value="vod" name="mproduct"><TD class="table_title" width="60">权限数值</TD></logic:equal>
    <TD class="table_title" width="60">状态</TD>
    <TD width="80" class="table_title">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="groupid" name="model"/>" <bean:write property="flags" name="model"/>></TD>
     <TD align="left">&nbsp;<bean:write name="model" property="groupname"/>
     <TD align="center"><bean:write name="model" property="groupno"/></TD>
     <logic:equal value="vod" name="mproduct"><TD align="center"><bean:write name="model" property="power"/></TD></logic:equal>
     <TD align="center"><java2code:value codetype="pstate" property="state" colorvalue="0" color="red"></java2code:value></TD>
     <td>
        <java2page:button url="/sysUserGroupAction.do" property="groupid" readonly="E"/>
        <logic:notEqual value="baoxiu" name="mproduct">
        <a style="cursor:pointer;" onclick="addGroupMember('<bean:write name="model" property="groupid"/>')" title="组成员"><img alt="组成员" border="0" src="/public/images/main/icon2.gif"/></a>
        </logic:notEqual>    
      </TD>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0  align="center">
  <TR>
    <TD align=center>
       <java2page:pager url="sysUserGroupAction.do?method=list" name="pagelist"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
       <input type="hidden" name="parentno" value="<bean:write name="parentno"/>" />
       <input type="hidden" name="mproduct" value="<bean:write name="mproduct"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
