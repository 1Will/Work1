package main.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.BackVisit;
import main.pojo.ContactArchives;
import main.pojo.CustomerInfo;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.ProjectInfo;
import main.pojo.ProjectInfoPersonnels;
import main.service.BackVisitService;
import main.service.ContactArchivesService;
import main.service.CustomerInfoService;
import main.service.EventService;
import main.service.ProjectInfoPersonnelsService;
import main.service.ProjectInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;

/**
 * 回访评论页面
 * 
 * @author dupeng
 * @date 20170224
 */
@SuppressWarnings("deprecation")
public class AddNewBackVisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  BackVisitService backVisitService;
	
	public  ProjectInfoPersonnelsService projectInfoPersonnelsService;	
	public  CustomerInfoService customerInfoService;
	public  ProjectInfoService projectInfoService;
	public  ContactArchivesService contactArchivesService;
	public static EventService eventService;

	 public void init() throws ServletException {  
	
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		
			backVisitService=(BackVisitService) context.getBean("backVisitService");

			projectInfoService=(ProjectInfoService) context.getBean("projectInfoService");
		
			contactArchivesService=(ContactArchivesService) context.getBean("contactArchivesService");
		
			projectInfoPersonnelsService=(ProjectInfoPersonnelsService) context.getBean("projectInfoPersonnelsService");

			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
			eventService=(EventService) context.getBean("eventService");
	
			
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try  
		{ 
			
		//客户
		long backvisittype=Long.parseLong(request.getParameter("backvisittype")) ;
		long visitType=Long.parseLong(request.getParameter("visitType")) ;
		
		String customerid=request.getParameter("customerNameid").toString();

		CustomerInfo customerInfo=customerInfoService.getById(Long.parseLong(customerid));
		

		String  customer=customerInfo.getCustomerName();
		
		//项目
		String projectinfoid=request.getParameter("projectName");
		String  project="";
	
		if(projectinfoid!= null && projectinfoid.length()>0)
		{ 
				
		  ProjectInfo projectInfo=projectInfoService.getById(Long.parseLong(projectinfoid));
		  project=projectInfo.getProjectName();
			System.out.println("111111111111");
		}
	
		System.out.println("2222222222");
		//联系人
		String contactname=request.getParameter("contactname");
		String contact="";
		if(contactname!= null && contactname.length()>0)
		{
			ContactArchives contactArchives=contactArchivesService.getContactArchivesById(Long.parseLong(contactname));
			contact =contactArchives.getContactName();
			
		}
		System.out.println(contactname);
		System.out.println("3333333333");
		
		 SimpleDateFormat VisitDate = new SimpleDateFormat("yyyy-MM-dd");
		 String visitDate =request.getParameter("visitDate");
		 Date date = VisitDate.parse(visitDate);
		
		 System.out.println("444444444");
		//回访内容
		String visitContent =new String(request.getParameter("visitContent").getBytes("iso-8859-1"), "UTF-8");
		
		//反馈
		String fankui =new String(request.getParameter("feedback").getBytes("iso-8859-1"), "UTF-8");
		

		//后期注意
		String attention =new String(request.getParameter("attention").getBytes("iso-8859-1"), "UTF-8"); 
		
		//备注

		String remarks =new String(request.getParameter("remarks").getBytes("iso-8859-1"), "UTF-8"); 
		
		long userid = Long.parseLong(request.getParameter("userid"));
		
		//int sender = Integer.parseInt(request.getParameter("sender"));
			
		String username = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getName();
		String LoginName = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getLoginName();
		BackVisit visit = new BackVisit();
		visit.setBackvisittype(backvisittype);
		visit.setVisittype(visitType);
		visit.setCustomerName(customer);
		visit.setCustomerid(Long.parseLong(customerid));
		visit.setProjectName(project);
		visit.setProjectinfoid(Long.parseLong(projectinfoid));
		visit.setContactid(Long.parseLong(contactname));
		visit.setContactname(contact);
		visit.setFeedback(fankui);
		visit.setVisitContent(visitContent);
		visit.setAttention(attention);
		visit.setRemarks(remarks);
		visit.setVisiter(username);
		visit.setVisiterid(userid);
		visit.setCreatetime(new Date());
	    visit.setVisitDate(date);
	    visit.setVersion(Long.parseLong("0"));
	    visit.setDisabled(0);
	    visit.setCreator(LoginName);
	    visit.setContactArchives(contact);
	    visit.setIsPublic("0");
	    visit.setCustomerStating(Long.parseLong("459"));
	    visit.setUnconnect(Long.parseLong("0"));
	    visit.setContinuebackvisit("0");
		backVisitService.saveBackVisit(visit);
	
        //插入事件表
		//获取发送的集合
		String moreinfo="{\"backVisitId\":\""+visit.getId()+"\",\"users\":\"";
		 List<ProjectInfoPersonnels> ProjectInfoPersonnelsList = new ArrayList<ProjectInfoPersonnels>();
		 ProjectInfoPersonnelsList= projectInfoPersonnelsService.getProjectInfoPersonnelsById(Long.parseLong(projectinfoid));
		 if( ProjectInfoPersonnelsList!=null)
		 {//发送项目组成员
		 for (int i = 0; i < ProjectInfoPersonnelsList.size(); i++) {
			 ProjectInfoPersonnels Personnels = ProjectInfoPersonnelsList.get(i);
			 moreinfo+=Personnels.getProPerson_id().toString()+",";
			
		 }
		 moreinfo = moreinfo.substring(0,moreinfo.length()-1);
		 }
		 else
		 {//发送全员
			 List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
			 for (int i = 0; i < UserInfoList.size(); i++) {
				 UserInfo User = SQLUtil.getAllUserId();
				 moreinfo+=User.getId().toString()+",";
				
			 }
			 moreinfo = moreinfo.substring(0,moreinfo.length()-1);
		 }
				
	    moreinfo+="\"}";
	    System.out.println(moreinfo);
		EventNew event=new EventNew();
		 EventType eventType = new EventType();
		 eventType = eventService.getEventTypeByCode("10001");	
		event.setEventType(eventType);
		event.setName("回访提交");
		event.setMoreinfo(moreinfo);
		event.setEffectflag("E");
		event.setUserId(Long.toString(userid));
		eventService.saveEvent(event);
		request.getRequestDispatcher("qingjia/result.jsp").forward(request, response);
		}
		catch (Exception e)  
		{  
		    System.out.println(e.getMessage());  
		}  
	}
}
