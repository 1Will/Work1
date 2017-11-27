<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
*{padding:0px; border:0px; margin:0px;}
body{font-size:12px; font-family:"宋体"; color:#666666; line-height:22px; background:url(/public/images/pcenter/bj2.gif) repeat-x;}
span,li,dt,dd,img{display:block;}

#comm_top_right{width:530px; float:right; height:33px; line-height:33px; text-align:right; color:#555; font-size:13px; font-family:Arial, "宋体"}
#comm_top_right a:link,#top_right a:visited{ text-decoration:none; color:#555; }
#comm_top_right a:hover{ text-decoration:underline; color:#0080c8;}
</style>
<script language="javascript" src="/public/js/checkform.js"></script>
<script language="JavaScript"  src="/public/js/autocheckform.js"></script>
<script type="text/javascript">
//兼容IE,Firefox
function loginname_onkeypress(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "")//获得keyBoardEvent对象
  keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  if (keyCode == 13){         
    keyCode=0;    
    document.getElementById("password").focus();
  }  
}

function password_onkeypress(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "")//获得keyBoardEvent对象
  keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  if (keyCode == 13){         
    keyCode=0;    
    indexLoginAction();
  }  
}

function indexLoginAction(){
  obj = document.loginForm;
  if(autoCheckForm(obj)==false){
    return false;
  }
  obj.action='/plogin.do?method=userLogin';
  obj.submit();
}

function logoutAction(){
  var url = window.parent.window.parent.window.parent.document.URL;
  window.top.location = '/plogin.do?method=userLogout&redirecturl=' + url;
}

function relogin(){
  obj = document.loginForm;
  obj.action='/plogin.do?method=reLogin';
  obj.submit();
}

function manager(){
  <logic:notEmpty name="s_userid" scope="session" >
  window.top.location = '/pcenter.do?method=ucenter';
  </logic:notEmpty>
}
		
function register(){
	alert("对不起，暂不对外提供注册，请与管理员联系。");
	//document.rgForm.action = '/register.do?method=br';
	//document.rgForm.submit();
}
</script>
</head>
<body>
<form name="rgForm" method="post" target="_top">
  <input type="hidden" name="unitid" value='<bean:write name="unitid"/>'>
</form>
<form name="loginForm" method="post">
<div id="comm_top_right">
    <%
	String userid = (String)session.getAttribute("s_userid");
	if(userid == null || "".equals(userid)){
	%>
    <div>
    <logic:present name="promptinfo" scope="request">
 		<a style="color:red;"><%=request.getAttribute("promptinfo")%></a>&nbsp;&nbsp;[<a style="cursor:pointer;" onclick="javascript:relogin()">重新登录</a>]
 	</logic:present>
 	<logic:notPresent name="promptinfo" scope="request">
 		<table style="float:right;margin-right:0px; display:inline">
            <tr>
            	<td>用户名：</td>
                <td><input type="text" name="loginname" id="loginname" CK_NAME="用户名" CK_TYPE="NotEmpty" style="width:80px; height:15px; border:#dddddd 1px solid;" onKeyPress="return loginname_onkeypress(event)"/></td>
                <td>密&nbsp;码：</td>
                <td><input type="password" name="password" id="password" CK_NAME="密码" CK_TYPE="NotEmpty" style="width:80px; height:15px; border:#dddddd 1px solid;" onKeyPress="return password_onkeypress(event)"/></td>
                <td><a style="cursor:pointer;" onclick="javascript:indexLoginAction()"><img src="/public/images/pcenter/dl.gif" /></a></td>
                <td><a style="cursor:pointer;" onclick="javascript:register()"><img src="/public/images/pcenter/zc.gif" /></a></td>
                <td><a href="/getpwd.do?method=getpwd1" style="color:#ccc;" target="_top" title="找回密码">[密码]</a></td>
            </tr>
        </table>
 	</logic:notPresent> 
      <%}else{ %>
       <div>欢迎您，<bean:write name="s_sysuserinfo" property="username" scope="session"/>&nbsp;<a style="color:#ccc;">|</a>&nbsp;<bean:write name="s_sysusertitle" property="name" scope="session"/>&nbsp;[积分/财富:<bean:write name="s_sysuserinfodetail" property="jifen"/>/<bean:write name="s_sysuserinfodetail" property="caifu"/>]&nbsp;<a style="color:#ccc;">|</a>&nbsp;<a onclick="javascript:manager()" style="cursor:pointer;">个人中心</a>&nbsp;<a style="color:#ccc;">|</a>&nbsp;<a style="cursor:pointer;" onclick="javascript:logoutAction()">退出</a></div>
      <%} %>
</div>
<input type="hidden" name="uid" value='<bean:write name="unitid"/>'>
</form>
</body>
</html>