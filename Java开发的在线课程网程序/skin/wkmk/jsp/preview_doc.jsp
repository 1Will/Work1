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
<script type="text/javascript" src="/public/js/flexpaper_flash.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
</head>

<body>
<%@ include file="top.jsp"%>
<%
VodFilmAnnex model = (VodFilmAnnex)request.getAttribute("model");
%>
<div id="tab_nav">
<div class="clearfix">
  <div class="tv clearfix">
    <ul class="y_coll_2 clearfix bt">
      <%-- <li><a href="/v-download-0-${model.annexid }.htm" class="d_coll">下载</a></li>--%>
      <li>${model.title }</li>
    </ul>
    <div class="video">
    <a id="viewerPlaceHolder" style="width:100%;height:600px;display:block"></a>
	<script type="text/javascript"> 
		var fp = new FlexPaperViewer(	
		 '/public/swf/FlexPaperView',
		 'viewerPlaceHolder', { config : {
		 <%if(model.getFileswf().startsWith("http://")){ %>
		 SwfFile : escape('<bean:write name="model" property="fileswf"/>'),
		 <%}else{%>
		 SwfFile : escape('/upload_dir/<bean:write name="model" property="fileswf"/>'),
		 <%}%>
		 Scale : 0.6, 
		 ZoomTransition : 'easeOut',
		 ZoomTime : 0.5,
		 ZoomInterval : 0.2,
		 FitPageOnLoad : true,
		 FitWidthOnLoad : true,
		 PrintEnabled : true,
		 FullScreenAsMaxWindow : true,
		 ProgressiveLoading : false,
		 MinZoomSize : 0.2,
		 MaxZoomSize : 5,
		 SearchMatchAll : false,
		 InitViewMode : 'Portrait',
		 
		 ViewModeToolsVisible : true,
		 ZoomToolsVisible : true,
		 NavToolsVisible : true,
		 CursorToolsVisible : true,
		 SearchToolsVisible : true,
		 localeChain: 'zh_CN'
				 }});
	</script>
    </div>
  </div>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
