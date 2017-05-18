<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String openid = (String)request.getParameter("openid");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>


<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function binding(aa){
var openid = $("#openid").val();
var url = "/EventNoti/bindingServlet";
var form = $("<form id='myForm' method='post' action='"+url+"'>"
			+"<input type='hidden' id='openid' name='openid' value='"+openid+"'/>"
			+"<input type='hidden' id='userid' name='userid' value='"+aa+"'/>"
			+"</form>");
form.appendTo('body').submit().remove();
}
function searchData(){
	
	$.ajax({
        url: "/EventNoti/searchUser",
        type: "POST",
        dataType: "json",
        data: {name:$("#searchName").val()},
        success: function(result){
            $("#searchResult").empty();
            if(result != null && result != ""){
            var html = "<div class='weui_cells_title'>搜索结果</div>";
            $.each(result, function(commentIndex, comment){
                html += "<div class='weui_cells'><div class='weui_cell'><div class='weui_cell_bd weui_cell_primary'>"
                +"<p><span>"+comment.name+"</span><span style='margin-left:70px;'>"+comment.loginName+"</span></p>"
                +"</div><div class='weui_cell_ft'><a onclick='binding("+comment.id+");' "
                +"href='javascript:' class='weui_btn weui_btn_mini weui_btn_primary'>绑定</a></div></div></div>";

          });
            $("#searchResult").html(html);
            }else{
            	var html = "<div class='weui_cells_title' style='margin-top:30%;margin-left:35%'>未搜索到用户</div>";
            	 $("#searchResult").html(html);
            }
		  
        }
    });
}

</script>
</head>
<div class="container" id="container"></div>
<div class="hd">
    <h1 class="page_title">用户搜索</h1>
</div>
<div class="bd">
<input type="hidden" id="openid" name="openid" value="<%=openid%>" />
    <div class="weui_search_bar">
        <form class="weui_search_outer">
            <div class="weui_search_inner">
                <i class="weui_icon_search"></i>
                <input id="searchName" name="searchName" type="search" class="weui_search_input"  placeholder="输入平台用户名或者手机号码"/>
            </div>
        </form>
        &nbsp;<a onclick="searchData();" href="javascript:" class="weui_btn weui_btn_mini weui_btn_primary">搜索</a>
    </div>
    <div id="searchResult">
		
		
	</div>

</div>
</body>
</html>