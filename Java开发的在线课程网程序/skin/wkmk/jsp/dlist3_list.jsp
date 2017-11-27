<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduVersionInfo"%>
<%@page import="com.bzt.edu.bo.EduGradeInfo"%>
<%@page import="com.bzt.edu.bo.EduSubjectInfo"%>
<%@page import="com.bzt.edu.bo.EduXueduanInfo"%>
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/beikestyle.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v.bo?method=dlist3f';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*5;
		document.pageForm.action = '/v.bo?method=dlist3f';
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
	document.getElementById("pageFormSearchButton").value = "1";
	document.pageForm.action = '/v.bo?method=dlist3f';
	document.pageForm.submit();
}
function gotoUrl(orderbytype){
	document.getElementById("orderbytype").value = orderbytype;
	document.pageForm.action = '/v.bo?method=dlist3f';
	document.pageForm.submit();
}
</script>
</head>

<body>
<form name="pageForm" method="post">
<div class="list_left mar_2">
  <div class="seach_tab">
    <%
    String orderbytype = (String)request.getAttribute("orderbytype");
    %>
    <ul>
      <li><a href="javascript:gotoUrl('1')" <%if("1".equals(orderbytype)){ %>class="link0"<%} %>>最新</a></li>
      <li><a href="javascript:gotoUrl('2')" <%if("2".equals(orderbytype)){ %>class="link1"<%} %>>评分</a></li>
      <li><a href="javascript:gotoUrl('3')" <%if("3".equals(orderbytype)){ %>class="link1"<%} %>>浏览量</a></li>
      <li><a href="javascript:gotoUrl('4')" <%if("4".equals(orderbytype)){ %>class="link1"<%} %>>收藏量</a></li>
      <li><a href="javascript:gotoUrl('5')" <%if("5".equals(orderbytype)){ %>class="link1"<%} %>>下载量</a></li>
      <li><a href="javascript:gotoUrl('6')" <%if("6".equals(orderbytype)){ %>class="link1"<%} %>>页数</a></li>
      <li><a href="javascript:gotoUrl('9')" <%if("9".equals(orderbytype)){ %>class="link0"<%} %>>精品</a></li>
    </ul>
    <div class="seach2">
      <input type="text" placeholder="搜索文档" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
      <a href="javascript:searchDoc()"></a>
    </div>
    <%@ include file="page1.jsp"%>
  </div>
  <div class="list_bottom clearfix">
    <%
    PageList pagelist = (PageList)request.getAttribute("pagelist");
  	List list = pagelist.getDatalist();
  	if(list != null && list.size() > 0){
  	Object[] object = null;
  	for(int i=0, size=list.size(); i<size; i++){
  		object = (Object[])list.get(i);
  	%>
    <div class="text_bottom">
      <dl class="dl_1">
        <dt><a href="/v-doc-<%=object[13] %>-<%=object[0] %>.htm" target="_blank"><img src="<%=object[4] %>" onerror="this.src='/upload_dir/default.jpg'"/></a></dt>
        <dd class="ico_<%=object[12] %> dd_1"><a href="/v-doc-<%=object[13] %>-<%=object[0] %>.htm" target="_blank" title="<%=object[1] %>"><%=SubStringDirectiveModel.subString(object[1].toString(), 33, "...") %></a></dd>
        <dd><a href="/v-user-<%=object[13] %>-<%=object[2] %>.htm" target="_blank" class="color_2"><%=object[3] %></a> | <a href="/html/<%=object[13] %>.htm" target="_blank" class="color_3"><%=object[14] %></a></dd>
        <dd>
          <span>浏览<strong class="color_5" style="font-weight:normal;"><%=object[8] %></strong></span>
          <span>收藏<strong class="color_5" style="font-weight:normal;"><%=object[7] %></strong></span>
          <span>下载量<strong class="color_5" style="font-weight:normal;"><%=object[9] %></strong></span>
          <span>页数<strong class="color_5" style="font-weight:normal;"><%=object[10] %></strong></span>
          <span>上传时间：<%=object[11].toString().substring(0, 10) %></span>
        </dd>
      </dl>
      <div class="right_box">
        <ul>
          <li><%=object[6] %>人评</li>
          <li>
            <%=StarUtil.getStarImage(Float.valueOf(object[5].toString())) %>
            <span class="font_1 mar_1"><%=object[5] %>分</span>
          </li>
        </ul>
      </div>
    </div>
    <%}}else{ %>
    <div class="text_bottom">
      <dl class="dl_1">
      <dd>抱歉，没有找到您需要的数据！</dd>
      </dl>
    </div>
    <%} %>
  </div>
  <logic:greaterThan value="1" name="pagelist" property="totalPages">
  <div class="page">
    <%@ include file="page.jsp"%>
  </div>
  </logic:greaterThan>
</div>

<input type="hidden" name="version" id="version" value='${version }'>
<input type="hidden" name="column" id="column" value="${column }"/>
<input type="hidden" name="orderbytype" id="orderbytype" value='${orderbytype }'>
<input type="hidden" name="searchButton" id="pageFormSearchButton" value=""/>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
</body>
</html>