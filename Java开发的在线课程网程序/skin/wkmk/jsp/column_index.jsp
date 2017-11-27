<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.vod.bo.VodNewsColumn"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-资讯中心</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-c-${f_unitid}-${curid}.htm';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*10;
		document.pageForm.action = '/v-c-${f_unitid}-${curid}.htm';
		document.pageForm.submit();
	}
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<form name="pageForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <div class="wap mar_5">
    <ul>
      <li><a href="/index.html" target="_blank">首页</a></li>
      <li>&gt;</li>
      <li><a class="color_6">资讯</a></li>
    </ul>
  </div>
  <div class="list_nav clearfix">
    <div class="list_nav_left">
      <h3 class="mod-nav__li-first"><a class="mod-nav__course-all">资讯中心</a></h3>
      <ul class="list_nav_1">
        <%
      	String curid = (String)request.getAttribute("curid");
      	List parentnList = (List)request.getAttribute("parentnList");
      	VodNewsColumn column = null;
      	for(int i=0, size=parentnList.size(); i<size; i++){
   	  	  	column = (VodNewsColumn)parentnList.get(i);
      	%>
      	<li <%if(curid.equals(column.getColumnid().toString())){ %>class="bg_li"<%} %>><a href="/v-c-<%=column.getUnitid() %>-<%=column.getColumnid() %>.htm"><%=column.getColumnname() %></a></li>
      	<%} %>
      </ul>
    </div>
    <div class="list_nav_right">
      <ul>
        <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
        <logic:empty name="model" property="linkurl">
        <li <%if(index%2 == 0){ %>class="li_bg"<%} %>><span class="date"><java2page:write name="model" property="createdate" length="10"/></span><a href="/v-vni-<bean:write name="model" property="unitid"/>-<bean:write name="model" property="newsid"/>.htm" target="_blank"><bean:write name="model" property="title"/></a></li>
        </logic:empty>
        <logic:notEmpty name="model" property="linkurl">
        <li <%if(index%2 == 0){ %>class="li_bg"<%} %>><span class="date"><java2page:write name="model" property="createdate" length="10"/></span><a href="<bean:write name="model" property="linkurl"/>" target="_blank"><bean:write name="model" property="title"/></a></li>
        </logic:notEmpty>
        </logic:iterate>
        <logic:empty name="pagelist" property="datalist">
		<li class="li_bg">暂无数据！</li>
		</logic:empty>
      </ul>
    </div>
    <logic:greaterThan value="1" name="pagelist" property="totalPages">
    <div class="page" style="margin-left:250px;">
    	<%@ include file="page.jsp"%>
    </div>
    </logic:greaterThan>
  </div>
</div>
</div>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>
