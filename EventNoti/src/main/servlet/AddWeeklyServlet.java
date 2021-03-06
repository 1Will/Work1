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
 * 读取项目信息页面
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
    	
    	//获取用户ID userid
    	 UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
    	 Integer userid=Integer.parseInt(userInfo.getId());
    	//获取用户姓名
 		String userName = SQLUtil.getUserDetail(userid).getName();
 		//通过人事表获取deptId和dutyId
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
		 
		//获取当前周报名称  四月第五周
		Calendar c = Calendar.getInstance();
		String[] months = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
		 
		String[] weeks = { "一", "二", "三", "四", "五","六" };
		String weeklyName=months[c.get(2)] + "月第" + weeks[(c.get(4) - 1)] + "周";
		
		      
        System.out.println("获取的 userName ，userid，dutyName，deptName，分别为 ："+userName+" "+userid+" "+dutyName+" "+deptName);	
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
	            
    	       //在获取请求对象内容前，调用setCharacterEncoding(“字符集”)
    			request.setCharacterEncoding("UTF-8");
    			
			try  
			{ 
				
			//填表人ID userid
			long rapporteurId=Long.parseLong(request.getParameter("userid")) ;
			//人事表ID PERSON_ID
			long personnelId=Long.parseLong(request.getParameter("personnelId")) ;
			//记录人 没有存储 存简写
			String userName =request.getParameter("userName");
			//周计划名称
			String weeklyName =request.getParameter("weeklyName");
           //开始时间
			String startDate=request.getParameter("startDate");
			//结束时间
			String endDate=request.getParameter("endDate");
			
            //本周总结
			String summary =request.getParameter("summary");
			
			//备注
			String comment=null;
			if (!request.getParameter("comment").equals("null")) {
				comment =request.getParameter("comment");
			}
			//部门ID deptId
			long deptId=Long.parseLong(request.getParameter("deptId")) ;
			//职位ID dutyId
			long dutyId=Long.parseLong(request.getParameter("dutyId")) ;
			//创建时间
			Date	createdTime=new Date();
			//最后修改时间
			Date	lastModifiedTime=new Date();

			//根据第几周填写code
	/*	    String current=format2.format(new Date());
		    Calendar c = Calendar.getInstance();
			String code=LoginName+"_"+current+"_"+0+c.get(4);
			System.out.println("当前code为 : "+code);  */
			
			//根据当前表最大code 生成新的code
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
			
		
			
			//不为空字段
			weekly.setInstId(Long.parseLong("14"));
			weekly.setDisabled(0);
			weekly.setOrganization(Long.parseLong("5"));
			
			weeklyService.saveWeekly(weekly);
			System.out.println("周总结保存成功，记录人为："+userName);
			
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		
	        //插入事件表
			//获取发送的集合
/*			String moreinfo="{\"backVisitId\":\""+visit.getId()+"\",\"users\":\"";
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
    
