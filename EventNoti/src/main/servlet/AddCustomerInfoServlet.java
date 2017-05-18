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

import main.pojo.CodeValue;
import main.pojo.CustomerInfo;
import main.pojo.PersonnelFiles;
import main.service.CodeValueService;
import main.service.CustomerInfoService;
import main.service.PersonnelFilesService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * �����ͻ���Ϣҳ��
 * 
 * @author subiao
 * @date 20170417
 */
@SuppressWarnings("deprecation")
public class AddCustomerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CustomerInfoService customerInfoService;
	public CodeValueService codeValueService;
	public PersonnelFilesService personnelFilesService;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		customerInfoService = (CustomerInfoService) context
				.getBean("customerInfoService");
		codeValueService =(CodeValueService) context.getBean("codeValueService");
		personnelFilesService=(PersonnelFilesService) context
				.getBean("personnelFilesService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��Ŀ�б�
		List<CodeValue> codeValueList = new ArrayList<CodeValue>();
		Long id=(long) 2;  //��ȡcvId=2�ļ���
		codeValueList = codeValueService.getCodeValueByCvid(id);
		request.setAttribute("codeValueList", codeValueList);
		//��ȡ������Ϣ�б�
		List<PersonnelFiles> personnelFilesList=new ArrayList<PersonnelFiles>();
		personnelFilesList=personnelFilesService.getPersonnelFilesById();
	    request.setAttribute("personnelFilesList", personnelFilesList);	
		request.getRequestDispatcher("backVisit/addCustomerInfo.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�ͻ����� 
		String customerName=null;
		if (!request.getParameter("customerName").equals("null")) {
			customerName= new String(request.getParameter("customerName").getBytes("iso-8859-1"), "UTF-8");
		}
		// �ͻ�״̬ Long
		String customerType=request.getParameter("customerType") ;
		//��ҵ����  Long 
		String companyNature=request.getParameter("companyNature") ;
		// ��ҵ Long 
	//	String industry=request.getParameter("industry") ;
		// ����Long 
		String country=request.getParameter("country") ;
		// ʡ��Long 
		String province=request.getParameter("province") ;
		// ����Long 
		String city=request.getParameter("city") ;
		// ��Ϣ��ԴLong 
		String resource=request.getParameter("resource") ;
		// ��˾��վ
		String web= new String(request.getParameter("web").getBytes("iso-8859-1"), "UTF-8");
		// ��ַ
		String address= new String(request.getParameter("address").getBytes("iso-8859-1"), "UTF-8");
		// ҵ��Χ
		String businessScope= new String(request.getParameter("businessScope").getBytes("iso-8859-1"), "UTF-8");
		// ��Ҫ��ϵ��
		String keyContacter=null;
		if (!request.getParameter("keyContacter").equals("null")) {
		     keyContacter= new String(request.getParameter("keyContacter").getBytes("iso-8859-1"), "UTF-8");
		}
		//�Ա� 
		String sex= request.getParameter("sex");
		// ����
		String dept= new String(request.getParameter("dept").getBytes("iso-8859-1"), "UTF-8");
		// ְ��
		String duty= new String(request.getParameter("duty").getBytes("iso-8859-1"), "UTF-8");
		// �칫�绰
		String mobilePhone= request.getParameter("mobilePhone");
		// �ֻ�
		String phone= request.getParameter("phone");
		// ӡ������
		String effectDescribe= new String(request.getParameter("effectDescribe").getBytes("iso-8859-1"), "UTF-8");
		// ��ѯ����
		String advisoryContent= new String(request.getParameter("advisoryContent").getBytes("iso-8859-1"), "UTF-8");
		// ��˾����ʱ��
		String setupTime = request.getParameter("setupTime");
		// �浵����
		String archiveTime = request.getParameter("archiveTime");
		// ������ʱ��
		Date lastModifiedTime = new Date();
	
		// ��ȡ��ǰ���ݱ������idֵ
		Object id1 = customerInfoService.findLastCustomerId();
		long id=Long.parseLong(String.valueOf(id1));
		//code�������IDֵ��ȡ��Ӧcode ����+1���� Ŀǰ��KH-000001�����ص�XM-999999
		String code = customerInfoService.getCustomerInfoById(id).getCode();
		if ( id>=0) {
			int b = Integer.parseInt(code.substring(3, 9)); //��ȡ��6λתΪint
            b=b+1;
			String str=String.format("%06d", b);
			code = "XM-".concat(str);
			System.out.println(code);
		}
         
		//��ȡ�ͻ�Id  �ͻ�����Ҳ��id��ʾ ��δ����
		long industry=0; //�ͻ�ID
		if(!request.getParameter("customerName").equals("null"))
		  {
			industry=Long.parseLong(request.getParameter("industry")); 
		  }else {
			industry=666;
		}
		 
		//����t_PersonnelFiles �е�ID ���ʵ�� �ٷֱ��ȡsalsman ����id�� ��businessmanId
		// ��������
		//	String parlorDept= ;
			// ҵ��Ա !!�����ȡ
         String personnelId=request.getParameter("saleman");
         PersonnelFiles personnelFiles=personnelFilesService.getPersonnelFilesById(Long.parseLong(personnelId));
         String saleman=personnelFiles.getName();		
		 Long businessmanId=personnelFiles.getId();
		 Long parlorDeptID =personnelFiles.getDeptId();//��ȡ�������ŵı��
		
		try {
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomerName(customerName);
			customerInfo.setCode(code); //code��������KH-999999
			customerInfo.setSetupTime(format1.parse(setupTime));
			customerInfo.setArchiveTime(format1.parse(archiveTime));
			customerInfo.setLastModifiedTime(lastModifiedTime);
			customerInfo.setCustomerType(Long.parseLong(customerType));
			customerInfo.setCompanyNature(Long.parseLong(companyNature));
			customerInfo.setIndustry(industry);
			customerInfo.setCountry(Long.parseLong(country));
			customerInfo.setProvince(Long.parseLong(province));
			customerInfo.setCity(Long.parseLong(city));
			customerInfo.setResource(Long.parseLong(resource));
			customerInfo.setWeb(web);
			customerInfo.setAddress(address);
			customerInfo.setBusinessScope(businessScope);
			customerInfo.setKeyContacter(keyContacter);
			customerInfo.setSex(sex);
			customerInfo.setDept(dept);
			customerInfo.setDuty(duty);
			customerInfo.setMobilePhone(mobilePhone);
			customerInfo.setPhone(phone);
			customerInfo.setEffectDescribe(effectDescribe);
			customerInfo.setAdvisoryContent(advisoryContent);
			customerInfo.setSaleman(saleman);
			customerInfo.setBusinessmanId(businessmanId); //�����Էǿ�
			customerInfo.setParlorDept(String.valueOf(parlorDeptID));
		
			// �����ֶβ���Ϊ��
			customerInfo.setStep(Long.parseLong("339"));// �ͻ��ȼ� Long �Զ���Ϊ1��
			customerInfo.setDisabled(Integer.parseInt("0"));
			customerInfo.setVersion(Long.parseLong("0"));
			customerInfo.setState(Long.parseLong("459")); //�̶�ֵ459
			
			customerInfoService.saveCustomerInfo(customerInfo);
			System.out.println("addcustomerInfo�洢��� ��˾����Ϊ:" + customerName);
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
