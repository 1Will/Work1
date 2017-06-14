<%@page import="main.pojo.UsersInfo"%>
<%@page import="main.pojo.Area"%>
<%@page import="main.pojo.PersonnelFiles"%>
<%@page import="main.pojo.CodeValue"%>
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
		<title>新增客户信息档案页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
		<script type="text/javascript">
  
    
    function getProvince(){  
    var url="searchProviceServlet?method=getProvince";//请求后台的url  
    $("#province").empty();//先置空  
    $("#province").append($(''));//加上--请选择--选项  


    url+="&t="+(new Date()).valueOf();//url参数  
  
    $.ajax({  
    url:url,  
    type:'POST',//POST方式  
    dataType:'text',//返回text类型  
    data: {areaId:$("#country").val()},
    beforeSend:function(xmlHttpRequest,status){  
    
    },  
    success:function(data,status){  
    var d=eval(data);//解析  
    $(d).each(function(index,entity){  
    $("#province").append($('<option value="'+entity['id']+'">'+entity['name']+'</option>'));//后台数据加到下拉框  
    });  
    },  
    complete:function(xmlHttpRequest,status){  
    
    },  
    error:function(){  
    
    }  
    });  
  
    }  
    
    function getCity(){  
    var url="searchCityServlet?method=getCity";//请求后台的url  
    $("#city").empty();//先置空  
    $("#city").append($(''));//加上--请选择--选项  


    url+="&t="+(new Date()).valueOf();//url参数  
  
    $.ajax({  
    url:url,  
    type:'POST',//POST方式  
    dataType:'text',//返回text类型  
    data: {areaId:$("#province").val()},
    beforeSend:function(xmlHttpRequest,status){  
    
    },  
    success:function(data,status){  
    var d=eval(data);//解析  
    $(d).each(function(index,entity){  
    $("#city").append($('<option value="'+entity['id']+'">'+entity['name']+'</option>'));//后台数据加到下拉框  
    });  
    },  
    complete:function(xmlHttpRequest,status){  
    
    },  
    error:function(){  
    
    }  
    });  
  
    }  
    
     //获取当前(yyyy-MM-dd)型日期
   function aa(){
     var nowDate=formatDate();
     document.getElementById("setupTime").value=nowDate; 
  
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

		
      function submitInfo(){
          
           if(document.getElementById("customerName").value.replace(/\s*/g, "") == "")
       {
        alert("请先填写客户名称");
		return false;
		}

           if(document.getElementById("keyContacter").value.replace(/\s*/g, "") == "")
       {
        alert("请先填写主要联系人");
		return false;
		}

           if(document.getElementById("province").value.replace(/\s*/g, "") == "")
       {
        alert("请先选择所在国家");
		return false;
		}

           if(document.getElementById("city").value.replace(/\s*/g, "") == "")
       {
        alert("请先选择所在省份");
		return false;
		}

           //直接跳转 
            document.myForm.action = "/EventNoti/addCustomerInfoServlet";
			document.myForm.submit();
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
		<form name="myForm" id="myForm" method="post" action=" ">
		<input type="hidden" name="userid" id="userid" value="<%=userInfo.getId()%>" /> 
			<div class="bd">

            <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								客户名称
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="customerName"
								name="customerName" />
						</div>
					</div>
       			 </div>
				<div class="weui_cells_title">
					企业性质
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="companyNature" id="companyNature">
								<option value="149">国营企业</option>
								<option value="150">外资企业</option>
								<option value="151">集体企业</option>
								<option value="152">私营企业</option>
								<option value="153">合资企业</option>
								<option value="183">民营企业</option>
								<option value="450">政府机关</option>
							</select>
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					行业
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="industry" id="industry">
                                 <%
                                       List<CodeValue> codeValueList=(List<CodeValue>)request.getAttribute("codeValueList"); 
                                       for(int i=0;i<codeValueList.size();i++){   
                                            CodeValue codeValue=codeValueList.get(i);                  	
                                  %>
                                 	<option value="<%=codeValue.getId()%>"><%=codeValue.getName()%></option> 
                                 <% 
                                    }
                                  %>
							
							</select>
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
							<input class="weui_input" type="date" value="" id="setupTime"
								name="setupTime" />
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					客户状态
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="customerType" id="customerType">
								<option value="111">潜在客户</option>
								<option value="112">目标客户</option>
								<option value="116">签单客户</option>
								<option value="345">失败客户</option>
							</select>
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					国家
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="country" id="country" onchange="getProvince()">
                                 <%
                                     List<Area> areaList =(List<Area>) request.getAttribute("areaList");
                                     for(int i=0;i<areaList.size();i++){
                                         Area country=areaList.get(i);
                                  %>
                              	<option value="<%=country.getId()%>"><%=country.getName()%></option>
                                 <% 
                                    }
                                  %>
							</select>
							
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					省会
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="province" id="province" onchange= "getCity()"> 
                                 <option value="">
										-请选择-
								 </option>
							</select>
							<!-- <a class="weui_btn weui_btn_mini weui_btn_primary"
									href="javascript:void(0)"
									onclick="getCity();document.getElementById('fade').style.display='block'">确认</a>
						        select(this.options[this.options.selectedIndex].value)
							 -->
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					城市
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="city" id="city">
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
								公司网站
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="web"
								name="web" />
						</div>
					</div>
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								地址
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="address"
							 maxlength="50"	name="address" />
						</div>
					</div>
				</div>
			   	<div class="weui_cells_title">
					企业简介
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="businessScope" name="businessScope"
								class="weui_textarea" placeholder="请输入企业介绍" onfocus="this.placeholder=' '"
								maxlength="500" rows="3"></textarea>
						</div>
					</div>
				</div>
                <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
							   主要联系人
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="keyContacter"
								name="keyContacter" />
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
								办公电话
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="string" value="" id="phone"
								name="phone" onchange="checkPhone()" />
						</div>
					</div>
				</div>
				  <div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								手机
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="number" value="" id="mobilePhone"
								name="mobilePhone" onchange="checkMPhone()" />
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					印象描述
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="effectDescribe" name="effectDescribe"
								class="weui_textarea" placeholder="请输入印象描述" onfocus="this.placeholder=' '"
								maxlength="500" rows="3"></textarea>
						</div>
					</div>
				</div>
			    
			    <div class="weui_cells_title">
					业务员
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="saleman" id="saleman">
                               <%
                                 List<UsersInfo> usersInfoList=(List<UsersInfo>)request.getAttribute("usersInfoList");
                                 for(int i=0;i<usersInfoList.size();i++){
                                     UsersInfo usersInfo=usersInfoList.get(i);
                             	 %>
                             	<option value="<%=usersInfo.getName()%>"><%=usersInfo.getName()%></option>
                                 <% 
                                    }
                                  %>
							</select>
						</div>
					</div>
				</div>
			    
                <div class="weui_cells_title">
					信息来源
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="resource" id="resource">
								<option value="154">网站</option>
								<option value="155">报纸</option>
								<option value="156">杂志</option>
								<option value="157">介绍</option>
								<option value="434">安徽软协</option>
								<option value="435">合肥科创</option>
								<option value="436">青企协</option>
								
							</select>
						</div>
					</div>
				</div>
				
				<div class="weui_cells_title">
					咨询内容
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary">
							<textarea id="advisoryContent" name="advisoryContent"
								class="weui_textarea" placeholder="请输入咨询内容" onfocus="this.placeholder=' '"
								maxlength="500" rows="3"></textarea>
						</div>
					</div>
				</div>
                
                
			<div class="weui_btn_area">
				<a class="weui_btn weui_btn_primary" href="javascript:"
					id="showTooltips" onclick="submitInfo();">提交</a>
			</div>

		</form>
	</body>
</html>
