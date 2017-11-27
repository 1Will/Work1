<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base target="_self"/>
<style>
*{padding:0px; margin:0px;}
ul,li{ list-style:none;}
.kecheng{ width:294px; border:1px solid #3f5d75;height:auto!important;height:379px;min-height:379px;background:#dcffff;padding:2px;}
.kecheng li{ width:143px; margin:2px; float:left; height:38px; display:inline;}
.kecheng li a{width:141px; border:1px solid #3f6276;text-align:center; height:36px; line-height:36px; background:#b9dbf9; display:block; color:#3f5d75; text-decoration:none; font-size:14px; font-weight:normal;}
.kecheng li a:hover{ color:#000; background:#fff;font-size:14px; font-weight:normal;}
.clear{clear:both; font-size:0px; height:0px;}
</style>
<script type="text/javascript">
	function toOnclick(subjectid){
	  document.pageForm.action = '/sysUserInfoAction.do?method=deal&subjectid=' + subjectid;
	  document.pageForm.submit();
	}
</script>
</head>

<body>
<FORM name="pageForm" method=post>
<ul class="kecheng">
	<logic:iterate id="model" name="list">
	<li><a href="javascript:toOnclick('<bean:write name="model" property="id"/>')" title='<bean:write name="model" property="name"/>'><bean:write name="model" property="name"/></a></li>
    </logic:iterate>
    <div class="clear"></div>
</ul>
</FORM>
</body>
</html>
