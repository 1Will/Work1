<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.bo.SysUnitInfo"%>
<%@ page contentType = "text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/public/js/dialog/drag.js"></script>
<script type="text/javascript" src="/public/js/dialog/dialog.js"></script>
</head>
<body>
<%SysUnitInfo sui = (SysUnitInfo)request.getAttribute("model"); %>
<input type="hidden" name="id" id="id" value="<%=sui.getUnitid().toString() %>"/>
<input type="hidden" name="name" id="name" value="<%=sui.getUnitname() %>"/>
</body>
<script>
$(function(){
	top.Dialog.close();
})
</script>
</html>
