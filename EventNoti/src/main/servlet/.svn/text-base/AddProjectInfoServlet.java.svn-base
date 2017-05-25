package main.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.PersonnelFiles;
import main.pojo.ProjectInfo;
import main.service.EventService;
import main.service.PersonnelFilesService;
import main.service.ProjectInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.util.SQLUtil;


/**
 * 创建项目页面
 * 
 * @author subiao
 * @date 20170411
 */
@SuppressWarnings("deprecation")
public class AddProjectInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProjectInfoService projectInfoService;
	public static EventService eventService;
	public PersonnelFilesService personnelFilesService;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		projectInfoService = (ProjectInfoService) context
				.getBean("projectInfoService");
		personnelFilesService=(PersonnelFilesService) context
				.getBean("personnelFilesService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取客户名称列表
		// 获取项目联系人名称列表
		
	/*	// 获取项目负责人名称列表
		List<PersonnelFiles> personnelFilesList = new ArrayList<PersonnelFiles>();
		personnelFilesList = personnelFilesService.getPersonnelFilesById();
		request.setAttribute("personnelFilesList", personnelFilesList);*/
		
		request.getRequestDispatcher("backVisit/addProjectInfo.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 项目名称
		String projectName=null;
		if (!request.getParameter("projectName").equals("null")) {
			 projectName = new String(request.getParameter("projectName").getBytes("iso-8859-1"), "UTF-8");
		}
		//获取 客户id
		String customerId=request.getParameter("customerId");
		// 客户名称不需要获取
		//获取 项目联系人id
		String contactId=request.getParameter("contactname");
	
		// 项目概述
		String outline = new String(request.getParameter("outline").getBytes("iso-8859-1"), "UTF-8");
		// 联系人角色说明
		String conOutline = new String(request.getParameter("conOutline").getBytes("iso-8859-1"), "UTF-8");
		// 创建时间
		String createdTime = request.getParameter("createdTime");
		//最后修改时间
		Date lastModifiedTime=new Date();
		//接收业务属性
		String businessType=request.getParameter("businessType");
		
		// 获取当前数据表中最大id值
		Object id1 = projectInfoService.findLastProjectId();
		long id=Long.parseLong(String.valueOf(id1));
		//code根据最大ID值获取对应code 进行+1处理 目前最大加载到XM-009999
		String code = projectInfoService.getProjectInfo_ById(id).getCode();
		if ( id>=0) {
			int b = Integer.parseInt(code.substring(3, 9));
			b=b+1; //自增1
			String str=String.format("%06d", b); //转变为000001型整数
			code = "XM-".concat(str);
			System.out.println(code);
		}
		

		// 项目负责人名字
		String controllerName = new String(request.getParameter("controllerName").getBytes("iso-8859-1"), "UTF-8");
		//根据name从人事表里获取对应ID
		 List<PersonnelFiles> list=personnelFilesService.getPersonnelFilesByName(controllerName);
		 PersonnelFiles personnelFiles=null;
		 for(int i=0;i<list.size();i++){
			 personnelFiles=list.get(0);
		 }
		 Long controllerId=personnelFiles.getId();
		
		//接收用户id
		long userid = Long.parseLong(request.getParameter("userid"));
		//获取用户姓名
		String creatorName = controllerName;
		String lastOperator=creatorName;
		System.out.println("查看获取的  userid ，username  分别为："+userid+" "+creatorName);

		
		try {
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setProjectName(projectName);
			projectInfo.setOutline(outline);
			projectInfo.setConOutline(conOutline);
			projectInfo.setCreatedTime(format1.parse(createdTime));
			projectInfo.setLastModifiedTime(lastModifiedTime);
			projectInfo.setCode(code);
			projectInfo.setBusinessType(Long.parseLong(businessType));
			
			projectInfo.setCustomerId(Long.parseLong(customerId));
			projectInfo.setContactId(Long.parseLong(contactId));
			projectInfo.setControllerId(controllerId);
			projectInfo.setCreatorName(creatorName);//接收 项目创建人
			projectInfo.setLastOperator(lastOperator);//接收 最后跟进人即是使用人
			projectInfo.setCreatorId(userid);//接收 使用人id
	
			// 下面字段不能为空
			projectInfo.setDisabled(Integer.parseInt("0"));
			projectInfo.setVersion(Long.parseLong("0"));
            projectInfo.setState(Long.parseLong("465"));//项目状态 465 立项
			projectInfoService.saveProjectInfo(projectInfo);
			System.out.println("addProject存储完成 项目名为:" + projectName);
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
