<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.sys.util.Constants"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constants.SYS_PRODUCT_NAME %>-意见反馈</title>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/common.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/header.css" />
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/main.css" />
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.2.min.js"></script>
<meta name="keywords" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<meta name="description" itemprop="description" content="<%=Constants.SYS_PRODUCT_KEYWORDS %>">
<script type="text/javascript">
function SetCwinHeight(){
	var iframeid=document.getElementById("feedbackFrame");
	if (document.getElementById){
	   if (iframeid && !window.opera){
		   if (iframeid.contentDocument && iframeid.contentWindow.document.documentElement.scrollHeight){
		     var height1 = iframeid.contentWindow.document.documentElement.scrollHeight
		   	 iframeid.height = height1>100?height1:100;
		   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
		     var height2 = iframeid.Document.body.scrollHeight;
		  	 iframeid.height = height2>100?height2:100;
		   }
	   }
	}
}

var temp1=false,temp2=false;
function feedback(){
	if(!temp1){
		checkValue(document.getElementById("content").value, 'content');
	}
	if(!temp2){
		checkValue(document.getElementById("contact").value, 'contact');
	}
	if(temp1 && temp2){
		document.feedbackForm.action = '/v.bo?method=addFeedback';
		document.feedbackForm.submit();
	}
}
function checkValue(value, id){
	if(id == 'content'){
		if(value == '' || value == "请填写您遇到的问题或意见建议，您的意见对微课慕课网非常重要，是微课慕课网前进的动力！"){
			document.getElementById(id).value = "请填写您遇到的问题或意见建议，您的意见对微课慕课网非常重要，是微课慕课网前进的动力！";
			document.getElementById(id + "_div").className = "two";
			document.getElementById(id + "_tip").style.display = "block";
			document.getElementById(id).style.border = "#f0c1c7 1px solid";
			document.getElementById(id).style.color = "#aaa";
			temp1 = false;
		}else{
			document.getElementById(id + "_div").className = "one";
			document.getElementById(id + "_tip").style.display = "none";
			document.getElementById(id).style.border = "#cccccc 1px solid";
			temp1 = true;
		}
	}
	if(id == 'contact'){
		if(value == '' || value == "请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！"){
			document.getElementById(id).value = "请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！";
			document.getElementById(id + "_div").className = "two";
			document.getElementById(id + "_tip").style.display = "block";
			document.getElementById(id).style.border = "#f0c1c7 1px solid";
			document.getElementById(id).style.color = "#aaa";
			temp2 = false;
		}else{
			document.getElementById(id + "_div").className = "one";
			document.getElementById(id + "_tip").style.display = "none";
			document.getElementById(id).style.border = "#cccccc 1px solid";
			temp2 = true;
		}
	}
}
function isNull(value, id){
	if(value == '请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！' || value == '请填写您遇到的问题或意见建议，您的意见对微课慕课网非常重要，是微课慕课网前进的动力！'){
		document.getElementById(id).value = "";
		document.getElementById(id).style.border = "#3eabff 1px solid";
		document.getElementById(id).style.color = "#000";
	}
}
</script>
</head>

<body style="background:#f7f7f7;">
<%@ include file="top.jsp"%>

<div class="view_main">
	<div class="view_left">
		<ul>
		<a href="/v-aboutus-12-0.htm"><li>关于我们</li></a>
		<a href="/v-aboutgroup-12-0.htm"><li>团队介绍</li></a>
		<a href="/v-job-12-0.htm"><li>人才招聘</li></a>
		<a href="/v-contact-12-0.htm"><li>联系我们</li></a>
		<a href="/feedback.htm"><li class="hover">意见反馈</li></a>
		<a href="/v-link-12-0.htm"><li>友情链接</li></a>
		<a href="/v-applylink-12-0.htm"><li style="border-bottom:none;">申请链接</li></a>
		</ul>
	</div>

	<div class="view_right">
		<form name="feedbackForm" method="post">
		<div class="view_right_01">
			<h3>意见反馈</h3>
			<div class="view_right_one">
				<logic:notPresent name="addOk">
				<span class="one" id="content_div">
				  <textarea name="content" id="content" class="line_01" onfocus="isNull(this.value, 'content')" onblur="checkValue(this.value, 'content')">请填写您遇到的问题或意见建议，您的意见对微课慕课网非常重要，是微课慕课网前进的动力！</textarea>
				</span>
				<span class="three" id="content_tip">意见反馈内容不能为空！</span>
				<span class="one" id="contact_div">
				  <input name="contact" id="contact" type="text" class="line_02" value="请留下您的联系方式（手机号、邮箱、QQ），产品经理可能会与您联系！" onfocus="isNull(this.value, 'contact')" onblur="checkValue(this.value, 'contact')"/>
				</span>
				<span class="three" id="contact_tip">联系方式不能为空！</span>
				<span class="four">
				<a href="javascript:feedback()"><img src="/skin/wkmk/images/f05.gif" border="0" /></a>
				</span>
				</logic:notPresent>
				<logic:present name="addOk">
				<div>
					<p style="font-size:20px;padding-top:130px;padding-left:260px;">提交成功</p>
					<p style="font-size:12px;padding-top:10px;padding-left:180px;padding-bottom:150px;">感谢您的建议，我们会努力完善微课慕课网！</p>
				</div>
				</logic:present>
			</div>
			
			<div class="view_right_two">
			微课幕课交流QQ群<br />
			282673318<br />
			职教微课大赛专属QQ群<br />
			474297139<br /><br />
			技术支持服务电话<br />
			010-83456666<br />
			010-84929209
			</div>
		</div>
		<input type="hidden" name="hiddenCode" value="<%=request.getAttribute("hiddenCode") %>"/>
		</form>
	
		<iframe name="feedbackFrame" id="feedbackFrame" onload="SetCwinHeight()" src="/v.bo?method=feedbackf" width="900" height="100" scrolling="no" frameborder="0"></iframe>
	</div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>