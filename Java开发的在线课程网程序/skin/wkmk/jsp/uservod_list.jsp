<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.util.date.DateTime"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-userf-1-${userid}.htm';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*20;
		document.pageForm.action = '/v-userf-1-${userid}.htm';
		document.pageForm.submit();
	}
}
</script>
</head>

<body>
<form name="pageForm" method="post">
<div class="Ind_right">
  <%
  Map map = (Map)request.getAttribute("map");
  List dateList = (List)request.getAttribute("dateList");
  String date = null;
  for(int m=0, n=dateList.size(); m<n; m++){
	  date = (String)dateList.get(m);
  %>
  <div class="Ind_1 clearfix">
    <h3 class="Ind_h2"><%=date.substring(0,4) %>年<%=date.substring(5,7) %>月</h3>
    <%
    List list = (List)map.get(date);
    if(list != null && list.size() > 0){
    	VodFilmInfo vfi = null;
    	for(int i=0, size=list.size(); i<size; i++){
    		vfi = (VodFilmInfo)list.get(i);
    %>
    <%if(i%3 == 0){ %>
    <ul class="h_pic2">
    <%} %>
        <li class="l_pic1 <%if(i%3 == 1){ %>mar_4<%} %>">
          <div class="mod-course-card h180">
            <a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank" class="mod-course-card__link-img">
              <%if(vfi.getSketch().startsWith("http://")){ %><img src="<%=vfi.getSketch() %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=vfi.getSketch() %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %>
              <i class="icon-card-play"></i>
            </a>
            <div class="course-title">
              <a title="<%=vfi.getTitle() %>" class="mod-course-card__name" target="_blank" href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm"><%=vfi.getTitle() %></a> 
            </div>
           <p class="mod-course-card__line"> 
              <a class="mod-course-card__agency font_1 mar_3">评分<span class="color_5"><%=vfi.getScore() %></span> (<%=vfi.getScoreusers() %>人评)</a>
              <a class="mod-course-card__agency font_1 mar_3">浏览<span class="color_5"><%=vfi.getCounts() %></span></a>
              <a class="mod-course-card__agency font_1 mar_3">收藏<span class="color_5"><%=vfi.getContest() %></span></a>
           </p>
          </div>
        </li>
    <%if((i != 0 && (i+1)%3 == 0) || i == size-1){ %>
    </ul>
    <%} %>
    <%}} %>
  </div>
  <%} %>
  <logic:greaterThan value="1" name="pagelist" property="totalPages">
  <div class="page">
    <%@ include file="page.jsp"%>
  </div>
  </logic:greaterThan>
</div>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
</body>
</html>
