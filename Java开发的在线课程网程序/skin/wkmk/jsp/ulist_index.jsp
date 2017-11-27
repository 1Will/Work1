<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.bo.SysUserInfo"%>
<%@page import="com.util.service.action.CacheUtil"%>
<%@page import="com.util.string.encode.Encode"%>
<%@page import="com.bzt.sys.util.SubStringDirectiveModel"%>
<%@page import="com.util.search.PageList"%>
<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-教师</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function gotoUrl(type){
	document.pageForm.action = '/v-ulist-' + type + '-0.htm';
	document.pageForm.submit();
}
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v-ulist-${type}-0.htm';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*8;
		document.pageForm.action = '/v-ulist-${type}-0.htm';
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
	document.getElementById("pageFormSearchButton").value = "1";
	document.pageForm.action = '/v-ulist-${type}-0.htm';
	document.pageForm.submit();
}
function changeArea(type, areano){
	if(type == '1'){
		if(areano != ''){
			$.ajax({
		        type: "get",
		        async: false,
		        url: "/v.bo?method=getArea&areano=" + areano + "&ram=" + Math.random(),
		        dataType: "text",
		        success: function(data){
		        	if(data != ''){
		        		$("#areano2").html(data);
					}else{
						$("#areano2").html("<option value=''>请选择</option>");
					}
		        }
			});
		}else{
			$("#areano2").html("<option value=''>请选择</option>");
		}
		$("#areano3").html("<option value=''>请选择</option>");
	}
	if(type == '2'){
		if(areano != ''){
			$.ajax({
		        type: "get",
		        async: false,
		        url: "/v.bo?method=getArea&areano=" + areano + "&ram=" + Math.random(),
		        dataType: "text",
		        success: function(data){
		        	if(data != ''){
		        		$("#areano3").html(data);
					}else{
						$("#areano3").html("<option value=''>请选择</option>");
					}
		        }
			});
		}else{
			$("#areano3").html("<option value=''>请选择</option>");
		}
	}
	searchUser();
}
</script>
</head>

<body>
<%@ include file="top.jsp"%>
<form name="pageForm" method="post">
<div id="tab_nav">
<div class="clearfix">
  <div class="form_1 clearfix">
    <select name="areano1" onchange="changeArea('1', this.value)">
      <option value="">请选择</option>
      <%
      String areano1 = (String)request.getAttribute("areano1");
      List arealist1 = (List)request.getAttribute("arealist1");
      GpwAreaInfo gai = null;
      for(int i=0, size=arealist1.size(); i<size; i++){
    	  gai = (GpwAreaInfo)arealist1.get(i);
      %>
      <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano1)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
      <%} %>
    </select>
    <select name="areano2" id="areano2" onchange="changeArea('2', this.value)">
      <option value="">请选择</option>
      <%
      List arealist2 = (List)request.getAttribute("arealist2");
      String areano2 = (String)request.getAttribute("areano2");
      if(arealist2 != null && arealist2.size() > 0){
      for(int i=0, size=arealist2.size(); i<size; i++){
    	  gai = (GpwAreaInfo)arealist2.get(i);
      %>
      <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano2)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
      <%}} %>
    </select>
    <select name="areano3" id="areano3" onchange="changeArea('3', this.value)">
      <option value="">请选择</option>
      <%
      List arealist3 = (List)request.getAttribute("arealist3");
      String areano3 = (String)request.getAttribute("areano3");
      if(arealist3 != null && arealist3.size() > 0){
      for(int i=0, size=arealist3.size(); i<size; i++){
    	  gai = (GpwAreaInfo)arealist3.get(i);
      %>
      <option value="<%=gai.getAreano() %>" <%if(gai.getAreano().equals(areano3)){ %>selected="selected"<%} %>><%=gai.getAreaname() %></option>
      <%}} %>
    </select>
    <div class="seach2" style="padding-top:0;">
      <input type="text" placeholder="搜索用户" name="keywords" id="keywords" value="${keywords }" maxlength="38" onKeyPress="return search_onkeypress1(event)">
      <a href="javascript:searchUser()"></a>
    </div>
  </div>
  <div class="list clearfix">
    <div class="list_left">
      <table width="100%" border="0" cellspacing="0" class="tabel_1">
		  <tr>
		    <%String type = (String)request.getAttribute("type"); %>
		    <th colspan="2" align="center">综合</th>
		    <th width="30%" align="left">学校</th>
		    <th width="12%" align="center"><a href="javascript:gotoUrl('1')" <%if("1".equals(type)){ %>class="link1"<%}else{ %>class="link2" style="color:#666"<%} %>>微课数量</a></th>
		    <th width="12%" align="center"><a href="javascript:gotoUrl('2')" <%if("2".equals(type)){ %>class="link1"<%}else{ %>class="link2" style="color:#666"<%} %>>文档数量</a></th>
		    <th width="16%" align="left">
		      <div class="page2">
		          <logic:equal name="pagelist" property="hasPreviousPage" value="true">
				  <a href="javascript:toPage('<bean:write name="pagelist" property="startOfPreviousPage" />')" class="left_ico"></a>
				  </logic:equal>
				  <a class="curss"><bean:write name="pagelist" property="curPage" /></a>
				  <span class="float1" style="line-height:20px;">/</span>
				  <a><bean:write name="pagelist" property="totalPages" /></a>
				  <logic:equal name="pagelist" property="hasNextPage" value="true">
				  <a href="javascript:toPage('<bean:write name="pagelist" property="startOfNextPage" />')" class="right_ico"></a>
				  </logic:equal>
		      </div>
		    </th>
		  </tr>
		  <%
		  PageList pagelist = (PageList)request.getAttribute("pagelist");
		  List list = pagelist.getDatalist();
		  if(list != null && list.size() > 0){
		  Object[] object = null;
		  for(int i=0, size=list.size(); i<size; i++){
		  	  object = (Object[])list.get(i);
		  %>
		  <%if("1".equals(type)){ %>
		  <tr>
		    <td width="13%" align="left"><a href="/v-user-<%=object[5] %>-<%=object[0] %>.htm" target="_blank"><img src="<%=object[2] %>" /></a></td>
		    <td width="17%" align="left"><p class="color_2"><a href="/v-user-<%=object[5] %>-<%=object[0] %>.htm" target="_blank" class="color_2"><%=SubStringDirectiveModel.subString(Encode.nullToBlank(object[1]), 4, "...") %></a></p>
		      <p class="color_3" title="<%=object[3] %>"><%=SubStringDirectiveModel.subString(Encode.nullToBlank(object[3]), 7, "...") %></p></td>
		    <td align="left"><a href="/html/<%=object[5] %>.htm" target="_blank" class="color_3"><%=CacheUtil.get("unitname_" + object[5]) %></a></td>
		    <td align="center"><%=Encode.nullToDefault(object[6], "0") %></td>
		    <td align="center"><%=CacheUtil.get("userDocs_" + object[0]) %></td>
		    <td align="center"><a href="/v-user-<%=object[5] %>-<%=object[0] %>.htm" class="btn1" target="_blank">进入主页</a></td>
		  </tr>
		  <%}else{ %>
		  <tr>
		    <td width="13%" align="left"><a href="/v-user-<%=object[5] %>-<%=object[0] %>.htm" target="_blank"><img src="<%=object[2] %>" /></a></td>
		    <td width="17%" align="left"><p class="color_2"><a href="/v-user-<%=object[5] %>-<%=object[0] %>.htm" target="_blank" class="color_2"><%=SubStringDirectiveModel.subString(Encode.nullToBlank(object[1]), 4, "...") %></a></p>
		      <p class="color_3" title="<%=object[3] %>"><%=SubStringDirectiveModel.subString(Encode.nullToBlank(object[3]), 7, "...") %></p></td>
		    <td align="left"><a href="/html/<%=object[5] %>.htm" target="_blank" class="color_3"><%=CacheUtil.get("unitname_" + object[5]) %></a></td>
		    <td align="center"><%=CacheUtil.get("userVods_" + object[0]) %></td>
		    <td align="center"><%=Encode.nullToDefault(object[6], "0") %></td>
		    <td align="center"><a href="/v-user-<%=object[5] %>-<%=object[0] %>.htm" class="btn1" target="_blank">进入主页</a></td>
		  </tr>
		  <%} %>
		  <%}}else{ %>
		  <tr>
		    <td colspan="6">抱歉，没有找到您需要的数据！</td>
		  </tr>
		  <%} %>
  		</table>
	  <logic:greaterThan value="1" name="pagelist" property="totalPages">
      <div class="page">
        <%@ include file="page.jsp"%>
      </div>
      </logic:greaterThan>
    </div>
    <div class="list_right mar_2">
      <div class="like clearfix" style="margin-top:0;">
        <h3>教师推荐</h3>
        <%
        List recommandList = (List)request.getAttribute("recommandList");
        SysUserInfo sui = null;
        for(int i=0, size=recommandList.size(); i<size; i++){
      	  sui = (SysUserInfo)recommandList.get(i);
        %>
        <div class="text_bottom1">
          <dl class="dl_3">
            <dt><img src="<%=sui.getPhoto() %>" /></dt>
            <dd><a href="/v-user-<%=sui.getUnitid() %>-<%=sui.getUserid() %>.htm" target="_blank" class="color_2"><%=SubStringDirectiveModel.subString(sui.getUsername(), 4, "...") %></a></dd>
            <dd title="<%=sui.getJob() %>"><%=SubStringDirectiveModel.subString(Encode.nullToBlank(sui.getJob()), 7, "...") %></dd>
            <dd><a href="/html/<%=sui.getUnitid() %>.htm" target="_blank" class="color_3" title="<%=sui.getFlags() %>"><%=SubStringDirectiveModel.subString(sui.getFlags(), 9, "...") %></a></dd>
            <dd>
              <span>微课<strong class="color_5" style="font-weight:normal;"><%=sui.getFlago() %></strong></span>
              <span>文档<strong class="color_5" style="font-weight:normal;"><%=sui.getDescript() %></strong></span>
            </dd>
          </dl>
        </div>
        <%} %>
    </div>
    </div>
</div>
</div>
</div>
<input type="hidden" name="searchButton" id="pageFormSearchButton" value=""/>
<input type="hidden" name="startcount" id="startcount" value=''>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>