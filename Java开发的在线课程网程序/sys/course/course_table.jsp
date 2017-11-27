<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysCourseSetting"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysCourseTable"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/public/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function changeCourseTable(week,noon,orderindex){
	var url = '/sysCourseTableAction.do?method=updateMyCourseTableMain&week=' + week + '&noon=' + noon + '&orderindex=' + orderindex;
	var retValue=showModalDialog(url,'课程表设置',"dialogWidth:240px;dialogHeight:270px;scroll=no;border=thin;help=0;status=no");
	if(retValue != null && retValue[0] == 'ok'){
		document.reloadForm.action = '/sysCourseTableAction.do?method=myCourseTable';
		document.reloadForm.submit();
	}
}
</script>
</head>

<body>
  <table width="260" height="150" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
  	<%
  		List tableList = (List)request.getAttribute("list");
  		SysCourseSetting model = (SysCourseSetting)request.getAttribute("setting");
  		int twidth = model.getWidth0(260);//表格宽度
  		int theight = model.getHeight0(150);//表格高度
  		
  		int days = model.getDays();
		int temp = 0;
  	%>
    <tr>
      <!--td width="30" height="35" align="center" bgcolor="#999999">&nbsp;</td-->
      <td align="center" bgcolor="#999999">&nbsp;</td>
    <%
    String week = null;
    String[] names = null;
    for(int i=1; i<=7; i++){
    	week = model.getWeek(i);
    	if("0".equals(week) || week == null) continue; 
    %>
      <td width="<%=twidth %>" align="center" bgcolor="#999999"><span class="style01"><%=Constants.getCodeTypevalue("week", i+"") %></span></td>
    <%} %>
    </tr>
    
    <!-- 早上 -->
    <%
    String tableid = "";
    int lesson0 = model.getLesson0().intValue();
    for(int i=1; i<=lesson0; i++){
    	temp++;
    %>
    <tr>
      <td style="line-height:<%=theight %>px;" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      	names = SysCourseTable.getCourseName(tableList, j, 0, temp);
      %>
      <td style="line-height:<%=theight %>px;text-align:center;font-size:10px;" id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeCourseTable('<%=j %>','0','<%=temp %>')" title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"><%=names[0] %></td>
      <%} %>
    </tr>
    <%} %>
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:2px;">&nbsp;</td>
    </tr>
    
    <!-- 上午 -->
    <%
    int lesson1 = model.getLesson1().intValue();
    for(int i=1; i<=lesson1; i++){
    	temp++;
    %>
    <tr>
      <td style="line-height:<%=theight %>px;" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      	names = SysCourseTable.getCourseName(tableList, j, 1, temp);
      %>
      <td style="line-height:<%=theight %>px;text-align:center;font-size:10px;" id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeCourseTable('<%=j %>','1','<%=temp %>')" title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"><%=names[0] %></td>
      <%} %>
    </tr>
    <%} %>
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:2px;">&nbsp;</td>
    </tr>
    
    <!-- 下午 -->
    <%
    int lesson2 = model.getLesson2().intValue();
    for(int i=1; i<=lesson2; i++){
    	temp++;
    %>
    <tr>
      <td style="line-height:<%=theight %>px;" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      	names = SysCourseTable.getCourseName(tableList, j, 2, temp);
      %>
      <td style="line-height:<%=theight %>px;text-align:center;font-size:10px;" id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeCourseTable('<%=j %>','2','<%=temp %>')" title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"><%=names[0] %></td>
      <%} %>
    </tr>
    <%} %>
    
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:2px;">&nbsp;</td>
    </tr>
    
    <!-- 晚上 -->
    <%
    int lesson3 = model.getLesson3().intValue();
    for(int i=1; i<=lesson3; i++){
    	temp++;
    %>
    <tr>
      <td style="line-height:<%=theight %>px;" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      	names = SysCourseTable.getCourseName(tableList, j, 3, temp);
      %>
      <td style="line-height:<%=theight %>px;text-align:center;font-size:10px;" id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeCourseTable('<%=j %>','3','<%=temp %>')" title="【班级:<%=names[1] %>】【教室:<%=names[2] %>】"><%=names[0] %></td>
      <%} %>
    </tr>
    <%} %>
  </table>
  <form name="reloadForm" method="post"></form>
</body>
</html:html>
