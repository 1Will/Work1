<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="java.util.Map"%>
<%@page import="com.bzt.vod.bo.VodAnnexType"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@page import="com.bzt.vod.bo.VodFilmLet"%>
<%@page import="com.bzt.vod.bo.VodFilmAnnex"%>
<%@page import="com.bzt.vod.bo.VodFilmKnopoint"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-<bean:write name="f_unitinfo" property="unitname"/>-<bean:write name="vodFilmInfo" property="title"/>-<bean:write name="vodFilmLet" property="filmletname"/></title>
<link rel="stylesheet" type="text/css" href="/public/js/dialog/blue/style.css"/>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/public/js/dialog/drag.js"></script>
<script type="text/javascript" src="/public/js/dialog/dialog.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="vodFilmInfo" property="title"/> <bean:write name="vodFilmLet" property="filmletname"/>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="vodFilmInfo" property="title"/> <bean:write name="vodFilmLet" property="filmletname"/>">
<%
VodFilmInfo vodFilmInfo = (VodFilmInfo)request.getAttribute("vodFilmInfo");
%>
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
	function collect(filmid, sc){
		$.ajax({
			   url: "/v.bo?method=collect",
			   data: "fileid="+filmid+"&type=1&filetype=2&ram=" + Math.random(),
			   success: function(msg){
				   if('-1' == msg){
					   document.getElementById("collect_01").style.display = "none";
					   document.getElementById("collect_02").style.display = "block";
				   }else if('0' == msg){
				   	    alert("请您先登录再收藏当前微课!");
				   }else{
					    document.getElementById("film_collects").innerHTML = sc+1;
					    document.getElementById("collect_01").style.display = "none";
						document.getElementById("collect_02").style.display = "block";
				   }
			   }
			});
	}
   function selectTree(fid){
    $.ajax({
      type:"post",
      url:"/v.bo?method=videoCollection",
      data:"fid="+fid+"&fileid="+<bean:write name="vodFilmInfo" property="filmid"/>
    });
   	var tree = document.getElementById("tree");
		tree.style.display = "none";
	document.getElementById("film_collects").innerHTML =<bean:write name="vodFilmInfo" property="collects"/>+1;
    document.getElementById("collect_01").style.display = "none";
	document.getElementById("collect_02").style.display = "block";
   }
   function createfavorites(){
    var diag=new top.Dialog();
    diag.Title="新建收藏夹";
    diag.Left='95%';
    diag.URL='/v.bo?method=createfavorites&filetype=2&fileid='+<bean:write name="vodFilmInfo" property="filmid"/>;
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
   function download(filmletid, caifu, money, moneytype){
	   if(moneytype != "0"){
		   if(confirm("下载当前微课需要在线支付"+money+"元人民币!")){
			   alert("处理中。。。");
		   }
	   }else{
		   if(caifu > 0){
			   if(confirm("下载当前视频需要扣除"+caifu+"财富值!")){
					$.ajax({
						   url: "/v.bo?method=collect",
						   data: "fileid="+filmletid+"&type=2&filetype=3&ram=" + Math.random(),
						   success: function(msg){
							   if('-1' == msg){
							   		alert("您的财富值不够下载当前视频，请先充值！");
							   }else if('0' == msg){
							   	    alert("请您先登录再下载当前视频!");
							   }else{
								   window.location.href = '/v.bo?method=downloadsVod&fileid='+filmletid+'&msg='+msg;
							   }
						   }
						});
				}
		   }else{
			   $.ajax({
				   url: "/v.bo?method=collect",
				   data: "fileid="+filmletid+"&type=2&filetype=3&ram=" + Math.random(),
				   success: function(msg){
					   if('-1' == msg){
					   		alert("您的财富值不够下载当前视频，请先充值！");
					   }else if('0' == msg){
					   	    alert("请您先登录再下载当前视频!");
					   }else{
						   window.location.href = '/v.bo?method=downloadsVod&fileid='+filmletid+'&msg='+msg;
					   }
				   }
				});
		   }
	   }
	}
</script>
</head>

<body style="background:#f7f7f7">
<jsp:include page="/xtree/xtree.jsp" />
<c:if test="${islogin==1 }">
<c:if test="${iscollection==1 }">
<div id="tree" class="floatTree" style="display: none;z-index: 5">
<div style="float: right;"><a href="javascript:createfavorites();">创建</a>&nbsp;|&nbsp;<a href="javascript:closeTree();">关闭</a></div>
<script>
 var tree=new WebFXLoadTree("我的收藏夹","sysUserFavoritesAction.do?method=orglist&parentno=0000","");
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
      <li><a href="<bean:write name="vodFilmInfo" property="vodFilmType.flags"/>"><bean:write name="vodFilmInfo" property="vodFilmType.typename"/></a></li>
      <li>&gt;</li>
      </logic:notEqual>
      <li><bean:write name="vodFilmInfo" property="title"/></li>
      <%-- 
      <li>&gt;</li>
      <li><bean:write name="vodFilmLet" property="filmletname"/></li>
      --%>
      <li style="float: right;margin-top:0px;margin-right:0px;"><span style="font-size: 18px;color: red;"><a style="cursor: pointer;color: red;" target="_blank" href="/v-reportWrong-<bean:write name="vodFilmInfo" property="filmid"/>-2.htm"><img src="/skin/wkmk/images/reportwrong.jpg" border="0" /></a></span></li>
    </ul>
  </div>
  <div class="yk_left">
    <div class="video1">
    <logic:equal value="swf" name="vodFilmLet" property="fileext">
    <%@ include file="play_swf.jsp"%>
    </logic:equal>
    <logic:notEqual value="swf" name="vodFilmLet" property="fileext">
    <%@ include file="play_info.jsp"%>
    </logic:notEqual>
    </div>
    <div class="news_j">
      <table width="100%" height="120" border="0" cellpadding="0" cellspacing="0" class="yk_1">
          <tr>
            <td width="29%" align="left">
              <h1 title="<bean:write name="vodFilmInfo" property="title"/>"><%=SubStringDirectiveModel.subString(vodFilmInfo.getTitle(), 17, "...") %></h1>
              <p>学校：<a href="/html/<bean:write name="f_unitid"/>.htm" target="_blank" title="<bean:write name="f_unitinfo" property="unitname"/>"><java2page:write name="f_unitinfo" property="unitname" length="12"/></a></p>
            </td>
            <td width="14%" align="center">
              <dt><a href="/v-user-<bean:write name="sysUserInfo" property="unitid"/>-<bean:write name="sysUserInfo" property="userid"/>.htm" target="_blank"><img src="<bean:write name="sysUserInfo" property="photo"/>" width="50" height="50"/></a></dt>
              <p><a href="/v-user-<bean:write name="sysUserInfo" property="unitid"/>-<bean:write name="sysUserInfo" property="userid"/>.htm" target="_blank"><bean:write name="sysUserInfo" property="username"/></a></p>
            </td>
            <td width="18%" align="left" class="p_line">
              <%List vodFilmLetListSize = (List)request.getAttribute("vodFilmLetList"); %>
              <p>课程数量：<%=vodFilmLetListSize.size() %></p>
        	  <p>浏览次数：<bean:write name="vodFilmInfo" property="counts"/></p>
        	  <p>收藏次数：<font id="film_collects"><bean:write name="vodFilmInfo" property="collects"/></font></p>
        	  <p>下载次数：<bean:write name="vodFilmInfo" property="downloads"/></p>
            </td>
            <td width="24%" align="left" class="p_line">
              <p>
	          <%=StarUtil.getStarImage(vodFilmInfo.getScore()) %>
	          </p>
	          <p>评分:<bean:write name="vodFilmInfo" property="score"/>（<bean:write name="vodFilmInfo" property="scoreusers"/>人评）</p>
	          <p><bean:write name="vodFilmInfo" property="createdate"/></p>
	          <%
	          String money = "0";
	          if(vodFilmInfo.getMoney() > 0.00F){ 
	        	  money = "1";
	          %>
	          <p>下载需：<bean:write name="vodFilmInfo" property="money"/>元人民币</p>
	          <%}else{ %>
	          <p>下载扣：<bean:write name="vodFilmLet" property="caifu"/>财富值</p>
	          <%} %>
            </td>
            <td width="15%" align="left">
             <a href="javascript:download('<bean:write name="vodFilmLet" property="filmletid"/>', <bean:write name="vodFilmLet" property="caifu"/>, <bean:write name="vodFilmInfo" property="money"/>, '<%=money %>')" class="d_coll">下载</a><br />
             <c:if test="${islogin==0 }">
              <a id="collect_01" href="javascript:collect('<bean:write name="vodFilmInfo" property="filmid"/>', <bean:write name="vodFilmInfo" property="collects"/>)" class="y_coll">收藏</a>
              </c:if>
              <c:if test="${islogin==1 }">
              <c:if test="${iscollection==1 }">
              <a id="collect_01" onclick="openTree(event);return false;" class="y_coll">收藏</a>
              </c:if>
              </c:if>
              <c:if test="${iscollection==0}">
               <a id="collect_02"  class="s_coll">已收藏</a>
              </c:if>
              <a id="collect_02" style="display:none" class="s_coll">已收藏</a>
            </td>
          </tr>
        </table>
    </div>
    <%@ include file="play_film.jsp"%>
     <iframe name="discussframe" id="discussframe" onload="SetCwinHeight()" src="/v-vinfo-${f_unitid }-${vodFilmInfo.filmid }.htm" width="812" height="320" scrolling="no" frameborder="0"></iframe>
  </div>
  <div class="yk_right">
    <div class="yk_bottom back_1 clearfix mar_12">
      <ul class="list_1">
        <li id="ta1"><a onclick="switchTa('ta1','co1');this.blur();return false;" style="cursor:pointer;" class="lik_2">相关微课</a></li>
        <li id="ta2"><a onclick="switchTa('ta2','co2');this.blur();return false;" style="cursor:pointer;">相关文档</a></li>
      </ul>
      <div class="yk_bottom11" id="co1">
        <dl>
          <%
          List list2 = (List)request.getAttribute("list2");
          VodFilmInfo vfi = null;
          for(int i=0, size=list2.size(); i<size; i++){
        	  vfi = (VodFilmInfo)list2.get(i);
          %>
          <dd><a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank" title="<%=vfi.getTitle() %>"><%=SubStringDirectiveModel.subString(vfi.getTitle(), 19, "...") %></a></dd>
          <%} %>
        </dl>
      </div>
      <div class="yk_bottom2" id="co2" style="display: none">
        <div class="text_bottom1">
          <dl class="dl_2 dl_21" style="width:330px;height:370px;">
            <%
	        List list1 = (List)request.getAttribute("list1");
	        DocFileInfo dfi = null;
	        for(int i=0, size=list1.size(); i<size; i++){
	        	dfi = (DocFileInfo)list1.get(i);
	        %>
            <dd class="ico_<%=dfi.getFileext() %> cc_1"><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank" title="<%=dfi.getTitle() %>"><%=SubStringDirectiveModel.subString(dfi.getTitle(), 19, "...") %></a></dd>
            <%} %>
          </dl>
        </div>
      </div>
    </div>
    <div class="yk_bottom back_1 clearfix">
      <ul class="list_1">
        <li id="ta1"><a>推荐微课</a></li>
      </ul>
      <div class="yk_bottom1">
        <%
        List recommandVodList = (List)request.getAttribute("recommandVodList");
        for(int i=0, size=recommandVodList.size(); i<size; i++){
        	vfi = (VodFilmInfo)recommandVodList.get(i);
        %>
        <dl id="vod_dl_<%=i+1 %>" <%if(i>=4){ %>style="display:none;"<%} %>>
          <dt><a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank"><%if(vfi.getSketch().startsWith("http://")){ %><img src="<%=vfi.getSketch() %>" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=vfi.getSketch() %>" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %></a></dt>
          <dd><a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank" class="color_6"><%=vfi.getTitle() %></a></dd> 
        </dl>
        <%} %>
      </div>
      <div class="btn_down" id="vod_btn_down" title="点击展开查看更多"><a href="javascript:showDiv('down', 'vod')"></a></div>
      <div class="btn_up" id="vod_btn_up" style="display:none;" title="点击收起"><a href="javascript:showDiv('up', 'vod')"></a></div>
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
        		for(var i=5; i<=8; i++){
           			if(document.getElementById(div + "_dl_" + i)){
           				document.getElementById(div + "_dl_" + i).style.display = "block";
           			}
           		}
       			document.getElementById(div + "_btn_down").style.display = "none";
       			document.getElementById(div + "_btn_up").style.display = "block";
        	}
			if(tag == 'up'){
        		for(var i=5; i<=8; i++){
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
