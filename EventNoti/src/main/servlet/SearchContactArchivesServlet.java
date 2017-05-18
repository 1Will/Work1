package main.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ContactArchives;
import main.service.ContactArchivesService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SearchContactArchivesServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public  ContactArchivesService contactArchivesService;
	 public void init() throws ServletException {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		 contactArchivesService=(ContactArchivesService) context.getBean("contactArchivesService");
		
	 }
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		}
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
			Long projectinfoid=Long.parseLong(request.getParameter("projectinfoid")); 
		
			//获取联系人列表
			 List<ContactArchives> ContactArchivesList = new ArrayList<ContactArchives>();
			 ContactArchivesList=contactArchivesService.getAllContactArchives(projectinfoid);
			
			//request.setAttribute("ContactArchivesList", ContactArchivesList);
			 if (ContactArchivesList != null) {  
		            JSONArray jsonArray = new JSONArray();//new一个json数组  
		            for (ContactArchives Contact : ContactArchivesList) {  
		                JSONObject obj = new JSONObject();  
		                obj.put("id", Contact.getId());  
		                obj.put("name", Contact.getContactName());  
		                jsonArray.add(obj);//循环new jsonObject 并把联系人信息 put进去 再add到josnArray里去  
		                //System.out.println("Contact.getContactName()");
		             }  
		            response.setCharacterEncoding("UTF-8"); 
		            PrintWriter out = response.getWriter();
		   		
		            out.write(jsonArray.toString());//输出写到页面 即下面的responseText里面  
		        } else {  
		            
		        }  
		      
			 /*String name=request.getParameter("name");
			 response.getWriter().print(name);*/
		 
	 }

}
