<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<script type="text/javascript">
function grzj(){
	document.pageForm.action = '/<bean:write name="userInfo" property="userid"/>/ucenter.htm';
	document.pageForm.submit();
}
</script>
</head>
<body>
<FORM name="pageForm" method=post target="_blank">
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">[<bean:write name="userInfo" property="username"/>]上载资源统计</TD>
 </TR>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="450" align="center">
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >登录名：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="userInfo" property="loginname"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >联系电话：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="userInfo" property="mobile"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >用户头衔：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="userTitle" property="name"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >用户积分：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="userInfo" property="flags"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >用户财富：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="userInfo" property="flago"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >总上载资源数：</TD>
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
    <TD align="right" width="200" >资源被下载总数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="totaldownload"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >今日资源被下载数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="todaydownload"/></TD>
</tr>
<TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
    <TD align="right" width="200" >资源总点击数：</TD>
    <TD align="left" width="250" style="font-weight:bold;color:#0055ee;">&nbsp;<bean:write name="totalonclick"/></TD>
</tr>
</TABLE>
<table width="98%" align="center">
<TR bgcolor="#ffffff" height="50">
    <TD align="center">
    <INPUT name="gotoback" type="button" class="btn_cancel" title="返回上一页" onclick="javascript:history.go(-1)" value="返&nbsp;回">
    <INPUT name="gotoback" type="button" class="btn_blank" title="查看个人专辑" onclick="javascript:grzj()" value="个人专辑">
    </TD>
</tr>
</table>
</FORM>
</body>
</html>
