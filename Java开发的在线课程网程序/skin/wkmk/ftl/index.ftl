<#setting number_format="#">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微课慕课网-分享微课 分享价值</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/jquery.jslides-banner.js"></script>
<script type="text/javascript" src="/skin/wkmk/js/mobile-check.js"></script>
<meta name="keywords" content="微课慕课网 微课 慕课 mooc 微课大赛 慕课大赛 翻转课堂 课程学习 在线学习 文库 文档 教案 课件 习题 试卷 中国微课 中国慕课 微课在线 慕课在线 微课学习 慕课学习 基础教育微课大赛 职业教育微课大赛 高等教育微课大赛 继续教育微课大赛 慕课网 中小学微课大赛 k12教育微课 www.wkmk.com www.wkmk.cn www.wkmk.org www.wkmk.net dasai.wkmk.com www.iweike.org.cn ${base_unitinfo.keywords }">
<meta name="description" itemprop="description" content="微课慕课网 微课 慕课 mooc 微课大赛 慕课大赛 翻转课堂 课程学习 在线学习 文库 文档 教案 课件 习题 试卷 中国微课 中国慕课 微课在线 慕课在线 微课学习 慕课学习 基础教育微课大赛 职业教育微课大赛 高等教育微课大赛 继续教育微课大赛 慕课网 中小学微课大赛 k12教育微课 www.wkmk.com www.wkmk.cn www.wkmk.org www.wkmk.net dasai.wkmk.com www.iweike.org.cn ${base_unitinfo.descript }">
</head>

<body>
<div id="header_h">
<div class="header">
  <div class="logo">
    <a href="/default.html"></a>
  </div>
  <div class="seach">
    <form name="searchForm" method="post">
    <div class="mod-search">
      <div id="lr_systembox">
	    <div id="lr_systembtn" class="lr_systembtn">
	        <a href="javascrip:;" class="lr_abtn"><span id="curSearchType">微课</span></a>
	        <div id="lr_menu" class="lr_menu"> 
	          <dl>
	             <dt><a href="javascript:changeSearchType('2')">文档</a></dt>
	             <dt><a href="javascript:changeSearchType('3')">教师</a></dt>
	             <dt><a href="javascript:changeSearchType('4')">学校</a></dt>
	          </dl>     
	        </div>
	    </div>
	  </div>
      <input type="text" placeholder="请输入搜索关键字" name="keywords" value="" class="mod-search__input" maxlength="38" id="js_keyword" onKeyPress="return search_onkeypress(event)">
      <a class="mod-search__btn-search" href="javascript:search()"></a>
    </div>
    <input type="hidden" name="searchType" id="searchType" value="1"/>
    <input type="hidden" name="searchButton" id="searchButton" value=""/>
    </form>
    <script type="text/javascript">
	$(document).ready(function(){
		var lr_systembtn = $("#lr_systembtn");
		var lr_menu = $("#lr_menu");
		lr_systembtn.mouseenter(function(){
			t_delay= setTimeout(function(){
				lr_menu.fadeIn("slow");
			},200);
		});
		lr_systembtn.mouseleave(function(){
			clearTimeout(t_delay);
			lr_menu.fadeOut("slow");
		});
	
	});
	function changeSearchType(searchtype){
		document.getElementById("searchType").value = searchtype;
		if(searchtype == '1'){
			document.getElementById("curSearchType").innerHTML = "微课";
			document.getElementById("lr_menu").innerHTML = "<dl><dt><a href=\"javascript:changeSearchType('2')\">文档</a></dt><dt><a href=\"javascript:changeSearchType('3')\">教师</a></dt><dt><a href=\"javascript:changeSearchType('4')\">学校</a></dt></dl>";
		}
		if(searchtype == '2'){
			document.getElementById("curSearchType").innerHTML = "文档";
			document.getElementById("lr_menu").innerHTML = "<dl><dt><a href=\"javascript:changeSearchType('1')\">微课</a></dt><dt><a href=\"javascript:changeSearchType('3')\">教师</a></dt><dt><a href=\"javascript:changeSearchType('4')\">学校</a></dt></dl>";
		}
		if(searchtype == '3'){
			document.getElementById("curSearchType").innerHTML = "教师";
			document.getElementById("lr_menu").innerHTML = "<dl><dt><a href=\"javascript:changeSearchType('1')\">微课</a></dt><dt><a href=\"javascript:changeSearchType('2')\">文档</a></dt><dt><a href=\"javascript:changeSearchType('4')\">学校</a></dt></dl>";
		}
		if(searchtype == '4'){
			document.getElementById("curSearchType").innerHTML = "学校";
			document.getElementById("lr_menu").innerHTML = "<dl><dt><a href=\"javascript:changeSearchType('1')\">微课</a></dt><dt><a href=\"javascript:changeSearchType('2')\">文档</a></dt><dt><a href=\"javascript:changeSearchType('3')\">教师</a></dt></dl>";
		}
	};
	function search_onkeypress(evt){
		evt = (evt) ? evt : ((window.event) ? window.event : "")
	  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
	  	if (keyCode == 13) {
	    	keyCode=0;
	    	search();
	  	}
	};
	function search(){
		var keywords = document.getElementById('js_keyword').value;
		if(keywords == ''){
			alert("请输入搜索关键字!");
			return;
		}
		var searchtype = document.getElementById("searchType").value;
		document.getElementById("searchButton").value = "1";
		if(searchtype == "1"){
			document.searchForm.action = '/v-vlist-x0-cx0-s0-g0-1.htm';
		}
		if(searchtype == "2"){
			document.searchForm.action = '/v-dlist-x0-cx0-s0-g0-1.htm';
		}
		if(searchtype == "3"){
			document.searchForm.action = '/v-ulist-1-0.htm';
		}
		if(searchtype == "4"){
			document.searchForm.action = '/v-slist-1-0.htm';
		}
		document.searchForm.submit();
	};
	</script>
    <div class="login1">
      <ul id="login_div">
        <li class="dv mar_9" >
          <a href="javascript:login()" class="login_1" style="color:#000">登录</a>
          <a href="/v.bo?method=register" target="_blank" class="login_2" style="color:#000">注册</a>
        </li>
      </ul>
    </div>
  </div>
</div>
</div>
<script type="text/javascript">
	function login(){
		var topurl = window.top.location;
		window.top.location = '/plogin.do?method=slogin&redirecturl=' + topurl;
	};
	function logout(){
	    var topurl = window.top.location;
	    window.top.location = '/plogin.do?method=userLogout&redirecturl=' + topurl;
	};
	function ajaxLogin(){
		$.ajax({
	        type: "get",
	        url: "/v.bo?method=ajaxLogin1&ram=" + Math.random(),
	        dataType: "text",
	        success: function(data){
	        	if(data != ''){
	        		document.getElementById("login_div").innerHTML = data;
	        		
	        		var lr_systembtn1 = $("#lr_systembtn1");
        			var lr_menu1 = $("#lr_menu1");
       				lr_systembtn1.mouseenter(function(){
       					t_delay= setTimeout(function(){
       						lr_menu1.fadeIn("slow");
       					},200);
       				});
       				lr_systembtn1.mouseleave(function(){
       					clearTimeout(t_delay);
       					lr_menu1.fadeOut("slow");
       				});
	        	}
	         }
	    });
	}
	ajaxLogin();
</script>
<!-- 代码begin -->
<div class="flexslider">
    <div class="nav">
    <ul>
      <li><a href="/v-vlist-x0-cx0-s0-g0-1.htm" target="_blank">微课</a></li>
      <li><a href="/v-dlist-x0-cx0-s0-g0-1.htm" target="_blank">文档</a></li>
      <li><a href="/v-ulist-1-0.htm" target="_blank">教师</a></li>
      <li><a href="/v-slist-1-0.htm" target="_blank">学校</a></li>
      <li><a href="/v-clist-12-0.htm" target="_blank">大赛</a></li>
      <li><a href="/v-c-12-0.htm" target="_blank">资讯</a></li>
      <li><a href="/v-statistics-12-0.htm" target="_blank">统计</a></li>
    </ul>
    <div class="nav_right">
      <a href="/feedback.htm" target="_blank">意见反馈</a>
      <span>|</span>
      <a href="/pcenter.do?method=index&mproduct=vod" target="_blank" class="sc">我要上传</a>
    </div>
  </div>
	<div id="full-screen-slider1">
		<ul id="slides1">
			<#list adlist as model>
			<li style="background:url('/upload_dir/${model.sketch }') no-repeat center top #02a8e4"><#if model.url?exists && model.url != ''><a href="${model.url }" target="_blank" title="${model.title }"></a></#if></li>
			</#list>
		</ul>
	</div>
    <div class="banner1">
      <div class="menu">
      <div class="mod-nav subpage">
        <h2 class="mod-nav__li-first"><a class="mod-nav__course-all" target="_blank" href="/v-vlist-x0-cx0-s0-g0-1.htm">全部课程</a></h2>
        <ul class="mod-nav__list prosul clearfix" id="proinfo">
          <li class="mod-nav__li h_2">
            <div class="mod-nav__wrap-nav-first">
              <a class="mod-nav__link-nav-first" target="_blank" href="/v-vlist1-x1-cx0-s0-g0-v0-c0-1.htm">小学教育</a>
            </div>
            <div class="mod-nav__wrap-nav-hot h_1">
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x1-cx9-s0-g0-v0-c0-1.htm">一年级</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x1-cx10-s0-g0-v0-c0-1.htm">二年级</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x1-cx11-s0-g0-v0-c0-1.htm">三年级</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x1-cx12-s0-g0-v0-c0-c0-1.htm">四年线</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x1-cx13-s0-g0-v0-c0-1.htm">五年级</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x1-cx14-s0-g0-v0-c0-1.htm">六年级</a>
            </div>
            <div class="prosmore hide">
              <#list menulist1 as menu>
              <span><em><a href="/v-vlist1-x1-cx0-s${menu.subjectid }-g0-v0-c0-1.htm" target="_blank">${menu.subjectname }</a></em></span>
			  </#list>
            </div>
          </li>
          <li class="mod-nav__li">
            <div class="mod-nav__wrap-nav-first">
              <a class="mod-nav__link-nav-first" target="_blank" href="/v-vlist1-x2-cx0-s0-g0-v0-c0-1.htm">初中教育</a>
            </div>
            <div class="mod-nav__wrap-nav-hot">
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x2-cx15-s0-g0-v0-c0-1.htm">初一</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x2-cx16-s0-g0-v0-c0-1.htm">初二</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x2-cx17-s0-g0-v0-c0-1.htm">初三</a>
            </div>
            <div class="prosmore hide">
              <#list menulist2 as menu>
              <span><em><a href="/v-vlist1-x2-cx0-s${menu.subjectid }-g0-v0-c0-1.htm" target="_blank">${menu.subjectname }</a></em></span>
			  </#list>
            </div>
          </li>
          <li class="mod-nav__li">
            <div class="mod-nav__wrap-nav-first">
              <a class="mod-nav__link-nav-first" target="_blank" href="/v-vlist1-x3-cx0-s0-g0-v0-c0-1.htm">高中教育</a>
            </div>
            <div class="mod-nav__wrap-nav-hot">
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x3-cx19-s0-g0-v0-c0-1.htm">高一</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x3-cx20-s0-g0-v0-c0-1.htm">高二</a>
              <a class="mod-nav__link-nav-hot" target="_blank" href="/v-vlist1-x3-cx21-s0-g0-v0-c0-1.htm">高三</a>
            </div>
            <div class="prosmore hide">
              <#list menulist3 as menu>
              <span><em><a href="/v-vlist1-x3-cx0-s${menu.subjectid }-g0-v0-c0-1.htm" target="_blank">${menu.subjectname }</a></em></span>
			  </#list>
            </div>
          </li>
          <li class="mod-nav__li h_3">
            <div class="mod-nav__wrap-nav-first">
              <a class="mod-nav__link-nav-first" target="_blank" href="/v-vlist-x4-cx41-s0-g0-1.htm">职业教育</a>
            </div>
          </li>
           <li class="mod-nav__li h_3">
            <div class="mod-nav__wrap-nav-first">
              <a class="mod-nav__link-nav-first" target="_blank" href="/v-vlist-x5-cx0-s0-g0-1.htm">高等教育</a>
            </div>
          </li>
           <li class="mod-nav__li h_3">
            <div class="mod-nav__wrap-nav-first">
              <a class="mod-nav__link-nav-first" target="_blank" href="/v-vlist-x6-cx0-s0-g0-1.htm">继续教育</a>
            </div>
          </li>
        </ul>
      </div>
    </div>
      </div>
<script type="text/javascript">
(function() {
    var $subblock = $(".subpage"),
    $head = $subblock.find('h2'),
    $ul = $("#proinfo"),
    $lis = $ul.find("li"),
    inter = false;
    $head.click(function(e) {
        e.stopPropagation();
        if (!inter) {
            $ul.show();
        } else {
            $ul.show();
        }
        inter = !inter;
    });
    $ul.click(function(event) {
        event.stopPropagation();
    });
    $('body').click(function() {
        $ul.show();
        inter = !inter;
    });
    $lis.hover(function() {
        if (!$(this).hasClass('nochild')) {
            $(this).addClass("prosahover");
            $(this).find(".prosmore").removeClass('hide');
        }
    },
    function() {
        if (!$(this).hasClass('nochild')) {
            if ($(this).hasClass("prosahover")) {
                $(this).removeClass("prosahover");
            }
            $(this).find(".prosmore").addClass('hide');
        }
    });
})();
</script>
   <div class="wrap_pic">
      <a target="_blank" class="activity-card__link" href="/v-vlist-x0-cx0-s0-g0-9.htm"><img class="activity-card__img" alt="" src="/skin/wkmk/images/ad_img1.jpg"></a>
      <a target="_blank" class="activity-card__link" href="/v-dlist-x0-cx0-s0-g0-9.htm"><img class="activity-card__img" alt="" src="/skin/wkmk/images/ad_img2.jpg"></a>
      <a target="_blank" class="activity-card__link" href="/v-slist-1-0.htm"><img class="activity-card__img" alt="" src="/skin/wkmk/images/ad_img3.jpg"></a>
      <a target="_blank" class="activity-card__link" href="http://mllab.bnu.edu.cn"><img class="activity-card__img" alt="" src="/skin/wkmk/images/ad_img4.jpg"></a>   
   </div>
</div>
<!-- 代码end -->


<!-- js调用部分begin -->
<script src="/skin/wkmk/js/jquery.min.js"></script>
<script src="/skin/wkmk/js/jquery.flexslider-min.js"></script>
<script>
$(function(){
	$('.flexslider').flexslider({
		directionNav: true,
		pauseOnAction: false
	});
});
</script>
<!-- js调用部分end -->
<div id="fine">
  <div class="fine_left">
    <h2 class="video"><a href="/v-vlist-x0-cx0-s0-g0-9.htm">更多&gt;&gt;</a>精品微课</h2>
    <div class="fine_left1">
      <div class="fine_left_1">
        <img src="/skin/wkmk/images/pic_2.jpg" />
      </div>
      <div class="fine_left_2">
        <ul class="h_pic">
          <#list recommandVodList as model>
          <li class="l_pic">
            <div class="mod-course-card">
              <a href="/v-play-${model.unitid }-${model.filmid }.htm" target="_blank" class="mod-course-card__link-img">
                <#if model.sketch?index_of("http://") != -1><img src="${model.sketch }" style="width:220px;height:124px;"/><#else><img src="/upload_dir/${model.sketch }" style="width:220px;height:124px;"/></#if>
                <i class="icon-card-play"></i>
              </a>
              <div class="course-title">
                <a title="${model.title }" class="mod-course-card__name" target="_blank" href="/v-play-${model.unitid }-${model.filmid }.htm">${model.title }</a> 
              </div>
              <p class="mod-course-card__line"> 
                <a title="${model.actors }" target="_blank" href="${model.linkurl }" class="mod-course-card__price mod-course-card__price_free"><#if model.actors?length lt 5>${model.actors}<#else>${model.actors[0..3]}</#if></a> 
                <a title="${model.unitname }" class="mod-course-card__agency" target="_blank" href="/html/${model.unitid }.htm">${model.unitname }</a>
             </p>
            </div>
          </li>
          </#list>
        </ul>
      </div>
    </div>
  </div>
  <div class="fine_right_1">
    <h2>
      <span id="tab1"><a class="link" onclick="switchTab('tab1','con1');this.blur();return false;" style="cursor:pointer;">精品文档</a></span>
      <span id="tab2"><a onclick="switchTab('tab2','con2');this.blur();return false;" style="cursor:pointer;">最新文档</a></span>
    </h2>
    <div class="fine_right_bottom" id="con1">
      <#list recommandDocList as model>
      <dl>
        <dd class="ico_${model.fileext}"><a href="/v-doc-${model.unitid}-${model.fileid}.htm" target="_blank" title="${model.title}"><#if model.title?length lt 12>${model.title}<#else>${model.title[0..9]}...</#if></a></dd>
        <dd>
          <span>
            ${model.flago}
          </span>
          <span class="font_1">${model.flags}分</span>
          <span class="mar_1 font_1">${model.pagenum}页</span>
        </dd>
      </dl>
      </#list>
      <div class="more"><a href="/v-dlist-x0-cx0-s0-g0-9.htm" target="_blank">更多&gt;&gt;</a></div>
    </div>
    <div class="fine_right_bottom" id="con2" style="display: none">
      <#list newDocList as model>
      <dl>
        <dd class="ico_${model.fileext}"><a href="/v-doc-${model.unitid}-${model.fileid}.htm" target="_blank" title="${model.title}"><#if model.title?length lt 12>${model.title}<#else>${model.title[0..9]}...</#if></a></dd>
        <dd>
          <span>
            ${model.flago}
          </span>
          <span class="font_1">${model.flags}分</span>
          <span class="mar_1 font_1">${model.pagenum}页</span>
        </dd>
      </dl>
      </#list>
      <div class="more"><a href="/v-dlist-x0-cx0-s0-g0-1.htm" target="_blank">更多&gt;&gt;</a></div>
    </div>
  </div>
  <script type="text/javascript">
        function switchTab(ProTag, ProBox) {
            for (i = 1; i <= 2; i++) {
                if ("tab" + i == ProTag) {
                    document.getElementById(ProTag).getElementsByTagName("a")[0].className = "link";
                } else {
                    document.getElementById("tab" + i).getElementsByTagName("a")[0].className = "";
                }
                if ("con" + i == ProBox) {
                    document.getElementById(ProBox).style.display = "";
                } else {
                    document.getElementById("con" + i).style.display = "none";
                }
            }
        }
    </script>
</div>
<div id="fine">
  <div class="fine_left">
    <h2 class="school"><a href="/v-slist-1-0.htm">更多&gt;&gt;</a>联盟学校</h2>
    <div class="fine_left1">
      <div class="fine_left_1">
        <img src="/skin/wkmk/images/pic_4.jpg" />
      </div>
      <div class="fine_left_2">
        <ul class="h_pic">
          <#list recommandUnitList as model>
          <li class="l_pic">
            <div class="mod-course-card">
              <a href="/html/${model.unitid}.htm" target="_blank" class="mod-course-card__link-img">
                <img src="${model.sketch}" width="220" height="124"/>
              </a>
              <div class="course-title">
                <a title="${model.unitname}" class="mod-course-card__name" target="_blank" href="/html/${model.unitid}.htm">${model.unitname}</a> 
              </div>
              <p class="mod-course-card__line"> 
                <a class="mod-course-card__agency">教师<span class="color_1">${model.flags}</span></a>
                <a class="mod-course-card__agency">微课<span class="color_1">${model.flago}</span></a>
                <a class="mod-course-card__agency">文档<span class="color_1">${model.descript}</span></a>
             </p>
            </div>
          </li>
          </#list>
        </ul>
      </div>
    </div>
  </div>
  <div class="fine_right">
    <h2>教师</h2>
    <div class="fine_bottom">
      <#list recommandUserList as model>
      <dl>
        <dt><a href="/v-user-${model.unitid}-${model.userid}.htm" target="_blank"><img src="${model.photo}" width="50" height="50"/></a></dt>
        <dd><a href="/v-user-${model.unitid}-${model.userid}.htm" target="_blank" class="color_2">${model.username}</a></dd>
        <dd><a class="color_3" title="${model.job}"><#if model.job?length lt 9>${model.job}<#else>${model.job[0..6]}...</#if></a></dd>
      </dl>
      </#list>
      <div class="more"><a href="/v-ulist-1-0.htm">更多&gt;&gt;</a></div>
    </div> 
  </div>
</div>

<div id="footer">
  <div class="footer_1">
    <div class="footer_right">
      <p class="footer_logo">微课慕课网  版权所有@2014-2015</p>
      <p>电话 : 010-83456666&nbsp;&nbsp;010-84929209</p>
      <p>地址 ：北京市西城区新街口外大街8号金丰和商务苑b座210室&nbsp;<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_5941511'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D5941511%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></p>
      <p>技术支持 ：北京师科学苑网络有限公司&nbsp;&nbsp;京ICP备15064195号</p>
      <p class="footer_ff">
        <a href="/v-aboutus-12-0.htm" target="_blank">关于我们</a>
        <span>|</span>
        <a href="/v-contact-12-0.htm" target="_blank">联系我们</a>
        <span>|</span>
        <a href="/v-job-12-0.htm" target="_blank">人才招聘</a>
        <span>|</span>
        <a href="/v-link-12-0.htm" target="_blank">友情链接</a>
        <span>|</span>
        <a href="/v-applylink-12-0.htm" target="_blank">申请链接</a>
      </p>
    </div>
    <div class="footer_left">
      <!-- 
      <dl>
        <dt><img src="/skin/wkmk/images/code_1.jpg" width="100" height="100"/></dt>
        <dd>APP下载</dd>
      </dl>
       -->
      <dl>
        <dt><img src="/skin/wkmk/images/wkmk-weixin.jpg" width="100" height="100"/></dt>
        <dd>微信公众号</dd>
      </dl>
    </div>
  </div>
</div>
<script type="text/javascript" src="/public/retop1/retop.js"></script>
</body>
</html>