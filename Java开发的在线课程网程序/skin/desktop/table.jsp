<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysCourseSetting"%>
<%@page import="com.util.date.DateTime"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysCourseTable"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/lanrentuku.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function changeCourseTable(week,noon,orderindex){
	var url = '/sysCourseTableAction.do?method=updateMyCourseTableMain&week=' + week + '&noon=' + noon + '&orderindex=' + orderindex;
	var retValue=showModalDialog(url,'课程表设置',"dialogWidth:420px;dialogHeight:270px;scroll=auto;border=thin;help=0;status=no");
	if(retValue != null && retValue[0] == 'ok'){
		document.reloadForm.action = '/pcenter.do?method=table';
		document.reloadForm.submit();
	}
}
</script>
</head>
<body style="background:none;">
<div id="kcb">
<h3><img src="/skin/desktop/images/z10.jpg" /></h3>
<ul>
<table width="250" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
  <%
	List tableList = (List)request.getAttribute("list");
	SysCourseSetting model = (SysCourseSetting)request.getAttribute("setting");
	int twidth = model.getWidth0(250);//表格宽度
	
	int days = model.getDays();
	int temp = 0;
  %>
  <tr>
    <td height="40" align="center" bgcolor="#E1E1E1"><strong>星期</strong></td>
    <%
    String week = null;
    String[] names = null;
    for(int i=1; i<=7; i++){
    	week = model.getWeek(i);
    	if("0".equals(week) || week == null) continue; 
    %>
    <td width="<%=twidth %>" align="center" bgcolor="#E1E1E1"><strong><%=Constants.getCodeTypevalue("week0", i+"") %></strong></td>
    <%} %>
  </tr>
  
  <!-- 早上 -->
  <%
    int lesson0 = model.getLesson0().intValue();
    for(int i=1; i<=lesson0; i++){
    	temp++;
  %>
  <tr>
  	<%if(i == 1){ %>
    <td rowspan="<%=lesson0 %>" align="center" bgcolor="#FFFFFF">早上</td>
    <%} %>
    <%
      	for(int j=1; j<=days; j++){
      	names = SysCourseTable.getCourseName(tableList, j, 0, temp);
    %>
    <td height="28" align="center" bgcolor="#FFFFFF" onclick="changeCourseTable('<%=j %>','0','<%=temp %>')" <%if(!"".equals(names[0])){ %>title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"<%} %>><%=names[0] %></td>
    <%} %>
  </tr>
  <%} %>
  
  <!-- 上午 -->
  <%
    int lesson1 = model.getLesson1().intValue();
    for(int i=1; i<=lesson1; i++){
    	temp++;
  %>
  <tr>
  	<%if(i == 1){ %>
    <td rowspan="<%=lesson1 %>" align="center" bgcolor="#FFFFFF">上午</td>
    <%} %>
    <%
      	for(int j=1; j<=days; j++){
      	names = SysCourseTable.getCourseName(tableList, j, 1, temp);
    %>
    <td height="28" align="center" bgcolor="#FFFFFF" onclick="changeCourseTable('<%=j %>','1','<%=temp %>')" <%if(!"".equals(names[0])){ %>title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"<%} %>><%=names[0] %></td>
    <%} %>
  </tr>
  <%} %>
  
  <!-- 值班 -->
  <%
    int onduty = model.getOnduty().intValue();
    for(int i=1; i<=onduty; i++){
    	temp++;
  %>
  <tr>
  	<%if(i == 1){ %>
    <td rowspan="<%=onduty %>" align="center" bgcolor="#FFFFFF">中午</td>
    <%} %>
    <%
      	for(int j=1; j<=days; j++){
      	names = SysCourseTable.getCourseName(tableList, j, 1, temp);
    %>
    <td height="28" align="center" bgcolor="#FFFFFF" onclick="changeCourseTable('<%=j %>','1','<%=temp %>')" <%if(!"".equals(names[0])){ %>title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"<%} %>><%=names[0] %></td>
    <%} %>
  </tr>
  <%} %>
  
  <!-- 下午 -->
  <%
    int lesson2 = model.getLesson2().intValue();
    for(int i=1; i<=lesson2; i++){
    	temp++;
  %>
  <tr>
  	<%if(i == 1){ %>
    <td rowspan="<%=lesson2 %>" align="center" bgcolor="#FFFFFF">下午</td>
    <%} %>
    <%
      	for(int j=1; j<=days; j++){
      	names = SysCourseTable.getCourseName(tableList, j, 2, temp);
    %>
    <td height="28" align="center" bgcolor="#FFFFFF" onclick="changeCourseTable('<%=j %>','2','<%=temp %>')" <%if(!"".equals(names[0])){ %>title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"<%} %>><%=names[0] %></td>
    <%} %>
  </tr>
  <%} %>
  
  <!-- 晚上 -->
  <%
    int lesson3 = model.getLesson3().intValue();
    for(int i=1; i<=lesson3; i++){
    	temp++;
  %>
  <tr>
  	<%if(i == 1){ %>
    <td rowspan="<%=lesson3 %>" align="center" bgcolor="#FFFFFF">晚上</td>
    <%} %>
    <%
      	for(int j=1; j<=days; j++){
      	names = SysCourseTable.getCourseName(tableList, j, 3, temp);
    %>
    <td height="28" align="center" bgcolor="#FFFFFF" onclick="changeCourseTable('<%=j %>','3','<%=temp %>')" <%if(!"".equals(names[0])){ %>title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"<%} %>><%=names[0] %></td>
    <%} %>
  </tr>
  <%} %>
</table>
</ul>
</div><!--#kcb-->
<form name="reloadForm" method="post"></form>
</body>
</html>