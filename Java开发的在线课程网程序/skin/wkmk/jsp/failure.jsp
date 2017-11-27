<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-提示</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<%@ include file="top.jsp"%>
<div id="access">
  <table width="100%" height="696" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="12%">&nbsp;</td>
    <td width="46%" style="font-size:35px;line-height:50px;">
    	<p>${promptinfo }</p>
    <td width="42%">&nbsp;</td>
  </tr>
</table>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
