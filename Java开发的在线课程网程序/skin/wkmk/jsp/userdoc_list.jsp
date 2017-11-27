<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
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
	document.pageForm.action = '/v-userf-2-${userid}.htm';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*12;
		document.pageForm.action = '/v-userf-2-${userid}.htm';
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
    	DocFileInfo dfi = null;
    	for(int i=0, size=list.size(); i<size; i++){
    		dfi = (DocFileInfo)list.get(i);
    %>
    <div class="text_bottom">
        <dl class="dl_1">
          <dt><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank"><img src="<%=dfi.getSketch() %>" /></a></dt>
          <dd class="ico_<%=dfi.getFileext() %> dd_1"><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank" title="<%=dfi.getTitle() %>"><%=SubStringDirectiveModel.subString(dfi.getTitle(), 33, "...") %></a></dd>
          <dd>
            <span>浏览<strong class="color_1"><%=dfi.getHits() %></strong></span>
            <span>收藏<strong class="color_1"><%=dfi.getCollects() %></strong></span>
            <span>下载量<strong class="color_1"><%=dfi.getDownloads() %></strong></span>
            <span>页数<strong class="color_1"><%=dfi.getPagenum() %></strong></span>
            <span>上传时间：<%=dfi.getCreatedate() %></span>
          </dd>
        </dl>
        <div class="right_box">
          <ul>
            <li><%=dfi.getScoreusers() %>人评</li>
            <li>
              <%=StarUtil.getStarImage(dfi.getScore()) %>
              <span class="font_1 mar_1"><%=dfi.getScore() %>分</span>
            </li>
          </ul>
        </div>
      </div>
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
