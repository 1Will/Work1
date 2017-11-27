<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/public/css/pcenter/pcenter.css" rel="stylesheet" type="text/css" />
<!--script type="text/javascript" src="/public/js/pcenter/iepng.js"></script-->
<script type="text/javascript" src="/public/js/pcenter/jquery-1.4.js"></script>
<script type="text/javascript" src="/public/js/pcenter/simpleMenu_split.js"></script>
</head>
<body>
<%-- 
<div id="navList">
<ul>

<li>
<a id="menu_site_2_1" href="/pcenter.do?method=index&mproduct=cms&siteid=1" class="nav"><img src="/public/images/pcenter/zt02.png" />
<span>门户网站</span></a>
</li>

<!-- 
<li>
<a id="menu_site_2_2" href="/pcenter.do?method=index&mproduct=cms&siteid=2" class="nav"><img src="/public/images/pcenter/zt01.png" />
<span>验收与成果</span></a>
</li>
-->
<li>
<a id="menu_site_3" href="/pcenter.do?method=index&mproduct=zxbk" class="nav"><img src="/public/images/pcenter/t02.png" />
<span>在线备课</span></a>
</li>
<li>
<a id="menu_site_9" href="/pcenter.do?method=index&mproduct=res" class="nav"><img src="/public/images/pcenter/t21.png" />
<span>资源中心</span></a>
</li>
<li>
<a id="menu_site_11" href="/pcenter.do?method=index&mproduct=tar" class="nav"><img src="/public/images/pcenter/t23.png" />
<span>教研中心</span></a>
</li>
<!-- 
<li>
<a id="menu_site_8" href="/pcenter.do?method=index&mproduct=pic" class="nav"><img src="/public/images/pcenter/t24.png" />
<span>图库中心</span></a>
</li>
  -->
<li>
<a id="menu_site_4" href="/pcenter.do?method=index&mproduct=vod" class="nav"><img src="/public/images/pcenter/t03.png" />
<span>微课系统</span></a>
</li>
<!--
 -->
 
<li>
<a id="menu_site_5" href="/pcenter.do?method=index&mproduct=baoxiu" class="nav"><img src="/public/images/pcenter/t04.png" />
<span>报修管理</span></a>
</li>
<li>
<a id="menu_site_6" href="/pcenter.do?method=index&mproduct=file" class="nav"><img src="/public/images/pcenter/t05.png" />
<span>网络硬盘</span></a>
</li>
 <!---->
<li>
<a id="menu_site_7" href="/pcenter.do?method=index&mproduct=msg" class="nav"><img src="/public/images/pcenter/t11.png" />
<span>消息管理</span></a>
</li>
<!--
<li>
<a id="menu_site_12" href="/pcenter.do?method=index&mproduct=eva" class="nav"><img src="/public/images/pcenter/zt02.png" />
<span>评价管理</span></a>
</li>
 
<li>
<a id="menu_site_8" href="/pcenter.do?method=index&mproduct=oa" class="nav"><img src="/public/images/pcenter/t20.png" />
<span>内部办公</span></a>
</li>

<li>
<a id="menu_site_10" href="/pcenter.do?method=index&mproduct=jxt" class="nav"><img src="/public/images/pcenter/t22.png" />
<span>家校互通</span>
</a>
</li>
 -->
 <li>
<a id="menu_site_12" href="/skin/pcenter/gotourl.jsp" target="_blank" class="nav"><img src="/public/images/pcenter/zt01.png" />
<span>数字期刊</span></a>
</li>
<li>
<a id="menu_site_1" href="/pcenter.do?method=umanager" class="nav"><img src="/public/images/pcenter/t10.png" />
<span>系统管理</span>
</a>
</li>
<!-- 
<li>
<a id="menu_site_0" href="http://117.117.231.2/uam/system/updateUserPersonalInf.action?menusId=" class="nav" target="_blank"><img src="/public/images/pcenter/pw.png" />
<span>修改密码</span>
</a>
</li>
 -->
</ul>
<script type="text/javascript">
var mproduct = '<%=request.getAttribute("mproduct") %>';
if(mproduct == 'user'){
	document.getElementById('menu_site_1').className = 'nav2';
}
if(mproduct == 'cms'){
	var siteid = '<%=session.getAttribute("s_siteid") %>';
	document.getElementById('menu_site_2_'+siteid).className = 'nav2';
	//document.getElementById('menu_site_2').className = 'nav2';
}
if(mproduct == 'zxbk'){
	document.getElementById('menu_site_3').className = 'nav2';
}
if(mproduct == 'vod'){
	document.getElementById('menu_site_4').className = 'nav2';
}
if(mproduct == 'baoxiu'){
	document.getElementById('menu_site_5').className = 'nav2';
}
if(mproduct == 'file'){
	document.getElementById('menu_site_6').className = 'nav2';
}
if(mproduct == 'msg'){
	document.getElementById('menu_site_7').className = 'nav2';
}
if(mproduct == 'pic'){
	document.getElementById('menu_site_8').className = 'nav2';
}
if(mproduct == 'res'){
	document.getElementById('menu_site_9').className = 'nav2';
}
if(mproduct == 'jxt'){
	document.getElementById('menu_site_10').className = 'nav2';
}
if(mproduct == 'tar'){
	document.getElementById('menu_site_11').className = 'nav2';
}
if(mproduct == 'eva'){
	document.getElementById('menu_site_12').className = 'nav2';
}
</script>
</div>
--%>
</body>
</html>