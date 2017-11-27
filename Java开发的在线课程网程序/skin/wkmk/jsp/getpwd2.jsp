<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-找回密码发送邮件</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function gotoLogin(){
	var topurl = "/index.html";
	window.top.location = '/plogin.do?method=slogin&redirecturl=' + topurl;
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <h2>找回密码</h2>
  <div class="list clearfix">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="float1 mar_7">
	  <tr>
	    <td align="center" valign="middle">
	      <table width="50%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="50" align="center">
		      <img src="/skin/wkmk/images/email_ico.png" />
		    </td>
		  </tr>
		  <tr>
		    <td align="center" style="line-height:30px;"><p>密码重设链接邮件已经发送到您的邮箱</p>
		      <p>${email }</p>
		      <p>请注意查收并重新设置密码</p>
		      <%
		      String email = (String)request.getAttribute("email");
		      if(email != null && (email.indexOf("@vip") != -1 || email.endsWith("@163.com") || email.endsWith("@126.com") || email.endsWith("@yeah.net") || email.indexOf("@sina") != -1  || email.endsWith("@sohu.com") || email.endsWith("@qq.com"))){
		      	  String emailtemp = email.substring(email.indexOf("@") + 1);
		      %>
		      <p><a href="http://mail.<%=emailtemp %>" class="color_6">查看邮件&gt;&gt;</a></p>
		      <%} %>
		    </td>
		  </tr>
		  <tr>
		    <td height="100" align="center">
		      <input class="sub_1" type="button" value="返回登录" onclick="gotoLogin()" />
		    </td>
		  </tr>
		</table>
	    </td>
	  </tr>
	</table>
  </div>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>