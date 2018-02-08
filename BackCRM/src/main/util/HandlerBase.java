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
 * 事件处理基础类，包含一些基础数据
 * 
 * @author wangy
 * @version 1.0
 * @since 2017年9月5日
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HandlerBase {

	public EventService eventService;
	public MessageService messageService;
	public static Logger log = Logger.getRootLogger();
	
//     public static String domain = "http:///crm.itiane.com.cn/"; //天鹅空调 项目URL、
//       public static String domain = "http:///www.ahsme.cn/crm/"; // 永君服务号项目URL 
     public static String domain = "http:///www.cyfd.cn/tektcrm/";   //  客户关系管理服务号 项目URL
	
//	   public static String domain = "http:///y2k5vg.natappfree.cc/"; // 测试账号 项目URL
	    

	public Message message;
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
	DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat format3 = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");

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
		String moreInfo = event.getMoreinfo();// 得到传递的参数，解析成map
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

	// 对空数据进行置空处理      20180109     测试可以将获取为空的数据转为空字符串来防止前台处理messeng 数据造成的事件中断
	private String isNull(String date){
		String dateString="";
		if (date!=null) {
			dateString = date;
		}
		return dateString;
	}
	
	
	
	/**
	 * 回访事件处理
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
		String title = "您有一条回访消息";
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
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String url = domain + "EventNoti/backVisitServlet?bid=" + backVisitId + "&userid="
					+ Long.parseLong(list[i]) + "&sender=" + sender;
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, fankui2);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, fankui2);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("visitor", map1.get("visitor"));
			keymap.put("projectName", projectName);
			keymap.put("customName", map1.get("customName"));
			keymap.put("stateName", stateName);
			keymap.put("fankui", fankui);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 项目提交处理
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
		String title = "您好，您有一条  ‘" + projectName + "’　项目录入";
		String customerId = map1.get("customerInfoId");
		Map<String, String> customerInfo = SQLUtil.getCustomerInfo(Integer.parseInt(customerId));
		String customerName = customerInfo.get("name");
		String contactId = map1.get("contactId");
		Map<String, String> contactMap = SQLUtil.getContactArchives(Integer.parseInt(contactId));
		String contactName = contactMap.get("contactName");
		String introduce = map1.get("introduce") + "..."; // 项目介绍
		String introduce2 = introduce;
		if (introduce.length() > 30) {
			introduce2 = introduce.substring(0, 30) + "...";
		}
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, introduce2);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, introduce2);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("customerName", customerName);
			keymap.put("contactName", contactName);
			keymap.put("creator", map1.get("creator"));
			keymap.put("rapporteDate", rapporteDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 0714 新增联系人事件处理
	 */
	public void publishContactArchivesNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String contactId = (String) map.get("contactArchivesId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getContactArchives(Integer.parseInt(contactId));
		String url = domain + "EventNoti/contactArchivesServlet?contactId=" + contactId;
		String title = "您有一条新增联系人信息,请注意查收：";
		String contactName = map1.get("contactName"); // 联系人
		String customerName = map1.get("customerName"); // 客户名称
		String creatorMan = map1.get("creatorMan"); // 创建人
		String currentDate = format3.format(new Date()); // 记录时间
		String enterpriseSynopsis = map1.get("enterpriseSynopsis") + "..."; // 印象描述
		if (enterpriseSynopsis.length() > 30) {
			enterpriseSynopsis = enterpriseSynopsis.substring(0, 30) + "...";
		}
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();

			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, enterpriseSynopsis);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, enterpriseSynopsis);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("customerName", customerName);
			keymap.put("contactName", contactName);
			keymap.put("creatorMan", creatorMan);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 0711 新增客户事件处理
	 */
	public void publishCustomerInfoNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String customerInfoId = (String) map.get("customerInfoId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getCustomerInfo(Integer.parseInt(customerInfoId));
		String title = "您有一条新增客户信息,请注意查收：";
		String name = map1.get("name"); // 客户名称
		String maorContact = map1.get("maorContact"); // 主要联系人
		String businessMan = map1.get("businessMan"); // 业务员
		String currentDate = format3.format(new Date()); // 记录时间
		String businessScope = map1.get("businessScope") + "..."; // 业务范围
		String businessScope2 = businessScope;
		if (businessScope.length() > 30) {
			businessScope2 = businessScope.substring(0, 30) + "...";
		}
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String url = domain + "EventNoti/customerInfoServlet?customerId=" + customerInfoId+"&userid="+list[i]+"&tag=fromSearchCustInfo";
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, businessScope2);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, businessScope2);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("name", name);
			keymap.put("maorContact", maorContact);
			keymap.put("businessMan", businessMan);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}
	

	/**
	 * 合同事件 subiao 20170717
	 * 
	 * @throws ParseException
	 */
	public void publishContractNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String contractManagementId = (String) map.get("contractManagementId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getContactManagement(Integer.parseInt(contractManagementId));
		String title = "你好，你有一条合同签订消息,请注意查阅：";
		String CManagementName = map1.get("CManagementName"); // 合同名称
		String customerId = map1.get("customerId"); // 客户id
		Map<String, String> map12 = SQLUtil.getCustomerInfo(Integer.parseInt(customerId));
		String customerName = map12.get("name"); // 客户id
		String mainContent = "合同名称：&nbsp;" + CManagementName + "&nbsp;&nbsp;客户名称：&nbsp;" + customerName;
		String ciemdingTime = map1.get("ciemdingTime"); // 签订日期
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/contractManagementServlet?contractManagementId=" + contractManagementId;
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("CManagementName", CManagementName);
			keymap.put("customerName", customerName);
			keymap.put("ciemdingTime", ciemdingTime);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 收款事件
	 * @author subiao
	 * @serialData 20170718 financialManagementId
	 */
	
	public void publishFinancialNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String financialManagementId = (String) map.get("financialManagementId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getFinancialManagement(Integer.parseInt(financialManagementId));
		String title = "你好，你有一条收款消息,请注意查阅：";
		String CManagementId = map1.get("CManagementId"); // 合同id
		Map<String, String> map12 = SQLUtil.getContactManagement(Integer.parseInt(CManagementId));
		String CManagementName = map12.get("CManagementName"); // 合同名称
		String customerId = map1.get("customerId"); // 客户id
		Map<String, String> map13 = SQLUtil.getCustomerInfo(Integer.parseInt(customerId));
		String customerName = map13.get("name"); // 客户id
		String sumReceivable = map1.get("sumReceivable"); // 应收金额
		String trueSum = map1.get("trueSum"); // 实收金额
		String collectionDate = map1.get("collectionDate"); // 签订日期
		String mainContent = "合同名称：&nbsp;" + CManagementName + "<br/> 应收金额：&nbsp;" + sumReceivable
				+ "&nbsp;&nbsp;实收金额：&nbsp;" + trueSum;
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/financialManagementServlet?financialManagementId=" + financialManagementId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, mainContent);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("customerName", customerName);
			keymap.put("sumReceivable", sumReceivable);
			keymap.put("trueSum", trueSum);
			keymap.put("collectionDate", collectionDate);
			keymap.put("CManagementName", CManagementName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 产品事件
	 * @author subiao
	 * @serialData 20170719 productsId
	 */
	public void publishProductsNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String productsId = (String) map.get("productsId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProductsManagement(Integer.parseInt(productsId));
		String title = "你好，你有一条新建产品信息,请注意查阅：";
		String productName = map1.get("productName"); // 产品名称
		String model = map1.get("model"); // 型号
		String isNoMain2 = map1.get("isNoMain"); // 是否主营 0 1
		String isNoMain = "是";
		if (isNoMain2.equals("0")) {
			isNoMain = "否";
		}
		String remark = map1.get("remark") + "...";
		if (remark.length() > 30) {
			remark = remark.substring(0, 30) + "...";
		}
		String launch = map1.get("launch"); // 推出时间
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/productsServlet?productsId=" + productsId;
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("productName", productName);
			keymap.put("model", model);
			keymap.put("isNoMain", isNoMain);
			keymap.put("launch", launch);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 周计划事件
	 * @author subiao
	 * @serialData 20170728 weeklyId
	 */
	public void publishWeekPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String weeklyId = (String) map.get("weeklyId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		// userId 去除重复 目前发送给全员
		Map<String, String> map1 = SQLUtil.getWeeklyById(Integer.parseInt(weeklyId));
		String title = "你好，你有一条新建周计划信息,请注意查阅：";
		String weeklyName = map1.get("weeklyName"); // 周计划名称
		String startDate = map1.get("startDate"); // 开始日期
		String endDate = map1.get("endDate"); // 结束日期
		String rapporteurId = map1.get("rapporteurId"); // 计划人
		String summary = map1.get("summary") + "..."; // 周总结
		if (summary.length() > 30) {
			summary = summary.substring(0, 30) + "...";
		}
		String rapporteurName = SQLUtil.getUserDetail(Integer.parseInt(rapporteurId)).getName();
		String rapporteDate = format3.format(new Date());
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 为了直接传值 调到下面
			Long userid = Long.parseLong(list[i]);
			String url = domain + "EventNoti/weeklyServlet?weeklyId=" + weeklyId + "&userid=" + userid + "&sender="
					+ sender;
			System.out.println("weeklyName, startDate, rapporteurName, rapporteDate :" + weeklyName + "  " + startDate
					+ "  " + rapporteurName + "  " + rapporteDate);
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, summary);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, summary);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("weeklyName", weeklyName);
			keymap.put("startDate", startDate);
			keymap.put("endDate", endDate);
			keymap.put("rapporteurName", rapporteurName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 工作计划事件
	 *            （项目 合同）
	 * @author subiao
	 * @serialData 20170801 projectInfoPlanId
	 */
	public void publishProjectInfoPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String sender = event.getUserId();
		String nowDate = format.format(new Date());
		String projectInfoPlanId = (String) map.get("projectInfoPlanId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProjectInfoPlanById(Integer.parseInt(projectInfoPlanId));
		String title = "你好，你有一条新建工作计划信息,请注意查阅：";
		String planName = map1.get("planName"); // 计划名称
		String rapporteurName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName(); // 回复人
		String percent = map1.get("percent").toString()+"%"; // 完成百分比
		String outline = "当前计划已完成 ： " + percent ;
		String dayNum = map1.get("dayNum"); // 工作量 天数
		String endDate = map1.get("endDate"); // 时限
		if("100".equals(map1.get("percent"))){
			remindNewPlan(projectInfoPlanId);
		}
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 为了直接传值 调到下面
			String url = domain + "EventNoti/projectInfoPlanServlet?projectInfoPlanId=" + projectInfoPlanId
					+ "&userid=" + Long.parseLong(list[i]) + "&sender=" + sender; 

			// 保存事件到信息表里****
			saveNew(planName, url, Long.parseLong(list[i]), nowDate, outline);
			saveNewLog(planName, url, Long.parseLong(list[i]), nowDate, outline);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("planName", planName);
			keymap.put("dayNum", dayNum);
			keymap.put("outline", outline);
			keymap.put("endDate", endDate);
			keymap.put("rapporteurName", rapporteurName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 工作计划
	 *            状态更改事件 （项目 合同）
	 * @author subiao
	 * @serialData 20170810 projectInfoPlanId
	 */
	public void publishGGZTProjectInfoPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String sender = event.getUserId();
		String nowDate = format.format(new Date());
		String projectInfoPlanId = (String) map.get("projectInfoPlanId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getProjectInfoPlanById(Integer.parseInt(projectInfoPlanId));
		String planName = map1.get("planName"); // 计划名称
		String percent = map1.get("percent").toString()+"%"; // 完成百分比
		String outline = "当前计划已完成 ： " + percent;
		String title = "你好，你有一条工作计划状态更改信息,请注意查阅：";
		String dayNum = map1.get("dayNum"); // 工作量 天数
		String endDate = map1.get("endDate"); // 时限
		String rapporteurName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName(); // 回复人
		if("100".equals(map1.get("percent"))){
			remindNewPlan(projectInfoPlanId);
		}
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 为了直接传值 调到下面
			String url = domain + "EventNoti/projectInfoPlanServlet?projectInfoPlanId=" + projectInfoPlanId
					+ "&userid=" + Long.parseLong(list[i]) + "&sender=" + sender;
			System.out.println("planName, rapporteurName, dayNum :" + planName + "  " + rapporteurName + "  " + dayNum);
			// 保存事件到信息表里****
			saveNew(planName, url, Long.parseLong(list[i]), nowDate, outline);
			saveNewLog(planName, url, Long.parseLong(list[i]), nowDate, outline);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("planName", planName);
			keymap.put("dayNum", dayNum);
			keymap.put("outline", outline);
			keymap.put("endDate", endDate);
			keymap.put("rapporteurName", rapporteurName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}
	
	public void remindNewPlan(String projectInfoPlanId){
		String ppsql = "SELECT projectInfo_id , contractManagement_id , productionOperationDetail FROM t_projectInfoPlan where id = "+projectInfoPlanId;
		List<Map> ppLists =(List<Map>) eventService.getSuperSession().createSQLQuery(ppsql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(ppLists != null && ppLists.size() >0 ){
            //  2018-01-25 修改 
			//	String psql = "select u.ID as uid , convert(varchar(255),pp.name ) as name , pp.start_Date as start from t_projectInfoPlan pp,t_personnelFiles pf,t_users u where pp.personnelFiles_id = pf.Id and pf.CODE =u.CODE and PROJECTINFOPLAN_ID = "+projectInfoPlanId;
			String psql = "select u.ID as uid , convert(varchar(255),pp.name ) as name , pp.start_Date as start from t_projectInfoPlan pp,t_personnelFiles pf,t_users u where pp.personnelFiles_id = pf.Id and pf.CODE =u.CODE and pp.id = "+projectInfoPlanId;
			List<Map> mapLists =(List<Map>) eventService.getSuperSession().createSQLQuery(psql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(ppLists.get(0).get("projectInfo_id")!=null){
				if(mapLists!=null && mapLists.size()>0){
					for (int j = 0; j < mapLists.size(); j++) {
						BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10018'").uniqueResult();
						String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'项目计划开始提醒','{\"users\":\"" + mapLists.get(j).get("uid") + "\",\"content\":\""+ "有项目计划:"+ mapLists.get(j).get("name")+ "("+ format2.format((Date)mapLists.get(j).get("start"))+ ")可以开始" + "\"}','E','0')";
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					}
				}
			}else if(ppLists.get(0).get("productionOperationDetail")!=null){
				if(mapLists!=null && mapLists.size()>0){
					for (int j = 0; j < mapLists.size(); j++) {
						BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10036'").uniqueResult();
						String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'生产经营计划开始提醒','{\"users\":\"" + mapLists.get(j).get("uid") + "\",\"content\":\""+ "有生产经营计划:"+ mapLists.get(j).get("name")+ "("+ format2.format((Date)mapLists.get(j).get("start"))+ ")可以开始" + "\"}','E','0')";
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					}
				}
			}else {
				if(mapLists!=null && mapLists.size()>0){
					for (int j = 0; j < mapLists.size(); j++) {
						BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10020'").uniqueResult();
						String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'合同计划开始提醒','{\"users\":\"" + mapLists.get(j).get("uid") + "\",\"content\":\""+ "有合同计划:"+ mapLists.get(j).get("name")+ "("+ format2.format((Date)mapLists.get(j).get("start"))+ ")可以开始" + "\"}','E','0')";
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					}
				}
			}
		}
		
	}
	

	/**
	 * @param 开票事件
	 * @author subiao
	 * @serialData 20170803 billingRecordId
	 */
	public void publishBillingRecordNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String billingRecordId = (String) map.get("billingRecordId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getBillingRecordById(Integer.parseInt(billingRecordId));
		String title = "你好，你有一条开票信息,请注意查阅：";
		String sum = map1.get("sum") + " 元"; // 开票金额
		String invoiceTitle = " "; // 开票抬头
		if (invoiceTitle != null) {
			invoiceTitle = map1.get("invoiceTitle"); // 开票抬头
		}
		String customerInfoId = map1.get("customerInfoId"); // 合同ID
		Map<String, String> customerInfoMap = SQLUtil.getCustomerInfo(Integer.parseInt(customerInfoId)); // 获取合同信息
		String customerName = customerInfoMap.get("name"); // 合同名称
		
		String content = map1.get("content") + "..."; // 发票内容
		if (content.length() > 30) {
			content = content.substring(0, 30) + "...";
		}
		
		String rapporteDate = format3.format(new Date()); // 开票时间
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/billingRecordServlet?billingRecordId=" + billingRecordId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, content);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();   
			keymap.put("customerName", customerName);
			keymap.put("sum", sum);
			keymap.put("invoiceTitle", invoiceTitle);
			keymap.put("rapporteDate", rapporteDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 付款事件
	 * @author subiao
	 * @serialData 20170803 paymentorderId
	 */
	public void publishPaymentOrderNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String paymentorderId = (String) map.get("paymentorderId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		// userId 去除重复 目前发送给全员
		Map<String, String> map1 = SQLUtil.getPaymentOrderById(Integer.parseInt(paymentorderId));
		String title = "你好，你有一条付款信息，请注意查阅：";
		String totalMoney = map1.get("totalMoney") + " 元"; // 付款金额
		String supplierId = map1.get("supplierId"); // 供货商ID
		String supplierName = "";
		String maorContact = "";
		String mobile = "";
		if (supplierId != null) {
			Map<String, String> supplierMap = SQLUtil.getSupplierById(Integer.parseInt(supplierId)); // 获取合同信息
			supplierName = supplierMap.get("supplierName"); // 供货商名称
			String maorContact2 = supplierMap.get("maorContact"); // 主要联系人
			if (maorContact2 != null) {
				maorContact = maorContact2;
			}
			String mobile2 = supplierMap.get("mobile"); // 手机号
			if (mobile2 != null) {
				mobile = mobile2;
			}
		}
		String remark = map1.get("remark") + "..."; // 备注
		if (remark.length() > 30) {
			remark = remark.substring(0, 30) + "...";
		}
		String mes = "联系人： " + maorContact + "  手机号： " + mobile;
		String rapporteDate = format3.format(new Date()); // 开票时间
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/paymentOrderServlet?paymentorderId=" + paymentorderId;
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("supplierName", supplierName);
			keymap.put("totalMoney", totalMoney);
			keymap.put("rapporteDate", rapporteDate);
			keymap.put("mes", mes);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 报销单提交事件
	 * @author subiao
	 * @serialData 20170815 expenseFormId
	 */
	public void publishExpenseFormNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String expenseFormId = (String) map.get("expenseFormId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getExpenseFormById(Integer.parseInt(expenseFormId));
		String title = "您好，有新的报销单需要您的审批";
		String money = map1.get("money") + " 元"; // 报销金额
		String personnelId = map1.get("personnelId"); // 申请人ID
		String applyPeopleName = "";
		if (personnelId != null) {
			applyPeopleName = SQLUtil.getPersonnelFilesById(Integer.parseInt(personnelId)).getName(); // 申请人
		}
		String projectId = map1.get("projectId"); // 项目ID
		String projectName = "";
		if (projectId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getProject(Integer.parseInt(projectId)); // 项目信息
			projectName = projectInfoMap.get("name"); // 合同名称
		}
		
		String remark = map1.get("remark") + "..."; // 备注
		if (remark.length() > 30) {
			remark = remark.substring(0, 30) + "...";
		}
		String rapporteDate = format3.format(new Date()); // 申请时间
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/expenseFormServlet?expenseFormId=" + expenseFormId;
		System.out.println("projectName,applyPeopleName,money,  rapporteDate :" + projectName + "  "
				+ applyPeopleName + "  " + money + "  " + rapporteDate);
		// 保存事件到信息表里****
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, remark);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("projectName", projectName);
			keymap.put("rapporteDate", rapporteDate);
			keymap.put("applyPeopleName", applyPeopleName);
			keymap.put("money", money);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 磁盘检查处理 wy 20170613
	 */
	public void spaceCheckerNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("content");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		String url = "#";
		String title = "您有一条磁盘空间消息";
		String currentDate = format.format(new Date());
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			saveNew(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			keymap.put("eventName", event.getName());
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 日报检查处理 wy 20170613
	 */
	public void dailyCheckNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("content");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		String title = "您有一条日报提醒消息";
		String currentDate = format.format(new Date());
		int state = content.length();
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			String userid =list[i].toString();
			System.out.println("接收人：" + userid);
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String url = domain + "EventNoti/dailyCheckServlet?state=" + state+"&userid="+userid;
			saveNew(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			keymap.put("eventName", event.getName());
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 日报信息处理
	 */
	public void publishDailyNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String eventName = event.getName();
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String dailyId = (String) map.get("dailyId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getDaily(Integer.parseInt(dailyId));
		String title = "您好，您有相关工作日报录入成功";
		String currentDate = format.format(new Date());
		String workContext = map1.get("workContext") + "..."; // 当天工作内容
		String workContext2 = workContext;
		if (workContext.length() > 30) {
			workContext2 = workContext.substring(0, 30) + "...";
		}
		
		// 获取用户名
		String rapporteurId = map1.get("rapporteurId");
		String userName = SQLUtil.getUserDetail(Integer.parseInt(rapporteurId)).getName();
		String questions = map1.get("questions"); // 问题 、感想、收获 对应模板中的备注
		String questions2 = questions;
		if (questions2.length() > 30) {
			questions2 = questions2.substring(0, 30) + "...";
		}
		for (int i = 0; i < list.length && !list.equals(""); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			Long userid = Long.parseLong(list[i]);
			String url = domain + "EventNoti/dailyServlet?dailyId=" + dailyId + "&userid=" + userid + "&sender="
					+ sender;
			// 保存事件到信息表里
			saveNew(eventName, url, Long.parseLong(list[i]), nowDate, workContext2);
			saveNewLog(eventName, url, Long.parseLong(list[i]), nowDate, workContext2);
			// 保存到Message表里
			Map<String, String> keymap = new HashMap();
			keymap.put("workContext2", workContext2);
			keymap.put("currentDate", currentDate);
			keymap.put("userName", userName);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 生日提醒 拜访提醒 项目计划开始提醒 项目计划结束提醒 合同计划开始提醒 合同事件结束提醒 合同到期提醒     20170823
	 * 系统检查提醒事件
	 */
	
	
	public void systemRemindCheckNotification(EventNew event) {
		Map<String, Object> map = getMap(event);
		String content = (String) map.get("content");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		String currentDate = format.format(new Date());
		String remindName = event.getName();
		// 这里 发送到系统提醒页面 systemRemind
		String url = domain + "EventNoti/backVisit/systemRemind.jsp?content=" + content + "&currentDate="
				+ currentDate + "&remindName=" + remindName;
		String title = "您好，您有一条" + remindName;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	
	/**
	 * 任务计划滞后反馈提醒 subiao 20170830 暂时使用系统检查模板 系统检查
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
		String title = "您好，您有一条" + remindName;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String userid = list[i];
			// 这里 发送到系统提醒页面 systemRemind
			String url = domain + "EventNoti/backVisit/workPlanLag.jsp?content=" + content + "&currentDate="
					+ currentDate + "&remindName=" + remindName + "&projectInfoPlanId=" + projectInfoPlanId
					+ "&projectInfoPlanName=" + projectInfoPlanName + "&userid=" + userid + "&sender=" + sender;
			// 保存事件到信息表里
			saveNew(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), currentDate, content);
			
			Map<String, String> keymap = new HashMap();
			keymap.put("content", content);
			keymap.put("currentDate", currentDate);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}

	/**
	 * 日报信息回复 调用新评价提醒模板 subiao 20170902
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
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			String userid = list[i];
			// 这里 发送到日报显示页面 systemRemind
			if (map.get("dailyId") != null) {
				String dailyId = (String) map.get("dailyId");
				title = "您好，您有新的日报回复信息";
				url = domain + "/EventNoti/dailyServlet?dailyId=" + dailyId + "&userid=" + userid + "&sender=" + sender;
			}
			if (map.get("customerInfoId") != null) {
				String customerInfoId = (String) map.get("customerInfoId");
				title = "您好，您有新的客户信息回复消息";
				url = domain + "/EventNoti/customerInfoServlet?customerId=" + customerInfoId + "&userid=" + userid
						+ "&sender=" + sender + "&tag=fromSearchCustInfo";
			}
			if (map.get("backVisitId") != null) {
				String backVisitId = (String) map.get("backVisitId");
				title = "您好，您有新的回访回复消息";
				url = domain + "/EventNoti/backVisitServlet?bid=" + backVisitId + "&userid=" + userid
						+ "&sender=" + sender ;
			}
			if (map.get("weeklyId") != null) {
				String weeklyId = (String) map.get("weeklyId");
				title = "您好，您有新的周计划回复消息";
				url = domain + "/EventNoti/weeklyServlet?weeklyId=" + weeklyId + "&userid=" + userid
						+ "&sender=" + sender ;
			}
			
			if (map.get("projectInfoPlanId") != null) {
				String projectInfoPlanId = (String) map.get("projectInfoPlanId");
				title = "您好，您有新的工作计划回复消息";
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
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 加班单提交事件
	 * @author subiao
	 * @serialData 20170825 overTimeBillId
	 */
	public void publishOverTimeBillNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String overTimeBillId = (String) map.get("overTimeBillId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		// String[] list = users.split(",");
		// userId 去除重复 目前发送给全员
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getOverTimeBillById(Integer.parseInt(overTimeBillId));
		String title = "您好，您有一份加班单的申请";
		String style = "加班单提交申请";
		String applyPersonId = map1.get("applyPersonId"); // 申请人ID
		String applyPeopleName = "";
		if (applyPersonId != null) {
			applyPeopleName = SQLUtil.getPersonnelFilesById(Integer.parseInt(applyPersonId)).getName(); // 申请人
		}
		
		String betreffzeile = map1.get("betreffzeile") + "..."; // 备注
		if (betreffzeile.length() > 30) {
			betreffzeile = betreffzeile.substring(0, 30) + "...";
		}
		
		String rapporteDate = format3.format(new Date()); // 申请时间
		
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/overTimeBillServlet?overTimeBillId=" + overTimeBillId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			System.out.println("overTimeBillId  :" + overTimeBillId);
			// 保存事件到信息表里****
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
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 公出单提交事件
	 * @author subiao
	 * @serialData 20170826 onTheRoadBillId
	 */
	public void publishOnTheRoadBillNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String onTheRoadBillId = (String) map.get("onTheRoadBillId");
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getOnTheRoadBillById(Integer.parseInt(onTheRoadBillId));
		String title = "您好，您有一份公出单的申请";
		String style = "公出单提交申请";
		String applyPersonId = map1.get("applyPersonId"); // 申请人ID
		String applyPeopleName = "";
		if (applyPersonId != null) {
			applyPeopleName = SQLUtil.getPersonnelFilesById(Integer.parseInt(applyPersonId)).getName(); // 申请人
		}
		String betreffzeile = map1.get("betreffzeile") + "..."; // 备注
		if (betreffzeile.length() > 30) {
			betreffzeile = betreffzeile.substring(0, 30) + "...";
		}
		String rapporteDate = format3.format(new Date()); // 申请时间
		// 为了直接传值 调到下面
		String url = domain + "EventNoti/onTheRoadBillServlet?onTheRoadBillId=" + onTheRoadBillId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 保存事件到信息表里****
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
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 请假提交事件
	 * @author subiao
	 * @serialData 20170906 leaveBillId
	 */
	public void publishQingjiaSubmitNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String sender = event.getUserId();
		String leaveBillId = (String) map.get("leaveBillId");

		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		String qingjiaName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName();
		TleaveBill tleaveBill = SQLUtil.getQingJia(Integer.parseInt(leaveBillId));
		String type = tleaveBill.getType();
		String typeName = SQLUtil.getNameById(type);
		Date startDate = tleaveBill.getStartDate();
		Date endDate = tleaveBill.getEndDate();
		String time = format.format(startDate) + " 至  " + format.format(endDate);
		String days = tleaveBill.getMainHour().toString()+" 小时 ";
		String yuanyin = tleaveBill.getBetreffzeile();
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			int shenheId = Integer.parseInt(list[i]);
			String openid = SQLUtil.getUserDetail(shenheId).getOpenid();
			// String style = "请假提交申请";
			String title = "亲爱的" + SQLUtil.getUserDetail(shenheId).getName() + "，你有新的请假审批";
			String url = domain + "EventNoti/qingjiaSuccessServlet?leavebillId=" + leaveBillId + "&shenheId="
					+ shenheId + "&qingjiaId=" + sender;
			// 保存事件到信息表里****
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
		saveEventLog(event);// 保存log日志
	}

	/**
	 * @param 请假审批结果事件
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
		String time = format.format(startDate) + " 至  " + format.format(endDate);
		String yuanyin = tleaveBill.getBetreffzeile();
		String status = tleaveBill.getStatus();
		String jieguo = "";
		if (status.equals("1")) {
			jieguo = "同意";
		} else {
			jieguo = "驳回";
		}
		
		// String style = "请假提交申请";
		String url = domain + "EventNoti/resultServlet?leaveBillId=" + leaveBillId;
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			int qingjiaId = Integer.parseInt(list[i]);
			String openid = SQLUtil.getUserDetail(qingjiaId).getOpenid();
			System.out.println("leaveBillId  :" + leaveBillId);

			String qingjiaName = SQLUtil.getUserDetail(qingjiaId).getName();
			String title = "亲爱的" + qingjiaName + "，你的请假审批结果如下";
			System.out.println("qingjiaName, typeName   time :" + qingjiaName + "  " + typeName + "  " + time);
			// 保存事件到信息表里****
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
		saveEventLog(event);// 保存log日志
	}
	

	/**
	 * @param 项目周计划
	 *            提交事件
	 * @author subiao
	 * @serialData 20170828 weekPlanId
	 */
	public void publishProjectWeekPlanNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String weekPlanId = (String) map.get("weekPlanId"); // 项目周计划的ID值
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		Map<String, String> map1 = SQLUtil.getWeekPlanById(Integer.parseInt(weekPlanId));
		String title = "您好，您有一条项目周计划信息录入通知";
		String sender = event.getUserId();
		String userName = "";
		if (sender != null) {
			userName = SQLUtil.getUserDetail(Integer.parseInt(sender)).getName(); // 申请人
		}
		String thisPlan = map1.get("thisPlan"); // 本周计划
		String weeklyId = map1.get("weeklyId"); // 周计划ID
		String projectId = map1.get("projectInfoId"); // 项目ID
		String projectName = "";
		String customerInfoId = null;
		String customerInfoName = "";
		String contactId = null;
		String contactName = "";
		if (projectId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getProject(Integer.parseInt(projectId)); // 项目信息
			projectName = projectInfoMap.get("name"); // 项目ID
			customerInfoId = projectInfoMap.get("customerInfoId"); // 客户id
			contactId = projectInfoMap.get("contactId"); // 联系人id
			
		}
		if (customerInfoId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getCustomerInfo(Integer.parseInt(customerInfoId)); // 客户信息
			customerInfoName = projectInfoMap.get("name");
		}
		if (contactId != null) {
			Map<String, String> projectInfoMap = SQLUtil.getContactArchives(Integer.parseInt(contactId)); // 联系人信息
			contactName = projectInfoMap.get("contactName");
		}
		
		String rapporteDate = format3.format(new Date()); // 申请时间
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 为了直接传值 调到下面
			Long userid = Long.parseLong(list[i]);
			String url = domain + "EventNoti/listWeeklyPlanServlet?weeklyId=" + weeklyId + "&userid=" + userid
					+ "&sender=" + sender + "&weekPlanId=" + weekPlanId + "&projectId=" + projectId + "&projectName="
					+ projectName + "&state=FromProject";
			// 保存事件到信息表里****
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
		saveEventLog(event);// 保存log日志
	}
	
	/**
	 * @param 待审批通知  提交事件
	 * @author subiao
	 * @serialData 20180112  runTaskId
	 */
	
	public void publishRunTaskPendingNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
		String runTaskId = (String) map.get("runTaskId"); // 任务流程id
		String historyTaskinstId = (String) map.get("historyTaskinstId");  // 历史流程id
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		
		Map<String, String> map1 = SQLUtil.getRunTask(Integer.parseInt(runTaskId)); // 获取任务流信息
		String flowId = map1.get("flowId"); // 流程类型id 根据列出类型id 判断去往不同处理页面 -- 待定 可能有相关通用页面
		String submitPerId = map1.get("submitPerId"); // 申请人id
		String createTime = map1.get("createTime"); // 申请时间
		PersonnelFiles pFiles = SQLUtil.getPersonnelFilesById(Integer.parseInt(submitPerId));
		String submitPerName = pFiles.getName(); // 申请人姓名
		Long deptId = pFiles.getDeptId();
		Department dept = SQLUtil.getDeptById(deptId);
		String deptName = dept.getName(); // 申请人部门名称 
		String name = (String)map.get("name"); // 2018-01-11,丁翠提交了任务为用车申请审批
		
		String title = "您好，您有一个待审批事项";
//		String sender = event.getUserId();  // 流程节点的发送人 待定。。。
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 为了直接传值 调到下面
//			Long userid = Long.parseLong(list[i]);  // 接收人用户id
	        // 0112 具体链接地址待定 
//			String url = domain + "EventNoti/runTaskServlet?runTaskId=" + runTaskId + "&userid=" + userid+"&sender="+sender ;
			String url = domain + "EventNoti/runtaskServlet?method=detail&id=" + runTaskId +"&historyTaskinstId="+historyTaskinstId;
			
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// 保存事件到  历史 信息表里****
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// 保存事件到  历史 信息表里****
			
			Map<String, String> keymap = new HashMap();
			keymap.put("submitPerName", submitPerName);
			keymap.put("deptName", deptName);
			keymap.put("name", name);
			keymap.put("createTime", createTime);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}
	
	/**
	 * @param  审批结果通知  提交事件
	 * @author subiao
	 * @serialData 20180112  runTaskId（未用）  runPointId
	 */
	
	public void publishRunTaskResultNotification(EventNew event) throws ParseException {
		Map<String, Object> map = getMap(event);
		String nowDate = format.format(new Date());
//		String runTaskId = (String) map.get("runTaskId"); // 任务流程id
		String runPointId = (String) map.get("runPointId"); // 审批节点id
		String historyTaskinstId = (String) map.get("historyTaskinstId"); // 历史人物信息  
		String users2 = (String) map.get("users");
		String users = users2.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] list = users.split(",");
		
		Map<String, String> map1 = SQLUtil.getRunPoint(Integer.parseInt(runPointId)); // 获取 审批节点 信息
		String flowId = map1.get("flowId"); // 流程类型id 根据列出类型id 判断去往不同处理页面 -- 待定 可能有相关通用页面
		String inspectPserId = map1.get("inspectPserId"); // 审批人id
		String createTime = map1.get("createTime"); // 申请时间
		String remark =map1.get("remark"); // 审批意见 
		if ("null".equals(remark)) {
			remark = "";
		}
		String resultId = map1.get("resultId"); // 审批结果 id
		String resultName =SQLUtil.getCodeValueName(Integer.parseInt(resultId));
		
		Map<String, String> map2 =SQLUtil.getPoint(Integer.parseInt(flowId));
		String flowTypeName = map2.get("name");
		PersonnelFiles pFiles = SQLUtil.getPersonnelFilesById(Integer.parseInt(inspectPserId));
		String inspectPserName = pFiles.getName(); // 审批人姓名
		
		String name = (String)map.get("name"); // 2018-01-11,丁翠提交了任务为用车申请审批
		
//		String title = "您好，你有一个申请已完成审批";
		String title = "您好，你有一条申请已完成信息";
		String sender = event.getUserId();  // 流程节点的发送人 待定。。。
		
		for (int i = 0; i < list.length && !"".equals(list[i]); i++) {
			System.out.println("接收人：" + list[i].toString());
			String openid = SQLUtil.getUserDetail(Integer.parseInt(list[i])).getOpenid();
			// 为了直接传值 调到下面
			Long userid = Long.parseLong(list[i]);  // 接收人用户id
			// 0112 具体链接地址待定 
//			String url = domain + "EventNoti/runPointServlet?runPointId=" + runPointId + "&userid=" + userid+"&sender="+sender ;
			String url = domain + "EventNoti/historyTaskinstServlet?method=detail&id=" + historyTaskinstId ;
			saveNew(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// 保存事件到  历史 信息表里****
			saveNewLog(event.getName(), url, Long.parseLong(list[i]), nowDate, name); 	// 保存事件到  历史 信息表里****
			
			Map<String, String> keymap = new HashMap();
			keymap.put("flowTypeName", flowTypeName);
			keymap.put("inspectPserName", inspectPserName);
			keymap.put("resultName", resultName);
			keymap.put("remark", remark);
			keymap.put("createTime", createTime);
			String keywords = JSONObject.fromObject(keymap).toString();
			saveMessage(event,title,openid,url,keywords);
		}
		saveEventLog(event);// 保存log日志
	}
	

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
