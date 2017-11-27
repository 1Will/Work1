<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.bzt.edu.bo.EduSubjectVersion"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<title>产品管理</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
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
    <TD class="page_title" colspan="2"><bean:write name="SC_szxysubjectname"/>统计</TD>
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
    <TD class="table_title" width="70" ><bean:write name="SC_szxysubjectname"/>名称</TD>
    <TD class="table_title" width="60">总资源数</TD>
    <TD class="table_title" width="85">未审核资源数</TD>
    <TD class="table_title" width="100">上月上载资源数</TD>
    <TD class="table_title" width="100">本月上载资源数</TD>
    <TD class="table_title" width="100">今日上载资源数</TD>
    <TD class="table_title" width="60">总点击量</TD>
    <TD class="table_title" width="60">总下载量</TD>
    <TD class="table_title"><bean:write name="SC_szxysubjectname"/>管理员</TD>
</tr>
<!--循环列出所有数据-->
  <%
  	List allsubjectList = (List)request.getAttribute("allsubjectList");
  	EduSubjectVersion subject = null;
  	String subjectid = null;
  	for(int i=0; i<allsubjectList.size(); i++){
  		subject = (EduSubjectVersion)allsubjectList.get(i);
  		subjectid = subject.getId().toString();
  %>
     <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
     	<TD align="center"><%=subject.getName() %></TD>
        <TD align="center"><%=request.getAttribute("count1_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("count2_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("count3_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("count4_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("count5_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("totalonclick_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("totaldownload_" + subjectid) %></td>
        <td align="center"><%=request.getAttribute("userlist_" + subjectid) %></td>
     </TR>
  <%} %>
</TABLE>
</FORM>
</body>
</html>
