<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-用户登录</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<script type="text/javascript" src="/skin/wkmk/js/mobile-check.js"></script>
<script language="JavaScript">
function loginname_onkeypress(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "");
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13){         
    	keyCode=0;    
    	document.getElementById("password").focus();
  	}  
}
function password_onkeypress(evt){
  	evt = (evt) ? evt : ((window.event) ? window.event : "");
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13){         
    	keyCode=0;    
    	document.getElementById("validate").focus();
  	}  
}
function code_onkeypress(evt){
  	evt = (evt) ? evt : ((window.event) ? window.event : "");
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13){
    	keyCode=0;
    	indexLoginAction();
  	}
}
function indexLoginAction(){
	var loginname = document.getElementById("loginname");
	if(loginname.value == '' || loginname.value == '用户名'){
		document.getElementById("login_tip").innerHTML = "请输入用户名!";
		loginname.select();
		return;
	}
	var password = document.getElementById("password");
	if(password.value == '' || password.value == '********'){
		document.getElementById("login_tip").innerHTML = "请输入密码!";
		password.select();
		return;
	}
  	obj = document.loginForm;
  	obj.action='/plogin.do?method=sklogin';
  	obj.submit();
}

function init(){
	<logic:equal value="1" name="r" scope="request">
	document.getElementById("validate").focus();
	</logic:equal>
	<logic:notEqual value="1" name="r" scope="request">
	document.getElementById("loginname").focus();
	</logic:notEqual>
}
</script>
</head>

<body onload="init()">
<div id="header1">
  <div class="logo">
    <a href="/index.html"></a>
  </div>
  <div class="seach">
    <div class="login">
      <a href="/index.html">首页</a>
      <span class="mar_1">|</span>
      <a href="/feedback.htm" style="padding-left:10px;">意见反馈</a>
    </div>
  </div>  
</div>
<div id="login_main">
  <div class="login_main1">
    <div class="login_box">
      <h1 class="login_top">用户登录</h1>
      <div class="login_bottom">
        <form name="loginForm" method="post">
          <dl class="login_tip" id="login_tip"><logic:notPresent name="promptinfo" scope="request">&nbsp;</logic:notPresent><logic:present name="promptinfo" scope="request"><bean:write name="promptinfo" scope="request"/></logic:present></dl>
          <span class="login_pad">
            <label class="user"></label>
            <input type="text" name="loginname" id="loginname" onKeyPress="return loginname_onkeypress(event)" value="${loginname }" class="input_1" />
          </span>
          <span class="login_pad">
            <label class="password"></label>
            <input type="password" name="password" id="password" onKeyPress="return password_onkeypress(event)" value="${password }" class="input_1" />
          </span>
          <span class="login_pad">
            <label class="code"></label>
            <input type="text" name="validate" id="validate" maxlength="4" onKeyPress="return code_onkeypress(event)" value="" class="input_2" />
            <img src="/sys/admin/code.jsp" width="79" height="37" />
          </span>
          <span class="login_pad1">
            <input type="checkbox" name="r" value="1" <logic:equal value="1" name="r" scope="request">checked="checked"</logic:equal>/>
            <label>记住密码</label>
            <a href="/v.bo?method=register" class="zc_1">注册</a>
            <a href="/getpwd.do?method=find" class="zc_2">忘记密码</a>
          </span>
          <span class="login_pad">
            <input type="button" value="登录" class="sub_1" onclick="indexLoginAction()"/>
          </span>
        <input type="hidden" name="redirecturl" value="<bean:write name="redirecturl"/>"/>
		<input type="hidden" name="hiddenpwd" value="${hiddenpwd }">
        </form>
      </div>
    </div>
  </div>
</div>
<div id="login_footer">
  <span>微课慕课网 版权所有@2014-2015</span>
  <span>Tel:010-8345-6666</span>
  <span>京ICP备09025234号
</div>
</body>
</html>