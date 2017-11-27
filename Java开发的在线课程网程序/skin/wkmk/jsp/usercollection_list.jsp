<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.doc.bo.DocFileInfo"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.util.date.DateTime"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript">

</script>
</head>

<body>
<form name="pageForm" method="post">
<div class="Ind_right" >
  <div class="Ind_1 clearfix">
    <h3 class="Ind_h2">我的收藏夹<c:if test="${type!='0000' }"><span style="float:right;"><a href="/v.bo?method=userf&flag=3&parentno=${parentno }&back=yes&objid=${userid}">返回&nbsp;&nbsp;</a></span></c:if></h3>
    <ul class="h_pic2" style="min-height: 20px;">
     <c:forEach items="${userfavoritesList}" var="uf">
        <li class="l_pic1" style="margin-left: 20px;margin-right: 20px;">
          <div class="mod-course-card h180" style="height: 150px;">
            <a href="/v.bo?method=userf&flag=3&parentno=${uf.fno }&objid=${userid}" target="_self" class="mod-course-card__link-img" >
             <img src="/upload_dir/Folder.jpg" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/>
            </a>
            <div class="course-title">
              <a title="${uf.name }" class="mod-course-card__name" target="_self" href="/v.bo?method=userf&flag=3&parentno=${uf.fno }&objid=${userid}">${uf.name }</a> 
            </div>
          </div>
        </li>
          </c:forEach>
          <c:if test="${type=='0000' }">
         <li class="l_pic1" style="margin-left: 20px;">
           <div class="mod-course-card h180" style="height: 150px;">
             <a href="/v.bo?method=userf&flag=3&parentno=0000&objid=${userid}" target="_self" class="mod-course-card__link-img">
             <img src="/upload_dir/Folder.jpg" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/>
            </a>
            <div class="course-title">
              <a title="未分类" class="mod-course-card__name" target="_self" href="/v.bo?method=userf&flag=3&parentno=0000&objid=${userid}">未分类</a> 
            </div>
           </div>
         </li>
         </c:if>
    </ul>
  </div>
</div>
<div class="Ind_right" >
  <div class="Ind_1 clearfix">
    <h3 class="Ind_h2">我收藏的微课</h3>
    <%
     List list=(List)request.getAttribute("voidFilminfoList");
     Object[]obj=null;
    for(int i=0, size=list.size(); i<size; i++){
           obj = (Object[])list.get(i);
     %>
     <ul class="h_pic2" style="min-height: 20px;">
        <li class="l_pic1" style="margin-left: 20px;margin-right: 20px;">
          <div class="mod-course-card h180">
            <a href="/v-play-<%=obj[7] %>-<%=obj[2] %>.htm" target="_blank" class="mod-course-card__link-img">
             <img src="<%=obj[8] %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/>
              <i class="icon-card-play"></i>
            </a>
            <div class="course-title">
              <a title="<%=obj[3] %>" class="mod-course-card__name" target="_blank" href="/v-play-<%=obj[7] %>-<%=obj[2] %>.htm"><%=obj[3] %></a> 
            </div>
           <p class="mod-course-card__line"> 
              <a class="mod-course-card__agency font_1 mar_3">评分<span class="color_5"><%=obj[5] %></span><%=obj[6]%>人评</a>
              <a class="mod-course-card__agency font_1 mar_3">浏览<span class="color_5"><%=obj[9] %> </span></a>
              <a class="mod-course-card__agency font_1 mar_3">收藏<span class="color_5"><%=obj[10] %></span></a>
           </p>
          </div>
        </li>
        <%} %>
    </ul>
  </div>
</div>

<div class="Ind_right">
  <div class="Ind_1 clearfix">
    <h3 class="Ind_h2">我收藏的文档</h3>
    <%
     List list2=(List)request.getAttribute("dociFilinfoList");
     Object[]obj2=null;
    for(int i=0, size=list2.size(); i<size; i++){
           obj2 = (Object[])list2.get(i);
     %>
    <div class="text_bottom">
        <dl class="dl_1">
          <dt><a href="/v-doc-<%=obj2[7] %>-<%=obj2[2] %>.htm" target="_blank"><img src="<%=obj2[8] %>" /></a></dt>
          <dd class="ico_<%=obj2[11] %> dd_1"><a href="/v-doc-<%=obj2[7] %>-<%=obj2[2] %>.htm" target="_blank" title="<%=obj2[4] %>"><%=SubStringDirectiveModel.subString(obj2[3].toString(), 33, "...") %></a></dd>
          <dd>
            <span>浏览<strong class="color_1"><%=obj2[9] %></strong></span>
            <span>收藏<strong class="color_1"><%=obj2[10] %></strong></span>
            <span>下载量<strong class="color_1"><%=obj2[12] %></strong></span>
            <span>页数<strong class="color_1"><%=obj2[4] %></strong></span>
            <span>上传时间：<%=obj2[13] %></span>
          </dd>
        </dl>
        <div class="right_box">
          <ul>
            <li><%=obj2[7] %>人评</li>
            <li>
              <span class="font_1 mar_1"><%=obj2[6] %>分</span>
            </li>
          </ul>
        </div>
      </div>
      <%} %>
  </div>
</div>


</form>
</body>
</html>
