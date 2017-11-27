<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="sysProductInfoAction.do?method=list1";
  document.pageForm.submit();
}

function moduleManager(productid){
  var path = "sysModuleInfoAction.do?method=main&productid="+productid;
  var result =window.showModalDialog(path,"模块管理","dialogWidth:800px;dialogheight:600px;toolbar:no;status:no;toolbar:no;")
}

function roleManager(productid){
  var path = "sysRoleInfoAction1.do?method=list&productid="+productid;
  var result =window.showModalDialog(path,"角色管理","dialogWidth:800px;dialogheight:600px;toolbar:no;status:no;toolbar:no;")
}
</SCRIPT>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">产品模块</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title" width="150">产品编号</TD>
    <TD class="table_title">产品名称</TD>
    <TD class="table_title" width="80">产品类型</TD>
    <TD class="table_title" width="80">产品状态</TD>
    <TD class="table_title" width="40">排序</TD>
    <TD class="table_title" width="70">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="productid" name="model"/>">
     </TD>
     <TD align="center"><bean:write name="model" property="productno"/></TD>
     <TD align="left">&nbsp;<bean:write name="model" property="productname"/></TD>
     <TD align="center">
     	<logic:equal value="1" name="model" property="type">市产品</logic:equal>
     	<logic:equal value="2" name="model" property="type">区产品</logic:equal>
     	<logic:equal value="3" name="model" property="type">校产品</logic:equal>
     </TD>
     <TD align="center"><java2code:value codetype="pstate" property="state"/></TD>
     <TD align="center"><bean:write name="model" property="orderindex"/></TD>
      <td align="center">
        <%-- <java2page:button url="sysProductInfoAction.do" property="productid" readonly="E"/>--%>
        <img title="模块管理" alt="模块管理" style="cursor:pointer;" onclick="moduleManager('<bean:write property="productid" name="model"/>')" border="0"  src="/public/images/main/module.gif"/>
        <img title="角色管理" alt="角色管理" style="cursor:pointer;" onclick="roleManager('<bean:write property="productid" name="model"/>')" border="0"  src="/public/images/main/power.gif"/>
      </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysProductInfoAction.do?method=list1" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
