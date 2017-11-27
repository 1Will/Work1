<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="sysMenuInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}

function doDelete(objid){
  if(confirm("确定删除当前图片?")){
    document.pageForm.action="sysMenuInfoAction.do?method=delRecord&menuid="+objid;
    document.pageForm.submit();
  }
}
</SCRIPT>
<title>菜单管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body style="overflow-x:hidden;">
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">菜单管理</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <logic:notEqual value="0000" name="typeno">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      </logic:notEqual>
      <INPUT class="btn_del"  onclick="delRecord('sysMenuInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="table_search_title" width="97%" align="center" cellSpacing=1 cellPadding=1  >
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>
<TABLE cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr bgcolor="#ffffff">
	<logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
	<td width="20%" align="center">
	<table width="100%" align="center">
		<tr>
		<td align="center"><img src="<bean:write property="sketch" name="model"/>" width="82" height="82"/></td>
		</tr>
		<tr>
		<td align="center">
		<input type="checkbox" name="checkid" value="<bean:write property="menuid" name="model"/>" >
		<a href="#" onclick="doDelete('<bean:write property="menuid" name="model"/>')">删除</a>
		</td>
		</tr>
	</table>
	</td>
	
	<%if((index+1)%5 == 0){ %>
	</tr>
	<tr bgcolor="#ffffff">
	<%} %>
	</logic:iterate>
	
</tr>
</TABLE>


<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysMenuInfoAction.do?method=list" name="pagelist" />
      <input type="hidden" name="typeno" id="typeno" value="<bean:write name="typeno"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>

