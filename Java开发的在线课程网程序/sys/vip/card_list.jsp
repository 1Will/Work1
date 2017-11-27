<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="sysVipCardAction.do?method=beforeAdd";
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysVipCardAction.do?method=list";
  document.pageForm.submit();
}
function exportCardno(){
  document.pageForm.action="sysVipCardAction.do?method=beforeExportCardno";
  document.pageForm.submit();
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
    <TD class="page_title" colspan="2">VIP会员卡管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>会员卡管理</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">会员卡号:</td>
                    <td><input type="text" value='<bean:write name="cardno" />' size="30" name="cardno"/></td>
                    <td width="80" height="25" align="right">是否使用:</td>
                    <td><java2code:option name="used" codetype="boolean" valuename="used" ckname="是否使用"/></td>
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
      <INPUT class="btn_del"  onclick="delRecord('sysVipCardAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
      <INPUT class="btn_batchdown"  onclick="exportCardno()" type="button" value="导出会员卡" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr class="table_title">
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">会员卡号</TD>
    <TD class="table_title" width="150">建卡时间</TD>
    <TD class="table_title" width="60">是否使用</TD>
    <TD width="150" class="table_title">使用时间</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="cardid" name="model"/>" <logic:equal value="1" name="model" property="used">disabled="disabled"</logic:equal>></TD>
     <TD align="center"><bean:write name="model" property="cardno"/>
     <TD align="center"><bean:write name="model" property="createdate"/></TD>
     <TD align="center">
       <logic:equal value="0" name="model" property="used">否</logic:equal>
       <logic:equal value="1" name="model" property="used"><font color="red">是</font></logic:equal>
     </TD>
     <td><bean:write name="model" property="usedtime"/></TD>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0  align="center">
  <TR>
    <TD align=center>
       <java2page:pager url="sysVipCardAction.do?method=list" name="pagelist"/>
       <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
