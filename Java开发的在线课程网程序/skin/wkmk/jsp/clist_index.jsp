<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="com.bzt.vod.bo.VodContestInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-大赛</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function loading(size){
	for(var i=4; i<=size; i++){
		if(document.getElementById("tr_"+i)){
			document.getElementById("tr_"+i).style.display = "";
		}
	}
	document.getElementById("con_loading").style.display = "none";
}
</script>
</head>

<body style="background:#d5fffd">
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <div class="contest_banner"></div>
  <div class="contest_botton clearfix">
    <div class="con_h1">区域大赛作品展示</div>
    <div class="con_bottom">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="con_tab">
      	  <%
      	  List contestlist = (List)request.getAttribute("contestlist");
      	  VodContestInfo vci = null;
      	  int temp = 0;
      	  for(int i=0, size=contestlist.size(); i<size; i++){
      		  vci = (VodContestInfo)contestlist.get(i);
      	  %>
      	  <%
      	  if(i%2 == 0){
      		  temp ++;
      	  %>
          <tr id="tr_<%=temp %>" <%if(temp>3){ %>style="display:none;"<%} %>>
          <%} %>
            <td align="center"><a href="/v-contest-${f_unitid }-<%=vci.getContestid() %>.htm" target="_blank"><img src="/upload_dir/<%=vci.getBanner() %>" /></a></td>
            <%if(i == size-1 && i%2 == 0){ %>
            <td align="center">&nbsp;</td>
            <%} %>
          <%if((i != 0 && (i+1)%2 == 0) || i == size-1){ %>
          </tr>
          <%} %>
          <%} %>
      </table>
    </div>
    <div class="con_loading" id="con_loading"><a href="javascript:loading(<%=temp %>)"><img src="/skin/wkmk/images/contest_loading.jpg"/></a></div>
  </div>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
