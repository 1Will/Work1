<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.bo.SysModuleInfo"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=Constants.SYS_PRODUCT_NAME %>-个人中心</title>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/public/js/dialog/blue/style.css"/>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/pcenter.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/public/js/dialog/drag.js"></script>
<script type="text/javascript" src="/public/js/dialog/dialog.js"></script>
<script type="text/javascript">
function setLocation(url,cur,total){
	for(var i=1; i<=total; i++){
		document.getElementById('menu_'+i).className = '';
	}
	document.getElementById('menu_'+cur).className = 'hover';
	if(url != ''){
		document.getElementById("mainFrame").src = url;
	}
}
</script>
</head>

<body style="background:#f0f2f5;">
<%@ include file="/skin/wkmk/jsp/top.jsp"%>

<div class="backstage_site">
<div class="backstage_site_main">
<%
String sysAdmin = (String)session.getAttribute("sysAdmin");
if("1".equals(sysAdmin)){
%>
<span class="backstage_site_01"><a href="/adminAutoLogin.do">欢迎您</a>，<bean:write name="s_sysuserinfo" property="username" scope="session"/></span>
<%}else{ %>
<span class="backstage_site_01">欢迎您，<bean:write name="s_sysuserinfo" property="username" scope="session"/></span>
<%} %>
<span class="backstage_site_02">财富值：<bean:write name="s_sysuserinfodetail" property="caifu" scope="session"/></span>
<span class="backstage_site_03"><a href="/pcenter.do?method=index&mproduct=vod">个人中心</a> | <a href="/plogin.do?method=userLogout">退出</a></span>
</div>
</div>

<div class="back">
<div class="back_left">
      <div class="mar_2">
        <div id="firstpane" class="menu_list">
          <%
			List dataList = (List)request.getAttribute("dataList");
			Map childMap = (Map)request.getAttribute("childMap");
			Integer childsize = (Integer)request.getAttribute("childsize");
			int menuid = 0;
			int size = dataList.size();
			SysModuleInfo moduleInfo = null;
			List childList = null;
			for(int i=0; i<size; i++){
				moduleInfo = (SysModuleInfo)dataList.get(i);
		  %>
          <p class="menu_head<%if(i==0){ %> current bgico<%}%>"><%=moduleInfo.getModulename() %></p>
          <div style="<%if(i==0){ %>display:block<%}else{%>display:none<%}%>" class="menu_body">
            <%
	      	childList = (List)childMap.get(moduleInfo.getModuleno());
	      	for(int j=0; j<childList.size(); j++){
	      		menuid ++;
	      		SysModuleInfo module = (SysModuleInfo)childList.get(j);
	        %>
            <a id="menu_<%=menuid %>" href="javascript:setLocation('<%=module.getLinkurl() %>','<%=menuid %>','<%=childsize %>')"><%=module.getModulename() %></a>
            <%} %>
          </div>
          <%} %>
        </div>
        <script type=text/javascript>
        $(document).ready(function(){
            $("#firstpane .menu_body:eq(0)").show();
            $("#firstpane p.menu_head").click(function(){
                $(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
                $(this).siblings().removeClass("current");
            });
            $("#secondpane .menu_body:eq(0)").show();
            $("#secondpane p.menu_head").mouseover(function(){
                $(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
                $(this).siblings().removeClass("current");
            });
            
        });
      </script>
      </div>
</div>

<div class="back_right">
<iframe id="mainFrame" name="mainFrame" width="100%" height="520" frameborder="0" marginheight="0" marginwidth="0" scrolling=auto src="/skin/wkmk/images/g09.jpg"></iframe>
</div>

</div>

<%@ include file="/skin/wkmk/jsp/footer.jsp"%>
</body>
</html>