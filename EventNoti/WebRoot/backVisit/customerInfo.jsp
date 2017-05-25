<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="main.pojo.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%

    CustomerInfo customerInfo=(CustomerInfo)request.getAttribute("customerInfo");
    String mobile=customerInfo.getMobilePhone(); //接收手机号
    String phone=customerInfo.getPhone();//接收 座机号码
    String customerName=customerInfo.getCustomerName();
    String keyContacter=customerInfo.getKeyContacter();
    String address=customerInfo.getAddress();
    String businessScope=customerInfo.getBusinessScope();
    
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	System.out.println(customerInfo.getAddress());
%>
<!DOCTYPE html>
<html>
	

	<body>
		<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>客户信息页面</title>
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
	  <%
	/*  List<CustomerInfo> CustomerInfoList = (List<CustomerInfo>) request.getAttribute("customerInfoList");
					for (int i = 0; i < CustomerInfoList.size(); i++) {
						CustomerInfo customerInfo = CustomerInfoList.get(i);  */
	   %>
	
	<div class="page">
			<div class="page__bd">
				<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								客户名称
							</label>
							<em class="weui-form-preview__value">
							<%=(customerName==null||customerName.equals(""))?"":customerName%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								主要联系人
							</label>
							<em class="weui-form-preview__value">
							<%=(keyContacter==null||keyContacter.equals(""))?"":keyContacter%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								联系电话
							</label>
							<em class="weui-form-preview__value">
							<%=(phone==null||phone.equals(""))?"":phone%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								手机号码
							</label>
							<em class="weui-form-preview__value">
							<%=(mobile==null||mobile.equals(""))?"":mobile%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								地址
							</label>
							<em class="weui-form-preview__value">
							<%=(address==null||address.equals(""))?"":address%></em>
						</div>
					</div>


					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								业务范围
							</label>
							<span class="weui-form-preview__value">
							<%=(businessScope==null||businessScope.equals(""))?"":businessScope%></span>
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
