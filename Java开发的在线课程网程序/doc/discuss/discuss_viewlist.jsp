<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>评论管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<script type="text/javascript" src="/public/js/comm.js"></script>
<script type="text/javascript" src="/public/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var num=<bean:write name="pagelist" property="pageCount" />

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/docFileDiscussAction.do?method=viewlist";
  document.pageForm.submit();
}
</script>
</head>
<body leftMargin=0 topMargin=0>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">评论管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>评论</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                  	<td width="80" height="25" align="right">评论类型:</td>
                    <td><java2code:option name="type" codetype="commenttype" valuename="type" ckname="评论类型"></java2code:option></td>
                    <td width="80" height="25" align="right">评论时间:</td>
                    <td><input type="text" size="12" name="createdate" value="<bean:write name="createdate"/>" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
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
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="80">评论者</TD> 
	<TD class="table_title" width="70">评论类型</TD>
    <TD class="table_title" width="130">评论时间</TD>
    <TD class="table_title">评论内容</TD>
   
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <tr onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
      <TD align="center"><bean:write name="model" property="username"/></TD>
	   <td align="center"><java2code:value codetype="commenttype" property="type"></java2code:value></td>
     <td align="center"><bean:write name="model" property="createdate"/></td> 
     <td align="center" style="white-space:nowrap;overflow:hidden;" title="<bean:write name="model" property="htmlcontent" filter="false"/>"><bean:write name="model" property="htmlcontent" filter="false"/></td>
     </tr>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="docFileDiscussAction.do?method=viewlist" name="pagelist" />
	  <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
	  <input type="hidden" name="filmid" value="<bean:write name="filmid"/>" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
