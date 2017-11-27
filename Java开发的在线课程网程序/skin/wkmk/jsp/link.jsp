<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.gpw.bo.GpwApplyLink"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-友情链接</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
</head>

<body style="background:#f7f7f7;">
<%@ include file="top.jsp"%>

<div class="view_main">
	<div class="view_left">
		<ul>
		<a href="/v-aboutus-12-0.htm"><li>关于我们</li></a>
		<a href="/v-aboutgroup-12-0.htm"><li>团队介绍</li></a>
		<a href="/v-job-12-0.htm"><li>人才招聘</li></a>
		<a href="/v-contact-12-0.htm"><li>联系我们</li></a>
		<a href="/feedback.htm"><li>意见反馈</li></a>
		<a href="/v-link-12-0.htm"><li class="hover">友情链接</li></a>
		<a href="/v-applylink-12-0.htm"><li style="border-bottom:none;">申请链接</li></a>
		</ul>
	</div>

	<div class="view_right">
		<div class="view_right_01 mar_13">
		<h3>友情链接</h3>
		<ul class="link_ul">
		  <li>
		  	<%
		  	Map map = (Map)request.getAttribute("map");
		  	String[] applylinkid = Constants.getCodeTypeid("applylink");
		  	String[] applylinkname = Constants.getCodeTypename("applylink");
		  	for(int i=0, size=applylinkid.length; i<size; i++){
		  	%>
		    <a href="#linktype<%=i+1 %>" class="bgli_<%=i+1 %>" <%if(i == size-1){ %>style="margin-right:0"<%} %>><%=applylinkname[i] %></a>
		    <%} %>
		  </li>
		</ul>
		</div>
		
		<%
		List list = null;
		GpwApplyLink gal = null;
		for(int i=0, size=applylinkid.length; i<size; i++){
		%>
		<div class="view_right_01 mar_13">
		<a name="linktype<%=i+1 %>"></a>
		<h3><%=applylinkname[i] %></h3>
		<%
		list = (List)map.get(applylinkid[i]);
		if(list != null && list.size() > 0){
			for(int m=0, n=list.size(); m<n; m++){
				gal = (GpwApplyLink)list.get(m);
		%>
		<%if(m%4 == 0){ %>
		<div class="part21_bottom">
		<%} %>
		  <dl <%if((m+1)%4 == 0){ %>style="margin-right:0"<%} %>>
		    <dt><a target="_blank" href="<%=gal.getLinkurl() %>"><img title="<%=gal.getLinkname() %>" style="width:190px;height:60px;" src="<%=gal.getLinklogo() %>" /></a></dt>
		  </dl>
		<%if((m != 0 && (m+1)%4 == 0) || m == n-1){ %>
		</div>
		<%} %>
		<%}} %>
		</div>
		<%} %>
	</div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>
