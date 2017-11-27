<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysCourseSetting"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>排课系统设置</title>
<link href="/public/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function changeWeek(weekid){
	if(weekid != '0'){
		var week = document.getElementById(weekid);
		var days = document.getElementById('sysCourseSetting.days').value;
		if(days == 1 && !week.checked){
			document.getElementById(weekid).checked = true;
			alert("每周上课天数不能少于1天。");
			return false;
		}
		if(week.checked){
			document.getElementById(weekid).value = '1';
			document.getElementById('sysCourseSetting.days').value = parseInt(days) + 1;
		}else{
			document.getElementById(weekid).value = '0';
			document.getElementById('sysCourseSetting.days').value = parseInt(days) - 1;
		}
	}
	if(weekid == '0'){
		var lesson1 = document.getElementById('sysCourseSetting.lesson1').value;
		if(lesson1 == '0'){
		    document.getElementById('sysCourseSetting.lesson1').value = '1';
			alert("每天上午至少要有一节课!");
			return false;
		}
	}
	obj = document.sysCourseSettingActionForm;
	obj.action="/sysCourseSettingAction.do?method=change";
    obj.submit();
}

function changeColor(id, colorvalue){
	//var value = document.getElementById('table'+id).value;
	//if(value == '0'){
	//	document.getElementById('table'+id).value = '1';
	//	document.getElementById('td'+id).style.background = '#ff0000';
	//}
	//if(value == '1'){
	//	document.getElementById('table'+id).value = '0';
	//	document.getElementById('td'+id).style.background = colorvalue;
	//}
}

function saveRecord(){
	obj = document.sysCourseSettingActionForm;
	obj.action="/sysCourseSettingAction.do?method=setSave";
    obj.submit();
}
</script>
</head>

<body>
<div id="center">
<html:form action="/sysCourseSettingAction.do" method="post" >
<div id="left">
<div id="left01">
	<h3>每周上课天数</h3>
	<ul>
		<li>
		<dt> <input type="checkbox" name="sysCourseSetting.monday" id="sysCourseSetting.monday" onclick="changeWeek('sysCourseSetting.monday')" value="<bean:write name="model" property="monday"/>" <logic:equal value="1" name="model" property="monday">checked="checked"</logic:equal>/></dt>
		<dd>星期一</dd>
		 </li>
		 <li>
		<dt> <input type="checkbox" name="sysCourseSetting.tuesday" id="sysCourseSetting.tuesday" onclick="changeWeek('sysCourseSetting.tuesday')" value="<bean:write name="model" property="tuesday"/>" <logic:equal value="1" name="model" property="tuesday">checked="checked"</logic:equal>/></dt>
		<dd>星期二</dd>
		 </li>
		 <li>
		<dt> <input type="checkbox" name="sysCourseSetting.wednesday" id="sysCourseSetting.wednesday" onclick="changeWeek('sysCourseSetting.wednesday')" value="<bean:write name="model" property="wednesday"/>" <logic:equal value="1" name="model" property="wednesday">checked="checked"</logic:equal>/></dt>
		<dd>星期三</dd>
		 </li>
		 <li>
		<dt> <input type="checkbox" name="sysCourseSetting.thursday" id="sysCourseSetting.thursday" onclick="changeWeek('sysCourseSetting.thursday')" value="<bean:write name="model" property="thursday"/>" <logic:equal value="1" name="model" property="thursday">checked="checked"</logic:equal>/></dt>
		<dd>星期四</dd>
		 </li>
		 <li>
		<dt> <input type="checkbox" name="sysCourseSetting.friday" id="sysCourseSetting.friday" onclick="changeWeek('sysCourseSetting.friday')" value="<bean:write name="model" property="friday"/>" <logic:equal value="1" name="model" property="friday">checked="checked"</logic:equal>/></dt>
		<dd>星期五</dd>
		 </li>
		 <li>
		<dt> <input type="checkbox" name="sysCourseSetting.saturday" id="sysCourseSetting.saturday" onclick="changeWeek('sysCourseSetting.saturday')" value="<bean:write name="model" property="saturday"/>" <logic:equal value="1" name="model" property="saturday">checked="checked"</logic:equal>/></dt>
		<dd>星期六</dd>
		 </li>
		 <li>
		<dt> <input type="checkbox" name="sysCourseSetting.sunday" id="sysCourseSetting.sunday" onclick="changeWeek('sysCourseSetting.sunday')" value="<bean:write name="model" property="sunday"/>" <logic:equal value="1" name="model" property="sunday">checked="checked"</logic:equal>/></dt>
		<dd>星期日</dd>
		 </li>
	</ul>
</div>

<div id="left02">
	<h3>每天上课节次</h3>
	<ul>
		<li>
		<dt>早上</dt>
		<dd>
		  <select style="width:100px;" name="sysCourseSetting.lesson0" onchange="changeWeek('0')">
		    <option value="0">不上课</option>
		    <option value="1" <logic:equal value="1" name="model" property="lesson0">selected="selected"</logic:equal>>1节</option>
		    <option value="2" <logic:equal value="2" name="model" property="lesson0">selected="selected"</logic:equal>>2节</option>
		    <option value="3" <logic:equal value="3" name="model" property="lesson0">selected="selected"</logic:equal>>3节</option>
		    <option value="4" <logic:equal value="4" name="model" property="lesson0">selected="selected"</logic:equal>>4节</option>
		  </select>
		</dd>
		</li>
		<li>
		<dt>上午</dt>
		<dd>
		  <select style="width:100px;" name="sysCourseSetting.lesson1" id="sysCourseSetting.lesson1" onchange="changeWeek('0')">
		    <option value="0">不上课</option>
		    <option value="1" <logic:equal value="1" name="model" property="lesson1">selected="selected"</logic:equal>>1节</option>
		    <option value="2" <logic:equal value="2" name="model" property="lesson1">selected="selected"</logic:equal>>2节</option>
		    <option value="3" <logic:equal value="3" name="model" property="lesson1">selected="selected"</logic:equal>>3节</option>
		    <option value="4" <logic:equal value="4" name="model" property="lesson1">selected="selected"</logic:equal>>4节</option>
		    <option value="5" <logic:equal value="5" name="model" property="lesson1">selected="selected"</logic:equal>>5节</option>
		    <option value="6" <logic:equal value="6" name="model" property="lesson1">selected="selected"</logic:equal>>6节</option>
		  </select>
		</dd>
		</li>
		<li>
		<dt>中午</dt>
		<dd>
		  <select style="width:100px;" name="sysCourseSetting.onduty" id="sysCourseSetting.onduty" onchange="changeWeek('0')">
		    <option value="0">不上课</option>
		    <option value="1" <logic:equal value="1" name="model" property="onduty">selected="selected"</logic:equal>>1节</option>
		    <option value="2" <logic:equal value="2" name="model" property="onduty">selected="selected"</logic:equal>>2节</option>
		    <option value="3" <logic:equal value="3" name="model" property="onduty">selected="selected"</logic:equal>>3节</option>
		    <option value="4" <logic:equal value="4" name="model" property="onduty">selected="selected"</logic:equal>>4节</option>
		  </select>
		</dd>
		</li>
		<li>
		<dt>下午</dt>
		<dd>
		  <select style="width:100px;" name="sysCourseSetting.lesson2" onchange="changeWeek('0')">
		    <option value="0">不上课</option>
		    <option value="1" <logic:equal value="1" name="model" property="lesson2">selected="selected"</logic:equal>>1节</option>
		    <option value="2" <logic:equal value="2" name="model" property="lesson2">selected="selected"</logic:equal>>2节</option>
		    <option value="3" <logic:equal value="3" name="model" property="lesson2">selected="selected"</logic:equal>>3节</option>
		    <option value="4" <logic:equal value="4" name="model" property="lesson2">selected="selected"</logic:equal>>4节</option>
		    <option value="5" <logic:equal value="5" name="model" property="lesson2">selected="selected"</logic:equal>>5节</option>
		    <option value="6" <logic:equal value="6" name="model" property="lesson2">selected="selected"</logic:equal>>6节</option>
		  </select>
		</dd>
		</li>
		<li>
		<dt>晚上</dt>
		<dd>
		  <select style="width:100px;" name="sysCourseSetting.lesson3" onchange="changeWeek('0')">
		    <option value="0">不上课</option>
		    <option value="1" <logic:equal value="1" name="model" property="lesson3">selected="selected"</logic:equal>>1节</option>
		    <option value="2" <logic:equal value="2" name="model" property="lesson3">selected="selected"</logic:equal>>2节</option>
		    <option value="3" <logic:equal value="3" name="model" property="lesson3">selected="selected"</logic:equal>>3节</option>
		    <option value="4" <logic:equal value="4" name="model" property="lesson3">selected="selected"</logic:equal>>4节</option>
		  </select>
		</dd>
		</li>
	</ul>
</div>
<input name="Submit" type="button" class="button" value="保存" onclick="saveRecord()" style="width:100px; text-align:center; margin:20px 0 10px 0px;"/>
</div>

<div id="right">
  <table width="575" height="435" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
  	<%
  		SysCourseSetting model = (SysCourseSetting)request.getAttribute("model");
  		int twidth = model.getWidth(575);//表格宽度
  		int theight = model.getHeight(435);//表格高度
  		
  		int days = model.getDays();
		int temp = 0;
  	%>
    <tr>
      <!--td width="30" height="35" align="center" bgcolor="#999999">&nbsp;</td-->
      <td align="center" bgcolor="#999999">&nbsp;</td>
    <%
    String week = null;
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
      <td height="<%=theight %>" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      %>
      <td id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeColor('<%=tableid %>','<%if(temp%2 != 0){ %>#f8f8f8<%} %><%if(temp%2 == 0){ %>#EBEBEB<%} %>')"></td>
      <input type="hidden" name="table<%=tableid %>" id="table<%=tableid %>" value="0">
      <%} %>
    </tr>
    <%} %>
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:5px;">&nbsp;</td>
    </tr>
    
    <!-- 上午 -->
    <%
    int lesson1 = model.getLesson1().intValue();
    for(int i=1; i<=lesson1; i++){
    	temp++;
    %>
    <tr>
      <td height="<%=theight %>" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      %>
      <td id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeColor('<%=tableid %>','<%if(temp%2 != 0){ %>#f8f8f8<%} %><%if(temp%2 == 0){ %>#EBEBEB<%} %>')"></td>
      <input type="hidden" name="table<%=tableid %>" id="table<%=tableid %>" value="0">
      <%} %>
    </tr>
    <%} %>
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:5px;">&nbsp;</td>
    </tr>
    
    <!-- 值班 -->
    <%
    int onduty = model.getOnduty().intValue();
    for(int i=1; i<=onduty; i++){
    	temp++;
    %>
    <tr>
      <td height="<%=theight %>" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      %>
      <td id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeColor('<%=tableid %>','<%if(temp%2 != 0){ %>#f8f8f8<%} %><%if(temp%2 == 0){ %>#EBEBEB<%} %>')"></td>
      <input type="hidden" name="table<%=tableid %>" id="table<%=tableid %>" value="0">
      <%} %>
    </tr>
    <%} %>
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:5px;">&nbsp;</td>
    </tr>
    
    <!-- 下午 -->
    <%
    int lesson2 = model.getLesson2().intValue();
    for(int i=1; i<=lesson2; i++){
    	temp++;
    %>
    <tr>
      <td height="<%=theight %>" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      %>
      <td id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeColor('<%=tableid %>','<%if(temp%2 != 0){ %>#f8f8f8<%} %><%if(temp%2 == 0){ %>#EBEBEB<%} %>')"></td>
      <input type="hidden" name="table<%=tableid %>" id="table<%=tableid %>" value="0">
      <%} %>
    </tr>
    <%} %>
    
    <tr>
      <td colspan="<%=days+1 %>" align="center" valign="middle" bgcolor="#CCDDEE" style="line-height:5px;">&nbsp;</td>
    </tr>
    
    <!-- 晚上 -->
    <%
    int lesson3 = model.getLesson3().intValue();
    for(int i=1; i<=lesson3; i++){
    	temp++;
    %>
    <tr>
      <td height="<%=theight %>" align="center" valign="middle" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %>><%=temp %></td>
      <%
      	for(int j=1; j<=days; j++){
      	tableid = "" + j + temp;
      %>
      <td id="td<%=tableid %>" <%if(temp%2 != 0){ %>bgcolor="#f8f8f8"<%} %><%if(temp%2 == 0){ %>bgcolor="#EBEBEB"<%} %> onclick="changeColor('<%=tableid %>','<%if(temp%2 != 0){ %>#f8f8f8<%} %><%if(temp%2 == 0){ %>#EBEBEB<%} %>')"></td>
      <input type="hidden" name="table<%=tableid %>" id="table<%=tableid %>" value="0">
      <%} %>
    </tr>
    <%} %>
    
  </table>
</div>

<input type="hidden" name="sysCourseSetting.settingid" value="<bean:write name="model" property="settingid"/>">
<input type="hidden" name="sysCourseSetting.days" id="sysCourseSetting.days" value="<bean:write name="model" property="days"/>">
<input type="hidden" name="sysCourseSetting.unitid" value="<bean:write name="model" property="unitid"/>">
</html:form>
</div>
</body>
</html:html>
