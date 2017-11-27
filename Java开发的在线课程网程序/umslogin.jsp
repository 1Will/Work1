<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数字校园统一用户认证系统</title>
<script language="JavaScript">
</script>
<meta http-equiv="Content-Type" content="text/html charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<script language="javascript" src="/public/js/checkform.js"></script>
<script language="JavaScript"  src="/public/js/autocheckform.js"></script>
<script language="JavaScript"  src="/public/js/comm.js"></script>
<style type="text/css">
/*登录样式*/
*{padding:0px; margin:0px}
li,span,{display:block;}
body{font-size:12px; font-family:"宋体", Arial; line-height:22px; color:#000000;}
#zc{font-size:12px; font-family:"宋体"; background:url(/public/images/login/z03.jpg) repeat-x #d7eefd; line-height:22px;}
#top{width:920px; margin:0 auto; background:url(/public/images/login/z022.jpg) no-repeat; height:82px; color:#FFFFFF;}
#top a{color:#FFFFFF;}
#zhuce{width:262px; height:277px; background:url(/public/images/login/dl_bj.jpg) no-repeat;  margin: 60px auto; padding:26px 23px 0 210px;  }
#zhuce ul{list-style:none;}
#zhuce li{ width:232px; float:left; display:block; font-size:14px; line-height:30px; margin-bottom:10px;}
#zhuce li dt{ width:60px; float:left; display:block;}
#zhuce li dd{width:170px; float:left; display:block;}
#point{width:125px; padding-left:120px; float:left; padding-bottom:20px; color:#FF0000;}
#foot01{width:1003px; margin:0 auto; background:url(/public/images/login/z20.jpg) no-repeat; padding-top:25px; text-align:center; color:#04719b; height:100px;}
</style>
<script language="JavaScript">
//兼容IE,Firefox
function loginname_onkeypress(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "")//获得keyBoardEvent对象
  keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  if (keyCode == 13)  
  {         
    keyCode=0;    
    document.getElementById("password").focus();
  }  
}
function password_onkeypress(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "")//获得keyBoardEvent对象
  keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  if (keyCode == 13)  
  {         
    keyCode=0;    
    document.getElementById("validate").focus();
  }  
}
function code_onkeypress(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "")//获得keyBoardEvent对象
  keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  if (keyCode == 13)
  {
    keyCode=0;
    indexLoginAction();
  }
}

function indexLoginAction(){
  obj = document.loginForm;
  if(autoCheckForm(obj)==false){
    return false;
  }
  obj.action='/umsLoginAction.do?method=login';
  obj.submit();
}

function init(){
 document.getElementById("loginname").focus();
}
</script>
</head>

<body id="zc" onload="init()">
<div id="top">
<span style="padding-left:120px; float:left;"><img src="/public/images/login/logo.jpg" /></span>
<span style="float:right; padding-right:120px; padding-top:10px;"><a href="/index.html" style="text-decoration:none;">首页</a> | <a href="http://www.edutech.com.cn" style="text-decoration:none;">帮助</a></span>
</div><!--#top-->
<form name="loginForm" method="post" >
<div id="zhuce"> 
<div id="point"><logic:notPresent name="promptinfo" scope="request">&nbsp;</logic:notPresent><logic:present name="promptinfo" scope="request"><bean:write name="promptinfo" scope="request"/></logic:present></div>
<ul>
<li>
<dt>用户名：</dt>
<dd><input CK_NAME="用户名" CK_TYPE="NotEmpty" name="loginname" id="loginname" onKeyPress="return loginname_onkeypress(event)" type="text" value="" style="width:160px; height:28px; border:#b2d5ec 1px solid; line-height:28px;" /></dd>
</li>
<li>
<dt>密&nbsp;&nbsp;码：</dt>
<dd><input CK_NAME="密码" CK_TYPE="NotEmpty" name="password" id="password" onKeyPress="return password_onkeypress(event)" type="password" value="" style="width:160px; height:28px; border:#b2d5ec 1px solid; line-height:28px;" />
</dd>
</li>
<li>
<dt>验证码：</dt>
<dd><span style="width:90px; float:left;"><input name="validate" id="validate" onKeyPress="return code_onkeypress(event)" type="text" value="" style="width:80px; height:28px; border:#b2d5ec 1px solid; line-height:28px;" /></span><span style="float:left;"><img src="/sys/admin/code.jsp" width="55" height="28" /></span></dd>
</li>
<li>
  <a style="cursor:pointer;" onclick="javascript:indexLoginAction()"><img src="/public/images/login/dl.jpg" border="0" /></a>
  <a href="#"><img src="/public/images/login/zc.jpg" border="0" /></a>
  <a href="#"><img src="/public/images/login/zm.jpg" border="0" /></a>
</li>
</ul>
<span style="width:232px; float:left; padding-top:15px;">
</span></div>
<!--#zhuce-->
</form>
<div id="foot01">
版权所有：师科阳光数字校园平台　Copyright&copy;2001-2012　All Rights Reserved　京ICP备09025234号<br/>
地址：北京市西城区新街口外大街8号金丰和商务苑B座205室 邮政编码：100088
</div><!--#foot01-->

</body>
</html>
