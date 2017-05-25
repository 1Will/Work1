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
 * �ط�����ҳ��
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
			
		//�ͻ�
		long backvisittype=Long.parseLong(request.getParameter("backvisittype")) ;
		long visitType=Long.parseLong(request.getParameter("visitType")) ;
		
		//���� �ط����Ͷ�ѡ
		String employees=new String(request.getParameter("employees2").getBytes("iso-8859-1"), "UTF-8");
		//��ʱ(��)
		String expendTime=request.getParameter("expendTime").toString();
		
		String customerid=request.getParameter("customerNameid").toString();

		CustomerInfo customerInfo=customerInfoService.getById(Long.parseLong(customerid));
		
        //�ͻ�����
		String  customer=customerInfo.getCustomerName();
		
		//��Ŀid ���ж��Ƿ�Ϊ��
		String projectinfoid=request.getParameter("projectName");
		String  project="";
		if(projectinfoid!= null && projectinfoid.length()>0)
		{ 
				
		  ProjectInfo projectInfo=projectInfoService.getById(Long.parseLong(projectinfoid));
		  project=projectInfo.getProjectName();
		  visit.setProjectinfoid(Long.parseLong(projectinfoid));
		  visit.setProjectName(project);
		}
	
		//��ϵ��
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
		//�ط�����
		String visitContent =new String(request.getParameter("visitContent").getBytes("iso-8859-1"), "UTF-8");
		
		//����
		String fankui =new String(request.getParameter("feedback").getBytes("iso-8859-1"), "UTF-8");
		

		//����ע��
		String attention =new String(request.getParameter("attention").getBytes("iso-8859-1"), "UTF-8"); 
		
		//��ע

		String remarks =new String(request.getParameter("remarks").getBytes("iso-8859-1"), "UTF-8"); 
		
		long userid = Long.parseLong(request.getParameter("userid"));
		String code = request.getParameter("code");
        //����code��ȡ���±��Ӧvisiterid
		Long visiterid=Long.parseLong("0");
		List<PersonnelFiles> pList=personnelFilesService.getPersonnelFilesByCode(code);
		for (int i = 0; i < pList.size(); i++) {
			PersonnelFiles personnelFiles=pList.get(0);
			visiterid=personnelFiles.getId();
		}
		
		//��ȡ�طô���
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
		visit.setLastModifiedTime(new Date()); //����޶�ʱ��
		visit.setLastOperator(LoginName); //����޶���
		visit.setCustomerSteping(Long.parseLong("339"));//�ͻ��ȼ�  339 һ��  340  342
	    visit.setVisitDate(date);
	    visit.setNextVisitDate(nextVisitDate2);//�´λط�����
	    visit.setIsSaved("0");//�Ƿ�ʧЧ
	    visit.setExpendTime(Long.parseLong(expendTime)); //��ʱ(��)
	    visit.setEmployees(employees);//ҵ��Աͬ����
	    visit.setVersion(Long.parseLong("0"));
	    visit.setDisabled(0);
	    visit.setCreator(LoginName);
	    visit.setContactArchives(contact);
	    visit.setIsPublic("0");
	    visit.setCustomerStating(Long.parseLong("459"));
	    //visit.setUnconnect(Long.parseLong("0"));
	    visit.setContinuebackvisit("0");
		backVisitService.saveBackVisit(visit);
		System.out.println("����BackVisit: id= "+visit.getId());
	
        //�����¼���
		//��ȡ���͵ļ���
		String moreinfo="{\"backVisitId\":\""+visit.getId()+"\",\"users\":\"";
		//�ж�projectinfoid�Ƿ�Ϊ��
		if( projectinfoid!=null)
		 {//������Ŀ���Ա
		 List<ProjectInfoPersonnels> ProjectInfoPersonnelsList = new ArrayList<ProjectInfoPersonnels>();
		 ProjectInfoPersonnelsList= projectInfoPersonnelsService.getProjectInfoPersonnelsById(Long.parseLong(projectinfoid));
		 for (int i = 0; i < ProjectInfoPersonnelsList.size(); i++) {
			 ProjectInfoPersonnels Personnels = ProjectInfoPersonnelsList.get(i);
			 moreinfo+=Personnels.getProPerson_id().toString()+",";
			
		 }
		 moreinfo = moreinfo.substring(0,moreinfo.length()-1);
		 }
		 else
		 {//����ȫԱ
			 
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
		event.setName("�ط��ύ");
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
