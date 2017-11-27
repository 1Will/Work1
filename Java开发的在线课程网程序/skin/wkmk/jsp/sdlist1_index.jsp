<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="java.net.URLEncoder"%>
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
<title><%=Constants.SYS_PRODUCT_NAME %>-${f_unitinfo.unitname }-学校文档</title>
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
	document.pageForm.action = '/v-sdlist-${f_unitid}-' + orderbytype + '.htm#position';
	document.pageForm.submit();
}
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-sdlist-${f_unitid}-${orderbytype}.htm#position';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*5;
		document.pageForm.action = '/v-sdlist-${f_unitid}-${orderbytype}.htm#position';
		document.pageForm.submit();
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchDoc();
  	}
}
function searchDoc(){
	document.pageForm.action = '/v-sdlist-${f_unitid}-${orderbytype}.htm#position';
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
  <h2>文档</h2>
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
          <li><a href="javascript:gotoUrl('${columnid }', '5')" <%if("5".equals(orderbytype)){ %>class="link1"<%} %>>下载量</a></li>
          <li><a href="javascript:gotoUrl('${columnid }', '6')" <%if("6".equals(orderbytype)){ %>class="link1"<%} %>>页数</a></li>
        </ul>
        <div class="seach2">
          <input type="text" placeholder="搜索文档" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
          <a href="javascript:searchDoc()"></a>
        </div>
        <%@ include file="page1.jsp"%>
      </div>
    <div class="list_bottom1 clearfix">
        <%
        PageList pagelist = (PageList)request.getAttribute("pagelist");
        List list = pagelist.getDatalist();
        if(list != null && list.size() > 0){
        Object[] object = null;
        for(int i=0, size=list.size(); i<size; i++){
            object = (Object[])list.get(i);
        %>
        <div class="de_left mar_7">
          <dl class="dl_wd">
            <dt><a href="/v-doc-<%=object[9] %>-<%=object[0] %>.htm" target="_blank"><img src="<%=object[2] %>" /></a></dt>
            <dd class="ico_<%=object[13] %> dd_1"><a href="/v-doc-<%=object[9] %>-<%=object[0] %>.htm" target="_blank" title="<%=object[1] %>"><%=SubStringDirectiveModel.subString(object[1].toString(), 33, "...") %></a></dd>
            <dd style="padding-left:0px;">
              <a href="/v-user-<%=object[9] %>-<%=object[10] %>.htm" target="_blank" class="color_2"><%=object[11] %></a> | 
              <span>浏览<strong class="color_5" style="font-weight:normal;"><%=object[3] %></strong></span>
              <span>收藏<strong class="color_5" style="font-weight:normal;"><%=object[5] %></strong></span>
              <span>下载量<strong class="color_5" style="font-weight:normal;"><%=object[4] %></strong></span>
              <span>页数<strong class="color_5" style="font-weight:normal;"><%=object[12] %></strong></span>
              <span>上传时间：<%=object[8].toString() %></span>
            </dd>
          </dl>
          <div class="right_box">
            <ul>
              <li><%=object[7] %>人评</li>
              <li style="width:120px;">
                <%=StarUtil.getStarImage(Float.valueOf(object[6].toString())) %>
                <span class="font_1 mar_1"><%=object[6] %>分</span>
              </li>
            </ul>
          </div>
        </div>
        <%if(i < size-1){ %>
        <div class="clearfix" style="border-bottom:1px solid #ccc;"></div>
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