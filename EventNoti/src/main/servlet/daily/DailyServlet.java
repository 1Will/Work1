package main.servlet.daily;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.Daily;
import main.pojo.ReplyBackVisit;
import main.pojo.ReplyDaily;
import main.service.DailyService;
import main.service.DepartmentService;
import main.service.ReplyBackVisitService;
import main.service.ReplyDailyService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;


/**
 * �ط�ҳ��
 * 
 * @author subiao
 * @date   20170511
 */
@SuppressWarnings("deprecation")
public class DailyServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 public  ReplyDailyService replyDailyService;
	 public  DailyService dailyService;
		
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			replyDailyService=(ReplyDailyService) context.getBean("replyDailyService");
			dailyService=(DailyService) context.getBean("dailyService");
	       
	    }  
	 //dailyServlet?dailyId="+dailyId+"&userName="+userName+"&dutyName="+dutyName+"&deptName="+deptName+"&userid="+Long.parseLong(list[i])+"&sender="+sender;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
			
			request.setCharacterEncoding("UTF-8");
		Long dailyId = Long.parseLong(request.getParameter("dailyId"));
		String userid =request.getParameter("userid");
		String sender =request.getParameter("sender");

		/*		String reporterName=request.getParameter("reporterName"); //�ձ�ģ�����û��ֵ �ձ��ظ�ģ�������ֵ
		//��ȡ���������� ����д�ձ���
		if ("".equals(reporterName)||reporterName==null) {
			//���reporterName������ ��һ�ν���senderid
			UserInfo userInfo  = SQLUtil.getUserDetail(Integer.parseInt(sender));
		    reporterName = userInfo.getName();
		} */
	   
		Daily daily =dailyService.getDailyById(dailyId);
		Long dutyId=daily.getDutyId();
		Long deptId=daily.getDeptId();
	    String loginName=daily.getCreator();
		
		//����loginName ��ȡ�û���Ϣ
		String reporterName=SQLUtil.getUserByLoginName(loginName).getName();
		
		String dutyName=SQLUtil.getDutyById(dutyId).getName();
		String deptName=SQLUtil.getDeptById(deptId).getName();
		
	//	Map<String,String> map = SQLUtil.getBackVisit(id);
		request.setAttribute("dailyId", dailyId.toString()); 
		request.setAttribute("userid", userid);
		request.setAttribute("sender", sender);
		request.setAttribute("deptName", deptName);
		request.setAttribute("dutyName", dutyName);
		request.setAttribute("reporterName", reporterName);
		
		request.setAttribute("currentDate", daily.getCurrentDate());
		request.setAttribute("weekDate", daily.getWeekDate().toString());
		request.setAttribute("backvisitContext",daily.getBackvisitContext());
		request.setAttribute("workContext",daily.getWorkContext());
		request.setAttribute("questions",daily.getQuestions());
		
		//��ȡ�ظ��б�
		 List<ReplyDaily> replyDailyList = new ArrayList<ReplyDaily>();
		 replyDailyList=replyDailyService.getReplyDailyById(dailyId);
		request.setAttribute("replyDailyList", replyDailyList);
		request.getRequestDispatcher("daily/daily.jsp").forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}