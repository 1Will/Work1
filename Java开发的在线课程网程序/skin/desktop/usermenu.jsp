<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysUserMenu"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/repaint_r1.12.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<script src="/skin/desktop/js/iepng.js" type="text/javascript"></script>
<![endif]-->
<SCRIPT src="/skin/desktop/js/repaint_r1.3.aaa.js" type=text/javascript></SCRIPT>
<script src="/skin/desktop/js/tab.js" type=text/javascript></script>
</head>
<body style="background:none;">
<form name="menuForm" method="post" target="_top">
<div class="dtk-carousel" id="dtk-car-0">
  <h2 style="color:green;">快捷菜单<span style="display:none;"><a href="#">点1</a>| <a href="#">点2</a>| <a href="#">点3</a></span></h2>
  <div class="scrollbody">
    <div class="scrollpages">
      <div class="quadruple">
      	<%
      	List menuList = (List)request.getAttribute("menuList");
      	int size = menuList.size();
      	SysUserMenu menu = null;
      	if(size > 0){
      	%>
      	<ul class="dtk-list">
      	<%
      	for(int i=0; i<12; i++){
      		if(i == size) break;
      		menu = (SysUserMenu)menuList.get(i);
      	%>
          <li class="dtk-item">
            <div><a href="javascript:setLocation('<%=menu.getSysModuleInfo().getMproduct() %>','<%=menu.getSysModuleInfo().getLinkurl() %>')"/><img title="<%=menu.getSysModuleInfo().getModulename() %>" width="82" height="81" src="<%=menu.getSketch() %>" /></a></div>
            <p><%=menu.getSysModuleInfo().getModulename() %></p>
          </li>
        <%} %>
        </ul>
        <%} %>
        <%
      	if(size > 12){
      	%>
      	<ul class="dtk-list">
      	<%
      	for(int i=12; i<24; i++){
      		if(i == size) break;
      		menu = (SysUserMenu)menuList.get(i);
      	%>
          <li class="dtk-item">
            <div><a href="javascript:setLocation('<%=menu.getSysModuleInfo().getMproduct() %>','<%=menu.getSysModuleInfo().getLinkurl() %>')"/><img title="<%=menu.getSysModuleInfo().getModulename() %>" width="82" height="81" src="<%=menu.getSketch() %>" /></a></div>
            <p><%=menu.getSysModuleInfo().getModulename() %></p>
          </li>
        <%} %>
        </ul>
        <%} %>
        <%
      	if(size > 24){
      	%>
      	<ul class="dtk-list">
      	<%
      	for(int i=24; i<36; i++){
      		if(i == size) break;
      		menu = (SysUserMenu)menuList.get(i);
      	%>
          <li class="dtk-item">
            <div><a href="javascript:setLocation('<%=menu.getSysModuleInfo().getMproduct() %>','<%=menu.getSysModuleInfo().getLinkurl() %>')"/><img title="<%=menu.getSysModuleInfo().getModulename() %>" width="82" height="81" src="<%=menu.getSketch() %>" /></a></div>
            <p><%=menu.getSysModuleInfo().getModulename() %></p>
          </li>
        <%} %>
        </ul>
        <%} %>
      </div>
    </div>
  </div>
</div>
<SCRIPT>
 for (var b=0; b<1; b++){
 YAHOO.Media.Dtk.CarouselMgr.init("dtk-car-"+b,{pageClassName:'dtk-list',pageTagName:'ul'});
 }
</SCRIPT>
<input type="hidden" name="onclickmenu" id="onclickmenu" value=""/>
</form>
</body>
</html>
