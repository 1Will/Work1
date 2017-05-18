<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>客户选择</title>
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>


<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function binding(id) {
 alert(id);
          window.opener.document.getElementById("customerName2").value=id;
          alert(window.opener.document.getElementById("customerName2").value);
          WeixinJSBridge.call('closeWindow');    
  }
function searchData(){
	
	$.ajax({
        url: "/EventNoti/searchCusomerServlet",
        type: "POST",
        dataType: "json",
        data: {name:$("#searchName").val()},
        success: function(result){
            $("#searchResult").empty();
            if(result != null && result != ""){
            var html = "<div class='weui_cells_title'>搜索结果</div>";
            $.each(result, function(commentIndex, comment){
                html += "<div class='weui_cells'><div class='weui_cell'><div class='weui_cell_bd weui_cell_primary'>"
                +"<p><span>"+comment.name+"</span></p>"
                +"</div><div class='weui_cell_ft'><a onclick='binding("+comment.id+");' "
                +"href='javascript:' class='weui_btn weui_btn_mini weui_btn_primary'>选择</a></div></div></div>";

          });
            $("#searchResult").html(html);
            }else{
            	var html = "<div class='weui_cells_title' style='margin-top:30%;margin-left:35%'>未搜索到客户</div>";
            	 $("#searchResult").html(html);
            }
		  
        }
    });
}

</script>
</head>
<body>
<div class="container" id="container"></div>
<div class="hd">
    <h1 class="page_title">客户搜索</h1>
</div>
<div class="bd">
    <div class="weui_search_bar">
        <form class="weui_search_outer">
            <div class="weui_search_inner">
                <i class="weui_icon_search"></i>
                <input id="searchName" name="searchName" type="search" class="weui_search_input"  placeholder="请输入名称"/>
            </div>
        </form>
        &nbsp;<a onclick="searchData();" href="javascript:" class="weui_btn weui_btn_mini weui_btn_primary">搜索</a>
    </div>
    <div id="searchResult">
		
		
	</div>

</div>
</body>
</html>