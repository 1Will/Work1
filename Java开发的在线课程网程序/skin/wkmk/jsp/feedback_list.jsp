<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.util.string.encode.Encode"%>
<%@page import="com.bzt.gpw.bo.GpwFeedbackInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	searchFeedback();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*5;
		searchFeedback();
	}
}
function checkValue(){
	var keywords = document.getElementById("keywords");
	if(keywords.value == "搜索意见反馈内容"){
		keywords.value = "";
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchFeedback();
  	}
}
function searchFeedback(){
	var keywords = document.getElementById("keywords");
	if(keywords.value == "搜索意见反馈内容"){
		keywords.value = "";
	}
	document.pageForm.action = '/v.bo?method=feedbackf';
	document.pageForm.submit();
}
</script>
</head>

<body>
<form name="pageForm" method="post">
<div class="view_right_02">
	<div class="five">
		<%
		String keywords = Encode.nullToBlank(request.getAttribute("keywords"));
		if("".equals(keywords)) keywords = "搜索意见反馈内容";
		%>
		<h3>全部反馈</h3>
		<span class="view_01"><input name="keywords" id="keywords" type="text" value="<%=keywords %>" class="view_02" style="padding-left:8px;color:#666;" onfocus="checkValue()" onKeyPress="return search_onkeypress1(event)"/><span class="view_03"><a href="javascript:searchFeedback()"><img src="/skin/wkmk/images/f06.gif" border="0" /></a></span></span>
	</div>
	<!--five-->
	<div class="six" style="padding-bottom:10px">
	<%
	PageList pagelist = (PageList)request.getAttribute("pagelist");
	List dataList = pagelist.getDatalist();
	GpwFeedbackInfo gfi = null;
	for(int i=0, size=dataList.size(); i<size; i++){
		gfi = (GpwFeedbackInfo)dataList.get(i);
	%>
	<dl>
	<dt><%=gfi.getContent() %></dt>
	<dd><span class="view_04"><%=gfi.getCreatedate().substring(0, 10) %> 提出</span></dd>
	<%if("1".equals(gfi.getIsreply()) && !"".equals(gfi.getReplycontent())){ %>
	<dd id="view_06"><span class="view_05">微课慕课产品经理:</span><%=gfi.getReplycontent() %></dd>
	<%} %>
	</dl>
	<%} %>
	</div>
	<logic:greaterThan value="1" name="pagelist" property="totalPages">
	<div class="page">
        <%@ include file="page.jsp"%>
    </div>
    </logic:greaterThan>
</div>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
</body>
</html>
