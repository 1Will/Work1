<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.net.URLEncoder"%>
<%@page import="com.bzt.vod.bo.VodFilmInfo"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.vod.bo.VodImageInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${contestInfo.title }</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/jquery.jslides.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${contestInfo.title }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${contestInfo.title }">
<script type="text/javascript">
function gotoUrl(rank, unittype, orderbytype){
	if(rank != ''){
		document.getElementById('rank').value = rank;
	}
	if(unittype != ''){
		document.getElementById('unittype').value = unittype;
	}
	if(orderbytype != ''){
		document.getElementById('orderbytype').value = orderbytype;
	}
	document.pageForm.action = '/v-contest-${f_unitid}-${contestInfo.contestid}.htm#position';
	document.pageForm.submit();
}
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-contest-${f_unitid}-${contestInfo.contestid}.htm#position';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*8;
		document.pageForm.action = '/v-contest-${f_unitid}-${contestInfo.contestid}.htm#position';
		document.pageForm.submit();
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchContest();
  	}
}
function searchContest(){
	document.pageForm.action = '/v-contest-${f_unitid}-${contestInfo.contestid}.htm#position';
	document.pageForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<div id="ds_banner">
  <div id="full-screen-slider">
	<ul id="slides">
		<%
		List adlist = (List)request.getAttribute("adlist");
		VodImageInfo vii = null;
		for(int i=0, size=adlist.size(); i<size; i++){
			vii = (VodImageInfo)adlist.get(i);
		%>
		<li style="background:url('/upload_dir/<%=vii.getSketch() %>') no-repeat center top #e0f2ff"><%if(vii.getUrl() != null && !"".equals(vii.getUrl())){ %><a href="<%=vii.getUrl() %>" target="_blank" title="<%=vii.getTitle() %>"></a><%} %></li>
		<%} %>
	</ul>
</div>
</div>
<a name="position"></a>
<form name="pageForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <h2>大赛微课</h2>
  <dl class="dl_tab">
    <dt>奖项：</dt>
    <dd>
      <%
      String rank = (String)request.getAttribute("rank");
      String[] ranknames = Constants.getCodeTypename("rank");
      String[] rankids = Constants.getCodeTypeid("rank");
      %>
      <a href="javascript:gotoUrl('0', '', '')" <%if("0".equals(rank)){ %>class="curr_all"<%} %>>全部</a>
      <%
      for(int i=0, size=ranknames.length; i<size; i++){
      if(!"0".equals(rankids[i])){
      %>
	  <a href="javascript:gotoUrl('<%=rankids[i] %>', '', '')" <%if(rankids[i].equals(rank)){ %>class="curr_all"<%} %>><%=ranknames[i] %></a>
	  <%}} %>
    </dd>
  </dl>
  <dl class="dl_tab" style="border-bottom:none">
    <dt>类型：</dt>
    <dd>
      <%
      String unittype = (String)request.getAttribute("unittype");
      String[] unittypenames = Constants.getCodeTypename("unittype");
      String[] unittypeids = Constants.getCodeTypeid("unittype");
      %>
      <a href="javascript:gotoUrl('', '0', '')" <%if("0".equals(unittype)){ %>class="curr_all"<%} %>>全部</a>
      <%
      for(int i=0, size=unittypenames.length; i<size; i++){
      %>
	  <a href="javascript:gotoUrl('', '<%=unittypeids[i] %>', '')" <%if(unittypeids[i].equals(unittype)){ %>class="curr_all"<%} %>><%=unittypenames[i] %></a>
	  <%} %>
    </dd>
  </dl>
  <div class="list clearfix">
    <div class="seach_tab_1">
    	<%
        String orderbytype = (String)request.getAttribute("orderbytype");
        %>
        <ul>
          <li><a href="javascript:gotoUrl('', '', '1')" <%if("1".equals(orderbytype)){ %>class="link0"<%} %>>最新</a></li>
          <li><a href="javascript:gotoUrl('', '', '2')" <%if("2".equals(orderbytype)){ %>class="link1"<%} %>>评分</a></li>
          <li><a href="javascript:gotoUrl('', '', '3')" <%if("3".equals(orderbytype)){ %>class="link1"<%} %>>浏览量</a></li>
        </ul>
        <div class="seach2">
          <input type="text" placeholder="搜索课程" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
          <a href="javascript:searchContest()"></a>
        </div>
        <%@ include file="page1.jsp"%>
      </div>
    <div class="list_bottom1 clearfix">
    	<%
        PageList pagelist = (PageList)request.getAttribute("pagelist");
      	List list = pagelist.getDatalist();
      	if(list != null && list.size() > 0){
      		Object[] object = null;
          	String author = null;
          	for(int i=0, size=list.size(); i<size; i++){
          		object = (Object[])list.get(i);
          		author = (String)object[2];
          		if(author.length() > 4) author = author.substring(0, 4);
      	%>
        <%if(i%4 == 0){ %>
        <ul class="h_pic3">
        <%} %>
          <li class="l_pic1" <%if(i==3 || i==7){ %>style="margin-right:0"<%} %>>
            <div class="mod-course-card h180">
              <a href="/v-play-<%=object[6] %>-<%=object[0] %>.htm" target="_blank" class="mod-course-card__link-img">
                <%if(object[3].toString().startsWith("http://")){ %><img src="<%=object[3] %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%}else{ %><img src="/upload_dir/<%=object[3] %>" width="220" height="124" onerror="this.src='/upload_dir/xueda.jpg'"/><%} %>
                <i class="icon-card-play"></i>
              </a>
              <div class="course-title">
                <a title="<%=object[1] %>" class="mod-course-card__name" target="_blank" href="/v-play-<%=object[6] %>-<%=object[0] %>.htm"><%=object[1] %></a> 
              </div>
              <p class="mod-course-card__line">
                <a title="<%=object[2] %>" target="_blank" href="/v.bo?method=actors&filmid=<%=object[0] %>&actors=<%=URLEncoder.encode(object[2].toString().trim(),"utf-8") %>" class="mod-course-card__price mod-course-card__price_free"><%=author %></a> 
                <a title="<%=object[7] %>" class="mod-course-card__agency" target="_blank" href="/html/<%=object[6] %>.htm"><%=object[7] %></a>
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
</div>
</div>
<input type="hidden" name="startcount" id="startcount" value=''>
<input type="hidden" name="rank" id="rank" value="<bean:write name="rank"/>"/>
<input type="hidden" name="unittype" id="unittype" value="<bean:write name="unittype"/>"/>
<input type="hidden" name="orderbytype" id="orderbytype" value="<bean:write name="orderbytype"/>"/>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>