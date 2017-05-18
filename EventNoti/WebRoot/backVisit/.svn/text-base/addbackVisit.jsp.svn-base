<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="souvc.pojo.WeixinUserInfo"%>
<%@page import="souvc.pojo.UserInfo"%>
<%@page import="java.util.List"%>
<%@page import="main.pojo.ProjectInfo"%>
<%@page import="main.pojo.CustomerInfo"%>
<%@page import="main.pojo.ContactArchives"%>
<%
	UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,user-scalable=0">
		<title>新增回访页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
		<script type="text/javascript">

		     function add(){
		     
		    window.open("searchCusomerServlet");

		     }
    
    function getContactArchives(){  
var url="searchContactArchivesServlet?method=getContactArchives";//请求后台的url  
$("#contactname").empty();//先置空  
$("#contactname").append($(''));//加上--请选择--选项  

url+="&t="+(new Date()).valueOf();//url参数  
  
$.ajax({  
url:url,  
type:'POST',//POST方式  
dataType:'text',//返回text类型  
data: {projectinfoid:$("#customerNameid").val()},
beforeSend:function(xmlHttpRequest,status){  
    
},  
success:function(data,status){  
var d=eval(data);//解析  
$(d).each(function(index,entity){  
$("#contactname").append($('<option value="'+entity['id']+'">'+entity['name']+'</option>'));//后台数据加到下拉框  
});  
},  
complete:function(xmlHttpRequest,status){  
    
},  
error:function(){  
    
}  
});  
  
}  
 function getProject(){  
var url="searchProjectinfoServlet?method=getProject";//请求后台的url  
$("#projectName").empty();//先置空  
$("#projectName").append($(''));//加上--请选择--选项  


url+="&t="+(new Date()).valueOf();//url参数  
  
$.ajax({  
url:url,  
type:'POST',//POST方式  
dataType:'text',//返回text类型  
data: {customerid:$("#customerNameid").val()},
beforeSend:function(xmlHttpRequest,status){  
    
},  
success:function(data,status){  
var d=eval(data);//解析  
$(d).each(function(index,entity){  
$("#projectName").append($('<option value="'+entity['id']+'">'+entity['name']+'</option>'));//后台数据加到下拉框  
});  
},  
complete:function(xmlHttpRequest,status){  
    
},  
error:function(){  
    
}  
});  
  
}  
</script>
		<script type="text/javascript">
function binding(id,name) {

          document.getElementById("customerName").value=name; 
          document.getElementById("customerNameid").value=id;  
          getProject();
          getContactArchives();
          
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
            var html = "";
            $.each(result, function(commentIndex, comment){
                html += "<div class='weui_cells'><div class='weui_cell'><div class='weui_cell_bd weui_cell_primary'>"
                +"<p><span>"+comment.name+"</span></p>"
                +"</div><div class='weui_cell_ft'><a onclick=\"binding('"+comment.id+"','"+comment.name+"')\""
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
 function submitInfo(){
  
       if(document.getElementById("customerName").value.replace(/\s*/g, "") == "")
       {
        alert("请先选择回访企业");
		return false;
		}
		if(document.getElementById("visitDate").value.replace(/\s*/g, "") == "")
       {
        alert("请输入回访日期");
		return false;
		}
    	$('#myForm').submit();

  }


</script>
		<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 25%;
	left: 0%;
	width: 100%;
	height: 100%;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity =   80);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 1%;
right: 1%;
	width: 98%;
	height: 60%;
	padding: 2px;
	border: 1px solid #04BE02;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
	</head>

	<body>
		<div class="page">
			<form name="myForm" id="myForm" method="post" action="/EventNoti/addNewBackVisitServlet">
				<input type="hidden" name="userid" id="userid"
					value="<%=userInfo.getId()%>">
				<input type="hidden" id="customerNameid" name="customerNameid"
					value="" />
				<div class="bd">
					<div class="weui_cells_title">
						回访类型
					</div>
					<div class="weui_cells">
						<div class="weui_cell weui_cell_select">
							<div class="weui_cell_bd weui_cell_primary">
								<select class="weui_select" name="backvisittype"
									id="backvisittype">
									<option value="441">
										初次回访
									</option>
									<option value="442">
										售前回访
									</option>
									<option value="443">
										售中回访
									</option>
									<option value="444">
										售后回访
									</option>
									<option value="447">
										合作回访
									</option>
									<option value="445">
										其他回访
									</option>

								</select>
							</div>
						</div>
					</div>

       

					<div class="weui_cells_title">
						客户名称
					</div>
<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="customerName" name="customerName" class="weui_textarea"
									placeholder="" rows="1"></textarea>
<a class="weui_btn weui_btn_mini weui_btn_primary"
									href="javascript:void(0)"
									onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">选择</a>
							</div>
						</div>
					</div>
			
						
 
							
							
						
					<div class="weui_cells_title">
						联系人
					</div>
					<div class="weui_cells">
						<div class="weui_cell weui_cell_select">
							<div class="weui_cell_bd weui_cell_primary">
								<select class="weui_select" name="contactname" id="contactname">
									<option value="">
										-请选择-
									</option>
								</select>
							</div>
						</div>
					</div>
					<div class="weui_cells_title">
						项目名称
					</div>
					<div class="weui_cells">
						<div class="weui_cell weui_cell_select">
							<div class="weui_cell_bd weui_cell_primary">
								<select class="weui_select" name="projectName" id="projectName">
									<option value="">
										-请选择-
									</option>
								</select>

							</div>
						</div>
					</div>

					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_hd">
								<label for="" class="weui_label">
									回访日期
								</label>
							</div>
							<div class="weui_cell_bd weui_cell_primary">
								<input class="weui_input" type="date" id="visitDate"
									name="visitDate" />
							</div>
						</div>
					</div>

					<div class="weui_cells_title">
						回访方式
					</div>
					<div class="weui_cells">
						<div class="weui_cell weui_cell_select">
							<div class="weui_cell_bd weui_cell_primary">
								<select class="weui_select" name="visitType" id="visitType">
									<option value="53">
										电话回访
									</option>
									<option value="54">
										网络回访
									</option>
									<option value="55">
										登门拜访
									</option>
									<option value="449">
										对方来访
									</option>

				</div>
				<div class="weui_cells_title">
					联系人
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="contactName" id="contactName">

								</select>
							</div>
						</div>
					</div>
					<div class="weui_cells_title">
						回访内容
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="visitContent" name="visitContent"
									class="weui_textarea" rows="3"></textarea>
							</div>
						</div>
					</div>
					<div class="weui_cells_title">
						客户反馈
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="feedback" name="feedback" class="weui_textarea"
									rows="3"></textarea>
							</div>
						</div>
					</div>
					<div class="weui_cells_title">
						后期注意
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="attention" name="attention" class="weui_textarea"
									rows="3"></textarea>
							</div>
						</div>
					</div>
					<div class="weui_cells_title">
						备注
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="remarks" name="remarks" class="weui_textarea"
									placeholder="" rows="3"></textarea>
							</div>
						</div>
					</div>

				</div>

				<div class="weui_btn_area">
					<a class="weui_btn weui_btn_primary" href="javascript:void(0)"
						id="showTooltips" onclick="submitInfo();">提交</a>
				</div>

				<div id="light" class="white_content">
					<div class="weui_search_bar">
						<form class="weui_search_outer">
							<div class="weui_search_inner">
								<i class="weui_icon_search"></i>
								<input id="searchName" name="searchName" type="search"
									 placeholder="" />
<a onclick="searchData();" href="javascript:" class="weui_btn weui_btn_mini weui_btn_primary">搜索</a>
<a href="javascript:void(0)" class="weui_btn weui_btn_mini weui_btn_primary" onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
							关闭</a>
							</div>
						</form>
					
						
						
					</div>
					<div id="searchResult">


					</div>

				</div>
				<div id="fade" class="black_overlay">
				</div>
			</form>
		</div>

	</body>
</html>
