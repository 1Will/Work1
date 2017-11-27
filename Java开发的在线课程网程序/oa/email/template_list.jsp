<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<Script language="JavaScript" src="/public/DatePicker/WdatePicker.js"></Script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action='/oaEmailTemplateAction.do?method=beforeAdd';
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.action="/oaEmailTemplateAction.do?method=list";
  document.pageForm.submit();
}
</SCRIPT>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">邮件模板</TD>
 </TR>
 <tr height="30">
   <td colspan="2">
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>邮件模板</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                  	<td width="80" height="25" align="right">模板名称:</td>
                    <td><input type="text" name="templatename" style="width:160px;" value="<bean:write name="templatename"/>"/></td>
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
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('/oaEmailTemplateAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD>
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">模板名称</TD>
    <TD class="table_title" width="120">是否自动发送模板</TD>
    <TD class="table_title" width="80">排序</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center">
       <input type="checkbox" name="checkid" value='<bean:write property="templateid" name="model" />'>
     </TD>
     <TD align="left">&nbsp;<bean:write name="model" property="templatename"/></TD>
     <td align="center">
     <logic:equal value="1" name="model" property="autosend"><font color="red">是</font></logic:equal>
     <logic:notEqual value="1" name="model" property="autosend"><font color="gray">否</font></logic:notEqual>
     </td>
     <td align="center"><bean:write name="model" property="orderindex"/></td>
     <td align="center">
     	<java2page:button url="oaEmailTemplateAction.do" property="templateid" readonly="E" />
     </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="oaEmailTemplateAction.do?method=list" name="pagelist" />
      <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
