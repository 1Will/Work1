<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmAnnex"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
</head>

<body>
<div class="detailed clearfix back_1">
  <ul class="ul_1">
    <li id="tab2"><a class="lik_1">课程简介</a></li>
    <li id="tab1"><a href="/v-vdiscuss-${f_unitid }-${filmid }.htm">用户评论</a></li>
  </ul>
  <div class="detailed_bottom">
    ${vodFilmInfo.descript }
	<div class="catalog dl_list">
	  <h3 class="bor_2">相关资源</h3>
	  <dl>
	    <dt>微教案</dt>
	    <%
	    List annexList = (List)request.getAttribute("annexList");
	    VodFilmAnnex annex = null;
	    for(int i=0, size=annexList.size(); i<size; i++){
	    	annex = (VodFilmAnnex)annexList.get(i);
	    	if("11".equals(annex.getTypeid())){
	    %>
	    <dd><a href="/v-view-<%=annex.getUnitid() %>-<%=annex.getAnnexid() %>.htm" target="_blank"><%=annex.getFilename() %></a></dd>
	    <%}} %>
	  </dl>
	  <dl>
	    <dt>微课件</dt>
	    <%
	    for(int i=0, size=annexList.size(); i<size; i++){
	    	annex = (VodFilmAnnex)annexList.get(i);
	    	if("12".equals(annex.getTypeid())){
	    %>
	    <dd><a href="/v-view-<%=annex.getUnitid() %>-<%=annex.getAnnexid() %>.htm" target="_blank"><%=annex.getFilename() %></a></dd>
	    <%}} %>
	  </dl>
	  <dl>
	    <dt>微习题</dt>
	    <%
	    for(int i=0, size=annexList.size(); i<size; i++){
	    	annex = (VodFilmAnnex)annexList.get(i);
	    	if("13".equals(annex.getTypeid())){
	    %>
	    <dd><a href="/v-view-<%=annex.getUnitid() %>-<%=annex.getAnnexid() %>.htm" target="_blank"><%=annex.getFilename() %></a></dd>
	    <%}} %>
	  </dl>
	  <dl>
	    <dt>微反思</dt>
	    <%
	    for(int i=0, size=annexList.size(); i<size; i++){
	    	annex = (VodFilmAnnex)annexList.get(i);
	    	if("14".equals(annex.getTypeid())){
	    %>
	    <dd><a href="/v-view-<%=annex.getUnitid() %>-<%=annex.getAnnexid() %>.htm" target="_blank"><%=annex.getFilename() %></a></dd>
	    <%}} %>
	  </dl>
	</div>
</div>
</div>
</body>
</html>