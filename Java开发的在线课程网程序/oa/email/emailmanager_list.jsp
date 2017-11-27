<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<Script language="JavaScript" src="/public/DatePicker/WdatePicker.js"></Script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action='/oaEmailManagerAction.do?method=beforeAdd';
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.action="/oaEmailManagerAction.do?method=list";
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
    <TD class="page_title" colspan="2">邮箱管理</TD>
 </TR>
 <TR>
    <TD class="page_blank" colspan="2"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('/oaEmailManagerAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD>
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title" width="200">发件人</TD>
    <TD class="table_title">邮箱</TD>
    <TD class="table_title" width="80">排序</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center">
       <input type="checkbox" name="checkid" value='<bean:write property="id" name="model" />'>
     </TD>
     <td align="center"><bean:write name="model" property="showname"/></td>
     <TD align="left">&nbsp;<bean:write name="model" property="email"/></TD>
     <td align="center"><bean:write name="model" property="orderindex"/></td>
     <td align="center">
     	<java2page:button url="oaEmailManagerAction.do" property="id" readonly="E" />
     </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="oaEmailManagerAction.do?method=list" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
