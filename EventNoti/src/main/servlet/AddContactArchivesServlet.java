package main.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ContactArchives;
import main.pojo.CustomerInfo;
import main.service.ContactArchivesService;
import main.service.CustomerInfoService;
import main.service.EventService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �½���ϵ��ҳ��
 * 
 * @author subiao
 * @date 20170413
 */
@SuppressWarnings("deprecation")
public class AddContactArchivesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ContactArchivesService contactArchivesService;
	public CustomerInfoService customerInfoService;
	public static EventService eventService;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		contactArchivesService = (ContactArchivesService) context
				.getBean("contactArchivesService");
		customerInfoService =(CustomerInfoService) context
				.getBean("customerInfoService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ȳ�ʼ�� ��ȡ�ͻ���Ϣ�б�
		List<CustomerInfo> customerInfoList = new ArrayList<CustomerInfo>();
		customerInfoList = customerInfoService.getAllCustomerInfo();
		request.setAttribute("customerInfoList", customerInfoList);
		request.getRequestDispatcher("backVisit/addContactArchives.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contactName=null;
		if (!request.getParameter("contactName").equals("null")) {
			contactName = new String(request.getParameter("contactName").getBytes("iso-8859-1"), "UTF-8");// ��ϵ������
		}
		String duty= new String(request.getParameter("duty").getBytes("iso-8859-1"), "UTF-8");
		String dept= new String(request.getParameter("dept").getBytes("iso-8859-1"), "UTF-8");
		String enterpriseSynopsis= new String(request.getParameter("enterpriseSynopsis").getBytes("iso-8859-1"), "UTF-8");
		String phone= request.getParameter("phone");
		String mobilePhone= request.getParameter("mobilePhone");
		String address= new String(request.getParameter("address").getBytes("iso-8859-1"), "UTF-8");
		String email= request.getParameter("email");
		String qq= request.getParameter("qqNUM");
		Integer sex = Integer.parseInt(request.getParameter("sex")); //��ȡsex����0 1
		String birthday = request.getParameter("birthday");
		Integer type=  Integer.parseInt(request.getParameter("type"));//��Ϥ�̶�
		 Date createdTime=new Date(); // ����ʱ��
		//��ȡ�ͻ�Id  �ͻ�����Ҳ��id��ʾ ��δ����
		long customerId=0; //�ͻ�ID
		if(!request.getParameter("customerName").equals("null"))
		  {
			customerId=Long.parseLong(request.getParameter("customerName")); 
		  }
		String  customerName=request.getParameter("customerN"); //������Ȼû�л�ȡ��ѡ�е��ı�ֵ��
		 
		try {
			ContactArchives contactArchives = new ContactArchives();
			contactArchives.setContactName(contactName);
			contactArchives.setCustomerId(customerId);
			contactArchives.setCustomerName(customerName);  
			contactArchives.setDuty(duty); //ְλ
			contactArchives.setDept(dept);
			contactArchives.setEnterpriseSynopsis(enterpriseSynopsis);
			contactArchives.setPhone(phone);
			contactArchives.setMobilePhone(mobilePhone);
			contactArchives.setAddress(address);
			contactArchives.setEmail(email);
			contactArchives.setQq(qq);
			contactArchives.setSex(sex);
			contactArchives.setType(Long.valueOf(type));
			contactArchives.setCreatedTime(createdTime);
			contactArchives.setBirthday(format1.parse(birthday));
			// �����ֶβ���Ϊ��
			contactArchives.setDisabled(Integer.parseInt("0"));
			contactArchives.setVersion(Long.parseLong("0"));

			contactArchivesService.saveContsctArchives(contactArchives);
			System.out.println("addContactArchives�洢��� ��ϵ����Ϊ:" + contactName);
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
