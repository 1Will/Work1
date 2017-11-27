<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action='/sysUserTitleAction.do?method=beforeAdd';
  document.pageForm.submit();
}
</SCRIPT>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">用户头衔</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <!-- 
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('/sysUserTitleAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
  -->
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <!-- <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD> -->
    <TD class="table_title" width="35" >序号</TD>
    <TD class="table_title">头衔名称</TD>
    <TD class="table_title" width="100">最小积分值</TD>
    <TD class="table_title" width="100">最大积分值</TD>
    <TD class="table_title" width="80">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <!-- <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="id" name="model" />'></TD> -->
     <TD align="center"><%=index + 1 %></TD>
     <TD align="left">&nbsp;<bean:write name="model" property="name"/></TD>
     <td align="center"><bean:write name="model" property="minvalue"/></td>
     <td align="center"><bean:write name="model" property="maxvalues"/></td>
     <td align="center">
       <java2page:button url="sysUserTitleAction.do" property="id" readonly="E" />
     </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUserTitleAction.do?method=list" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
