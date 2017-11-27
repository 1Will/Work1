<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="sysUserFavoritesAction.do?method=beforeAdd";
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysUserFavoritesAction.do?method=list";
  document.pageForm.submit();
}

function reloadTree(){
  <logic:present name="reloadtree">
    parent.tree.location ='/sysUserFavoritesAction.do?method=tree';
  </logic:present>  
}
</SCRIPT>
<title>收藏夹管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body onload="javascript:reloadTree()">
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">收藏夹管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>收藏夹</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">栏目名称:</td>
                    <td><input type="text" size="20" name="name" value='<bean:write name="name" />' /></td>
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
      <INPUT class="btn_del"  onclick="delRecord('sysUserFavoritesAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">栏目名称</TD>
    <TD class="table_title" width="120">栏目编号</TD>
    <TD class="table_title" width="100">栏目父编号</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="fid" name="model"/>" <bean:write property="flags" name="model"/> /> </TD>
        <td align="left">&nbsp;<bean:write property="name" name="model" /></a></td>
         <td align="center">&nbsp;<bean:write property="fno" name="model" /></td>
         <td align="center">&nbsp;<bean:write property="parentno" name="model" /></td>
         <td align="center">
			<java2page:button url="sysUserFavoritesAction.do" property="fid" readonly="E"/>
         </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUserFavoritesAction.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
      <input type="hidden" name="parentno" value="<bean:write name="parentno"/>"/>
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
