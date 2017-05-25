<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="souvc.pojo.WeixinUserInfo"%>
<%@page import="souvc.pojo.UserInfo"%>
<%@page import="java.util.List"%>
<%@page import="main.pojo.ProjectInfo"%>
<%@page import="main.pojo.CustomerInfo"%>
<%@page import="main.pojo.ContactArchives"%>
<%
  String userid=request.getAttribute("userid").toString();
  String userName=(String)request.getAttribute("userName");
  String dutyName=(String)request.getAttribute("dutyName");
  String deptName=(String)request.getAttribute("deptName");
  String deptId=request.getAttribute("deptId").toString();
  String dutyId=request.getAttribute("dutyId").toString();
  String personnelId=request.getAttribute("personnelId").toString();
  String weeklyName=(String)request.getAttribute("weeklyName");
  
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
		<title>新增周计划页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.js"></script>
		
		
		
		<script type="text/javascript">
    function submitInfo(){
  
    	document.getElementById("myForm").submit();
        
        if (document.getElementById("startDate").value.replace(/\s*/g, "") == "") {
			alert("请选择开始日期");
			return false;
		}
        
        if (document.getElementById("endDate").value.replace(/\s*/g, "") == "") {
			alert("请选择结束日期");
			return false;
		}
        
        if(document.getElementById("summary").value().replace(/\s*/g, "") == ""){
           alert("请输入 当天周总结内容");
           return false;
        }
    
    }
   
</script>
		
	</head>

	<body>
	    <form name="myForm" id="myForm" method="post" action="/EventNoti/addWeeklyServlet">
			<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
			<input type="hidden" name="dutyId" id="dutyId" value="<%=dutyId%>" />
			<input type="hidden" name="deptId" id="deptId" value="<%=deptId%>" />
			<input type="hidden" name="userName" id="userName" value="<%=userName%>" />
			<input type="hidden" name="personnelId" id="personnelId" value="<%=personnelId%>" />
			<input type="hidden" name="weeklyName" id="weeklyName" value="<%=weeklyName%>" />

		<div class="page__bd">
				
				<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								报告人
							</label>
							<em class="weui-form-preview__value">
							<%=(userName==null||userName.equals(""))?"":userName%></em>
						</div>
					</div>
				</div>
				
				<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								周报名称
							</label>
							<em class="weui-form-preview__value">
							<%=(weeklyName==null||weeklyName.equals(""))?"":weeklyName%></em>
						</div>
					</div>
				
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_hd">
								<label for="" class="weui_label">
									起始日期
								</label>
							</div>
							<div class="weui_cell_bd weui_cell_primary">
								<input class="weui_input" type="date" id="startDate"
									name="startDate" />
							</div>
						</div>
					</div>
                
				
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_hd">
								<label for="" class="weui_label">
									结束日期
								</label>
							</div>
							<div class="weui_cell_bd weui_cell_primary">
								<input class="weui_input" type="date" id="endDate"
									name="endDate" />
							</div>
						</div>
					</div>
               
					<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								所属部门
							</label>
							<em class="weui-form-preview__value">
							<%=(deptName==null||deptName.equals(""))?"":deptName%></em>
						</div>
					</div>
					<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								职位
							</label>
							<em class="weui-form-preview__value">
							<%=(dutyName==null||dutyName.equals(""))?"":dutyName%></em>
						</div>
					</div>
			 	
					<div class="weui_cells_title  weui_cell_warn">
						本周工作总结
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="summary" name="summary"
									class="weui_textarea" rows="3"></textarea>
							</div>
						</div>
					</div> 
					
					<div class="weui_cells_title">
						备注
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="comment" name="comment" class="weui_textarea"
									placeholder="" rows="3"></textarea>
							</div>
						</div>
					</div>
				</div>
				
			   <div class="weui_cells_title">部门领导</div>
    	            <div class="weui_cells">
	                    <div class="weui_cell weui_cell_select">
	                         <div class="weui_cell_bd weui_cell_primary">
	                             <select class="weui_select" name="shenhe" id="shenhe">
	                              <%
							        List<WeixinUserInfo> list = (List<WeixinUserInfo>)request.getAttribute("userList");
							            for (int i = 0; i < list.size(); i++) {    
                                      WeixinUserInfo user=list.get(i);
                                    if(user.getNickname()!=null)
                                     {	
						           %>
						           <option value="<%=user.getOpenid() %>"><%=user.getNickname() %></option>
						           <% 
							        }}
						           %>
	                             </select>
	                        </div>
	                   </div>
                  </div>
               
				<div class="weui_btn_area">
					<a class="weui_btn weui_btn_primary" href="javascript:void(0)"
						id="showTooltips" onclick="submitInfo();">提交</a>
				</div>

                </div>
              </div>
           </div>               			
	  </form>
	  
	  
	</body>
</html>
