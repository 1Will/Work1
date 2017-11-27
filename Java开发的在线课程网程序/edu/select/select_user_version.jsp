<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduVersionInfo"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base target="_self"/>
<style>
*{padding:0px; margin:0px;background:#dcffff;overflow-x:hidden; }
ul,li{ list-style:none;}
.kecheng{ width:98%;height:auto!important;height:378px;min-height:378px;padding:2px;}
.kecheng li{ width:99%; margin:2px; float:left; height:38px; display:inline;}
.kecheng li a{width:98%; border:1px solid #3f6276;text-align:center; height:36px; line-height:36px; background:#b9dbf9; display:block; color:#3f5d75; text-decoration:none; font-size:14px; font-weight:normal;}
.kecheng li a:hover{ color:#000; background:#fff;font-size:14px; font-weight:normal;}
.clear{clear:both; font-size:0px; height:0px;}
</style>
<script type="text/javascript">
function toOnclick(versionid){
	document.pageForm.action = '/eduSelectAction.do?method=selectDeal&tag=version&objid=' + versionid;
	document.pageForm.submit();
}
</script>
</head>

<body>
<FORM name="pageForm" method=post>
<ul class="kecheng">
	<%
	List list = (List)request.getAttribute("versionList");
	if(list != null && list.size() > 0){
	EduVersionInfo evi = null;
	for(int i=0, size=list.size(); i<size; i++){
		evi = (EduVersionInfo)list.get(i);
	%>
	<li><a href="javascript:toOnclick('<%=evi.getVersionid() %>')" title='<%=evi.getVersionname() %>'><%=evi.getVersionname()%></a></li>
    <%}}else{ %>
    <li style="width:280px;text-align:center;font-size:38px;color:green;">暂无数据！</li>
    <%} %>
    <div class="clear"></div>
</ul>
</FORM>
</body>
</html>