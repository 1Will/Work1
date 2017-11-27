<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.vod.bo.VodFilmDiscuss"%>
<%@page import="java.util.List"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/page.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function toPage(start){
	document.getElementById('startcount').value = start;
	document.pageForm.action = '/v.bo?method=ddiscuss';
	document.pageForm.submit();
}
function GotoPage(pagesize){
	var totalsize = <bean:write name="pagelist" property="totalPages" />;
	if(pagesize > totalsize || pagesize < 1){
	  alert('您输入的页码不正确！');
	}
	if(pagesize <= totalsize && pagesize > 0){
		document.getElementById('startcount').value = (pagesize-1)*5;
		document.pageForm.action = '/v.bo?method=ddiscuss';
		document.pageForm.submit();
	}
}
function checkLogin(){
	<logic:equal value="0" name="r_nmpl">
	<logic:notPresent property="userid" name="s_sysuserinfo">
	$.ajax({
	   url: "/skinBaoxiuIndexAction.bo?method=checkLogin",
	   data: "ram=" + Math.random(),
	   async: false,
	   success: function(msg){
	   	 if('0' == msg){
	   	 	document.getElementById('comments').value = '请先登录，再发表评论!';
			document.getElementById('comments').blur();
			return;
	   	 }else{
	   	 	var value = document.getElementById('comments').value;
	   	 	if(value == '请先登录，再发表评论!'){
	   	 		document.getElementById('comments').value = '';
	   	 	}
	   	 }
	   }
	}); 
	</logic:notPresent>
	</logic:equal>
}
function addSaveDiscuss(){
	var temp = '1';
	<logic:equal value="0" name="r_nmpl">
	<logic:notPresent property="userid" name="s_sysuserinfo">
	$.ajax({
	   url: "/skinBaoxiuIndexAction.bo?method=checkLogin",
	   data: "ram=" + Math.random(),
	   async: false,
	   success: function(msg){
	   	 if('0' == msg){
	   	 	document.getElementById('comments').value = '请先登录，再发表评论!';
			document.getElementById('comments').blur();
			temp = '0';
	   	 }else{
	   	 	var value = document.getElementById('comments').value;
	   	 	if(value == '请先登录，再发表评论!'){
	   	 		document.getElementById('comments').value = '';
	   	 	}
	   	 }
	   }
	}); 
	</logic:notPresent>
	</logic:equal>
	
	if(temp == '1'){
		var obj = document.getElementById('comments').value;
		if(obj == '' || obj.length < 5){
			alert('评论内容至少为5个字符！');
			return;
		}
		<logic:equal value="1" name="r_shpl">
		alert("评论发布成功，请等待管理员审核!");
		</logic:equal>
		document.pageForm.action = '/v.bo?method=addDdiscuss';
		document.pageForm.submit();
	}
}
function updateTipOrTread(objid, id, flag){
	$.ajax({
	   url: "/v.bo?method=updateTipOrTread",
	   data: "discussid="+objid+"&flag="+flag+"&ram=" + Math.random(),
	   success: function(msg){
	   	 if('-1' == msg){
	   	 	alert("操作太频繁，不能重复评价！");
	   	 }else{
	   	    $("#"+id).text(msg);
	   	 }
	   }
	}); 
	return;
}
</script>
</head>

<body>
<form name="pageForm" method="post">
<div class="detailed clearfix back_1">
  <ul class="ul_1">
    <li id="tab1"><a class="lik_1">用户评论</a></li>
  </ul>
  <div class="detailed_bottom">
    <div class="form_2 clearfix">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="9%" align="left" style="font-size:32px;color:#333"><bean:write name="docFileInfo" property="score"/></td>
            <td width="12%" rowspan="2" style="border-left:1px solid #ccc;padding-left:20px;">课程内容：</td>
            <td width="81%" rowspan="2"><div id="discussdemo" style="float:left;"></div> <%if("1".equals(request.getAttribute("addscore"))){ %>&nbsp;<font style="font-size:11px;color:red;">(已打分)</font><%}else{ %>&nbsp;<font style="font-size:11px;">(请打分)</font><%} %></td>
          </tr>
          <tr align="left">
            <td>综合评分</td>
          </tr>
          <tr>
            <td colspan="3">
              <textarea name="comments" id="comments" onfocus="checkLogin()" class="textrarea_1"></textarea>
            </td>
          </tr>
          <tr>
            <td colspan="3" align="right"><a href="javascript:addSaveDiscuss()" class="sub_btn">我要评论</a></td>
          </tr>
      </table>
    </div>
    <%
    PageList pagelist = (PageList)request.getAttribute("pagelist");
    List list = pagelist.getDatalist();
    VodFilmDiscuss discuss = null;
    for(int i=0, size=list.size(); i<size; i++){
    	discuss = (VodFilmDiscuss)list.get(i);
    %>
    <div class="form_list clearfix">
      <dl>
        <%if("0".equals(discuss.getUserid())){ %>
        <dt><img src="/upload_dir/user.gif" width="50" height="50"/></dt>
        <dd>
          <span class="color_6"><%=discuss.getUsername() %></span>
          <span><%=discuss.getCreatedate() %></span>
        </dd>
        <%}else{ %>
        <dt><a href="/v-user-<%=discuss.getUnitid() %>-<%=discuss.getUserid() %>.htm" target="_blank"><img src="<%=discuss.getFlago() %>" width="50" height="50"/></a></dt>
        <dd>
          <span class="color_6"><a href="/v-user-<%=discuss.getUnitid() %>-<%=discuss.getUserid() %>.htm" target="_blank"><%=discuss.getUsername() %></a></span>
          <span><%=discuss.getCreatedate() %></span>
        </dd>
        <%} %>
        <dd><%=discuss.getHtmlcontent() %> </dd>
      </dl>
      <div class="w140">
        <a href="javascript:updateTipOrTread('<%=discuss.getDiscussid() %>','tip<%=i %>','tip')" class="zan">赞(<color id="tip<%=i %>"><%=discuss.getTip() %></color>)</a>
        <a href="javascript:updateTipOrTread('<%=discuss.getDiscussid() %>','tread<%=i %>','tread')" class="cai">踩(<color id="tread<%=i %>"><%=discuss.getTread() %></color>)</a>
      </div>
    </div>
    <%} %>
    <logic:greaterThan value="1" name="pagelist" property="totalPages">
    <div class="page clearfix float1" style="width:810px">
    <%@ include file="page.jsp"%>
    </div>
    </logic:greaterThan>
  </div>
</div>
<input type="hidden" name="startcount" id="startcount" value=''>
<input type="hidden" name="unitid" value="<bean:write name="f_unitid"/>"/>
<input type="hidden" name="objid" value='<bean:write name="fileid"/>'>
</form>
<script type="text/javascript" src="/skin/wkmk/js/rater-star.js"></script>
<link href="/skin/wkmk/css/rater-star.css" rel="stylesheet"/>
<script type="text/javascript">
var options	= { 
		value : <bean:write name="docFileInfo" property="score"/>,
		image : '/skin/wkmk/images/star.gif',
		width : 25,
		height : 25,
		<%
		if("1".equals(request.getAttribute("addscore"))){
		%>
		enabled:false,
		<%}%>
		after_ajax : function(ret){
			ret.ajax;
		},
		after_click : function(ret) {
		    this.url = '/v.bo?method=addScoreToDoc&fileid=<bean:write name="docFileInfo" property="fileid"/>&score='+ret.value;
			this.value	= ret.value;
			this.enabled= false;
			$('#discussdemo').rater(this);
			var title = '';
			switch (ret.value) {
				case 1 : 
					title	= '较差';
					break;
				case 2 : 
					title	= '一般';
					break;
				case 3 : 
					title	= '还行';
					break;
				case 4 : 
					title	= '推荐';
					break;
				case 5 : 
					title	= '力荐';
					break;
				default :
					title = ret.value;
					break;
			}
		},
		title_format : function(value) {
			var title = '';
			switch (value) {
				case 1 : 
					title	= '较差';
					break;
				case 2 : 
					title	= '一般';
					break;
				case 3 : 
					title	= '还行';
					break;
				case 4 : 
					title	= '推荐';
					break;
				case 5 : 
					title	= '力荐';
					break;
				default :
					title = value;
					break;
			}
			return title;
		}
		}
 $('#discussdemo').rater(options);
</script>
</body>
</html>