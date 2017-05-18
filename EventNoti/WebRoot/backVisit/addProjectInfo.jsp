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
		<title>新增项目页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript">
    function submitInfo(){
			document.getElementById("myForm").submit();
			 
    }
</script>
	</head>

	<body>
		<div class="container" id="container">


		</div>
		<form name="myForm" id="myForm" method="post" action="/EventNoti/addProjectInfoServlet">
			<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
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
							<select class="weui_select" name="customerName" id="customerName">

								<option value=""></option>

							</select>
						</div>
					</div>
				</div>
				<div class="weui_cells_title">
					项目联系人
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="contactName" id="contactName">

								<option value=""></option>

							</select>
						</div>
					</div>
				</div>
                <div class="weui_cells_title">
					项目负责人
				</div>
				<div class="weui_cells">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="creatorName" id="creatorName">

								<option value=""></option>

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
							<input class="weui_input" type="date" value="" id="createdTime"
								name="createdTime" />
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
								class="weui_textarea" placeholder="请输入项目简介" rows="3"></textarea>
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
