<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME%>-统计</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/style.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery1.9.js"></script>
<script type="text/jscript" src="/skin/wkmk/js/jqnav.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS%>">
	<meta name="description" itemprop="description"
		content="<%=Constants.SYS_PRODUCT_KEYWORDS%>">
</head>
	<script >
 function changeStatistic(temp,province){
    var form=document.getElementById("form");;
    form.action="v-statistics2-"+temp+"-"+province+".htm";
    form.submit();
 }
 function play(){
  var province=${province};
  document.getElementById(province).style.display="block";
 }
</script>
<body onload="play()">
<%@ include file="top.jsp"%>
<div class="map">
<form id="form" method="post">
<img src="/skin/wkmk/images/map.jpg" width="716" height="595" usemap="#Map" border="0"/> 
	<div class="city"><div id="1" class="citybg" style="background:url('/skin/wkmk/images/anhui.png') no-repeat 0 0; top:314px; left:523px; width:75px; height:90px;"></div><a style=" position:absolute; top:355px; left:545px; z-index:10;" href="javascript:changeStatistic('0012','1');">安徽</a></div>
	<div class="city"><div id="2" class="citybg" style="background:url(/skin/wkmk/images/neimeng.png) no-repeat 0 0;  top:9px; left:296px; width:318px; height:272px; "></div><a style="position:absolute; top:211px; left:424px; z-index:10;" href="javascript:changeStatistic('0005','2');">内蒙古</a></div>
	<div class="city"><div id="3" class="citybg" style=" background:url(/skin/wkmk/images/heilongjiang.png) no-repeat 0 0; top:1px; left:550px; width:165px; height:151px;"></div><a style="position:absolute;top:81px; left:624px; z-index:10;" href="javascript:changeStatistic('0008','3');">黑龙江</a></div>
	<div class="city"><div id="4" class="citybg" style=" background:url(/skin/wkmk/images/xinjiang.png) no-repeat 0 0; top:73px; left:0px; width:292px; height:223px;"></div><a style="position:absolute;top:211px; left:124px; z-index:10;" href="javascript:changeStatistic('0031','4');">新疆</a></div>
	<div class="city"><div id="5" class="citybg" style=" background:url(/skin/wkmk/images/xizang.png) no-repeat 0 0; top:275px; left:31px; width:281px; height:175px;"></div><a style="position:absolute;top:361px; left:145px; z-index:10;" href="javascript:changeStatistic('0026','5');">西藏</a></div>
	<div class="city"><div id="6" class="citybg" style=" background:url(/skin/wkmk/images/qinghai.png) no-repeat 0 0; top:240px; left:182px; width:185px; height:135px;"></div><a style="position:absolute;top:300px; left:254px; z-index:10;" href="javascript:changeStatistic('0029','6');">青海</a></div>
	<div class="city"><div id="7" class="citybg" style=" background:url(/skin/wkmk/images/gansu.png) no-repeat 0 0; top:187px; left:236px; width:207px; height:177px;"></div><a style="position:absolute;top:310px; left:364px; z-index:10;" href="javascript:changeStatistic('0028','7');">甘肃</a></div>
	<div class="city"><div id="8" class="citybg" style=" background:url(/skin/wkmk/images/ningxia.png) no-repeat 0 0; top:245px; left:379px; width:49px; height:75px;"></div><a style="position:absolute;top:272px; left:390px; z-index:10;" href="javascript:changeStatistic('0030','8');">宁夏</a></div>
	<div class="city"><div id="9" class="citybg" style=" background:url(/skin/wkmk/images/shanghai.png) no-repeat 0 0; top:354px; left:610px; width:23px; height:22px;"></div><a style="position:absolute;top:352px; left:615px;; z-index:10;" href="javascript:changeStatistic('0009','9');">上海</a></div>
	<div class="city"><div id="10" class="citybg" style=" background:url(/skin/wkmk/images/liaoning.png) no-repeat 0 0; top:161px; left:557px; width:91px; height:87px;"></div><a style="position:absolute;top:180px; left:600px; z-index:10;" href="javascript:changeStatistic('0006','10');">辽宁</a></div>
	<div class="city"><div id="11" class="citybg" style=" background:url(/skin/wkmk/images/guangdong.png) no-repeat 0 0; top:470px; left:464px; width:111px; height:88px;"></div><a style="position:absolute;top:490px; left:500px; z-index:10;" href="javascript:changeStatistic('0019','11');">广东</a></div>
	<div class="city"><div id="12"  class="citybg" style=" background:url(/skin/wkmk/images/guangxi.png) no-repeat 0 0; top:454px; left:382px; width:118px; height:92px;"></div><a style="position:absolute;top:488px; left:432px; z-index:10;" href="javascript:changeStatistic('0020','12');">广西</a></div>
	<div class="city"><div id="13" class="citybg" style=" background:url(/skin/wkmk/images/henan.png) no-repeat 0 0; top:288px; left:461px; width:118px; height:92px;"></div><a style="position:absolute;top:320px; left:490px; z-index:10;" href="javascript:changeStatistic('0016','13');">河南</a></div>
	<div class="city"><div id="14" class="citybg" style=" background:url(/skin/wkmk/images/shanxi.png) no-repeat 0 0; top:242px; left:396px; width:79px; height:134px;"></div><a style="position:absolute;top:321px; left:430px; z-index:10;" href="javascript:changeStatistic('0027','14');">陕西</a></div>
	<div class="city"><div id="15" class="citybg" style=" background:url(/skin/wkmk/images/shanxi2.png) no-repeat 0 0; top:219px; left:458px; width:56px; height:112px;"></div><a style="position:absolute;top:271px; left:470px; z-index:10;" href="javascript:changeStatistic('0004','15');">山西</a></div>
	<div class="city"><div id="16" class="citybg" style=" background:url(/skin/wkmk/images/hebei.png) no-repeat 0 0; top:184px; left:497px; width:85px; height:118px;"></div><a style="position:absolute;top:247px; left:508px; z-index:10;" href="javascript:changeStatistic('0003','16');">河北</a></div>
	<div class="city"><div id="17" class="citybg" style=" background:url(/skin/wkmk/images/jilin.png) no-repeat 0 0; top:113px; left:575px; width:125px; height:88px;"></div><a style="position:absolute;top:150px; left:642px; z-index:10;" href="javascript:changeStatistic('0007','17');">吉林</a></div>
	<div class="city"><div id="18" class="citybg" style=" background:url(/skin/wkmk/images/beijing.png) no-repeat 0 0; top:210px; left:512px; width:50px; height:38px;"></div><a style="position:absolute;top:215px; left:515px; z-index:10;" href="javascript:changeStatistic('0001','18');">北京</a></div>
	<div class="city"><div id="19" class="citybg" style=" background:url(/skin/wkmk/images/tianjin.png) no-repeat 0 0; top:224px; left:535px; width:26px; height:34px;"></div><a style="position:absolute;top:229px; left:535px; z-index:10;" href="javascript:changeStatistic('0002','19');">天津</a></div>
	<div class="city"><div id="20" class="citybg" style=" background:url(/skin/wkmk/images/shandong.png) no-repeat 0 0; top:256px; left:521px; width:103px; height:68px;"></div><a style="position:absolute;top:281px; left:540px; z-index:10;" href="javascript:changeStatistic('0015','20');">山东</a></div>
	<div class="city"><div id="21" class="citybg" style=" background:url(/skin/wkmk/images/jiangsu.png) no-repeat 0 0; top:305px; left:539px; width:93px; height:72px;"></div><a style="position:absolute;top:321px; left:570px; z-index:10;" href="javascript:changeStatistic('0010','21');">江苏</a></div> 
	<div class="city"><div id="22" class="citybg" style=" background:url(/skin/wkmk/images/hainan.png) no-repeat 0 0; top:556px; left:442px; width:89px; height:88px;"></div><a style="position:absolute;top:565px; left:450px; z-index:10;" href="javascript:changeStatistic('0021','22');">海南</a></div>  
	<div class="city"><div id="23" class="citybg" style=" background:url(/skin/wkmk/images/hubei.png) no-repeat 0 0; top:345px; left:438px; width:115px; height:75px;"></div><a style="position:absolute;top:365px; left:480px; z-index:10;" href="javascript:changeStatistic('0017','23');">湖北</a></div>
	<div class="city"><div id="24" class="citybg" style=" background:url(/skin/wkmk/images/yunnan.png) no-repeat 0 0; top:412px; left:280px; width:132px; height:138px;"></div><a style="position:absolute;top:485px; left:320px; z-index:10;" href="javascript:changeStatistic('0025','24');">云南</a></div>
	<div class="city"><div id="25" class="citybg" style=" background:url(/skin/wkmk/images/sichuan.png) no-repeat 0 0; top:328px; left:284px; width:161px; height:143px;"></div><a style="position:absolute;top:385px; left:345px; z-index:10;" href="javascript:changeStatistic('0023','25');">四川</a></div>
	<div class="city"><div id="26" class="citybg" style=" background:url(/skin/wkmk/images/guizhou.png) no-repeat 0 0; top:409px; left:367px; width:93px; height:81px;"></div><a style="position:absolute;top:445px; left:405px; z-index:10;" href="javascript:changeStatistic('0024','26');">贵州</a></div> 
	<div class="city"><div id="27" class="citybg" style=" background:url(/skin/wkmk/images/taiwan.png) no-repeat 0 0; top:456px; left:613px; width:32px; height:65px;"></div><a style="position:absolute;top:475px; left:620px; z-index:10;" href="javascript:changeStatistic('0','27');">台湾</a></div>
	<div class="city"><div id="28" class="citybg" style=" background:url(/skin/wkmk/images/fujian.png) no-repeat 0 0; top:415px; left:548px; width:70px; height:84px;"></div><a style="position:absolute;top:445px; left:565px; z-index:10;" href="javascript:changeStatistic('0013','28');">福建</a></div>
	<div class="city"><div id="29" class="citybg" style=" background:url(/skin/wkmk/images/hunan.png) no-repeat 0 0; top:394px; left:445px; width:83px; height:96px;"></div><a style="position:absolute;top:425px; left:475px; z-index:10;" href="javascript:changeStatistic('0018','29');">湖南</a></div>
	<div class="city"><div id="30" class="citybg" style=" background:url(/skin/wkmk/images/zhejiang.png) no-repeat 0 0; top:367px; left:574px; width:62px; height:70px;"></div><a style="position:absolute;top:395px; left:588px; z-index:10;" href="javascript:changeStatistic('0011','30');">浙江</a></div>
	
	<div class="city"><div id="31" class="citybg" style=" background:url(/skin/wkmk/images/jiangxi.png) no-repeat 0 0; top:390px; left:513px; width:76px; height:98px;"></div><a style="position:absolute;top:425px; left:525px; z-index:10;" href="javascript:changeStatistic('0014','31');">江西</a></div>
	<div class="city"><div id="32" class="citybg" style=" background:url(/skin/wkmk/images/chongqing.png) no-repeat 0 0; top:363px; left:397px; width:70px; height:80px;"></div><a style="position:absolute;top:390px; left:420px; z-index:10;" href="javascript:changeStatistic('0022','32');">重庆</a></div>
</form>
</div>
<div class="statistics">
  	<%
  	String statistics = (String)request.getAttribute("statistics");
  	String[] tj = statistics.split(";");
  	%>
	<div class="statistics_p">
		<p id="t1"><%=tj[0] %>所</p>
    </div>
    <div class="statistics_p">
    	<p><%=tj[1] %>名</p>
    </div>
    <div class="statistics_p">
    	<p><%=tj[2] %>个</p>
    </div>
    <div class="statistics_p">
    	<p><%=tj[3] %>个</p>
    </div>
    <div class="statistics_p">
    	<p class="statistics_p_1"><%=tj[4] %>份</p>
    </div>   
</div>
<%@ include file="footer.jsp"%>
</body>
</html>