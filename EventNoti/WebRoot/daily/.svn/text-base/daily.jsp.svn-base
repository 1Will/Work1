
<%@page import="bsh.This"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="main.pojo.ReplyDaily"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
    
	String dailyId = (String)request.getAttribute("dailyId");
	String userid = (String)request.getAttribute("userid");
	String sender = (String)request.getAttribute("sender");
	String reporterName = (String)request.getAttribute("reporterName");
	Date currentDate1 = (Date)request.getAttribute("currentDate");
	String weekDate = (String)request.getAttribute("weekDate");
	String deptName = (String)request.getAttribute("deptName");
	String dutyName = (String)request.getAttribute("dutyName");
	String backvisitContext = (String)request.getAttribute("backvisitContext");
	String workContext = (String)request.getAttribute("workContext");
	String questions = (String)request.getAttribute("questions");

    DateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
    String currentDate=format1.format(currentDate1);
    
	 String backvisitContext2="&nbsp&nbsp&nbsp&nbsp";
 	if(!"".equals(backvisitContext)&& backvisitContext!=null)
	 {
	   String bvContext[]=backvisitContext.split(";");
	   for(int i=0;i<bvContext.length;i++){
	            if (i<bvContext.length-1) {
		    	   backvisitContext2+=bvContext[i]+"；<br>&nbsp&nbsp&nbsp&nbsp";
		    	}else {
		    		backvisitContext2+=bvContext[i];
				}
				
	   }
	}
	 
	/* 
	String workContext2="&nbsp&nbsp&nbsp&nbsp";
 	if(!"".equals(workContext)&& workContext!=null)
	 {
	   String wkContext[]=workContext.split("；");
	   for(int i=0;i<wkContext.length;i++){
	            if (i<wkContext.length-1) {
		    	   workContext2+=wkContext[i]+"；<br>&nbsp&nbsp&nbsp&nbsp";
		    	}else {
		    		workContext2+=wkContext[i]+"；";
				}
				
	   }
	}
	 
	 String questions2="&nbsp&nbsp&nbsp&nbsp";
 	if(!"".equals(questions)&& questions!=null)
	 {
	   String quest[]=workContext.split("；");
	   for(int i=0;i<quest.length;i++){
	            if (i<quest.length-1) {
		    	   questions2+=quest[i]+"；<br>&nbsp&nbsp&nbsp&nbsp";
		    	}else {
		    		questions2+=quest[i]+"；";
				}
				
	   }
	}
 */	 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<!DOCTYPE html>
<html>
<head>
<title>工作日报</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=basePath%>/css/weui.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/page.css"/>
<script src="<%=basePath%>/js/router.min.js"></script>
<script src="<%=basePath%>/js/example.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script type="text/javascript">
	
		 function submitInfo(){
		 
		 //判断回复内容是否为空
       if (document.getElementById("fankui").value.replace(
						/\s*/g, "") == "") {
					alert("请输入回复意见");
					return false;
				}
    
		 
		 
    	document.getElementById("myForm").submit();
    }
    </script>
</head>

<body>

<div class="page">
        <div class="page__bd">
			<div class="weui-form-preview">
				
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">报告人</label>
						<em class="weui-form-preview__value">
						<%=(reporterName==null || reporterName.equals(""))?"":reporterName %></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">日报日期</label>
						<em class="weui-form-preview__value">
						<%=(currentDate==null || currentDate.equals(""))?"":currentDate%></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">星期</label>
						<em class="weui-form-preview__value">
						<%=(weekDate==null || weekDate.equals(""))?"":weekDate%></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">部门名称</label>
						<em class="weui-form-preview__value">
						<%=(deptName==null || deptName.equals(""))?"":deptName%></em>
					</div>
				</div>
				<div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">职位名称</label>
                    <span class="weui-form-preview__value">
                      <%=(dutyName==null || dutyName.equals(""))?"":dutyName%></span>
                </div>
            </div>
				<div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">拜访工作内容</label>
                    <span class="weui-form-preview__value">
                      <%=(backvisitContext2==null || backvisitContext2.equals(""))?"":backvisitContext2%></span>
                </div>
            </div>
				<div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">工作内容</label>
                    <span class="weui-form-preview__value">
                      <%=(workContext==null || workContext.equals(""))?"":workContext%></span>
                </div>
            </div>
				<div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">收获/问题/建议</label>
                    <span class="weui-form-preview__value">
                      <%=(questions==null || questions.equals(""))?"":questions%></span>
                </div>
              </div>
		   </div>
         
         
               <form name="myForm" id="myForm" method="post"
					action="/EventNoti/replyDailyServlet">
					<input type="hidden" name="dailyId" id="dailyId" value="<%=dailyId%>" />
					<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
					<input type="hidden" name="sender" id="sender" value="<%=sender%>" />
					<input type="hidden" name="reporterName" id="reporterName" value="<%=reporterName%>" />
					<input type="hidden" name="deptName" id="deptName" value="<%=deptName%>" />
					<input type="hidden" name="dutyName" id="dutyName" value="<%=dutyName%>" />
					<div class="weui_cells_title" style="text-align: left;">
						回复意见
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="fankui" name="fankui" class="weui_textarea"
									placeholder="请输入回复意见" onfocus="this.placeholder=' '"  rows="3"></textarea>
							</div>
						</div>
					</div>

					<div class="weui_btn_area">
						<a class="weui_btn weui_btn_primary" href="javascript:"
							id="showTooltips" onclick="submitInfo();">回复</a>
					</div>

				</form>
				<div class="weui-form-preview__bd">
						最新回复
					</div>
				<article class="weui_article">
				<%
					List<ReplyDaily> replyDailyList = (List<ReplyDaily>) request
							.getAttribute("replyDailyList");
					for (int i = 0; i < replyDailyList.size(); i++) {
						ReplyDaily user = replyDailyList.get(i);
						 if(user!=null)
                           {   SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                     String dateString =formatter.format(user.getReplydate());
				%>

				<section>
				<h2 class="title">
					<%=user.getUsername()%>：<%=dateString%>
				</h2>
				<section>
				<p>
					<%=user.getContent()%>
				</p>
				</section>

				</section>
				<%
					}}
				%>
				</article>
			
	    </div>
     </div>

</body>
</html>
