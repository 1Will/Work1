<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<!DOCTYPE html>
<html>
<head>
<title>操作成功</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>
<script src="<%=basePath%>/js/zepto.min.js"></script>
<script src="<%=basePath%>/js/router.min.js"></script>
<script src="<%=basePath%>/js/example.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script type="text/javascript">
		function aa(){
    	wx.closeWindow();
		}
    </script>
</head>

<body>
<div class="weui_msg">
    <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
    <div class="weui_text_area">
        <h2 class="weui_msg_title">操作成功</h2>
        <p class="weui_msg_desc">信息已经发送给相关人员</p>
    </div>
    <div class="weui_opr_area">
        <p class="weui_btn_area">
            <a href="javascript:;" class="weui_btn weui_btn_primary" onclick="aa();">返回</a>
        </p>
    </div>
</div>

</body>
</html>
