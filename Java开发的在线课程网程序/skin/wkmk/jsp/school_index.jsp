<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${f_unitinfo.unitname }</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <div class="school_home">
    <div class="school_banner">
      <img src="${f_unitinfo.banner}" />
    </div>
    <div class="school_right">
      <dl>
        <%
        SysUnitInfo unitInfo = (SysUnitInfo)request.getAttribute("f_unitinfo");
        if(unitInfo.getHomepage() != null && !"".equals(unitInfo.getHomepage())){
        %>
        <dt><a href="${f_unitinfo.homepage}" target="_blank"><img src="${f_unitinfo.logo}"/></a></dt>
        <%}else{ %>
        <dt><img src="${f_unitinfo.logo}"/></dt>
        <%} %>
        <dd>教师数量：<font id="s_js">0</font></dd>
        <dd>微课数量：<font id="s_wk">0</font></dd>
        <dd>文档数量：<font id="s_doc">0</font></dd>
        <a href="javascript:praise()" class="zan_btn" id="s_praise">${f_unitinfo.praise}</a>
      </dl>
      <p class="p_dl"><%=SubStringDirectiveModel.subString(unitInfo.getKeywords(), 150, "...") %><a href="/v-sinfo-${f_unitid}-0.htm" target="_blank">［查看更多］</a></p>
    </div>
  </div>
  <div class="list clearfix">
    <div class="list_bottom1 clearfix">
      <h2><a href="/v-svlist-${f_unitid}-1.htm" class="date">更多&gt;&gt;</a>微课</h2>
      	<%
      	List vodlist = (List)request.getAttribute("vodlist");
      	if(vodlist != null && vodlist.size() > 0){
      	VodFilmInfo vfi = null;
      	for(int i=0, size=vodlist.size(); i<size; i++){
      		vfi = (VodFilmInfo)vodlist.get(i);
      	%>
        <%if(i%4 == 0){ %>
        <ul class="h_pic3">
        <%} %>
          <li class="l_pic1" <%if((i+1)%4 == 0){ %>style="margin-right:0"<%} %>>
            <div class="mod-course-card h180">
              <a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank" class="mod-course-card__link-img">
                <%if(vfi.getSketch().startsWith("http://")){ %><img src="<%=vfi.getSketch() %>" width="220" height="124"/><%}else{ %><img src="/upload_dir/<%=vfi.getSketch() %>" width="220" height="124"/><%} %>
                <i class="icon-card-play"></i>
              </a>
              <div class="course-title">
                <a title="<%=vfi.getTitle() %>" class="mod-course-card__name" target="_blank" href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm"><%=vfi.getTitle() %></a> 
              </div>
             <p class="mod-course-card__line"> 
                <a title="<%=vfi.getActors() %>" target="_blank" href="<%=vfi.getFlags() %>" class="mod-course-card__price mod-course-card__price_free"><%if(vfi.getActors().length() < 5){ %><%=vfi.getActors() %><%}else{ %><%=vfi.getActors().substring(0, 3) %>..<%} %></a> 
                <a class="mod-course-card__agency font_1 mar_3">评分<span class="color_5"><%=vfi.getScore() %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">浏览<span class="color_5"><%=vfi.getCounts() %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">收藏<span class="color_5"><%=vfi.getContest() %></span></a>
             </p>
            </div>
          </li>
        <%if((i != 0 && (i+1)%4 == 0) || i == size-1){ %>
        </ul>
        <%} %>
        <%}} %>
    </div>
    <div class="list_bottom1 clearfix">
      <h2><a href="/v-sdlist-${f_unitid}-1.htm" class="date">更多&gt;&gt;</a>文档</h2>
      <%
      List doclist = (List)request.getAttribute("doclist");
      if(doclist != null && doclist.size() > 0){
      DocFileInfo dfi = null;
      for(int i=0, size=doclist.size(); i<size; i++){
      	dfi = (DocFileInfo)doclist.get(i);
      %>
      <%if(i%3 == 0){ %>
      <div class="ed_bottom1">
      <%} %>
        <dl class="ed_1" <%if((i+1)%3 == 0){ %>style="margin-right:0"<%} %>>
            <dt><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank"><img src="<%=dfi.getSketch() %>" /></a></dt>
            <dd class="ico_<%=dfi.getFileext().toLowerCase() %>"><a href="/v-doc-<%=dfi.getUnitid() %>-<%=dfi.getFileid() %>.htm" target="_blank" style="font-size:14px;"><%if(dfi.getTitle().length() < 19){ %><%=dfi.getTitle() %><%}else{ %><%=dfi.getTitle().substring(0, 17) %>..<%} %></a></dd>
            <dd style="padding-left:0px;"><a href="/v-user-<%=dfi.getUnitid() %>-<%=dfi.getUserid() %>.htm" target="_blank" class="color_2"><%=dfi.getFlags() %></a> | <span>评分<span class="color_5"><%=dfi.getScore() %></span></span><span>共<font class="color_5"><%=dfi.getPagenum() %></font>页</span></dd>
            <dd style="padding-left:0px;">
              <span>收藏<span class="color_5"><%=dfi.getCollects() %></span></span>
              <span>浏览<span class="color_5"><%=dfi.getHits() %></span></span>
              <span>下载量<span class="color_5"><%=dfi.getDownloads() %></span></span>
            </dd>
          </dl>
      <%if((i != 0 && (i+1)%3 == 0) || i == size-1){ %>
      </div>
      <%} %>
      <%}} %>
    </div>
    <div class="list_bottom1 clearfix">
      <h2><a href="/v-sulist-${f_unitid }-1.htm" class="date">更多&gt;&gt;</a>教师</h2>
      <div class="de_left">
      <table width="100%" border="0" cellspacing="0" class="tabel_1">
		  <%
	      List userlist = (List)request.getAttribute("userlist");
	      if(userlist != null && userlist.size() > 0){
	      SysUserInfo sui = null;
	      for(int i=0, size=userlist.size(); i<size; i++){
	    	  sui = (SysUserInfo)userlist.get(i);
	      %>
	      <%if(i%4 == 0){ %>
		  <tr>
		  <%} %>
		    <td width="10%" align="left"><%if(sui != null){ %><a href="/v-user-${f_unitid }-<%=sui.getUserid() %>.htm"><img src="<%=sui.getPhoto() %>" /></a><%}else{ %>&nbsp;<%} %></td>
		    <%if(i == 0 || i == 4){ %>
		    <td width="19%" align="left">
		    <%} %>
		    <%if(i == 1 || i == 5){ %>
		    <td width="17%" align="left">
		    <%} %>
		    <%if(i == 2 || i == 6){ %>
		    <td width="14%" align="left">
		    <%} %>
		    <%if(i == 3 || i == 7){ %>
		    <td width="10%" align="left">
		    <%} %>
		    <%if(sui != null){ %>
		      <p><a href="/v-user-${f_unitid }-<%=sui.getUserid() %>.htm" class="color_2"><%=sui.getUsername() %></a>&nbsp;&nbsp;<span class="color_3"><%if(sui.getJob() != null && !"".equals(sui.getJob())){ %><%=sui.getJob() %><%} %></span></p>
		      <p class="color_3" style="font-size:10px;">微课数量：<span class="color_5"><%=sui.getFlago() %></span></p>
		      <p class="color_3" style="font-size:10px;">文档数量：<span class="color_5"><%=sui.getFlags() %></span></p>
		    <%}else{ %><p>&nbsp;</p><%} %>
		    </td>
		    <%if((i != 0 && (i+1)%4 == 0) || i == size-1){ %>
		    </tr>
		    <%} %>
		    <%}} %>
		</table>
    </div>
    </div>
  </div>
</div>
</div>
<script type="text/javascript">
function praise(){
	$.ajax({
	   url: "/v.bo?method=praise",
	   data: "unitid=${f_unitid}&flag=1",
	   success: function(msg){
	   	 if('-1' == msg){
	   	 	alert("您已点过赞！");
	   	 }else{
	   	    $("#s_praise").text(msg);
	   	 }
	   }
	}); 
	return;
}
function getSchoolStatistics(){
	$.ajax({
        type: "get",
        url: "/v.bo?method=getSchoolStatistics",
        data: "unitid=${f_unitid}&ram=" + Math.random(),
        dataType: "text",
        success: function(data){
        	var statistics = data.split(";");
        	$("#s_js").text(statistics[0]);
        	$("#s_wk").text(statistics[1]);
        	$("#s_doc").text(statistics[2]);
        	$("#s_praise").text(statistics[3]);
         }
    });
}
getSchoolStatistics();
</script>
<%@ include file="footer.jsp"%>
</body>
</html>