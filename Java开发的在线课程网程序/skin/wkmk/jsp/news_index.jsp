<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${model.title }</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${model.title }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${model.title }">
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <div class="wap mar_5">
    <ul>
      <li><a href="/index.html" target="_blank">首页</a></li>
      <li>&gt;</li>
      <li><a class="color_6">资讯</a></li>
      <li>&gt;</li>
      <li><a href="/v-c-${f_unitid }-${model.vodNewsColumn.columnid }.htm">${model.vodNewsColumn.columnname }</a></li>
    </ul>
  </div>
  <div class="list_nav clearfix">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_ta">
	  <tr>
	    <th height="50" colspan="3" style="font-size:24px;font-weight:normal">${model.title }</th>
	  </tr>
	  <tr>
	    <td height="50" colspan="3" align="center">
	      <span>作者：${model.author }</span>
	      <span>时间：${model.createdate }</span>
	      <span>浏览量：${model.hits }</span>
	    </td>
	  </tr>
	  <logic:equal value="1" name="model" property="newstype">
	  <tr>
	    <td width="9%">&nbsp;</td>
	    <td width="82%">
	      <p>${model.htmlcontent }</p>
	    </td>
	    <td width="9%">&nbsp;</td>
	  </tr>
	  </logic:equal>
	  <logic:equal value="2" name="model" property="newstype">
	  <tr>
	    <td width="9%">&nbsp;</td>
	    <td width="82%">
	      <p>
	        <script type="text/javascript" src="/public/js/flexpaper_flash.js"></script>
            <span style="width:100%; float:left; font-size:12px; padding:0px;">
			<a id="viewerPlaceHolder" style="width:100%;height:600px;display:block"></a>
			<script type="text/javascript"> 
				var fp = new FlexPaperViewer(	
				 '/public/swf/FlexPaperView',
				 'viewerPlaceHolder', { config : {
				 SwfFile : escape('/upload_dir/<bean:write name="model" property="swfpath"/>'),
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
	      </p>
	    </td>
	    <td width="9%">&nbsp;</td>
	  </tr>
	  </logic:equal>
	</table>
  </div>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"slide":{"type":"slide","bdImg":"8","bdPos":"right","bdTop":"200"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</html>