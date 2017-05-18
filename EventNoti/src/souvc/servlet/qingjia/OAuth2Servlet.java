package souvc.servlet.qingjia;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import souvc.pojo.Code;
import souvc.pojo.UserInfo;
import souvc.pojo.WeixinUserInfo;
import souvc.util.SQLUtil;
import souvc.util.SendUtil;
import souvc.util.WeixinUtil;


public class OAuth2Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8"); 
		  PrintWriter out = response.getWriter();
		  //用户同意授权后，能获取到code
		  String code = request.getParameter("code");
		  String state = request.getParameter("state");
		  
		  if (!"authdeny".equals(code)) {
		  System.out.println("----code-----"+code);
		  String userid = WeixinUtil.getOpenidFromOauth(code);
		  
		//设置要传递的参数
		  request.setAttribute("openid", userid);
		  List<WeixinUserInfo> userList = new ArrayList<WeixinUserInfo>();
		  UserInfo  userInfo = SQLUtil.getUserByOpenid(userid);
		  List<String> openidList = SendUtil.getAllWeiXinUser();
		  for(String openid : openidList){
			  
			  WeixinUserInfo user = SendUtil.getUserInfo(openid);
			  String name = SQLUtil.getUserByOpenid(openid).getName();
			  user.setNickname(name);
			  userList.add(user);
		  }
		  List<Code> list = SQLUtil.getType();
		  request.setAttribute("userList", userList);
		  request.setAttribute("userInfo", userInfo);
		  request.setAttribute("list", list);
		  }else{
			  out.print("授权获取失败。。。");
		  }
		  
		// 跳转到新增请假信息jsp页面
		  if(state.equals("qingjia")){
			  request.getRequestDispatcher("qingjia/qingjiaAdd.jsp").forward(request, response);
		  }
		  if(state.equals("backvisit")){
			 // request.getRequestDispatcher("backVisit/addbackVisit.jsp").forward(request, response);
			  request.getRequestDispatcher("addBackVisitServlet").forward(request, response);
		  }
		  //跳转到新增项目信息jsp页面       
		  if(state.equals("addProjectInfo")){       
			  request.getRequestDispatcher("addProjectInfoServlet").forward(request, response);
		  }
		  //跳转到新增客户信息servlet页面         
		  if(state.equals("addCustomerInfo")){       
			  request.getRequestDispatcher("addCustomerInfoServlet").forward(request, response);
		  }	
		  //跳转到新增联系人信息servlet页面         
		  if(state.equals("addContactArchives")){       
			  request.getRequestDispatcher("addContactArchivesServlet").forward(request, response);
		  }	
	 
	 
	 
	 }	

}