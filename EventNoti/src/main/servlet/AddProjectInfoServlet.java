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

import main.pojo.EventNew;
import main.pojo.EventType;
import main.pojo.PersonnelFiles;
import main.pojo.ProjectInfo;
import main.pojo.ProjectInfoPersonnels;
import main.pojo.UsersInfo;
import main.service.EventService;
import main.service.PersonnelFilesService;
import main.service.ProjectInfoPersonnelsService;
import main.service.ProjectInfoService;
import main.service.UsersInfoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



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
	public  EventService eventService;
	public PersonnelFilesService personnelFilesService;
	public ProjectInfoPersonnelsService pInfoPersonnelsService;
	public UsersInfoService usersInfoService;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		projectInfoService = (ProjectInfoService) context
				.getBean("projectInfoService");
		personnelFilesService=(PersonnelFilesService) context
				.getBean("personnelFilesService");
		pInfoPersonnelsService=(ProjectInfoPersonnelsService) context
				.getBean("projectInfoPersonnelsService");
		usersInfoService=(UsersInfoService) context.getBean("usersInfoService");
		eventService =(EventService) context.getBean("eventService");
		
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�ͻ������б�
		// ��ȡ��Ŀ��ϵ�������б�
		
		
		request.getRequestDispatcher("backVisit/addProjectInfo.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	   //�ڻ�ȡ�����������ǰ������setCharacterEncoding(���ַ�����)
		request.setCharacterEncoding("UTF-8");
		
		// ��Ŀ����
		String projectName=null;
		if (!request.getParameter("projectName").equals("null")) {
			projectName = request.getParameter("projectName");
		}
		//��ȡ �ͻ�id
		String customerId=request.getParameter("customerId");
		// �ͻ����Ʋ���Ҫ��ȡ
		//��ȡ ��Ŀ��ϵ��id
		String contactId=request.getParameter("contactname");
	
		// ��Ŀ����
		String outline = request.getParameter("outline");
		// ��ϵ�˽�ɫ˵��
		String conOutline = request.getParameter("conOutline");
		// ����ʱ��
		String createdTime = request.getParameter("createdTime");
		//����޸�ʱ��
		Date lastModifiedTime=new Date();
		//����ҵ������
		String businessType=request.getParameter("businessType");
		
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
		

		// ��Ŀ����������
		String controllerName = request.getParameter("controllerName");
		//����name�����±����ȡ��ӦID
		 List<PersonnelFiles> list=personnelFilesService.getPersonnelFilesByName(controllerName);
		 PersonnelFiles personnelFiles=null;
		 for(int i=0;i<list.size();i++){
			 personnelFiles=list.get(0);
		 }
		 Long controllerId=personnelFiles.getId();
		
		//�����û�id
		long userid = Long.parseLong(request.getParameter("userid"));
		//��ȡ�û�����
		String creatorName = controllerName;
		String lastOperator=creatorName;
		System.out.println("�鿴��ȡ��  userid ��username  �ֱ�Ϊ��"+userid+" "+creatorName);

		
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
			projectInfo.setCreatorName(creatorName);//���� ��Ŀ������
			projectInfo.setLastOperator(lastOperator);//���� �������˼���ʹ����
			projectInfo.setCreatorId(userid);//���� ʹ����id
	
			// �����ֶβ���Ϊ��
			projectInfo.setDisabled(Integer.parseInt("0"));
			projectInfo.setVersion(Long.parseLong("0"));
            projectInfo.setState(Long.parseLong("465"));//��Ŀ״̬ 465 ����
			projectInfoService.saveProjectInfo(projectInfo);
			System.out.println("addProject�洢��� ��Ŀ��Ϊ:" + projectName);
			
			//ͬʱ����Ŀ���Ա���浽����
			ProjectInfoPersonnels personnels=new ProjectInfoPersonnels();
			
			personnels.setProjectInfo_id(projectInfo.getId());
			personnels.setProPerson_id(controllerId);
			personnels.setOutline("��Ŀ������");
			personnels.setVersion(Long.parseLong("0"));
			personnels.setDisabled(Integer.parseInt("0"));
			personnels.setBusinessType(Long.parseLong("470")); //��Ŀ������
			personnels.setCreator(controllerName);
			personnels.setLastOperator(controllerName);
			personnels.setCreatedTime(new Date());
			personnels.setLastModifiedTime(lastModifiedTime);
			
			pInfoPersonnelsService.saveProjectInfoPersonnels(personnels);
			System.out.println("����ProjectInfoPersonnels idΪ : "+personnels.getId());
			
		//	request.getRequestDispatcher("backVisit/result.jsp").forward(request, response);
		
	        //�����¼���
			//��ȡ���͵ļ���
			Long projectinfoId=projectInfo.getId();
			String moreinfo="{\"projectInfoId\":\""+projectinfoId+"\",\"users\":\"";
			
			//������Ŀ���Ա  Ŀǰ��Ŀ���Աֻ��Ĭ�ϴ�����
				List<ProjectInfoPersonnels> ProjectInfoPersonnelsList = new ArrayList<ProjectInfoPersonnels>();
				ProjectInfoPersonnelsList= pInfoPersonnelsService.getProjectInfoPersonnelsById(projectinfoId);
				for (int i = 0; i < ProjectInfoPersonnelsList.size(); i++) {
					ProjectInfoPersonnels Personnels = ProjectInfoPersonnelsList.get(i);
		            Long personelId=Personnels.getProPerson_id();
		            PersonnelFiles pFiles=personnelFilesService.getPersonnelFilesById(personelId);
		            String code1=pFiles.getCode();
		            List<UsersInfo> userList=usersInfoService.getUsersInfoByCode(code1);
		            for (int j = 0; j < userList.size(); j++) {
		            	UsersInfo usersInfo=userList.get(j);
		            	moreinfo+=usersInfo.getId().toString()+",";
		            	
					}
				}
				moreinfo = moreinfo.substring(0,moreinfo.length()-1);
		    moreinfo+="\"}";
		    System.out.println(moreinfo);
			EventNew event=new EventNew();
			EventType eventType = new EventType();
			eventType = eventService.getEventTypeByCode("10003");	
			event.setEventType(eventType);
			event.setName("�½���Ŀ�ύ");
			event.setMoreinfo(moreinfo);
			event.setEffectflag("E");
			event.setUserId(Long.toString(userid));
			eventService.saveEvent(event);
			request.getRequestDispatcher("backVisit/result.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
