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
	String userid = (String) request.getAttribute("userid");
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
		<script type="text/javascript">
    function submitInfo(){
           //直接跳转 
     /*  var obj = document.getElementById("customerName"); //定位id
      var index = obj.selectedIndex; // 选中索引
      var customerName = obj.options[index].Text; // 选中文本值 */
            document.myForm.action = "/EventNoti/addCustomerInfoServlet";
			document.myForm.submit();
    }
   
</script>
	</head>

	<body>
		<div class="container" id="container">


		</div>
		<form name="myForm" id="myForm" method="post" action=" ">
			<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
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
							<select class="weui_select" name="country" id="country">
                                 <%
                                                             	
                                  %>
                          <%--    	<option value="<%=customerInfo.getId()%>"><%=customerInfo.getCustomerName()%></option> --%>
                                    <option value="43">中国</option>
                                 <% 
                                   // }
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
							<select class="weui_select" name="province" id="province">
                                 <%
                                                             	
                                  %>
                       <%--       	<option value="<%=customerInfo.getId()%>"><%=customerInfo.getCustomerName()%></option> --%>
                                    <option value="44">安徽</option>
                                 <% 
                                   // }
                                  %>
							
							</select>
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
                                 <%
                                                             	
                                  %>
                      <%--        	<option value="<%=customerInfo.getId()%>"><%=customerInfo.getCustomerName()%></option> --%>
                                    <option value="76">合肥</option>
                                 <% 
                                   // }
                                  %>
							
							</select>
						</div>
					</div>
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">
								存档日期
							</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="date" value="" id="archiveTime"
								name="archiveTime" />
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
								name="address" />
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
								class="weui_textarea" placeholder="请输入企业介绍" rows="3"></textarea>
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
							<input class="weui_input" type="string" value="" id="mobilePhone"
								name="mobilePhone" />
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
							<input class="weui_input" type="string" value="" id="phone"
								name="phone" />
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
								class="weui_textarea" placeholder="请输入印象描述" rows="3"></textarea>
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
                                       List<PersonnelFiles> personnelFilesList=(List<PersonnelFiles>)request.getAttribute("personnelFilesList"); 
                                       for(int i=0;i<personnelFilesList.size();i++){   
                                            PersonnelFiles personnelFiles=personnelFilesList.get(i);                  	
                                  %>
                                 	<option value="<%=personnelFiles.getId()%>"><%=personnelFiles.getName()%></option> 
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
								class="weui_textarea" placeholder="请输入咨询内容" rows="3"></textarea>
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
