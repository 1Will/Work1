package main.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.PersonnelFiles;
import main.pojo.Weekly;
import main.service.DepartmentService;
import main.service.PersonnelFilesService;
import main.service.WeeklyService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;


/**
 * ��ȡ��Ŀ��Ϣҳ��
 * 
 * @author subiao
 * @date   20170428
 */
@SuppressWarnings("deprecation")
public class AddWeeklyServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 DateFormat format2 = new SimpleDateFormat("yyyy-MM");
	 public WeeklyService weeklyService;
	 public PersonnelFilesService personnelFilesService;
	 public DepartmentService departmentService;
	 public	Weekly weekly;
	 
	
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			weeklyService=(WeeklyService) context.getBean("weeklyService");
			personnelFilesService=(PersonnelFilesService) context.getBean("personnelFilesService");
			departmentService=(DepartmentService) context.getBean("departmentService");
			
	    }  
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//��ȡ�û�ID userid
    	 UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
    	 Integer userid=Integer.parseInt(userInfo.getId());
    	//��ȡ�û�����
 		String userName = SQLUtil.getUserDetail(userid).getName();
 		//ͨ�����±���ȡdeptId��dutyId
 		List<PersonnelFiles> list=personnelFilesService.getPersonnelFilesByName(userName);
 		PersonnelFiles personnelFiles=null;
 		for (int i = 0; i < list.size(); i++) {
			personnelFiles=list.get(0);
		}
    	Long deptId=personnelFiles.getDeptId();
    	Long dutyId=personnelFiles.getDutyId();
    	Long personnelId=personnelFiles.getId();
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
		 
		//��ȡ��ǰ�ܱ�����  ���µ�����
		Calendar c = Calendar.getInstance();
		String[] months = { "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "ʮһ", "ʮ��" };
		 
		String[] weeks = { "һ", "��", "��", "��", "��","��" };
		String weeklyName=months[c.get(2)] + "�µ�" + weeks[(c.get(4) - 1)] + "��";
		
		      
        System.out.println("��ȡ�� userName ��userid��dutyName��deptName���ֱ�Ϊ ��"+userName+" "+userid+" "+dutyName+" "+deptName);	
    	request.setAttribute("userName", userName);
    	request.setAttribute("userid", userid);
    	request.setAttribute("dutyName",dutyName);
    	request.setAttribute("deptName",deptName);
    	request.setAttribute("dutyId", dutyId);
    	request.setAttribute("deptId", deptId);
    	request.setAttribute("personnelId", personnelId);
    	request.setAttribute("weeklyName", weeklyName);
 		request.getRequestDispatcher("daily/addNewWeekly.jsp").forward(request, response);
		
    }

	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	synchronized (this) {
	            
    	       //�ڻ�ȡ�����������ǰ������setCharacterEncoding(���ַ�����)
    			request.setCharacterEncoding("UTF-8");
    			
			try  
			{ 
				
			//�����ID userid
			long rapporteurId=Long.parseLong(request.getParameter("userid")) ;
			//���±�ID PERSON_ID
			long personnelId=Long.parseLong(request.getParameter("personnelId")) ;
			//��¼�� û�д洢 ���д
			String userName =request.getParameter("userName");
			//�ܼƻ�����
			String weeklyName =request.getParameter("weeklyName");
           //��ʼʱ��
			String startDate=request.getParameter("startDate");
			//����ʱ��
			String endDate=request.getParameter("endDate");
			
            //�����ܽ�
			String summary =request.getParameter("summary");
			
			//��ע
			String comment=null;
			if (!request.getParameter("comment").equals("null")) {
				comment =request.getParameter("comment");
			}
			//����ID deptId
			long deptId=Long.parseLong(request.getParameter("deptId")) ;
			//ְλID dutyId
			long dutyId=Long.parseLong(request.getParameter("dutyId")) ;
			//����ʱ��
			Date	createdTime=new Date();
			//����޸�ʱ��
			Date	lastModifiedTime=new Date();

			//���ݵڼ�����дcode
	/*	    String current=format2.format(new Date());
		    Calendar c = Calendar.getInstance();
			String code=LoginName+"_"+current+"_"+0+c.get(4);
			System.out.println("��ǰcodeΪ : "+code);  */
			
			//���ݵ�ǰ�����code �����µ�code
			String LoginName = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getLoginName();
			String code=null;
			 Calendar time = Calendar.getInstance();
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		     String currentDate = sdf.format(time.getTime());
		     String maxCode = weeklyService.getMaxWeeklyCode(LoginName + "_" + currentDate, rapporteurId );
		     if (null != maxCode) {
		       int num = Integer.parseInt(maxCode) + 1;
		       code= LoginName + "_" + currentDate + "_0" + num;
		     }else {
		    	 code=LoginName + "_" + currentDate + "_" + "01";
			}
			
			Weekly weekly = new Weekly();
			weekly.setRapporteurId(rapporteurId);
			weekly.setPersonId(personnelId);
			weekly.setCode(code);
			weekly.setWeeklyName(weeklyName);
			weekly.setSummary(summary);
			weekly.setStartDate(format1.parse(startDate));
			weekly.setEndDate(format1.parse(endDate));
			weekly.setDeptId(deptId);
			weekly.setDutyId(dutyId);
			weekly.setComment(comment);
			weekly.setCreator(LoginName);
			weekly.setLastOperator(LoginName);
			weekly.setCreatedTime(createdTime);
			weekly.setLastModifiedTime(lastModifiedTime);
			
		
			
			//��Ϊ���ֶ�
			weekly.setInstId(Long.parseLong("14"));
			weekly.setDisabled(0);
			weekly.setOrganization(Long.parseLong("5"));
			
			weeklyService.saveWeekly(weekly);
			System.out.println("���ܽᱣ��ɹ�����¼��Ϊ��"+userName);
			
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		
	        //�����¼���
			//��ȡ���͵ļ���
/*			String moreinfo="{\"backVisitId\":\""+visit.getId()+"\",\"users\":\"";
			 List<ProjectInfoPersonnels> ProjectInfoPersonnelsList = new ArrayList<ProjectInfoPersonnels>();
			 ProjectInfoPersonnelsList= projectInfoPersonnelsService.getProjectInfoPersonnelsById(Long.parseLong(projectinfoid));
			 if( ProjectInfoPersonnelsList!=null)
			 {//������Ŀ���Ա
			 for (int i = 0; i < ProjectInfoPersonnelsList.size(); i++) {
				 ProjectInfoPersonnels Personnels = ProjectInfoPersonnelsList.get(i);
				 moreinfo+=Personnels.getProPerson_id().toString()+",";
				
			 }
			 moreinfo = moreinfo.substring(0,moreinfo.length()-1);
			 }
			 else
			 {//����ȫԱ
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
			event.setName("�ط��ύ");
			event.setMoreinfo(moreinfo);
			event.setEffectflag("E");
			event.setUserId(Long.toString(userid));
			eventService.saveEvent(event);
			request.getRequestDispatcher("qingjia/result.jsp").forward(request, response);
			
			 
	
			}
			
			*/
			 }
			catch (Exception e)  
			{  
				e.printStackTrace();
			    System.out.println(e.getMessage());  
			} 
		
	
	//  }
    }			
   }	
    