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
 * 创建客户信息页面
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
		// 获取项目列表
		List<CodeValue> codeValueList = new ArrayList<CodeValue>();
		Long id=(long) 2;  //获取cvId=2的集合
		codeValueList = codeValueService.getCodeValueByCvid(id);
		request.setAttribute("codeValueList", codeValueList);
		//获取人事信息列表
		List<PersonnelFiles> personnelFilesList=new ArrayList<PersonnelFiles>();
		personnelFilesList=personnelFilesService.getPersonnelFilesById();
	    request.setAttribute("personnelFilesList", personnelFilesList);	
		request.getRequestDispatcher("backVisit/addCustomerInfo.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//客户名称 
		String customerName=null;
		if (!request.getParameter("customerName").equals("null")) {
			customerName= new String(request.getParameter("customerName").getBytes("iso-8859-1"), "UTF-8");
		}
		// 客户状态 Long
		String customerType=request.getParameter("customerType") ;
		//企业性质  Long 
		String companyNature=request.getParameter("companyNature") ;
		// 行业 Long 
	//	String industry=request.getParameter("industry") ;
		// 国家Long 
		String country=request.getParameter("country") ;
		// 省份Long 
		String province=request.getParameter("province") ;
		// 城市Long 
		String city=request.getParameter("city") ;
		// 信息来源Long 
		String resource=request.getParameter("resource") ;
		// 公司网站
		String web= new String(request.getParameter("web").getBytes("iso-8859-1"), "UTF-8");
		// 地址
		String address= new String(request.getParameter("address").getBytes("iso-8859-1"), "UTF-8");
		// 业务范围
		String businessScope= new String(request.getParameter("businessScope").getBytes("iso-8859-1"), "UTF-8");
		// 主要联系人
		String keyContacter=null;
		if (!request.getParameter("keyContacter").equals("null")) {
		     keyContacter= new String(request.getParameter("keyContacter").getBytes("iso-8859-1"), "UTF-8");
		}
		//性别 
		String sex= request.getParameter("sex");
		// 部门
		String dept= new String(request.getParameter("dept").getBytes("iso-8859-1"), "UTF-8");
		// 职务
		String duty= new String(request.getParameter("duty").getBytes("iso-8859-1"), "UTF-8");
		// 办公电话
		String mobilePhone= request.getParameter("mobilePhone");
		// 手机
		String phone= request.getParameter("phone");
		// 印象描述
		String effectDescribe= new String(request.getParameter("effectDescribe").getBytes("iso-8859-1"), "UTF-8");
		// 咨询内容
		String advisoryContent= new String(request.getParameter("advisoryContent").getBytes("iso-8859-1"), "UTF-8");
		// 公司创建时间
		String setupTime = request.getParameter("setupTime");
		// 存档日期
		String archiveTime = request.getParameter("archiveTime");
		// 最后跟进时间
		Date lastModifiedTime = new Date();
	
		// 获取当前数据表中最大id值
		Object id1 = customerInfoService.findLastCustomerId();
		long id=Long.parseLong(String.valueOf(id1));
		//code根据最大ID值获取对应code 进行+1处理 目前从KH-000001最大加载到XM-999999
		String code = customerInfoService.getCustomerInfoById(id).getCode();
		if ( id>=0) {
			int b = Integer.parseInt(code.substring(3, 9)); //截取后6位转为int
            b=b+1;
			String str=String.format("%06d", b);
			code = "XM-".concat(str);
			System.out.println(code);
		}
         
		//获取客户Id  客户名称也用id表示 暂未解析
		long industry=0; //客户ID
		if(!request.getParameter("customerName").equals("null"))
		  {
			industry=Long.parseLong(request.getParameter("industry")); 
		  }else {
			industry=666;
		}
		 
		//根据t_PersonnelFiles 中的ID 获得实体 再分别获取salsman 部门id号 和businessmanId
		// 所属部门
		//	String parlorDept= ;
			// 业务员 !!联表获取
         String personnelId=request.getParameter("saleman");
         PersonnelFiles personnelFiles=personnelFilesService.getPersonnelFilesById(Long.parseLong(personnelId));
         String saleman=personnelFiles.getName();		
		 Long businessmanId=personnelFiles.getId();
		 Long parlorDeptID =personnelFiles.getDeptId();//获取所属部门的编号
		
		try {
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomerName(customerName);
			customerInfo.setCode(code); //code自增长到KH-999999
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
			customerInfo.setBusinessmanId(businessmanId); //该属性非空
			customerInfo.setParlorDept(String.valueOf(parlorDeptID));
		
			// 下面字段不能为空
			customerInfo.setStep(Long.parseLong("339"));// 客户等级 Long 自定义为1星
			customerInfo.setDisabled(Integer.parseInt("0"));
			customerInfo.setVersion(Long.parseLong("0"));
			customerInfo.setState(Long.parseLong("459")); //固定值459
			
			customerInfoService.saveCustomerInfo(customerInfo);
			System.out.println("addcustomerInfo存储完成 公司名称为:" + customerName);
			request.getRequestDispatcher("backVisit/result.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
