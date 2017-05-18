package main.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.CustomerInfo;
import main.service.CustomerInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SearchCusomerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public  CustomerInfoService customerInfoService;
	 public void init() throws ServletException {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
	 }
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		}
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	     	 String name = request.getParameter("name");
		  
			//获取联系人列表
			 List<CustomerInfo> CustomerInfoList = new ArrayList<CustomerInfo>();
			 CustomerInfoList=customerInfoService.getCustomerInfoByName(name);
			 
			//request.setAttribute("ProjectInfoList", ProjectInfoList);
			 if (CustomerInfoList != null) {  
		            JSONArray jsonArray = new JSONArray();//new一个json数组  
		            for (CustomerInfo Project : CustomerInfoList) {  
		                JSONObject obj = new JSONObject();  
		                obj.put("id", Project.getId());  
		                obj.put("name", Project.getCustomerName());  
		                jsonArray.add(obj);//循环new jsonObject 并把联系人信息 put进去 再add到josnArray里去  
		             
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
