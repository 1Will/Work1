<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language="javascript" src="/public/DatePicker/WdatePicker.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />

function addRecord(){
  document.pageForm.action="vodBulletinInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="vodBulletinInfoAction.do?method=list";
  document.pageForm.submit();
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
    <TD class="page_title" colspan="2">微课公告</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>微课公告</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">公告标题:</td>
                    <td><input type="text" value='<bean:write property="title" name="model" />'  size="20" name="title"/></td>
                     <td width="80" align="right">开始日期:</td>
                     <td><input  type="text"  size="10" name="startdate" value='<bean:write property="startdate" name="model"/>'  readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
                     <td width="80" align="right">结束日期:</td>
                     <td><input  type="text" size="10" name="enddate" value='<bean:write property="enddate" name="model"/>'  readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
                     <td width="80" align="right">是否显示:</td>
                     <td><java2code:option  name="status" codetype="view" property="status" ckname="是否显示"/></td>
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
      <INPUT class="btn_del"  onclick="delRecord('vodBulletinInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title">标题</TD>
    <TD class="table_title" width="80">开始日期</TD>
    <TD class="table_title" width="80">结束日期</TD>
    <TD class="table_title" width="60">状态</TD>
    <TD class="table_title" width="50">排序</TD>
    <TD class="table_title" width="40">操作</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     	<TD align="center"><input type="checkbox" name="checkid" value="<bean:write property="bulletinid" name="model"/>"></TD>
        <TD align="left">&nbsp;<bean:write property="title" name="model" /></td>
        <td align="center"><bean:write property="startdate" name="model"/></td>
        <td align="center"><bean:write property="enddate" name="model"/></td>
        <td align="center"><java2code:value codetype="view" property="status"/></td>
        <td align="center"><bean:write property="orderindex" name="model"/></td>
        <td align="center">
           <java2page:button url="vodBulletinInfoAction.do" property="bulletinid" readonly="E"/>
        </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="vodBulletinInfoAction.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
