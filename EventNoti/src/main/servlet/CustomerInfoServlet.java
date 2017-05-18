package main.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.CustomerInfo;
import main.service.CustomerInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.disthubmq.impl.formats.Envelop.extenda.is;



/**
 * ��ȡ�ͻ���Ϣҳ��
 * 
 * @author subiao
 * @date   20170406
 */
@SuppressWarnings("deprecation")
public class CustomerInfoServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 public  CustomerInfoService customerInfoService;
	 public	 CustomerInfo customerInfo;
	 
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
	       
	    }  
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Long id = Long.parseLong(request.getParameter("customerId"));
    	 customerInfo=customerInfoService.getCustomerInfoById(id);
 		request.setAttribute("customerInfo", customerInfo);
 		request.getRequestDispatcher("backVisit/customerInfo.jsp").forward(request, response);
		}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
	
		/*Map<String,String> map = SQLUtil.getCustomerInfo(id);
		request.setAttribute("id", map.get("id"));
		request.setAttribute("customerName", map.get("customerName"));
		request.setAttribute("mobile", map.get("mobile"));
		request.setAttribute("phone", map.get("phone"));
		request.setAttribute("keyContacter", map.get("keyContacter"));
		request.setAttribute("address",map.get("address"));
		request.setAttribute("company_Introduction", map.get("company_Introduction"));*/
		
		//��ȡ�ظ��б�     ����ʹ��hibernate�Ŀ�ܷ��������õ���SQL���ķ���
		// List<CustomerInfo> customerInfoList = new ArrayList<CustomerInfo>();
		
		
		}
	}


	
}
