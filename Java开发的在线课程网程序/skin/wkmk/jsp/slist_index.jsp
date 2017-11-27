<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.util.string.encode.Encode"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="com.util.service.action.CacheUtil"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-学校</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function gotoUrl(type){
	document.pageForm.action = '/v-slist-' + type + '-0.htm';
	document.pageForm.submit();
}
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-slist-${type}-0.htm';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*15;
		document.pageForm.action = '/v-slist-${type}-0.htm';
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
	document.getElementById("pageFormSearchButton").value = "1";
	document.pageForm.action = '/v-slist-${type}-0.htm';
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
<%@ include file="top.jsp"%>
<form name="pageForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <div class="form_1 clearfix" style="border-bottom:1px solid #e1e1e1;">
    <select name="areano1" onchange="changeArea('1', this.value)">
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
    <select name="areano2" id="areano2" onchange="changeArea('2', this.value)">
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
    <select name="areano3" id="areano3" onchange="changeArea('3', this.value)">
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
    <div class="seach2" style="padding-top:0;">
      <input type="text" placeholder="搜索学校" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
      <a href="javascript:searchSchool()"></a>
    </div>
  </div>
  <div class="list clearfix">
    <div class="list_left">
      <div class="seach_tab hh">
        <ul>
          <li id="tab1"><a href="javascript:gotoUrl('1')" <logic:equal value="1" name="type">class="hov"</logic:equal>>基础教育</a></li>
          <li id="tab2"><a href="javascript:gotoUrl('2')" <logic:equal value="2" name="type">class="hov"</logic:equal>>职业教育</a></li>
          <li id="tab3"><a href="javascript:gotoUrl('3')" <logic:equal value="3" name="type">class="hov"</logic:equal>>高等教育</a></li>
          <li id="tab4"><a href="javascript:gotoUrl('4')" <logic:equal value="4" name="type">class="hov"</logic:equal>>继续教育</a></li>
        </ul>
        <%@ include file="page1.jsp"%>
      </div>
      <div class="list_bottom clearfix" id="con1">
        <%
        PageList pagelist = (PageList)request.getAttribute("pagelist");
      	List list = pagelist.getDatalist();
      	if(list != null && list.size() > 0){
      	Object[] object = null;
      	for(int i=0, size=list.size(); i<size; i++){
      		object = (Object[])list.get(i);
      	%>
        <%if(i%3 == 0){ %>
        <ul class="h_pic2">
        <%} %>
          <li class="l_pic1 <%if(i==1 || i==4 || i==7 || i==10 || i==13){ %>mar_4<%} %>">
            <div class="mod-course-card h190">
              <a href="/html/<%=object[0] %>.htm" target="_blank" class="mod-course-card__link-img">
                <img src="<%=object[2] %>" width="220" height="124"/>
              </a>
              <div class="course-title">
                <a title="<%=object[1] %>" class="mod-course-card__name" target="_blank" href="/html/<%=object[0] %>.htm"><%=object[1] %></a> 
              </div>
             <p class="mod-course-card__line"> 
                <a class="mod-course-card__agency font_1 mar_3">教师 <span class="color_5"><%=CacheUtil.get("unitUsers_" + object[0]) %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">微课 <span class="color_5"><%=Encode.nullToDefault(object[3], "0") %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">文档 <span class="color_5"><%=CacheUtil.get("unitDocs_" + object[0]) %></span></a>
             </p>
            </div>
          </li>
        <%if((i != 0 && (i+1)%3 == 0) || i == size-1){ %>
        </ul>
        <%} %>
        <%}}else{ %>
        <div class="text_bottom">
          <dl class="dl_1">
          <dd>抱歉，没有找到您需要的数据！</dd>
          </dl>
        </div>
        <%} %>
      </div>
      <logic:greaterThan value="1" name="pagelist" property="totalPages">
      <div class="page">
        <%@ include file="page.jsp"%>
      </div>
      </logic:greaterThan>
    </div>
    <div class="list_right mar_2">
      <div class="like clearfix" style="margin-top:0">
        <h3>学校推荐</h3>
        <ul class="h_pic1">
          <%
          List recommandList = (List)request.getAttribute("recommandList");
          SysUnitInfo sui = null;
          for(int i=0, size=recommandList.size(); i<size; i++){
        	  sui = (SysUnitInfo)recommandList.get(i);
          %>
          <li class="l_pic">
            <div class="mod-course-card h190">
              <a href="/html/<%=sui.getUnitid() %>.htm" target="_blank" class="mod-course-card__link-img">
                <img src="<%=sui.getSketch() %>" width="220" height="124"/>
              </a>
              <div class="course-title">
                <a title="<%=sui.getUnitname() %>" class="mod-course-card__name" target="_blank" href="/html/<%=sui.getUnitid() %>.htm"><%=sui.getUnitname() %></a> 
              </div>
            <p class="mod-course-card__line"> 
                <a class="mod-course-card__agency font_1 mar_3">教师 <span class="color_5"><%=sui.getFlags() %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">微课 <span class="color_5"><%=sui.getFlago() %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">文档 <span class="color_5"><%=sui.getDescript() %></span></a>
             </p>
            </div>
          </li>
          <%} %>
        </ul>
    </div>
    </div>
</div>
</div>
</div>
<input type="hidden" name="searchButton" id="pageFormSearchButton" value=""/>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>