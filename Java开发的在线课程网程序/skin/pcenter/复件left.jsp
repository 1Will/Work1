<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysModuleInfo"%>
<script type="text/javascript">
<!--
function setLocation(url,cur,total){
	for(var i=1; i<=total; i++){
		document.getElementById('menu_'+i).className = '';
	}
	document.getElementById('menu_'+cur).className = 'sdmenu_onclick';
	if(url != ''){
		if(url.indexOf("beiKeAction") >= 0 || url.indexOf("jyResInfoAction.do?method=beforeAdd") >= 0){
			if(navigator.userAgent.indexOf("Firefox") < 0){
				if(url.indexOf("beiKeAction.do?method=jiaoan") >= 0){
					window.open(url,'beike1window','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
				}else{
					window.open(url,'beike2window','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
				}
			}else{
				alert("请用除Firefox(火狐)浏览器之外的其他浏览器撰写教案和课件，WebOffice控件不支持Firefox浏览器!");
				return false;
			}
		}else{
			document.getElementById("mainFrame").src = url;
		}
	}
}

function changeBg(flag,id){
	var className = document.getElementById(id).className;
	if(flag == '1' && className != 'sdmenu_onclick'){
		document.getElementById(id).className = 'sdmenu_hover';
	}
	if(flag == '2' && className != 'sdmenu_onclick'){
		document.getElementById(id).className = '';
	}
}
//-->
</script>
<div id="left">
<h3><a href="/pcenter.do?method=ucenter"><img src="/public/images/pcenter/c01.jpg"/></a></h3>
<div style="float: left" id="my_menu" class="sdmenu">
<p style="height:10px;line-height:10px;">&nbsp;</p>
	<%
		List dataList = (List)request.getAttribute("dataList");
		Map childMap = (Map)request.getAttribute("childMap");
		Integer childsize = (Integer)request.getAttribute("childsize");
		int menuid = 0;
		for(int i=0; i<dataList.size(); i++){
			SysModuleInfo moduleInfo = (SysModuleInfo)dataList.get(i);
	%>
      <div>
        <span id="title"><%=moduleInfo.getModulename() %></span>
      <%
      	List childList = (List)childMap.get(moduleInfo.getModuleno());
      	for(int j=0; j<childList.size(); j++){
      		menuid ++;
      		SysModuleInfo module = (SysModuleInfo)childList.get(j);
      %>  
		<a id="menu_<%=menuid %>" onclick="setLocation('<%=module.getLinkurl() %>','<%=menuid %>','<%=childsize %>')" onmouseover="changeBg('1','menu_<%=menuid %>')" onmouseout="changeBg('2','menu_<%=menuid %>')"><%=module.getModulename() %></a>
	  <%} %>
      </div>
    <%} %>
  </div><!--#left导航结束-->
<span style="float:left; "><img src="/public/images/pcenter/c05.gif" /></span>
</div>
