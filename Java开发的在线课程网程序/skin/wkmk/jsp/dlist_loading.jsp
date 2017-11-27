<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
      <li><a href="javascript:gotoUrl('5')" <%if("5".equals(orderbytype)){ %>class="link1"<%} %>>下载量</a></li>
      <li><a href="javascript:gotoUrl('6')" <%if("6".equals(orderbytype)){ %>class="link1"<%} %>>页数</a></li>
      <li><a href="javascript:gotoUrl('9')" <%if("9".equals(orderbytype)){ %>class="link0"<%} %>>精品</a></li>
    </ul>
    <div class="seach2">
      <input type="text" placeholder="搜索文档" name="keywords" id="keywords" value="" maxlength="38">
      <a href="javascript:void();"></a>
    </div>
  </div>
  <div class="list_bottom clearfix">
    <div class="text_bottom">
      <dl class="dl_1">
      <dd>数据正在加载中......</dd>
      </dl>
    </div>
  </div>
</div>
</form>
</body>
</html>