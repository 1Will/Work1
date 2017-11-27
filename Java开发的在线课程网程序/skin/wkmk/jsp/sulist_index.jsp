<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.util.string.encode.Encode"%>
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.util.search.PageList"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-${f_unitinfo.unitname }-学校教师</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/school.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %> ${f_unitinfo.keywords }">
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-sulist-${f_unitid}-1.htm#position';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*20;
		document.pageForm.action = '/v-sulist-${f_unitid}-1.htm#position';
		document.pageForm.submit();
	}
}
function search_onkeypress1(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "")
  	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
  	if (keyCode == 13) {
    	keyCode=0;
    	searchUser();
  	}
}
function searchUser(){
	document.pageForm.action = '/v-sulist-${f_unitid}-1.htm#position';
	document.pageForm.submit();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<div id="tab_nav">
<div class="clearfix">
  <%@ include file="school_banner.jsp"%>
  <a name="position"></a>
  <form name="pageForm" method="post">
  <h2></a>教师</h2>
  <div class="list clearfix">
    <div class="seach_tab_1">
        <div class="seach2">
          <input type="text" placeholder="搜索用户" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
          <a href="javascript:searchUser()"></a>
        </div>
        <%@ include file="page1.jsp"%>
      </div>
    <div class="list_bottom1 clearfix">
        <div class="de_left">
      <table width="100%" border="0" cellspacing="0" class="tabel_1">
          <%
          PageList pagelist = (PageList)request.getAttribute("pagelist");
      	  List list = pagelist.getDatalist();
      	  if(list != null && list.size() > 0){
      		  SysUserInfo sui = null;
              for(int i=0, size=list.size(); i<size; i++){
            	  sui = (SysUserInfo)list.get(i);
          %>
          <%if(i%4 == 0){ %>
		  <tr>
		  <%} %>
		    <td width="10%" align="left"><a href="/v-user-<%=sui.getUnitid() %>-<%=sui.getUserid() %>.htm"><img src="<%=sui.getPhoto() %>" /></a></td>
		    <%if(i==0 || i==4 || i==8 || i==12 || i==16){ %>
		    <td width="19%" align="left">
		    <%} %>
		    <%if(i==1 || i==5 || i==9 || i==13 || i==17){ %>
		    <td width="17%" align="left">
		    <%} %>
		    <%if(i==2 || i==6 || i==10 || i==14 || i==18){ %>
		    <td width="14%" align="left">
		    <%} %>
		    <%if(i==3 || i==7 || i==11 || i==15 || i==19){ %>
		    <td width="10%" align="left">
		    <%} %>
		      <p><a href="/v-user-<%=sui.getUnitid() %>-<%=sui.getUserid() %>.htm" class="color_2"><%=sui.getUsername() %></a>&nbsp;&nbsp;<span class="color_3"><%=Encode.nullToBlank(sui.getJob()) %></span></p>
		      <p class="color_3" style="font-size:10px;">微课数量：<span class="color_5"><%=sui.getFlago() %></span></p>
		      <p class="color_3" style="font-size:10px;">文档数量：<span class="color_5"><%=sui.getFlags() %></span></p>
		    </td>
		    <%if((i != 0 && (i+1)%4 == 0) || i == size-1){ %>
		    </tr>
		    <%} %>
		    <%}}else{ %>
		    <tr>
		    	<td colspan="2">抱歉，没有找到您需要的数据！</td>
		  	</tr>
		    <%} %>
   		</table>
    </div>
    </div>
    <logic:greaterThan value="1" name="pagelist" property="totalPages">
    <div class="page" style="margin-left:100px;">
    	<%@ include file="page.jsp"%>
    </div>
    </logic:greaterThan>
  </div>
  <input type="hidden" name="startcount" id="startcount" value=''>
  </form>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>