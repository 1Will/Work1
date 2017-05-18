package souvc.servlet.qingjia;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import souvc.pojo.TleaveBill;
import souvc.util.SQLUtil;
import souvc.util.SendUtil;


/**
 * 请求处理的核心类
 * 
 * @author dupeng
 * @date   20161223
 */
public class QingJiaSubmit extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userid = request.getParameter("userid");
		 String deptId = request.getParameter("deptId");
		 String openid = request.getParameter("openid");
		 String type = request.getParameter("type");
		 String startDate = request.getParameter("startDate");
		 String endDate = request.getParameter("endDate");
		 String days = request.getParameter("days");
		 byte[] yuanyin = request.getParameter("yuanyin").getBytes("iso-8859-1");
		 String shenhe = request.getParameter("shenhe");
		 
		 TleaveBill bill = new TleaveBill();
		 try {
			 String username = SQLUtil.getUserByOpenid(openid).getName();
			 bill.setTitle(username+"的请假单");
			 bill.setDeptId(deptId);
			 bill.setCreateDate(new Date());
			 bill.setBetreffzeile(new String(yuanyin,"UTF-8"));
			 bill.setEndDate(format1.parse(endDate));
			 bill.setStartDate(format1.parse(startDate));
			 bill.setMainHour(Float.parseFloat(days));
			 bill.setPersonId(userid);
			 bill.setType(type);
			 bill.setShenhe(shenhe);
			 bill.setStatus("0");
			 int id = SQLUtil.saveQingJia(bill);
			 String url = "http://yjkj.ngrok.cc/EventNoti/qingjiaSuccessServlet?id="+id+"&name="+openid;
			 //String template_id = "OaNt-f1tPkZuKyrQqsD_DeF9Mh-ZPaWTAGMlooyMnXU";
			 //SendUtil.sendTemplateMessage(shenhe, url, template_id);
			 String title = "亲爱的"+ SQLUtil.getUserByOpenid(shenhe).getName()+"，你有新的请假审批";
			 String time=startDate+"至"+endDate;
			 SendUtil.sendTemplateQj(shenhe, url, title,username, SQLUtil.getNameById(type), time, days, new String(yuanyin,"UTF-8"));
			 request.setAttribute("startDate", startDate);
			 request.setAttribute("endDate", endDate);
			 request.setAttribute("days", days);
			 request.setAttribute("yuanyin", new String(yuanyin,"UTF-8"));
			 String name = SQLUtil.getNameById(type);
			 request.setAttribute("type",name);
			 String person = SQLUtil.getUserByOpenid(shenhe).getName();
			 request.setAttribute("shenhe", person);
			 request.getRequestDispatcher("qingjia/qingjiaSuccess.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
	}
}
