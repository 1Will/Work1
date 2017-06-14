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
<title>注册信息</title>
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
<div class="container" id="container"></div>
	  <form id="myForm" method="post" action="/souvc/saveUserInfo">
	  <input type="hidden" name="userid" id="userid" value="<%=openid %>">
	  <div class="bd">
	  
	  <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">登录名</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="loginName" id="loginName"  class="weui_input" type="text"  
					placeholder="请输入公司名称" onfocus="this.placeholder=' '" />
				</div>
		 </div>
     </div>
	  <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">姓名</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="name" id="name"  class="weui_input" type="text" 
					 placeholder="请输入姓名" onfocus="this.placeholder=' '" />
				</div>
		 </div>
     </div>
	  <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">手机号</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="tel" id="tel"  class="weui_input" type="number" pattern="[0-9]*" 
					 placeholder="请输入手机号码" onfocus="this.placeholder=' '" />
				</div>
		 </div>
     </div>
     <div class="weui_cells_title">性别</div>
    	<div class="weui_cells">
	        <div class="weui_cell weui_cell_select">
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" name="sex" id="sex">
	                    <option value="男">男</option>
				         <option value="女">女</option>
	                </select>
	            </div>
	        </div>
        </div>
	  <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">公司名称</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="company" id="company"  class="weui_input" type="text" 
					 placeholder="请输入公司名称" onfocus="this.placeholder=' '" />
				</div>
		 </div>
     </div>
	  <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">职位</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="position" id="position"  class="weui_input" type="text" 
					 placeholder="请输入职位信息" onfocus="this.placeholder=' '" />
				</div>
		 </div>
     </div>
	  <div class="weui_cells_title">业务</div>
    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <textarea id="business" name="business" class="weui_textarea"  rows="3"></textarea>
            </div>
        </div>
    </div>
    <div class="weui_cells_title">爱好</div>
    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <textarea id="hobby" name="hobby" class="weui_textarea"  rows="3"></textarea>
            </div>
        </div>
    </div>
	  <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">邮箱</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="email" id="email"  class="weui_input" type="text" 
					 placeholder="请输入邮箱地址" onfocus="this.placeholder=' '" />
				</div>
		 </div>
     </div>
     <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
				<div class="weui_cell_hd"><label for="" class="weui_label">地址</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input name="locale" id="locale"  class="weui_input" type="text" 
					 placeholder="请输入地址" onfocus="this.placeholder=' '" />
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