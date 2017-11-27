<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线预览</title>
<script type="text/javascript" src="/public/js/flexpaper_flash.js"></script>
</head>

<body>
<%
DocFileInfo model = (DocFileInfo)request.getAttribute("model"); 
if("1".equals(model.getConvertstatus())){
%>
<span style="width:780px; float:left; font-size:12px; padding:0px 0px 0px 0px;">
<a id="viewerPlaceHolder" style="width:780px;height:540px;display:block"></a>
<script type="text/javascript"> 
	var fp = new FlexPaperViewer(	
	 '/public/swf/FlexPaperView',
	 'viewerPlaceHolder', { config : {
	 SwfFile : escape('<%=model.getSwfpath() %>'),
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
</span>
<%}else if("2".equals(model.getConvertstatus())){ %>
<center style="text-align:center; font-size:26px; color:red; height:300px; padding-top:200px;">
文档转码失败，当前文档无法在线预览！
</center>
<%}else{ %>
<center style="text-align:center; font-size:26px; color:green; height:300px; padding-top:200px;">
文档正在转码中，当前文档无法在线预览！
</center>
<%} %>
</body>
</html>
