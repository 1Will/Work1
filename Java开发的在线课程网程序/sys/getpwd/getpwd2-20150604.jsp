<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码</title>
<link href="/public/css/register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function relogin(){
	document.location = '/index.jsp';
}

function getpwd(){
	var pwdanswer = document.getElementById('pwdanswer').value;
	if(pwdanswer == ''){
		document.getElementById('getpwd1').style.display = '';
		return false;
	}
	
	document.getpwdForm.action = '/getpwd.do?method=getpwd3';
	document.getpwdForm.submit();
}

</script>
</head>

<body>
<div id="top">
<%
if("v002".equals(Constants.getDefaultTemplate())){
%>
<span style="padding-left:120px; float:left;"><img src="/public/images/login/logo.jpg" /></span>
<%}else{ %>
<span style="padding-left:120px; float:left;"><img src="/public/images/login/Elogo.jpg" /></span>
<%} %>
<span style="float:right; padding-right:120px; padding-top:10px;"><a href="/index.jsp">首页</a> | <a href="http://www.edutech.com.cn" target="_blank">帮助</a></span>
</div><!--#top-->
<form name="getpwdForm" method="post">
<div id="center"> 
	<div id="list_top">
	  <span style="border-top:#8bb8d5 1px solid; background-color:#FFFFFF; line-height:1px;*line-height:4px; float:left; width:740px;">&nbsp;
	  <span style="width:5px; position:absolute; left:0px; top:0px;"><img src="/public/images/register/z16.jpg" /></span>
	  <span style="width:5px; position:absolute; right:0px; top:0px;"><img src="/public/images/register/z15.jpg" /></span>
	  </span>  
	</div><!--#list_top-->
	<div id="list">
		<div id="list02">
		<h3 style="background:url(/public/images/register/z01.jpg) no-repeat 10px 1px;">找回密码：第二步</h3>
		<ul>
			<li>
				<dt>&nbsp;</dt>
				<dd style="width:200px;color:red;">
				<logic:present name="getpwderror">
				<bean:write name="getpwderror"/>
				</logic:present>
				</dd>
			</li>
			<li>
				<dt>密码问题：</dt>
				<dd class="show"><bean:write name="userInfo" property="pwdquestion"/></dd>
			</li>
			<li>
				<dt>问题答案：</dt>
				<dd style="width:163px;"><input name="pwdanswer" id="pwdanswer" type="text" value=""  style="width:153px; height:24px; background:url(/public/images/register/z19.jpg) no-repeat; line-height:24px; padding-left:5px; border:none;" />
				</dd>
				<dd class="two" id="getpwd1" style="display:none;">请填写密码保护问题的答案！</dd>
			</li>
		</ul>
		<input type="hidden" name="loginname" value="<bean:write name="userInfo" property="loginname"/>"/>
		</div><!--#list02-->
		
		<div id="list04" style="margin-top:20px;">
		<ul>
			<span style="width:668px; text-align:center;"><a style="cursor:pointer;" onclick="getpwd()"><img src="/public/images/register/z12.jpg" border="0" /></a>
			<a style="cursor:pointer;" onclick="relogin()"><img src="/public/images/register/z13.jpg" border="0" /></a>
			</span>
		</ul>
		</div><!--#list04-->
	
	</div><!--#list-->

	<div id="list_bottom" style=" float:left;">
	  <span style="border-bottom:#8bb8d5 1px solid; background-color:#FFFFFF; line-height:1px;*line-height:3px; float:left; width:740px;">&nbsp;
	  <span style="width:5px; position:absolute; left:0px; bottom:0px;*bottom:0px;"><img src="/public/images/register/z17.jpg" /></span>
	  <span style="width:5px; position:absolute; right:0px; bottom:0px;*bottom:0px;"><img src="/public/images/register/z18.jpg" /></span>
	  </span>  
	</div><!--#list_bottom-->
</div><!--#center-->
</form>
<%
if("v002".equals(Constants.getDefaultTemplate())){
%>
<%@ include file="/sys/admin/footer.jsp"%>
<%}else{ %>
<%@ include file="/sys/admin/Efooter.jsp"%>
<%} %>

</body>
</html:html>
