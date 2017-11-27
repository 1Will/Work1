<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.net.URLEncoder"%>
<%@page import="com.bzt.edu.bo.EduVersionInfo"%>
<%@page import="com.bzt.edu.bo.EduGradeInfo"%>
<%@page import="com.bzt.edu.bo.EduSubjectInfo"%>
<%@page import="com.bzt.edu.bo.EduXueduanInfo"%>
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.sys.util.Constants"%>
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
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/beikestyle.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v.bo?method=vlist2f';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*9;
		document.pageForm.action = '/v.bo?method=vlist2f';
		document.pageForm.submit();
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchVod();
  	}
}
function searchVod(){
	document.getElementById("pageFormSearchButton").value = "1";
	document.pageForm.action = '/v.bo?method=vlist2f';
	document.pageForm.submit();
}
function gotoUrl(orderbytype){
	document.getElementById("orderbytype").value = orderbytype;
	document.pageForm.action = '/v.bo?method=vlist2f';
	document.pageForm.submit();
}
</script>
</head>

<body>
<form name="pageForm" method="post">
<div class="list_left mar_2">
  <div class="seach_tab">
    <%
    String orderbytype = (String)request.getAttribute("orderbytype");
    %>
    <ul>
      <li><a href="javascript:gotoUrl('1')" <%if("1".equals(orderbytype)){ %>class="link0"<%} %>>最新</a></li>
      <li><a href="javascript:gotoUrl('2')" <%if("2".equals(orderbytype)){ %>class="link1"<%} %>>评分</a></li>
      <li><a href="javascript:gotoUrl('3')" <%if("3".equals(orderbytype)){ %>class="link1"<%} %>>浏览量</a></li>
      <li><a href="javascript:gotoUrl('4')" <%if("4".equals(orderbytype)){ %>class="link1"<%} %>>收藏量</a></li>
      <li><a href="javascript:gotoUrl('9')" <%if("9".equals(orderbytype)){ %>class="link0"<%} %>>精品</a></li>
    </ul>
    <div class="seach2">
      <input type="text" placeholder="搜索微课" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
      <a href="javascript:searchVod()"></a>
    </div>
    <%@ include file="page1.jsp"%>
  </div>
  <div class="list_bottom clearfix">
    <%
    PageList pagelist = (PageList)request.getAttribute("pagelist");
  	List list = pagelist.getDatalist();
  	if(list != null && list.size() > 0){
  	Object[] object = null;
  	String author = null;
  	for(int i=0, size=list.size(); i<size; i++){
  		object = (Object[])list.get(i);
  		author = (String)object[2];
  		if(author.length() > 4) author = author.substring(0, 4);
  	%>
  	<%if(i%3 == 0){ %>
    <ul class="h_pic2">
    <%} %>
      <li class="l_pic1 <%if(i==1 || i==4 || i==7 || i==10 || i==13){ %>mar_4<%} %>">
        <div class="mod-course-card h310">
          <a href="/v-play-<%=object[4] %>-<%=object[0] %>.htm" target="_blank" class="mod-course-card__link-img">
            <%if(object[3].toString().startsWith("http://")){ %><img src="<%=object[3] %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=object[3] %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %>
            <i class="icon-card-play"></i>
          </a>
          <div class="course-title">
            <a title="<%=object[1] %>" class="mod-course-card__name" target="_blank" href="/v-play-<%=object[4] %>-<%=object[0] %>.htm"><%=object[1] %></a> 
          </div>
          <p class="mod-course-card__line">
            <a title="<%=object[2] %>" target="_blank" href="/v.bo?method=actors&filmid=<%=object[0] %>&actors=<%=URLEncoder.encode(object[2].toString().trim(),"utf-8") %>" class="mod-course-card__price mod-course-card__price_free"><%=author %></a> 
            <a title="<%=object[5] %>" class="mod-course-card__agency" target="_blank" href="/html/<%=object[4] %>.htm"><%=object[5] %></a>
         </p>
         <p class="mod-course-card__line"> 
            <a class="mod-course-card__agency font_1 mar_3">评分<span class="color_5"><%=object[7] %></span> (<%=object[8] %>人评)</a>
            <a class="mod-course-card__agency font_1 mar_3">收藏<span class="color_5"><%=object[9] %></span></a>
            <a class="mod-course-card__agency font_1 mar_3">浏览<span class="color_5"><%=object[10] %></span></a>
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

<input type="hidden" name="xueduan" id="xueduan" value="${xueduan }"/>
<input type="hidden" name="subject" id="subject" value='${subject }'>
<input type="hidden" name="column" id="column" value="${knopointid }"/>
<input type="hidden" name="orderbytype" id="orderbytype" value='${orderbytype }'>
<input type="hidden" name="searchButton" id="pageFormSearchButton" value=""/>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
</body>
</html>