<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String startDate = (String)request.getAttribute("startDate");
	String endDate = (String)request.getAttribute("endDate");
	String days = (String)request.getAttribute("days");
	String yuanyin = (String)request.getAttribute("yuanyin");
	String type = (String)request.getAttribute("type");
	String shenhe = (String)request.getAttribute("shenhe");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<!DOCTYPE html>
<html>
<head>
<title>提交成功</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/page.css"/>
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
<div class="container" id="container"></div>

<div class="weui_msg">
    <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
    <div class="weui_text_area">
        <h2 class="weui_msg_title">操作成功</h2>
        <div class="page__bd">
			<div class="weui-form-preview">
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">请假类型</label>
						<em class="weui-form-preview__value"><%=type %></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">开始时间</label>
						<em class="weui-form-preview__value"><%=startDate %></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">结束时间</label>
						<em class="weui-form-preview__value"><%=endDate %></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">请假天数</label>
						<em class="weui-form-preview__value"><%=days %>天</em>
					</div>
				</div>
				<div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">请假事由</label>
                    <span class="weui-form-preview__value"><%=yuanyin %></span>
                </div>
            </div>
			</div>
	</div>
        
    </div>
    <div class="weui_opr_area">
        <p class="weui_btn_area">
            <a href="javascript:;" class="weui_btn weui_btn_primary" onclick="aa();">确定</a>
        </p>
    </div>
</div>

</body>
</html>
