<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%

	String startDate = (String)request.getAttribute("startDate");
	String endDate = (String)request.getAttribute("endDate");
	String days = (String)request.getAttribute("days");
	String yuanyin = (String)request.getAttribute("yuanyin");
	String type = (String)request.getAttribute("type");
	String shenhe = (String)request.getAttribute("shenhe");
	String status = (String)request.getAttribute("status");
	String fankui = (String)request.getAttribute("fankui");
	String name = (String)request.getAttribute("name");

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
        <p class="page__desc">请假详情</p>
    </div>
    <div class="page__bd">
        <div class="weui-form-preview">
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">请假人</label>
                    <em class="weui-form-preview__value"><%=name %></em>
                </div>
            </div>
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">审核人</label>
                    <em class="weui-form-preview__value"><%=shenhe %></em>
                </div>
            </div>
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
			
			<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">审核结果</label>
                    <em class="weui-form-preview__value">
						<%
							if(status.equals("1") || status =="1"){
						%>
						同意
						<%
						}else{
						%>
						驳回
						<%
						}
						%>
					</em>
                </div>
            </div>

            <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">请假事由</label>
                    <span class="weui-form-preview__value"><%=yuanyin %></span>
                </div>
            </div>
			<div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">审核反馈</label>
                    <span class="weui-form-preview__value"><%=fankui %></span>
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
