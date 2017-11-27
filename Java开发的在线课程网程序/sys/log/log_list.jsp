<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />
function delAllRecord(){
   if(confirm("此操作会删除当前单位中所有日志，您确认要删除吗？")){
     document.pageForm.action="sysUserLogAction.do?method=delBatchAllRecord";
     document.pageForm.submit();
   }
}
function searchRecord(){
  document.pageForm.action="sysUserLogAction.do?method=list";
  document.pageForm.submit();
}
</SCRIPT>
<title>日志管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">日志管理</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>日志信息</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">用&nbsp;户&nbsp;名:</td>
                    <td><input type="text" value='<bean:write name="username"/>' size="15" name="username"/></td>
                    <td width="80" align="right">时&nbsp;&nbsp;间:</td>
                    <td colspan="3"><input  type="text" size="15" name="createdate" value='<bean:write name="createdate"/>'/></td>
                    <td width="80" align="right">日志内容:</td>
                    <td><input  type="text"  size="30" name="descript" value='<bean:write name="descript"/>'/></td>
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
<%--   
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
       <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
       <INPUT class="btn_del"  onclick="delRecord('sysUserLogAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
       <INPUT class="btn_alldel"  onclick="delAllRecord()" type="button" value="全删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
 --%>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD width="100" class="table_title">用户名</TD>
    <TD width="110" class="table_title">登录IP</TD>
    <TD width="130" class="table_title">时间</TD>
    <TD class="table_title">日志内容</TD>
</tr>
<!--循环列出所有数据-->
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
  <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center"> <input type="checkbox" name="checkid" value="<bean:write property="logid" name="model"/>"></TD>
     <td align="center" ><bean:write property="sysUserInfo.username" name="model"/></td>
     <td align="center"><bean:write property="userip" name="model"/></td>
     <td align="center"><bean:write property="createdate" name="model"/></td>
     <td align="left">&nbsp;<bean:write property="descript" name="model"/></td>
  </TR>
  </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUserLogAction.do?method=list" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
