<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduSubjectInfo"%>
<%@page import="com.bzt.edu.bo.EduXueduanInfo"%>
<%@page import="com.bzt.sys.util.StarUtil"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.bzt.gpw.bo.GpwSearchHots"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.vod.bo.VodFilmType"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-文档</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function gotoUrl(xueduan, cxueduan, subject, grade, orderbytype){
	document.pageForm.action = '/v-dlist-x' + xueduan + '-cx' + cxueduan + '-s' + subject + '-g' + grade + '-' + orderbytype + '.htm';
	document.pageForm.submit();
}
function gotoUrl1(xueduan, orderbytype){
	document.pageForm.action = '/v-dlist1-x' + xueduan + '-cx0-s0-g0-v0-c0-' + orderbytype + '.htm';
	document.pageForm.submit();
}
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-dlist-x${xueduan}-cx${cxueduan}-s${subject}-g${grade}-${orderbytype}.htm';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*8;
		document.pageForm.action = '/v-dlist-x${xueduan}-cx${cxueduan}-s${subject}-g${grade}-${orderbytype}.htm';
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
	document.pageForm.action = '/v-dlist-x${xueduan}-cx${cxueduan}-s${subject}-g${grade}-${orderbytype}.htm';
	document.pageForm.submit();
}
function hotSearch(keywords){
	document.getElementById('keywords').value = keywords;
	document.pageForm.action = '/v-dlist-x${xueduan}-cx${cxueduan}-s${subject}-g${grade}-${orderbytype}.htm';
	document.pageForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<form name="pageForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <h2>全部文档</h2>
  <%
  String xueduan = (String)request.getAttribute("xueduan");
  String cxueduan = (String)request.getAttribute("cxueduan");
  String subject = (String)request.getAttribute("subject");
  String grade = (String)request.getAttribute("grade");
  List subjectList = (List)request.getAttribute("subjectList");
  List gradeList = (List)request.getAttribute("gradeList");
  %>
  <dl class="dl_tab">
    <dt>学段：</dt>
    <dd>
      <a href="javascript:gotoUrl('0', '0', '0', '0', '${orderbytype }')" <%if("0".equals(xueduan)){ %>class="curr_all"<%} %>>全部</a>
      <a href="javascript:gotoUrl1('1', '${orderbytype }')" <%if("1".equals(xueduan)){ %>class="curr_all"<%} %>>小学教育</a>
      <a href="javascript:gotoUrl1('2', '${orderbytype }')" <%if("2".equals(xueduan)){ %>class="curr_all"<%} %>>初中教育</a>
      <a href="javascript:gotoUrl1('3', '${orderbytype }')" <%if("3".equals(xueduan)){ %>class="curr_all"<%} %>>高中教育</a>
      <a href="javascript:gotoUrl('4', '41', '0', '0', '${orderbytype }')" <%if("41".equals(cxueduan)){ %>class="curr_all"<%} %>>中职教育</a>
      <a href="javascript:gotoUrl('4', '42', '0', '0', '${orderbytype }')" <%if("42".equals(cxueduan)){ %>class="curr_all"<%} %>>高职教育</a>
      <a href="javascript:gotoUrl('5', '0', '0', '0', '${orderbytype }')" <%if("5".equals(xueduan)){ %>class="curr_all"<%} %>>高等教育</a>
      <a href="javascript:gotoUrl('6', '0', '0', '0', '${orderbytype }')" <%if("6".equals(xueduan)){ %>class="curr_all"<%} %>>继续教育</a>
    </dd>
  </dl>
  <%
  if(!"0".equals(xueduan)){
  	if("1".equals(xueduan) || "2".equals(xueduan) || "3".equals(xueduan)){
  %>
  <dl class="dl_tab">
    <dt>年级：</dt>
    <dd>
      <a href="javascript:gotoUrl('${xueduan }', '0', '0', '0', '${orderbytype }')" <%if("0".equals(cxueduan)){ %>class="curr_all"<%} %>>全部</a>
      <%
        List cxueduanList = (List)request.getAttribute("cxueduanList");
        int xueduanid = Integer.valueOf(xueduan) + 1;
		EduXueduanInfo exi = null;
		for(int i=0, size=cxueduanList.size(); i<size; i++){
			exi = (EduXueduanInfo)cxueduanList.get(i);
	  %>
	  <a href="javascript:gotoUrl('${xueduan }','<%=exi.getXueduanid() %>', '0', '0', '${orderbytype }')" <%if(exi.getXueduanid().toString().equals(cxueduan)){ %>class="curr_all"<%} %>><%=exi.getName() %></a>
	  <%} %>
    </dd>
  </dl>
  <dl class="dl_tab">
    <dt>学科：</dt>
    <dd>
      <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '0', '0', '${orderbytype }')" <%if("0".equals(subject)){ %>class="curr_all"<%} %>>全部</a>
      <%
      EduSubjectInfo esi = null;
      for(int i=0, size=subjectList.size(); i<size; i++){
    	  esi = (EduSubjectInfo)subjectList.get(i);
	  %>
	  <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '<%=esi.getSubjectid() %>', '0', '${orderbytype }')" <%if(esi.getSubjectid().toString().equals(subject)){ %>class="curr_all"<%} %>><%=esi.getSubjectname() %></a>
	  <%} %>
    </dd>
  </dl>
  <%}else{ %>
  <dl class="dl_tab">
    <dt>分类：</dt>
    <dd>
      <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '0', '0', '${orderbytype }')" <%if("0".equals(subject)){ %>class="curr_all"<%} %>>全部</a>
      <%
      VodFilmType vft = null;
      for(int i=0, size=subjectList.size(); i<size; i++){
      	vft = (VodFilmType)subjectList.get(i);
	  %>
	  <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '<%=vft.getTypeid() %>', '0', '${orderbytype }')" <%if(vft.getTypeid().toString().equals(subject)){ %>class="curr_all"<%} %>><%=vft.getTypename() %></a>
	  <%} %>
    </dd>
  </dl>
  <%if(gradeList != null && gradeList.size() > 0){ %>
  <dl class="dl_tab">
    <dt>专业：</dt>
    <dd>
      <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '0', '${orderbytype }')" <%if("0".equals(grade)){ %>class="curr_all"<%} %>>全部</a>
      <%
      for(int i=0, size=gradeList.size(); i<size; i++){
      	vft = (VodFilmType)gradeList.get(i);
	  %>
	  <a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '<%=vft.getTypeid() %>', '${orderbytype }')" <%if(vft.getTypeid().toString().equals(grade)){ %>class="curr_all"<%} %>><%=vft.getTypename() %></a>
	  <%} %>
    </dd>
  </dl>
  <%} %>
  <%}} %>
  <div class="list clearfix">
    <div class="list_left">
      <div class="seach_tab">
        <%
        String orderbytype = (String)request.getAttribute("orderbytype");
        %>
        <ul>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '1')" <%if("1".equals(orderbytype)){ %>class="link0"<%} %>>最新</a></li>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '2')" <%if("2".equals(orderbytype)){ %>class="link1"<%} %>>评分</a></li>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '3')" <%if("3".equals(orderbytype)){ %>class="link1"<%} %>>浏览量</a></li>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '4')" <%if("4".equals(orderbytype)){ %>class="link1"<%} %>>收藏量</a></li>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '5')" <%if("5".equals(orderbytype)){ %>class="link1"<%} %>>下载量</a></li>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '6')" <%if("6".equals(orderbytype)){ %>class="link1"<%} %>>页数</a></li>
          <li><a href="javascript:gotoUrl('${xueduan }', '${cxueduan }', '${subject }', '${grade }', '9')" <%if("9".equals(orderbytype)){ %>class="link0"<%} %>>精品</a></li>
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
    <div class="list_right mar_2">
      <div class="hot_tab">
        <h3>热门搜索</h3>
        <ul>
          <%
          String keywords = (String)request.getAttribute("keywords");
          List hotSearchList = (List)request.getAttribute("hotSearchList");
          GpwSearchHots gsh = null;
          for(int i=0, size=hotSearchList.size(); i<size; i++){
        	  gsh = (GpwSearchHots)hotSearchList.get(i);
          %>
          <li><a href="javascript:hotSearch('<%=gsh.getKeywords() %>')" <%if(gsh.getKeywords().equals(keywords)){ %>class="color_6"<%} %>><%=gsh.getKeywords() %></a></li>
          <%} %>
        </ul>
      </div>
      <div class="like clearfix">
        <h3>猜你喜欢</h3>
        <%
        List guessYouLikeList = (List)request.getAttribute("guessYouLikeList");
        if(guessYouLikeList != null && guessYouLikeList.size() > 0){
        	Object[] object = null;
         	String author = null;
         	for(int i=0, size=guessYouLikeList.size(); i<size; i++){
         		object = (Object[])guessYouLikeList.get(i);
         		author = (String)object[3];
         		if(author.length() > 4) author = author.substring(0, 4);
        %>
        <div class="text_bottom1">
          <dl class="dl_2">
            <dd class="ico_<%=object[12] %> cc_1 pad_2"><a href="/v-doc-<%=object[13] %>-<%=object[0] %>.htm" target="_blank" title="<%=object[1] %>"><%=SubStringDirectiveModel.subString(object[1].toString(), 14, "...") %></a></dd>
            <dd><a href="/v-user-<%=object[13] %>-<%=object[2] %>.htm" target="_blank" class="color_2"><%=author %></a> | <a href="/html/<%=object[13] %>.htm" target="_blank" class="color_3"><%=SubStringDirectiveModel.subString(object[14].toString(), 13, "...") %></a></dd>
            <dd>
              <span>
                <%=StarUtil.getStarImage(Float.valueOf(object[5].toString())) %>
                <span class="font_1"><%=object[5] %>分</span>
              </span>
              <span class="mar_1"><%=object[10] %>页</span>
            </dd>
          </dl>
        </div>
        <%}} %>
      </div>
    </div>
  </div>
</div>
</div>
<input type="hidden" name="searchButton" id="pageFormSearchButton" value=""/>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>