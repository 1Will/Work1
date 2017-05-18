package souvc.servlet.register;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import souvc.util.WeixinUtil;


public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  System.out.println("=========进入registerServlet-=============");
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8"); 
		  PrintWriter out = response.getWriter();
		  //用户同意授权后，能获取到code
		  String code = request.getParameter("code");
		  System.out.println("=========进入registerServlet-====code值========="+code);
		  if (!"authdeny".equals(code)) {
		  // 根据code获取网页授权的openid
		  String openid = WeixinUtil.getOpenidFromOauth(code);
		  System.out.println("=========进入registerServlet-====openid值========="+openid);
		  //设置要传递的参数
		  request.setAttribute("openid", openid);
		  }else{
			  out.print("授权获取失败，至于为什么，自己找原因。。。");
		  }
		  
		  // 跳转到用户注册页面
		request.getRequestDispatcher("register/register.jsp").forward(request, response);
		}	

}
