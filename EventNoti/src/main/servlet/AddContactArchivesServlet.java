package main.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.CodeValue;
import main.pojo.ContactArchives;
import main.pojo.CustomerInfo;
import main.service.CodeValueService;
import main.service.ContactArchivesService;
import main.service.CustomerInfoService;
import main.service.EventService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.util.SQLUtil;

/**
 * 新建联系人页面
 * 
 * @author subiao
 * @date 20170413
 */
@SuppressWarnings("deprecation")
public class AddContactArchivesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ContactArchivesService contactArchivesService;
	public CustomerInfoService customerInfoService;
	public CodeValueService codeValueService;
	public static EventService eventService;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		contactArchivesService = (ContactArchivesService) context
				.getBean("contactArchivesService");
		customerInfoService =(CustomerInfoService) context
				.getBean("customerInfoService");
		codeValueService =(CodeValueService)context
				.getBean("codeValueService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先初始化 获取客户信息列表
		/*List<CustomerInfo> customerInfoList = new ArrayList<CustomerInfo>();
		customerInfoList = customerInfoService.getAllCustomerInfo();
		request.setAttribute("customerInfoList", customerInfoList);*/
		
		request.getRequestDispatcher("backVisit/addContactArchives.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contactName=null;
		if (!request.getParameter("contactName").equals("null")) {
			contactName = new String(request.getParameter("contactName").getBytes("iso-8859-1"), "UTF-8");// 联系人姓名
		}
		String duty= new String(request.getParameter("duty").getBytes("iso-8859-1"), "UTF-8");
		String dept= new String(request.getParameter("dept").getBytes("iso-8859-1"), "UTF-8");
		String enterpriseSynopsis= new String(request.getParameter("enterpriseSynopsis").getBytes("iso-8859-1"), "UTF-8");
		String phone= request.getParameter("phone");
		String mobilePhone= request.getParameter("mobilePhone");
		String address= new String(request.getParameter("address").getBytes("iso-8859-1"), "UTF-8");
		String honorific= new String(request.getParameter("honorific").getBytes("iso-8859-1"), "UTF-8");
		String email= request.getParameter("email");
		String qq= request.getParameter("qq");
		Integer sex = Integer.parseInt(request.getParameter("sex")); //获取sex代码0 1
		String birthday=null; //判断birthday是否为空
		if (!request.getParameter("birthday").equals("null")) {
			birthday = request.getParameter("birthday");
		}
		Integer type=  Integer.parseInt(request.getParameter("type"));//熟悉程度
		 Date createdTime=new Date(); // 创建时间
		 Date lastModifiedTime=new Date(); // 最后修改时间
		//获取客户Id  客户名称也用id表示 暂未解析
		long customerId=0; //客户ID
		if(!request.getParameter("customerId").equals("null"))
		  {
			customerId=Long.parseLong(request.getParameter("customerId")); 
		  }
		//根据customerId 获取客户实体
		CustomerInfo customerInfo=customerInfoService.getCustomerInfoById(customerId);
		String customerInfoCode =customerInfo.getCode();
		Long customerTypeId=customerInfo.getCustomerType();
		Long industryId =customerInfo.getIndustry();
		//根据industry ID值 从CodeValue中转换对应名称
		CodeValue codeValue=codeValueService.getCodeValueById(industryId);
		String industry=codeValue.getName();
		
		
		//获取客户名称
		String customerName=null;
		if (!request.getParameter("customerName").equals("null")) {
		     customerName=new String(request.getParameter("customerName").getBytes("iso-8859-1"), "UTF-8"); 
		}
		 //接收userid
		Long userid=Long.parseLong(request.getParameter("userid"));
		//获取用户姓名
		String creatorName = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getName();
		String lastOperator=creatorName;
		System.out.println("查看获取的  userid ，username  分别为："+userid+creatorName);

		
		try {
			ContactArchives contactArchives = new ContactArchives();
			contactArchives.setContactName(contactName);
			contactArchives.setCustomerId(customerId);
			contactArchives.setCustomerName(customerName);  
			contactArchives.setDuty(duty); //职位
			contactArchives.setDept(dept);
			contactArchives.setEnterpriseSynopsis(enterpriseSynopsis);
			contactArchives.setPhone(phone);
			contactArchives.setMobilePhone(mobilePhone);
			contactArchives.setAddress(address);
			contactArchives.setHonorific(honorific);
			contactArchives.setEmail(email);
			contactArchives.setQq(qq);
			contactArchives.setSex(sex);
			contactArchives.setType(Long.valueOf(type));
			contactArchives.setCreatedTime(createdTime);
			contactArchives.setLastModifiedTime(lastModifiedTime);
			contactArchives.setBirthday(format1.parse(birthday));
			contactArchives.setCreatorName(creatorName);
			contactArchives.setLastOperator(lastOperator);
			contactArchives.setCustomerInfoCode(customerInfoCode);
			contactArchives.setCustomerTypeId(customerTypeId);
			contactArchives.setIndustry(industry);
			// 下面字段不能为空
			contactArchives.setDisabled(Integer.parseInt("0"));
			contactArchives.setVersion(Long.parseLong("0"));

			contactArchivesService.saveContsctArchives(contactArchives);
			System.out.println("addContactArchives存储完成 联系人名为:" + contactName);
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
