package main.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.PersonnelFiles;
import main.service.BackVisitService;
import main.service.CustomerInfoService;
import main.service.PersonnelFilesService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 回访评论页面
 * 
 * @author dupeng
 * @date 20170224
 */
@SuppressWarnings("deprecation")
public class AddBackVisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  BackVisitService backVisitService;
	public  CustomerInfoService customerInfoService;
	public  PersonnelFilesService personnelFilesService;


	 public void init() throws ServletException {  
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		
			customerInfoService=(CustomerInfoService) context.getBean("customerInfoService");
			personnelFilesService=(PersonnelFilesService)context.getBean("personnelFilesService");
			
	    }  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //获取人事列表
		List<PersonnelFiles> personnelFilesList=personnelFilesService.getAllPersonnelFiles();
		request.setAttribute("personnelFilesList", personnelFilesList);
		
		request.getRequestDispatcher("backVisit/addbackVisit.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
