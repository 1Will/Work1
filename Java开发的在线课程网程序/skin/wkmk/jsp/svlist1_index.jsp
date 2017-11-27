<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.net.URLEncoder"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.util.search.PageList"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@page import="java.util.Map"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${f_unitinfo.unitname }-学校微课</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<script type="text/javascript">
function gotoUrl(typeid, orderbytype){
	if(typeid != ''){
		document.getElementById("columnid").value = typeid;
	}
	document.pageForm.action = '/v-svlist-${f_unitid}-' + orderbytype + '.htm#position';
	document.pageForm.submit();
}
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-svlist-${f_unitid}-${orderbytype}.htm#position';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*12;
		document.pageForm.action = '/v-svlist-${f_unitid}-${orderbytype}.htm#position';
		document.pageForm.submit();
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchVod();
  	}
}
function searchVod(){
	document.pageForm.action = '/v-svlist-${f_unitid}-${orderbytype}.htm#position';
	document.pageForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <%@ include file="school_banner.jsp"%>
  <a name="position"></a>
  <form name="pageForm" method="post">
  <h2></a>微课</h2>
  <%
  Map map = (Map)request.getAttribute("map");
  VodFilmType vft = null;
  List list1 = (List)map.get("list1");
  String typeid1 = null;
  if(list1 != null && list1.size() > 0){
	  typeid1 = (String)map.get("typeid1");
  %>
  <dl class="dl_tab">
    <dt>分类：</dt>
    <dd>
      <a href="javascript:gotoUrl('0', '${orderbytype }')" <%if("0".equals(typeid1)){ %>class="curr_all"<%} %>>全部</a>
      <%
	  for(int i=0, size=list1.size(); i<size; i++){
	  	  vft = (VodFilmType)list1.get(i);
	  %>
	  <a href="javascript:gotoUrl('<%=vft.getTypeid() %>', '${orderbytype }')" <%if(typeid1.equals(vft.getTypeid().toString())){ %>class="curr_all"<%} %>><%=vft.getTypename() %></a>
	  <%} %>
    </dd>
  </dl>
  <%} %>
  <%
  List list2 = (List)map.get("list2");
  String typeid2 = null;
  if(list2 != null && list2.size() > 0){
	  typeid2 = (String)map.get("typeid2");
  %>
  <dl class="dl_tab" style="border-bottom:none">
    <dt>专业：</dt>
    <dd>
      <a href="javascript:gotoUrl('<%=typeid1 %>', '${orderbytype }')" <%if("0".equals(typeid2)){ %>class="curr_all"<%} %>>全部</a>
      <%
	  for(int i=0, size=list2.size(); i<size; i++){
		  vft = (VodFilmType)list2.get(i);
	  %>
	  <a href="javascript:gotoUrl('<%=vft.getTypeid() %>', '${orderbytype }')" <%if(typeid2.equals(vft.getTypeid().toString())){ %>class="curr_all"<%} %>><%=vft.getTypename() %></a>
	  <%} %>
    </dd>
  </dl>
  <%} %>
  <div class="list clearfix">
    <div class="seach_tab_1">
        <%
        String orderbytype = (String)request.getAttribute("orderbytype");
        %>
        <ul>
          <li><a href="javascript:gotoUrl('${columnid }', '1')" <%if("1".equals(orderbytype)){ %>class="link0"<%} %>>最新</a></li>
          <li><a href="javascript:gotoUrl('${columnid }', '2')" <%if("2".equals(orderbytype)){ %>class="link1"<%} %>>评分</a></li>
          <li><a href="javascript:gotoUrl('${columnid }', '3')" <%if("3".equals(orderbytype)){ %>class="link1"<%} %>>浏览量</a></li>
          <li><a href="javascript:gotoUrl('${columnid }', '4')" <%if("4".equals(orderbytype)){ %>class="link1"<%} %>>收藏量</a></li>
        </ul>
        <div class="seach2">
          <input type="text" placeholder="搜索课程" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
          <a href="javascript:searchVod()"></a>
        </div>
        <%@ include file="page1.jsp"%>
      </div>
    <div class="list_bottom1 clearfix">
        <%
        PageList pagelist = (PageList)request.getAttribute("pagelist");
      	List list = pagelist.getDatalist();
      	if(list != null && list.size() > 0){
      	VodFilmInfo vfi = null;
      	String actors = null;
      	for(int i=0, size=list.size(); i<size; i++){
      		vfi = (VodFilmInfo)list.get(i);
      		actors = vfi.getActors();
      		if(actors.length() > 4) actors = actors.substring(0, 4);
      	%>
      	<%if(i%4 == 0){ %>
        <ul class="h_pic3">
        <%} %>
          <li class="l_pic1" <%if(i==3 || i==7 || i==11){ %>style="margin-right:0"<%} %>>
            <div class="mod-course-card h180">
              <a href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm" target="_blank" class="mod-course-card__link-img">
                <%if(vfi.getSketch().startsWith("http://")){ %><img src="<%=vfi.getSketch() %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=vfi.getSketch() %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %>
                <i class="icon-card-play"></i>
              </a>
              <div class="course-title">
                <a title="<%=vfi.getTitle() %>" class="mod-course-card__name" target="_blank" href="/v-play-<%=vfi.getUnitid() %>-<%=vfi.getFilmid() %>.htm"><%=vfi.getTitle() %></a> 
              </div>
             <p class="mod-course-card__line"> 
                <a title="<%=vfi.getActors() %>" target="_blank" href="/v.bo?method=actors&filmid=<%=vfi.getFilmid() %>&actors=<%=URLEncoder.encode(vfi.getActors().trim(),"utf-8") %>" class="mod-course-card__price mod-course-card__price_free"><%=actors %></a> 
                <a class="mod-course-card__agency font_1 mar_3">评分<span class="color_5"><%=vfi.getScore() %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">收藏<span class="color_5"><%=vfi.getContest() %></span></a>
                <a class="mod-course-card__agency font_1 mar_3">浏览<span class="color_5"><%=vfi.getCounts() %></span></a>
             </p>
            </div>
          </li>
        <%if((i != 0 && (i+1)%4 == 0) || i == size-1){ %>
        </ul>
        <%} %>
        <%}}else{ %>
        <div class="text_bottom">
          <dl class="dl_1">
          <dd>抱歉，没有找到您需要的数据！</dd>
          </dl>
        </div>
        <%} %>
    </div>
    <logic:greaterThan value="1" name="pagelist" property="totalPages">
    <div class="page" style="margin-left:100px;">
    	<%@ include file="page.jsp"%>
    </div>
    </logic:greaterThan>
  </div>
  <input type="hidden" name="columnid" id="columnid" value="${columnid }"/>
  <input type="hidden" name="startcount" id="startcount" value=''>
  </form>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>