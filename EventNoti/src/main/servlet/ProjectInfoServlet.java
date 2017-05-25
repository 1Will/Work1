package main.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.ProjectInfo;
import main.service.ProjectInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




/**
 * 读取项目信息页面
 * 
 * @author subiao
 * @date   20170410
 */
@SuppressWarnings("deprecation")
public class ProjectInfoServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 public  ProjectInfoService projectInfoService;
	 public	 ProjectInfo projectInfo;
	 
	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
			projectInfoService=(ProjectInfoService) context.getBean("projectInfoService");
	       
	    }  
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//读取项目信息列表
    	Long id = Long.parseLong(request.getParameter("projectInfoId"));
    	projectInfo=projectInfoService.getProjectInfo_ById(id);
 		request.setAttribute("projectInfo", projectInfo);
 		request.getRequestDispatcher("backVisit/projectInfo.jsp").forward(request, response);
		}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
	
		
		}
	}


	
}
