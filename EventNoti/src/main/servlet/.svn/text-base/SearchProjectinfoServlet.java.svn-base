package main.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ProjectInfo;
import main.service.ProjectInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SearchProjectinfoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public  ProjectInfoService projectInfoService;
	 public void init() throws ServletException {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			projectInfoService=(ProjectInfoService) context.getBean("projectInfoService");
		;
	 }
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		}
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
			Long customerid=Long.parseLong(request.getParameter("customerid")); 
		  
			//获取联系人列表
			 List<ProjectInfo> ProjectInfoList = new ArrayList<ProjectInfo>();
			 ProjectInfoList=projectInfoService.getAllProjectInfo(customerid);
			 
			//request.setAttribute("ProjectInfoList", ProjectInfoList);
			 if (ProjectInfoList != null) {  
		            JSONArray jsonArray = new JSONArray();//new一个json数组  
		            for (ProjectInfo Project : ProjectInfoList) {  
		                JSONObject obj = new JSONObject();  
		                obj.put("id", Project.getId());  
		                obj.put("name", Project.getProjectName());  
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
