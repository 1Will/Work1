<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.bzt.edu.bo.EduSubjectVersion"%>
<script language="javascript">
 var retValue = new Array();
 
 <%
 	EduSubjectVersion model=(EduSubjectVersion)request.getAttribute("model");
 %>
 	retValue[0]='<%=model.getName()%>';//名称
	retValue[1]='<%=model.getId() %>';//id
 
 window.parent.window.returnValue=retValue;
 window.parent.window.close();
</script>
