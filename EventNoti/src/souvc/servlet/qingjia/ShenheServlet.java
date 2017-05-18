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
import souvc.util.SendUtil;


/**
 * 请求处理的核心类
 * 
 * @author liyufeng
 * @date   20161230
 */
public class ShenheServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("qid"));
		String status = request.getParameter("shenheType").toString();
		byte[] arrayStr = request.getParameter("fankui").getBytes("iso-8859-1");
		String fankui = new String(arrayStr, "UTF-8");
		TleaveBill bill = new TleaveBill();
		bill.setStatus(status);
		bill.setBohui(fankui);
		SQLUtil.updateQingJia(bill, id);
		//发送请假模板
		String url = "http://yjkj.ngrok.cc/EventNoti/resultServlet?id="+id;
		TleaveBill billbyid = SQLUtil.getQingJia(id);
		String openid= SQLUtil.getUserDetail(Integer.parseInt(billbyid.getPersonId())).getOpenid();
		String name = SQLUtil.getUserName(Integer.parseInt(billbyid.getPersonId()));
	    
	     String type = SQLUtil.getNameById(billbyid.getType());
		 String startDate =billbyid.getStartDate().toString();
		 String endDate = billbyid.getEndDate().toString();
		 String time=startDate+"至"+endDate;
		//String template_id = "3U6FSZchzEQOTpWQLBV_jDestxaWS-iEkkbR3fWiZwA";
		//List<String> list = SendUtil.getAllWeiXinUser();
		//for(String openid : list){
		 String title = "亲爱的"+ name+"，你的请假审批结果如下";
		 String shenhe = SQLUtil.getUserByOpenid(billbyid.getShenhe()).getName();
		 String yuanyin=billbyid.getBetreffzeile().toString();
		 String jieguo;
		
		 if(status.equals("1"))
		 {
			 jieguo="同意";
		 }
		 else
		{jieguo="驳回";}
		
			//SendUtil.sendTemplateMessage(openid, url, template_id);
		 SendUtil.sendTemplateShQj(openid, url, title,type, time, shenhe, yuanyin, jieguo, fankui);
		//}
		
		request.getRequestDispatcher("qingjia/result.jsp").forward(request, response);
	}
}
