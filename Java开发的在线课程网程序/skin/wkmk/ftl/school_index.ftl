<#setting number_format="#">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微课慕课网-${f_unitinfo.unitname}</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="微课慕课网 微课 慕课 mooc 微课大赛 慕课大赛 翻转课堂 课程学习 在线学习 文库 文档 教案 课件 习题 试卷 中国微课 中国慕课 微课在线 慕课在线 微课学习 慕课学习 基础教育微课大赛 职业教育微课大赛 高等教育微课大赛 继续教育微课大赛 慕课网 中小学微课大赛 k12教育微课 www.wkmk.com www.wkmk.cn www.wkmk.org www.wkmk.net dasai.wkmk.com www.iweike.org.cn ${productName} ${base_unitinfo.unitname} ${base_unitinfo.descript}">
<meta name="description" itemprop="description" content="微课慕课网 微课 慕课 mooc 微课大赛 慕课大赛 翻转课堂 课程学习 在线学习 文库 文档 教案 课件 习题 试卷 中国微课 中国慕课 微课在线 慕课在线 微课学习 慕课学习 基础教育微课大赛 职业教育微课大赛 高等教育微课大赛 继续教育微课大赛 慕课网 中小学微课大赛 k12教育微课 www.wkmk.com www.wkmk.cn www.wkmk.org www.wkmk.net dasai.wkmk.com www.iweike.org.cn ${productName} ${base_unitinfo.unitname} ${base_unitinfo.descript}">
</head>

<body>
<!--#include virtual="/skin/wkmk/comm/top.html"-->
<div id="tab_nav">
<div class="clearfix">
  <div class="school_home">
    <div class="school_banner">
      <img src="${f_unitinfo.banner}" />
    </div>
    <div class="school_right">
      <dl>
        <dt><#if f_unitinfo.homepage?exists><a href="${f_unitinfo.homepage}" target="_blank"></#if><img src="${f_unitinfo.logo}"/><#if f_unitinfo.homepage?exists></a></#if></dt>
        <dd>教师数量：<font id="s_js">${usercount}</font></dd>
        <dd>微课数量：<font id="s_wk">${filmcount}</font></dd>
        <dd>文档数量：<font id="s_doc">${doccount}</font></dd>
        <a href="javascript:praise()" class="zan_btn" id="s_praise">${f_unitinfo.praise}</a>
      </dl>
      <p class="p_dl"><#if f_unitinfo.keywords?length lt 150>${f_unitinfo.keywords}<#else>${f_unitinfo.keywords[0..150]}</#if><a href="/v-sinfo-${f_unitid}-0.htm" target="_blank">［查看更多］</a></p>
    </div>
  </div>
  <div class="list clearfix">
    <div class="list_bottom1 clearfix">
      <h2><a href="/v-svlist-${f_unitid}-1.htm" class="date">更多&gt;&gt;</a>微课</h2>
        <#list vodlist as model>
        <#if model_index%4 == 0>
        <ul class="h_pic3">
        </#if>
          <li class="l_pic1" <#if model_index == 3>style="margin-right:0"</#if><#if model_index == 7>style="margin-right:0"</#if>>
            <div class="mod-course-card h180">
              <a href="/v-play-${model.unitid }-${model.filmid }.htm" target="_blank" class="mod-course-card__link-img">
                <#if model.sketch?index_of("http://") != -1><img src="${model.sketch }" width="220" height="124"/><#else><img src="/upload_dir/${model.sketch }" width="220" height="124"/></#if>
                <i class="icon-card-play"></i>
              </a>
              <div class="course-title">
                <a title="${model.title }" class="mod-course-card__name" target="_blank" href="/v-play-${model.unitid }-${model.filmid }.htm">${model.title }</a> 
              </div>
             <p class="mod-course-card__line"> 
                <a title="${model.actors }" target="_blank" href="${model.flags }" class="mod-course-card__price mod-course-card__price_free"><#if model.actors?length lt 5>${model.actors}<#else>${model.actors[0..3]}</#if></a> 
                <a class="mod-course-card__agency font_1 mar_3">评分<span class="color_5">${model.score}</span></a>
                <a class="mod-course-card__agency font_1 mar_3">浏览<span class="color_5">${model.counts}</span></a>
                <a class="mod-course-card__agency font_1 mar_3">收藏<span class="color_5">${model.collects}</span></a>
             </p>
            </div>
          </li>
        <#if (model_index != 0 && (model_index+1)%4 == 0) || model_index == vodlist?size-1>
        </ul>
        </#if>
        </#list>
    </div>
    <div class="list_bottom1 clearfix">
      <h2><a href="/v-sdlist-${f_unitid}-1.htm" class="date">更多&gt;&gt;</a>文档</h2>
      <#list doclist as model>
      <#if model_index%3 == 0>
      <div class="ed_bottom1">
      </#if>
        <dl class="ed_1" <#if model_index == 2>style="margin-right:0"</#if><#if model_index == 5>style="margin-right:0"</#if>>
            <dt><a href="/v-doc-${model.unitid }-${model.fileid }.htm" target="_blank"><img src="${model.sketch }" /></a></dt>
            <dd class="ico_${model.fileext }"><a href="/v003-doc-${model.unitid }-${model.fileid }.htm" target="_blank" style="font-size:14px;"><#if model.title?length lt 19>${model.title}<#else>${model.title[0..16]}..</#if></a></dd>
            <dd style="padding-left:0px;"><a href="/v-user-${model.unitid }-${model.userid }.htm" target="_blank" class="color_2">${model.flags }</a> | <span>评分<span class="color_5">${model.score }</span></span><span>共<font class="color_5">${model.pagenum }</font>页</span></dd>
            <dd style="padding-left:0px;">
              <span>收藏<span class="color_5">${model.collects }</span></span>
              <span>浏览<span class="color_5">${model.hits }</span></span>
              <span>下载量<span class="color_5">${model.downloads }</span></span>
            </dd>
          </dl>
      <#if (model_index != 0 && (model_index+1)%3 == 0) || model_index == doclist?size-1>
      </div>
      </#if>
      </#list>
    </div>
    <div class="list_bottom1 clearfix">
      <h2><a href="/v-sulist-${f_unitid }-1.htm" class="date">更多&gt;&gt;</a>教师</h2>
      <div class="de_left">
      <table width="100%" border="0" cellspacing="0" class="tabel_1">
		  <#list userlist as model>
		  <#if model_index%4 == 0>
		  <tr>
		  </#if>
		    <td width="10%" align="left"><#if model?exists><a href="/v-user-${f_unitid }-${model.userid }.htm"><img src="${model.photo }" /></a><#else>&nbsp;</#if></td>
		    <#if model_index == 0 || model_index == 4>
		    <td width="19%" align="left">
		    </#if>
		    <#if model_index == 1 || model_index == 5>
		    <td width="17%" align="left">
		    </#if>
		    <#if model_index == 2 || model_index == 6>
		    <td width="14%" align="left">
		    </#if>
		    <#if model_index == 3 || model_index == 7>
		    <td width="10%" align="left">
		    </#if>
		    <#if model?exists>
		      <p><a href="/v-user-${f_unitid }-${model.userid }.htm" class="color_2">${model.username }</a>&nbsp;&nbsp;<span class="color_3"><#if model.job?exists && model.job != ''>${model.job }</#if></span></p>
		      <p class="color_3" style="font-size:10px;">微课数量：<span class="color_5">${model.flago }</span></p>
		      <p class="color_3" style="font-size:10px;">文档数量：<span class="color_5">${model.flags }</span></p>
		    <#else><p>&nbsp;</p></#if>
		    </td>
		    <#if (model_index != 0 && (model_index+1)%4 == 0) || model_index == userlist?size-1>
		    </tr>
		    </#if>
		    </#list>
		</table>
    </div>
    </div>
  </div>
</div>
</div>
<script type="text/javascript">
function praise(){
	$.ajax({
	   url: "/v.bo?method=praise",
	   data: "unitid=${f_unitid}&flag=1",
	   success: function(msg){
	   	 if('-1' == msg){
	   	 	alert("您已点过赞！");
	   	 }else{
	   	    $("#s_praise").text(msg);
	   	 }
	   }
	}); 
	return;
}
function getSchoolStatistics(){
	$.ajax({
        type: "get",
        url: "/v.bo?method=getSchoolStatistics",
        data: "unitid=${f_unitid}&ram=" + Math.random(),
        dataType: "text",
        success: function(data){
        	var statistics = data.split(";");
        	$("#s_js").text(statistics[0]);
        	$("#s_wk").text(statistics[1]);
        	$("#s_doc").text(statistics[2]);
        	$("#s_praise").text(statistics[3]);
         }
    });
}
getSchoolStatistics();
</script>
<!--#include virtual="/skin/wkmk/comm/footer.html"-->
</body>
</html>