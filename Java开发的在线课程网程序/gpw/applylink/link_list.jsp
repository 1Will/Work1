<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="gpwApplyLinkAction.do?method=list";
  document.pageForm.submit();
}
</SCRIPT>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body onload="javascript:reloadTree()">
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">申请链接</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>申请链接</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">申请链接名称:</td>
                    <td><input type="text" size="30" name="linkname" value='<bean:write name="linkname" />' /></td>
                    <td width="80" height="25" align="right">状态:</td>
                    <td>
                    <select name="status">
                    	<option value="">请选择...</option>
                    	<option value="0" <logic:equal value="0" name="status">selected="selected"</logic:equal>>未审核</option>
                    	<option value="1" <logic:equal value="1" name="status">selected="selected"</logic:equal>>审核通过</option>
                    </select>
                    </td>
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
      <INPUT class="btn_del"  onclick="delRecord('gpwApplyLinkAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">申请链接名称</TD>
    <TD class="table_title" width="100">联系人</TD>
    <TD class="table_title" width="90">联系电话</TD>
    <TD class="table_title" width="60">状态</TD>
    <TD class="table_title" width="50">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
        <TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="applyid" name="model"/>" <bean:write property="flags" name="model"/> /> </TD>
        <td align="left">&nbsp;<bean:write property="linkname" name="model" /></a></td>
         <td align="center"><bean:write property="linkman" name="model" /></td>
         <td align="center"><bean:write property="telephone" name="model" /></td>
         <td align="center">
         <logic:equal value="0" name="model" property="status"><font color="red">未审核</font></logic:equal>
         <logic:equal value="1" name="model" property="status">审核通过</logic:equal>
         </td>
         <td align="center">
			<java2page:button url="gpwApplyLinkAction.do" property="applyid" readonly="E"/>
         </td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="gpwApplyLinkAction.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
      <input type="hidden" name="linktype" value="<bean:write name="linktype"/>"/>
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
