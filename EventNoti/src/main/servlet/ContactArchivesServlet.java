package main.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ContactArchives;
import main.service.ContactArchivesService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * 读取联系人信息页面
 * 
 * @author subiao
 * @date   20170407
 */
@SuppressWarnings("deprecation")
public class ContactArchivesServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 public  ContactArchivesService contactArchivesService;
	 public	 ContactArchives contactArchives;
	 
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			contactArchivesService=(ContactArchivesService) context.getBean("contactArchivesService");
	       
	    } 
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// this.doPost(request,response);
    	 long id = Long.parseLong(request.getParameter("contactId"));

 		contactArchives=contactArchivesService.getContactArchivesById(id);
 		request.setAttribute("contactArchives", contactArchives);
 		request.getRequestDispatcher("backVisit/contactArchives.jsp").forward(request, response);
    	 
		}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
		
		}
	}


	
}
