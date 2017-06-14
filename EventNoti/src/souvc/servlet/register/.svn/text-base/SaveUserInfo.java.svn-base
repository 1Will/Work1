package souvc.servlet.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;


/**
 * 请求处理的核心类
 * 
 * @author dupeng
 * @date   20170112
 */
public class SaveUserInfo extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userid = request.getParameter("userid");
		 String loginName = new String(request.getParameter("loginName").getBytes("iso-8859-1"),"UTF-8");
		 String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
		 String company = new String(request.getParameter("company").getBytes("iso-8859-1"),"UTF-8");
		 String position = new String(request.getParameter("position").getBytes("iso-8859-1"),"UTF-8");
		 String business = new String(request.getParameter("business").getBytes("iso-8859-1"),"UTF-8");
		 String tel = new String(request.getParameter("tel").getBytes("iso-8859-1"),"UTF-8");
		 String sex = new String(request.getParameter("sex").getBytes("iso-8859-1"),"UTF-8");
		 String hobby = new String(request.getParameter("hobby").getBytes("iso-8859-1"),"UTF-8");
		 String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"UTF-8");
		 String locale = new String(request.getParameter("locale").getBytes("iso-8859-1"),"UTF-8");
		 
				UserInfo user = new UserInfo();
				user.setLoginName(loginName);
				user.setName(name);
				user.setCompany(company);
				user.setPosition(position);
				user.setBusiness(business);
				user.setSex(sex);
				user.setHobby(hobby);
				user.setEmail(email);
				user.setLocale(locale);
				user.setTel(tel);
				user.setOpenid(userid);
		 SQLUtil.saveUserInfo(user);
		 System.out.println("用户注册成功,userid= "+user.getId());
		 request.setAttribute("loginName", loginName);
		 request.setAttribute("name", name);
		 request.setAttribute("company", company);
		 request.setAttribute("position", position);
		 request.setAttribute("business", business);
		 request.setAttribute("sex", sex);
		 request.setAttribute("hobby", hobby);
		 request.setAttribute("email", email);
		 request.setAttribute("locale", locale);
		 request.setAttribute("tel", tel);
		 request.getRequestDispatcher("register/userDetail.jsp").forward(request, response);
		 
	}
}
