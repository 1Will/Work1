<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.FileUtil"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-<bean:write name="f_unitinfo" property="unitname"/>-<bean:write name="model" property="title"/></title>
<link rel="stylesheet" type="text/css" href="/public/js/dialog/blue/style.css"/>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/public/js/dialog/drag.js"></script>
<script type="text/javascript" src="/public/js/dialog/dialog.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/public/js/flexpaper_flash.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
<%DocFileInfo model = (DocFileInfo)request.getAttribute("model"); %>
<script type="text/javascript">
	function SetCwinHeight(){
		var iframeid=document.getElementById("discussframe");
		if (document.getElementById){
		   if (iframeid && !window.opera){
			   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
			     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
			   	 iframeid.height = height1>320?height1:320;
			   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
			     var height2 = iframeid.Document.body.scrollHeight;
			  	 iframeid.height = height2>320?height2:320;
			   }
		   }
		}
	}
	function collect(fileid, type, caifu, collects){
		if(type == '2' && caifu > 0){
			if(confirm("下载当前文档需要扣除"+caifu+"财富值!")){
				$.ajax({
					   url: "/v.bo?method=collect",
					   data: "fileid="+fileid+"&type="+type+"&filetype=1&ram=" + Math.random(),
					   success: function(msg){
						   if(type == '1'){
							   if('-1' == msg){
								   document.getElementById("collect_01").style.display = "none";
								   document.getElementById("collect_02").style.display = "block";
							   }else if('0' == msg){
							   	    alert("请您先登录再收藏当前文档!");
							   }else{
								   document.getElementById("film_collects").innerHTML = collects+1;
								    document.getElementById("collect_01").style.display = "none";
									document.getElementById("collect_02").style.display = "block";
							   }
						   }
						   if(type == '2'){
							   if('-1' == msg){
							   		alert("您的财富值不够下载当前文档，请先充值！");
							   }else if('0' == msg){
							   	    alert("请您先登录再下载当前文档!");
							   }else{
								   window.location.href = '/v.bo?method=downloads&fileid='+fileid+'&msg='+msg;
							   }
						   }
					   }
					});
			}
		}else{
			$.ajax({
				   url: "/v.bo?method=collect",
				   data: "fileid="+fileid+"&type="+type+"&filetype=1&ram=" + Math.random(),
				   success: function(msg){
					   if(type == '1'){
						   if('-1' == msg){
							   document.getElementById("collect_01").style.display = "none";
							   document.getElementById("collect_02").style.display = "block";
						   }else if('0' == msg){
						   	    alert("请您先登录再收藏当前文档!");
						   }else{
							   document.getElementById("film_collects").innerHTML = collects+1;
							    document.getElementById("collect_01").style.display = "none";
								document.getElementById("collect_02").style.display = "block";
						   }
					   }
					   if(type == '2'){
						   if('0' == msg){
						   	    alert("请您先登录再下载当前文档!");
						   }else{
							   window.location.href = '/v.bo?method=downloads&fileid='+fileid+'&msg='+msg;
						   }
					   }
				   }
				});
		}
	}
	function selectTree(fid){
	$.ajax({
	  typ:"post",
	  url:"/v.bo?method=docCollection",
	  data:"fid="+fid+"&fileid="+${model.fileid} 
	});
	var tree = document.getElementById("tree");
		tree.style.display = "none";
	document.getElementById("film_collects").innerHTML = ${model.collects}+1;
    document.getElementById("collect_01").style.display = "none";
	document.getElementById("collect_02").style.display = "block";
	
	}
	
function createfavorites(){
    var diag=new top.Dialog();
    diag.Title="新建收藏夹";
    diag.Left='95%';
    diag.URL='/v.bo?method=createfavorites&filetype=1&fileid='+${model.fileid} ;
    diag.Width = 400;
	diag.Height = 240;
	diag.CancelEvent = function(){
	   if(diag.innerFrame.contentWindow.document.getElementById('name')){
	     if(diag.innerFrame.contentWindow.document.getElementById('name').value=='1'){
	        document.getElementById("collect_01").style.display = "none";
	        document.getElementById("collect_02").style.display = "block";
	        closeTree();
		  }
		}
		 diag.close();
	};
    diag.show();
   }
	
</script>
</head>

<body style="background:#f7f7f7">
<jsp:include page="/xtree/xtree.jsp" />
<c:if test="${islogin ==1 }">
<c:if test="${iscollection==1 }">
<div id="tree" class="floatTree" style="display: none;z-index: 5">
<div style="float: right;"><a href="javascript:createfavorites();">创建</a>&nbsp;|&nbsp;<a href="javascript:closeTree();">关闭</a></div>
<script>
  var tree=new WebFXLoadTree("我的收藏夹","sysUserFavoritesAction.do?method=orglist&userid=${userid}&parentno=0000","");
  document.write(tree);
</script>
</div>
</c:if>
</c:if>
<%@ include file="top.jsp"%>

<div id="tab_nav">
<div class="clearfix">
  <div class="wap mar_5">
    <ul>
      <li><a href="/index.html">首页</a></li>
      <li>&gt;</li>
      <li><a href="/html/<bean:write name="f_unitinfo" property="unitid"/>.htm"><bean:write name="f_unitinfo" property="unitname"/></a></li>
      <li>&gt;</li>
      <logic:equal value="2010" name="f_unitinfo" property="type">
      <li><a href="<bean:write name="subjectInfo" property="flags"/>"><bean:write name="subjectInfo" property="subjectname"/></a></li>
      <li>&gt;</li>
      <li><a href="<bean:write name="gradeInfo" property="flags"/>"><bean:write name="gradeInfo" property="gradename"/></a></li>
      <li>&gt;</li>
      <li><a href="<bean:write name="versionInfo" property="flags"/>"><bean:write name="versionInfo" property="versionname"/></a></li>
      <li>&gt;</li>
      </logic:equal>
      <logic:notEqual value="2010" name="f_unitinfo" property="type">
      <logic:present name="parent">
      <li><a href="<bean:write name="parent" property="flags"/>"><bean:write name="parent" property="typename"/></a></li>
      <li>&gt;</li>
      </logic:present>
      <li><a href="<bean:write name="model" property="vodFilmType.flags"/>"><bean:write name="model" property="vodFilmType.typename"/></a></li>
      <li>&gt;</li>
      </logic:notEqual>
      <li><bean:write name="model" property="title"/></li>
      <li style="float: right;margin-top:0px;margin-right:0px;"><span style="font-size: 18px;color: red;"><a style="cursor: pointer;color: red;" target="_blank" href="/v-reportWrong-<bean:write name="model" property="fileid"/>-1.htm"><img src="/skin/wkmk/images/reportwrong.jpg" border="0" /></a></span></li>
    </ul>
  </div>
  <div class="yk_left">
    <div class="video1" style="height:620px;">
    <a id="viewerPlaceHolder" style="width:100%;height:620px;display:block"></a>
	<script type="text/javascript"> 
		var fp = new FlexPaperViewer(	
		 '/public/swf/FlexPaperView',
		 'viewerPlaceHolder', { config : {
		 SwfFile : escape('${model.swfpath }'),
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
    <div class="news_j">
      <table width="100%" height="120" border="0" cellpadding="0" cellspacing="0" class="yk_1">
          <tr>
            <td width="29%" align="left">
              <h1 title="<%=model.getTitle() %>"><%=SubStringDirectiveModel.subString(model.getTitle(), 17, "...") %></h1>
              <p>学校：<a href="/html/<bean:write name="f_unitid"/>.htm" target="_blank" title="<bean:write name="f_unitinfo" property="unitname"/>"><java2page:write name="f_unitinfo" property="unitname" length="12"/></a></p>
            </td>
            <td width="14%" align="center">
              <dt><a href="/v-user-<bean:write name="userInfo" property="unitid"/>-<bean:write name="userInfo" property="userid"/>.htm" target="_blank"><img src="<bean:write name="userInfo" property="photo"/>" width="50" height="50"/></a></dt>
              <p><a href="/v-user-<bean:write name="userInfo" property="unitid"/>-<bean:write name="userInfo" property="userid"/>.htm" target="_blank"><bean:write name="userInfo" property="username"/></a></p>
            </td>
            <td width="18%" align="left" class="p_line">
              <p>浏览次数：<%=model.getHits() %></p>
	          <p>收藏次数：<font id="film_collects"><%=model.getCollects() %></font></p>
	          <p>下载次数：<%=model.getDownloads() %></p>
	          <p>文档大小：<%=FileUtil.getFileSizeName(model.getFilesize().doubleValue(), 2) %></p>
            </td>
            <td width="24%" align="left" class="p_line">
              <p>
		          <%=StarUtil.getStarImage(model.getScore()) %>
	          </p>
	          <p>评分：<%=model.getScore() %>（<%=model.getScoreusers() %>人评）</p>
	          <p><%=model.getCreatedate() %></p>
	          <p>下载扣：<%=model.getCaifu() %>财富值</p>
            </td>
            <td width="15%" align="left">
              <a href="javascript:collect('${model.fileid }', '2', ${model.caifu }, ${model.collects })" class="d_coll">下载</a><br />
              <c:if test="${islogin ==0}">
              <a href="javascript:collect('${model.fileid }', '1', ${model.caifu }, ${model.collects })" class="y_coll" id="collect_01">收藏</a>
      
              </c:if>
              <c:if test="${islogin ==1 }">
               <c:if test="${iscollection==1 }">
              <a onclick="openTree(event); return false;" class="y_coll" id="collect_01">收藏</a>
                </c:if>
              </c:if>
              <c:if test="${iscollection==0}">
               <a id="collect_02" class="s_col_1" ">已收藏</a>
              </c:if>
              <a id="collect_02" class="s_col_1" style="display:none;">已收藏</a>
            </td>
          </tr>
        </table>
    </div>
    <iframe name="discussframe" id="discussframe" onload="SetCwinHeight()" src="/v-ddiscuss-${f_unitid }-${model.fileid }.htm" width="812" height="320" scrolling="no" frameborder="0"></iframe>
  </div>
  <div class="yk_right">
    <div class="yk_bottom back_1 clearfix mar_12">
      <ul class="list_1">
      	<li id="ta2"><a onclick="switchTa('ta2','co2');this.blur();return false;" style="cursor:pointer;" class="lik_2">相关文档</a></li>
        <li id="ta1"><a onclick="switchTa('ta1','co1');this.blur();return false;" style="cursor:pointer;">相关微课</a></li>
      </ul>
      <div class="yk_bottom11" id="co1" style="display: none">
        <dl style="width:330px;height:510px;">
          <%
          List list2 = (List)request.getAttribute("list2");
          VodFilmInfo vfi = null;
          for(int i=0, size=list2.size(); i<size; i++){
        	  vfi = (VodFilmInfo)list2.get(i);
          %>
          <dd><a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank" title="<%=vfi.getTitle() %>"><%=SubStringDirectiveModel.subString(vfi.getTitle(), 18, "...") %></a></dd>
          <%} %>
        </dl>
      </div>
      <div class="yk_bottom2" id="co2">
        <div class="text_bottom1">
          <dl class="dl_2 dl_21" style="width:330px;height:530px;">
            <%
	        List list1 = (List)request.getAttribute("list1");
	        DocFileInfo dfi = null;
	        for(int i=0, size=list1.size(); i<size; i++){
	        	dfi = (DocFileInfo)list1.get(i);
	        %>
            <dd class="ico_<%=dfi.getFileext() %> cc_1"><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank" title="<%=dfi.getTitle() %>"><%=SubStringDirectiveModel.subString(dfi.getTitle(), 18, "...") %></a></dd>
            <%} %>
          </dl>
        </div>
      </div>
    </div>
    <div class="yk_bottom back_1 clearfix">
      <ul class="list_1">
        <li><a>推荐文档</a></li>
      </ul>
      <div class="yk_bottom2">
        <%
        List recommandDocList = (List)request.getAttribute("recommandDocList");
        for(int i=0, size=recommandDocList.size(); i<size; i++){
        	dfi = (DocFileInfo)recommandDocList.get(i);
        %>
        <div class="text_bottom1" id="doc_dl_<%=i+1 %>" <%if(i>=5){ %>style="display:none;"<%} %>>
          <dl class="dl_2 dl_20" style="width:330px;">
            <dd class="ico_<%=dfi.getFileext() %> cc_1"><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank" title="<%=dfi.getTitle() %>"><%=SubStringDirectiveModel.subString(dfi.getTitle(), 18, "...") %></a></dd>
            <dd>
              <span>
                <%=StarUtil.getStarImage(dfi.getScore()) %>
                <span class="font_1"><%=dfi.getScore() %>分</span>
              </span>
              <span class="mar_1"><%=dfi.getPagenum() %>页</span>
            </dd>
          </dl>
        </div>
        <%} %>
        <div class="btn_down" id="doc_btn_down" title="点击展开查看更多"><a href="javascript:showDiv('down', 'doc')"></a></div>
        <div class="btn_up" id="doc_btn_up" style="display:none;" title="点击收起"><a href="javascript:showDiv('up', 'doc')"></a></div>
      </div>
     </div>
  </div>  
</div>
</div>
<script type="text/javascript">
        function switchTa(ProTag1, ProBox1) {
            for (i = 1; i < 3; i++) {
                if ("ta" + i == ProTag1) {
                    document.getElementById(ProTag1).getElementsByTagName("a")[0].className = "lik_2";
                } else {
                    document.getElementById("ta" + i).getElementsByTagName("a")[0].className = "";
                }
                if ("co" + i == ProBox1) {
                    document.getElementById(ProBox1).style.display = "";
                } else {
                    document.getElementById("co" + i).style.display = "none";
                }
            }
        }
        function showDiv(tag, div){
        	if(tag == 'down'){
        		for(var i=6; i<=10; i++){
           			if(document.getElementById(div + "_dl_" + i)){
           				document.getElementById(div + "_dl_" + i).style.display = "block";
           			}
           		}
       			document.getElementById(div + "_btn_down").style.display = "none";
       			document.getElementById(div + "_btn_up").style.display = "block";
        	}
			if(tag == 'up'){
        		for(var i=6; i<=10; i++){
           			if(document.getElementById(div + "_dl_" + i)){
           				document.getElementById(div + "_dl_" + i).style.display = "none";
           			}
           		}
				document.getElementById(div + "_btn_down").style.display = "block";
       			document.getElementById(div + "_btn_up").style.display = "none";
        	}
        }
    </script>
<%@ include file="footer.jsp"%>
</body>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"slide":{"type":"slide","bdImg":"8","bdPos":"right","bdTop":"200"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</html>