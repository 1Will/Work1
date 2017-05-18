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
		<title>新增联系人档案页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript">
    function submitInfo(){
           //直接跳转 
      var obj = document.getElementById("customerName"); //定位id
      var index = obj.selectedIndex; // 选中索引
      var customerName = obj.options[index].value; // 选中值
            document.myForm.action = "/EventNoti/addContactArchivesServlet?customerN="+customerName;
			document.myForm.submit();
    }
   
</script>
	</head>

	<body>
		<div class="container" id="container">


		</div>
		<form name="myForm" id="myForm" method="post" action="/EventNoti/addContactArchivesServlet">
			<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
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
							<select class="weui_select" name="customerName" id="customerName">
                                 <%
                                 List<CustomerInfo> customerInfoList=(List<CustomerInfo>)request.getAttribute("customerInfoList");
                                 for(int i=0;i<customerInfoList.size();i++){
                                     CustomerInfo customerInfo=customerInfoList.get(i);
                             	 %>
                             	<option value="<%=customerInfo.getId()%>"><%=customerInfo.getCustomerName()%></option>
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
								class="weui_textarea" placeholder="请输入印象描述" rows="3"></textarea>
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
							<input class="weui_input" type="string" value="" id="mobilePhone"
								name="mobilePhone" />
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
							<input class="weui_input" type="string" value="" id="phone"
								name="phone" />
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
							<input class="weui_input" type="string" value="" id="email"
								name="email" />
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
							<input class="weui_input" type="string" value="" id="qq"
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

		</form>
	</body>
</html>
