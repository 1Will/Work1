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
    UserInfo userInfo=(UserInfo)request.getAttribute("userInfo");
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
		<title>新增联系人档案页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
		<script type="text/javascript">
    		     function add(){
		     
		    window.open("searchCusomerServlet");

		     }
    
</script>
		<script type="text/javascript">
function binding(id,name) {

          document.getElementById("customerName").value=name; 
          document.getElementById("customerId").value=id;
        //  getProject();
          
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
             /*     +"</div><div class='weui_cell_ft'><a onclick=\"binding('"+comment.id+"','"+comment.name+"')\""
                +"href='javascript:' class='weui_btn weui_btn_mini weui_btn_primary'>选择</a></div></div></div>";
              */    
                +"</div><div class='weui_cell_ft'><a onclick=\"binding('"+comment.id+"','"+comment.name+"');"
                +"document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'\""
                +"href='javascript:void(0)' class='weui_btn weui_btn_mini weui_btn_primary'>选择</a></div></div></div>";
                jsonLength++;
          });
		 //   alert("jsonLength:"+jsonLength);
            $("#searchResult").html(html);
            $("#searchResult").prepend("<p>当前检索到<span style='color:green;' >"+jsonLength+"</span>条</p>");
            
            }else{
            	var html = "<div class='weui_cells_title' style='margin-top:30%;margin-left:35%'>未搜索到客户</div>";
            	 $("#searchResult").html(html);
            }
        }
    });
}

  //先判断是否为空，不为空的情况下进行正则匹配座机号
function checkPhone(){
       var value1=$('#phone').val();
       if(value1 !== ''){
           var str=/^0\d{2,3}-\d{7,8}$/;
           var bool=str.test(value1);
           if(!bool){
              alert("请输入正确座机号");
           }
        
        }
     }

  //先判断是否为空，不为空的情况下进行正则匹配手机号
function checkMPhone(){
       var value1=$('#mobilePhone').val();
       if(value1 !== ''){
           var str=/^1\d{10}$/;
           var bool=str.test(value1);
           if(!bool){
              alert("请输入正确手机号");
           }
        
        }
     }

  //先判断是否为空，不为空的情况下进行正则匹配email
function checkEmail(){
       var value1=$('#email').val();
       if(value1 !== ''){
           var str=/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
           var bool=str.test(value1);
           if(!bool){
              alert("请输入正确Email格式");
           }
        
        }
     }


 function submitInfo(){
  
       if(document.getElementById("customerName").value.replace(/\s*/g, "") == "")
       {
        alert("请先选择客户名称");
		return false;
		}

       if(document.getElementById("contactName").value.replace(/\s*/g, "") == "")
       {
        alert("请先填写联系人姓名");
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
		<div class="container" id="container">


		</div>
		<form name="myForm" id="myForm" method="post" action="/EventNoti/addContactArchivesServlet">
			<input type="hidden" name="userid" id="userid" value="<%=userInfo.getId()%>" />
			<input type="hidden" name="customerId" id="customerId" value=" ">
			<input type="hidden" name="customername" id="customername" value=" ">
			<div class="bd">

            <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								姓名
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="contactName"
								name="contactName" />
						</div>
					</div>
			 </div>
           <div class="weui_cells_title">性别</div>
    	     <div class="weui_cells">
	           <div class="weui_cell weui_cell_select">
	             <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" name="sex" id="sex">
	                     <option value="0">男</option>
				         <option value="1">女</option>
	                </select>
	             </div>
	           </div>
           </div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								称呼
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="honorific"
								name="honorific" />
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
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								职位
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="duty"
								name="duty" />
						</div>
					</div>
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								部门
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="dept"
								name="dept" />
						</div>
					</div>
				</div>
			   	<div class="weui_cells_title">
					印象描述
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="enterpriseSynopsis" name="enterpriseSynopsis"
								class="weui_textarea" placeholder="请输入印象描述" onfocus="this.placeholder=' '"
								maxlength="500"  rows="3"></textarea>
						</div>
					</div>
				</div>
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								联系电话
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value=""  id="phone"
								name="phone" onchange="checkPhone()" />
						</div>
					</div>
				</div>
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								手机号
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="number" value=""  id="mobilePhone"
								name="mobilePhone" onchange="checkMPhone()" />
						</div>
					</div>
				</div>
               
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								生日
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="date" value="" id="birthday"
								name="birthday" />
						</div>
					</div>
				</div>
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								家庭住址
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="address"
								name="address" />
						</div>
					</div>
				</div>
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								email
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" onchange="checkEmail()" value=""  id="email"
								name="email"  />
						</div>
					</div>
				</div>
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								 QQ
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="number" value="" id="qq"
								name="qq" />
						</div>
					</div>
				</div>
                <div class="weui_cells_title">
					熟悉程度
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="type" id="type">
								<option value="169">陌生</option>
								<option value="170">一般</option>
								<option value="171">熟悉</option>
							</select>
						</div>
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
                      
                              <!--    <a class="weui_btn weui_btn_mini weui_btn_primary"
									href="javascript:void(0)"
									onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">选择</a> -->
						
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
