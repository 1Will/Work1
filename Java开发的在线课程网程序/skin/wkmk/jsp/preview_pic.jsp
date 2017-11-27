<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-<bean:write name="f_unitinfo" property="unitname"/>-<bean:write name="model" property="title"/></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> <bean:write name="f_unitinfo" property="keywords"/> <bean:write name="model" property="title"/>">
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  
<div class="nph_area">
	<img id="photoPrevLoading" src="/skin/wkmk/picshow/images/bg06.png" width="0" height="0"/>
	<div class="nph_rela" style="display:none;">
	    <div id="nhp_poparea"> <a href="#" class="nhp_pclose"></a> <span>支持键盘← →键翻阅图片</span> </div>
	</div>
	<div class="nph_gallery clearfix" id="gallery">
	<div class="nph_bg">
		<!-- 标题开始 -->
		<div class="nph_cnt clearfix">
	    	<div class="nph_set_info">
	        	<div id="setInfo">
	            <div class="nph_set_title">
	            	<h1><bean:write name="model" property="title"/></h1>
	                <span class="nph_set_cur hidden" id="photoType">（<span id="photoIndex" class="nph_c_lh"></span>/<bean:write name="photonum"/>）</span><span class="nph_set_size" id="streamType">（共<span class="nph_c_lh"><bean:write name="photonum"/></span>张）</span><span><java2page:write name="model" property="createdate" length="16"/></span><span style="float:right;"><a href="#" onclick="doView();">查看原图</a>&nbsp;</span><span style="float:right;"><a href="javascript:download();">[下载图集]</a>&nbsp;&nbsp;&nbsp;&nbsp;</span> 
	            </div>
	        	</div>
		        <div id="searchTheme" class="hidden"> <a href="" class="nph_btn_return" id="viewPhoto">&lt; 返回</a>
		            <h3 class="hidden">找到<span></span>相关图片<span class="nph_search_count">（共<span class="nph_c_lh"></span>张）</span></h3>
		            <h3 class="hidden">未找到<span></span>相关图片</h3>
		        </div>
	    	</div>
	    </div>
	    <!-- 标题结束 -->
	    <span class="nph_hr_solid"></span>
	    <div id="modePhoto" class="nph_photo">
	    	<!-- 图片显示区域开始 -->
		    <div class="nph_photo_view" style="width:100%; position:relative; text-align:center;">
		    	<div class="nph_photo_desc"><font size="4" id="photoDescSpan"></font></div>
		    	<div class="nph_cnt" id="photoView"><img src="/skin/wkmk/picshow/images/bg06.png" id="photo"/></div>
		        <div class="nph_photo_prev"><a href="#" hidefocus="true" target="_self" class="nph_btn_pphoto" id="photoPrev"><span></span></a></div>
		        <div class="nph_photo_next"><a href="#" hidefocus="true" target="_self" class="nph_btn_nphoto" id="photoNext"><span></span></a></div>
		        <div class="nph_photo_loading" id="photoLoading">加载中...</div>
		        <div style="width:100%; height:100%;position:absolute; left: 0; top:0;" id="photoLayout">
		            <div class="nph_layout_bg"></div>
		            <div class="nph_photo_layout" id="photomiddle" style="display:none;">
		                <div class="nph_layout_cnt"> <a href="#close" target="_self" class="nph_layout_close"></a>
		                    <div class="nph_layout_hd">已经到最后一张了。<a href="#again" target="_self" class="nph_btn_again">重新浏览</a></div>
		                    <div class="nph_layout_bd">
		                        <h6></h6>
		                        <ul class="nph_list_relat clearfix">
		                            <li><div></div><h5></h5></li>
		                            <li><div></div><h5></h5></li>
								</ul>
		                    </div>
		                </div>
		            </div>
		    	</div>
		    </div>
		    <!-- 图片显示区域结束 -->
		    <div id="photoDesc" style="display:none;"></div>
		    <span class="nph_hr_solid_half1"></span>
		    <span class="nph_hr_solid_half2"></span>
		    <div class="nph_cnt">
		        <div class="clearfix">
		            <div class="nph_set">
		                <div class="nph_set_thumb">
		                    <div class="nph_photo_thumb clearfix" id="scrl">
		                        <div class="clearfix">
		                            <div class="nph_scrl">
		                                <div class="nph_scrl_thumb">
		                                    <div class="nph_scrl_main">
		                                        <ul class="nph_list_thumb clearfix" id="thumb"></ul>
		                                    </div>
		                                    <div class="nph_scrl_bar clearfix"> <span class="nph_scrl_lt"></span> <span class="nph_scrl_rt"></span>
		                                        <div class="nph_scrl_bd">
		                                            <div class="nph_scrl_ct"> <a href="" hidefocus="true" class="nph_btn_scrl" id="bar"> <b class="nph_btn_lt"></b> <b class="nph_btn_rt"></b> <span class="nph_btn_bd"><span><b class="nph_btn_ct"></b></span></span> </a> </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                            <span class="nph_scrl_prev"><a href="" hidefocus="true" class="nph_btn_pscrl" id="scrlPrev"></a></span> <span class="nph_scrl_next"><a href="" hidefocus="true" class="nph_btn_nscrl" id="scrlNext"></a></span> 
		                            </div>
		                    </div>
		                </div>
		            </div>
		            <!-- 小图片显示区域开始 -->
		            <textarea class="hidden" id="photoList">
					<logic:iterate id="photo" name="list" indexId="ii">
						<%if(ii == 0){ %>
						<input type="hidden" name="hiddenphotoid" id="hiddenphotoid" value="<bean:write name="photo" property="photoid"/>"/>
						<%} %>
						<li>
				        <a href="#p=<bean:write name="photo" property="photoid"/>" hidefocus="true"><img src="<bean:write name="photo" property="sketch"/>" title="<bean:write name="photo" property="descript"/>"  border="0" /></a>
				        <h2></h2>
				        <p><java2page:write name="photo" property="descript" length="52"/></p>
				        <i title="img"><bean:write name="photo" property="picpath"/></i>
				        <i title="timg"><bean:write name="photo" property="sketch"/></i>
				        </li>
					</logic:iterate>
					</textarea>
					<!-- 小图片显示区域结束 -->
				</div>
			</div>
		</div>
	</div>
	</div>
</div>
<link type="text/css" rel="stylesheet" href="/skin/wkmk/picshow/css/style.css"/>
<link type="text/css" rel="stylesheet" href="/skin/wkmk/picshow/css/nph_gallery_2.15_1.css"/>
<script type="text/javascript" language="javascript" src="/skin/wkmk/picshow/js/ntes_jslib_1.x.js" charset="gb2312"></script>
<script type="text/javascript" language="javascript" src="/skin/wkmk/picshow/js/ntes_ui_scroll.js" charset="gb2312"></script>
<script type="text/javascript" language="javascript" src="/skin/wkmk/picshow/js/nph_gallery_2.13_1.js" charset="gb2312"></script>
<script type="text/javascript">
	NTES.ready(function ($) {
    	var nphLayoutGG = $('#nphLayoutGG');
        if (nphLayoutGG) {
            nphLayoutGG.addCss({ display: 'block', position: 'absolute', left: 0, top: '10px', width: '280px', height: '210px' });
            $('#photomiddle').addCss({display: 'block', width: '564px', marginLeft: '-282px' });
            $('#photomiddle').appendChild(nphLayoutGG);
            new nph.Gallery({ photoViewMode: 2 });
        } else {
            new nph.Gallery({ photoViewMode: 0 });
    	}
    	var tieCountNode = NTES.one('#tieArea span.tieTotalCount') || NTES.one('#tieArea span.tieCount');
    	if (tieCountNode) {
        	$('#tieCount').innerHTML = String.format('(%1)', tieCountNode.innerHTML);
    	}
    	/*按键提示层*/
    	nph.popinit = function(){
        	var nhp_poparea = $('#nhp_poparea');
        	var getcookie = NTES.cookie.get("nphStorage");
        	if(getcookie == 'tips'){
            	$("#nhp_poparea").addCss({display:'none'});
        	}else{
            	if(nhp_poparea){
	                $("#nhp_poparea").addCss({display:'block'});
	                setTimeout(function(){$("#nhp_poparea").addCss({display:'none'});},10000);
	                nhp_poparea.$(".nhp_pclose").addEvent('click',function(ev){
	                    ev.preventDefault();
	                    nph.hide(nhp_poparea);
	                    NTES.cookie.set("nphStorage", "tips" , "1M" );
	                });
            	}
        	}
    	}
    	nph.popinit();
    });
    
    function doView(){
		var str = document.location.href;
		var url = "/v.bo?method=photo";
		if(str.indexOf("#p=") != -1){
			url += "&objid=" + str.substring(str.indexOf("#p=") + 3);
		}else{
			url += "&objid=" + document.getElementById('hiddenphotoid').value;
		}
		window.open(url);
	}
	
	window.onload=function(){
 		var dl = document.getElementsByTagName("dl");
 		if(dl.length<1) return false;
 		for(var i=0; i<dl.length; i++){
  			//初始化，让第一个类为over
  			if(dl[i].className.indexOf("over")==-1){
   				dl[0].className="over";
  			}
  			//遍历循环，模拟:hover伪类
  			dl[i].onmouseover=function(){
   				for(var j=0;j<dl.length;j++){
    				dl[j].className="";
   				}
   				this.className="over";
  			}
 		}
	}
	
	function download(){
		window.location.href = '/v.bo?method=downloadPhoto&objid=<bean:write name="model" property="annexid"/>';
	}
	
	function killerrors() {
		return true;
	}
	window.onerror = killerrors;
</script>
  
</div>
<div>&nbsp;</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>