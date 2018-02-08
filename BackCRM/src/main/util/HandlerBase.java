package main.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.pojo.Department;
import main.pojo.EventNew;
import main.pojo.Message;
import main.pojo.News;
import main.pojo.NewsLog;
import main.pojo.PersonnelFiles;
import main.pojo.TleaveBill;
import main.service.EventService;
import main.service.MessageService;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.transform.Transformers;


/**
 * �¼���������࣬����һЩ��������
 * 
 * @author wangy
 * @version 1.0
 * @since 2017��9��5��
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HandlerBase {

	public EventService eventService;
	public MessageService messageService;
	public static Logger log = Logger.getRootLogger();
	
//     public static String domain = "http:///crm.itiane.com.cn/"; //���յ� ��ĿURL��
//       public static String domain = "http:///www.ahsme.cn/crm/"; // �����������ĿURL 
     public static String domain = "http:///www.cyfd.cn/tektcrm/";   //  �ͻ���ϵ�������� ��ĿURL
	
//	   public static String domain = "http:///y2k5vg.natappfree.cc/"; // �����˺� ��ĿURL
	    

	public Message message;
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
	DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat format3 = new SimpleDateFormat("yyyy��MM��dd��   HH:mm");

	public HandlerBase(EventService eventService, MessageService messageService) {
		this.eventService = eventService;
		this.messageService = messageService;
	}

	public void saveEventLog(EventNew event) {
		eventService.saveEventLog(event);
		eventService.deleteEvent(event);
	}
	

	public Map<String, Object> getMap(EventNew event) {
		Map<String, Object> map = new HashMap<String, Object>();
		String moreInfo = event.getMoreinfo();// �õ����ݵĲ�����������map
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		return map;
	}

	public void saveNew(String eventName, String url, Long id, String nowDate, String content) {
		News news = new News();
		news.setEventName(eventName);
		news.setUrl(url);
		news.setUserid(id);
		news.setCurrentDate(nowDate);
		news.setTime(Long.parseLong("1"));
		news.setIsRead("0");
		news.setMainContent(content);
		SQLUtil.saveNews(news);
	}
	
	
	public void saveNewLog(String eventName, String url, Long id, String nowDate, String content) {
		NewsLog newsLog = new NewsLog();
		newsLog.setEventName(eventName);
		newsLog.setUrl(url);
		newsLog.setUserid(id);
		newsLog.setCurrentDate(nowDate);
		newsLog.setTime(Long.parseLong("1"));
		newsLog.setIsRead("0");
		newsLog.setMainContent(content);
		SQLUtil.saveNewsLog(newsLog);
	}
	
	public void saveMessage(EventNew event,String title,String openid,String url,String keywords){
		message = new Message();
		message.setEventType(event.getEventType());
		message.setTitle(title);
		message.setOpenid(openid);
		message.setTime(new Date());
		message.setUrl(url);
		message.setKeywords(keywords);
		messageService.saveMessage(message);
	}

	public void qingJiaEvent(EventNew event) {
		Map<String, Object> map = getMap(event);
		String users = (String) map.get("users");
		String[] list = users.split(",");
		for (int i = 0; i < list.length; i++) {

		}
		saveEventLog(event);
	}

	// �Կ����ݽ����ÿմ���      20180109     ���Կ��Խ���ȡΪ�յ�����תΪ���ַ�������ֹǰ̨����messeng ������ɵ��¼��ж�
	private String isNull(String date){
		String dateString="";
		if (date!=null) {
			dateString = date;
		}
		return dateString;
	}
	
	
	
	/**
	 * �ط��¼�����
	 */
	public void publishNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String backVisitId = (String) map.get("backVisitId");
		String users = (String) map.get("users");
		 users = users.replaceAll(" ", "");
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getBackVisit(Integer.parseInt(backVisitId));
		String title = "����һ���ط���Ϣ";
		String stateName = SQLUtil.getNameById(map1.get("state"));
		String fankui = map1.get("feed") + "...";
		String projectName =map1.get("projectName");
		if (projectName==null) {
			projectName = "";
		}
		String fankui2 = fankui;
		if (fankui.length() > 40) {
			fankui2 = fankui.substring(0, 40) + "...";
		}
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String url = domain + "EventNoti/backVisitServlet?bid=" + backVisitId + "&userid="
					+ Long.parseLong(list[i]) + "&sender=" + sender;
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, fankui2);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, fankui2);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("visitor", map1.get("visitor"));
			keymap.put("projectName", projectName);
			keymap.put("customName", map1.get("customName"));
			keymap.put("stateName", stateName);
			keymap.put("fankui", fankui);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * ��Ŀ�ύ����
	 */
	public void publishProjectNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String projectInfoId = (String) map.get("projectInfoId");
		String users = (String) map.get("users");
		 users = users.replaceAll(" ", "");
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProject(Integer.parseInt(projectInfoId));
		String url = domain + "EventNoti/projectInfoServlet?projectInfoId=" + projectInfoId;
		String rapporteDate = format3.format(new Date());
		String projectName = map1.get("name");
		String title = "���ã�����һ��  ��" + projectName + "������Ŀ¼��";
		String customerId = map1.get("customerInfoId");
		Map<String, String> customerInfo = SQLUtil.getCustomerInfo(Integer.parseInt(customerId));
		String customerName = customerInfo.get("name");
		String contactId = map1.get("contactId");
		Map<String, String> contactMap = SQLUtil.getContactArchives(Integer.parseInt(contactId));
		String contactName = contactMap.get("contactName");
		String introduce = map1.get("introduce") + "..."; // ��Ŀ����
		String introduce2 = introduce;
		if (introduce.length() > 30) {
			introduce2 = introduce.substring(0, 30) + "...";
		}
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, introduce2);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, introduce2);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("customerName", customerName);
			keymap.put("contactName", contactName);
			keymap.put("creator", map1.get("creator"));
			keymap.put("rapporteDate", rapporteDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * 0714 ������ϵ���¼�����
	 */
	public void publishContactArchivesNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String contactId = (String) map.get("contactArchivesId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getContactArchives(Integer.parseInt(contactId));
		String url = domain + "EventNoti/contactArchivesServlet?contactId=" + contactId;
		String title = "����һ��������ϵ����Ϣ,��ע����գ�";
		String contactName = map1.get("contactName"); // ��ϵ��
		String customerName = map1.get("customerName"); // �ͻ�����
		String creatorMan = map1.get("creatorMan"); // ������
		String currentDate = format3.format(new Date()); // ��¼ʱ��
		String enterpriseSynopsis = map1.get("enterpriseSynopsis") + "..."; // ӡ������
		if (enterpriseSynopsis.length() > 30) {
			enterpriseSynopsis = enterpriseSynopsis.substring(0, 30) + "...";
		}
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();

			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, enterpriseSynopsis);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, enterpriseSynopsis);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("customerName", customerName);
			keymap.put("contactName", contactName);
			keymap.put("creatorMan", creatorMan);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * 0711 �����ͻ��¼�����
	 */
	public void publishCustomerInfoNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String customerInfoId = (String) map.get("customerInfoId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getCustomerInfo(Integer.parseInt(customerInfoId));
		String title = "����һ�������ͻ���Ϣ,��ע����գ�";
		String name = map1.get("name"); // �ͻ�����
		String maorContact = map1.get("maorContact"); // ��Ҫ��ϵ��
		String businessMan = map1.get("businessMan"); // ҵ��Ա
		String currentDate = format3.format(new Date()); // ��¼ʱ��
		String businessScope = map1.get("businessScope") + "..."; // ҵ��Χ
		String businessScope2 = businessScope;
		if (businessScope.length() > 30) {
			businessScope2 = businessScope.substring(0, 30) + "...";
		}
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String url = domain + "EventNoti/customerInfoServlet?customerId=" + customerInfoId+"&userid="+list[i]+"&tag=fromSearchCustInfo";
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, businessScope2);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, businessScope2);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("name", name);
			keymap.put("maorContact", maorContact);
			keymap.put("businessMan", businessMan);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}
	

	/**
	 * ��ͬ�¼� subiao 20170717
	 * 
	 * @throws ParseException
	 */
	public void publishContractNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String contractManagementId = (String) map.get("contractManagementId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getContactManagement(Integer.parseInt(contractManagementId));
		String title = "��ã�����һ����ͬǩ����Ϣ,��ע����ģ�";
		String CManagementName = map1.get("CManagementName"); // ��ͬ����
		String customerId = map1.get("customerId"); // �ͻ�id
		Map<String, String> map12 = SQLUtil.getCustomerInfo(Integer.parseInt(customerId));
		String customerName = map12.get("name"); // �ͻ�id
		String mainContent = "��ͬ���ƣ�&nbsp;" + CManagementName + "&nbsp;&nbsp;�ͻ����ƣ�&nbsp;" + customerName;
		String ciemdingTime = map1.get("ciemdingTime"); // ǩ������
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/contractManagementServlet?contractManagementId=" + contractManagementId;
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("CManagementName", CManagementName);
			keymap.put("customerName", customerName);
			keymap.put("ciemdingTime", ciemdingTime);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �տ��¼�
	 * @author subiao
	 * @serialData 20170718 financialManagementId
	 */
	
	public void publishFinancialNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String financialManagementId = (String) map.get("financialManagementId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getFinancialManagement(Integer.parseInt(financialManagementId));
		String title = "��ã�����һ���տ���Ϣ,��ע����ģ�";
		String CManagementId = map1.get("CManagementId"); // ��ͬid
		Map<String, String> map12 = SQLUtil.getContactManagement(Integer.parseInt(CManagementId));
		String CManagementName = map12.get("CManagementName"); // ��ͬ����
		String customerId = map1.get("customerId"); // �ͻ�id
		Map<String, String> map13 = SQLUtil.getCustomerInfo(Integer.parseInt(customerId));
		String customerName = map13.get("name"); // �ͻ�id
		String sumReceivable = map1.get("sumReceivable"); // Ӧ�ս��
		String trueSum = map1.get("trueSum"); // ʵ�ս��
		String collectionDate = map1.get("collectionDate"); // ǩ������
		String mainContent = "��ͬ���ƣ�&nbsp;" + CManagementName + "<br/> Ӧ�ս�&nbsp;" + sumReceivable
				+ "&nbsp;&nbsp;ʵ�ս�&nbsp;" + trueSum;
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/financialManagementServlet?financialManagementId=" + financialManagementId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("customerName", customerName);
			keymap.put("sumReceivable", sumReceivable);
			keymap.put("trueSum", trueSum);
			keymap.put("collectionDate", collectionDate);
			keymap.put("CManagementName", CManagementName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param ��Ʒ�¼�
	 * @author subiao
	 * @serialData 20170719 productsId
	 */
	public void publishProductsNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String productsId = (String) map.get("productsId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProductsManagement(Integer.parseInt(productsId));
		String title = "��ã�����һ���½���Ʒ��Ϣ,��ע����ģ�";
		String productName = map1.get("productName"); // ��Ʒ����
		String model = map1.get("model"); // �ͺ�
		String isNoMain2 = map1.get("isNoMain"); // �Ƿ���Ӫ 0 1
		String isNoMain = "��";
		if (isNoMain2.equals("0")) {
			isNoMain = "��";
		}
		String remark = map1.get("remark") + "...";
		if (remark.length() > 30) {
			remark = remark.substring(0, 30) + "...";
		}
		String launch = map1.get("launch"); // �Ƴ�ʱ��
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/productsServlet?productsId=" + productsId;
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("productName", productName);
			keymap.put("model", model);
			keymap.put("isNoMain", isNoMain);
			keymap.put("launch", launch);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �ܼƻ��¼�
	 * @author subiao
	 * @serialData 20170728 weeklyId
	 */
	public void publishWeekPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String weeklyId = (String) map.get("weeklyId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		// userId ȥ���ظ� Ŀǰ���͸�ȫԱ
		Map<String, String> map1 = SQLUtil.getWeeklyById(Integer.parseInt(weeklyId));
		String title = "��ã�����һ���½��ܼƻ���Ϣ,��ע����ģ�";
		String weeklyName = map1.get("weeklyName"); // �ܼƻ�����
		String startDate = map1.get("startDate"); // ��ʼ����
		String endDate = map1.get("endDate"); // ��������
		String rapporteurId = map1.get("rapporteurId"); // �ƻ���
		String summary = map1.get("summary") + "..."; // ���ܽ�
		if (summary.length() > 30) {
			summary = summary.substring(0, 30) + "...";
		}
		String rapporteurName = SQLUtil.getUserDetail(Integer.parseInt(rapporteurId)).getName();
		String rapporteDate = format3.format(new Date());
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// Ϊ��ֱ�Ӵ�ֵ ��������
			Long userid = Long.parseLong(list[i]);
			String url = domain + "EventNoti/weeklyServlet?weeklyId=" + weeklyId + "&userid=" + userid + "&sender="
					+ sender;
			System.out.println("weeklyName, startDate, rapporteurName, rapporteDate :" + weeklyName + "  " + startDate
					+ "  " + rapporteurName + "  " + rapporteDate);
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, summary);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, summary);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("weeklyName", weeklyName);
			keymap.put("startDate", startDate);
			keymap.put("endDate", endDate);
			keymap.put("rapporteurName", rapporteurName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �����ƻ��¼�
	 *            ����Ŀ ��ͬ��
	 * @author subiao
	 * @serialData 20170801 projectInfoPlanId
	 */
	public void publishProjectInfoPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String sender = event.getUserId();
		String nowDate = format.format(new Date());
		String projectInfoPlanId = (String) map.get("projectInfoPlanId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProjectInfoPlanById(Integer.parseInt(projectInfoPlanId));
		String title = "��ã�����һ���½������ƻ���Ϣ,��ע����ģ�";
		String planName = map1.get("planName"); // �ƻ�����
		String rapporteurName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName(); // �ظ���
		String percent = map1.get("percent").toString()+"%"; // ��ɰٷֱ�
		String outline = "��ǰ�ƻ������ �� " + percent ;
		String dayNum = map1.get("dayNum"); // ������ ����
		String endDate = map1.get("endDate"); // ʱ��
		if("100".equals(map1.get("percent"))){
			remindNewPlan(projectInfoPlanId);
		}
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// Ϊ��ֱ�Ӵ�ֵ ��������
			String url = domain + "EventNoti/projectInfoPlanServlet?projectInfoPlanId=" + projectInfoPlanId
					+ "&userid=" + Long.parseLong(list[i]) + "&sender=" + sender; 

			// �����¼�����Ϣ����****
			saveNew(planName, url, Long.parseLong(list[i]), nowDate, outline);
			saveNewLog(planName, url, Long.parseLong(list[i]), nowDate, outline);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("planName", planName);
			keymap.put("dayNum", dayNum);
			keymap.put("outline", outline);
			keymap.put("endDate", endDate);
			keymap.put("rapporteurName", rapporteurName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �����ƻ�
	 *            ״̬�����¼� ����Ŀ ��ͬ��
	 * @author subiao
	 * @serialData 20170810 projectInfoPlanId
	 */
	public void publishGGZTProjectInfoPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String sender = event.getUserId();
		String nowDate = format.format(new Date());
		String projectInfoPlanId = (String) map.get("projectInfoPlanId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProjectInfoPlanById(Integer.parseInt(projectInfoPlanId));
		String planName = map1.get("planName"); // �ƻ�����
		String percent = map1.get("percent").toString()+"%"; // ��ɰٷֱ�
		String outline = "��ǰ�ƻ������ �� " + percent;
		String title = "��ã�����һ�������ƻ�״̬������Ϣ,��ע����ģ�";
		String dayNum = map1.get("dayNum"); // ������ ����
		String endDate = map1.get("endDate"); // ʱ��
		String rapporteurName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName(); // �ظ���
		if("100".equals(map1.get("percent"))){
			remindNewPlan(projectInfoPlanId);
		}
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// Ϊ��ֱ�Ӵ�ֵ ��������
			String url = domain + "EventNoti/projectInfoPlanServlet?projectInfoPlanId=" + projectInfoPlanId
					+ "&userid=" + Long.parseLong(list[i]) + "&sender=" + sender;
			System.out.println("planName, rapporteurName, dayNum :" + planName + "  " + rapporteurName + "  " + dayNum);
			// �����¼�����Ϣ����****
			saveNew(planName, url, Long.parseLong(list[i]), nowDate, outline);
			saveNewLog(planName, url, Long.parseLong(list[i]), nowDate, outline);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("planName", planName);
			keymap.put("dayNum", dayNum);
			keymap.put("outline", outline);
			keymap.put("endDate", endDate);
			keymap.put("rapporteurName", rapporteurName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}
	
	public void remindNewPlan(String projectInfoPlanId){
		String ppsql = "SELECT projectInfo_id , contractManagement_id , productionOperationDetail FROM t_projectInfoPlan where id = "+projectInfoPlanId;
		List<Map> ppLists =(List<Map>) eventService.getSuperSession().createSQLQuery(ppsql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(ppLists != null && ppLists.size() >0 ){
            //  2018-01-25 �޸� 
			//	String psql = "select u.ID as uid , convert(varchar(255),pp.name ) as name , pp.start_Date as start from t_projectInfoPlan pp,t_personnelFiles pf,t_users u where pp.personnelFiles_id = pf.Id and pf.CODE =u.CODE and PROJECTINFOPLAN_ID = "+projectInfoPlanId;
			String psql = "select u.ID as uid , convert(varchar(255),pp.name ) as name , pp.start_Date as start from t_projectInfoPlan pp,t_personnelFiles pf,t_users u where pp.personnelFiles_id = pf.Id and pf.CODE =u.CODE and pp.id = "+projectInfoPlanId;
			List<Map> mapLists =(List<Map>) eventService.getSuperSession().createSQLQuery(psql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(ppLists.get(0).get("projectInfo_id")!=null){
				if(mapLists!=null && mapLists.size()>0){
					for (int j = 0; j < mapLists.size(); j++) {
						BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10018'").uniqueResult();
						String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'��Ŀ�ƻ���ʼ����','{\"users\":\"" + mapLists.get(j).get("uid") + "\",\"content\":\""+ "����Ŀ�ƻ�:"+ mapLists.get(j).get("name")+ "("+ format2.format((Date)mapLists.get(j).get("start"))+ ")���Կ�ʼ" + "\"}','E','0')";
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					}
				}
			}else if(ppLists.get(0).get("productionOperationDetail")!=null){
				if(mapLists!=null && mapLists.size()>0){
					for (int j = 0; j < mapLists.size(); j++) {
						BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10036'").uniqueResult();
						String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'������Ӫ�ƻ���ʼ����','{\"users\":\"" + mapLists.get(j).get("uid") + "\",\"content\":\""+ "��������Ӫ�ƻ�:"+ mapLists.get(j).get("name")+ "("+ format2.format((Date)mapLists.get(j).get("start"))+ ")���Կ�ʼ" + "\"}','E','0')";
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					}
				}
			}else {
				if(mapLists!=null && mapLists.size()>0){
					for (int j = 0; j < mapLists.size(); j++) {
						BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10020'").uniqueResult();
						String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'��ͬ�ƻ���ʼ����','{\"users\":\"" + mapLists.get(j).get("uid") + "\",\"content\":\""+ "�к�ͬ�ƻ�:"+ mapLists.get(j).get("name")+ "("+ format2.format((Date)mapLists.get(j).get("start"))+ ")���Կ�ʼ" + "\"}','E','0')";
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					}
				}
			}
		}
		
	}
	

	/**
	 * @param ��Ʊ�¼�
	 * @author subiao
	 * @serialData 20170803 billingRecordId
	 */
	public void publishBillingRecordNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String billingRecordId = (String) map.get("billingRecordId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getBillingRecordById(Integer.parseInt(billingRecordId));
		String title = "��ã�����һ����Ʊ��Ϣ,��ע����ģ�";
		String sum = map1.get("sum") + " Ԫ"; // ��Ʊ���
		String invoiceTitle = " "; // ��Ʊ̧ͷ
		if (invoiceTitle != null) {
			invoiceTitle = map1.get("invoiceTitle"); // ��Ʊ̧ͷ
		}
		String customerInfoId = map1.get("customerInfoId"); // ��ͬID
		Map<String, String> customerInfoMap = SQLUtil.getCustomerInfo(Integer.parseInt(customerInfoId)); // ��ȡ��ͬ��Ϣ
		String customerName = customerInfoMap.get("name"); // ��ͬ����
		
		String content = map1.get("content") + "..."; // ��Ʊ����
		if (content.length() > 30) {
			content = content.substring(0, 30) + "...";
		}
		
		String rapporteDate = format3.format(new Date()); // ��Ʊʱ��
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/billingRecordServlet?billingRecordId=" + billingRecordId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, content);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();   
			keymap.put("customerName", customerName);
			keymap.put("sum", sum);
			keymap.put("invoiceTitle", invoiceTitle);
			keymap.put("rapporteDate", rapporteDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �����¼�
	 * @author subiao
	 * @serialData 20170803 paymentorderId
	 */
	public void publishPaymentOrderNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String paymentorderId = (String) map.get("paymentorderId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		// userId ȥ���ظ� Ŀǰ���͸�ȫԱ
		Map<String, String> map1 = SQLUtil.getPaymentOrderById(Integer.parseInt(paymentorderId));
		String title = "��ã�����һ��������Ϣ����ע����ģ�";
		String totalMoney = map1.get("totalMoney") + " Ԫ"; // ������
		String supplierId = map1.get("supplierId"); // ������ID
		String supplierName = "";
		String maorContact = "";
		String mobile = "";
		if (supplierId != null) {
			Map<String, String> supplierMap = SQLUtil.getSupplierById(Integer.parseInt(supplierId)); // ��ȡ��ͬ��Ϣ
			supplierName = supplierMap.get("supplierName"); // ����������
			String maorContact2 = supplierMap.get("maorContact"); // ��Ҫ��ϵ��
			if (maorContact2 != null) {
				maorContact = maorContact2;
			}
			String mobile2 = supplierMap.get("mobile"); // �ֻ���
			if (mobile2 != null) {
				mobile = mobile2;
			}
		}
		String remark = map1.get("remark") + "..."; // ��ע
		if (remark.length() > 30) {
			remark = remark.substring(0, 30) + "...";
		}
		String mes = "��ϵ�ˣ� " + maorContact + "  �ֻ��ţ� " + mobile;
		String rapporteDate = format3.format(new Date()); // ��Ʊʱ��
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/paymentOrderServlet?paymentorderId=" + paymentorderId;
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("supplierName", supplierName);
			keymap.put("totalMoney", totalMoney);
			keymap.put("rapporteDate", rapporteDate);
			keymap.put("mes", mes);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �������ύ�¼�
	 * @author subiao
	 * @serialData 20170815 expenseFormId
	 */
	public void publishExpenseFormNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String expenseFormId = (String) map.get("expenseFormId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getExpenseFormById(Integer.parseInt(expenseFormId));
		String title = "���ã����µı�������Ҫ��������";
		String money = map1.get("money") + " Ԫ"; // �������
		String personnelId = map1.get("personnelId"); // ������ID
		String applyPeopleName = "";
		if (personnelId != null) {
			applyPeopleName = SQLUtil.getPersonnelFilesById(Integer.parseInt(personnelId)).getName(); // ������
		}
		String projectId = map1.get("projectId"); // ��ĿID
		String projectName = "";
		if (projectId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getProject(Integer.parseInt(projectId)); // ��Ŀ��Ϣ
			projectName = projectInfoMap.get("name"); // ��ͬ����
		}
		
		String remark = map1.get("remark") + "..."; // ��ע
		if (remark.length() > 30) {
			remark = remark.substring(0, 30) + "...";
		}
		String rapporteDate = format3.format(new Date()); // ����ʱ��
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/expenseFormServlet?expenseFormId=" + expenseFormId;
		System.out.println("projectName,applyPeopleName,money,  rapporteDate :" + projectName + "  "
				+ applyPeopleName + "  " + money + "  " + rapporteDate);
		// �����¼�����Ϣ����****
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("projectName", projectName);
			keymap.put("rapporteDate", rapporteDate);
			keymap.put("applyPeopleName", applyPeopleName);
			keymap.put("money", money);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * ���̼�鴦�� wy 20170613
	 */
	public void spaceCheckerNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("content");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		String url = "#";
		String title = "����һ�����̿ռ���Ϣ";
		String currentDate = format.format(new Date());
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			saveNew(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			keymap.put("eventName", event.getName());
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * �ձ���鴦�� wy 20170613
	 */
	public void dailyCheckNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("content");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		String title = "����һ���ձ�������Ϣ";
		String currentDate = format.format(new Date());
		int state = content.length();
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			String userid =list[i].toString();
			System.out.println("�����ˣ�" + userid);
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String url = domain + "EventNoti/dailyCheckServlet?state=" + state+"&userid="+userid;
			saveNew(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			keymap.put("eventName", event.getName());
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * �ձ���Ϣ����
	 */
	public void publishDailyNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String eventName = event.getName();
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String dailyId = (String) map.get("dailyId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getDaily(Integer.parseInt(dailyId));
		String title = "���ã�������ع����ձ�¼��ɹ�";
		String currentDate = format.format(new Date());
		String workContext = map1.get("workContext") + "..."; // ���칤������
		String workContext2 = workContext;
		if (workContext.length() > 30) {
			workContext2 = workContext.substring(0, 30) + "...";
		}
		
		// ��ȡ�û���
		String rapporteurId = map1.get("rapporteurId");
		String userName = SQLUtil.getUserDetail(Integer.parseInt(rapporteurId)).getName();
		String questions = map1.get("questions"); // ���� �����롢�ջ� ��Ӧģ���еı�ע
		String questions2 = questions;
		if (questions2.length() > 30) {
			questions2 = questions2.substring(0, 30) + "...";
		}
		for (int i = 0; i < list.length && !list.equals(""); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			Long userid = Long.parseLong(list[i]);
			String url = domain + "EventNoti/dailyServlet?dailyId=" + dailyId + "&userid=" + userid + "&sender="
					+ sender;
			// �����¼�����Ϣ����
			saveNew(eventName, url, Long.parseLong(list[i]), nowDate, workContext2);
			saveNewLog(eventName, url, Long.parseLong(list[i]), nowDate, workContext2);
			// ���浽Message����
			Map<String, String> keymap = new HashMap();
			keymap.put("workContext2", workContext2);
			keymap.put("currentDate", currentDate);
			keymap.put("userName", userName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * �������� �ݷ����� ��Ŀ�ƻ���ʼ���� ��Ŀ�ƻ��������� ��ͬ�ƻ���ʼ���� ��ͬ�¼��������� ��ͬ��������     20170823
	 * ϵͳ��������¼�
	 */
	
	
	public void systemRemindCheckNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("content");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		String currentDate = format.format(new Date());
		String remindName = event.getName();
		// ���� ���͵�ϵͳ����ҳ�� systemRemind
		String url = domain + "EventNoti/backVisit/systemRemind.jsp?content=" + content + "&currentDate="
				+ currentDate + "&remindName=" + remindName;
		String title = "���ã�����һ��" + remindName;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	
	/**
	 * ����ƻ��ͺ������� subiao 20170830 ��ʱʹ��ϵͳ���ģ�� ϵͳ���
	 */
	public void systemWorkPlanLagNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("name");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", "");
		String[] list = users.split(",");
		String currentDate = format.format(new Date());
		String projectInfoPlanId = (String) map.get("projectInfoPlanId");
		String projectInfoPlanName = "";
		if (projectInfoPlanId != null) {
			projectInfoPlanName = SQLUtil.getProjectInfoPlanById(Long.parseLong(projectInfoPlanId)).getName();
		}
		String remindName = event.getName();
		String sender = event.getUserId();
		String title = "���ã�����һ��" + remindName;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String userid = list[i];
			// ���� ���͵�ϵͳ����ҳ�� systemRemind
			String url = domain + "EventNoti/backVisit/workPlanLag.jsp?content=" + content + "&currentDate="
					+ currentDate + "&remindName=" + remindName + "&projectInfoPlanId=" + projectInfoPlanId
					+ "&projectInfoPlanName=" + projectInfoPlanName + "&userid=" + userid + "&sender=" + sender;
			// �����¼�����Ϣ����
			saveNew(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * �ձ���Ϣ�ظ� ��������������ģ�� subiao 20170902
	 */
	public void publishReplyNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", "");
		String[] list = users.split(",");
		String currentDate = format.format(new Date());
		String username = (String) map.get("username");
		String sender = event.getUserId();
		String deptId = SQLUtil.getUserDetail(Integer.parseInt(sender)).getDeptid();
		String deptName = SQLUtil.getDeptById(Long.parseLong(deptId)).getName();
		String title = "";
		String url = "";
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String userid = list[i];
			// ���� ���͵��ձ���ʾҳ�� systemRemind
			if (map.get("dailyId") != null) {
				String dailyId = (String) map.get("dailyId");
				title = "���ã������µ��ձ��ظ���Ϣ";
				url = domain + "/EventNoti/dailyServlet?dailyId=" + dailyId + "&userid=" + userid + "&sender=" + sender;
			}
			if (map.get("customerInfoId") != null) {
				String customerInfoId = (String) map.get("customerInfoId");
				title = "���ã������µĿͻ���Ϣ�ظ���Ϣ";
				url = domain + "/EventNoti/customerInfoServlet?customerId=" + customerInfoId + "&userid=" + userid
						+ "&sender=" + sender + "&tag=fromSearchCustInfo";
			}
			if (map.get("backVisitId") != null) {
				String backVisitId = (String) map.get("backVisitId");
				title = "���ã������µĻطûظ���Ϣ";
				url = domain + "/EventNoti/backVisitServlet?bid=" + backVisitId + "&userid=" + userid
						+ "&sender=" + sender ;
			}
			if (map.get("weeklyId") != null) {
				String weeklyId = (String) map.get("weeklyId");
				title = "���ã������µ��ܼƻ��ظ���Ϣ";
				url = domain + "/EventNoti/weeklyServlet?weeklyId=" + weeklyId + "&userid=" + userid
						+ "&sender=" + sender ;
			}
			
			if (map.get("projectInfoPlanId") != null) {
				String projectInfoPlanId = (String) map.get("projectInfoPlanId");
				title = "���ã������µĹ����ƻ��ظ���Ϣ";
				url = domain + "/EventNoti/projectInfoPlanServlet?projectInfoPlanId=" + projectInfoPlanId + "&userid=" + userid
						+ "&sender=" + sender ;
			}

			Map<String, String> keymap = new HashMap();
			keymap.put("currentDate", currentDate);
			keymap.put("deptName", deptName);
			keymap.put("username", username);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �Ӱ൥�ύ�¼�
	 * @author subiao
	 * @serialData 20170825 overTimeBillId
	 */
	public void publishOverTimeBillNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String overTimeBillId = (String) map.get("overTimeBillId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		// String[] list = users.split(",");
		// userId ȥ���ظ� Ŀǰ���͸�ȫԱ
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getOverTimeBillById(Integer.parseInt(overTimeBillId));
		String title = "���ã�����һ�ݼӰ൥������";
		String style = "�Ӱ൥�ύ����";
		String applyPersonId = map1.get("applyPersonId"); // ������ID
		String applyPeopleName = "";
		if (applyPersonId != null) {
			applyPeopleName = SQLUtil.getPersonnelFilesById(Integer.parseInt(applyPersonId)).getName(); // ������
		}
		
		String betreffzeile = map1.get("betreffzeile") + "..."; // ��ע
		if (betreffzeile.length() > 30) {
			betreffzeile = betreffzeile.substring(0, 30) + "...";
		}
		
		String rapporteDate = format3.format(new Date()); // ����ʱ��
		
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/overTimeBillServlet?overTimeBillId=" + overTimeBillId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			System.out.println("overTimeBillId  :" + overTimeBillId);
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, betreffzeile);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, betreffzeile);
		
			Map<String, String> keymap = new HashMap();
			keymap.put("style", style);
			keymap.put("applyPeopleName", applyPeopleName);
			keymap.put("rapporteDate", rapporteDate);
			keymap.put("betreffzeile", betreffzeile);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �������ύ�¼�
	 * @author subiao
	 * @serialData 20170826 onTheRoadBillId
	 */
	public void publishOnTheRoadBillNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String onTheRoadBillId = (String) map.get("onTheRoadBillId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getOnTheRoadBillById(Integer.parseInt(onTheRoadBillId));
		String title = "���ã�����һ�ݹ�����������";
		String style = "�������ύ����";
		String applyPersonId = map1.get("applyPersonId"); // ������ID
		String applyPeopleName = "";
		if (applyPersonId != null) {
			applyPeopleName = SQLUtil.getPersonnelFilesById(Integer.parseInt(applyPersonId)).getName(); // ������
		}
		String betreffzeile = map1.get("betreffzeile") + "..."; // ��ע
		if (betreffzeile.length() > 30) {
			betreffzeile = betreffzeile.substring(0, 30) + "...";
		}
		String rapporteDate = format3.format(new Date()); // ����ʱ��
		// Ϊ��ֱ�Ӵ�ֵ ��������
		String url = domain + "EventNoti/onTheRoadBillServlet?onTheRoadBillId=" + onTheRoadBillId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, betreffzeile);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, betreffzeile);
			
			Map<String, String> keymap = new HashMap();
			keymap.put("style", style);
			keymap.put("applyPeopleName", applyPeopleName);
			keymap.put("rapporteDate", rapporteDate);
			keymap.put("betreffzeile", betreffzeile);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param ����ύ�¼�
	 * @author subiao
	 * @serialData 20170906 leaveBillId
	 */
	public void publishQingjiaSubmitNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String leaveBillId = (String) map.get("leaveBillId");

		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		String qingjiaName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName();
		TleaveBill tleaveBill = SQLUtil.getQingJia(Integer.parseInt(leaveBillId));
		String type = tleaveBill.getType();
		String typeName = SQLUtil.getNameById(type);
		Date startDate = tleaveBill.getStartDate();
		Date endDate = tleaveBill.getEndDate();
		String time = format.format(startDate) + " ��  " + format.format(endDate);
		String days = tleaveBill.getMainHour().toString()+" Сʱ ";
		String yuanyin = tleaveBill.getBetreffzeile();
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			int shenheId = Integer.parseInt(list[i]);
			String openid = SQLUtil.getUserDetail(shenheId).getOpenid();
			// String style = "����ύ����";
			String title = "�װ���" + SQLUtil.getUserDetail(shenheId).getName() + "�������µ��������";
			String url = domain + "EventNoti/qingjiaSuccessServlet?leavebillId=" + leaveBillId + "&shenheId="
					+ shenheId + "&qingjiaId=" + sender;
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, yuanyin);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, yuanyin);
			
			Map<String, String> keymap = new HashMap();
			// keymap.put("style", style);
			keymap.put("qingjiaName", qingjiaName);
			keymap.put("typeName", typeName);
			keymap.put("time", time);
			keymap.put("days", days);
			keymap.put("yuanyin", yuanyin);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}

	/**
	 * @param �����������¼�
	 * @author subiao
	 * @serialData 20170906 leaveBillId
	 */
	public void publishQingjiaResultNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String shenheId = event.getUserId();
		String leaveBillId = (String) map.get("leaveBillId");

		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", "");
		String[] list = users.split(",");
		String shenheName = SQLUtil.getUserDetail(Integer.parseInt(shenheId)).getName();
		TleaveBill tleaveBill = SQLUtil.getQingJia(Integer.parseInt(leaveBillId));
		String type = tleaveBill.getType();
		String typeName = SQLUtil.getNameById(type);
		Date startDate = tleaveBill.getStartDate();
		Date endDate = tleaveBill.getEndDate();
		String time = format.format(startDate) + " ��  " + format.format(endDate);
		String yuanyin = tleaveBill.getBetreffzeile();
		String status = tleaveBill.getStatus();
		String jieguo = "";
		if (status.equals("1")) {
			jieguo = "ͬ��";
		} else {
			jieguo = "����";
		}
		
		// String style = "����ύ����";
		String url = domain + "EventNoti/resultServlet?leaveBillId=" + leaveBillId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			int qingjiaId = Integer.parseInt(list[i]);
			String openid = SQLUtil.getUserDetail(qingjiaId).getOpenid();
			System.out.println("leaveBillId  :" + leaveBillId);

			String qingjiaName = SQLUtil.getUserDetail(qingjiaId).getName();
			String title = "�װ���" + qingjiaName + "�������������������";
			System.out.println("qingjiaName, typeName   time :" + qingjiaName + "  " + typeName + "  " + time);
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, yuanyin);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, yuanyin);
			
			Map<String, String> keymap = new HashMap();
			// keymap.put("style", style);
			keymap.put("shenheName", shenheName);
			keymap.put("typeName", typeName);
			keymap.put("time", time);
			keymap.put("jieguo", jieguo);
			keymap.put("yuanyin", yuanyin);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}
	

	/**
	 * @param ��Ŀ�ܼƻ�
	 *            �ύ�¼�
	 * @author subiao
	 * @serialData 20170828 weekPlanId
	 */
	public void publishProjectWeekPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String weekPlanId = (String) map.get("weekPlanId"); // ��Ŀ�ܼƻ���IDֵ
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getWeekPlanById(Integer.parseInt(weekPlanId));
		String title = "���ã�����һ����Ŀ�ܼƻ���Ϣ¼��֪ͨ";
		String sender = event.getUserId();
		String userName = "";
		if (sender != null) {
			userName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName(); // ������
		}
		String thisPlan = map1.get("thisPlan"); // ���ܼƻ�
		String weeklyId = map1.get("weeklyId"); // �ܼƻ�ID
		String projectId = map1.get("projectInfoId"); // ��ĿID
		String projectName = "";
		String customerInfoId = null;
		String customerInfoName = "";
		String contactId = null;
		String contactName = "";
		if (projectId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getProject(Integer.parseInt(projectId)); // ��Ŀ��Ϣ
			projectName = projectInfoMap.get("name"); // ��ĿID
			customerInfoId = projectInfoMap.get("customerInfoId"); // �ͻ�id
			contactId = projectInfoMap.get("contactId"); // ��ϵ��id
			
		}
		if (customerInfoId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getCustomerInfo(Integer.parseInt(customerInfoId)); // �ͻ���Ϣ
			customerInfoName = projectInfoMap.get("name");
		}
		if (contactId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getContactArchives(Integer.parseInt(contactId)); // ��ϵ����Ϣ
			contactName = projectInfoMap.get("contactName");
		}
		
		String rapporteDate = format3.format(new Date()); // ����ʱ��
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// Ϊ��ֱ�Ӵ�ֵ ��������
			Long userid = Long.parseLong(list[i]);
			String url = domain + "EventNoti/listWeeklyPlanServlet?weeklyId=" + weeklyId + "&userid=" + userid
					+ "&sender=" + sender + "&weekPlanId=" + weekPlanId + "&projectId=" + projectId + "&projectName="
					+ projectName + "&state=FromProject";
			// �����¼�����Ϣ����****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, thisPlan);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, thisPlan);
			
			Map<String, String> keymap = new HashMap();
			keymap.put("customerInfoName", customerInfoName);
			keymap.put("contactName", contactName);
			keymap.put("userName", userName);
			keymap.put("rapporteDate", rapporteDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}
	
	/**
	 * @param ������֪ͨ  �ύ�¼�
	 * @author subiao
	 * @serialData 20180112  runTaskId
	 */
	
	public void publishRunTaskPendingNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String runTaskId = (String) map.get("runTaskId"); // ��������id
		String historyTaskinstId = (String) map.get("historyTaskinstId");  // ��ʷ����id
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		
		Map<String, String> map1 = SQLUtil.getRunTask(Integer.parseInt(runTaskId)); // ��ȡ��������Ϣ
		String flowId = map1.get("flowId"); // ��������id �����г�����id �ж�ȥ����ͬ����ҳ�� -- ���� ���������ͨ��ҳ��
		String submitPerId = map1.get("submitPerId"); // ������id
		String createTime = map1.get("createTime"); // ����ʱ��
		PersonnelFiles pFiles = SQLUtil.getPersonnelFilesById(Integer.parseInt(submitPerId));
		String submitPerName = pFiles.getName(); // ����������
		Long deptId = pFiles.getDeptId();
		Department dept = SQLUtil.getDeptById(deptId);
		String deptName = dept.getName(); // �����˲������� 
		String name = (String)map.get("name"); // 2018-01-11,�����ύ������Ϊ�ó���������
		
		String title = "���ã�����һ������������";
//		String sender = event.getUserId();  // ���̽ڵ�ķ����� ����������
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// Ϊ��ֱ�Ӵ�ֵ ��������
//			Long userid = Long.parseLong(list[i]);  // �������û�id
	        // 0112 �������ӵ�ַ���� 
//			String url = domain + "EventNoti/runTaskServlet?runTaskId=" + runTaskId + "&userid=" + userid+"&sender="+sender ;
			String url = domain + "EventNoti/runtaskServlet?method=detail&id=" + runTaskId +"&historyTaskinstId="+historyTaskinstId;
			
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// �����¼���  ��ʷ ��Ϣ����****
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// �����¼���  ��ʷ ��Ϣ����****
			
			Map<String, String> keymap = new HashMap();
			keymap.put("submitPerName", submitPerName);
			keymap.put("deptName", deptName);
			keymap.put("name", name);
			keymap.put("createTime", createTime);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}
	
	/**
	 * @param  �������֪ͨ  �ύ�¼�
	 * @author subiao
	 * @serialData 20180112  runTaskId��δ�ã�  runPointId
	 */
	
	public void publishRunTaskResultNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
//		String runTaskId = (String) map.get("runTaskId"); // ��������id
		String runPointId = (String) map.get("runPointId"); // �����ڵ�id
		String historyTaskinstId = (String) map.get("historyTaskinstId"); // ��ʷ������Ϣ  
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // �����Ƿ��пո�ǿ��ȥ��
		String[] list = users.split(",");
		
		Map<String, String> map1 = SQLUtil.getRunPoint(Integer.parseInt(runPointId)); // ��ȡ �����ڵ� ��Ϣ
		String flowId = map1.get("flowId"); // ��������id �����г�����id �ж�ȥ����ͬ����ҳ�� -- ���� ���������ͨ��ҳ��
		String inspectPserId = map1.get("inspectPserId"); // ������id
		String createTime = map1.get("createTime"); // ����ʱ��
		String remark =map1.get("remark"); // ������� 
		if ("null".equals(remark)) {
			remark = "";
		}
		String resultId = map1.get("resultId"); // ������� id
		String resultName =SQLUtil.getCodeValueName(Integer.parseInt(resultId));
		
		Map<String, String> map2 =SQLUtil.getPoint(Integer.parseInt(flowId));
		String flowTypeName = map2.get("name");
		PersonnelFiles pFiles = SQLUtil.getPersonnelFilesById(Integer.parseInt(inspectPserId));
		String inspectPserName = pFiles.getName(); // ����������
		
		String name = (String)map.get("name"); // 2018-01-11,�����ύ������Ϊ�ó���������
		
//		String title = "���ã�����һ���������������";
		String title = "���ã�����һ�������������Ϣ";
		String sender = event.getUserId();  // ���̽ڵ�ķ����� ����������
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("�����ˣ�" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// Ϊ��ֱ�Ӵ�ֵ ��������
			Long userid = Long.parseLong(list[i]);  // �������û�id
			// 0112 �������ӵ�ַ���� 
//			String url = domain + "EventNoti/runPointServlet?runPointId=" + runPointId + "&userid=" + userid+"&sender="+sender ;
			String url = domain + "EventNoti/historyTaskinstServlet?method=detail&id=" + historyTaskinstId ;
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// �����¼���  ��ʷ ��Ϣ����****
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// �����¼���  ��ʷ ��Ϣ����****
			
			Map<String, String> keymap = new HashMap();
			keymap.put("flowTypeName", flowTypeName);
			keymap.put("inspectPserName", inspectPserName);
			keymap.put("resultName", resultName);
			keymap.put("remark", remark);
			keymap.put("createTime", createTime);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// ����log��־
	}
	

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
