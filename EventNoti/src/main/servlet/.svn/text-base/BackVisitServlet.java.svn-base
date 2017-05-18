package main.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ReplyBackVisit;
import main.service.ReplyBackVisitService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.util.SQLUtil;


/**
 * 回访页面
 * 
 * @author dupeng
 * @date   20170224
 */
@SuppressWarnings("deprecation")
public class BackVisitServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 public  ReplyBackVisitService replyService;
		
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			replyService=(ReplyBackVisitService) context.getBean("replyService");
	       
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
		int id = Integer.parseInt(request.getParameter("bid"));
		String userid =request.getParameter("userid");
		String sender =request.getParameter("sender");
		Map<String,String> map = SQLUtil.getBackVisit(id);
		request.setAttribute("qid", map.get("id"));
		request.setAttribute("userid", userid);
		request.setAttribute("sender", sender);
		request.setAttribute("customName", map.get("customName"));
		request.setAttribute("customerId", map.get("customerId"));  //接收customerId
		request.setAttribute("projectName", map.get("projectName"));
		request.setAttribute("projectInfoId", map.get("projectInfoId"));  //接收projectInfoId
		request.setAttribute("contactName", map.get("contactName"));
		request.setAttribute("contactId", map.get("contactId"));    ////接收contactId
		request.setAttribute("visitor", map.get("visitor"));
		request.setAttribute("visitContent",map.get("visitContent"));
		request.setAttribute("laterAtten", map.get("laterAtten"));
		
		request.setAttribute("feed", map.get("feed"));
		request.setAttribute("remark", map.get("remark"));
		
		
		request.setAttribute("visitDate", map.get("visitDate"));
		//获取回复列表
		 List<ReplyBackVisit> ReplyList = new ArrayList<ReplyBackVisit>();
		 ReplyList=replyService.getReplyBackVisitById(id);
		request.setAttribute("ReplyList", ReplyList);
		request.getRequestDispatcher("backVisit/backVisit.jsp").forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
