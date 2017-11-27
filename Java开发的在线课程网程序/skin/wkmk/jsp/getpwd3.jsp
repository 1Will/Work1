<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-重置密码</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function resetpwd(){
	var password = document.getElementById("password").value;
	if(password == ""){
		document.getElementById("password_tip").style.display = "";
		return;
	}else{
		if(password.length < 6 || password.length > 25){
			document.getElementById("password_tip").innerHTML = "格式错误，6-25位字符,建议由字母/数字/符号两种以上组合";
			document.getElementById("password_tip").style.display = "";
			return;
		}else{
			document.getElementById("password_tip").style.display = "none";
		}
	}
	var repassword = document.getElementById("repassword").value;
	if(repassword == "" || repassword != password){
		document.getElementById("repassword_tip").style.display = "";
		return;
	}else{
		document.getElementById("repassword_tip").style.display = "none";
	}
	
	document.getpwdForm.action = "/getpwd.do?method=resetpwd";
	document.getpwdForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<form name="getpwdForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <h2>重置密码</h2>
  <div class="list clearfix">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="float1 mar_7">
	  <tr>
	    <td align="center" valign="middle">
	      <table width="50%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
		      <div class="pas_input">
		        <label class="password_1"></label>
		        <input type="password" name="password" id="password" value="" class="user_input1" placeholder="请输入重置密码" />
		      </div>
		      <div style="color:#ff3333;margin-top:20px;display:none;" id="password_tip">&nbsp;&nbsp;重置密码不能为空！</div>
		    </td>
		  </tr>
		  <tr>
		    <td>
		      <div class="pas_input">
		        <label class="password_1"></label>
		        <input type="password" name="repassword" id="repassword" value="" class="user_input1" placeholder="请输入确认密码" />
		      </div>
		      <div style="color:#ff3333;margin-top:20px;display:none;" id="repassword_tip">&nbsp;&nbsp;两次密码输入不一致！</div>
		    </td>
		  </tr>
		  <tr>
		    <td height="100">
		      <input class="sub_1" type="button" value="提交" onclick="resetpwd()"/>
		    </td>
		  </tr>
		</table>	
	    </td>
	  </tr>
	</table>
  </div>
</div>
</div>
<input type="hidden" name="codeno" value="${codeno }"/>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>
