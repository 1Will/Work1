<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.edu.bo.EduGradeInfo"%>
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
.kecheng li{ width:48%; margin:2px; float:left; height:38px; display:inline;}
.kecheng li a{width:98%; border:1px solid #3f6276;text-align:center; height:36px; line-height:36px; background:#b9dbf9; display:block; color:#3f5d75; text-decoration:none; font-size:14px; font-weight:normal;}
.kecheng li a:hover{ color:#000; background:#fff;font-size:14px; font-weight:normal;}
.clear{clear:both; font-size:0px; height:0px;}
</style>
<script type="text/javascript">
function toOnclick(gradeid){
	document.pageForm.action = '/eduSelectAction.do?method=selectDeal&tag=grade&objid=' + gradeid;
	document.pageForm.submit();
}
function toAll(){
	window.location.href = "/eduSelectAction.do?method=selectGrade&isAll=1&subjectid=${subjectid}";
}
</script>
</head>

<body>
<FORM name="pageForm" method=post>
<ul class="kecheng">
	<%
	String userGrade = (String)request.getAttribute("userGrade");//1表示是加载当前学科所有年级
	List list = (List)request.getAttribute("gradeList");
	if(list != null && list.size() > 0){
	EduGradeInfo egi = null;
	for(int i=0, size=list.size(); i<size; i++){
		egi = (EduGradeInfo)list.get(i);
	%>
	<li><a href="javascript:toOnclick('<%=egi.getGradeid() %>')" title='<%=egi.getGradename() %>'><%=egi.getGradename()%></a></li>
    <%}%>
	<%if("1".equals(userGrade)){ %>
	<li style="width:98%;text-align:center;"><a href="javascript:toAll()" title="在修改个人信息选择了教学设置时，将会根据个人设置的教学设置（学科-年级-版本）自动关联，如果没有设置则显示当前单位的当前学科所有年级。">加载全部年级</a></li>
	<%} %>
	<%}else{ %>
    <li style="width:280px;text-align:center;font-size:38px;color:green;">暂无数据！</li>
    <%} %>
    <div class="clear"></div>
</ul>
</FORM>
</body>
</html>