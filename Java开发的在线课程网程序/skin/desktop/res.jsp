<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/lanrentuku.css" rel="stylesheet" type="text/css" />
<script>
function changeType(ftag){
	document.getElementById('ftag').value = ftag;
	document.pageForm.action = '/pcenter.do?method=res';
	document.pageForm.submit();
}
</script>
</head>
<body style="background:none;">
<form name="pageForm" method="post">
<div id="Tab21">
<div class="Menubox21">
<ul>
<li id="qb1" onclick="changeType('0')" <logic:equal value="0" name="ftag">class="hover"</logic:equal>>全部</li>
<li id="qb2" onclick="changeType('1')" <logic:equal value="1" name="ftag">class="hover"</logic:equal>>教案</li>
<li id="qb3" onclick="changeType('2')" <logic:equal value="2" name="ftag">class="hover"</logic:equal>>课件</li>
<li id="qb4" onclick="changeType('3')" <logic:equal value="3" name="ftag">class="hover"</logic:equal>>试卷</li>
<li id="qb5" onclick="changeType('4')" <logic:equal value="4" name="ftag">class="hover"</logic:equal>>素材</li>
<li id="qb6" onclick="changeType('7')" <logic:equal value="7" name="ftag">class="hover"</logic:equal>>学案</li>
<li id="qb7" onclick="changeType('5')" <logic:equal value="5" name="ftag">class="hover"</logic:equal>>论文</li>
<li id="qb8" onclick="changeType('6')" <logic:equal value="6" name="ftag">class="hover"</logic:equal>>视频</li>
<li id="qb9" onclick="changeType('tj')" <logic:equal value="tj" name="ftag">class="hover"</logic:equal>>推荐</li>
<li id="qb10" onclick="changeType('jy')" <logic:equal value="jy" name="ftag">class="hover"</logic:equal>>文档</li>
</ul>
</div>
<div class="Contentbox21">
<div id="con_qb_1" class="hover">
<div id="jy_nr">
<logic:equal value="jy" name="ftag">
<logic:iterate id="model" name="list">
<dl>
<dt><a href='/html/<bean:write name="model" property="subjectid"/>/<bean:write name="model" property="htmlpath"/>/jy<bean:write name="model" property="resid"/>.shtml' target="_blank"><bean:write name="model" property="title"/></a></dt>
<dd><bean:write name="model" property="sysUserInfo.username"/></dd>
<dd><java2page:write name="model" property="createdate" length="10"/></dd>
</dl>
</logic:iterate>
</logic:equal>
<logic:notEqual value="jy" name="ftag">
<logic:iterate id="model" name="list">
<dl>
<dt><a href='/html/<bean:write name="model" property="subjectid"/>/<bean:write name="model" property="htmlpath"/>/ri<bean:write name="model" property="resid"/>.shtml' target="_blank"><bean:write name="model" property="title"/></a></dt>
<dd><bean:write name="model" property="sysUserInfo.username"/></dd>
<dd><java2page:write name="model" property="createdate" length="10"/></dd>
</dl>
</logic:iterate>
</logic:notEqual>
</div><!--#jy_nr-->
</div>
</div>
</div>
<input type="hidden" name="ftag" id="ftag" value=""/>
</form>
</body>
</html>
