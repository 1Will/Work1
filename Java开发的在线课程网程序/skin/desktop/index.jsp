<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.util.date.DateTime"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
<link href="/skin/desktop/css/css.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/skin/desktop/css/lanrentuku.css" rel="stylesheet" type="text/css" />
<script src="/skin/desktop/js/dMarquee.js" type=text/javascript></script>
<script src="/skin/desktop/js/tab.js" type=text/javascript></script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>
<script>
<!--
function SetCwinHeight(frameid){
	var iframeid=document.getElementById(frameid); //iframe id
	if (document.getElementById){
	   if (iframeid && !window.opera){
		   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
		     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
		   	 iframeid.height = height1
		   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
		     var height2 = iframeid.Document.body.scrollHeight;
		  	 iframeid.height = height2
		   }
	   }
	}
}

function updateDescript(){
	var xq = document.getElementById('xq_value').value;
	if(xq != '' && xq != '来，说说你在做什么，想什么'){
		document.getElementById('xq').innerHTML = '心情：' + xq;
		new Ajax.Request(
	  	'/pcenter.do?method=updateDescript&descript='+encodeURI(encodeURI(xq))+'&ram=' + Math.random(),
	  	{
	  		method: 'get',
	  		asynchronous:true,//true为异步请求
	  		onComplete:function(xhr){}
	  	});
	}
}

function checkValue(){
	var xq = document.getElementById('xq_value').value;
	if(xq == '来，说说你在做什么，想什么'){
		document.getElementById('xq_value').value = '';
		document.getElementById('xq_value').style.color = '#000000';
	}
}

function uploadPhoto(){
  var retValue=showModalDialog('/sysImageUploadAction.do?method=uploadimage&savepath=user&pathtype=ID','缩略图',"dialogWidth:350px;dialogHeight:200px;scroll=no;border=thin;help=0;status=no");
  if(retValue!=null){
     if(retValue[0] != ''){
  	 	alert(retValue[0]);
  		return false;
  	 }
     document.getElementById('userphoto').src="/upload_dir/"+retValue[1];
     
     //ajax保存头像信息
     var photo = "/upload_dir/"+retValue[1];
     new Ajax.Request(
	  	'/sysUserInfoAction.do?method=updatePhoto&userid=<bean:write name="s_sysuserinfo" property="userid"/>&photo='+photo+'&ram=' + Math.random(),
	  	{
	  		method: 'get',
	  		asynchronous:true,//true为异步请求
	  		onComplete:function(xhr){}
	  	});
  }
}

/*屏蔽所有的js错误*/ 
function killerrors() {
	return true; 
} 
window.onerror = killerrors; 
//-->
</script>

</head>
<body>
<script type="text/javascript" id="login_top" src="/skin/pcenter/top.js?uid=${s_unitid}"></script>
<div id="box">
<%@ include file="../pcenter/menu.jsp"%>
<!--#navList-->

<div id="grzx_left">
<form name="menuForm" method="post" target="_top">
<div id="grzx_one">
<div id="gr_xx">
<dl>
<dt><a href="/sns-user-0-<bean:write name="s_sysuserinfo" property="userid"/>.htm" target="_top" title="个人空间"><img id="userphoto" src="<bean:write name="s_sysuserinfo" property="photo"/>" width="118" height="108" border="0"/></a></dt>
<dd class="gr05"><a href="/sns-follow-1-<bean:write name="s_sysuserinfo" property="userid"/>.htm" target="_top">关注(<bean:write name="gzcount"/>)</a></dd>
<dd class="gr06"><a href="/sns-follow-2-<bean:write name="s_sysuserinfo" property="userid"/>.htm" target="_top">粉丝(<bean:write name="fscount"/>)</a></dd>
<dd class="gr01"><a href="javascript:setMenu()">设置菜单</a></dd>
<dd class="gr02"><a href="javascript:setLocation('res', 'sysUserInfoAction.do?method=beforeUpdateSelf')">个人资料</a></dd>
<dd class="gr03"><a href="javascript:setSubject()">设置<bean:write name="SC_szxysubjectname"/></a></dd>
<dd style="background:url(/skin/desktop/images/userphoto.png) no-repeat left center;"><a href="javascript:uploadPhoto()">上传头像</a></dd>
<!-- <dd class="gr04"><a href="javascript:setLocation('res', 'sysUserInfoAction.do?method=modifyPassword')">修改密码</a></dd> -->
</dl>
</div><!--#gr_xx-->

<div id="gr_zl">
<div id="name">
<span class="name01"><bean:write name="s_sysuserinfo" property="username"/></span>
<span class="name03">今天是：<%=DateTime.getDateYMDWCN() %></span>
</div><!--#name-->

<div id="tj">
<ul>
<!-- 
<li><a href="javascript:setLocation('jxt', 'gpwLeaveWordAction1.do?method=list&status=0')">留言</a>(<span class="red"><bean:write name="unreadmsg"/></span>)</li>
 -->
<li><a href="javascript:setLocation('msg', 'oaBulletinInfoAction.do?method=list2')">消息</a>(<span class="red"><bean:write name="unreadcount"/></span>)</li>
<logic:present name="unreadcount2">
<li><a href="javascript:setLocation('msg', 'oaBulletinInfoAction2.do?method=list2')">通知</a>(<span class="red"><bean:write name="unreadcount2"/></span>)</li>
</logic:present>
<logic:present name="unreadcount3">
<li><a href="javascript:setLocation('msg', 'oaBulletinInfoAction3.do?method=list2')">公文</a>(<span class="red"><bean:write name="unreadcount3"/></span>)</li>
</logic:present>
<logic:notEqual value="0" name="isAdmin" scope="session">
<li style="width:110px;"><a href="javascript:setLocation('res', 'eduResInfoCheckAction.do?method=list')">待审核资源</a>(<span class="red"><bean:write name="checkrescount"/></span>)</li>
</logic:notEqual>
</ul>
</div><!--#tj-->

<div id="xq">心情：<bean:write name="s_sysuserinfo" property="descript" filter="false"/></div>

<div id="xq_bj">
<span class="xq_bj_left"><textarea name="xq_value" id="xq_value" style="width:440px; height:55px; border:#e3e3e3 1px solid; color:gray;" onclick="checkValue()">来，说说你在做什么，想什么</textarea></span>
<span class="xq_bj_right"><a style="cursor:pointer;" onclick="updateDescript()"><img src="/skin/desktop/images/gx.png" border="0" /></a></span>
</div><!--#xq_bj-->

</div><!--#gr_zl-->
</div><!--#grzx_one-->
<input type="hidden" name="onclickmenu" id="onclickmenu" value=""/>
</form>
<iframe id="usermenuFrame" name="usermenuFrame" width="100%" height="50" onload="SetCwinHeight('usermenuFrame')" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/pcenter.do?method=usermenu"></iframe>

<iframe id="beikeFrame" name="beikeFrame" width="100%" height="50" onload="SetCwinHeight('beikeFrame')" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/pcenter.do?method=beike"></iframe>

<iframe id="resFrame" name="resFrame" width="100%" height="50" onload="SetCwinHeight('resFrame')" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/pcenter.do?method=res"></iframe>

</div><!--#grzx_left-->

<div id="grzx_right">

<form name="msgForm" method="post" target="_top">
<div id="scxx">
<ul id="scroll">
<logic:iterate id="usermsg" name="usermsgList">
<li><a href=javascript:autoRead('oaBulletinInfoAction.do?method=list2&autoRead=<bean:write name="usermsg" property="limitid"/>')><bean:write name="usermsg" property="oaBulletinInfo.title"/></a></li>
</logic:iterate>
<logic:iterate id="sysmsg" name="sysmsgList">
<li><bean:write name="sysmsg" property="htmlcontent" filter="false"/></li>
</logic:iterate>
</ul>
</div><!--#scxx-->
<input type="hidden" name="onclickmsg" id="onclickmsg" value=""/>
</form>

<!-- iframe id="tableFrame" name="tableFrame" width="100%" height="50" onload="SetCwinHeight('tableFrame')" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/pcenter.do?method=table"></iframe-->
<iframe id="userFrame" name="userFrame" width="100%" height="50" onload="SetCwinHeight('userFrame')" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/pcenter.do?method=activeUser"></iframe>
<!-- iframe id="vodFrame" name="vodFrame" width="100%" height="50" onload="SetCwinHeight('vodFrame')" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/pcenter.do?method=vod"></iframe-->

<div id="xxtj" style="margin-top:0px;">
<h3>统计信息</h3>
<ul>
<li>我的头衔：<span class="red"><bean:write name="userTitle" property="name"/></span></li>
<li>我的积分：<span class="red" style="margin-right:2px;"><bean:write name="userInfoDetail" property="jifen"/></span>积分</li>
<li>我的财富：<span class="red" style="margin-right:2px;"><bean:write name="userInfoDetail" property="caifu"/></span>财富值</li>
<li>联系电话：<span class="red"><bean:write name="userInfo" property="mobile"/></span></li>
<li>总上载资源数：<span class="red" style="margin-right:2px;"><bean:write name="count1"/></span>份</li>
<li>未审核资源数：<span class="red" style="margin-right:2px;"><bean:write name="count2"/></span>份</li>
<li>上月上载资源数：<span class="red" style="margin-right:2px;"><bean:write name="count3"/></span>份</li>
<li>本月上载资源数：<span class="red" style="margin-right:2px;"><bean:write name="count4"/></span>份</li>
<li>今日上载资源数：<span class="red" style="margin-right:2px;"><bean:write name="count5"/></span>份</li>
<li>资源被下载总数：<span class="red" style="margin-right:2px;"><bean:write name="totaldownload"/></span>次</li>
<li>今日资源被下载数：<span class="red" style="margin-right:2px;"><bean:write name="todaydownload"/></span>次</li>
<li>资源被点击总数：<span class="red" style="margin-right:2px;"><bean:write name="totalonclick"/></span>次</li>
<li>最后登录：<span class="red"><bean:write name="userInfoDetail" property="lastlogin"/></span></li>
<li>注册时间：<span class="red"><bean:write name="userInfo" property="createdate"/></span></li>
</ul>
</div><!--#xxtj-->

</div><!--#grzx_right-->

</div><!--#box-->
<iframe id="footerFrame" name="footerFrame" width="100%" height="45" frameborder="0" marginheight="0" marginwidth="0" scrolling=no src="/plogin.do?method=footer"></iframe>
</body>
</html>
