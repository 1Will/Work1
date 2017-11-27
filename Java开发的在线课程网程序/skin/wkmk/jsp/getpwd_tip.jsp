<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-重置密码提示</title>
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
  <h2>重置密码</h2>
  <div class="list clearfix">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="float1 mar_7">
	  <tr>
	    <td align="center" valign="middle">
	      <table width="50%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td align="center" style="line-height:30px;font-size:18px;">
		    <logic:present name="pwdtip"><p>${pwdtip }</p></logic:present>
		    <logic:notPresent name="pwdtip"><p>重置密码连接地址已失效！</p></logic:notPresent>
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
