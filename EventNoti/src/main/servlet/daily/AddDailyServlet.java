package main.servlet.daily;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.BackVisit;
import main.pojo.Daily;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.PersonnelFiles;
import main.pojo.UsersInfo;
import main.service.BackVisitService;
import main.service.DailyService;
import main.service.DepartmentService;
import main.service.EventService;
import main.service.PersonnelFilesService;
import main.service.ProjectInfoPersonnelsService;
import main.service.UsersInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;
import souvc.util.SendUtil;


/**
 * ��ȡ��Ŀ��Ϣҳ��
 * 
 * @author subiao
 * @date   20170425
 */
@SuppressWarnings("deprecation")
public class AddDailyServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	 public DailyService dailyService;
	 public PersonnelFilesService personnelFilesService;
	 public DepartmentService departmentService;
	 public ProjectInfoPersonnelsService projectInfoPersonnelsService;
	 public BackVisitService backVisitService;
	 public EventService eventService;
	 public UsersInfoService usersInfoService;
	 public	Daily daily;
	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 
	
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			dailyService=(DailyService) context.getBean("dailyService");
			personnelFilesService=(PersonnelFilesService) context.getBean("personnelFilesService");
			departmentService=(DepartmentService) context.getBean("departmentService");
			projectInfoPersonnelsService=(ProjectInfoPersonnelsService)context
					.getBean("projectInfoPersonnelsService");
			backVisitService=(BackVisitService) context.getBean("backVisitService");
			eventService=(EventService) context.getBean("eventService");
			usersInfoService=(UsersInfoService) context.getBean("usersInfoService");
			
			
	    }  
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//��ȡ�û�ID userid
    	 UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
    	 Integer userid=Integer.parseInt(userInfo.getId());
    	//��ȡ�û�����
    	 String userName = userInfo.getName();
    	 //��ȡ�û�code
         String code=userInfo.getCode();   	 
    	//��ȡ�û������lf
    	 String LoginName = userInfo.getLoginName();
 		//ͨ�����±��ȡdeptId��dutyId
 		List<PersonnelFiles> list=personnelFilesService.getPersonnelFilesByName(userName);
 		PersonnelFiles personnelFiles=null;
 		for (int i = 0; i < list.size(); i++) {
			personnelFiles=list.get(0);
		}
    	Long deptId=personnelFiles.getDeptId();
    	Long dutyId=personnelFiles.getDutyId();
    	Long personnelId=personnelFiles.getId();
    	Long instId=personnelFiles.getInstId();
    	Long organization=personnelFiles.getOrganization();
    	
        String dutyName=SQLUtil.getDutyById(dutyId).getName();    	
      
        List<String> newList=null;
		 String deptName=null;
		 try {
			 newList= departmentService.getDeptnameById(deptId);
		      for(int i = 0; i < newList.size(); i++){
		    	  deptName=newList.get(0);
		     }
		} catch (Exception e) {
		     e.printStackTrace();
		}
		 
		 
        System.out.println("��ȡ�� userName ,LoginName,code,userid,dutyName,deptName,instId,�ֱ�Ϊ ��"+userName+" "+LoginName+" "+code+" "+userid+" "+dutyName+" "+deptName+" "+instId);	
    	request.setAttribute("userName", userName);
    	request.setAttribute("LoginName", LoginName);
    	request.setAttribute("code", code);
    	request.setAttribute("userid", userid);
    	request.setAttribute("dutyName",dutyName);
    	request.setAttribute("deptName",deptName);
    	request.setAttribute("dutyId", dutyId);
    	request.setAttribute("deptId", deptId);
    	request.setAttribute("instId", instId);
    	request.setAttribute("organization", organization);
    	request.setAttribute("personnelId", personnelId);
 		request.getRequestDispatcher("daily/addNewDaily.jsp").forward(request, response);
		
    }

	
    @SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	synchronized (this) {
	            
			try  
			{ 
			Daily daily = new Daily();
				
			//�����ID userid
			long userid=Long.parseLong(request.getParameter("userid")) ;
			Long rapporteurId=userid;
			//���±�ID PERSON_ID
			long personnelId=Long.parseLong(request.getParameter("personnelId")) ;
			//��¼�� û�д洢 ���д
			String userName =new String(request.getParameter("userName").getBytes("iso-8859-1"), "UTF-8");
			//���ּ�� lf
			String loginName=request.getParameter("loginName");
			//Ա��code
			String code=request.getParameter("code");
           //��ǰʱ��
			String currentDate=request.getParameter("currentDate");
		   //����
			String weekDate=new String(request.getParameter("weekDate").getBytes("iso-8859-1"), "UTF-8");		
			
			//�ݷù�������
			String backvisitContext =new String(request.getParameter("backvisitContext").getBytes("iso-8859-1"), "UTF-8");
            //������������
			String workContext =new String(request.getParameter("workContext").getBytes("iso-8859-1"), "UTF-8");
		
			  //����
			String questions=null;
			if (!request.getParameter("questions").equals("null")) {
				questions =new String(request.getParameter("questions").getBytes("iso-8859-1"), "UTF-8");
			}
			
			
			//����ID deptId
			long deptId=Long.parseLong(request.getParameter("deptId")) ;
			String deptName=new String(request.getParameter("deptName").getBytes("iso-8859-1"), "UTF-8");
			//ְλID dutyId
			long dutyId=Long.parseLong(request.getParameter("dutyId")) ;
			String dutyName=new String(request.getParameter("dutyName").getBytes("iso-8859-1"), "UTF-8");
            //���� instId			
			long instId=Long.parseLong(request.getParameter("instId")) ;
			//���� organization
			long organization=Long.parseLong(request.getParameter("organization")) ;
			
			//��ȡ ��ʼʱ�� ����ʱ��
			 String startHour=request.getParameter("startHour");
		     String startMinute=request.getParameter("startMinute");
		     String endHour=request.getParameter("endHour");
		     String endMinute=request.getParameter("endMinute");
		     Calendar c =Calendar.getInstance();
		     String d=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime())+ " ";
		     Date startTime=new Date();
		     Date endTime  =new Date();
		     try {
		    	  startTime = new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(d +startHour + ":" + startMinute);
			       
   		          endTime = new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(d + endHour + ":" + endMinute);
			} catch (Exception e) {
				System.out.println("��ȡ startTime��endTimeʧ�ܣ�");
				e.printStackTrace();
			}
		  
			//����ʱ��
		   Date	createdTime=new Date();
		   //����޸�ʱ��
		   Date	lastModifiedTime=new Date();
		   
		 //��ȡ�طü�¼������id
		   String backVisitIds=request.getParameter("backVisitIds");
		   if (!"".equals(backVisitIds)) {
			String idsTemp[]=backVisitIds.split("-");
			Long bvtIds[]=new Long[idsTemp.length];
            for (int i = 0; i < idsTemp.length; i++) {
				bvtIds[i]=Long.parseLong(idsTemp[i]);
			}		  
            for (int i = 0; i < bvtIds.length; i++) {
				BackVisit backVisit=backVisitService.getBackVisitById(bvtIds[i]) ;
				
				daily.getBvtList().add(backVisit);
            }		  
		} 

		    daily.setRapporteurId(rapporteurId);
			daily.setPersonId(personnelId);
			daily.setCurrentDate(format1.parse(currentDate));
			daily.setWeekDate(weekDate);
			daily.setDeptId(deptId);
			daily.setDutyId(dutyId);
			daily.setBackvisitContext(backvisitContext);
			daily.setWorkContext(workContext);
			daily.setQuestions(questions);
			daily.setCreator(loginName);
			daily.setLastOperator(loginName);
			daily.setCreatedTime(createdTime);
			daily.setLastModifiedTime(lastModifiedTime);
			
			daily.setStartTime(startTime);
			daily.setEndTime(endTime);
			
			//��Ϊ���ֶ�
			daily.setInstId(instId);
			daily.setDisabled(0);
			daily.setOrganization(organization);
			
			dailyService.saveDaily(daily);
			System.out.println("�ձ�����ɹ�����¼��Ϊ��"+userName);
			//��ȡ���һ��ID
			Long id=  dailyService.getLastId();
			System.out.println("��ǰ���ݴ洢��idΪ: "+id);
			
		
	        //�����¼���
			//��ȡ���͵ļ���   dutyName�����׽��� ֱ�Ӵ���ȥ
			String moreinfo="{\"dailyId\":\""+daily.getId()+"\",\"code\":\""+code+"\",\"dutyName\":\""+dutyName+"\",\"deptName\":\""+deptName+"\",\"userName\":\""+userName+"\",\"users\":\"";
			
		   //����ȫԱ  ��ʱ
	/*		List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
		    for (int i = 0; i < UserInfoList.size(); i++) {
			     UserInfo User = SQLUtil.getAllUserId();
				 moreinfo+=User.getId().toString()+",";
				 }
			 moreinfo = moreinfo.substring(0,moreinfo.length()-1); */
			 
			String ids =userid+"";
			//���͸��Լ����ϼ��쵼 ��ȡid  ѭ���ж�
		    String code1=code;
		   loop:for ( ;code1!=null; ) {
		  
		   List<PersonnelFiles> pList=personnelFilesService.getPersonnelFilesByCode(code1);				
		   PersonnelFiles pFiles=null;
           for (int j = 0; j < pList.size(); ) {
			 pFiles=pList.get(0).getSuperiorLeader();
			 if (pFiles!=null) {
				 code1=pFiles.getCode(); //ͨ�����±� ��ȡ�쵼ʵ�� �õ�code
				 //����code ��ȡusers��Ӧʵ�� ��ȡid
				 List<UsersInfo> usersList=(List<UsersInfo>) usersInfoService.getUsersInfoByCode(code1);
			     for (int i = 0; i < usersList.size(); i++) {
					UsersInfo users=usersList.get(i);
					ids+=","+users.getId().toString();
				}
				 
			 continue loop; //���pFiles!=null ����loop�����ж�
			 }else {
				break loop;
			}
         } 
	  }
		//    ids=new String(ids.getBytes("UTF-8"),"8859_1");
		   moreinfo+=ids+"\"}";
			 
		   System.out.println(moreinfo);
			 
			EventNew event=new EventNew();
		    EventType eventType = new EventType();
		    eventType = eventService.getEventTypeByCode("10002"); //�ձ�ʱ��	
			event.setEventType(eventType);
			event.setName("�ձ��ύ");
			event.setMoreinfo(moreinfo);
			event.setEffectflag("E");
			event.setUserId(Long.toString(rapporteurId)); //rapporteurId ����userid
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
    
