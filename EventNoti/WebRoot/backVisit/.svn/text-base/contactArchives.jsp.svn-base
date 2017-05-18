<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="main.pojo.ContactArchives"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    ContactArchives contactArchives=(ContactArchives)request.getAttribute("contactArchives");
    String phone=contactArchives.getPhone();
    String mobilePhone=contactArchives.getMobilePhone();
    String contactName=contactArchives.getContactName();
    Integer sex=contactArchives.getSex();
    String honorific=contactArchives.getHonorific();
    String customerName=contactArchives.getCustomerName();
    String industry=contactArchives.getIndustry();
    String dept=contactArchives.getDept();
    String duty=contactArchives.getDuty();
    Date birthday=contactArchives.getBirthday();
    String address=contactArchives.getAddress();
    String remark=contactArchives.getRemark();
        
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	System.out.println(contactArchives.getAddress());
	//测试能否获取该属性
	
%>
<!DOCTYPE html>
<html>
	

	<body>
		<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>联系人信息页面</title>
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
								姓名
							</label>
							<em class="weui-form-preview__value">
							<%=(contactName==null||contactName.equals(""))?"":contactName%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								性别
							</label>
							<em class="weui-form-preview__value">
							<%=(sex==0)?"男":"女"%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								称呼
							</label>
							<em class="weui-form-preview__value">
							<%=(honorific==null||honorific.equals(""))?"":honorific%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								所属公司
							</label>
							<em class="weui-form-preview__value">
							<%=(customerName==null||customerName.equals(""))?"":customerName%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								行业
							</label>
							<em class="weui-form-preview__value">
							<%=(industry==null||industry.equals(""))?"":industry%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								部门
							</label>
							<em class="weui-form-preview__value">
							<%=(dept==null||dept.equals(""))?"":dept%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								职位
							</label>
							<em class="weui-form-preview__value">
							<%=(duty==null||duty.equals(""))?"":duty%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								联系电话
							</label>
							<em class="weui-form-preview__value">
							<%=(mobilePhone==null||mobilePhone.equals(""))?"":mobilePhone%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								手机号码
							</label>
							<em class="weui-form-preview__value">
							<%=(phone==null||phone.equals(""))?"":phone%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								生日
							</label>
							<em class="weui-form-preview__value">
							<%=(birthday==null||birthday.equals(""))?"":birthday%></em>
						</div>
					</div>
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								地址
							</label>
							<em class="weui-form-preview__value">
							<%=(duty==null||duty.equals(""))?"":duty%></em>
						</div>
					</div>


					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">
								评价意见
							</label>
							<span class="weui-form-preview__value">
							<%=(remark==null||remark.equals(""))?"":remark%></span>
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
