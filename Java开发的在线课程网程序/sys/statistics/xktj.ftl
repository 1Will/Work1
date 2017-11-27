<html>
<head>
<title>${SC_szxysubjectname}统计</title>
<link href="/public/css/default.css" rel="stylesheet" type="text/css">
<link href="/public/css/coolbutton.css" rel="stylesheet" type="text/css">
<link href="/public/css/button.css" rel="stylesheet" type="text/css">
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
function exportSubject(){
	document.pageForm.action = '/sysStatisticsAction.do?method=exportSubject';
	document.pageForm.submit();
}
function getHtml(){
	document.pageForm.action = '/sysStatisticsAction.do?method=xktj&reload=1';
	document.pageForm.submit();
}
</SCRIPT>
</head>
<body>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">${SC_szxysubjectname}统计</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_download" onclick="exportSubject()" type="button" value="导出数据" name="selectall">
      <INPUT class="btn_html"  onclick="getHtml()" type="button" value="重新统计" name="selectnone">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="70" >${SC_szxysubjectname}名称</TD>
    <TD class="table_title" width="60">总资源数</TD>
    <TD class="table_title" width="85">未审核资源数</TD>
    <TD class="table_title" width="100">上月上载资源数</TD>
    <TD class="table_title" width="100">本月上载资源数</TD>
    <TD class="table_title" width="100">今日上载资源数</TD>
    <TD class="table_title" width="60">总点击量</TD>
    <TD class="table_title" width="60">总下载量</TD>
    <TD class="table_title">${SC_szxysubjectname}管理员</TD>
</tr>
${subjectlist }
</TABLE>
</FORM>
</body>
</html>
