package main.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.Area;
import main.pojo.CustomerInfo;
import main.service.AreaService;
import main.service.CustomerInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SearchProviceServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public  AreaService areaService;
	 public void init() throws ServletException {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			areaService=(AreaService) context.getBean("areaService");
	 }
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	     }
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	     	 Long PareaId = Long.parseLong(request.getParameter("areaId")) ;
		  
			//获取省份
			 List<Area> areaList = new ArrayList<Area>();
			 areaList=areaService.getAreaByAreaid(PareaId);
			 
			//request.setAttribute("ProjectInfoList", ProjectInfoList);
			 if (areaList != null) {  
		            JSONArray jsonArray = new JSONArray();//new一个json数组  
		            for (Area area : areaList) {  
		                JSONObject obj = new JSONObject();  
		                obj.put("id", area.getId());  
		                obj.put("name", area.getName());  
		                jsonArray.add(obj);//循环new jsonObject 并把省份信息 put进去 再add到josnArray里去  
		             
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
