<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v.bo?method=selectSchoolList';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*10;
		document.pageForm.action = '/v.bo?method=selectSchoolList';
		document.pageForm.submit();
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchSchool();
  	}
}
function searchSchool(){
	document.pageForm.action = '/v.bo?method=selectSchoolList';
	document.pageForm.submit();
}
function deal(unitid){
	document.pageForm.action="/v.bo?method=selectSchoolDeal&unitid="+unitid;
	document.pageForm.submit();
}
function add(){
	document.pageForm.action="/v.bo?method=selectSchoolAdd";
	document.pageForm.submit();
}
function changeArea(type, areano){
	if(type == '1'){
		if(areano != ''){
			$.ajax({
		        type: "get",
		        async: false,
		        url: "/v.bo?method=getArea&areano=" + areano + "&ram=" + Math.random(),
		        dataType: "text",
		        success: function(data){
		        	if(data != ''){
		        		$("#areano2").html(data);
					}else{
						$("#areano2").html("<option value=''>请选择</option>");
					}
		        }
			});
		}else{
			$("#areano2").html("<option value=''>请选择</option>");
		}
		$("#areano3").html("<option value=''>请选择</option>");
	}
	if(type == '2'){
		if(areano != ''){
			$.ajax({
		        type: "get",
		        async: false,
		        url: "/v.bo?method=getArea&areano=" + areano + "&ram=" + Math.random(),
		        dataType: "text",
		        success: function(data){
		        	if(data != ''){
		        		$("#areano3").html(data);
					}else{
						$("#areano3").html("<option value=''>请选择</option>");
					}
		        }
			});
		}else{
			$("#areano3").html("<option value=''>请选择</option>");
		}
	}
	searchSchool();
}
</script>
</head>

<body>
  <form name="pageForm" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  	<tr>
    	<th height="40" colspan="2" align="center" valign="middle" style="font-weight:normal;font-size:24px;">学校列表</th>
    </tr>
  	<tr>
    	<td height="30" colspan="2" valign="middle" style="background-color:#efefef; ">查询&gt;学校信息</td>
  	</tr>
  	<tr>
	    <td height="50" colspan="2">
	      <label class="w100" style="margin-left:0px;">学校地区</label>
	      <select name="areano1" onchange="changeArea('1', this.value)" class="select_1">
	      	  <option value="">请选择</option>
	          <%
	          String areano1 = (String)request.getAttribute("areano1");
	          List arealist1 = (List)request.getAttribute("arealist1");
	          GpwAreaInfo gai = null;
	          for(int i=0, size=arealist1.size(); i<size; i++){
	        	  gai = (GpwAreaInfo)arealist1.get(i);
	          %>
	          <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano1)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
	          <%} %>
	      </select>
	      <select name="areano2" id="areano2" onchange="changeArea('2', this.value)" class="select_1">
	          <option value="">请选择</option>
	          <%
	          List arealist2 = (List)request.getAttribute("arealist2");
	          String areano2 = (String)request.getAttribute("areano2");
	          if(arealist2 != null && arealist2.size() > 0){
	        	  
	          for(int i=0, size=arealist2.size(); i<size; i++){
	        	  gai = (GpwAreaInfo)arealist2.get(i);
	          %>
	          <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano2)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
	          <%}} %>
	      </select>
	      <select name="areano3" id="areano3" onchange="changeArea('3', this.value)" class="select_1">
	          <option value="">请选择</option>
	          <%
	          List arealist3 = (List)request.getAttribute("arealist3");
	          String areano3 = (String)request.getAttribute("areano3");
	          if(arealist3 != null && arealist3.size() > 0){
	          for(int i=0, size=arealist3.size(); i<size; i++){
	        	  gai = (GpwAreaInfo)arealist3.get(i);
	          %>
	          <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano3)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
	          <%}} %>
	      </select>
		</td>
  	</tr>
  	<tr>
	    <td height="50" colspan="2">
	      <label class="w100" style="margin-left:0px;">学校性质</label>
	      <select name="unittype" class="select_1" onchange="searchSchool()">
	        <option value="">请选择</option>
	        <option value="2010" <logic:equal value="2010" name="unittype">selected="selected"</logic:equal>>基础教育</option>
	        <option value="2020" <logic:equal value="2020" name="unittype">selected="selected"</logic:equal>>职业教育</option>
	        <option value="2030" <logic:equal value="2030" name="unittype">selected="selected"</logic:equal>>高等教育</option>
	        <option value="2040" <logic:equal value="2040" name="unittype">selected="selected"</logic:equal>>继续教育</option>
	      </select>
	      <div class="seach2" style="padding-top:0;">
	          <input type="text" placeholder="在结果中搜索" name="unitname" value="${unitname }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
	          <a href="javascript:searchSchool()"></a>
	        </div>
	    </td>
  	</tr>
  	<tr>
	    <td height="50">
	      <span class="color_8">说明：请在学校地区中选择省/市/区和学校性质缩小收缩范围，学校名称输入框中请使用学校简称<br />关键词进行搜索，若果列表中没有您的学校，可以直接创建学校</span>
	    </td>
	    <td><input type="button" value="创建学校" onclick="add()" class="sub_re1" style="margin-left:10px;"/></td>
  	</tr>
  <tr>
    <td colspan="2">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#ccc" class="tab_mar">
	  <tr>
	    <td width="55" height="40" align="center" bgcolor="#efefef">序号</td>
	    <td align="center" bgcolor="#efefef">学校名称</td>
	    <td width="110" align="center" bgcolor="#efefef">学校性质</td>
	    <td width="55" align="center" bgcolor="#efefef">确认</td>
	  </tr>
	  <%Integer startcount = (Integer)request.getAttribute("startcount"); %>
  	  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
  	  <%if(index%2 == 0){ %>
	  <tr>
	    <td height="30" align="center" bgcolor="#FFFFFF"><%=startcount+index+1 %></td>
	    <td bgcolor="#FFFFFF"><bean:write name="model" property="unitname"/></td>
	    <td align="center" bgcolor="#FFFFFF"><java2code:value codetype="unittype" property="type"></java2code:value></td>
	    <td align="center" bgcolor="#FFFFFF" style="cursor:pointer;" onclick="deal('<bean:write name="model" property="unitid"/>')"><input name="unitid" type="radio" value="<bean:write name="model" property="unitid"/>"/></td>
	  </tr>
	  <%}else{ %>
	  <tr>
	    <td height="30" align="center" bgcolor="#efefef"><%=startcount+index+1 %></td>
	    <td bgcolor="#efefef"><bean:write name="model" property="unitname"/></td>
	    <td align="center" bgcolor="#efefef"><java2code:value codetype="unittype" property="type"></java2code:value></td>
	    <td align="center" bgcolor="#efefef" style="cursor:pointer;" onclick="deal('<bean:write name="model" property="unitid"/>')"><input name="unitid" type="radio" value="<bean:write name="model" property="unitid"/>"/></td>
	  </tr>
	  <%} %>
	  </logic:iterate>
	  </table>
	  <logic:greaterThan value="1" name="pagelist" property="totalPages">
      <div class="page" style="width:770px;">
        <%@ include file="page.jsp"%>
      </div>
      </logic:greaterThan>
    </td>
  </tr>
 </table>
 <input type="hidden" name="startcount" id="startcount" value=''>
 </form>
</body>
</html>