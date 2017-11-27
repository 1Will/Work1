<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.edu.bo.EduResInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/lanrentuku.css" rel="stylesheet" type="text/css" />
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script>
function changeType(ftag){
	document.getElementById('ftag').value = ftag;
	document.pageForm.action = '/pcenter.do?method=beike';
	document.pageForm.submit();
}
function editXTBK(resid, fileext){
	var result = false;
	//先用ajax判断当前文档是否可以编辑
	new Ajax.Request(
		"xtBkAction.do?method=canEditor&resid=" + resid + "&ram=" + Math.random(),
		{
		method:"get",
		asynchronous:false,//true为异步请求
		onComplete:function(xhr){
			var responseObj = xhr.responseText;
			if(responseObj != 'ok'){
				result = true;
				alert(responseObj);
			}
		}
		}
	);
	
	if(!result){
		if(!+[1,] || "msDoNotTrack" in window.navigator){
			if(fileext == 'doc' || fileext == 'docx'){
				var url = '/beiKeAction.do?method=jiaoan&xtbk=1&resid=' + resid;
				window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
			}
			if(fileext == 'ppt' || fileext == 'pptx'){
				var url = '/beiKeAction.do?method=kejian&xtbk=1&resid=' + resid;
				window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
			}
			if(fileext == 'dsc'){
				var url = '/xbeiKeAction.do?method=jiaoan&xtbk=1&resid=' + resid;
				window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
			}
			if(fileext == 'xpt'){
				var url = '/xbeiKeAction.do?method=kejian&xtbk=1&resid=' + resid;
				window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
			}
		}else{
			alert("请用IE浏览器修改教案，WebOffice控件只支持IE浏览器!");
			return ;
		}
	}
}
function moreVersion(resid){
	var url = '/jtBkAction.do?method=moreVersionMain&resid=' + resid;
	showModalDialog(url,'历史版本',"dialogWidth:800px;dialogHeight:550px;scroll=auto;border=thin;help=0;status=no");
}
function openJTBK(resid){
	if(!+[1,] || "msDoNotTrack" in window.navigator){
		var url = "/jtBkAction.do?method=openJTBK&resid=" + resid;
		window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
	}else{
		alert("请用IE浏览器参与集体备课，WebOffice控件只支持IE浏览器!");
		return ;
	}
}
function fabu(resid){
	if(confirm("我的教研发布后需等待管理员审核，审核后将会在前台显示，是否发布？")){
		document.getElementById('ftag').value = "3";
		document.pageForm.action="/pcenter.do?method=fabu&resid=" + resid;
    	document.pageForm.submit();
	}
}
function gongxiang(resid){
	if(confirm("我的私有资源共享后需等待管理员审核，审核后将会在前台显示，是否共享？")){
		document.getElementById('ftag').value = "4";
		document.pageForm.action="/pcenter.do?method=gongxiang&resid=" + resid;
    	document.pageForm.submit();
	}
}
function editRecord(resid, restype){
	if(!+[1,] || "msDoNotTrack" in window.navigator){
		var url = '/jyResInfoAction.do?method=beforeAdd&restype=' + restype + '&resid=' + resid;
		window.open(url,'beikewindow','fullscreen=1,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes, status=no')
	}else{
		alert("请用IE浏览器修改教案，WebOffice控件只支持IE浏览器!");
		return ;
	}
}
function downloadFile(resid){
	window.location = '/eduResInfoAction.do?method=downloadFile&resid=' + resid;
}
</script>
</head>
<body style="background:none;">
<form name="pageForm" method="post">
<div id="Tab20">
<div class="Menubox20">
<ul>
<li id="qh4" onclick="changeType('4')" <logic:equal value="4" name="ftag">class="hover"</logic:equal>>我的资源</li>
<li id="qh3" onclick="changeType('3')" <logic:equal value="3" name="ftag">class="hover"</logic:equal>>我的教研</li>
<li id="qh1" onclick="changeType('1')" <logic:equal value="1" name="ftag">class="hover"</logic:equal>>协同备课</li>
<li id="qh2" onclick="changeType('2')" <logic:equal value="2" name="ftag">class="hover"</logic:equal>>集体备课</li>
</ul>
</div>
<div class="Contentbox20">
<div id="con_qh_1" class="hover">
<logic:equal value="1" name="ftag">
<div id="bk_nr">
<logic:iterate id="model" name="list">
<dl>
<dt><a href=javascript:editXTBK('<bean:write name="model" property="resid"/>','<bean:write name="model" property="fileext"/>') title="点击进入协同备课"><bean:write name="model" property="title"/></a></dt>
<dd><bean:write name="model" property="sysUserInfo.username"/></dd>
<logic:equal value="0" name="model" property="fabu"><dd style="color:green;">进行中</dd></logic:equal>
<logic:equal value="1" name="model" property="fabu"><dd style="color:red;">已结束</dd></logic:equal>
<dd><a href=javascript:editXTBK('<bean:write name="model" property="resid"/>','<bean:write name="model" property="fileext"/>') title="点击进入协同备课">进入</a>&nbsp;<a href=javascript:moreVersion('<bean:write name="model" property="resid"/>') title="历史版本"><img src="/skin/desktop/images/z09.png" border="0" /></a></dd>
</dl>
</logic:iterate>
</div><!--#bk_nr-->
</logic:equal>
<logic:equal value="2" name="ftag">
<div id="bk_nr">
<logic:iterate id="model" name="list">
<dl>
<dt><a href=javascript:openJTBK('<bean:write name="model" property="resid"/>') title="点击进入集体备课"><bean:write name="model" property="title"/></a></dt>
<dd><bean:write name="model" property="sysUserInfo.username"/></dd>
<logic:equal value="0" name="model" property="fabu"><dd style="color:green;">进行中</dd></logic:equal>
<logic:equal value="1" name="model" property="fabu"><dd style="color:red;">已结束</dd></logic:equal>
<dd><a href=javascript:openJTBK('<bean:write name="model" property="resid"/>') title="点击进入集体备课">进入</a></dd>
</dl>
</logic:iterate>
</div><!--#bk_nr-->
</logic:equal>
<logic:equal value="3" name="ftag">
<div id="bk_nr">
<logic:iterate id="model" name="list">
<dl>
<dt><a href=javascript:editRecord('<bean:write name="model" property="resid"/>','<bean:write name="model" property="restype"/>') title="点击进行修改教研内容"><bean:write name="model" property="title"/></a></dt>
<dd>
<logic:equal value="11" name="model" property="restype">教学论文</logic:equal>
<logic:equal value="12" name="model" property="restype">教学随笔</logic:equal>
<logic:equal value="13" name="model" property="restype">教研计划</logic:equal>
<logic:equal value="14" name="model" property="restype">教研记录</logic:equal>
</dd>
<logic:equal value="0" name="model" property="fabu"><dd style="color:red;cursor:pointer;" onclick="fabu('<bean:write name="model" property="resid"/>')" title="已发布教研文档经管理员审核通过后将可在首页显示供下载!">发布</dd></logic:equal>
<logic:equal value="1" name="model" property="fabu"><dd style="color:green;">已发布</dd></logic:equal>
<dd><a href=javascript:editRecord('<bean:write name="model" property="resid"/>','<bean:write name="model" property="restype"/>') title="点击进行修改教研内容">编辑</a></dd>
</dl>
</logic:iterate>
</div><!--#bk_nr-->
</logic:equal>
<logic:equal value="4" name="ftag">
<div id="bk_nr">
<%
List list = (List)request.getAttribute("list");
EduResInfo eri = null;
for(int i=0; i<list.size(); i++){
	eri = (EduResInfo)list.get(i);
%>
<dl>
<dt><a  href=javascript:downloadFile('<%=eri.getResid() %>') title="点击下载资源"><%=eri.getTitle() %></a></dt>
<dd>
<%=Constants.getCodeTypevalue("restype", eri.getRestype()) %>
</dd>
<%if("1".equals(eri.getPrivatestatus())){ %>
<dd style="color:green;">共享</dd>
<%}else{ %>
<dd style="color:red;cursor:pointer;" onclick="gongxiang('<%=eri.getResid() %>')" title="已共享资源经管理员审核通过后将可在首页显示供下载!">私有</dd>
<%} %>
<dd><a href=javascript:downloadFile('<%=eri.getResid() %>')>下载</a></dd>
</dl>
<%} %>
</div><!--#bk_nr-->
</logic:equal>
</div>
</div>
</div>
<input type="hidden" name="ftag" id="ftag" value=""/>
</form>
</body>
</html>