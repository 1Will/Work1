<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.vod.bo.VodFilmAnnex"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-<bean:write name="f_unitinfo" property="unitname"/>-<bean:write name="model" property="title"/></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
</head>

<body>
<%@ include file="top.jsp"%>
<%
VodFilmAnnex model = (VodFilmAnnex)request.getAttribute("model");
String mp3path = model.getFilepath();
if(!mp3path.startsWith("http://")){
	mp3path = "/upload_dir/" + mp3path;
}
%>
<div id="tab_nav">
<div class="clearfix">
  <div class="tv clearfix">
    <ul class="y_coll_2 clearfix bt">
      <%-- <li><a href="/v-download-0-${model.annexid }.htm" class="d_coll">下载</a></li>--%>
      <li>${model.title }</li>
    </ul>
    <div class="video">
    <span style="width:100%; float:left; font-size:12px; padding:0px 0px 8px 0px;">
	<EMBED height=280 type=application/x-shockwave-flash pluginspage=http://www.macromedia.com/go/getflashplayer width=100% src=/skin/wkmk/js/mp3.swf?soundFile=<%=mp3path %>&amp;bg=0xCDDFF3&amp;leftbg=0x357DCE&amp;lefticon=0xF2F2F2&amp;rightbg=0x357DCE&amp;rightbghover=0x4499EE&amp;righticon=0xF2F2F2&amp;righticonhover=0xFFFFFF&amp;text=0x357DCE&amp;slider=0x357DCE&amp;track=0xFFFFFF&amp;border=0xFFFFFF&amp;loader=0x8EC2F4&amp;autostart=yes&amp;loop=no allowscriptaccess="never" quality="high" wmode="transparent"></EMBED>
	</span>
    </div>
  </div>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
