<%@page import="com.bzt.sys.bo.SysUserPoint"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<SCRIPT language=javascript src="/public/js/comm.js"></SCRIPT>
<Script language="JavaScript"  src="/public/DatePicker/WdatePicker.js"></Script>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />
function searchRecord(){
  document.pageForm.action="sysUserPointAction.do?method=myPoint";
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
    <TD class="page_title" colspan="2">我的积分明细</TD>
 </TR>
 <tr height="30">
   <td>
     <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
              <td >查询>>积分明细</td>
            </tr>
       </table>
       <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" align="right">性质:</td>
                    <td>
                    <select name="pointtype" onchange="searchRecord()">
                    	<option value="">请选择</option>
                    	<option value="1" <logic:equal value="1" name="pointtype">selected="selected"</logic:equal>>奖励</option>
                    	<option value="-1" <logic:equal value="-1" name="pointtype">selected="selected"</logic:equal>>扣除</option>
                    </select>
                    </td>
                    <td width="80" align="right">日&nbsp;&nbsp;期:</td>
                    <td colspan="3"><input type="text" size="15" name="createdate" value='<bean:write name="createdate"/>' readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
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
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" >序号</TD>
    <TD width="40" class="table_title">性质</TD>
    <TD width="40" class="table_title">积分</TD>
    <TD width="130" class="table_title">时间</TD>
    <TD class="table_title">积分明细内容</TD>
</tr>
<!--循环列出所有数据-->
  <%
  Integer startcount = (Integer)request.getAttribute("startcount");
  PageList pagelist = (PageList)request.getAttribute("pagelist");
  List list = pagelist.getDatalist();
  SysUserPoint point = null;
  for(int i=0, size=list.size(); i<size; i++){
	  point = (SysUserPoint)list.get(i);
  %>
  <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     <TD align="center"><%=startcount+i+1 %></TD>
     <td align="center">
     <%if("1".equals(point.getPointtype())){ %>
     <font color="green">奖励</font>
     <%}else{ %>
     <font color="red">扣除</font>
     <%} %>
     </td>
     <td align="center"><%=point.getPoint() %></td>
     <td align="center"><%=point.getCreatedate() %></td>
     <td align="left"><%=point.getDescript() %></td>
  </TR>
  <%} %>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="sysUserPointAction.do?method=myPoint" name="pagelist" />
    </TD>
  </TR>
</TABLE>
</FORM>
</body>
</html>
