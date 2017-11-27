<html>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.util.date.DateTime"%>
<HEAD>
<style type="text/css">
*{padding:0px;margin:0px;}
#foot{width:100%; margin-top:5px; text-align:center; padding-top:16px; background:url(/public/images/pcenter/footbg2.gif) repeat-x left top; height:45px; font-size:13px;}
</style>
</HEAD>
<BODY>
<div id="foot">
2010-<%=DateTime.getDate("yyyy") %>@<bean:write name="szxy_sysunitinfo" property="unitname"/> ALL RIGHT RESERVED
联系邮箱:<a href='mailto:<bean:write name="szxy_sysunitinfo" property="email"/>' style="text-decoration:none;color:#000;"><bean:write name="szxy_sysunitinfo" property="email"/></a>  联系电话:<bean:write name="szxy_sysunitinfo" property="telephone"/>
</div>
</BODY>
</html>
