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
 * @author liyufeng
 * @date   20161230
 */
public class ResultServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TleaveBill bill = SQLUtil.getQingJia(id);
		request.setAttribute("startDate", format1.format(bill.getStartDate()));
		request.setAttribute("endDate", format1.format(bill.getEndDate()));
		request.setAttribute("days", bill.getMainHour().toString());
		request.setAttribute("yuanyin", bill.getBetreffzeile());
		request.setAttribute("type", SQLUtil.getNameById(bill.getType()));
		request.setAttribute("status", bill.getStatus().toString());
		request.setAttribute("fankui", bill.getBohui());
		
		String name = SQLUtil.getUserName(Integer.parseInt(bill.getPersonId()));
		request.setAttribute("name", name);
		String shenhe = SQLUtil.getUserByOpenid(bill.getShenhe()).getName();
		request.setAttribute("shenhe", shenhe);
		request.getRequestDispatcher("qingjia/detail.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
