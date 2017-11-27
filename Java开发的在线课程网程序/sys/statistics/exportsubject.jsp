<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.util.date.DateTime"%>
<%@page import="com.bzt.edu.bo.EduSubjectVersion"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<%
String sBrowser = request.getHeader("User-Agent");
String filename = (String)request.getAttribute("filename");
if(sBrowser.indexOf("MSIE 6.0")!=-1){
	sBrowser = "6.0";
}else if(sBrowser.indexOf("MSIE 5.0")!=-1){
	sBrowser = "5.0";
}else if(sBrowser.indexOf("MSIE 5.5")!=-1){
	sBrowser = "5.5";
}
if(sBrowser.equals("5.5")){
	response.setContentType("APPLICATION/force-download;name=Dealer Eveluation Result;charset=gb2312");
	response.setHeader("Content-Disposition","anything;filename=" + new String(filename.getBytes("gb2312"), "iso8859-1") + DateTime.getDate("yyyyMMdd") + ".xls");
}
else{
	response.setContentType("APPLICATION/msword");
	response.setHeader("Content-Disposition","attachment; filename=" + new String(filename.getBytes("gb2312"), "iso8859-1") + DateTime.getDate("yyyyMMdd") + ".xls");
}
%>

<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 12">
<link rel=File-List href="<bean:write name="SC_szxysubjectname"/>统计.files/filelist.xml">
<style id="<bean:write name="SC_szxysubjectname"/>统计_9658_Styles">
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font59658
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.xl639658
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;}
-->

.style01{border-bottom:#e0dfe3 1px solid; border-left:#168c9d 1px solid;}
.style02{border-top:#e0dfe3 1px solid; border-right:#168c9d 1px solid; background-color:#f7f7f7;}
.style03{border-top:#e0dfe3 1px solid; border-right:#168c9d 1px solid; background-color:#fff;}
.style04{border-top:#e0dfe3 1px solid; border-right:#e0dfe3 1px solid; background-color:#f7f7f7;}
.style05{border-top:#e0dfe3 1px solid; border-right:#e0dfe3 1px solid; background-color:#fff;}

</style>
</head>

<body>
<!--[if !excel]>　　<![endif]-->
<!--下列信息由 Microsoft Office Excel 的“发布为网页”向导生成。-->
<!--如果同一条目从 Excel 中重新发布，则所有位于 DIV 标记之间的信息均将被替换。-->
<!----------------------------->
<!--“从 EXCEL 发布网页”向导开始-->
<!----------------------------->

<div id="<bean:write name="SC_szxysubjectname"/>统计_9658" align=center x:publishsource="Excel">

<table border="0" width="900" align="center" cellpadding="0" cellspacing="0" class="style01">
  <tr height="35">
    <td colspan="9" style="width:100%;text-align:center;font-weight:bold;"><%=filename %></td>
  </tr>
</table>
<table border="0" width="900" cellpadding="0" cellspacing="0" class="style01">
  <tr height="30">
    <td align="center" class="style02" width="100"><bean:write name="SC_szxysubjectname"/>名称</td>
    <td align="center" class="style02" width="70">总资源数</td>
    <td align="center" class="style02" width="100">未审核资源数</td>
    <td align="center" class="style02" width="100">上月上载资源数</td>
    <td align="center" class="style02" width="100">本月上载资源数</td>
    <td align="center" class="style02" width="100">今日上载资源数</td>
    <td align="center" class="style02" width="60">总点击量</td>
    <td align="center" class="style02" width="60">总下载量</td>
    <td align="center" class="style02" width="210"><bean:write name="SC_szxysubjectname"/>管理员</td>
  </tr>
  <%
  	List allsubjectList = (List)request.getAttribute("allsubjectList");
  	EduSubjectVersion subject = null;
  	String subjectid = null;
  	for(int i=0; i<allsubjectList.size(); i++){
  		subject = (EduSubjectVersion)allsubjectList.get(i);
  		subjectid = subject.getId().toString();
  %>
  <tr height="38">
    <td align="center" class="style03" width="100"><%=subject.getName() %></td>
    <td align="center" class="style03" width="70"><%=request.getAttribute("count1_" + subjectid) %></td>
    <td align="center" class="style03" width="100"><%=request.getAttribute("count2_" + subjectid) %></td>
    <td align="center" class="style03" width="100"><%=request.getAttribute("count3_" + subjectid) %></td>
    <td align="center" class="style03" width="100"><%=request.getAttribute("count4_" + subjectid) %></td>
    <td align="center" class="style03" width="100"><%=request.getAttribute("count5_" + subjectid) %></td>
    <td align="center" class="style03" width="60"><%=request.getAttribute("totalonclick_" + subjectid) %></td>
    <td align="center" class="style03" width="60"><%=request.getAttribute("totaldownload_" + subjectid) %></td>
    <td align="center" class="style03" width="210"><%=request.getAttribute("userlist_" + subjectid) %></td>
  </tr>
  <%} %>
</table>

</div>


<!----------------------------->
<!--“从 EXCEL 发布网页”向导结束-->
<!----------------------------->
</body>

</html>
