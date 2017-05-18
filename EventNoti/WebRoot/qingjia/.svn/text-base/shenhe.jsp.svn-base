<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String startDate = (String)request.getAttribute("startDate");
	String endDate = (String)request.getAttribute("endDate");
	String days = (String)request.getAttribute("days");
	String yuanyin = (String)request.getAttribute("yuanyin");
	String type = (String)request.getAttribute("type");
	String qid = (String)request.getAttribute("qid");
	String name = (String)request.getAttribute("name");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>审核页面</title>
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/page.css"/>
<script src="<%=basePath%>/js/zepto.min.js"></script>
<script src="<%=basePath%>/js/router.min.js"></script>
<script src="<%=basePath%>/js/example.js"></script>
<script>
function shenheResult(type){
	   
	document.getElementById("shenheType").value = type;
	   
	document.getElementById("myForm").submit();
	}

</script>
</head>

<body>
<div class="container" id="container"></div>
<div class="weui_msg">
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
						<label class="weui-form-preview__label">请假人</label>
						<em class="weui-form-preview__value"><%=name %></em>
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
    
    <form name="myForm" id="myForm" method="post" action="/EventNoti/shenheServlet">
		<input type="hidden" name="shenheType" id="shenheType" />
		<input type="hidden" name="qid" id="qid" value="<%=qid %>" />
		
		<div class="weui_cells_title" style="text-align: left;">意见说明</div>
	    <div class="weui_cells weui_cells_form">
	        <div class="weui_cell">
	            <div class="weui_cell_bd weui_cell_primary">
	                <textarea id="fankui" name="fankui" class="weui_textarea" placeholder="请输入反馈意见" rows="3"></textarea>
	            </div>
	        </div>
	    </div>
	    
	    <div class="weui_btn_area">
        	<a class="weui_btn weui_btn_primary" href="javascript:" id="showTooltips" onclick="shenheResult(1);">同意</a>
    	</div>
    	<div class="weui_btn_area">
        	<a class="weui_btn weui_btn_primary" href="javascript:" id="showTooltips" onclick="shenheResult(2);">驳回</a>
    	</div>
	</form>
</div>

</body>
</html>
