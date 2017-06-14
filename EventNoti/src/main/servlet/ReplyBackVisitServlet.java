package main.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.BackVisit;
import main.pojo.ReplyBackVisit;
import main.service.BackVisitService;
import main.service.ReplyBackVisitService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.util.SQLUtil;
import souvc.util.SendUtil;

/**
 * 回访评论页面
 * 
 * @author dupeng
 * @date 20170224
 */
@SuppressWarnings("deprecation")
public class ReplyBackVisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  ReplyBackVisitService replyService;
	public  BackVisitService backVisitService;
		
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			replyService=(ReplyBackVisitService) context.getBean("replyService");
			backVisitService=(BackVisitService) context.getBean("backVisitService");
			
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("qid"));
		long userid = Long.parseLong(request.getParameter("userid"));
		int sender = Integer.parseInt(request.getParameter("sender"));
		byte[] arrayStr = request.getParameter("fankui").getBytes("iso-8859-1");
		String fankui = new String(arrayStr, "UTF-8");
		String customName =new String(request.getParameter("customName").getBytes("iso-8859-1"), "UTF-8"); 
		String projectName ="";
			if(!request.getParameter("projectName").equals("null"))
			{
				projectName=new String(request.getParameter("projectName").getBytes("iso-8859-1"), "UTF-8"); 
			}
	
			
		String username = SQLUtil.getUserDetail(Integer.parseInt(request.getParameter("userid"))).getName();
		ReplyBackVisit Reply = new ReplyBackVisit();
		
		Reply.setVisitid(id);
		Reply.setUserid(userid);
		Reply.setContent(fankui);
		Reply.setUsername(username);
		Reply.setRaplydate(new Date());
		 System.out.println(id+","+userid+","+fankui);
		 replyService.saveReplyBackVisit(Reply);
		 System.out.println("保存");
		 //t_backVisit 获取记录回复次数并自增一
        BackVisit backVisit=backVisitService.getBackVisitById(id);
        Long replyTime=backVisit.getReplyTime();
        replyTime+=1; //自加一
        backVisit.setReplyTime(replyTime);
		backVisitService.updateBackVisit(backVisit);
		System.out.println("replyTime+1 : "+replyTime);
		// 保存
		// 发送对象
		String openid = SQLUtil.getUserDetail(sender).getOpenid();
		
		String title = "你有新的回访反馈信息";
		String url = "http://yjkj.ngrok.cc/EventNoti/backVisitServlet?bid="
				+ id + "&userid=" + sender + "&sender=" + userid;
		 System.out.println(openid+","+url+","+title+","+customName+","+projectName+","+username+","+fankui);
		SendUtil.sendTemplatePLHf(openid, url, title, customName, projectName,
				username, fankui, "点击查看详情");
		request.getRequestDispatcher("qingjia/result.jsp").forward(request,
				response);

	}
}
