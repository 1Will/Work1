<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${model.username }的个人主页</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${mode.username }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords } ${mode.username }">
<script type="text/javascript">
	function SetCwinHeight(){
		var iframeid=document.getElementById("uframe");
		if (document.getElementById){
		   if (iframeid && !window.opera){
			   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
			     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
			   	 iframeid.height = height1>380?height1:380;
			   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
			     var height2 = iframeid.Document.body.scrollHeight;
			  	 iframeid.height = height2>380?height2:380;
			   }
		   }
		}
	}
	
	function switchTab(ProTag) {
        for (i = 1; i < 4; i++) {
            if ("tab" + i == ProTag) {
                document.getElementById(ProTag).getElementsByTagName("a")[0].className = "vvv";
            } else {
                document.getElementById("tab" + i).getElementsByTagName("a")[0].className = "";
            }
        }
        if(ProTag == 'tab1'){
        	document.getElementById("uframe").src = "/v-userf-1-${model.userid }.htm";
        }else if(ProTag=='tab2'){
        	document.getElementById("uframe").src = "/v-userf-2-${model.userid }.htm";
        }else{
            document.getElementById("uframe").src = "/v-userf-3-${model.userid }.htm";
        }
    }
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <div class="Ind_left">
    <dl class="Ind_dl">
      <dt><a href="/v-uinfo-${model.unitid }-${model.userid }.htm" target="_blak"><img src="${model.photo }"></a></dt>
      <dd class="font_2 color_7"><a href="/v-uinfo-${model.unitid }-${model.userid }.htm" target="_blak">${model.username }</a></dd>
      <dd>${model.job }
      <dd><a href="/html/${f_unitinfo.unitid }.htm" target="_blank">${f_unitinfo.unitname }</a></dd>
    </dl>
    <div class="Ind_li">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center"><p class="font_2">${vods } </p>
            <p>微课数量</p></td>
            <td align="center"><p class="font_2">${docs } </p>
            <p>文档数量</p></td>
          </tr>
        </table>
    </div>
    <ul class="Ind_ul">
      <li id="tab1"><a onclick="switchTab('tab1');this.blur();return false;" class="vvv" style="cursor:pointer;"><logic:equal value="0" name="model" property="sex">我</logic:equal><logic:equal value="1" name="model" property="sex">我</logic:equal>的微课</a></li>
      <li id="tab2"><a onclick="switchTab('tab2');this.blur();return false;" style="cursor:pointer;"><logic:equal value="0" name="model" property="sex">我</logic:equal><logic:equal value="1" name="model" property="sex">我</logic:equal>的文档</a></li>
      <li id="tab3"><a onclick="switchTab('tab3');this.blur();return false;" style="cursor: pointer;"><logic:equal value="0" name="model" property="sex">我</logic:equal><logic:equal value="1" name="model" property="sex">我</logic:equal>的收藏 </a> </li>
    </ul>
  </div>
  <iframe name="uframe" id="uframe" onload="SetCwinHeight()" src="/v-userf-1-${model.userid }.htm" width="900" height="380" scrolling="no" frameborder="0"></iframe>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
