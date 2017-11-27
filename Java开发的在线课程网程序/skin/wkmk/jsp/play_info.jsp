<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodFilmTip"%>
<div id="a1"></div>
<script type="text/javascript" src="/ckplayer/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
	function playerstop(){
		window.location.href = '/v-play2-<bean:write name="vodFilmLet" property="unitid"/>-<bean:write name="vodFilmLet" property="filmletid"/>.htm';
	}
	
	var ptime = 0;
	function ckplayer_status(str){
		if(str=='newVideo'){
			setInterval('getstart()',1000);
		}
		if(str.indexOf('time:') != -1){
			ptime = parseInt(str.replace('time:',''));
		}
	}
	
	function getstart(){
		//30秒后需要登录才能查看
		//if(ptime >= 180){
		//	<logic:notPresent name="s_sysuserinfo">
		//	CKobject.getObjectById('ckplayer_a1').videoPause();//暂停播放
		//	alert("请您先登录再继续观看，没有账号请先注册！");
		//	var topurl = window.top.location;
		//	window.top.location = '/plogin.do?method=slogin&redirecturl=' + topurl;
		//	</logic:notPresent>
		//}
	}
	
	var flashvars={
		f:'/v.bo?method=url%26vid=[$pat]',
		a:'<bean:write name="playid"/>',
		s:'1',
		c:'0',
		e:'0',
		v:'80',
		p:'1',
		h:'3',
		my_url:encodeURIComponent(window.location.href)
		};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always'};
	var video=['/v.bo?method=url&vid=<bean:write name="playid"/>->ajax/get/utf-8'];
	CKobject.embed('/ckplayer/ckplayer/ckplayer.swf','a1','ckplayer_a1','810','460',false,flashvars,video,params);
  </script>