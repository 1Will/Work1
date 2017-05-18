package souvc.servlet.register;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import souvc.pojo.UserInfo;
import souvc.util.SQLUtil;


public class SearchUser extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		}
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 String name = request.getParameter("name");
		 List<UserInfo> list = SQLUtil.getUser(name);
		 JSONArray json = JSONArray.fromObject(list);  
		 response.setCharacterEncoding("UTF-8"); 
		 PrintWriter out = response.getWriter();
		 out.print(json);
		 /*String name=request.getParameter("name");
		 response.getWriter().print(name);*/
		 
	 }

}
