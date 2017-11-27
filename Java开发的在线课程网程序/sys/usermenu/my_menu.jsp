<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../public/jsp/taglibs.jsp"%>

<html>
<head>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
function setMenu(){
	var url = '/sysUserMenuAction.do?method=main';
	showModalDialog(url,'快捷菜单设置',"dialogWidth:650px;dialogHeight:460px;scroll=no;border=thin;help=0;status=no");
	document.location.reload();
}
function setLocation(mproduct, url){
	document.getElementById('onclickmenu').value = url;
	document.menuForm.action = '/pcenter.do?method=index&mproduct=' + mproduct + '&msg=0';
	document.menuForm.submit();
}
function autoRead(mproduct, url){
	document.getElementById('onclickmenu').value = "oaBulletinInfoAction.do?method=list2&autoRead=1";
	document.menuForm.action = '/pcenter.do?method=index&mproduct=msg&msg=00';
	document.menuForm.submit();
}
function setSubject(){
	var url = '/sysUserInfoAction.do?method=userSubjectMain';
	showModalDialog(url,'设置学科',"dialogWidth:470px;dialogHeight:260px;scroll=no;border=thin;help=0;status=no");
}
</SCRIPT>
<title>我的快捷菜单</title>
<%@ include file="../../public/jsp/style.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
</head>
<body style="overflow-x:hidden;">
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">我的快捷菜单</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_add"  onclick="setMenu()" type="button" value="设置" name="btnadd">
      <a href="javascript:setSubject()">设置学科</a>
      <a href="javascript:autoRead()">自动阅读消息测试</a>
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr bgcolor="#ffffff">
	<logic:iterate id="model" name="list" indexId="index">
	<td width="20%" align="center">
	<table width="100%" align="center">
		<tr>
		<td align="center"><img src="<bean:write property="sketch" name="model"/>" style="width:60px;height:50px;cursor:pointer;" title="<bean:write property="sysModuleInfo.modulename" name="model"/>" onclick="setLocation('<bean:write property="sysModuleInfo.mproduct" name="model"/>','<bean:write property="sysModuleInfo.linkurl" name="model"/>')"/></td>
		</tr>
		<tr>
		</tr>
	</table>
	</td>
	
	<%if((index+1)%5 == 0){ %>
	</tr>
	<tr bgcolor="#ffffff">
	<%} %>
	</logic:iterate>
	
</tr>
</TABLE>
<FORM name="menuForm" method=post target="_top">
<input type="hidden" name="onclickmenu" id="onclickmenu" value="">
</FORM>
</body>
</html>

