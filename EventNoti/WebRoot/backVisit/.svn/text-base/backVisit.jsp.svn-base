<%@page import="main.pojo.ReplyBackVisit"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="main.pojo.ReplyBackVisit"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
 	String customName = (String) request.getAttribute("customName");
 	String customerId = (String) request.getAttribute("customerId");  //接收客户id
	String projectName = (String) request.getAttribute("projectName");
	String projectInfoId = (String) request.getAttribute("projectInfoId");   //接收项目id
	String contactName = (String) request.getAttribute("contactName");
	String contactId = (String) request.getAttribute("contactId");   //接收联系人id
	String visitor = (String) request.getAttribute("visitor");
	String visitContent = (String) request.getAttribute("visitContent");
	String laterAtten = (String) request.getAttribute("laterAtten");
	String visitDate = (String) request.getAttribute("visitDate");

	String remark = (String) request.getAttribute("remark");
	String feed = (String) request.getAttribute("feed");
	String qid = (String) request.getAttribute("qid");
	String userid = (String) request.getAttribute("userid");
	String sender = (String) request.getAttribute("sender"); 

	String employees = (String) request.getAttribute("employees"); // 回访人同行者  
	String nextVisitDate = (String) request.getAttribute("nextVisitDate"); // 下次回访时间
	String expendTime = (String) request.getAttribute("expendTime"); // 耗时(分)
    double expendTime1=Double.parseDouble(expendTime);
    int expendTime2=(int)expendTime1; //转化为int

 //   String expendTime2=expendTime.substring(0,expendTime.length()-2);
	
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
		<title>回访页面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
		<link rel="stylesheet" href="<%=basePath%>/css/weui.css" />
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
							<label class="weui-form-preview__label">
								回访人
							</label>
							<em class="weui-form-preview__value"><%=visitor%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								回访人同行者
							</label>
							<em class="weui-form-preview__value">
							<%=(employees==null||employees.equals(""))?"":employees%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								回访时间
							</label>
							<em class="weui-form-preview__value"><%=visitDate%></em>
						</div>
					</div>       
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								下次回访时间
							</label>
							<em class="weui-form-preview__value">
							<%=(nextVisitDate==null||nextVisitDate.equals(""))?"":nextVisitDate%></em>
						</div>
					</div>       
					
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								回访耗时(分)
							</label>
							<em class="weui-form-preview__value">
 						 <%--    	<%=(expendTime2==null||expendTime2.equals(""))?"":expendTime2%>   --%>
                                                    <%=expendTime2%> </em>
						</div>
					</div>       
					
					<!-- 客户名称页面拓展 -->
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								 客户名称
							</label>
							<em class="weui-form-preview__value">
							<a  href="/EventNoti/customerInfoServlet?customerId=<%=customerId%>" ><%=customName%></a>
							     </em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								项目名称
							</label>
							<em class="weui-form-preview__value">
							   <a  href="/EventNoti/projectInfoServlet?projectInfoId=<%=projectInfoId%>" >
							     <%=(projectName==null||projectName.equals(""))?"":projectName%>
							   </a>
							 </em>
						</div>
					</div>   <!-- 联系人页面拓展 -->
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								 联系人
							</label>
							<em class="weui-form-preview__value">
							<a href="/EventNoti/contactArchivesServlet?contactId=<%=contactId%>" ><%=contactName%></a>
							</em>
						</div>
					</div>


					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								回访内容
							</label>
							<span class="weui-form-preview__value">
							<%=(visitContent==null||visitContent.equals(""))?"":visitContent%></span>
						</div>
					</div>
					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								客户反馈
							</label>
							<span class="weui-form-preview__value">
							<%=(feed==null||feed.equals(""))?"":feed%></span>
						</div>
					</div>

					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								后期注意
							</label>
							<span class="weui-form-preview__value">
							<%=(laterAtten==null||laterAtten.equals(""))?"":laterAtten%></span>
						</div>
					</div>

					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								备注
							</label>
							<span class="weui-form-preview__value">
							<%=(remark==null||remark.equals(""))?"":remark%></span>
						</div>
					</div>
					<!-- <div class="weui-form-preview__ft">
                <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:" onclick="aa();">返回</a>
            </div> -->
				</div>
				<form name="myForm" id="myForm" method="post"
					action="/EventNoti/replyBackVisitServlet">
					<input type="hidden" name="qid" id="qid" value="<%=qid%>" />
					<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
					<input type="hidden" name="sender" id="sender" value="<%=sender%>" />
					<input type="hidden" name="customName" id="customName"
						value="<%=customName%>" />
					<input type="hidden" name="projectName" id="projectName"
						value="<%=projectName%>" />
					<div class="weui_cells_title" style="text-align: left;">
						回复意见
					</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea id="fankui" name="fankui" class="weui_textarea"
									placeholder="请输入回复意见" onfocus="this.placeholder=' '" rows="3"></textarea>
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
					List<ReplyBackVisit> ReplyList = (List<ReplyBackVisit>) request
							.getAttribute("ReplyList");
					for (int i = 0; i < ReplyList.size(); i++) {
						ReplyBackVisit user = ReplyList.get(i);
						 if(user!=null)
                           {   SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                     String dateString =formatter.format(user.getRaplydate());
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
