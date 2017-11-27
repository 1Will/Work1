<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册成功!</title>
<link href="/public/css/register.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="top">
<span style="padding-left:140px; float:left;"><img src="/public/images/register/logo.jpg" /></span>
<span style="float:right; padding-right:120px; padding-top:10px;"><a href="/index.html">首页</a> | <a href="http://www.edutech.com.cn" target="_blank">帮助</a></span>
</div><!--#top-->
<form name="getpwdForm" method="post">
<div id="center"> 
	<div id="list_top">
	  <span style="border-top:#8bb8d5 1px solid; background-color:#FFFFFF; line-height:1px;*line-height:4px; float:left; width:740px;">&nbsp;
	  <span style="width:5px; position:absolute; left:0px; top:0px;"><img src="/public/images/register/z16.jpg" /></span>
	  <span style="width:5px; position:absolute; right:0px; top:0px;"><img src="/public/images/register/z15.jpg" /></span>
	  </span>
	</div><!--#list_top-->
	<div id="list" style="text-align:center;font-size:18px;color:green;font-weight:bold;height:100px;padding-top:50px;">
		恭喜您注册成功，请等待管理员审核!
	</div><!--#list-->



	<div id="list_bottom" style=" float:left;">
	  <span style="border-bottom:#8bb8d5 1px solid; background-color:#FFFFFF; line-height:1px;*line-height:3px; float:left; width:740px;">&nbsp;
	  <span style="width:5px; position:absolute; left:0px; bottom:0px;*bottom:0px;"><img src="/public/images/register/z17.jpg" /></span>
	  <span style="width:5px; position:absolute; right:0px; bottom:0px;*bottom:0px;"><img src="/public/images/register/z18.jpg" /></span>
	  </span>  
	</div><!--#list_bottom-->
</div><!--#center-->
</form>
<%@ include file="/sys/comm/footer.jsp"%>

</body>
</html:html>
