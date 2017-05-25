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
import main.pojo.PersonnelFiles;
import main.pojo.ProjectInfo;
import main.pojo.ProjectInfoPersonnels;
import main.pojo.UsersInfo;
import main.service.BackVisitService;
import main.service.ContactArchivesService;
import main.service.CustomerInfoService;
import main.service.EventService;
import main.service.PersonnelFilesService;
import main.service.ProjectInfoPersonnelsService;
import main.service.ProjectInfoService;
import main.service.UsersInfoService;

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
	public  PersonnelFilesService personnelFilesService;
	public  UsersInfoService usersInfoService;
	public static EventService eventService;

	 public void init() throws ServletException {  
	
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		
			backVisitService=(BackVisitService) context.getBean("backVisitService");

			projectInfoService=(ProjectInfoService) context.getBean("projectInfoService");
		
			contactArchivesService=(ContactArchivesService) context.getBean("contactArchivesService");
		
			projectInfoPersonnelsService=(ProjectInfoPersonnelsService) context.getBean("projectInfoPersonnelsService");

			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
			
			personnelFilesService=(PersonnelFilesService) context.getBean("personnelFilesService");
			
			usersInfoService=(UsersInfoService) context.getBean("usersInfoService");
			
			eventService=(EventService) context.getBean("eventService");
	
			
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PersonnelFiles> personnelFilesList=personnelFilesService.getAllPersonnelFiles();
		request.setAttribute("personnelFilesList", personnelFilesList);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try  
		{ 
			BackVisit visit = new BackVisit();
			
		//客户
		long backvisittype=Long.parseLong(request.getParameter("backvisittype")) ;
		long visitType=Long.parseLong(request.getParameter("visitType")) ;
		
		//测试 回访类型多选
		String employees=new String(request.getParameter("employees2").getBytes("iso-8859-1"), "UTF-8");
		//耗时(分)
		String expendTime=request.getParameter("expendTime").toString();
		
		String customerid=request.getParameter("customerNameid").toString();

		CustomerInfo customerInfo=customerInfoService.getById(Long.parseLong(customerid));
		
        //客户名称
		String  customer=customerInfo.getCustomerName();
		
		//项目id 并判断是否为空
		String projectinfoid=request.getParameter("projectName");
		String  project="";
		if(projectinfoid!= null && projectinfoid.length()>0)
		{ 
				
		  ProjectInfo projectInfo=projectInfoService.getById(Long.parseLong(projectinfoid));
		  project=projectInfo.getProjectName();
		  visit.setProjectinfoid(Long.parseLong(projectinfoid));
		  visit.setProjectName(project);
		}
	
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
		 String nextVisitDate =request.getParameter("nextVisitDate");
		 Date nextVisitDate2 = VisitDate.parse(nextVisitDate);
		 
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
		String code = request.getParameter("code");
        //根据code获取人事表对应visiterid
		Long visiterid=Long.parseLong("0");
		List<PersonnelFiles> pList=personnelFilesService.getPersonnelFilesByCode(code);
		for (int i = 0; i < pList.size(); i++) {
			PersonnelFiles personnelFiles=pList.get(0);
			visiterid=personnelFiles.getId();
		}
		
		//获取回访次数
		List<BackVisit> backVisitList = backVisitService.getBackVisitByCustomerName(customer);
		Long sum = Long.valueOf(backVisitList.size() + 1);
		customerInfo.setBackVisitSum(sum);
		customerInfoService.saveCustomerInfo(customerInfo);
		
			
		String username = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getName();
		String LoginName = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getLoginName();
		
		visit.setBackvisittype(backvisittype);
		visit.setVisittype(visitType);
		visit.setCustomerName(customer);
		visit.setCustomerid(Long.parseLong(customerid));
		visit.setContactid(Long.parseLong(contactname));
		visit.setContactname(contact);
		visit.setFeedback(fankui);
		visit.setVisitContent(visitContent);
		visit.setAttention(attention);
		visit.setRemarks(remarks);
		visit.setVisitor(username);
		visit.setVisiterid(visiterid);
		visit.setCreatetime(new Date());
		visit.setLastModifiedTime(new Date()); //最后修订时间
		visit.setLastOperator(LoginName); //最后修订人
		visit.setCustomerSteping(Long.parseLong("339"));//客户等级  339 一星  340  342
	    visit.setVisitDate(date);
	    visit.setNextVisitDate(nextVisitDate2);//下次回访日期
	    visit.setIsSaved("0");//是否失效
	    visit.setExpendTime(Long.parseLong(expendTime)); //耗时(分)
	    visit.setEmployees(employees);//业务员同行者
	    visit.setVersion(Long.parseLong("0"));
	    visit.setDisabled(0);
	    visit.setCreator(LoginName);
	    visit.setContactArchives(contact);
	    visit.setIsPublic("0");
	    visit.setCustomerStating(Long.parseLong("459"));
	    //visit.setUnconnect(Long.parseLong("0"));
	    visit.setContinuebackvisit("0");
		backVisitService.saveBackVisit(visit);
		System.out.println("新增BackVisit: id= "+visit.getId());
	
        //插入事件表
		//获取发送的集合
		String moreinfo="{\"backVisitId\":\""+visit.getId()+"\",\"users\":\"";
		//判断projectinfoid是否为空
		if( projectinfoid!=null)
		 {//发送项目组成员
		 List<ProjectInfoPersonnels> ProjectInfoPersonnelsList = new ArrayList<ProjectInfoPersonnels>();
		 ProjectInfoPersonnelsList= projectInfoPersonnelsService.getProjectInfoPersonnelsById(Long.parseLong(projectinfoid));
		 for (int i = 0; i < ProjectInfoPersonnelsList.size(); i++) {
			 ProjectInfoPersonnels Personnels = ProjectInfoPersonnelsList.get(i);
			 moreinfo+=Personnels.getProPerson_id().toString()+",";
			
		 }
		 moreinfo = moreinfo.substring(0,moreinfo.length()-1);
		 }
		 else
		 {//发送全员
			 
			 List<UsersInfo> UserInfoList = usersInfoService.getAllUsersInfoByOpenId();
			 for (int i = 0; i < UserInfoList.size(); i++) {
				 UsersInfo User = UserInfoList.get(i);
				 moreinfo+=User.getId().toString()+",";
				
			 }
			 moreinfo = moreinfo.substring(0,moreinfo.length()-1);
		 }
				
		 System.out.println();
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
			e.printStackTrace();
		    System.out.println(e.getMessage());  
		}  
	}
}
