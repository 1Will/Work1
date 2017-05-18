<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="souvc.pojo.WeixinUserInfo"%>
<%@page import="souvc.pojo.UserInfo"%>
<%@page import="souvc.pojo.Code"%>
<%@page import="java.util.List"%>
<%
	String userid = (String)request.getAttribute("userid");
	String openid = (String)request.getAttribute("openid");
	UserInfo userInfo = (UserInfo)request.getAttribute("userInfo");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>请假信息填写</title>
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>
<script src="<%=basePath%>/js/zepto.min.js"></script>
<script src="<%=basePath%>/js/router.min.js"></script>
<script src="<%=basePath%>/js/example.js"></script>
<script type="text/javascript">
    function submitInfo(){
    	document.getElementById("myForm").submit();
    }
</script>
</head>
<body>
<div class="container" id="container"></div>
	  <form id="myForm" method="post" action="/EventNoti/qingjiaSubmit">
	  <input type="hidden" name="userid" id="userid" value="<%=userInfo.getId() %>">
	  <input type="hidden" name="openid" id="openid" value="<%=openid %>">
	  <input type="hidden" name="deptId" id="deptId" value="<%=userInfo.getDeptid() %>">
	  <div class="bd">
	  <div class="weui_cells_title">请假类型</div>
    	<div class="weui_cells">
	        <div class="weui_cell weui_cell_select">
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" name="type" id="type">
	                    <%
							List<Code> codeList = (List<Code>)request.getAttribute("list");
							for(Code code : codeList){
						%>
						<option value="<%=code.getId() %>"><%=code.getValue() %></option>
						<% 
							}
						%>
	                </select>
	            </div>
	        </div>
        </div>
	 <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">开始日期</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="date" value="" id="startDate" name="startDate"/>
            </div>
        </div>
    </div>
	<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">结束日期</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="date" value="" id="endDate" name="endDate"/>
            </div>
        </div>
    </div> 
	 <div class="weui_cells weui_cells_form">
		<div class="weui_cell weui_cell_warn">
				<div class="weui_cell_hd"><label for="" class="weui_label">天数</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="days" id="days"  class="weui_input" type="number" pattern="[0-9]*" value="weui input error" placeholder="请输入天数"/>
				</div>
		 </div>
     </div>
     <div class="weui_cells_title">事由</div>
    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <textarea id="yuanyin" name="yuanyin" class="weui_textarea" placeholder="请输入请假事由" rows="3"></textarea>
            </div>
        </div>
    </div>
	  
	<div class="weui_cells_title">审核人</div>
    	<div class="weui_cells">
	        <div class="weui_cell weui_cell_select">
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" name="shenhe" id="shenhe">
	                    <%
							List<WeixinUserInfo> list = (List<WeixinUserInfo>)request.getAttribute("userList");
							 for (int i = 0; i < list.size(); i++) {    
                                 WeixinUserInfo user=list.get(i);
                            if(user.getNickname()!=null)
                           {	
						%>
						<option value="<%=user.getOpenid() %>"><%=user.getNickname() %></option>
						<% 
							}}
						%>
	                </select>
	            </div>
	        </div>
       </div>
       <div class="weui_btn_area">
        	<a class="weui_btn weui_btn_primary" href="javascript:" id="showTooltips" onclick="submitInfo();">提交</a>
    	</div>
  </div>
</form>
</body>
</html>