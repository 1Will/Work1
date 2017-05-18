<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String openid = (String)request.getAttribute("openid");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>

<script src="<%=basePath%>/js/zepto.min.js"></script>
<script src="<%=basePath%>/js/router.min.js"></script>
<script src="<%=basePath%>/js/example.js"></script>
</head>
<div class="container" id="container"></div>
	  
<div class="hd">
    <h1 class="page_title" style="margin-bottom:10%">欢迎注册</h1>
    <span class="page_desc">&nbsp;&nbsp;永君的主要软件产品包括在线教育平台，<br/>英文的名字是e-Learning（电子学习）；
	</span><span class="page_desc">
	产品可以在企业，开发区，行业协会，创业领域等领域应用；<br/>产品可以延展的应用场景很多，学生教育，培训机构，考证培训，老年教育，职业培训，大学，农业，成人教育.
	</span>
	<br/><span class="page_desc">
	能想象的一些跟教育，授课，传播相关的领域都有应用这个平台软件的可能性；</span>
</div>

<div class="bd spacing" style="margin-top:15%">
	<span class="page_desc" style="margin-left:0;">(已有平台用户，将微信与平台进行绑定)</span>
    <a href="<%=basePath%>/register/binding.jsp?openid=<%=openid %>" class="weui_btn weui_btn_primary">用户绑定</a>
    <a style="margin-top:10%" href="<%=basePath%>/register/registerNew.jsp?openid=<%=openid %>" class="weui_btn weui_btn_primary">注册新用户</a>
</div>
</body>
</html>