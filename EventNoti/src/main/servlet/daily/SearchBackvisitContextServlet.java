package main.servlet.daily;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.BackVisit;
import main.pojo.ContactArchives;
import main.pojo.CustomerInfo;
import main.pojo.Daily;
import main.service.AreaService;
import main.service.BackVisitService;
import main.service.ContactArchivesService;
import main.service.CustomerInfoService;
import main.service.DailyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SearchBackvisitContextServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public  AreaService areaService;
    public  BackVisitService backVisitService;
    public  CustomerInfoService customerInfoService;
    public  ContactArchivesService contactArchivesService;
    public  DailyService dailyService;
    
	 public void init() throws ServletException {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			areaService=(AreaService) context.getBean("areaService");
			backVisitService=(BackVisitService) context.getBean("backVisitService");
			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
			contactArchivesService=(ContactArchivesService) context.getBean("contactArchivesService");
			dailyService=(DailyService) context.getBean("dailyService");
	 }
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	     }
	 @SuppressWarnings({ "unused", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	     	 String currentDate = request.getParameter("currentDate");
	     	 String userName = request.getParameter("userName");
	     	 //获取用户名简称
	     	 String loginName = request.getParameter("loginName");
	     	 //根据选择时间 和姓名简称  到日报表中查询该日报是否存在
	     	 List<Daily> dailyList=dailyService.getDailyByDate(currentDate, loginName);
	     	 System.out.println("dailyList.size(): "+dailyList.size());
	     	 
	     	 //json 处理准备
	     	 JSONArray jsonArray = new JSONArray();//new一个json数组  
 	     	 JSONObject obj = new JSONObject();
 	   	     List<Object> list =new ArrayList<Object>();
 	   	     
	     if (dailyList.size()>0) {
			 list.add("F");
		  } else {
		  
			  //获取 BackvisitList
	     	 List<BackVisit> backVisitList=backVisitService.getBackVisitByDate(currentDate, userName);
	     	 
	     	 List<CustomerInfo> customerInfoList=customerInfoService .getCustomerInfoByDate(currentDate, userName);
	     	 
	     	 List<ContactArchives> contactArchivesList=contactArchivesService.getContactArchivesByDate(currentDate, userName);
	     	 //测试获取到的一些集合
	     	 System.out.println("测试！！+backVisitList.size() : "+backVisitList.size());
	     	 System.out.println("测试！！+customerInfoList.size() : "+customerInfoList.size());
	     	 System.out.println("测试！！+contactArchivesList.size() : "+contactArchivesList.size());
	     	 
	     	 //传递  backVisitList 里的内容
	     	 List list2=new ArrayList();
 	     	for (BackVisit backVisit : backVisitList) {
				list2.add(backVisit.getId());
				list2.add(backVisit.getCustomerName());
				list2.add(backVisit.getContactname());
				list2.add(backVisit.getProjectName());
			    }
     	     	
 	         	//json本质解析对象 对象再获取属性  把三个返回值放入list数组里 传递到前端
     	     	 list.add(list2);  // backVisitList 里面含有daily属性 存在级联问题 所以解析为list2传到前台
     	     	 list.add(contactArchivesList.size());
     	     	 list.add(customerInfoList.size());
		  
		  }     	 
     	         	
     	     	 obj.put("list", list);
     	     	    jsonArray.add(obj);//循环new jsonObject 并把回访内容信息 put进去 再add到josnArray里去  	
		            response.setCharacterEncoding("UTF-8"); 
		            PrintWriter out = response.getWriter();
		   		
		            out.write(jsonArray.toString());//输出写到页面 即下面的responseText里面  
		            System.out.println("jsonArray: "+jsonArray);
		            out.flush();  
                    out.close();  

     	     	 
   }    
}
