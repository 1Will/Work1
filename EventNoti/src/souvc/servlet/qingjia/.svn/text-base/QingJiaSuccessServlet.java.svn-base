package souvc.servlet.qingjia;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import souvc.pojo.TleaveBill;
import souvc.util.SQLUtil;


/**
 * 请求处理的核心类
 * 
 * @author liyufeng
 * @date   20161230
 */
public class QingJiaSuccessServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		//String username = new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
		String openid=new String(request.getParameter("name"));
		TleaveBill bill = SQLUtil.getQingJia(id);
		request.setAttribute("startDate", format1.format(bill.getStartDate()));
		request.setAttribute("endDate", format1.format(bill.getEndDate()));
		request.setAttribute("days", bill.getMainHour().toString());
		request.setAttribute("yuanyin", bill.getBetreffzeile());
		request.setAttribute("type", SQLUtil.getNameById(bill.getType()));
		request.setAttribute("qid", id+"");
	   String name = SQLUtil.getUserByOpenid(openid).getName();
		request.setAttribute("name",name);
		request.getRequestDispatcher("qingjia/shenhe.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
