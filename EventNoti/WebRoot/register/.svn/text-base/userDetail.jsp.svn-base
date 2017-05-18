<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%

	String loginName = (String)request.getAttribute("loginName");
	String name = (String)request.getAttribute("name");
	String tel = (String)request.getAttribute("tel");
	String sex = (String)request.getAttribute("sex");
	String company = (String)request.getAttribute("company");
	String position = (String)request.getAttribute("position");
	String hobby = (String)request.getAttribute("hobby");
	String business = (String)request.getAttribute("business");
	String email = (String)request.getAttribute("email");
	String locale = (String)request.getAttribute("locale");

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>请假详情</title>
	<link rel="stylesheet" href="<%=basePath%>/css/page.css"/>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script type="text/javascript">
		function aa(){
    	wx.closeWindow();
		}
    </script>
</head>

<body>
<div class="page">
    <div class="page__hd">
        <p class="page__desc">个人注册详情</p>
    </div>
    <div class="page__bd">
        <div class="weui-form-preview">
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">登录名</label>
                    <em class="weui-form-preview__value"><%=company %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">姓名</label>
                    <em class="weui-form-preview__value"><%=name %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">手机号码</label>
                    <em class="weui-form-preview__value"><%=tel %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">性别</label>
                    <em class="weui-form-preview__value"><%=sex %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">公司</label>
                    <em class="weui-form-preview__value"><%=company %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">职位</label>
                    <em class="weui-form-preview__value"><%=position %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">邮箱</label>
                    <em class="weui-form-preview__value"><%=email %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">地址</label>
                    <em class="weui-form-preview__value"><%=locale %>天</em>
                </div>
            </div>
			
            <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">爱好</label>
                    <span class="weui-form-preview__value"><%=hobby %></span>
                </div>
            </div>
			<div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">业务</label>
                    <span class="weui-form-preview__value"><%=business %></span>
                </div>
            </div>
            <div class="weui-form-preview__ft">
                <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:" onclick="aa();">返回</a>
            </div>
        </div>
      
    </div>
</div>

</body>
</html>
