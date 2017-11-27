<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${f_unitinfo.unitname }-${model.username }-个人介绍</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${model.username }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${model.username }">
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <div class="wap mar_5">
    <ul>
      <li><a href="/index.html" target="_blank">首页</a></li>
      <li>&gt;</li>
      <li><a href="/html/${f_unitid }.htm">${f_unitinfo.unitname }</a></li>
      <li>&gt;</li>
      <li><a href="/v-user-${f_unitid }-${model.userid }.htm">${model.username }</a></li>
      <li>&gt;</li>
      <li><a>用户详情</a></li>
    </ul>
  </div>
  <div class="list_nav clearfix">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_ta">
	  <tr>
	    <td width="9%">&nbsp;</td>
	    <td width="82%">
	      <p>${model.htmlcontent }</p>
	    </td>
	    <td width="9%">&nbsp;</td>
	  </tr>
	</table>
  </div>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
