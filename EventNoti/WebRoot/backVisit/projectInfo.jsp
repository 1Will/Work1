<%@page import="java.util.Date"%>
<%@page import="main.pojo.ProjectInfo"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    ProjectInfo projectInfo=(ProjectInfo)request.getAttribute("projectInfo");
    String projectName=projectInfo.getProjectName();
    String code=projectInfo.getCode();
    String outline=projectInfo.getOutline();
    String creatorName=projectInfo.getCreatorName();
    Date createdTime=projectInfo.getCreatedTime();
    //获取项目状态
    int state=projectInfo.getState().intValue();
    String stateValue=null;	
	switch (state) {
		case 465:
	         stateValue="立项";
			break;
		case 466:
			stateValue="合同";
			break;
		case 467:
			stateValue="付费";
			break;
		case 468:
			stateValue="结案";
			break;
		case 469:
			stateValue="失败";
			break;
		}
    
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
		
%>
<!DOCTYPE html>
<html>
	

	<body>
		<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>项目信息页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css">
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css">
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript">
    function submitInfo(){
    	document.getElementById("myForm").submit();
    }
    function aa(){
    	wx.closeWindow();
		}
</script>

	</head>
	  
	<div class="page">
			<div class="page__bd">
				<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目名称
							</label>
							<em class="weui-form-preview__value">
							<%=(projectName==null||projectName.equals(""))?"":projectName%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目编号
							</label>
							<em class="weui-form-preview__value">
							<%=(code==null||code.equals(""))?"":code%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目概述 
							</label>
							<em class="weui-form-preview__value">
							<%=(outline==null||outline.equals(""))?"":outline%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目状态
							</label>
							<em class="weui-form-preview__value">
							<%=(stateValue==null||stateValue.equals(""))?"":stateValue%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目负责人
							</label>
							<em class="weui-form-preview__value">
							<%=(creatorName==null||creatorName.equals(""))?"":creatorName%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								创建时间
							</label>
							<em class="weui-form-preview__value">
							<%=(createdTime==null||createdTime.equals(""))?"":createdTime%></em>
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
