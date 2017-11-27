<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="com.util.date.DateTime"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<style>
#foot{width:1003px; margin:0 auto; background:url(/public/images/register/z20.jpg) no-repeat; padding-top:35px; text-align:center; color:#04719b; height:100px;}
</style>
<div id="foot">
版权所有：<bean:write name="szxy_sysunitinfo" property="unitname"/>　Copyright&copy;2001-<%=DateTime.getDate("yyyy") %>　All Rights Reserved　<bean:write name="szxy_sysunitinfo" property="icp"/><br/>
地址：<bean:write name="szxy_sysunitinfo" property="address"/> 邮政编码：<bean:write name="szxy_sysunitinfo" property="postcode"/>
</div>