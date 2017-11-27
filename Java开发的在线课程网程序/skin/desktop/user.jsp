<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function searchUser(){
	var url = '/sns.bo?method=searchUserMain';
	showModalDialog(url,'找人',"dialogWidth:580px;dialogHeight:350px;scroll=no;border=thin;help=0;status=no");
}
</script>
</head>
<body style="background:none;">
<div id="xxtj">
<h3>活跃用户<a href="javascript:searchUser()" style="float:right;padding-right:10px;color:#666;font-size:12px;font-weight:normal;">找人</a></h3>
<ul style="width:238px;">
<logic:iterate id="model" name="list">
<li style="width:78px;float:left;">
<dt><a href="/sns-user-0-<bean:write name="model" property="userid"/>.htm" target="_blank"><img src="<bean:write name="model" property="photo"/>" width="70" height="70" border="0"/></a></dt>
<dd style="text-align:center;"><a href="/sns-user-0-<bean:write name="model" property="userid"/>.htm" target="_blank"><bean:write name="model" property="username"/></a></dd>
</li>
</logic:iterate>
</ul>
</div>
</body>
</html>
