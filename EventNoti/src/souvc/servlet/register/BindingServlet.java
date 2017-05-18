package souvc.servlet.register;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import souvc.util.SQLUtil;


public class BindingServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		}
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 String openid = request.getParameter("openid");
		 String userid = request.getParameter("userid");
		 int id = Integer.parseInt(userid);
		 SQLUtil.updateUser(openid, id);
		 
		 request.getRequestDispatcher("register/bindingSuccess.jsp").forward(request, response);
		 
		 
	 }

}
