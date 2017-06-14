package main.servlet.daily;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ReplyBackVisit;
import main.pojo.ReplyDaily;
import main.service.ReplyBackVisitService;
import main.service.ReplyDailyService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.util.SQLUtil;
import souvc.util.SendUtil;

/**
 * 回访评论页面
 * 
 * @author subiao
 * @date 20170512
 */
@SuppressWarnings("deprecation")
public class ReplyDailyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  ReplyDailyService replyDailyService;
		
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			replyDailyService=(ReplyDailyService) context.getBean("replyDailyService");
	       
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
	//	request.setCharacterEncoding("UTF-8");
		long dailyId = Long.parseLong(request.getParameter("dailyId"));
		long userid = Long.parseLong(request.getParameter("userid"));
		int sender = Integer.parseInt(request.getParameter("sender"));
		
		byte[] arrayStr = request.getParameter("fankui").getBytes("iso-8859-1");
		String fankui = new String(arrayStr, "UTF-8");
		// 记录到replydaily userame为使用者名称	
		String username = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getName();
		ReplyDaily replyDaily = new ReplyDaily();
		
		replyDaily.setDailyid(dailyId);
		replyDaily.setUserid(userid);
		replyDaily.setContent(fankui);
		replyDaily.setUsername(username);
		replyDaily.setReplydate(new Date());
		 System.out.println(dailyId+","+userid+","+fankui);
		 replyDailyService.saveReplyDaily(replyDaily);
		 System.out.println("保存一条日报回复");
		// 保存
		// 发送对象 发送给写日报的人
		String openid = SQLUtil.getUserDetail(sender).getOpenid();
		
		//dailyServlet?dailyId="+dailyId+"&userName="+userName+"&dutyName="+dutyName+"&deptName="+deptName+"&userid="+Long.parseLong(list[i])+"&sender="+sender;
		String title = "你有新的日报回复信息";
		String url = "http://yjkj.ngrok.cc/EventNoti/dailyServlet?dailyId="+dailyId+ "&userid=" + sender + "&sender=" + userid ;
		 System.out.println(username+","+openid+","+url+","+title+","+userid+","+fankui);
		 Map<String,String> map1 = SQLUtil.getDaily((int) dailyId);
		 String workContext=map1.get("workContext"); //当天工作内容 对应模板中的描述
		 String workContext2=workContext;
			if (workContext.length()>25) {
				 workContext2=workContext.substring(0,25);
			}
		 
		 SendUtil.sendTemplateRBHF(openid, url, title,String.valueOf(dailyId),workContext2,username, fankui, "请点击查看详情");
		request.getRequestDispatcher("qingjia/result.jsp").forward(request,
				response);

	}
}
