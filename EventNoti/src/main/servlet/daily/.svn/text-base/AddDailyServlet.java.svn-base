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
 * 读取项目信息页面
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
    	
    	//获取用户ID userid
    	 UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
    	 Integer userid=Integer.parseInt(userInfo.getId());
    	//获取用户姓名
    	 String userName = userInfo.getName();
    	 //获取用户code
         String code=userInfo.getCode();   	 
    	//获取用户名简称lf
    	 String LoginName = userInfo.getLoginName();
 		//通过人事表获取deptId和dutyId
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
		 
		 
        System.out.println("获取的 userName ,LoginName,code,userid,dutyName,deptName,instId,分别为 ："+userName+" "+LoginName+" "+code+" "+userid+" "+dutyName+" "+deptName+" "+instId);	
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
				
			//填表人ID userid
			long userid=Long.parseLong(request.getParameter("userid")) ;
			Long rapporteurId=userid;
			//人事表ID PERSON_ID
			long personnelId=Long.parseLong(request.getParameter("personnelId")) ;
			//记录人 没有存储 存简写
			String userName =new String(request.getParameter("userName").getBytes("iso-8859-1"), "UTF-8");
			//名字简称 lf
			String loginName=request.getParameter("loginName");
			//员工code
			String code=request.getParameter("code");
           //当前时间
			String currentDate=request.getParameter("currentDate");
		   //星期
			String weekDate=new String(request.getParameter("weekDate").getBytes("iso-8859-1"), "UTF-8");		
			
			//拜访工作内容
			String backvisitContext =new String(request.getParameter("backvisitContext").getBytes("iso-8859-1"), "UTF-8");
            //其他工作内容
			String workContext =new String(request.getParameter("workContext").getBytes("iso-8859-1"), "UTF-8");
		
			  //问题
			String questions=null;
			if (!request.getParameter("questions").equals("null")) {
				questions =new String(request.getParameter("questions").getBytes("iso-8859-1"), "UTF-8");
			}
			
			
			//部门ID deptId
			long deptId=Long.parseLong(request.getParameter("deptId")) ;
			String deptName=new String(request.getParameter("deptName").getBytes("iso-8859-1"), "UTF-8");
			//职位ID dutyId
			long dutyId=Long.parseLong(request.getParameter("dutyId")) ;
			String dutyName=new String(request.getParameter("dutyName").getBytes("iso-8859-1"), "UTF-8");
            //接收 instId			
			long instId=Long.parseLong(request.getParameter("instId")) ;
			//接收 organization
			long organization=Long.parseLong(request.getParameter("organization")) ;
			
			//获取 开始时间 结束时间
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
				System.out.println("获取 startTime，endTime失败！");
				e.printStackTrace();
			}
		  
			//创建时间
		   Date	createdTime=new Date();
		   //最后修改时间
		   Date	lastModifiedTime=new Date();
		   
		 //获取回访记录的所有id
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
			
			//不为空字段
			daily.setInstId(instId);
			daily.setDisabled(0);
			daily.setOrganization(organization);
			
			dailyService.saveDaily(daily);
			System.out.println("日报保存成功，记录人为："+userName);
			//获取最后一条ID
			Long id=  dailyService.getLastId();
			System.out.println("当前数据存储的id为: "+id);
			
		
	        //插入事件表
			//获取发送的集合   dutyName不容易解析 直接带过去
			String moreinfo="{\"dailyId\":\""+daily.getId()+"\",\"code\":\""+code+"\",\"dutyName\":\""+dutyName+"\",\"deptName\":\""+deptName+"\",\"userName\":\""+userName+"\",\"users\":\"";
			
		   //发送全员  暂时
	/*		List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
		    for (int i = 0; i < UserInfoList.size(); i++) {
			     UserInfo User = SQLUtil.getAllUserId();
				 moreinfo+=User.getId().toString()+",";
				 }
			 moreinfo = moreinfo.substring(0,moreinfo.length()-1); */
			 
			String ids =userid+"";
			//发送给自己和上级领导 获取id  循环判断
		    String code1=code;
		   loop:for ( ;code1!=null; ) {
		  
		   List<PersonnelFiles> pList=personnelFilesService.getPersonnelFilesByCode(code1);				
		   PersonnelFiles pFiles=null;
           for (int j = 0; j < pList.size(); ) {
			 pFiles=pList.get(0).getSuperiorLeader();
			 if (pFiles!=null) {
				 code1=pFiles.getCode(); //通过人事表 获取领导实体 得到code
				 //根据code 获取users对应实体 获取id
				 List<UsersInfo> usersList=(List<UsersInfo>) usersInfoService.getUsersInfoByCode(code1);
			     for (int i = 0; i < usersList.size(); i++) {
					UsersInfo users=usersList.get(i);
					ids+=","+users.getId().toString();
				}
				 
			 continue loop; //如果pFiles!=null 返回loop继续判断
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
		    eventType = eventService.getEventTypeByCode("10002"); //日报时间	
			event.setEventType(eventType);
			event.setName("日报提交");
			event.setMoreinfo(moreinfo);
			event.setEffectflag("E");
			event.setUserId(Long.toString(rapporteurId)); //rapporteurId 即是userid
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
    
