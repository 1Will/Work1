<%@page import="main.pojo.PersonnelFiles"%>
<%@page import="main.pojo.ContactArchives"%>
<%@page import="main.pojo.CustomerInfo"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="souvc.pojo.WeixinUserInfo"%>

<%@page import="souvc.pojo.UserInfo"%>
<%@page import="java.util.List"%>
<%@page import="main.pojo.ProjectInfo"%>
<%

  UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
  String userName=userInfo.getName();

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
	
		<title>新增项目页面</title>
		
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
data: {projectinfoid:$("#customerId").val()},
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
	
		
function binding(id,name) {

          document.getElementById("customerName").value=name; 
          document.getElementById("customerId").value=id;
        //  getProject();
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
            var jsonLength=0; //记录长度
            $.each(result, function(commentIndex, comment){
                html += "<div class='weui_cells'><div class='weui_cell'><div class='weui_cell_bd weui_cell_primary'>"
                +"<p><span>"+comment.name+"</span></p>"
                +"</div><div class='weui_cell_ft'><a onclick=\"binding('"+comment.id+"','"+comment.name+"');"
                +"document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'\""
                +"href='javascript:void(0)' class='weui_btn weui_btn_mini weui_btn_primary'>选择</a></div></div></div>";
                jsonLength++;

          });
          
            $("#searchResult").html(html);
            $("#searchResult").prepend("<p>当前检索到<span style='color:green;' >"+jsonLength+"</span>条</p>");
           
            }else{
            	var html = "<div class='weui_cells_title' style='margin-top:30%;margin-left:35%'>未搜索到客户</div>";
            	 $("#searchResult").html(html);
            }
		  
        }
    });
}

  //获取当前(yyyy-MM-dd)型日期
   function aa(){
     var nowDate=formatDate();
     document.getElementById("createdTime").value=nowDate; 
  
   }
 
			function formatDate() {
				var d = new Date(); 
				month = '' + (d.getMonth() + 1);				 
				day = '' + d.getDate();
				year = d.getFullYear();
				if (month.length < 2) {
					month = '0' + month;
				}
				if (day.length < 2) {
					day = '0' + day;
				}
				return [ year, month, day ].join('-');
			}
			
		

 function submitInfo(){
  
       if(document.getElementById("customerName").value.replace(/\s*/g, "") == "")
       {
        alert("请先选择客户名称");
		return false;
		}
      
       if(document.getElementById("projectName").value.replace(/\s*/g, "") == "")
       {
        alert("请先填写项目名称");
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

	<body onload="aa()">
		<div class="container" id="container">
		</div>
		<form name="myForm" id="myForm" method="post" action="/EventNoti/addProjectInfoServlet">
			<input type="hidden" name="userid" id="userid" value="<%=userInfo.getId()%>" />
			<input type="hidden" name="controllerName" id="controllerName" value="<%=userName%>" />
			<input type="hidden" name="creatorName" id="creatorName" value="" />
			<input type="hidden" name="customerId" id="customerId" value="" />
			<%-- <input type="hidden" name="creatorName" id="creatorName" value="<%=creatorName%>" /> --%>
			<div class="bd">
            
            <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								项目名称
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="projectName"
								name="projectName" />
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					客户名称
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="customerName" name="customerName" class="weui_textarea"
									placeholder="" rows="1" readonly="readonly" ></textarea>
                                     <a class="weui_btn weui_btn_mini weui_btn_primary"
									href="javascript:void(0)"
									onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">选择</a>
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					项目联系人
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
					联系人角色说明
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="conOutline" name="conOutline"
								class="weui_textarea" placeholder="请输入联系人简介" onfocus="this.placeholder=' '"
								maxlength="1000" rows="3"></textarea>
						</div>
					</div>
				</div>
               
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								创建日期
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="date" value="" id="createdTime"
								name="createdTime" />
						</div>
					</div>
				</div>
				
				 <div class="weui_cells_title">
					业务属性
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="businessType" id="businessType">
								<option value="522">使用者</option>
								<option value="523">决策者</option>
								<option value="524">财务</option>
								<option value="525">其他</option>
							</select>
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					项目概要
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="outline" name="outline"
								class="weui_textarea" placeholder="请输入项目简介" onfocus="this.placeholder=' '" 
								maxlength="1000" rows="3"></textarea>
						</div>
					</div>
				</div>
				
				<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目负责人
							</label>
							<em class="weui-form-preview__value">
							<%=(userName==null||userName.equals(""))?"":userName%></em>
						</div>
					</div>
				
			<div class="weui_btn_area">
				<a class="weui_btn weui_btn_primary" href="javascript:"
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
                                 <a href="javascript:void(0)" class="weui_btn weui_btn_mini weui_btn_primary" 
                                 onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
							关闭</a>
							</div>
						</form>
					</div>
					<div id="searchResult">
					</div>
				</div>
				<div id="fade" class="black_overlay">
				</div>
            </div> 
		
		
		</form>
	</body>
</html>
