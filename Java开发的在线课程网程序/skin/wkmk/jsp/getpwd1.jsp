<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-找回密码</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function getpwd(){
	var loginname = document.getElementById("loginname").value;
	if(loginname == ""){
		document.getElementById("loginname_tip").innerHTML = "&nbsp;&nbsp;请输入用户名！";
		document.getElementById("loginname_tip").style.display = "";
		return;
	}else{
		document.getElementById("loginname_tip").style.display = "none";
	}
	var email = document.getElementById("email").value;
	if(email == ""){
		document.getElementById("email_tip").innerHTML = "&nbsp;&nbsp;请输入注册邮箱！";
		document.getElementById("email_tip").style.display = "";
		return;
	}else{
		document.getElementById("email_tip").style.display = "none";
	}
	var validate = document.getElementById("validate").value;
	if(validate == ""){
		document.getElementById("validate_tip").innerHTML = "&nbsp;&nbsp;请输入验证码！";
		document.getElementById("validate_tip").style.display = "";
		return;
	}else{
		document.getElementById("validate_tip").style.display = "none";
	}
	
	document.getpwdForm.action = "/getpwd.do?method=find";
	document.getpwdForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<form name="getpwdForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <h2>找回密码</h2>
  <div class="list clearfix">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="float1 mar_7">
	  <tr>
	    <td align="center" valign="middle">
	      <table width="50%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="50" style="font-size:18px">通过注册邮箱链接重设密码</td>
		  </tr>
		  <tr>
		    <td>
		      <div class="pas_input">
		        <label class="user_1"></label>
		        <input type="text" name="loginname" id="loginname" value="${loginname }" class="user_input1" placeholder="请输入用户名" />
		      </div>
		      <div style="color:#ff3333;margin-top:20px;<logic:notPresent name="loginname_error">display:none;</logic:notPresent>" id="loginname_tip">&nbsp;&nbsp;${loginname_error }</div>
		    </td>
		  </tr>
		  <tr>
		    <td>
		      <div class="pas_input">
		        <label class="email_1"></label>
		        <input type="text" name="email" id="email" value="${email }" class="user_input1" placeholder="请输入注册邮箱" />
		      </div>
		      <div style="color:#ff3333;margin-top:20px;<logic:notPresent name="email_error">display:none;</logic:notPresent>" id="email_tip">&nbsp;&nbsp;${email_error }</div>
		    </td>
		  </tr>
		  <tr>
		    <td>
		      <input type="text" name="validate" id="validate" value="" maxlength="4" class="user_input2" placeholder="请输入验证码" />
		      <img src="/sys/admin/code.jsp" width="79" height="37" />
		      <span style="color:#ff3333;<logic:notPresent name="code_error">display:none;</logic:notPresent>" id="validate_tip">&nbsp;&nbsp;${code_error }</span>
		    </td>
		  </tr>
		  <tr>
		    <td height="100">
		      <input class="sub_1" type="button" value="提交" onclick="getpwd()"/>
		    </td>
		  </tr>
		</table>	
	    </td>
	  </tr>
	</table>
  </div>
</div>
</div>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>
