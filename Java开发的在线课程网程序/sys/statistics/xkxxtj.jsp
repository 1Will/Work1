<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2"><bean:write name="SC_szxysubjectname"/>信息统计</TD>
 </TR>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="450" align="center">
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >总资源数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="count1"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >未审核资源数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="count2"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >上月上载资源数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="count3"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >本月上载资源数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="count4"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >今日上载资源数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="count5"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >总点击量：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="totalonclick"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >今日点击量：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="todayonclick"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >总下载量：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="totaldownload"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >今日下载量：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="todaydownload"/></TD>
</tr>
</TABLE>
</FORM>
</body>
</html>
