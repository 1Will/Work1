<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduColumnInfo"%>
<%@ page contentType = "text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<base target="_self"/>
<style type="text/css">
*{padding:0px; margin:0px;}
.danyuan{ margin:10px; width:98%;}
.danyuan .title2 a{ padding-left:0px; font-size:16px; font-weight:bold; height:30px; line-height:30px; margin:15px 0px 5px 0px;color:#000;text-decoration:none;}
.danyuan .title2 a:hover{ text-decoration:none; color:#666;}
.danyuan .title a{ padding-left:8px; font-size:14px; font-weight:bold; height:20px; line-height:20px; margin:15px 0px 5px 0px;text-decoration:none;color:#000;}
.danyuan .title a:hover{ text-decoration:none; color:#666;}
.danyuan .content a{background:url(/public/images/edu/arrow.gif) no-repeat left 3px; height:30px; line-height:30px; border-bottom:1px dashed #ccc; padding-left:15px;font-size:12px; font-weight:normal; text-decoration:none; color:#000; margin-right:10px;}
.danyuan .content a:hover{ font-size:12px; font-weight:normal; text-decoration:none; color:#666;}
</style>
</head>
<script type="text/javascript">
function toOnclick(columnid){
	document.pageForm.action = '/eduSelectAction.do?method=selectDeal&tag=column&objid=' + columnid;
	document.pageForm.submit();
}
</script>
<body>
<form name="pageForm" method=post>
<div class="danyuan">
	<%
        List parentList = (List)request.getAttribute("parentList");
        Map keMap = (Map)request.getAttribute("keMap");
        Map jieMap = (Map)request.getAttribute("jieMap");
        boolean iske = (Boolean)request.getAttribute("iske");
        
        EduColumnInfo parent = null;
        EduColumnInfo ke = null;
        EduColumnInfo model = null;
        List childList = null;
        List childList0 = null;
        if(iske){
    	for(int i=0, size=parentList.size(); i<size; i++){
    		parent = (EduColumnInfo)parentList.get(i);
    %>
		<div class="title2"><a href="javascript:toOnclick('<%=parent.getColumnid() %>')"><%=parent.getColumnname() %></a></div>
		<%
			childList = (List)keMap.get(parent.getVersionid() + parent.getColumnno());
			if(childList != null && childList.size() > 0){
			for(int m=0; m<childList.size(); m++){
				ke = (EduColumnInfo)childList.get(m);
		%>
		<div class="title"><img src="/public/images/edu/btn_right.gif" style="margin-right:2px;"/><a href="javascript:toOnclick('<%=ke.getColumnid() %>')"><%=ke.getColumnname() %></a></div>
		<%
			childList0 = (List)jieMap.get(ke.getVersionid() + ke.getColumnno());
			if(childList0 != null && childList0.size() > 0){
			for(int k=0; k<childList0.size(); k++){
				model = (EduColumnInfo)childList0.get(k);
		%>
		<%if(k == 0){ %>
    	<div class="content">
    	<%} %>
    	<a href="javascript:toOnclick('<%=model.getColumnid() %>')"><%=model.getColumnname() %></a>
    	<%if(k == childList0.size() - 1){ %>
    	</div>
    	<%} %>
    	<%}} %>
    	<%}} %>
    	<%} %>
    <%
        }else{
        for(int i=0, size=parentList.size(); i<size; i++){
    		parent = (EduColumnInfo)parentList.get(i);
    %>
		<div class="title2"><a href="javascript:toOnclick('<%=parent.getColumnid() %>')"><%=parent.getColumnname() %></a></div>
		<%
			childList = (List)keMap.get(parent.getVersionid() + parent.getColumnno());
			if(childList != null && childList.size() > 0){
			for(int k=0; k<childList.size(); k++){
				model = (EduColumnInfo)childList.get(k);
		%>
		<%if(k == 0){ %>
    	<div class="content">
    	<%} %>
    	<a href="javascript:toOnclick('<%=model.getColumnid() %>')"><%=model.getColumnname() %></a>
    	<%if(k == childList.size() - 1){ %>
    	</div>
    	<%} %>
    	<%}} %>
    	<%} %>
    <%
        }
    %>
		
</div>
</form>
</body>
</html>