package main.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.pojo.BackVisit;
import main.pojo.EventNew;
import main.pojo.ProjectInfo;
import main.pojo.ProjectInfoPersonnels;
import main.service.BackVisitService;
import main.service.EventService;
import main.service.ProjectInfoPersonnelsService;
import main.service.ProjectInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import souvc.util.SQLUtil;

/**
 * ������Ŀҳ��
 * 
 * @author subiao
 * @date 20170411
 */
@SuppressWarnings("deprecation")
public class AddProjectInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProjectInfoService projectInfoService;
	public static EventService eventService;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		projectInfoService = (ProjectInfoService) context
				.getBean("projectInfoService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��Ŀ�б�
		List<ProjectInfo> ProjectInfoList = new ArrayList<ProjectInfo>();
		ProjectInfoList = projectInfoService.getProjectInfoById();
		request.setAttribute("ProjectList", ProjectInfoList);
		request.getRequestDispatcher("backVisit/addProjectInfo.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��Ŀ����
		String projectName=null;
		if (!request.getParameter("projectName").equals("null")) {
			 projectName = new String(request.getParameter("projectName").getBytes("iso-8859-1"), "UTF-8");
		}
		// ��Ŀ����
		String outline = new String(request.getParameter("outline").getBytes("iso-8859-1"), "UTF-8");
		// ����ʱ��
		String createdTime = request.getParameter("createdTime");
		// ��ȡ��ǰ���ݱ������idֵ
		Object id1 = projectInfoService.findLastProjectId();
		long id=Long.parseLong(String.valueOf(id1));
		//code�������IDֵ��ȡ��Ӧcode ����+1���� Ŀǰ�����ص�XM-009999
		String code = projectInfoService.getProjectInfo_ById(id).getCode();
		if ( id>=0) {
			int b = Integer.parseInt(code.substring(3, 9));
			b=b+1; //����1
			String str=String.format("%06d", b); //ת��Ϊ000001������
			code = "XM-".concat(str);
			System.out.println(code);
		}

		try {
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setProjectName(projectName);
			projectInfo.setOutline(outline);
			projectInfo.setCreatedTime(format1.parse(createdTime));
			projectInfo.setCode(code);
			// �����ֶβ���Ϊ��
			projectInfo.setDisabled(Integer.parseInt("0"));
			projectInfo.setVersion(Long.parseLong("0"));

			projectInfoService.saveProjectInfo(projectInfo);
			System.out.println("addProject�洢��� ��Ŀ��Ϊ:" + projectName);
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
