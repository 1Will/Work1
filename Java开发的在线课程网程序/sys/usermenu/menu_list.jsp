<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function selectMenu(){
    var url = '/sysUserMenuAction.do?method=selectMain';
	var retValue=showModalDialog(url,'快捷菜单设置',"dialogWidth:400px;dialogHeight:550px;scroll=auto;border=thin;help=0;status=no");
	if(retValue != null && retValue[0] == 'ok'){
		document.pageForm.action = '/sysUserMenuAction.do?method=list';
		document.pageForm.submit();
	}
}
</SCRIPT>
<title>模块管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">快捷菜单管理</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_del"  onclick="delRecord('sysUserMenuAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
      <INPUT class="btn_add"  onclick="selectMenu()" type="button" value="选择" name="btnadd">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">菜单名称</TD>
     <TD class="table_title" width="100">菜单图片</TD>
     <TD class="table_title" width="70">排序</TD>
    <TD class="table_title" width="60">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="menuid" name="model"/>">
     </TD>
     <TD align="left">&nbsp;<bean:write name="model" property="sysModuleInfo.modulename"/></TD>
     <TD align="center"><img src="<bean:write name="model" property="sketch"/>" width="60" height="26"></TD>
      <TD align="center"><bean:write name="model" property="orderindex"/></TD>
      <td align="center">
        <java2page:button url="sysUserMenuAction.do" property="menuid" readonly="E"/>
      </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0  align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUserMenuAction.do?method=list" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
