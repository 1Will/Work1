package main.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.BackVisitService;
import main.service.CustomerInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 回访评论页面
 * 
 * @author dupeng
 * @date 20170224
 */
@SuppressWarnings("deprecation")
public class AddBackVisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  BackVisitService backVisitService;
	public  CustomerInfoService customerInfoService;


	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		
			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
			
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取客户列表
		/*List<CustomerInfo> CustomerInfoList = new ArrayList<CustomerInfo>();
		CustomerInfoList=customerInfoService.getAllCustomerInfo();
		request.setAttribute("CustomerInfoList", CustomerInfoList);
		*/
		request.getRequestDispatcher("backVisit/addbackVisit.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
