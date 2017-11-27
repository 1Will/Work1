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
<script type="text/javascript">
function indexLoginAction(){
  var topurl = window.top.location;
  //window.top.location = '/pcenter.do?method=ucenter&redirecturl=' + topurl;
  //window.top.location = '/pcenter.do?method=ucenter';
  window.top.location = '/pcenter.do?method=index&mproduct=vod';
}

function logoutAction(){
  var topurl = window.top.location;
  window.top.location = '/plogin.do?method=userLogout&redirecturl=' + topurl;
}

function manager(){
  <logic:notEmpty name="s_userid" scope="session" >
  //window.top.location = '/pcenter.do?method=ucenter';
  window.top.location = '/pcenter.do?method=index&mproduct=vod';
  </logic:notEmpty>
}
		
function register(){
	alert("暂不提供对外注册!");
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
    <logic:present name="loginfailure" scope="application">
 		<a style="color:red;"><%=application.getAttribute("loginfailure")%></a>
 	</logic:present>
 	<logic:notPresent name="loginfailure" scope="application">
 		您好，欢迎您来到<bean:write name="szxy_sysunitinfo" property="unitname" scope="session"/>！
 	</logic:notPresent> 
       &nbsp;&nbsp;<a style="cursor:pointer;text-decoration:underline;" onclick="javascript:indexLoginAction()">登录</a>&nbsp;&nbsp;</div>
      <%}else{ %>
       <div>欢迎您，<bean:write name="s_sysuserinfo" property="username" scope="session"/>&nbsp;[财富值:<bean:write name="s_sysuserinfodetail" property="caifu"/>分]&nbsp;<a style="color:#ccc;">|</a>&nbsp;<a onclick="javascript:manager()" style="cursor:pointer;">个人中心</a>&nbsp;<a style="color:#ccc;">|</a>&nbsp;<a style="cursor:pointer;" onclick="javascript:logoutAction()">退出</a></div>
      <%} %>
   <logic:present name="loginfailure" scope="application">
   <%application.removeAttribute("loginfailure");%>
   </logic:present>
</div>
</form>
</body>
</html>
