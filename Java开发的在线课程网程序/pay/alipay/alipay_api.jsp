<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bzt.pay.alipay.util.*"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>支付宝纯担保交易接口</title>
</head>
<%
    Map<String, String> sParaTemp = (Map)request.getAttribute("sParaTemp");
	//建立请求
	String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
	System.out.print(sHtmlText);
	out.println(sHtmlText);
%>
<body>
</body>
</html>
