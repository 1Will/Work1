<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="sysCodeDataAction.do?method=hasbeforeAdd";
  document.pageForm.submit();
}

function doEdit(codeid){
	document.pageForm.action = 'sysCodeDataAction.do?method=hasbeforeUpdate&objid=' + codeid;
	document.pageForm.submit();
}
</SCRIPT>
<title>管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body >
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">[<bean:write property="codedata" name="codeData"/>]码表值</TD>
 </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('sysCodeDataAction.do?method=hasdelBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE align="center" width="98%" border="0" cellpadding="4" cellspacing="1" bgcolor="#CECFCE" style="table-layout:fixed;word-break:break-all">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">名称</TD>
    <TD class="table_title" width="250">编号</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="codeid" name="model"/>">
     </TD>
      <TD align="left">&nbsp;
        <bean:write name="model" property="codedata"/>
      </TD>
     <TD align="center"><bean:write name="model" property="codeno"/></TD>
     <td align="center">
        <a onclick="doEdit('<bean:write property="codeid" name="model"/>')" style="cursor:pointer;" title="编辑">
        <img alt="编辑" border="0" src="/public/images/main/edit.gif"/>
        </a>
     </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="100%" border=0  align="center">
  <TR>
    <TD align=center>
    <java2page:pager url="sysCodeDataAction.do?method=hasList" name="pagelist"/>
    <input type="hidden" name="startcount" value="<bean:write name="startcount"/>" />
    <input type="hidden" name="codeid" value="<bean:write name="codeData" property="codeid"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
