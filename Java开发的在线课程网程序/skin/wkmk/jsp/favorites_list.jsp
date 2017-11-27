<%@ page pageEncoding="UTF-8" contentType="text/xml; charset=UTF-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.addHeader("Cache-Control", "must-revalidate");
	out.clear();
%>
${buffer}
	
